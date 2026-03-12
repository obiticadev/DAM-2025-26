# 📚 3. Validación de Documentos XML (Formación vs Validez)

La validación de documentos XML es un proceso de verificación doble. Un documento puede estar perfectamente escrito sintácticamente, pero seguir siendo rechazado por un sistema si no cumple las reglas de negocio dictaminadas por su "Esquema" jefe.

---

## 📑 Leyenda (Índice de Contenido)
1. [¿Qué es la validación de documentos?](#1-que-es-la-validacion-de-documentos)
2. [Reglas Sintácticas (Documento "Bien Formado")](#2-reglas-sintacticas-documento-bien-formado)
   - [Reglas Generales y de Caracteres](#reglas-generales-y-de-caracteres)
   - [Reglas de la Estructura (Árbol)](#reglas-de-la-estructura-arbol)
   - [Reglas Clave para Nombres, Etiquetas y Atributos](#reglas-clave-para-nombres-etiquetas-y-atributos)
3. [¿Qué es un esquema XML? (Documento "Válido")](#3-que-es-un-esquema-xml-documento-valido)
   - [Requisitos de un documento "Válido"](#requisitos-de-un-documento-valido)
4. [Definición de Esquemas XML (Modelos)](#4-definicion-de-esquemas-xml-modelos)

---

<a id="1-que-es-la-validacion-de-documentos"></a>
## 1. ¿Qué es la validación de documentos?

Consiste en comprobar que el documento cumple **dos grandes requisitos escalonados**:
1.  **Fase 1 (Bien formado):** Cumple a rajatabla todas las reglas sintácticas y de ortografía del propio lenguaje XML global. Es decir, el procesador puede leerlo sin que el programa crashee.
2.  **Fase 2 (Válido):** Además de estar bien formado, cumple sumisamente con la estructura, las etiquetas autorizadas y la gramática que le impone un documento de Ley superior externo llamado **esquema XML** (`.dtd` o `.xsd`).

---

<a id="2-reglas-sintacticas-documento-bien-formado"></a>
## 2. Reglas Sintácticas (Documento "Bien Formado")

Si un documento no está "bien formado", **no se considera un documento XML en lo absoluto**. El navegador o procesador arrojará un "Fatal Error" o "Syntax Error" y dejará de leer en la línea exacta del fallo. 

### Reglas Generales y de Caracteres
*   Los caracteres menor que (`<`) y ampersand (`&`) **solo** se pueden utilizar como marcadores estructurales de etiquetas o entidades. Si necesitas escribir un `<` dentro del texto de un mensaje tuyo, asumes un castigo si no usas la entidad escapatoria `&lt;`.

### Reglas de la Estructura (Árbol)
*   **Elemento raíz único e indiscutible:** Debe existir un **único elemento raíz** masivo (padre absoluto) que abrace al 100% del resto de sub-elementos. No puedes crear dos raíces al mismo nivel.
*   **Anidamiento cruzado (El error fatal más común):** 
    *   *MAL:* `<a><b>Texto</a></b>` (Cerraste la 'a' exterior antes de cerrar a tu hijo 'b').
    *   *BIEN:* `<a><b>Texto</b></a>` (Abre A, abres B, cierras B, y luego ya puedes cerrar A).

### Reglas Clave para Nombres, Etiquetas y Atributos
*   Deben comenzar SIEMPRE por una **letra**.
*   Los nombres jamás tendrán un **espacio en blanco**. (Por eso verás un millón de guiones bajos `mi_etiqueta_bonita` o CamelCase `MiEtiquetaBonita` en programación moderna).
*   **Es quisquilloso (Case-Sensitive):** La etiqueta de apertura y la de cierre son clones gemelos matemáticos. 
    ` <Mi_Casa>` se debe cerrar con `</Mi_Casa>`, obligatoriamente.
*   Ninguna etiqueta tendrá dos atributos gemelos a su vera con idéntico nombre: `<coche color="rojo" color="azul">`. Eso rompe al compilador.
*   **Comillas de valores:** `version="1"`. Si haces `version=1` el documento XML muere automáticamente al vuelo. Las comillas (dobles o simples) son indiscutibles al asignarle "valoréctica" a los tags.

---

<a id="3-que-es-un-esquema-xml-documento-valido"></a>
## 3. ¿Qué es un esquema XML? (Documento "Válido")

Imagina que una aseguradora le pide ficheros XML a talles de coches. Si Pepe Tallerista usa para el precio el tag `<dinero>`, y Paco el tag `<coste>`, el sistema informático de la aseguradora morirá en el intento de automatización de facturación.

Un **esquema XML** define la **gramática restrictiva (la ley)** de lo que el programador remoto tiene permitido usar al enviarle documentos XML a nuestro sistema.

### La Gramática de Esquemas establece:
1.  Qué nombres de elementos exactos y qué nombres de atributos pueden usar.
2.  La secuencia piramidal de todos esos hijos.
3.  La repetición infinita o finita min/max de apariciones de cada rama.
4.  Si dentro de `<precio>` pueden meter chistes (strings) o dinero puro (integers flotantes).

### Requisitos de un documento "Válido"
Un documento que ya era "Bien formado" se gradúa con la medalla de **"Válido"** si:
1.  Lleva colgada de la cabecera del XML una **referencia `DECLARADA`** que apunta a su URL del esquema.
2.  Solo incluye elementos del esquema (sin inventarse etiquetas random `<!ELEMENT>`).
3.  No viola reglas (Como si de forma traviesa intenta enviarte tres etiquetas `<nombre>` cuando el esquema solo permitía un tope máximo `maxOccurs="1"`).

---

<a id="4-definicion-de-esquemas-xml-modelos"></a>
## 4. Definición de Esquemas XML (Modelos)

La industria telemática mundial dictamina el estándar de validación universal entre dos bandos:

1.  **DTD (Document Type Definition):** El anticuario. Heredado del veterano sistema SGML en texto puro primitivo, incapaz de leer el tipo aritmético de datos profundo de forma sólida. Sirve de salvoconducto rápido, pero sus carencias estructurales forzaron saltos generacionales.
2.  **XSD (XML Schema Definition):** El salvador final, creado por el todopoderoso y neutral **Consorcio de la World Wide Web (W3C)** como sucesor mortal contra el DTD. Moderno, superpoderoso en control escalar atómico matemático, y dictado él mismo bajo el propio formato base de un verdadero y bonito lenguaje XML que se valida a sí mismo, un hito informático.
