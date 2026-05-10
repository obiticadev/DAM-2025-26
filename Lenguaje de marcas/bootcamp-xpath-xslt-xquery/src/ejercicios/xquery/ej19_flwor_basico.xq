(: =============================================================================
   Ejercicio 19 — FLWOR Básico (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: for, where, order by, return

   INSTRUCCIONES:
   Completa cada apartado reemplazando TODO el bloque (: TODO :) ... ()
   con tu consulta FLWOR.

   IMPORTANTE: Cada apartado debe devolver XML válido.
   ============================================================================= :)

(: apartado a — Devuelve los títulos de TODOS los libros envueltos en <titulo>
   Usa: for $libro in /biblioteca/libro
        return <titulo>{ texto del titulo }</titulo>
:)
(: TODO: Escribe tu FLWOR aquí :)
for $libro in /biblioteca/libro
return <titulo>{$libro/titulo/text()}</titulo>

(: apartado b — Devuelve los títulos de libros con precio > 10, ordenados por precio descendente
   Usa: for ... where number(...) > 10 ... order by ... descending ... return ...
   Envuelve cada resultado en <libro-caro>{ titulo }</libro-caro>
:)
(: TODO: Escribe tu FLWOR aquí :)
for $libro in biblioteca/libro
where $libro/precio > 10
order by $libro/precio descending
return 
   <libro-caro>{$libro/titulo}</libro-caro>

(: apartado c — Devuelve los libros publicados después del año 1900
   Usa: for ... where ... > 1900 ... return ...
   Envuelve cada resultado en <moderno><titulo>...</titulo><anio>...</anio></moderno>
:)
(: TODO: Escribe tu FLWOR aquí :)
for $libro in biblioteca/libro
where $libro/anio > 1900
return
   <moderno>
      <titulo>{$libro/titulo}</titulo>
      <anio>{$libro/anio}</anio>
   </moderno>
