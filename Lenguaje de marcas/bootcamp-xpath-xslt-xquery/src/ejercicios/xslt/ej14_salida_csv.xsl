<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 14 — Salida CSV (XSLT)
  XML fuente: src/xml_data/empleados.xml
  Concepto: method="text", xsl:text, salto de línea con &#10;

  OBJETIVO:
  Transforma empleados.xml en un fichero CSV con esta estructura:

  nombre,departamento,salario
  Ana López,Desarrollo,3200
  Carlos Ruiz,Analítica,2800
  ...

  REQUISITOS:
  1. Usa xsl:output method="text"
  2. Primera línea: cabecera "nombre,departamento,salario" seguida de salto de línea
  3. Para cada empleado: nombre + "," + departamento + "," + salario + salto de línea
  4. Usa xsl:text para las comas y &#10; para los saltos de línea
  5. Usa normalize-space() en el nombre para limpiar espacios extra
-->
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" encoding="UTF-8"/>

  <xsl:template match="/">
    <!-- TODO: Emitir la cabecera CSV usando xsl:text:
         nombre,departamento,salario&#10;
    -->
         <xsl:text>nombre,departamento,salario&#10;</xsl:text>
    <!-- TODO: Iterar sobre empresa/empleado con xsl:for-each
         Para cada empleado, emitir:
         - normalize-space(nombre)
         - xsl:text con la coma ","
         - departamento
         - xsl:text con la coma ","
         - salario
         - xsl:text con salto de línea &#10;
    -->
         <xsl:for-each select="empresa/empleado">
          <xsl:value-of select="normalize-space(nombre)"/>
          <xsl:text>,</xsl:text>
          <xsl:value-of select="departamento"/>
          <xsl:text>,</xsl:text>
          <xsl:value-of select="salario"/>
          <xsl:text>&#10;</xsl:text>
         </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>
