<?xml version="1.0" encoding="UTF-8"?>
<!--
  Ejercicio 11 — Condicionales (XSLT)
  XML fuente: src/xml_data/empleados.xml
  Concepto: xsl:if, xsl:choose/when/otherwise

  OBJETIVO:
  Transforma empleados.xml en una tabla HTML con columnas:
  Nombre | Departamento | Salario | Nivel

  REQUISITOS:
  1. Si el departamento está vacío (string-length = 0), mostrar "Sin asignar".
  2. La columna "Nivel" se calcula así:
     - salario > 3500  → "Senior"
     - salario > 2500  → "Medio"
     - en otro caso    → "Junior"
  3. Si el empleado NO está activo (activo="false"), mostrar su nombre en cursiva (<em>).

  RESULTADO ESPERADO (estructura):
  <html>
    <body>
      <table>
        <tr><th>Nombre</th><th>Departamento</th><th>Salario</th><th>Nivel</th></tr>
        <tr><td>Ana López</td><td>Desarrollo</td><td>3200</td><td>Medio</td></tr>
        <tr><td><em>María García</em></td><td>Desarrollo</td><td>1900</td><td>Junior</td></tr>
        <tr><td>Pedro Vega</td><td>Sin asignar</td><td>4100</td><td>Senior</td></tr>
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
        <h1>Empleados</h1>
        <table>
          <tr>
            <th>Nombre</th>
            <th>Departamento</th>
            <th>Salario</th>
            <th>Nivel</th>
          </tr>
          <!-- TODO: Iterar sobre empresa/empleado con xsl:for-each.
               Para cada empleado generar un <tr> con 4 <td>:

               1. Nombre: Si activo="false", envolver en <em>. Si activo="true", texto normal.
                  (Usa xsl:choose o dos xsl:if)

               2. Departamento: Si string-length(departamento) = 0, mostrar "Sin asignar".
                  Si no, mostrar el valor de departamento.
                  (Usa xsl:choose con xsl:when y xsl:otherwise)

               3. Salario: Muestra el valor directamente.

               4. Nivel: Usa xsl:choose con 3 ramas:
                  - xsl:when test="salario > 3500" → "Senior"
                  - xsl:when test="salario > 2500" → "Medio"
                  - xsl:otherwise → "Junior"
          -->
          <xsl:for-each select="empresa/empleado">
            <tr>
              <td>
                <xsl:if test="@activo='false'">
                  <em><xsl:value-of select="nombre"/></em>
                </xsl:if>
                <xsl:if test="@activo='true'">
                  <xsl:value-of select="nombre"/>
                </xsl:if>
              </td>
              <td>
                <xsl:choose>
                  <xsl:when test="string-length(departamento) = 0">
                    Sin asignar
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="departamento"/>
                  </xsl:otherwise>
                </xsl:choose>
              </td>
              <td>
                <xsl:value-of select="salario"/>
              </td>
              <td>
                <xsl:choose>
                  <xsl:when test="salario > 3500">
                    Senior
                  </xsl:when>
                  <xsl:when test="salario > 2500">
                    Medio
                  </xsl:when>
                  <xsl:otherwise>
                    Junior
                  </xsl:otherwise>
                </xsl:choose>
              </td>
            </tr>

          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
