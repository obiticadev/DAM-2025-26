<?xml version="1.0" encoding="UTF-8"?>
<!--
  RETO FINAL — Parte 3: Dashboard XSLT
  XML fuente: src/xml_data/inventario-completo.xml (resultado de la parte 1)
  
  OBJETIVO:
  Generar un Dashboard HTML usando los conceptos avanzados de XSLT.
  
  REQUISITOS:
  1. DECLARACIONES GLOBALES:
     - PARÁMETRO "moneda" con valor por defecto "EUR".
     - VARIABLE "totalActivos" con count(//item).
  
  2. PLANTILLA NOMBRADA "footer-stats":
     Debe generar:
     <footer>
       <p>Total de productos activos: [mostrar variable totalActivos]</p>
       <p>Valor total de inventario: [sum de todos los precios] [mostrar param moneda]</p>
     </footer>
  
  3. MODO "tabla":
     Una plantilla match="item" mode="tabla" que genere un <tr>.
     - Condicional: si el stock es &lt;= 5, el <tr> debe tener class="warning".
     - Columnas (<td>): nombre, categoria, precio (con la moneda), proveedor, pais y stock.
  
  4. PLANTILLA RAÍZ (match="/"):
     - Genera la estructura HTML.
     - Crea un <h1>Dashboard del ERP</h1>
     - Crea una <table> con sus cabeceras (<th>).
     - Usa xsl:apply-templates con mode="tabla" sobre inventario-completo/item,
       ordenados por precio ascendente (data-type="number").
     - Finalmente, llama a la plantilla "footer-stats" con xsl:call-template.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:param name="moneda"> EUR</xsl:param>
  <xsl:variable name="totalActivos" select="count(//item)"/>
  <xsl:template name="footer-stats">
    <footer>
      <p>Total de productos activos: <xsl:value-of select="$totalActivos"/></p>
      <p>Valor total de inventario: <xsl:value-of select="sum(//precio)"/> <xsl:value-of select="$moneda"/></p>
    </footer>
  </xsl:template>
  <xsl:template match="item" mode="tabla">
    <tr>
      <xsl:if test="@stock &lt;= 5">
        <xsl:attribute name="class">warning</xsl:attribute>
      </xsl:if>
      <td><xsl:value-of select="nombre"/></td>
      <td><xsl:value-of select="@categoria"/></td>
      <td><xsl:value-of select="concat(precio, ' ' , $moneda)"/></td>
      <td><xsl:value-of select="proveedor"/></td>
      <td><xsl:value-of select="pais"/></td>
      <td><xsl:value-of select="@stock"/></td>
    </tr>
  </xsl:template>

  <xsl:template match="/">
    <html>
      <body>
      <h1>Dashboard del ERP</h1>
      <table>
        <tr>
          <th>Nombre</th>
          <th>Categoría</th>
          <th>Precio</th>
          <th>Proveedor</th>
          <th>País</th>
          <th>Stock</th>
        </tr>
        <xsl:apply-templates select="inventario-completo/item" mode="tabla">
          <xsl:sort select="precio" data-type="number" order="ascending"/>
        </xsl:apply-templates>
      </table>
      </body>

      <xsl:call-template name="footer-stats"/>

    </html>
  </xsl:template>

</xsl:stylesheet>
