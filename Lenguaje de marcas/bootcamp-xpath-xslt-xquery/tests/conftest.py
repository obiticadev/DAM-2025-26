"""
Fixtures compartidos para todos los tests del bootcamp.
"""
import os
import pytest
from lxml import etree

# Ruta base del proyecto
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
XML_DATA_DIR = os.path.join(BASE_DIR, "src", "xml_data")
EJERCICIOS_DIR = os.path.join(BASE_DIR, "src", "ejercicios")


def parse_xml(filename: str) -> etree._ElementTree:
    """Carga y parsea un fichero XML desde xml_data/."""
    path = os.path.join(XML_DATA_DIR, filename)
    return etree.parse(path)


def read_xpath_file(filename: str) -> list[str]:
    """
    Lee un fichero .xpath y devuelve las líneas no vacías que no empiezan por #.
    Cada línea es una expresión XPath correspondiente a un apartado.
    """
    path = os.path.join(EJERCICIOS_DIR, "xpath", filename)
    with open(path, "r", encoding="utf-8") as f:
        lines = f.readlines()

    expressions = []
    for line in lines:
        stripped = line.strip()
        if stripped and not stripped.startswith("#"):
            expressions.append(stripped)
    return expressions


def read_xslt_file(filename: str) -> str:
    """Lee un fichero .xsl desde ejercicios/xslt/."""
    path = os.path.join(EJERCICIOS_DIR, "xslt", filename)
    with open(path, "r", encoding="utf-8") as f:
        return f.read()


def read_xquery_file(filename: str) -> str:
    """Lee un fichero .xq desde ejercicios/xquery/."""
    path = os.path.join(EJERCICIOS_DIR, "xquery", filename)
    with open(path, "r", encoding="utf-8") as f:
        return f.read()


@pytest.fixture
def catalogo():
    """Fixture: catalogo.xml parseado."""
    return parse_xml("catalogo.xml")


@pytest.fixture
def empleados():
    """Fixture: empleados.xml parseado."""
    return parse_xml("empleados.xml")


@pytest.fixture
def tienda():
    """Fixture: tienda.xml parseado."""
    return parse_xml("tienda.xml")


@pytest.fixture
def biblioteca():
    """Fixture: biblioteca.xml parseado."""
    return parse_xml("biblioteca.xml")


@pytest.fixture
def inventario():
    """Fixture: inventario.xml parseado."""
    return parse_xml("inventario.xml")
