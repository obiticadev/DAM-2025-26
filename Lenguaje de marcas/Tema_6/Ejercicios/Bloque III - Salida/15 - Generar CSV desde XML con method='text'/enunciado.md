# Ejercicio 15: Generación de CSV (Texto plano)
Transforma el XML de empleados en un archivo **CSV** con cabecera.

**Requisitos:**
- Usa `<xsl:output method="text" />`.
- La cabecera debe ser: `nombre,departamento,salario`.
- Cada línea debe terminar con un salto de línea (pista: usa `&#10;` dentro de `<xsl:text>`).
- Evita espacios en blanco innecesarios en la salida.
