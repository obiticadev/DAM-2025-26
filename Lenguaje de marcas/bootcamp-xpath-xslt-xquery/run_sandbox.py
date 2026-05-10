#!/usr/bin/env python3
"""
🧪 Sandbox Visual — Bootcamp XPath / XSLT / XQuery

Ejecuta transformaciones XSLT, consultas XQuery o expresiones XPath
contra un fichero XML y muestra el resultado por consola.

Uso:
    python run_sandbox.py <xml> <archivo.xsl|.xq>        # XSLT o XQuery
    python run_sandbox.py <xml> --xpath "//expresion"     # XPath interactivo

Ejemplos:
    python run_sandbox.py src/xml_data/catalogo.xml src/ejercicios/xslt/ej09_plantilla_basica.xsl
    python run_sandbox.py src/xml_data/biblioteca.xml src/ejercicios/xquery/ej19_flwor_basico.xq
    python run_sandbox.py src/xml_data/catalogo.xml --xpath "//libro/titulo/text()"
"""

import sys
import os

if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8", errors="replace")


def run_xslt(xml_path: str, xsl_path: str) -> None:
    """Aplica una hoja XSLT a un XML y muestra el resultado."""
    from lxml import etree

    xml_doc = etree.parse(xml_path)
    xsl_doc = etree.parse(xsl_path)
    transform = etree.XSLT(xsl_doc)
    result = transform(xml_doc)

    print("=" * 60)
    print(f"📄 XML:  {xml_path}")
    print(f"🎨 XSLT: {xsl_path}")
    print("=" * 60)
    output = str(result)
    if output.strip():
        print(output)
    else:
        print("⚠️  La transformación produjo una salida vacía.")
        print("    Revisa que hayas completado los TODOs del ejercicio.")
    print("=" * 60)


def run_xquery(xml_path: str, xq_path: str) -> None:
    """Ejecuta una consulta XQuery contra un XML y muestra el resultado."""
    try:
        from saxonche import PySaxonProcessor
    except ImportError:
        print("❌ Error: saxonche no está instalado.")
        print("   Ejecuta: pip install saxonche")
        sys.exit(1)

    with open(xq_path, "r", encoding="utf-8") as f:
        xquery_code = f.read()

    with PySaxonProcessor(license=False) as proc:
        xq_processor = proc.new_xquery_processor()

        # Establecer el documento XML como contexto
        xml_abs = os.path.abspath(xml_path)
        node = proc.parse_xml(xml_file_name=xml_abs)
        xq_processor.set_context(xdm_item=node)

        # Ejecutar la consulta
        try:
            result = xq_processor.run_query_to_string(query_text=xquery_code)
        except Exception as e:
            print("=" * 60)
            print(f"📄 XML:    {xml_path}")
            print(f"🔍 XQuery: {xq_path}")
            print("=" * 60)
            print(f"❌ Error al ejecutar la consulta XQuery:\n{e}")
            print("=" * 60)
            return

    print("=" * 60)
    print(f"📄 XML:    {xml_path}")
    print(f"🔍 XQuery: {xq_path}")
    print("=" * 60)
    if result and result.strip():
        print(result)
    else:
        print("⚠️  La consulta XQuery produjo una salida vacía.")
        print("    Revisa que hayas completado los TODOs del ejercicio.")
    print("=" * 60)


def run_xpath(xml_path: str, expression: str) -> None:
    """Evalúa una expresión XPath contra un XML y muestra los resultados."""
    from lxml import etree

    xml_doc = etree.parse(xml_path)

    print("=" * 60)
    print(f"📄 XML:   {xml_path}")
    print(f"🎯 XPath: {expression}")
    print("=" * 60)

    try:
        results = xml_doc.xpath(expression)
    except etree.XPathSyntaxError as e:
        print(f"❌ Error de sintaxis XPath: {e}")
        print("=" * 60)
        return

    if isinstance(results, list):
        if not results:
            print("⚠️  La expresión no devolvió ningún resultado.")
        else:
            print(f"📊 {len(results)} resultado(s):\n")
            for i, r in enumerate(results, 1):
                if isinstance(r, etree._Element):
                    print(f"  [{i}] {etree.tostring(r, encoding='unicode', pretty_print=True).strip()}")
                else:
                    print(f"  [{i}] {r}")
    else:
        # Resultado atómico (número, booleano, string)
        print(f"📊 Resultado: {results}")

    print("=" * 60)


def run_xquery_expr(xml_path: str, expression: str) -> None:
    """Evalúa una expresión XQuery contra un XML y muestra el resultado."""
    try:
        from saxonche import PySaxonProcessor
    except ImportError:
        print("❌ Error: saxonche no está instalado.")
        print("   Ejecuta: pip install saxonche")
        sys.exit(1)

    print("=" * 60)
    print(f"📄 XML:    {xml_path}")
    print(f"🔍 XQuery: {expression}")
    print("=" * 60)

    with PySaxonProcessor(license=False) as proc:
        xq_proc = proc.new_xquery_processor()
        xml_abs = os.path.abspath(xml_path)
        node = proc.parse_xml(xml_file_name=xml_abs)
        xq_proc.set_context(xdm_item=node)
        try:
            result = xq_proc.run_query_to_string(query_text=expression)
        except Exception as e:
            print(f"❌ Error XQuery:\n{e}")
            print("=" * 60)
            return

    if result and result.strip():
        print(result)
    else:
        print("⚠️  La expresión no devolvió ningún resultado.")
    print("=" * 60)


def main():
    if len(sys.argv) < 3:
        print(__doc__)
        sys.exit(1)

    xml_path = sys.argv[1]

    if not os.path.exists(xml_path):
        print(f"❌ No se encontró el fichero XML: {xml_path}")
        sys.exit(1)

    # Modo XPath interactivo
    if sys.argv[2] == "--xpath":
        if len(sys.argv) < 4:
            print("❌ Falta la expresión XPath.")
            print('   Uso: python run_sandbox.py <xml> --xpath "//expresion"')
            sys.exit(1)
        run_xpath(xml_path, sys.argv[3])
        return

    # Modo XQuery interactivo
    if sys.argv[2] == "--xquery":
        if len(sys.argv) < 4:
            print("❌ Falta la expresión XQuery.")
            print('   Uso: python run_sandbox.py <xml> --xquery "//expresion"')
            sys.exit(1)
        run_xquery_expr(xml_path, sys.argv[3])
        return

    # Modo archivo (XSLT o XQuery)
    file_path = sys.argv[2]

    if not os.path.exists(file_path):
        print(f"❌ No se encontró el fichero: {file_path}")
        sys.exit(1)

    ext = os.path.splitext(file_path)[1].lower()

    if ext == ".xsl" or ext == ".xslt":
        run_xslt(xml_path, file_path)
    elif ext == ".xq" or ext == ".xquery":
        run_xquery(xml_path, file_path)
    else:
        print(f"❌ Extensión no reconocida: {ext}")
        print("   Extensiones soportadas: .xsl, .xslt, .xq, .xquery")
        sys.exit(1)


if __name__ == "__main__":
    main()
