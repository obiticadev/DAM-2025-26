(: =============================================================================
   Ejercicio 20 — FLWOR con let (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: let vs for, variables locales, cálculos

   INSTRUCCIONES:
   Completa cada apartado. Fíjate en la diferencia entre for (itera) y let (asigna).
   ============================================================================= :)

(: apartado a — Calcula el precio con IVA (21%) para cada libro
   Usa: for $libro in ...
        let $precio := number($libro/precio)
        let $precioIva := $precio * 1.21
        return <libro-iva><titulo>...</titulo><precio-iva>{ $precioIva }</precio-iva></libro-iva>
:)
(: TODO: Escribe tu FLWOR con let aquí :)
for $libro in /biblioteca/libro
let $precio := number($libro/precio)
let $precioIva := $precio * 1.21
return
   <libro-iva>
      <titulo>{$libro/titulo}</titulo>
      <precio-iva>{$precioIva}</precio-iva>
   </libro-iva>

(: apartado b — Muestra solo libros cuyo precio con IVA sea MENOR que 15
   Combina let (para calcular) y where (para filtrar)
   return <oferta><titulo>...</titulo><precio-final>{ ... }</precio-final></oferta>
:)
(: TODO: Escribe tu FLWOR aquí :)
for $libro in /biblioteca/libro
let $precio := number($libro/precio)
let $precioIva := $precio * 1.21
where $precioIva < 15
return
   <oferta>
      <titulo>{$libro/titulo}</titulo>
      <precio-iva>{$precioIva}</precio-iva>
   </oferta>

(: apartado c — Usa SOLO let (sin for) para obtener estadísticas globales
   let $precios := /biblioteca/libro/precio
   return <stats>
            <total>{ count($precios) }</total>
            <suma>{ sum($precios) }</suma>
            <media>{ avg($precios) }</media>
          </stats>
:)
(: TODO: Escribe tu consulta con let (sin for) aquí :)
let $precio := /biblioteca/libro/precio
return
   <stats>
      <total>{count($precio)}</total>
      <suma>{sum($precio)}</suma>
      <media>{avg($precio)}</media>
   </stats>
