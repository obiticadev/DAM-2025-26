"""
Tests para el Bloque IV — Reto Final (XQuery Joins, XQUF, XSLT Dashboard).
"""
import pytest
import os
import tempfile
from lxml import etree
from .conftest import parse_xml, BASE_DIR, EJERCICIOS_DIR, XML_DATA_DIR

try:
    from saxonche import PySaxonProcessor
    SAXON_AVAILABLE = True
except ImportError:
    SAXON_AVAILABLE = False

pytestmark = pytest.mark.skipif(
    not SAXON_AVAILABLE,
    reason="saxonche no está instalado"
)

# Helper para ejecutar XQuery (importado/adaptado de test_xquery.py)
def run_xquery(xq_filename: str, xml_filename: str, extra_xml: dict = None) -> str:
    xq_path = os.path.join(EJERCICIOS_DIR, "xquery", xq_filename)
    xml_path = os.path.join(XML_DATA_DIR, xml_filename)
    with open(xq_path, "r", encoding="utf-8") as f:
        xquery_code = f.read()
    with PySaxonProcessor(license=False) as proc:
        xq_proc = proc.new_xquery_processor()
        node = proc.parse_xml(xml_file_name=os.path.abspath(xml_path))
        xq_proc.set_context(xdm_item=node)
        if extra_xml:
            for var_name, xml_file in extra_xml.items():
                extra_path = os.path.join(XML_DATA_DIR, xml_file)
                extra_node = proc.parse_xml(xml_file_name=os.path.abspath(extra_path))
                xq_proc.set_parameter(var_name, extra_node)
        return xq_proc.run_query_to_string(query_text=xquery_code) or ""

import re
def run_xquf_apartado(xq_filename: str, apartado_index: int, xml_filename: str) -> etree._Element:
    xq_path = os.path.join(EJERCICIOS_DIR, "xquery", xq_filename)
    xml_src = os.path.join(XML_DATA_DIR, xml_filename)
    with open(xq_path, "r", encoding="utf-8") as f:
        content = f.read()
    pattern = r'\(:\s*apartado\s+[a-z]\s'
    matches = list(re.finditer(pattern, content))
    if apartado_index >= len(matches):
        pytest.skip("Apartado no encontrado")
    start = matches[apartado_index].start()
    end = matches[apartado_index + 1].start() if apartado_index + 1 < len(matches) else len(content)
    block = content[start:end]
    code_lines = [l for l in block.split("\n") if l.strip() and not "(:" in l and not ":)" in l and l.strip() != "()"]
    xquery_code = "\n".join(code_lines).strip()
    if not xquery_code:
        pytest.skip("Apartado no completado")
    with tempfile.NamedTemporaryFile(suffix=".xml", delete=False, mode="w", encoding="utf-8") as tmp:
        with open(xml_src, "r", encoding="utf-8") as src:
            tmp.write(src.read())
        tmp_path = tmp.name
    try:
        xquery_code = xquery_code.replace("/erp", f"doc('{tmp_path.replace(os.sep, '/')}')/erp")
        with PySaxonProcessor(license=False) as proc:
            xq_proc = proc.new_xquery_processor()
            xq_proc.run_query_to_string(query_text=xquery_code)
        return etree.parse(tmp_path).getroot()
    finally:
        os.unlink(tmp_path)


# Helper XSLT
def apply_xslt(xml_filename: str, xsl_filename: str) -> etree._Element:
    xml_path = os.path.join(XML_DATA_DIR, xml_filename)
    xsl_path = os.path.join(EJERCICIOS_DIR, "xslt", xsl_filename)
    xml_doc = etree.parse(xml_path)
    xsl_doc = etree.parse(xsl_path)
    transform = etree.XSLT(xsl_doc)
    return transform(xml_doc)


class TestRetoJoin:
    """Tests para reto_final_join.xq"""
    def test_join_genera_inventario_completo(self):
        result = run_xquery("reto_final_join.xq", "erp_productos.xml", 
                            {"erp_productos": "erp_productos.xml", "erp_proveedores": "erp_proveedores.xml"})
        assert "<inventario-completo>" in result
        
    def test_join_solo_activos_y_proveedores_correctos(self):
        result = run_xquery("reto_final_join.xq", "erp_productos.xml", 
                            {"erp_productos": "erp_productos.xml", "erp_proveedores": "erp_proveedores.xml"})
        tree = etree.fromstring(result.encode("utf-8"))
        items = tree.findall("item")
        assert len(items) == 3  # 3 activos, 1 obsoleto
        
        # PROD-001 prov_id="PR-A" (TechCorp Solutions, USA)
        prod1 = tree.find("item[@id='PROD-001']")
        assert prod1 is not None
        assert prod1.find("proveedor").text == "TechCorp Solutions"
        assert prod1.find("pais").text == "USA"

class TestRetoUpdate:
    """Tests para reto_final_update.xq"""
    def test_a_actualizar_stock(self):
        root = run_xquf_apartado("reto_final_update.xq", 0, "erp_productos.xml")
        p = root.find(".//producto[@id='PROD-001']")
        assert p.find("stock").text == "4"

    def test_b_insertar_pedido(self):
        root = run_xquf_apartado("reto_final_update.xq", 1, "erp_productos.xml")
        pedidos = root.find("pedidos")
        assert len(pedidos.findall("pedido")) == 1
        assert pedidos.find("pedido").get("id") == "PED-999"

    def test_c_borrar_obsoletos(self):
        root = run_xquf_apartado("reto_final_update.xq", 2, "erp_productos.xml")
        obsoletos = root.findall(".//producto[estado='obsoleto']")
        assert len(obsoletos) == 0

class TestRetoDashboard:
    """Tests para reto_dashboard.xsl"""
    def test_dashboard_estructura(self):
        result = apply_xslt("inventario-completo.xml", "reto_dashboard.xsl")
        html = str(result)
        assert "Dashboard del ERP" in html
        assert "<table" in html
        assert "<footer" in html

    def test_dashboard_warning_class(self):
        result = apply_xslt("inventario-completo.xml", "reto_dashboard.xsl")
        html = str(result)
        tree = etree.HTML(html)
        # PROD-003 stock 0 (warning), PROD-001 stock 5 (warning), PROD-002 stock 999
        warnings = tree.xpath("//tr[@class='warning']")
        assert len(warnings) == 2

    def test_dashboard_footer_stats(self):
        result = apply_xslt("inventario-completo.xml", "reto_dashboard.xsl")
        html = str(result)
        tree = etree.HTML(html)
        footer_text = "".join(tree.xpath("//footer//text()"))
        assert "3" in footer_text  # total activos
        assert "4150" in footer_text or "4150.0" in footer_text # sum(2500+1200+450)
