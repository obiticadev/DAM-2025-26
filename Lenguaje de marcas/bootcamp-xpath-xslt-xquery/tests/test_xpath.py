"""
Tests para los ejercicios XPath (01-08).
Cada test lee el fichero .xpath del usuario, extrae sus expresiones
y las evalúa contra el XML correspondiente.
"""
import pytest
from lxml import etree
from .conftest import parse_xml, read_xpath_file


# =============================================================================
# Ejercicio 01 — Rutas Básicas
# =============================================================================
class TestEj01RutasBasicas:
    """Tests para ej01_rutas_basicas.xpath contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("catalogo.xml")
        self.exprs = read_xpath_file("ej01_rutas_basicas.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6, (
            f"Se esperan 6 expresiones (a-f), pero se encontraron {len(self.exprs)}. "
            "Asegúrate de escribir una expresión por línea."
        )

    def test_a_elemento_raiz_catalogo(self):
        """a) Selecciona el elemento raíz <catalogo>"""
        result = self.doc.xpath(self.exprs[0])
        assert len(result) == 1
        assert result[0].tag == "catalogo"

    def test_b_todos_los_libros(self):
        """b) Selecciona TODOS los elementos <libro>"""
        result = self.doc.xpath(self.exprs[1])
        assert len(result) == 5
        assert all(el.tag == "libro" for el in result)

    def test_c_todos_los_titulos(self):
        """c) Selecciona TODOS los <titulo> del documento"""
        result = self.doc.xpath(self.exprs[2])
        assert len(result) == 5
        assert all(el.tag == "titulo" for el in result)

    def test_d_titulo_primer_libro(self):
        """d) Selecciona el <titulo> del primer libro"""
        result = self.doc.xpath(self.exprs[3])
        assert len(result) == 1
        assert result[0].tag == "titulo"
        assert result[0].text == "El Quijote"

    def test_e_texto_todos_titulos(self):
        """e) Selecciona el TEXTO de todos los títulos"""
        result = self.doc.xpath(self.exprs[4])
        assert isinstance(result, list)
        texts = [str(r) for r in result]
        assert "El Quijote" in texts
        assert "Cien años de soledad" in texts
        assert "La sombra del viento" in texts
        assert len(texts) == 5

    def test_f_hijos_primer_libro(self):
        """f) Selecciona todos los hijos del primer libro"""
        result = self.doc.xpath(self.exprs[5])
        assert len(result) == 4  # titulo, autor, precio, anio
        tags = [el.tag for el in result]
        assert "titulo" in tags
        assert "autor" in tags
        assert "precio" in tags
        assert "anio" in tags


# =============================================================================
# Ejercicio 02 — Atributos
# =============================================================================
class TestEj02Atributos:
    """Tests para ej02_atributos.xpath contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("catalogo.xml")
        self.exprs = read_xpath_file("ej02_atributos.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_id_todos_libros(self):
        """a) Atributo id de todos los libros"""
        result = self.doc.xpath(self.exprs[0])
        ids = [str(r) for r in result]
        assert "001" in ids
        assert "002" in ids
        assert "003" in ids
        assert "004" in ids
        assert "005" in ids

    def test_b_id_segundo_libro(self):
        """b) Atributo id del segundo libro"""
        result = self.doc.xpath(self.exprs[1])
        assert len(result) == 1
        assert str(result[0]) == "002"

    def test_c_todos_atributos_primer_libro(self):
        """c) Todos los atributos del primer libro"""
        result = self.doc.xpath(self.exprs[2])
        assert len(result) == 2  # id y disponible
        vals = [str(r) for r in result]
        assert "001" in vals
        assert "true" in vals

    def test_d_libros_disponibles(self):
        """d) Libros con disponible=true"""
        result = self.doc.xpath(self.exprs[3])
        assert len(result) == 3
        assert all(el.tag == "libro" for el in result)
        assert all(el.get("disponible") == "true" for el in result)

    def test_e_moneda_precios(self):
        """e) Atributo moneda de todos los precios"""
        result = self.doc.xpath(self.exprs[4])
        monedas = [str(r) for r in result]
        assert len(monedas) == 5
        assert "EUR" in monedas

    def test_f_titulo_libro_003(self):
        """f) Título del libro con id=003"""
        result = self.doc.xpath(self.exprs[5])
        assert len(result) == 1
        if hasattr(result[0], "text"):
            assert result[0].text == "La sombra del viento"
        else:
            assert str(result[0]) == "La sombra del viento"


# =============================================================================
# Ejercicio 03 — Ejes
# =============================================================================
class TestEj03Ejes:
    """Tests para ej03_ejes.xpath contra tienda.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("tienda.xml")
        self.exprs = read_xpath_file("ej03_ejes.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_descendientes_informatica(self):
        """a) Nombres descendientes de sección Informatica"""
        result = self.doc.xpath(self.exprs[0])
        nombres = [el.text for el in result]
        assert "Ratón" in nombres
        assert "Teclado" in nombres
        assert "Monitor" in nombres
        assert len(nombres) == 3

    def test_b_hermanas_posteriores_informatica(self):
        """b) Secciones hermanas posteriores de Informatica"""
        result = self.doc.xpath(self.exprs[1])
        assert len(result) == 2  # Papeleria y Mobiliario
        nombres = [el.get("nombre") for el in result]
        assert "Papeleria" in nombres
        assert "Mobiliario" in nombres

    def test_c_ultimo_producto_primera_seccion(self):
        """c) Nombre del último producto de la primera sección"""
        result = self.doc.xpath(self.exprs[2])
        assert len(result) == 1
        if hasattr(result[0], "text"):
            assert result[0].text == "Monitor"
        else:
            assert str(result[0]) == "Monitor"

    def test_d_padre_producto_T04(self):
        """d) Padre del producto T04 (debe ser sección Papeleria)"""
        result = self.doc.xpath(self.exprs[3])
        assert len(result) == 1
        assert result[0].tag == "seccion"
        assert result[0].get("nombre") == "Papeleria"

    def test_e_todos_nombres_producto(self):
        """e) Todos los nombres de producto del documento"""
        result = self.doc.xpath(self.exprs[4])
        assert len(result) == 6

    def test_f_hermanas_anteriores_mobiliario(self):
        """f) Secciones hermanas anteriores de Mobiliario"""
        result = self.doc.xpath(self.exprs[5])
        assert len(result) == 2  # Informatica y Papeleria
        nombres = [el.get("nombre") for el in result]
        assert "Informatica" in nombres
        assert "Papeleria" in nombres


# =============================================================================
# Ejercicio 04 — Predicados de Posición
# =============================================================================
class TestEj04PredicadosPosicion:
    """Tests para ej04_predicados_posicion.xpath contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("catalogo.xml")
        self.exprs = read_xpath_file("ej04_predicados_posicion.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_primer_libro(self):
        """a) Primer libro"""
        result = self.doc.xpath(self.exprs[0])
        assert len(result) == 1
        assert result[0].get("id") == "001"

    def test_b_ultimo_libro(self):
        """b) Último libro"""
        result = self.doc.xpath(self.exprs[1])
        assert len(result) == 1
        assert result[0].get("id") == "005"

    def test_c_segundo_libro(self):
        """c) Segundo libro usando position()"""
        result = self.doc.xpath(self.exprs[2])
        assert len(result) == 1
        assert result[0].get("id") == "002"

    def test_d_libros_posicion_par(self):
        """d) Libros en posición par"""
        result = self.doc.xpath(self.exprs[3])
        assert len(result) == 2
        ids = [el.get("id") for el in result]
        assert "002" in ids
        assert "004" in ids

    def test_e_tres_primeros_libros(self):
        """e) Los 3 primeros libros"""
        result = self.doc.xpath(self.exprs[4])
        assert len(result) == 3
        ids = [el.get("id") for el in result]
        assert ids == ["001", "002", "003"]

    def test_f_titulo_penultimo(self):
        """f) Título del penúltimo libro"""
        result = self.doc.xpath(self.exprs[5])
        assert len(result) == 1
        if hasattr(result[0], "text"):
            assert result[0].text == "La casa de los espíritus"
        else:
            assert str(result[0]) == "La casa de los espíritus"


# =============================================================================
# Ejercicio 05 — Predicados Compuestos
# =============================================================================
class TestEj05PredicadosCompuestos:
    """Tests para ej05_predicados_compuestos.xpath contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("catalogo.xml")
        self.exprs = read_xpath_file("ej05_predicados_compuestos.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_disponibles_precio_mayor_15(self):
        """a) Libros disponibles con precio > 15"""
        result = self.doc.xpath(self.exprs[0])
        assert len(result) == 3  # 001 (17.50), 003 (19.95), 004 (15.00)
        for el in result:
            assert el.get("disponible") == "true"
            assert float(el.find("precio").text) > 15

    def test_b_precio_mayor_20_o_id_001(self):
        """b) Libros con precio > 20 o id=001"""
        result = self.doc.xpath(self.exprs[1])
        ids = [el.get("id") for el in result]
        assert "001" in ids  # id=001
        assert "002" in ids  # precio 22.00

    def test_c_no_disponibles(self):
        """c) Libros no disponibles"""
        result = self.doc.xpath(self.exprs[2])
        assert len(result) == 2  # 002 y 005
        for el in result:
            assert el.get("disponible") == "false"

    def test_d_titulos_entre_1900_y_2000(self):
        """d) Títulos de libros entre 1900 y 2000"""
        result = self.doc.xpath(self.exprs[3])
        titulos = [el.text if hasattr(el, "text") else str(el) for el in result]
        assert "Cien años de soledad" in titulos  # 1967
        assert "La casa de los espíritus" in titulos  # 1982
        assert "Rayuela" in titulos  # 1963

    def test_e_disponibles_moneda_eur(self):
        """e) Libros disponibles con moneda EUR"""
        result = self.doc.xpath(self.exprs[4])
        for el in result:
            assert el.get("disponible") == "true"
            assert el.find("precio").get("moneda") == "EUR"
        # 001 y 003 son disponibles y EUR
        assert len(result) == 2

    def test_f_autores_precio_15_a_20(self):
        """f) Autores de libros con precio entre 15 y 20"""
        result = self.doc.xpath(self.exprs[5])
        autores = [el.text if hasattr(el, "text") else str(el) for el in result]
        assert "Miguel de Cervantes" in autores  # 17.50
        assert "Carlos Ruiz Zafón" in autores  # 19.95
        assert "Isabel Allende" in autores  # 15.00


# =============================================================================
# Ejercicio 06 — Funciones Numéricas
# =============================================================================
class TestEj06FuncionesNumericas:
    """Tests para ej06_funciones_numericas.xpath contra catalogo.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("catalogo.xml")
        self.exprs = read_xpath_file("ej06_funciones_numericas.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_count_libros(self):
        """a) count de todos los libros"""
        result = self.doc.xpath(self.exprs[0])
        assert result == 5.0 or result == 5

    def test_b_sum_precios(self):
        """b) sum de todos los precios"""
        result = self.doc.xpath(self.exprs[1])
        assert abs(float(result) - 86.95) < 0.01

    def test_c_count_disponibles(self):
        """c) count de libros disponibles"""
        result = self.doc.xpath(self.exprs[2])
        assert result == 3.0 or result == 3

    def test_d_round_primer_precio(self):
        """d) round del precio del primer libro (17.50 → 18)"""
        result = self.doc.xpath(self.exprs[3])
        assert result == 18.0 or result == 18

    def test_e_floor_tercer_precio(self):
        """e) floor del precio del tercer libro (19.95 → 19)"""
        result = self.doc.xpath(self.exprs[4])
        assert result == 19.0 or result == 19

    def test_f_ceiling_segundo_precio(self):
        """f) ceiling del precio del segundo libro (22.00 → 22)"""
        result = self.doc.xpath(self.exprs[5])
        assert result == 22.0 or result == 22


# =============================================================================
# Ejercicio 07 — Funciones de Cadena
# =============================================================================
class TestEj07FuncionesCadena:
    """Tests para ej07_funciones_cadena.xpath contra empleados.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("empleados.xml")
        self.exprs = read_xpath_file("ej07_funciones_cadena.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_email_empresa(self):
        """a) Empleados cuyo email contiene empresa.com"""
        result = self.doc.xpath(self.exprs[0])
        assert len(result) == 4  # Ana, Carlos, María, Laura (no Pedro)
        for el in result:
            assert "empresa.com" in el.find("email").text

    def test_b_puesto_empieza_desar(self):
        """b) Empleados cuyo puesto empieza por Desar"""
        result = self.doc.xpath(self.exprs[1])
        assert len(result) == 2  # Ana (Senior) y María (Junior)

    def test_c_longitud_nombre_primero(self):
        """c) string-length del nombre del primer empleado (con espacios)"""
        result = self.doc.xpath(self.exprs[2])
        # "  Ana López  " tiene 14 caracteres (2 + 3 + 1 + 5 + 2 + 1 espacio = 14)
        # Contemos: "  Ana López  " = 2 espacios + "Ana" + 1 espacio + "López" + 2 espacios = 14
        nombre = self.doc.xpath("//empleado[1]/nombre/text()")[0]
        expected_len = len(nombre)
        assert float(result) == expected_len

    def test_d_normalize_nombre_primero(self):
        """d) normalize-space del nombre del primer empleado"""
        result = self.doc.xpath(self.exprs[3])
        assert str(result) == "Ana López"

    def test_e_concat_segundo(self):
        """e) Concatenación nombre + ' - ' + puesto del segundo empleado"""
        result = self.doc.xpath(self.exprs[4])
        assert str(result) == "Carlos Ruiz - Analista"

    def test_f_nombre_mas_10_chars(self):
        """f) Empleados con nombre > 10 caracteres"""
        result = self.doc.xpath(self.exprs[5])
        for el in result:
            nombre = el.find("nombre").text
            assert len(nombre) > 10


# =============================================================================
# Ejercicio 08 — Funciones Avanzadas
# =============================================================================
class TestEj08FuncionesAvanzadas:
    """Tests para ej08_funciones_avanzadas.xpath contra empleados.xml"""

    @pytest.fixture(autouse=True)
    def setup(self):
        self.doc = parse_xml("empleados.xml")
        self.exprs = read_xpath_file("ej08_funciones_avanzadas.xpath")

    def test_tiene_6_expresiones(self):
        assert len(self.exprs) >= 6

    def test_a_translate_mayusculas(self):
        """a) Nombre del segundo empleado en mayúsculas"""
        result = self.doc.xpath(self.exprs[0])
        assert str(result).upper() == "CARLOS RUIZ"

    def test_b_substring_puesto(self):
        """b) 3 primeros caracteres del puesto del primer empleado"""
        result = self.doc.xpath(self.exprs[1])
        assert str(result) == "Des"

    def test_c_activos_salario_mayor_3000(self):
        """c) Empleados activos con salario > 3000"""
        result = self.doc.xpath(self.exprs[2])
        for el in result:
            assert el.get("activo") == "true"
            assert float(el.find("salario").text) > 3000
        ids = [el.get("id") for el in result]
        assert "E01" in ids  # Ana, 3200
        assert "E04" in ids  # Pedro, 4100

    def test_d_departamento_no_vacio(self):
        """d) Empleados con departamento no vacío"""
        result = self.doc.xpath(self.exprs[3])
        assert len(result) == 4  # Todos menos Pedro (departamento vacío)
        for el in result:
            assert len(el.find("departamento").text or "") > 0

    def test_e_count_desarrollo(self):
        """e) Número de empleados del departamento Desarrollo"""
        result = self.doc.xpath(self.exprs[4])
        assert result == 2.0 or result == 2

    def test_f_email_no_empieza_p(self):
        """f) Emails de empleados cuyo nombre no empieza por P"""
        result = self.doc.xpath(self.exprs[5])
        emails = [str(r) if not hasattr(r, "text") else r.text for r in result]
        assert "pedro@externo.org" not in emails
        assert len(emails) == 4  # Ana, Carlos, María, Laura
