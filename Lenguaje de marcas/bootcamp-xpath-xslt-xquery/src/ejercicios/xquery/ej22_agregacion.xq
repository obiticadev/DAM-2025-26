(: =============================================================================
   Ejercicio 22 — Funciones de Agregación (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: count(), sum(), avg(), min(), max(), estadísticas

   INSTRUCCIONES:
   Genera UN SOLO documento XML <estadisticas> con toda la información pedida.
   ============================================================================= :)

(: TODO: Escribe una consulta que devuelva este XML con los valores calculados:

   <estadisticas>
     <total-libros>{ count de todos los libros }</total-libros>
     <precio-total>{ sum de todos los precios }</precio-total>
     <precio-medio>{ avg de todos los precios }</precio-medio>
     <precio-minimo>{ min de todos los precios }</precio-minimo>
     <precio-maximo>{ max de todos los precios }</precio-maximo>
     <libros-novela>{ count de libros con genero="novela" }</libros-novela>
     <precio-medio-novela>{ avg de precios de libros de genero="novela" }</precio-medio-novela>
   </estadisticas>

   PISTA: Usa let para asignar las secuencias de precios y luego return.
:)
let $libros := /biblioteca/libro
let $totalLibros := count($libros)
let $precioTotal := sum($libros/precio)
let $precioMedio := avg($libros/precio)
let $precioMin := min($libros/precio)
let $precioMax := max($libros/precio)
let $librosNovela := $libros[@genero="novela"]
let $precioMedioNovela := avg($librosNovela/precio)
return
   <estadisticas>
     <total-libros>{ $totalLibros }</total-libros>
     <precio-total>{ $precioTotal }</precio-total>
     <precio-medio>{ $precioMedio }</precio-medio>
     <precio-minimo>{ $precioMin }</precio-minimo>
     <precio-maximo>{ $precioMax }</precio-maximo>
     <libros-novela>{ string-join($librosNovela/titulo, ', ') }</libros-novela>
     <precio-medio-novela>{ $precioMedioNovela }</precio-medio-novela>
   </estadisticas>
