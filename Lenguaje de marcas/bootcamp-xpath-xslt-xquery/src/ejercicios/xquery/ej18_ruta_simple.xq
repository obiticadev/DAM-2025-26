(: =============================================================================
   Ejercicio 18 — Ruta Simple (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: doc(), rutas XPath en XQuery, expresiones simples

   INSTRUCCIONES:
   Completa cada apartado reemplazando el () vacío con tu consulta XQuery.
   Cada apartado está marcado con (: apartado X :).
   NO borres los comentarios de apartado.

   NOTA: En estos tests, el XML se carga como contexto (no necesitas doc()).
   Simplemente usa las rutas XPath directamente.
   ============================================================================= :)

(: apartado a — Selecciona TODOS los títulos de libros :)
//titulo

(: apartado b — Selecciona el texto del título del libro con id="2" :)
(: TODO: Usa un predicado para filtrar por @id y extrae el text() :)
//libro[@id="2"]/titulo/text()

(: apartado c — Selecciona todos los autores del documento :)
(: TODO: Ruta simple para obtener todos los elementos <autor> :)
//autor

(: apartado d — Selecciona los libros del género "novela" :)
(: TODO: Usa un predicado sobre el atributo @genero :)
//libro[@genero="novela"]

(: apartado e — Selecciona el precio del último libro :)
(: TODO: Usa last() para acceder al último libro y luego su precio :)
//libro[position()=last()]/precio
