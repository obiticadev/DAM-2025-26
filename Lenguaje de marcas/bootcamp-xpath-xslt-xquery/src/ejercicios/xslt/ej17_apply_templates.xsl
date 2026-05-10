<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 17 — apply-templates vs for-each (XSLT)
  XML fuente: src/xml_data/biblioteca.xml
  Concepto: xsl:apply-templates, plantillas por tipo de nodo, paradigma declarativo

  OBJETIVO:
  Transforma biblioteca.xml en HTML usando SOLO xsl:apply-templates
  (NO uses xsl:for-each en este ejercicio).

  REQUISITOS:
  1. Plantilla match="/": genera la estructura HTML básica.
     Dentro del <body>, usa xsl:apply-templates select="biblioteca/libro"
     con xsl:sort por titulo ascendente.

  2. Plantilla match="libro": genera un <div class="libro"> con:
     a. <h2> con el título
     b. Aplicar plantillas a los hijos del libro: xsl:apply-templates

  3. Plantilla match="autor": genera <p class="autor">Autor: [valor]</p>

  4. Plantilla match="precio": genera <p class="precio">Precio: [valor] EUR</p>

  5. Plantilla match="anio": genera <span class="anio">([valor])</span>

  6. Plantilla match="titulo": no generes nada (el h2 del libro ya lo muestra).
     Usa una plantilla vacía: <xsl:template match="titulo"/>

  RESULTADO ESPERADO (estructura):
  <html>
    <body>
      <h1>Biblioteca</h1>
      <div class="libro">
        <h2>Cien años de soledad</h2>
        <p class="autor">Autor: García Márquez</p>
        <p class="precio">Precio: 22.00 EUR</p>
        <span class="anio">(1967)</span>
      </div>
      ...  (ordenados alfabéticamente por título)
    </body>
  </html>
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- TODO: Plantilla match="/" → estructura HTML base
       Dentro del body: <h1>Biblioteca</h1>
       Luego xsl:apply-templates select="biblioteca/libro"
       con xsl:sort select="titulo" order="ascending"
       -->
       <xsl:template match="/">
  <html>
    <body>
      <h1>Biblioteca</h1>
      <xsl:apply-templates select="biblioteca/libro">
        <xsl:sort select="titulo" order="ascending"/>
      </xsl:apply-templates>
    </body>
    </html>
  </xsl:template>
  <!-- TODO: Plantilla match="libro" → <div class="libro">
       <h2>[titulo]</h2>
       <xsl:apply-templates/>  (aplica las plantillas a autor, precio, anio, titulo)
  -->
  <xsl:template match="libro">
    <div>
      <xsl:attribute name="class">libro</xsl:attribute>
      <h2><xsl:value-of select="titulo"/></h2>
      <xsl:apply-templates select="*"/>
    </div>
  </xsl:template>

  <!-- TODO: Plantilla match="titulo" → vacía (para que no duplique el título)
       <xsl:template match="titulo"/>
  -->
  <xsl:template match="titulo"/>

  <!-- TODO: Plantilla match="autor" → <p class="autor">Autor: [valor]</p> -->
  <xsl:template match="autor">
    <p><xsl:attribute name="class">autor</xsl:attribute>Autor: <xsl:value-of select="."/></p>
  </xsl:template>
  <!-- TODO: Plantilla match="precio" → <p class="precio">Precio: [valor] EUR</p> -->
  <xsl:template match="precio">
    <p><xsl:attribute name="class">precio</xsl:attribute>Precio: <xsl:value-of select="."/> EUR</p>
  </xsl:template>
  <!-- TODO: Plantilla match="anio" → <span class="anio">([valor])</span> -->
  <xsl:template match="anio">
    <span><xsl:attribute name="class">anio</xsl:attribute>(<xsl:value-of select="."/>)</span>
  </xsl:template>

</xsl:stylesheet>