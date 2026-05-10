(: =============================================================================
   Ejercicio 23 — Joins (XQuery)
   XML fuente: src/xml_data/biblioteca.xml + src/xml_data/autores.xml
   Concepto: Join entre dos documentos, exists(), empty()

   NOTA IMPORTANTE: En este ejercicio los XMLs se cargan como variables externas.
   Usa $biblioteca y $autores como raíces de cada documento:
     - $biblioteca/biblioteca/libro  (para acceder a los libros)
     - $autores/autores/autor        (para acceder a los autores)

   INSTRUCCIONES:
   Cruza los datos de biblioteca.xml con autores.xml.
   La "clave foránea" es: libro/autor = autor/nombre
   ============================================================================= :)

(: TODO: Escribe un FLWOR que:
   1. Itere sobre cada libro:  for $libro in $biblioteca/biblioteca/libro
   2. Busque su autor en el otro documento:
      let $autor := $autores/autores/autor[nombre = $libro/autor]
   3. Filtre solo los que tengan coincidencia: where exists($autor)
   4. Devuelva un XML con datos de AMBOS documentos:
      return
        <libro-completo>
          <titulo>{ data($libro/titulo) }</titulo>
          <autor>{ data($libro/autor) }</autor>
          <nacionalidad>{ data($autor/nacionalidad) }</nacionalidad>
          <siglo>{ data($autor/siglo) }</siglo>
        </libro-completo>
:)
()
