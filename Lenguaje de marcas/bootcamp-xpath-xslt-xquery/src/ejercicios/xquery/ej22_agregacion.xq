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
()
