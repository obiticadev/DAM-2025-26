<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 09 — Plantilla Básica (XSLT)
  XML fuente: src/xml_data/catalogo.xml
  Concepto: Estructura XSLT, xsl:template match="/", xsl:value-of

  OBJETIVO:
  Transforma catalogo.xml en una página HTML que muestre una lista <ul>
  con el título de cada libro dentro de un <li>.

  RESULTADO ESPERADO (estructura):
  <html>
    <head><title>Catálogo de Libros</title></head>
    <body>
      <h1>Catálogo de Libros</h1>
      <ul>
        <li>El Quijote</li>
        <li>Cien años de soledad</li>
        ...
      </ul>
    </body>
  </html>
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head><title>Catálogo de Libros</title></head>
      <body>
        <h1>Catálogo de Libros</h1>
        <ul>
          <xsl:for-each select="catalogo/libro">
            <li>
              <xsl:value-of select="titulo"/>
            </li>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
