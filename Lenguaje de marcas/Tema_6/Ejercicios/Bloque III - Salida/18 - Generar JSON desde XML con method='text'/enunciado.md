# Ejercicio 18: Generación de JSON manual
Convierte el XML de artículos en un **array de objetos JSON** válido.

**Requisitos:**
- Salida tipo texto.
- Formato esperado: `[ {"id":"...", "nombre":"...", "precio":...}, ... ]`
- **Importante:** Debes controlar que la última línea no lleve coma al final. 
- Pista: Usa `xsl:if test="position() != last()"` para poner la coma separadora.
