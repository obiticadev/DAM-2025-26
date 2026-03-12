# 🚀 CHEATSHEET & GLOSARIO DEFINITIVO: XML, DTD Y XSD

Gran diccionario de sintaxis rápida. Contiene **todas** las estructuras, ejemplos, atributos y reglas vistas en el Tema 5.

---
## 📑 Índice de Bloques Sintácticos
1. [Sintaxis de Lenguaje XML Base](#1-sintaxis-de-lenguaje-xml-base)
2. [El Glosario DTD Completo](#2-el-glosario-dtd-completo)
3. [El Glosario XSD Completo](#3-el-glosario-xsd-completo)
4. [Diccionario de Namespaces (Espacios de Nombres)](#4-diccionario-de-namespaces)
---

<a id="1-sintaxis-de-lenguaje-xml-base"></a>
## 1. Sintaxis de Lenguaje XML Base

### 1.1 Bloque Completo XML (Cabecera, DTD y Atributos)
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE nodo_raiz SYSTEM "archivo.dtd">
<?xml-stylesheet type="text/css" href="estilo.css"?>

<nodo_raiz atributo_base="SIEMPRE_CON_COMILLAS">
  <nodo_hijo1></nodo_hijo1>
  <nodo_vacio_hijo2 />
</nodo_raiz>
```

### 1.2 Reglas estáticas (Fail-States)
*   **Prohibido atributos gemelos:** `<taza color="rojo" color="azul">` (Sintax Error).
*   **Prohibido espacios en tags:** `<mi taza>` (Sintax Error).
*   **Prohibido cruzar cierres (Anidamiento):** `<a><b></a></b>` (Sintax Error).
*   **Prohibido meter atributos en el End-Tag:** `</taza color="rojo">` (Sintax Error).

### 1.3 Entidades y CDATA
```xml
<!-- Entidades: Definición de variables globales rápidas. -->
<!ENTITY empresa "Mi Gran Empresa Inc.">
<texto>Trabajo en &empresa;</texto> <!-- Retorna: Trabajo en Mi Gran Empresa Inc. -->

<!-- CDATA: Bloque blindado que impide que XML lea su contenido como etiquetas operacionales -->
<codigo_crudo>
    <![CDATA[ <p>Esto es HTML, si XML lo intentara parsear moriría.</p> ]]>
</codigo_crudo>
```

---

<a id="2-el-glosario-dtd-completo"></a>
## 2. El Glosario DTD Completo

*(Leyes arcaicas extraídas al texto plano bajo etiquetas exclamativas `!`).*

### 2.1 Sintaxis de los Elementos (`<!ELEMENT>`)
| Tipo / Uso de DTD | Ejemplo Literal | Descripción del Efecto |
| :--- | :--- | :--- |
| **PCDATA (Texto Raso)** | `<!ELEMENT nombre (#PCDATA)>` | Obliga a que la etiqueta `<nombre>` guarde **sólo texto literario**, y prohíbe que guarde a otros hijos dentro. |
| **EMPTY (Vacías)** | `<!ELEMENT salto_linea EMPTY>` | Obliga a que `<salto_linea/>` sea vacía SIEMPRE. |
| **ANY (Caos total)** | `<!ELEMENT caja ANY>` | Te autoriza a meterle sub-componentes o textos pelados. Libre albedrío inútil matemáticamente. |

### 2.2 Agrupadores y Operadores Parentales (Para hijos dentro del ELEMENT)
```xml
<!ELEMENT padre (a, b?, (c | d)*, e+)>
```
*   **`,` (Coma):** Criterio de Secuencia. Orden marcial dictatorial. "Primero esto, luego lo otro".
*   **`|` (Tubo / OR):** Alternativa única. "O te coges a b o a c, pero nunca los 2".
*   **`?` (Interrogante):** "Un triste 0 o un estricto 1 intento de repeticiones de la etiqueta".
*   **`*` (Asterisco):** "Opción opcional a generar infinitas etiquetas clon".
*   **`+` (Suma):** "Debes generar por ley mínimo la etiqueta 1 VEZ, y de ahí infinitas si quieres".

### 2.3  Glosario de Atributos (`<!ATTLIST>`)
```xml
<!-- Estructura base: Nombre, CampoQueAtaca, Limitante, GradoDeObligatoriedad -->
<!ATTLIST empleado clave ID #REQUIRED>
```
| Limitante | Significado en DTD |
| :--- | :--- |
| **`CDATA`** | Texto libre. (Ojo, a diferencia de Element PCDATA, Atributo tiene su propia variante textCDAta). |
| **`(uno | dos)`** | Sólo puedes usar como comodín las palabras enumeradas ahí. (Ej. `color (rojo\|azul)`). |
| **`NMTOKEN`** | Prohíbe totalmente espacios entre palabras en el tributo. ("hola_mundo" vale, "hola mundo" da Crash). |
| **`ID`** | Nombra a ese atributo como una **Primary Key**. En todo el XML nadie más podrá asignarse ese valor exacto. |
| **`IDREF`** | **Foreign Key**. Obliga a enganchar un valor verificado que exista listado como `ID` de otro hijo lejanamente por el XML. |

**Obligatoriedad Oculta (Modificadores finales):**
*   `#REQUIRED`: Tu código XML debe poner manualmente ese `<tag atr="...">`.
*   `#IMPLIED`: Lo puedes poner.. o puedes ignorar alegremente su existencia.
*   `"12"`: Es opcional escribirlo, pero de no hacerlo yo DTD, se lo insertaré en oculto forzando su impresión.
*   `#FIXED "12"`: Puedes u omitirlo o escribirlo, PERO si lo escribes manualmente estás OBLIGADO A ESCRIBIR `12`.

---

<a id="3-el-glosario-xsd-completo"></a>
## 3. El Glosario XSD Completo

*(La maravilla jerárquica contemporánea. Estructurado 100% en puro XML y altamente tipado).*

### 3.1 Promesas e Iniciaciones
```xml
<!-- En archivo miRegla.xsd (Se declara el Schema y el padre supremo) -->
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:element name="raiz_suprema" />
</xs:schema>

<!-- En archivo local .xml (Le prometes instanciar el Schema y le chivas la ruta) -->
<raiz_suprema xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:noNamespaceSchemaLocation="miRegla.xsd">
```

### 3.2. Elements y Atributtes base
```xml
<xs:element name="edad_alumno" type="xs:integer" default="18" minOccurs="0" maxOccurs="3"/>
<xs:attribute name="clase" type="xs:string" use="required" />
```
*(Nota de sangre: `maxOccurs` jamás debe emplearse para la Raíz Global Padre del document XSD al que referencie el .XML, o estallará todo).*
Tipos Nativos: `xs:string`, `xs:integer`, `xs:date`, `xs:boolean`, `xs:decimal`.

### 3.3. SimpleType Vs ComplexType
**`xs:simpleType`**: Únicamente puede portar texto en crudo o dígitos internos puros. NO POSEE ATRIBUTOS, NO POSEE HIJOS. Sirve para inyectar límites férreos (Facetas/Restrictions).
**`xs:complexType`**: Permite almacenar Elementos u hospeda Atributos a su final.

```xml
<!-- EL COMPLEJO STANDARD MATRIOSKA COMPLETO DE DÍA A DÍA -->
<xs:element name="empleado">
    <xs:complexType>
        <!-- 1. Los Indicadores: Todo va empaquetado adentro en secuencias o paths -->
        <xs:sequence>
            <!-- (xs:sequence -> Estricto Marcial. xs:choice -> Sólo 1. xs:all -> Caos de orden) -->
            <xs:element name="nombre" type="xs:string" />
            <xs:element name="salario" type="xs:decimal" />
        </xs:sequence>
        <!-- 2. Los Atributos deben ir obligatoriamente después del cierre global de agrupamiento. -->
        <xs:attribute name="n_seguridad_social" type="xs:string" use="required" />
    </xs:complexType>
</xs:element>
```

### 3.4. Facetas y Restricciones (El poder máximo XSD - SimpleType)
¿Quieres un "Edad" entre 18 y máximo de 65 años justos? Usas `xs:restriction` atada al tipo numérico `base`.

```xml
<xs:element name="edad_trabajador">
    <xs:simpleType>
        <xs:restriction base="xs:integer">
            <xs:minInclusive value="18"/>      
            <xs:maxInclusive value="65"/>      <!-- También existe el maxExclusive que repelería el 65 dejándolo en tope 64! -->
        </xs:restriction>
    </xs:simpleType>
</xs:element>
```

**Bloque de Lista de Restricciones Facetadas Relevantes:**
| Faceta | Sintaxis/Atributo | Para qué se usa |
| :--- | :--- | :--- |
| **Enum** | `<xs:enumeration value="Lunes"/>` | Limitar inputs en arrays gigantes textuales inamovibles. |
| **Dígitos** | `<xs:totalDigits value="4"/>` | El float/decimal/numero tiene límite de visualizaciones. |
| **Largos** | `<xs:maxLength value="20"/>` | Texto string de 20 campos obligatorios de máximo limite de largo. |
| **Regex** | `<xs:pattern value="[A-Z]{3}"/>` | Limitar obligando al programador XML a meter un formato fijo de 3 letras en bloque mayúscula y prohibiendo el resto. |
| **WhiteSpace** | `<xs:whiteSpace value="collapse"/>` | Arrancador de tabulaciones basura en crudo y espacios dobles/triples ocultos en tu XML. |

### 3.5. Grupos Modulares Reusables XSD
Escribir lo mismo todo el rato es estúpido. Puedes fabricar variables globales componentes.
```xml
<!-- Fabricado a primer nivel en Schema (FUERA DEL ELEMENT RAÍZ) -->
<xs:group name="GrupitoFijo">
    <xs:sequence>
        <xs:element name="nombre" type="xs:string"/>
        <xs:element name="dni" type="xs:string"/>
    </xs:sequence>
</xs:group>

<!-- Invocado en lugar del listado de codigo, dentro del Complex->Sequence -->
<xs:group ref="GrupitoFijo" minOccurs="1" maxOccurs="1"/>
```

---

<a id="4-diccionario-de-namespaces"></a>
## 4. Diccionario de Namespaces (Espacios de Nombres)

Para evitar que tu `title` de facturas chille colapsando tu XML contra un `title` genérico de una librería incrustada al HTML global.

### El código mágico: Atributo `xmlns` (Xml Name Space)
Se acopla junto a nombres base en las raíces supremas unificadoras para definir un terreno de juego claro.

```xml
<!-- Múltiples prefijos definidos (Alias) -->
<raiz xmlns:miApp="http://www.ejemplo.es/1" 
      xmlns:facturas="http://www.facturas.es/schema">
    
    <!-- Hay que invocar con cariño sus Dos Puntos -->
    <miApp:title> Titulo Normal </miApp:title>
    <facturas:title> 15.02 Euros Acumulativo de título base </facturas:title>
    
</raiz>
```

### El Namespace Default Limpiador Global
*Si ignoras otorgarle prefijo (se declara como `xmlns="..."`), este será el DICTADOR SILENCIOSO sobre cualquier nombre "huérfano" por debajo de él.*
```xml
<raiz xmlns="http://www.ejemplo.es/1" 
      xmlns:h="http://www.w3.org/html">
    
    <!-- Ya no tengo a miApp! Adquiero la URL base general por Default de padre! -->
    <title> Titulo Normal General </title>
    <h:title> Titulo exótico invocado por h:</h:title>
</raiz>
```
