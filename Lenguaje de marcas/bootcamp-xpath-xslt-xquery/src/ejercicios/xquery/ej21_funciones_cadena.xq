(: =============================================================================
   Ejercicio 21 — Funciones de Cadena (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: contains(), lower-case(), starts-with(), ends-with(), string-length()

   INSTRUCCIONES:
   Completa cada apartado con una consulta XQuery.
   ============================================================================= :)

(: apartado a — Busca libros cuyo título CONTENGA la palabra "de" (insensible a mayúsculas)
   Usa: contains(lower-case(...), 'de')
   Devuelve los elementos <titulo> que coincidan
:)
(: TODO :)
for $libro in /biblioteca/libro
where contains(lower-case($libro/titulo), 'de')
return
   <titulo>{$libro/titulo}</titulo>

(: apartado b — Busca libros cuyo autor EMPIECE por "C"
   Usa: starts-with(...)
   Devuelve <resultado><autor>...</autor><titulo>...</titulo></resultado>
:)
(: TODO :)
for $libro in /biblioteca/libro
where starts-with($libro/autor, 'C')
return
   <resultado>
      <autor>{$libro/autor}</autor>
      <titulo>{$libro/titulo}</titulo>
   </resultado>

(: apartado c — Busca libros cuyo título tenga MÁS de 15 caracteres
   Usa: string-length(...)
   Devuelve <titulo-largo>{ texto del titulo }</titulo-largo>
:)
(: TODO :)
for $libro in /biblioteca/libro
where string-length($libro/titulo) > 15
return
   <titulo-largo>
      <titulo>{$libro/titulo}</titulo>
   </titulo-largo>

(: apartado d — Convierte TODOS los títulos a mayúsculas
   Usa: upper-case(...)
   Devuelve <titulo-upper>{ título en mayúsculas }</titulo-upper> para cada libro
:)
(: TODO :)
()
