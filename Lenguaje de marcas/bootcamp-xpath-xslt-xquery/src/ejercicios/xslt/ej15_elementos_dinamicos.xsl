<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 15 — Elementos y Atributos Dinámicos (XSLT)
  XML fuente: src/xml_data/inventario.xml
  Concepto: xsl:element, xsl:attribute, {} AVT (Attribute Value Templates)

  OBJETIVO:
  Transforma inventario.xml en un NUEVO XML con esta estructura:

  <inventario-resumen>
    <item ref="P01" categoria="Hardware">
      <nombre>Teclado mecánico</nombre>
      <estado>disponible</estado>
    </item>
    <item ref="P03" categoria="Hardware">
      <nombre>Monitor 27"</nombre>
      <estado>agotado</estado>
    </item>
    ...
  </inventario-resumen>

  REQUISITOS:
  1. Usa xsl:output method="xml"
  2. Para cada producto, genera un elemento <item> con:
     a. Atributo "ref" cuyo valor sea el @id del producto (usa AVT: ref="{@id}")
     b. Atributo "categoria" cuyo valor sea el @categoria del producto
  3. Dentro de <item>:
     a. Elemento <nombre> con el nombre del producto
     b. Elemento <estado> con:
        - "agotado" si stock = 0
        - "bajo" si stock <= 5
        - "disponible" en otro caso
        (usa xsl:choose para determinar el texto del estado)
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <inventario-resumen>
      <!-- TODO: Iterar sobre inventario/producto con xsl:for-each
           Para cada producto, generar:
           <item ref="{@id}" categoria="{@categoria}">
             <nombre>[nombre del producto]</nombre>
             <estado>
               [usa xsl:choose para decidir: agotado / bajo / disponible]
             </estado>
           </item>
      -->
          <xsl:for-each select="inventario/producto">
            <item ref="{@id}" categoria="{@categoria}">
              <nombre><xsl:value-of select="nombre"/></nombre>
              <estado>
                <xsl:choose>
                  <xsl:when test="stock = 0">agotado</xsl:when>
                  <xsl:when test="stock &lt;= 5">bajo</xsl:when>
                  <xsl:otherwise>disponible</xsl:otherwise>
                </xsl:choose>
              </estado>
            </item>
          </xsl:for-each>
    </inventario-resumen>
  </xsl:template>

</xsl:stylesheet>
