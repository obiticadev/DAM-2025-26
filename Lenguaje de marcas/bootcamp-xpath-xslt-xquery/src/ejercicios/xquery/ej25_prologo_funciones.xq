(: =============================================================================
   Ejercicio 25 — Prólogo y Funciones (XQuery)
   XML fuente: src/xml_data/biblioteca.xml
   Concepto: Prólogo, declare function, tipos XSD, if-then-else

   INSTRUCCIONES:
   Completa el prólogo y la consulta principal.
   ============================================================================= :)

(: TODO: Declara la versión XQuery :)
(: xquery version '3.1'; :)

(: TODO: Declara una variable global $IVA de tipo xs:decimal con valor 0.21 :)
(: declare variable $IVA as xs:decimal := 0.21; :)

(: TODO: Declara una función local:precio-con-iva que reciba un xs:decimal
   y devuelva el precio multiplicado por (1 + $IVA)
   declare function local:precio-con-iva($precio as xs:decimal) as xs:decimal {
     $precio * (1 + $IVA)
   };
:)

(: TODO: Declara una función local:clasificar que reciba un xs:decimal (precio)
   y devuelva un xs:string:
   - Si precio > 20 → "premium"
   - Si precio > 10 → "normal"
   - Si no → "económico"
   Usa if-then-else (recuerda: else es OBLIGATORIO en XQuery)
:)

(: TODO: Consulta principal
   Para cada libro en /biblioteca/libro:
   1. Calcula el precio con IVA usando local:precio-con-iva()
   2. Clasifica el libro usando local:clasificar()
   3. Devuelve:
      <libro-clasificado>
        <titulo>{ data del titulo }</titulo>
        <precio-original>{ precio }</precio-original>
        <precio-iva>{ precio con IVA }</precio-iva>
        <categoria>{ clasificación }</categoria>
      </libro-clasificado>
:)
()
