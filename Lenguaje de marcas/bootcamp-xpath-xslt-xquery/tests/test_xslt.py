"""
Tests para los ejercicios XSLT (09-17).
Cada test aplica la hoja XSLT del usuario contra el XML correspondiente
y valida la estructura y contenido del resultado.
"""
import pytest
import os
from lxml import etree
from .conftest import parse_xml, BASE_DIR, EJERCICIOS_DIR


def apply_xslt(xml_filename: str, xsl_filename: str, params: dict = None) -> etree._Element:
    """Aplica una hoja XSLT a un XML y devuelve el resultado parseado."""
    xml_path = os.path.join(BASE_DIR, "src", "xml_data", xml_filename)
    xsl_path = os.path.join(EJERCICIOS_DIR, "xslt", xsl_filename)

    xml_doc = etree.parse(xml_path)
    xsl_doc = etree.parse(xsl_path)
    transform = etree.XSLT(xsl_doc)

    if params:
        str_params = {}
        for k, v in params.items():
            if isinstance(v, str):
                str_params[k] = etree.XSLT.strparam(v)
            else:
                str_params[k] = str(v)
        result = transform(xml_doc, **str_params)
    else:
        result = transform(xml_doc)
    return result


def result_to_string(result) -> str:
    """Convierte el resultado XSLT a string."""
    return str(result)


def result_to_tree(result) -> etree._Element:
    """Convierte el resultado XSLT a un árbol XML/HTML parseable."""
    text = str(result)
    try:
        return etree.HTML(text)
    except Exception:
        return etree.fromstring(text.encode("utf-8"))


# =============================================================================
# Ejercicio 09 — Plantilla Básica
# =============================================================================
class TestEj09PlantillaBasica:
    """Tests para ej09_plantilla_basica.xsl contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("catalogo.xml", "ej09_plantilla_basica.xsl")
        self.html = result_to_tree(self.result)

    def test_tiene_titulo_html(self):
        """Debe tener un <title> con 'Catálogo de Libros'"""
        titles = self.html.xpath("//title")
        assert len(titles) >= 1
        assert "Catálogo" in titles[0].text

    def test_tiene_h1(self):
        """Debe tener un <h1>"""
        h1s = self.html.xpath("//h1")
        assert len(h1s) >= 1

    def test_tiene_5_li(self):
        """Debe tener 5 <li>, uno por cada libro"""
        lis = self.html.xpath("//li")
        assert len(lis) == 5

    def test_li_contienen_titulos(self):
        """Los <li> deben contener los títulos de los libros"""
        lis = self.html.xpath("//li")
        textos = [li.text or "" for li in lis]
        all_text = " ".join(textos)
        assert "El Quijote" in all_text
        assert "Cien años de soledad" in all_text
        assert "La sombra del viento" in all_text


# =============================================================================
# Ejercicio 10 — for-each y sort
# =============================================================================
class TestEj10ForeachSort:
    """Tests para ej10_foreach_sort.xsl contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("catalogo.xml", "ej10_foreach_sort.xsl")
        self.html = result_to_tree(self.result)

    def test_tiene_tabla(self):
        """Debe contener una tabla"""
        tables = self.html.xpath("//table")
        assert len(tables) >= 1

    def test_tiene_cabecera_tabla(self):
        """Debe tener cabecera con Título, Autor, Precio, Año"""
        ths = self.html.xpath("//th")
        th_texts = [th.text or "" for th in ths]
        assert any("tulo" in t for t in th_texts)  # Título
        assert any("Autor" in t for t in th_texts)
        assert any("Precio" in t for t in th_texts)

    def test_tiene_5_filas_datos(self):
        """Debe tener 5 filas de datos (+ 1 cabecera = 6 tr)"""
        trs = self.html.xpath("//table//tr")
        assert len(trs) == 6  # 1 cabecera + 5 datos

    def test_orden_precio_descendente(self):
        """Los libros deben estar ordenados por precio descendente"""
        # El primer libro en la tabla debe ser el más caro (22.00)
        tds_first_row = self.html.xpath("//table//tr[2]/td")
        if tds_first_row:
            first_title = tds_first_row[0].text or ""
            assert "Cien" in first_title  # Cien años de soledad = 22.00 (más caro)

    def test_precio_con_eur(self):
        """Los precios deben llevar ' EUR'"""
        text = result_to_string(self.result)
        assert "EUR" in text


# =============================================================================
# Ejercicio 11 — Condicionales
# =============================================================================
class TestEj11Condicionales:
    """Tests para ej11_condicionales.xsl contra empleados.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("empleados.xml", "ej11_condicionales.xsl")
        self.html = result_to_tree(self.result)
        self.text = result_to_string(self.result)

    def test_tiene_tabla(self):
        """Debe contener una tabla"""
        tables = self.html.xpath("//table")
        assert len(tables) >= 1

    def test_tiene_5_filas_datos(self):
        """Debe tener 5 filas de datos"""
        trs = self.html.xpath("//table//tr")
        assert len(trs) >= 6  # cabecera + 5 datos

    def test_sin_asignar_para_pedro(self):
        """Pedro Vega debe tener 'Sin asignar' en departamento"""
        assert "Sin asignar" in self.text

    def test_niveles_presentes(self):
        """Deben aparecer los niveles Senior, Medio y Junior"""
        assert "Senior" in self.text
        assert "Medio" in self.text
        assert "Junior" in self.text

    def test_inactivo_en_cursiva(self):
        """María García (inactiva) debe estar en <em>"""
        ems = self.html.xpath("//em")
        em_texts = [em.text or "" for em in ems]
        all_em = " ".join(em_texts)
        assert "María" in all_em or "Mar" in all_em


# =============================================================================
# Ejercicio 12 — Variables y Parámetros
# =============================================================================
class TestEj12VariablesParametros:
    """Tests para ej12_variables_parametros.xsl contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("catalogo.xml", "ej12_variables_parametros.xsl")
        self.html = result_to_tree(self.result)
        self.text = result_to_string(self.result)

    def test_h1_con_total(self):
        """El <h1> debe mencionar el número total de libros"""
        h1s = self.html.xpath("//h1")
        assert len(h1s) >= 1
        h1_text = h1s[0].text or etree.tostring(h1s[0], encoding="unicode", method="text")
        assert "5" in h1_text

    def test_tiene_items_lista(self):
        """Debe tener al menos algunos <li> (libros que pasan el filtro)"""
        lis = self.html.xpath("//li")
        assert len(lis) >= 1

    def test_precios_con_iva(self):
        """Los precios mostrados deben incluir IVA"""
        assert "EUR" in self.text or "con IVA" in self.text

    def test_filtra_por_limite(self):
        """Con precioLimite=25, no todos los libros aparecen"""
        lis = self.html.xpath("//li")
        # Precio con IVA: 17.50*1.21=21.175, 22*1.21=26.62, 19.95*1.21=24.14,
        # 15*1.21=18.15, 12.50*1.21=15.125
        # Menores que 25: 21.175, 24.14, 18.15, 15.125 = 4 libros
        assert len(lis) == 4


# =============================================================================
# Ejercicio 13 — Modos y Plantillas Nombradas
# =============================================================================
class TestEj13ModosNamed:
    """Tests para ej13_modos_named.xsl contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("catalogo.xml", "ej13_modos_named.xsl")
        self.html = result_to_tree(self.result)
        self.text = result_to_string(self.result)

    def test_tiene_cabecera(self):
        """Debe tener un <header> con el título"""
        headers = self.html.xpath("//header")
        assert len(headers) >= 1

    def test_cabecera_con_total(self):
        """La cabecera debe indicar el total de libros"""
        header_text = etree.tostring(self.html.xpath("//header")[0],
                                     encoding="unicode", method="text")
        assert "5" in header_text

    def test_tiene_lista_resumen(self):
        """Debe tener una <ul> con <li> (modo resumen)"""
        lis = self.html.xpath("//ul/li")
        assert len(lis) == 5

    def test_tiene_fichas_detalle(self):
        """Debe tener <article> (modo detalle)"""
        articles = self.html.xpath("//article")
        assert len(articles) == 5

    def test_fichas_tienen_h2(self):
        """Cada ficha detalle debe tener <h2> con el título"""
        h2s = self.html.xpath("//article/h2")
        assert len(h2s) == 5

    def test_fichas_tienen_autor_y_precio(self):
        """Las fichas deben mencionar autor y precio"""
        assert "Autor" in self.text or "autor" in self.text
        assert "EUR" in self.text


# =============================================================================
# Ejercicio 14 — Salida CSV
# =============================================================================
class TestEj14SalidaCsv:
    """Tests para ej14_salida_csv.xsl contra empleados.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("empleados.xml", "ej14_salida_csv.xsl")
        self.text = result_to_string(self.result).strip()
        self.lines = [l for l in self.text.split("\n") if l.strip()]

    def test_primera_linea_cabecera(self):
        """Primera línea debe ser: nombre,departamento,salario"""
        assert self.lines[0].strip() == "nombre,departamento,salario"

    def test_tiene_6_lineas(self):
        """Debe tener 6 líneas: 1 cabecera + 5 empleados"""
        assert len(self.lines) == 6

    def test_formato_csv_correcto(self):
        """Cada línea de datos debe tener 3 campos separados por comas"""
        for line in self.lines[1:]:
            fields = line.strip().split(",")
            assert len(fields) == 3, f"La línea '{line}' no tiene 3 campos"

    def test_nombre_limpio(self):
        """Ana López debe aparecer sin espacios extra (normalize-space)"""
        ana_line = [l for l in self.lines if "Ana" in l]
        assert len(ana_line) == 1
        assert "  Ana" not in ana_line[0]  # Sin espacios extra
        assert "Ana López" in ana_line[0]

    def test_contiene_todos_empleados(self):
        """Deben aparecer los 5 empleados"""
        all_text = "\n".join(self.lines)
        assert "Carlos Ruiz" in all_text
        assert "Pedro Vega" in all_text


# =============================================================================
# Ejercicio 15 — Elementos y Atributos Dinámicos
# =============================================================================
class TestEj15ElementosDinamicos:
    """Tests para ej15_elementos_dinamicos.xsl contra inventario.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("inventario.xml", "ej15_elementos_dinamicos.xsl")
        self.text = result_to_string(self.result)
        self.tree = etree.fromstring(self.text.encode("utf-8"))

    def test_elemento_raiz(self):
        """El elemento raíz debe ser <inventario-resumen>"""
        assert self.tree.tag == "inventario-resumen"

    def test_tiene_6_items(self):
        """Debe tener 6 elementos <item>"""
        items = self.tree.findall("item")
        assert len(items) == 6

    def test_items_tienen_ref(self):
        """Cada <item> debe tener atributo 'ref' con el id del producto"""
        items = self.tree.findall("item")
        refs = [item.get("ref") for item in items]
        assert "P01" in refs
        assert "P03" in refs

    def test_items_tienen_categoria(self):
        """Cada <item> debe tener atributo 'categoria'"""
        items = self.tree.findall("item")
        for item in items:
            assert item.get("categoria") is not None

    def test_estado_agotado(self):
        """Productos con stock=0 deben tener estado 'agotado'"""
        for item in self.tree.findall("item"):
            if item.get("ref") in ("P03", "P05"):
                estado = item.find("estado").text
                assert estado == "agotado", f"Item {item.get('ref')} debería ser 'agotado'"

    def test_estado_bajo(self):
        """Productos con stock <= 5 (no 0) deben tener estado 'bajo'"""
        for item in self.tree.findall("item"):
            # P02 stock=3 → bajo
            if item.get("ref") == "P02":
                estado = item.find("estado").text
                assert estado == "bajo"

    def test_estado_disponible(self):
        """Productos con stock > 5 deben tener estado 'disponible'"""
        for item in self.tree.findall("item"):
            if item.get("ref") in ("P01", "P04"):
                estado = item.find("estado").text
                assert estado == "disponible"


# =============================================================================
# Ejercicio 16 — Integrador Inventario
# =============================================================================
class TestEj16IntegradorInventario:
    """Tests para ej16_integrador_inventario.xsl contra inventario.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("inventario.xml",
                                 "ej16_integrador_inventario.xsl")
        self.html = result_to_tree(self.result)
        self.text = result_to_string(self.result)

    def test_tiene_cabecera_header(self):
        """Debe tener un <header>"""
        headers = self.html.xpath("//header")
        assert len(headers) >= 1

    def test_cabecera_total_productos(self):
        """La cabecera debe mencionar 6 productos"""
        header_text = etree.tostring(self.html.xpath("//header")[0],
                                     encoding="unicode", method="text")
        assert "6" in header_text

    def test_tiene_6_articles(self):
        """Debe tener 6 <article>"""
        articles = self.html.xpath("//article")
        assert len(articles) == 6

    def test_clases_css_correctas(self):
        """Los articles deben tener clases agotado/bajo/ok"""
        articles = self.html.xpath("//article")
        clases = [a.get("class", "") for a in articles]
        assert "agotado" in clases  # P03 y P05
        assert "ok" in clases

    def test_orden_precio_descendente(self):
        """El primer article debe ser el más caro (Monitor 349.00)"""
        first_h3 = self.html.xpath("//article[1]/h3")
        if first_h3:
            assert "Monitor" in (first_h3[0].text or "")

    def test_tiene_footer_con_total(self):
        """Debe tener un <footer> con el valor total"""
        footers = self.html.xpath("//footer")
        assert len(footers) >= 1
        footer_text = etree.tostring(footers[0], encoding="unicode",
                                     method="text")
        assert "763" in footer_text  # sum de todos los precios


# =============================================================================
# Ejercicio 17 — apply-templates
# =============================================================================
class TestEj17ApplyTemplates:
    """Tests para ej17_apply_templates.xsl contra biblioteca.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.result = apply_xslt("biblioteca.xml",
                                 "ej17_apply_templates.xsl")
        self.html = result_to_tree(self.result)
        self.text = result_to_string(self.result)

    def test_tiene_h1(self):
        """Debe tener un <h1> con 'Biblioteca'"""
        h1s = self.html.xpath("//h1")
        assert len(h1s) >= 1
        assert "Biblioteca" in (h1s[0].text or "")

    def test_tiene_5_divs_libro(self):
        """Debe tener 5 <div class='libro'>"""
        divs = self.html.xpath("//div[@class='libro']")
        assert len(divs) == 5

    def test_cada_div_tiene_h2(self):
        """Cada div.libro debe tener un <h2> con el título"""
        h2s = self.html.xpath("//div[@class='libro']/h2")
        assert len(h2s) == 5

    def test_tiene_p_autor(self):
        """Debe haber <p class='autor'> con 'Autor:'"""
        autores = self.html.xpath("//p[@class='autor']")
        assert len(autores) == 5

    def test_tiene_p_precio(self):
        """Debe haber <p class='precio'> con 'Precio:' y 'EUR'"""
        precios = self.html.xpath("//p[@class='precio']")
        assert len(precios) == 5

    def test_tiene_span_anio(self):
        """Debe haber <span class='anio'>"""
        anios = self.html.xpath("//span[@class='anio']")
        assert len(anios) == 5

    def test_orden_alfabetico(self):
        """Los libros deben estar ordenados por título ascendente"""
        h2s = self.html.xpath("//div[@class='libro']/h2")
        titulos = [h2.text or "" for h2 in h2s]
        assert titulos == sorted(titulos)

    def test_titulo_no_duplicado(self):
        """El título NO debe aparecer fuera del <h2> dentro de cada div"""
        # La plantilla match="titulo" debe ser vacía
        for div in self.html.xpath("//div[@class='libro']"):
            texts = div.xpath("text()")
            # No debería haber texto suelto con el título
            h2_text = div.xpath("h2")[0].text or ""
            for t in texts:
                assert h2_text not in t.strip() or t.strip() == ""
