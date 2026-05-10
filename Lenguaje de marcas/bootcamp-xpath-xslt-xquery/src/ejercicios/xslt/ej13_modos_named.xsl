<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 13 — Modos y Plantillas Nombradas (XSLT)
  XML fuente: src/xml_data/catalogo.xml
  Concepto: mode="resumen" / mode="detalle", xsl:call-template, name=""

  OBJETIVO:
  Genera una página HTML con DOS secciones:
  1. Una lista resumen (solo títulos) usando mode="resumen"
  2. Fichas completas (título + autor + precio) usando mode="detalle"
  3. Una cabecera reutilizable usando una plantilla NOMBRADA.

  REQUISITOS:
  1. Crear una plantilla nombrada "cabecera" que genere:
     <header><h1>Mi Catálogo</h1><p>Total: X libros</p></header>
  2. Crear una plantilla match="libro" mode="resumen" que genere:
     <li>TÍTULO</li>
  3. Crear una plantilla match="libro" mode="detalle" que genere:
     <article><h2>TÍTULO</h2><p>Autor: AUTOR</p><p>Precio: PRECIO EUR</p></article>
  4. En la plantilla raíz:
     a. Llamar a la plantilla "cabecera" con xsl:call-template
     b. Generar <ul> y aplicar plantillas con mode="resumen"
     c. Generar <section> y aplicar plantillas con mode="detalle"
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- TODO: Crear xsl:template name="cabecera"
       Que genere:
       <header>
         <h1>Mi Catálogo</h1>
         <p>Total: [count(//libro)] libros</p>
       </header>
  -->
    <xsl:template name="cabecera">
      <header>
        <h1>Mi Catálogo</h1>
        <p>Total: <xsl:value-of select="count(//libro)"/> libros</p>
      </header>
    </xsl:template>

  <!-- TODO: Crear xsl:template match="libro" mode="resumen"
       Que genere: <li>[titulo]</li>
  -->
    <xsl:template match="libro" mode="resumen">
          <li>
            <xsl:value-of select="titulo"/>
          </li>
    </xsl:template>
  <!-- TODO: Crear xsl:template match="libro" mode="detalle"
       Que genere:
       <article>
         <h2>[titulo]</h2>
         <p>Autor: [autor]</p>
         <p>Precio: [precio] EUR</p>
       </article>
  -->
    <xsl:template match="libro" mode="detalle">
        <article>
          <h2><xsl:value-of select="titulo"/></h2>
          <p>Autor: <xsl:value-of select="autor"/></p>
          <p>Precio: <xsl:value-of select="precio"/> EUR</p>
        </article>
    </xsl:template>

  <xsl:template match="/">
    <html>
      <body>
        <!-- TODO: Llamar a la plantilla "cabecera" con xsl:call-template -->
        <xsl:call-template name="cabecera"/>
        <h2>Lista de títulos:</h2>
        <ul>
          <!-- TODO: xsl:apply-templates select="catalogo/libro" mode="resumen" -->
           <xsl:apply-templates select="catalogo/libro" mode="resumen"/>
        </ul>

        <h2>Fichas completas:</h2>
        <section>
          <!-- TODO: xsl:apply-templates select="catalogo/libro" mode="detalle" -->
           <xsl:apply-templates select="catalogo/libro" mode="detalle"/>
        </section>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
