<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 12 — Variables y Parámetros (XSLT)
  XML fuente: src/xml_data/catalogo.xml
  Concepto: xsl:variable, xsl:param, referencia con $, cálculos con IVA

  OBJETIVO:
  Muestra una lista de libros con su precio CON IVA, filtrando por un límite.

  REQUISITOS:
  1. Declarar un PARÁMETRO global "precioLimite" con valor por defecto 25.
  2. Declarar una VARIABLE global "iva" con valor 0.21.
  3. Declarar una VARIABLE global "totalLibros" con count(//libro).
  4. En la plantilla raíz, mostrar un <h1> con "Catálogo — X libros" (usando $totalLibros).
  5. Iterar sobre los libros. Para cada uno:
     a. Crear una variable local "precioConIva" = precio * (1 + $iva)
     b. Solo mostrar el libro si $precioConIva es MENOR que $precioLimite
     c. Mostrar en un <li>: "Título — XX.XX EUR (con IVA)"

  RESULTADO ESPERADO (con precioLimite=25):
  Los libros cuyo precio+IVA < 25 aparecerán en la lista.
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- TODO: Declarar xsl:param name="precioLimite" con select="25" -->
   <xsl:param name="precioLimite" select="25"/>

  <!-- TODO: Declarar xsl:variable name="iva" con select="0.21" -->
   <xsl:variable name="iva" select="0.21"/>

  <!-- TODO: Declarar xsl:variable name="totalLibros" con select="count(//libro)" -->
   <xsl:variable name="totalLibros" select="count(//libro)"></xsl:variable>

  <xsl:template match="/">
    <html>
      <body>
        <!-- TODO: Mostrar <h1> con "Catálogo — " seguido de $totalLibros y " libros" -->
        <h1>Catálogo - <xsl:value-of select="$totalLibros"/> libros</h1>
        <ul>
          <!-- TODO: Iterar sobre catalogo/libro con xsl:for-each
               Para cada libro:
               1. Crear xsl:variable name="precioConIva" select="precio * (1 + $iva)"
               2. Usar xsl:if test="$precioConIva &lt; $precioLimite"
               3. Dentro del if, generar <li> con:
                  xsl:value-of del titulo, " — ", xsl:value-of del $precioConIva, " EUR (con IVA)"
          -->
            <xsl:for-each select="catalogo/libro">
              <xsl:variable name="precioConIva" select="precio * (1 + $iva)"/>
              <xsl:if test="$precioConIva &lt; $precioLimite">
                <li>
                  <xsl:value-of select="concat(titulo, ' - ', $precioConIva, ' EUR (con IVA)')"/>
                </li>
              </xsl:if>
            </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
