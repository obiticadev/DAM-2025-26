"""
Tests para los ejercicios XQuery (18-25).
Cada test lee el fichero .xq del usuario, lo ejecuta con saxonche (Saxon-HE)
contra el XML correspondiente y valida los resultados.

NOTA: saxonche debe estar instalado: pip install saxonche
Los tests XQUF (ej24) usan copias temporales del XML para idempotencia (Regla 9).
"""
import pytest
import os
import re
import shutil
import tempfile
from lxml import etree

try:
    from saxonche import PySaxonProcessor
    SAXON_AVAILABLE = True
except ImportError:
    SAXON_AVAILABLE = False

from .conftest import BASE_DIR, EJERCICIOS_DIR, XML_DATA_DIR

# Saltar todos los tests si saxonche no está instalado
pytestmark = pytest.mark.skipif(
    not SAXON_AVAILABLE,
    reason="saxonche no está instalado. Ejecuta: pip install saxonche"
)


def run_xquery(xq_filename: str, xml_filename: str = "biblioteca.xml",
               extra_xml: dict = None) -> str:
    """
    Ejecuta un fichero XQuery contra un XML y devuelve el resultado como string.
    extra_xml: dict de {nombre_variable: fichero_xml} para variables externas.
    """
    xq_path = os.path.join(EJERCICIOS_DIR, "xquery", xq_filename)
    xml_path = os.path.join(XML_DATA_DIR, xml_filename)

    with open(xq_path, "r", encoding="utf-8") as f:
        xquery_code = f.read()

    with PySaxonProcessor(license=False) as proc:
        xq_proc = proc.new_xquery_processor()

        # Cargar XML principal como contexto
        node = proc.parse_xml(xml_file_name=os.path.abspath(xml_path))
        xq_proc.set_context(xdm_item=node)

        # Cargar XMLs adicionales como variables externas
        if extra_xml:
            for var_name, xml_file in extra_xml.items():
                extra_path = os.path.join(XML_DATA_DIR, xml_file)
                extra_node = proc.parse_xml(
                    xml_file_name=os.path.abspath(extra_path))
                xq_proc.set_parameter(var_name, extra_node)

        result = xq_proc.run_query_to_string(query_text=xquery_code)
        return result or ""


def run_xquery_apartado(xq_filename: str, apartado_index: int,
                        xml_filename: str = "biblioteca.xml",
                        extra_xml: dict = None) -> str:
    """
    Ejecuta SOLO un apartado de un fichero XQuery multi-apartado.
    Extrae el bloque de código entre el marcador de apartado y el siguiente.
    """
    xq_path = os.path.join(EJERCICIOS_DIR, "xquery", xq_filename)
    xml_path = os.path.join(XML_DATA_DIR, xml_filename)

    with open(xq_path, "r", encoding="utf-8") as f:
        content = f.read()

    # Buscar los bloques de apartados
    # Patrón: buscar desde "(: apartado X" hasta el siguiente "(: apartado" o fin
    pattern = r'\(:\s*apartado\s+[a-z]\s'
    matches = list(re.finditer(pattern, content))

    if apartado_index >= len(matches):
        pytest.skip(f"Apartado {apartado_index} no encontrado en {xq_filename}")

    start = matches[apartado_index].start()
    end = matches[apartado_index + 1].start() if apartado_index + 1 < len(matches) else len(content)

    block = content[start:end].strip()

    # Extraer el código real (lo que NO es comentario ni ())
    # Buscar la última expresión no vacía y no comentario
    lines = block.split("\n")
    code_lines = []
    in_comment = False
    for line in lines:
        stripped = line.strip()
        if "(:" in stripped and ":)" in stripped:
            continue  # Comentario de línea completa
        if "(:" in stripped:
            in_comment = True
            continue
        if ":)" in stripped:
            in_comment = False
            continue
        if in_comment:
            continue
        if stripped and stripped != "()":
            code_lines.append(line)

    xquery_code = "\n".join(code_lines).strip()

    if not xquery_code or xquery_code == "()":
        pytest.skip(f"Apartado {apartado_index} no completado aún")

    with PySaxonProcessor(license=False) as proc:
        xq_proc = proc.new_xquery_processor()

        node = proc.parse_xml(xml_file_name=os.path.abspath(xml_path))
        xq_proc.set_context(xdm_item=node)

        if extra_xml:
            for var_name, xml_file in extra_xml.items():
                extra_path = os.path.join(XML_DATA_DIR, xml_file)
                extra_node = proc.parse_xml(
                    xml_file_name=os.path.abspath(extra_path))
                xq_proc.set_parameter(var_name, extra_node)

        result = xq_proc.run_query_to_string(query_text=xquery_code)
        return result or ""


# =============================================================================
# Ejercicio 18 — Ruta Simple
# =============================================================================
class TestEj18RutaSimple:
    """Tests para ej18_ruta_simple.xq contra biblioteca.xml"""

    def test_a_todos_titulos(self):
        result = run_xquery_apartado("ej18_ruta_simple.xq", 0)
        assert "Don Quijote" in result
        assert "Cien años de soledad" in result
        assert "La metamorfosis" in result

    def test_b_titulo_libro_id2(self):
        result = run_xquery_apartado("ej18_ruta_simple.xq", 1)
        assert "Cien años de soledad" in result

    def test_c_todos_autores(self):
        result = run_xquery_apartado("ej18_ruta_simple.xq", 2)
        assert "Cervantes" in result
        assert "Kafka" in result

    def test_d_libros_novela(self):
        result = run_xquery_apartado("ej18_ruta_simple.xq", 3)
        assert "Don Quijote" in result
        assert "Cien años de soledad" in result
        assert "El arte de la guerra" not in result

    def test_e_precio_ultimo_libro(self):
        result = run_xquery_apartado("ej18_ruta_simple.xq", 4)
        assert "9.50" in result or "9.5" in result


# =============================================================================
# Ejercicio 19 — FLWOR Básico
# =============================================================================
class TestEj19FlworBasico:
    """Tests para ej19_flwor_basico.xq contra biblioteca.xml"""

    def test_a_todos_titulos(self):
        result = run_xquery_apartado("ej19_flwor_basico.xq", 0)
        assert "<titulo>" in result
        assert "Don Quijote" in result
        assert result.count("<titulo>") == 5

    def test_b_libros_caros_ordenados(self):
        result = run_xquery_apartado("ej19_flwor_basico.xq", 1)
        assert "<libro-caro>" in result
        # Solo 3 libros con precio > 10: Don Quijote (17.50), Cien años (22), Veinte poemas (12)
        assert "Don Quijote" in result
        assert "Cien" in result
        # El más caro primero (descendente)
        pos_cien = result.find("Cien")
        pos_don = result.find("Don Quijote")
        assert pos_cien < pos_don  # 22.00 antes que 17.50

    def test_c_libros_modernos(self):
        result = run_xquery_apartado("ej19_flwor_basico.xq", 2)
        assert "<moderno>" in result
        assert "Cien años de soledad" in result
        assert "La metamorfosis" in result
        assert "El arte de la guerra" not in result  # año -500


# =============================================================================
# Ejercicio 20 — FLWOR con let
# =============================================================================
class TestEj20FlworLet:
    """Tests para ej20_flwor_let.xq contra biblioteca.xml"""

    def test_a_precio_con_iva(self):
        result = run_xquery_apartado("ej20_flwor_let.xq", 0)
        assert "<libro-iva>" in result
        assert "<precio-iva>" in result
        # Don Quijote: 17.50 * 1.21 = 21.175
        assert "21.175" in result or "21.18" in result

    def test_b_ofertas_menos_15(self):
        result = run_xquery_apartado("ej20_flwor_let.xq", 1)
        assert "<oferta>" in result
        # La metamorfosis: 8.99 * 1.21 = 10.88 < 15 ✓
        # El arte de la guerra: 9.50 * 1.21 = 11.495 < 15 ✓
        # Veinte poemas: 12 * 1.21 = 14.52 < 15 ✓
        assert "La metamorfosis" in result
        assert "El arte de la guerra" in result
        # Don Quijote: 17.50 * 1.21 = 21.175 > 15 ✗
        assert "Don Quijote" not in result

    def test_c_estadisticas(self):
        result = run_xquery_apartado("ej20_flwor_let.xq", 2)
        assert "<stats>" in result
        assert "<total>" in result
        assert "5" in result  # 5 libros


# =============================================================================
# Ejercicio 21 — Funciones de Cadena
# =============================================================================
class TestEj21FuncionesCadena:
    """Tests para ej21_funciones_cadena.xq contra biblioteca.xml"""

    def test_a_contiene_de(self):
        result = run_xquery_apartado("ej21_funciones_cadena.xq", 0)
        assert "Cien años de soledad" in result
        assert "El arte de la guerra" in result

    def test_b_empieza_por_c(self):
        result = run_xquery_apartado("ej21_funciones_cadena.xq", 1)
        assert "Cervantes" in result

    def test_c_titulo_largo(self):
        result = run_xquery_apartado("ej21_funciones_cadena.xq", 2)
        assert "<titulo-largo>" in result
        # "Cien años de soledad" = 21 chars > 15
        assert "Cien" in result
        # "Veinte poemas de amor" = 21 chars > 15
        assert "Veinte" in result
        # "El arte de la guerra" = 20 chars > 15
        assert "arte" in result

    def test_d_mayusculas(self):
        result = run_xquery_apartado("ej21_funciones_cadena.xq", 3)
        assert "<titulo-upper>" in result
        assert "DON QUIJOTE" in result
        assert result.count("<titulo-upper>") == 5


# =============================================================================
# Ejercicio 22 — Agregación
# =============================================================================
class TestEj22Agregacion:
    """Tests para ej22_agregacion.xq contra biblioteca.xml"""

    def test_tiene_estadisticas(self):
        result = run_xquery("ej22_agregacion.xq")
        assert "<estadisticas>" in result

    def test_total_libros(self):
        result = run_xquery("ej22_agregacion.xq")
        tree = etree.fromstring(result.encode("utf-8"))
        total = tree.find("total-libros")
        assert total is not None
        assert total.text.strip() == "5"

    def test_precio_total(self):
        result = run_xquery("ej22_agregacion.xq")
        tree = etree.fromstring(result.encode("utf-8"))
        total = tree.find("precio-total")
        assert total is not None
        # 17.50 + 22.00 + 8.99 + 12.00 + 9.50 = 69.99
        assert abs(float(total.text.strip()) - 69.99) < 0.01

    def test_precio_maximo(self):
        result = run_xquery("ej22_agregacion.xq")
        tree = etree.fromstring(result.encode("utf-8"))
        maximo = tree.find("precio-maximo")
        assert maximo is not None
        assert abs(float(maximo.text.strip()) - 22.00) < 0.01

    def test_libros_novela(self):
        result = run_xquery("ej22_agregacion.xq")
        tree = etree.fromstring(result.encode("utf-8"))
        novelas = tree.find("libros-novela")
        assert novelas is not None
        assert novelas.text.strip() == "3"


# =============================================================================
# Ejercicio 23 — Joins
# =============================================================================
class TestEj23Joins:
    """Tests para ej23_joins.xq contra biblioteca.xml + autores.xml"""

    def test_tiene_resultados(self):
        result = run_xquery(
            "ej23_joins.xq", "biblioteca.xml",
            extra_xml={
                "biblioteca": "biblioteca.xml",
                "autores": "autores.xml"
            }
        )
        assert "<libro-completo>" in result

    def test_tiene_5_resultados(self):
        result = run_xquery(
            "ej23_joins.xq", "biblioteca.xml",
            extra_xml={
                "biblioteca": "biblioteca.xml",
                "autores": "autores.xml"
            }
        )
        assert result.count("<libro-completo>") == 5

    def test_contiene_nacionalidad(self):
        result = run_xquery(
            "ej23_joins.xq", "biblioteca.xml",
            extra_xml={
                "biblioteca": "biblioteca.xml",
                "autores": "autores.xml"
            }
        )
        assert "<nacionalidad>" in result
        assert "Colombiana" in result  # García Márquez
        assert "Española" in result  # Cervantes

    def test_contiene_siglo(self):
        result = run_xquery(
            "ej23_joins.xq", "biblioteca.xml",
            extra_xml={
                "biblioteca": "biblioteca.xml",
                "autores": "autores.xml"
            }
        )
        assert "<siglo>" in result
        assert "XVII" in result  # Cervantes


# =============================================================================
# Ejercicio 24 — XQUF (con copia temporal — Regla 9)
# =============================================================================
class TestEj24Xquf:
    """
    Tests para ej24_xquf.xq contra una COPIA de biblioteca.xml.
    Cada test usa un fichero temporal para no mutar el original.
    """

    def _run_xquf_apartado(self, apartado_index: int) -> etree._Element:
        """Ejecuta un apartado XQUF contra una copia temporal y devuelve el XML resultante."""
        xq_path = os.path.join(EJERCICIOS_DIR, "xquery", "ej24_xquf.xq")
        xml_src = os.path.join(XML_DATA_DIR, "biblioteca.xml")

        with open(xq_path, "r", encoding="utf-8") as f:
            content = f.read()

        # Extraer apartado
        pattern = r'\(:\s*apartado\s+[a-z]\s'
        matches = list(re.finditer(pattern, content))
        if apartado_index >= len(matches):
            pytest.skip("Apartado no encontrado")

        start = matches[apartado_index].start()
        end = matches[apartado_index + 1].start() if apartado_index + 1 < len(matches) else len(content)
        block = content[start:end].strip()

        # Extraer código no comentario
        lines = block.split("\n")
        code_lines = []
        in_comment = False
        for line in lines:
            stripped = line.strip()
            if "(:" in stripped and ":)" in stripped:
                continue
            if "(:" in stripped:
                in_comment = True
                continue
            if ":)" in stripped:
                in_comment = False
                continue
            if in_comment:
                continue
            if stripped and stripped != "()":
                code_lines.append(line)

        xquery_code = "\n".join(code_lines).strip()
        if not xquery_code or xquery_code == "()":
            pytest.skip("Apartado no completado aún")

        # Crear copia temporal del XML (Regla 9: idempotencia)
        with tempfile.NamedTemporaryFile(suffix=".xml", delete=False, mode="w",
                                         encoding="utf-8") as tmp:
            with open(xml_src, "r", encoding="utf-8") as src:
                tmp.write(src.read())
            tmp_path = tmp.name

        try:
            # Reemplazar rutas relativas por la ruta temporal
            xquery_code = xquery_code.replace(
                "/biblioteca", f"doc('{tmp_path.replace(os.sep, '/')}')/biblioteca"
            )

            with PySaxonProcessor(license=False) as proc:
                xq_proc = proc.new_xquery_processor()
                xq_proc.run_query_to_string(query_text=xquery_code)

            # Leer el XML modificado
            return etree.parse(tmp_path).getroot()
        finally:
            os.unlink(tmp_path)

    def test_a_insertar_libro(self):
        root = self._run_xquf_apartado(0)
        libros = root.findall("libro")
        assert len(libros) == 6
        nuevo = root.find("libro[@id='6']")
        assert nuevo is not None
        assert nuevo.find("titulo").text == "El señor de los anillos"

    def test_b_cambiar_precio(self):
        root = self._run_xquf_apartado(1)
        libro2 = root.find("libro[@id='2']")
        assert libro2.find("precio").text == "19.99"

    def test_c_añadir_atributo(self):
        root = self._run_xquf_apartado(2)
        libro1 = root.find("libro[@id='1']")
        assert libro1.get("disponible") == "true"

    def test_d_borrar_caros(self):
        root = self._run_xquf_apartado(3)
        for libro in root.findall("libro"):
            assert float(libro.find("precio").text) <= 20


# =============================================================================
# Ejercicio 25 — Prólogo y Funciones
# =============================================================================
class TestEj25PrologoFunciones:
    """Tests para ej25_prologo_funciones.xq contra biblioteca.xml"""

    def test_tiene_resultados(self):
        result = run_xquery("ej25_prologo_funciones.xq")
        assert "<libro-clasificado>" in result

    def test_tiene_5_resultados(self):
        result = run_xquery("ej25_prologo_funciones.xq")
        assert result.count("<libro-clasificado>") == 5

    def test_precio_iva_correcto(self):
        result = run_xquery("ej25_prologo_funciones.xq")
        assert "<precio-iva>" in result
        # Don Quijote: 17.50 * 1.21 = 21.175
        assert "21.175" in result or "21.18" in result

    def test_categorias_correctas(self):
        result = run_xquery("ej25_prologo_funciones.xq")
        assert "<categoria>" in result
        # Cien años: 22.00 > 20 → premium
        assert "premium" in result
        # Don Quijote: 17.50 > 10 → normal
        assert "normal" in result
        # La metamorfosis: 8.99 < 10 → económico
        # El arte de la guerra: 9.50 < 10 → económico
        assert "econ" in result.lower()
