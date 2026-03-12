# 📚 4. Esquemas XSD (XML Schema Definition)

El sucesor moderno, definitivo y potente de DTD impulsado por el W3C. Es la última tecnología en validación profunda.

---

## 📑 Leyenda (Índice de Contenido)
1. [¿Qué es XSD y sus Ventajas?](#1-que-es-xsd-y-sus-ventajas)
2. [Estructura y Referencia de un Esquema XSD](#2-estructura-y-referencia-de-un-esquema-xsd)
3. [Declaraciones de Elementos (xs:element)](#3-declaraciones-de-elementos-xselement)
4. [Declaraciones de Atributos (xs:attribute)](#4-declaraciones-de-atributos-xsattribute)
5. [Tipos de Datos Base](#5-tipos-de-datos-base-simples-y-complejos)
   - [5.1. Datos Simples (xs:simpleType)](#51-tipos-de-datos-simples-xssimpletype)
   - [5.2. Datos Complejos (xs:complexType)](#52-tipos-de-datos-complejos-xscomplextype)
6. [Indicadores en Tipos Complejos](#6-indicadores-en-tipos-complejos)
   - [A. Indicadores de Orden (Sequence, Choice, All)](#a-indicadores-de-orden)
   - [B. Indicadores de Ocurrencia (Occurs)](#b-indicadores-de-ocurrencia)
   - [C. Grupos reusables (Group)](#c-indicadores-de-grupo)
7. [Restricciones y Facetas Avanzadas](#7-restricciones-facetas-avanzadas)

---

<a id="1-que-es-xsd-y-sus-ventajas"></a>
## 1. ¿Qué es XSD y sus Ventajas?

Un **esquema XSD** dictamina la ley sobre qué XML es válido. Ventajas demoledoras frente a su antepasado DTD:
1.  **Sintaxis XML Pura:** No tienes que aprenderte otro lenguaje raro "plano". Escribirás las leyes validantes **usando las mismas etiquetas y atributos XML** de la vida diaria. ¡Los validadores XML online validarán tu propio validador porque de cajón, es XML!
2.  **Tipado fuerte:** Conoce la fecha `xs:date`, el precio decimal `xs:decimal` y el booleano verdadero/falso. Se acabó tratar todo como triste texto general "PCDATA".
3.  **Control matemático de cardinalidad:** Despídete del comodín torpe "Asterisco *" o "Suma +". Si el departamento solo tramita 5 becas, puedes clavar a fuego un `maxOccurs="5"`.
4.  **Soporta Espacios de Nombres (Namespaces)** para fusionar varios vocabularios en archivos kilométricos.

---

<a id="2-estructura-y-referencia-de-un-esquema-xsd"></a>
## 2. Estructura y Referencia de un Esquema XSD

<a id="estructura-interna-xsd"></a>
### 2.1. Estructura interna del archivo XSD (`.xsd`)
Las etiquetas "oficiales" directas de XSD se les suele acoplar el prefijo `xs:` en el "NameSpace" oficial `http://www.w3.org/2001/XMLSchema`.
*   Apertura y raíz dictatorial: `<xs:schema>`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="biblioteca" /> <!-- Definir el elemento raíz superior dictatorial del XML -->
</xs:schema>
```

<a id="referencia-del-esquema-en-xml"></a>
### 2.2. Referencia del esquema desde el XML ("La Promesa")
Para que tu solitario XML en "biblioteca.xml" intente ganarse el honor de validez, debe "prometer" en su cabecera que él está dispuesto a acatar sin rechistar las leyes del W3C, para ello vincula en su raíz su paradero con el parámetro localizador `xsi:noNamespaceSchemaLocation="archivoXSD.xsd"`.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<biblioteca xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:noNamespaceSchemaLocation="biblioteca.xsd">
    ...
</biblioteca>
```

---

<a id="3-declaraciones-de-elementos-xselement"></a>
## 3. Declaraciones de Elementos (`xs:element`)

Las leyes para crear un elemento (tag con texto, con sub-hijos, vacío...) y de cuántas veces puede salir esa etiqueta a relucir en el XML.

**Sintaxis completa en XSD:**
```xml
<xs:element name="apellidos" type="xs:string" default="Desconocido" minOccurs="0" maxOccurs="1" />
```

*   `name="apellidos"`: Se creará la etiqueta `<apellidos>` en el XML real. Obligatorio poner un nombre a menos que llames a uno pre-creado vía `ref`.
*   `type="xs:string"`: Es texto alfabético/numérico (string).
*   `default`: Valor predeterminado autoinyectado si la dejan sorda (o `fixed` si es dictatorial e inalterable).
*   `minOccurs="0"` y `maxOccurs="1"`: Es el equivalente al antiguo `?` condicional opcional de DTD. (Tope para multiplicador infinito: `"unbounded"`). *Atención: Al elemento Raíz Mayor supremo que se aloja colgando del `xs:schema` original NO LE PUEDES METER maxOccurs! Siempre tiene que salir 1 única vez.*

---

<a id="4-declaraciones-de-atributos-xsattribute"></a>
## 4. Declaraciones de Atributos (`xs:attribute`)

Otorgan los famosos "propiedades de etiqueta" extra. Tienen los mismos atributos mágicos que el Element (type, ref...)

**Sintaxis XSD:**
```xml
<xs:attribute name="moneda" type="xs:string" default="euro" use="required" />
```

*   `use="required"`: Es un atributo letal. Si se le olvida al chaval programarlo en su `<hamburguesa moneda="EUR">` estallará el compilador. También están `optional` (Predeterminado de fábrica) y `prohibited`.
*   **Regla estructural**: Si un Elemento contiene atributos, SIEMPRE pasará de ser un elemento tipo Simple `xs:simpleType` para transmutarse en la forma superior compleja `xs:complexType` alojándolos **siempre el último en la fila, al fondo del todo, después de las secuencias de hijos**.

---

<a id="5-tipos-de-datos-base-simples-y-complejos"></a>
## 5. Tipos de Datos Base (Simples y Complejos)

XSD discrimina los datos por jerarquías "Genealógicas" (quién puede alojar a quién).

<a id="51-tipos-de-datos-simples-xssimpletype"></a>
### 5.1. Datos Simples (`xs:simpleType`)
Los elementos Simples **JAMÁS pueden ser Padres de otras etiquetas**, **y JAMÁS pueden contener atributos pegados a ellos propios**. Un Simple Type de toda la vida es puro texto raso.

1.  **Tipos Atómicos Base Nativos:** `xs:string`, `xs:integer`, `xs:date`, `xs:boolean`.
2.  **Tipos definidos bajo restricciones severas por el programador:** Un programador puede inventar su propio tipo especial aplicando *Facetas* cortadoras al Base Nativo.

> **EJEMPLO: Crear un tipo de dato llamado "longitudMaxima" e insertarlo**
> ```xml
> <!-- 1. Declarar globalmente una raza inventada de tipo string, a la cual le extirpamos el maximo largo perimitido con una cuchilla 'restriction' dejandolo encadenado a no poder pasar los 10 carácteres... -->
> <xs:simpleType name="tipoStringPequenito">
>     <xs:restriction base="xs:string">
>         <xs:maxLength value="10"/>
>     </xs:restriction>
> </xs:simpleType>
> 
> <!-- 2. Lo usamos para nuestra etiqueta nombre que solo queremos que tenga texto corto -->
> <xs:element name="nombre_usuario" type="tipoStringPequenito"/>
> ```

<a id="52-tipos-de-datos-complejos-xscomplextype"></a>
### 5.2. Datos Complejos (`xs:complexType`)
Son los Jefes de Equipo de las etiquetas. Se dedican a engullir hijos en fila (`xs:element`), a guardar atributos en sus propios tags, o ser un mixto caótico.

> **EJEMPLO DE TIPO COMPLEJO COMPLETO:**
> El clásico `<persona id="3"> <nombre/> <edad/> </persona>` metido a XSD se escribe con paciencia, haciendo "muñecas matrioskas" al ir encerrando `element` > `ComplexType` > `sequence` > Hijos Elementos... Y finalmente Atributos al salir de Sequence.
> 
> ```xml
> <!-- La capa exterior, que encierra un TypeComplejo de toda la vida... -->
> <xs:element name="persona"> 
>     <xs:complexType> 
>         <xs:sequence> <!-- Indica orden estricto de bajada en hijos-->
>             <xs:element name="nombre" type="xs:string"/>
>             <xs:element name="edad" type="xs:integer"/>
>         </xs:sequence>
>         <!-- Atributo escondido pegado al complexType justo después de cerrar todas las sequences de sus hijos! (Para validar el famoso: <persona numero="1">) -->
>         <xs:attribute name="numero_socio" type="xs:integer" use="required"/>
>     </xs:complexType>
> </xs:element>
> ```

---

<a id="6-indicadores-en-tipos-complejos"></a>
## 6. Indicadores en Tipos Complejos
Leyes agrupadoras para ordenar e instruir la disciplina a la hora de soltar hijos por el XML final.

<a id="a-indicadores-de-orden"></a>
### A. Indicadores de Orden
*   **`xs:sequence`**: Los hijos XML se deben tirar al fichero en el estricto mismo orden marcial top-down escrito en el archivo de reglas `XSD`.
*   **`xs:choice`**: Opción de caminos A-B-C. Solo UNO puede sobrevivir y aparecer finalmente por ese cruce.
*   **`xs:all`**: Batiburrillo ameno. Salen todos de forma necesaria pero en cualquier orden caótico en el XML.

<a id="b-indicadores-de-ocurrencia"></a>
### B. Indicadores de Ocurrencia (Occurs)
Si te da la locura y a un `xs:choice` le plantas un `maxOccurs="4"`, el usuario en XML podrá generar de forma esquizofrénica hasta 4 variaciones de hijos por cada ronda (Podría meter A... y en vez de cerrarse, abre a B y la elige, y luego dos C's).

<a id="c-indicadores-de-grupo"></a>
### C. Grupos Reusables (Group)
*   Agrupa bloques estandarizados (como si fueran Componentes Web o Plantillas copiables) con `xs:group`. Se llaman con `ref="id_del_grupo"`.
*   Ideal para el bloque `<direccion>` genérico: `<calle> <piso> <codigo_postal>`. En lugar de picar ese mismo tocho de código dentro del Vendedor y luego dentro del Comprador, lo picas global envuelto de `<xs:group>` y luego usas la variable.

---

<a id="7-restricciones-facetas-avanzadas"></a>
## 7. Restricciones y Facetas Avanzadas

Llevando el control matemático de validación al máximo nivel (se incrustan dentro del cascarón restrictivo de un SimpleType):

*   **Rango de Fechas / Numérico:** (El más común). Obliga al entero o al decimal a flotar.
    *   `minInclusive` (Mayor o igual a...), `maxExclusive` (Menor estricto a...).
*   **Poda drástica (Enumeración Selectors):** La etiqueta `<coche>` obliga a encerrarlo en Valores selectivos fijos de marca de coche.
    ```xml
    <xs:restriction base="xs:string">
        <xs:enumeration value="BMW"/>
        <xs:enumeration value="Audi"/>
        <xs:enumeration value="Tesla"/>
    </xs:restriction>
    <!-- Esto prohibirá rotunamente a un XML generar un tag: <coche>Nissan</coche> -->
    ```
*   **Seguridad Regex Patterns (`pattern`):** Creado para los locos de la validación. Genera un patrón maestro ultra restrictivo.
    *   `[A-Z][a-z0-9_]{3,}`: A-Z mayúscula para arrancar, seguido de tres o hasta el infinito alfanumérico en minúscula pegados a guiones bajos para el resto del texto.
*   **Tratamiento Pulidor (Espacios Muertos `whiteSpace`):** En Strings largos.
    *   `preserve` / `replace` / `collapse` (Este último es el terrorífico destructor de párrafos que fulmina y comprime todos los tabuladores, intros `\n` y 400 espacios intermedios gigantes colapsándolos en un solo bonito espacio universal " ").
