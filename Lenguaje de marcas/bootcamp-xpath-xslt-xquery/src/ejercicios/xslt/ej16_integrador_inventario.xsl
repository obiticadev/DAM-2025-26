<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 16 — Integrador Inventario (XSLT)
  XML fuente: src/xml_data/inventario.xml
  Concepto: Combina param + sort + choose + plantilla nombrada + footer

  OBJETIVO:
  Genera una página HTML completa tipo "dashboard" del inventario.

  REQUISITOS:
  1. PARÁMETRO "stockMinimo" con valor por defecto 5.
  2. PLANTILLA NOMBRADA "cabecera" que genere:
     <header><h1>Dashboard de Inventario</h1><p>Total: X productos</p></header>
  3. Productos ordenados por PRECIO DESCENDENTE (data-type="number").
  4. Para cada producto, un <article> con:
     a. Atributo class CSS condicional usando xsl:attribute:
        - stock = 0       → class="agotado"
        - stock <= $stockMinimo → class="bajo"
        - otro caso        → class="ok"
     b. <h3> con el nombre
     c. <p> con "Categoría: [categoria]"
     d. <p> con "Precio: [precio] EUR"
     e. <p> con "Stock: [stock] uds."
  5. Un <footer> con el precio TOTAL acumulado (sum) de todos los productos.

  RESULTADO ESPERADO (estructura):
  <html>
    <body>
      <header><h1>Dashboard de Inventario</h1><p>Total: 6 productos</p></header>
      <main>
        <article class="agotado"><h3>Monitor 27"</h3>...</article>
        <article class="ok"><h3>Licencia Office</h3>...</article>
        ...
      </main>
      <footer><p>Valor total del inventario: 763.38 EUR</p></footer>
    </body>
  </html>
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- TODO: Declarar xsl:param name="stockMinimo" con select="5" -->
  <xsl:param name="stockMinimo" select="5"/>
  <!-- TODO: Crear xsl:template name="cabecera" que genere:
       <header>
         <h1>Dashboard de Inventario</h1>
         <p>Total: [count(//producto)] productos</p>
       </header>
  -->
  <xsl:template name="cabecera">
    <header>
      <h1>Dasboard de Inventario</h1>
      <p>Total: <xsl:value-of select="count(//producto)"/> productos</p>
    </header>
  </xsl:template>
  <xsl:template match="/">
    <html>
      <body>
        <!-- TODO: Llamar a la plantilla "cabecera" -->
         <xsl:call-template name="cabecera"/>

        <main>
          <!-- TODO: Iterar sobre inventario/producto
               Ordenar por precio descendente (data-type="number")
               Para cada producto:
               1. Generar <article>
               2. Añadir xsl:attribute name="class" con xsl:choose:
                  - stock = 0 → "agotado"
                  - stock <= $stockMinimo → "bajo"
                  - otherwise → "ok"
               3. Contenido del article:
                  <h3>[nombre]</h3>
                  <p>Categoría: [categoria]</p>
                  <p>Precio: [precio] EUR</p>
                  <p>Stock: [stock] uds.</p>
          -->
                  <xsl:for-each select="inventario/producto">
                    <xsl:sort select="precio" data-type="number" order="descending"/>
                      <article>
                        <xsl:attribute name="class">
                          <xsl:choose>
                            <xsl:when test="stock = 0">agotado</xsl:when>
                            <xsl:when test="stock &lt;= $stockMinimo">bajo</xsl:when>
                            <xsl:otherwise>ok</xsl:otherwise>
                          </xsl:choose>
                        </xsl:attribute>
                        <h3><xsl:value-of select="nombre"/></h3>
                        <p>Categoría: <xsl:value-of select="@categoria"></xsl:value-of></p>
                        <p>Precio: <xsl:value-of select="precio"/> EUR</p>
                        <p>Stock: <xsl:value-of select="stock"/> uds.</p>
                      </article>
                    
                  </xsl:for-each>
        </main>

        <footer>
          <!-- TODO: <p>Valor total del inventario: [sum(//precio)] EUR</p> -->
           <p>Valor total del inventario: <xsl:value-of select="sum(//precio)"/> EUR</p>
        </footer>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
