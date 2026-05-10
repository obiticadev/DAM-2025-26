(: =============================================================================
   Ejercicio 24 — XQUF: XQuery Update Facility
   XML fuente: src/xml_data/biblioteca.xml (se usa una COPIA para no mutar el original)
   Concepto: insert node, delete node, replace value of, rename node

   INSTRUCCIONES:
   Este ejercicio tiene 4 apartados. Escribe UNA operación XQUF por apartado.
   Los tests ejecutan cada apartado por separado contra una copia temporal del XML.
   ============================================================================= :)

(: apartado a — Insertar un libro nuevo al final de <biblioteca>
   Inserta:
   <libro id="6" genero="novela">
     <titulo>El señor de los anillos</titulo>
     <autor>Tolkien</autor>
     <precio>25.00</precio>
     <anio>1954</anio>
   </libro>
   Usa: insert node ... into /biblioteca
:)
(: TODO :)
()

(: apartado b — Cambiar el precio del libro con id="2" a "19.99"
   Usa: replace value of /biblioteca/libro[@id='2']/precio with '19.99'
:)
(: TODO :)
()

(: apartado c — Añadir un atributo disponible="true" al primer libro
   Usa: insert node attribute disponible { 'true' } into /biblioteca/libro[1]
:)
(: TODO :)
()

(: apartado d — Borrar todos los libros con precio mayor que 20
   Usa: delete node /biblioteca/libro[number(precio) > 20]
:)
(: TODO :)
()
