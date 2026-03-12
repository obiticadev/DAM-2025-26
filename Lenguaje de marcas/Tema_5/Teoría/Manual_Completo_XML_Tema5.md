# 📖 Manual Completo y Definitivo de XML, DTD y XSD (Tema 5)

Este manual recopila, íntegramente y sin omitir un solo detalle, todo el contenido de los 5 bloques del Tema 5 de Lenguaje de Marcas (Lenguaje XML, Esquemas DTD, Validación, Esquemas XSD y Namespaces). Toda la información, casos de uso, ejemplos y estructura original se conservan intactos en este archivo maestro para consulta general.

---

## 📑 Índice General

*   [**PARTE I: Lenguaje XML (Fundamentos y Estructura)**](#parte-i-lenguaje-xml)
    *   [1. ¿Qué es XML?](#1-que-es-xml)
    *   [2. Usos Principales de XML](#2-usos-principales-de-xml)
    *   [3. Estructura de un documento XML](#3-estructura-de-un-documento-xml)
    *   [4. Componentes de un documento XML](#4-componentes-de-un-documento-xml)
    *   [5. Ejemplo Completo y Nomenclatura del Árbol XML](#5-ejemplo-completo-y-nomenclatura-del-arbol-xml)
*   [**PARTE II: Esquemas DTD (Document Type Definition)**](#parte-ii-esquemas-dtd)
    *   [6. ¿Qué es DTD?](#6-que-es-dtd)
    *   [7. Referencia a un esquema DTD](#7-referencia-a-un-esquema-dtd)
    *   [8. Declaraciones de Elementos (<!ELEMENT>)](#8-declaraciones-de-elementos)
    *   [9. Anidamiento y Cardinalidad en Elementos](#9-anidamiento-y-cardinalidad)
    *   [10. Declaraciones de Entidades (<!ENTITY>)](#10-declaraciones-de-entidades)
    *   [11. Declaraciones de Atributos (<!ATTLIST>)](#11-declaraciones-de-atributos)
    *   [12. Limitaciones Famosas de DTD](#12-limitaciones-famosas-de-dtd)
*   [**PARTE III: Validación de Documentos XML**](#parte-iii-validacion-de-documentos-xml)
    *   [13. ¿Qué es la validación de documentos?](#13-que-es-la-validacion)
    *   [14. Reglas Sintácticas (Documento "Bien Formado")](#14-reglas-sintacticas-bien-formado)
    *   [15. ¿Qué es un esquema XML? (Documento "Válido")](#15-esquema-xml-valido)
    *   [16. Definición de Esquemas XML (Modelos)](#16-modelos-esquemas)
*   [**PARTE IV: Esquemas XSD (XML Schema Definition)**](#parte-iv-esquemas-xsd)
    *   [17. ¿Qué es XSD y sus Ventajas?](#17-que-es-xsd)
    *   [18. Estructura y Referencia de un Esquema XSD](#18-estructura-referencia-xsd)
    *   [19. Declaraciones de Elementos (xs:element)](#19-declaraciones-elementos)
    *   [20. Declaraciones de Atributos (xs:attribute)](#20-declaraciones-atributos)
    *   [21. Tipos de Datos Base (Simples y Complejos)](#21-tipos-de-datos)
    *   [22. Indicadores en Tipos Complejos](#22-indicadores-complejos)
    *   [23. Restricciones y Facetas Avanzadas](#23-restricciones-facetas)
*   [**PARTE V: Espacios de Nombres en XML (Namespaces)**](#parte-v-espacios-de-nombres)
    *   [24. El Problema Universal de la Colisión XML](#24-problema-colision)
    *   [25. El Atributo xmlns y los prefijos](#25-atributo-xmlns)
    *   [26. El Espacio de Nombres por Defecto](#26-espacio-por-defecto)

---
---

<a id="parte-i-lenguaje-xml"></a>
# PARTE I: Lenguaje XML (Fundamentos y Estructura)

XML es la base estándar de intercambio de datos en la web estructurada. Su comprensión sólida es fundamental antes de pasar a su validación (DTD o XSD).

<a id="1-que-es-xml"></a>
## 1. ¿Qué es XML?
**XML** (eXtensible Markup Language) es un subconjunto de **SGML**, simplificado y adaptado a Internet. 
*   **Legibilidad:** Es legible y comprensible para cualquier usuario aunque no tenga conocimientos de lenguajes de marcas, ya que las etiquetas aportan el significado al contenido que encierran ("Self-Describing").
*   **Metalenguaje:** No es un lenguaje de marcas estricto (como HTML con sus etiquetas fijas `<h1>`, `<body>`), sino un **metalenguaje**. Te permite inventar tus propias etiquetas para cubrir las necesidades de tu sistema.
*   **Origen:** Creado por el **W3C** (World Wide Web Consortium) a finales de los años 90 (1998).
*   **Finalidad:** Permite representar **información estructurada** en la web de modo que pueda ser almacenada, transmitida, procesada y visualizada por diversos tipos de aplicaciones y dispositivos, sin importar el lenguaje de programación en el que estén escritos.

<a id="2-usos-principales-de-xml"></a>
## 2. Usos Principales de XML
El uso fundamental de XML se basa en el **almacenamiento y distribución neutral de información**:
1.  **Intercambio de información entre aplicaciones:** Rompe la barrera entre plataformas. Un servidor Linux con BD Oracle puede enviar datos limpios a un móvil iOS mediante un archivo `.xml`.
2.  **Computación distribuida (Web Services clásicos, SOAP):** Los documentos XML son **inocuos** y seguros, no pueden contener código maligno ejecutable.
3.  **Información empresarial y de configuración:** Utilizado masivamente para generar documentos de facturación (Facturae), archivos de configuración (`pom.xml` de Maven, o `AndroidManifest.xml` de Android) gracias a su rigidez jerárquica.

<a id="3-estructura-de-un-documento-xml"></a>
## 3. Estructura de un documento XML
Un documento XML consiste en un fichero de texto plano (extensión **`.xml`**) que contiene datos delimitados por etiquetas.

### 3.1. Estructura Física (Bloques del documento)
Se distinguen dos bloques diferenciados (el primero es opcional pero muy recomendable):
1.  **El Prólogo (o cabecera):** Contiene información sobre la versión de XML, codificación del archivo y si depende de validaciones externas.
2.  **El Cuerpo:** Contiene todos los datos reales encapsulados en etiquetas.

> **EJEMPLO DE ESTRUCTURA BÁSICA:**
> ```xml
> <!-- 1. PROLOGO -->
> <?xml version="1.0" encoding="UTF-8" standalone="no"?>
> <!DOCTYPE persona SYSTEM "persona.dtd">
> 
> <!-- 2. CUERPO -->
> <persona>
>     <nombre>Luis</nombre>
>     <apellidos>Pérez</apellidos>
> </persona>
> ```

### 3.2. Estructura Lógica (Árbol Dirigido)
Lógicamente, un XML tiene diseño de **árbol genealógico dirigido**. Todos los nodos, excepto uno, tienen un único padre.
*   **Nodo raíz:** El envoltorio total. Agrupa a todos los demás y **no tiene padre**. En el ejemplo anterior, sería `<persona>`. Solo puede haber 1 por documento.
*   **Nodos hermanos:** Aquellos que cuelgan del mismo padre en el mismo nivel.
*   **Nodos descendientes/ascendientes:** Relación de hijos, nietos, padres o abuelos.

<a id="4-componentes-de-un-documento-xml"></a>
## 4. Componentes de un documento XML

### 4.1. Etiquetas (Tags)
Marcas que identifican un contenido encerrándolo entre `< >`. Son *Case-Sensitive*.
1.  **Apertura (start-tag):** `<apartado>`
2.  **Cierre (end-tag):** Llevan barra diagonal inicial. `</apartado>`.
3.  **Vacías (empty-tag):** Llevan barra diagonal final. `<salto-de-linea />`.

### 4.2. Elementos
Son el bloque estructural completo (Apertura + Contenido + Cierre).
```xml
<autor>Maria Jimenez</autor>
```

### 4.3. Atributos
Propiedades anexadas a las etiquetas de apertura. Forman la estructura **`nombre="valor"`**. Su valor DEBE ir obligatoriamente entre comillas siempre.
*   **Regla vital 1:** ¡NUNCA en etiquetas de cierre! Solo en apertura o vacías.
*   **Regla vital 2:** No puedes repetir el mismo atributo dos veces en la misma etiqueta.

### 4.4. Instrucciones de Procesamiento
Son directivas para el motor/navegador que va a leer el XML indicándole cómo procesarlo (`<? ... ?>`).
```xml
<?xml-stylesheet type="text/css" href="estilo.css"?>
```

### 4.5. Entidades y Referencias a entidades
*   **Entidades:** Constantes globales para no repetir textos largos. Se declaran `<!ENTITY ...>`.
*   **Referencias:** Invocar esa constante. Empiezan por **`&`** y terminan con **`;`**. 
Existen 5 predefinidas: `&lt;` (<), `&gt;` (>), `&amp;` (&).

### 4.6. Secciones CDATA (Character Data)
Cápsulas blindadas. Cualquier texto escrito dentro de `<![CDATA[ ... ]]>` será ignorado por el procesador XML. 
```xml
<![CDATA[ <strong>Texto destacado</strong> ]]>
```

<a id="5-ejemplo-completo-y-nomenclatura-del-arbol-xml"></a>
## 5. Ejemplo Completo y Nomenclatura del Árbol XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<biblioteca>
    <libro>
        <titulo>La vida está en otra parte</titulo>
        <autor>Milan Kundera</autor>
        <fechaPublicacion año="1973"/>
    </libro>
    <libro>
        <titulo>Pantaleón y las visitadoras</titulo>
        <autor fechaNacimiento="28/03/1936">Mario Vargas Llosa</autor>
        <fechaPublicacion año="1973"/>
    </libro>
</biblioteca>
```
**Nodos:**
1.  **Nodo Raíz (Document Root):** Origen invisible (`/`).
2.  **Nodo Elemento:** Etiquetas estructurales (`<libro>`).
3.  **Nodo Atributo:** Propiedades de elementos (`año="1973"`).
4.  **Nodo de Texto:** La información pura alojada dentro (`"Milan Kundera"`).

---
---

<a id="parte-ii-esquemas-dtd"></a>
# PARTE II: Esquemas DTD (Document Type Definition)

El DTD fue la primera tecnología nacida en la Web para comprobar ("validar") que un XML inventor de etiquetas tenía sentido lógico.

<a id="6-que-es-dtd"></a>
## 6. ¿Qué es DTD?
**DTD** es un documento de texto plano (que **no** está escrito en sintaxis XML, no tiene etiquetas clásicas) que dicta las leyes y normas para validar un documento XML. 
*   Establece qué elementos o atributos pueden aparecer, orden y parentesco.
*   El XML se debe enfrentar al DTD para lograr el certificado de **Documento Válido**.

<a id="7-referencia-a-un-esquema-dtd"></a>
## 7. Referencia a un esquema DTD (Conexión)
1.  **Interna:** Meter las normas DTD directamente en la cabecera del XML.
2.  **Externa (SYSTEM):** Llamar a un archivo en tu disco duro (`.dtd`). 
*   **Pública (`PUBLIC`):** Llamar a DTDs famosos que residen en una URL de internet.

```xml
<!DOCTYPE agenda SYSTEM "normativa_agenda.dtd">
```

<a id="8-declaraciones-de-elementos"></a>
## 8. Declaraciones de Elementos (`<!ELEMENT>`)
Definen desde cero cómo se llama la etiqueta y qué relleno tiene autorizado.
```xml
<!ELEMENT nombreElemento contenidoPermitido >
```

### Tipos de Contenido Base:
*   **`EMPTY`**: No contiene nada. `<!ELEMENT saltoLinea EMPTY>`
*   **`(#PCDATA)`**: Solo acepta Texto Puro. `<!ELEMENT nombre (#PCDATA)>`
*   **`ANY`**: Permite cualquier cosa. `<!ELEMENT cajonDeSastre ANY>`

<a id="9-anidamiento-y-cardinalidad"></a>
## 9. Anidamiento y Cardinalidad en Elementos
Obligaciones entre paréntesis `(...)`.

### A. Operadores Lógicos (Orden o Exclusión)
*   **Coma ( `,` ) - Secuencia Estricta:** Indica el orden obligatorio y de forzosa aparición.
    `<!ELEMENT jugador (nombre, apellidos)>` 
*   **Pipe ( `|` ) - Alternativa Excluyente:** Puede aparecer el de la izquierda o el de la derecha, pero nunca ambos.
    `<!ELEMENT contacto (email | telefono)>`

### B. Operadores Matemáticos (Multiplicadores)
*   **Interrogante ( `?` ):** Opcional (0 o 1 vez). `<!ELEMENT ficha (nombre, segundoNombre?)>`
*   **Asterisco ( `*` ):** Múltiple Opcional (0 o Infinitas veces). `<!ELEMENT pelicula (titulosAlternativos*)>`
*   **Suma ( `+` ):** Múltiple Obligatorio (1 o Infinitas veces). `<!ELEMENT listado_compra (producto+)>`

<a id="10-declaraciones-de-entidades"></a>
## 10. Declaraciones de Entidades (`<!ENTITY>`)
Un DTD proveyendo atajos para el XML al usar el `&...;`.
```xml
<!ENTITY autor_oficial "Servicio de Salud de Madrid S.A.">
```

<a id="11-declaraciones-de-atributos"></a>
## 11. Declaraciones de Atributos (`<!ATTLIST>`)
Añaden propiedades laterales a las etiquetas.
```xml
<!ATTLIST nombreElementoASobreponer nombreAtributo tipoRestriccion comportamientoCierre >
```

### 11.1 Tipos de Atributos:
*   **`CDATA`**: Texto libre arbitrario.
*   **`NMTOKEN`**: Prohíbe espacios " " en su valor.
*   **Enumeraciones `(op1 | op2)`**: Limita a opciones cerradas.
*   **`ID` y `IDREF`**: Identificador único global y Clave foránea referencial.

### 11.2 Comportamientos Finales (Obligatoriedad):
*   **`#REQUIRED`**: Atributo de presencia obligatoria.
*   **`#IMPLIED`**: Atributo opcional.
*   **`"valor por defecto"`**: Se inserta solo si el usuario no escribe el atributo.
*   **`#FIXED "inamovible"`**: Constante dictatorial, si la escribes debe ser exactamente ESE valor.

<a id="12-limitaciones-famosas-de-dtd"></a>
## 12. Limitaciones Famosas de DTD
1.  **Imposibilidad de Tipado de Datos Fuerte:** En DTD todo es texto. No existe `xs:integer`.
2.  **Imposibilidad de limitar repeticiones exactas:** No sabe hacer "min=15 max=15".
3.  **No soporta Namespaces:** Choca en colisiones multi-herramientas.
4.  **No es XML nativo:** Su sintaxis es prehistórica.

---
---

<a id="parte-iii-validacion-de-documentos-xml"></a>
# PARTE III: Validación de Documentos XML (Formación vs Validez)

<a id="13-que-es-la-validacion"></a>
## 13. ¿Qué es la validación de documentos?
Consiste en comprobar que el documento cumple **dos grandes requisitos escalonados**:
1.  **Fase 1 (Bien formado):** Cumple reglas sintácticas propias de XML.
2.  **Fase 2 (Válido):** Además de estar bien formado, cumple con la estructura impuesta por el **esquema XML** asignado.

<a id="14-reglas-sintacticas-bien-formado"></a>
## 14. Reglas Sintácticas (Documento "Bien Formado")
Si no están, el parser morirá dando Error Sintáctico.
*   Obligatorio escapar `<` con `&lt;`.
*   Un **único elemento raíz absoluto**.
*   **Anidamiento cruzado (El error fatal más común):** `<a><b>Texto</b></a>` en vez de `<a><b>Texto</a></b>`.
*   Nombres **nunca contendrán espacios** en blanco (`mi_etiqueta`). Iniciar con vocal/consonante.
*   **Case-Sensitive:** `<Mi_Casa>` se debe cerrar con `</Mi_Casa>`.
*   Están prohibidos los atributos clonados `<coche color="rojo" color="azul">`.
*   Las **comillas de valores** son obligatorias.

<a id="15-esquema-xml-valido"></a>
## 15. ¿Qué es un esquema XML? (Documento "Válido")
Define la **gramática restrictiva (la ley)** que rige a las etiquetas, secuencias, topes numéricos y tipado. Un XML es válido si trae consigo el `DECLARADO` (DOCTYPE o schemaLocation), obedece las normas marcadas y no inventa tags de la nada.

<a id="16-modelos-esquemas"></a>
## 16. Definición de Esquemas XML (Modelos)
1.  **DTD:** Heredado de SGML en texto puro primitivo. Limitado.
2.  **XSD:** Creado por W3C, moderno, puramente escrito en XML y súper tipado (aritmético).

---
---

<a id="parte-iv-esquemas-xsd"></a>
# PARTE IV: Esquemas XSD (XML Schema Definition)

<a id="17-que-es-xsd"></a>
## 17. ¿Qué es XSD y sus Ventajas?
Mecanismo moderno del W3C:
1.  **Sintaxis XML Pura:** No hay sintaxis extraña, se formula como XML.
2.  **Tipado fuerte:** Cientos de tipos: Date, Decimal, Boolean...
3.  **Control matemático de cardinalidad:** Límites rígidos y precisos como `maxOccurs="5"`.
4.  **Soporta Espacios de Nombres (Namespaces).**

<a id="18-estructura-referencia-xsd"></a>
## 18. Estructura y Referencia de un Esquema XSD
**El documento `.xsd`:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="biblioteca" /> 
</xs:schema>
```
**Conexión en XML (La promesa):**
```xml
<biblioteca xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:noNamespaceSchemaLocation="biblioteca.xsd">
```

<a id="19-declaraciones-elementos"></a>
## 19. Declaraciones de Elementos (`xs:element`)
```xml
<xs:element name="apellidos" type="xs:string" default="Desconocido" minOccurs="0" maxOccurs="1" />
```
*   `maxOccurs` y `minOccurs` (Soporta `"unbounded"` para infinito).
*   Se prohíbe el uso de `maxOccurs` sobre el super elemento de la raíz global del documento.

<a id="20-declaraciones-atributos"></a>
## 20. Declaraciones de Atributos (`xs:attribute`)
```xml
<xs:attribute name="moneda" type="xs:string" default="euro" use="required" />
```
*   Tienen `use="required|optional|prohibited"`. 
*   **Importante:** Cuando un Elemento decide incrustarse atributos, pasará a ser catalogado genéticamente como `xs:complexType` automáticamente, y el atributo irá a posteriori, cerrando la lista de validación tras la cabecera secuencial.

<a id="21-tipos-de-datos"></a>
## 21. Tipos de Datos Base (Simples y Complejos)
### 21.1. Datos Simples (`xs:simpleType`)
JAMÁS pueden ser Padres de otras etiquetas. JAMÁS contienen atributos pegados a ellos propios. Válidos para puras atómicas: `integer`, `string`.
Puedes crear restricciones inventadas por ti mismo sobre un `xs:string`.

### 21.2. Datos Complejos (`xs:complexType`)
Hospedan sub-hijos, o atributos, o todos los anteriores juntos a la vez.
```xml
<xs:element name="persona"> 
    <xs:complexType> 
        <xs:sequence>
            <xs:element name="nombre" type="xs:string"/>
            <xs:element name="edad" type="xs:integer"/>
        </xs:sequence>
        <xs:attribute name="numero_socio" type="xs:integer" use="required"/>
    </xs:complexType>
</xs:element>
```

<a id="22-indicadores-complejos"></a>
## 22. Indicadores en Tipos Complejos
*   **`xs:sequence`**: Los hijos XML se deben tirar al fichero en el estricto mismo orden.
*   **`xs:choice`**: Opción de caminos A-B-C. Solo UNO puede sobrevivir.
*   **`xs:all`**: Todos saldrán al paso pero en cualquier caótico orden.
*   **Grupos Reusables (Group / AttributeGroup):** Para crear componentes estándar recargables usando `ref="bloque"`.

<a id="23-restricciones-facetas"></a>
## 23. Restricciones y Facetas Avanzadas
Poda drástica del tipo de datos en XSD (encerrado en una base de restricción):
*   **Fechas/Numérico:** `minInclusive`, `maxExclusive`.
*   **Lista de Valores (`enumeration`):** Limitado a strings como "Audi", "Tesla".
*   **Expresión Regular (`pattern`):** Patrones matemáticos o literales regex ultra potentes. Ej `[A-Z0-9]+`.
*   **Tratamiento `whiteSpace`**: `preserve`, `replace`, o destructores `collapse` para purgar tabulaciones.

---
---

<a id="parte-v-espacios-de-nombres"></a>
# PARTE V: Espacios de Nombres en XML (Namespaces)

<a id="24-problema-colision"></a>
## 24. El Problema Universal de la Colisión XML
La tragedia ocurre si mezclamos una DTD de libros con una DTD de web HTML y ambas usan `<title>`. El procesador muere al intentar encajar la semiótica de la regla 1 con la regla 2 simultáneamente de un mismo tag.

<a id="25-atributo-xmlns"></a>
## 25. El Atributo `xmlns` y los prefijos
El atributo fijo especial `xmlns:` sella y distingue los espacios de nombre asociando la procedencia con prefijos.
```xml
<documento_maestro  xmlns:prefijo_mio="http://www.miapp.com/esquema" 
                    xmlns:w3c_html="http://www.w3c.org/html">
    <prefijo_mio:title>Documento de Informes del Mes</prefijo_mio:title>
    <prefijo_mio:contenido_rico>
        <w3c_html:title>Titulo oculto de mi web de HTML</w3c_html:title>
    </prefijo_mio:contenido_rico>
</documento_maestro>
```

<a id="26-espacio-por-defecto"></a>
## 26. El Espacio de Nombres por Defecto (Por limpieza visual)
Para no enguarrar todas las 10,000 etiquetas de un fichero general, se puede declarar sin prefijo `xmlns="urloficial.com"`. De esta manera, todo el arbolado que nazca huérfano de prefijos pertenecerá por asimilación default a dicha URL sin re-escribir códigos extraños, debiendo declarar únicamente el gueto de las extrañas extranjeras ajenas.

```xml
<documento_maestro  xmlns="http://www.miapp.com/esquema_generalizado" 
                    xmlns:w3c_html="http://www.w3c.org/html">
    <title>Asume sin prefijos y limpiamente su lugar global generalizado</title>
    <w3c_html:title>Identificado como forastero</w3c_html:title>
</documento_maestro>
```
