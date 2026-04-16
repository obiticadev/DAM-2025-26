# Ejercicio 12: Atributo 'mode'
Utilizando el XML de catálogo (Ej. 01), crea una hoja XSLT que genere dos secciones en el mismo documento HTML:

1. **Índice:** Una lista (`<ul>`) con solo los títulos de los libros (usa `mode="resumen"`).
2. **Catálogo completo:** Una serie de artículos (`<article>`) con toda la información (título, autor, precio) usando (`mode="detalle"`).

**Objetivo:** Aprender a reutilizar el mismo `<xsl:apply-templates>` sobre los mismos nodos con diferentes salidas.
