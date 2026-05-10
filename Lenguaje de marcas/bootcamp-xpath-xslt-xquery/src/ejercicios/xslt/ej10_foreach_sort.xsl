<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 10 — for-each y sort (XSLT)
  XML fuente: src/xml_data/catalogo.xml
  Concepto: xsl:for-each, xsl:sort con data-type y order

  OBJETIVO:
  Transforma catalogo.xml en una tabla HTML con columnas:
  Título | Autor | Precio | Año

  REQUISITOS:
  1. Los libros deben estar ordenados por PRECIO DESCENDENTE (de mayor a menor).
  2. Usa data-type="number" en el sort.
  3. El precio debe mostrarse con el texto " EUR" después del valor.

  RESULTADO ESPERADO (estructura):
  <html>
    <body>
      <h1>Catálogo — Ordenado por precio</h1>
      <table>
        <tr><th>Título</th><th>Autor</th><th>Precio</th><th>Año</th></tr>
        <tr><td>Cien años de soledad</td><td>...</td><td>22.00 EUR</td><td>1967</td></tr>
        ...
      </table>
    </body>
  </html>
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <body>
        <h1>Catálogo — Ordenado por precio</h1>
        <table>
          <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>Precio</th>
            <th>Año</th>
          </tr>
          <xsl:for-each select="catalogo/libro">
            <xsl:sort select="precio" data-type="number" order="descending"/>
            <tr>
            <td>
              <xsl:value-of select="titulo"></xsl:value-of>
            </td>
            <td>
              <xsl:value-of select="autor"></xsl:value-of>
            </td>
            <td>
              <xsl:value-of select="concat(precio, ' EUR')"></xsl:value-of>
            </td>
            <td>
              <xsl:value-of select="anio"></xsl:value-of>
            </td>
            </tr>
          </xsl:for-each>
          <!-- TODO: Usar xsl:for-each sobre catalogo/libro
               Dentro del for-each, añadir xsl:sort por precio (number, descending)
               Para cada libro, generar un <tr> con 4 <td>:
               - título
               - autor
               - precio seguido del texto " EUR"
               - año -->
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
