# 📚 1. Lenguaje XML (Fundamentos y Estructura)

XML es la base estándar de intercambio de datos en la web estructurada. Su comprensión sólida es fundamental antes de pasar a su validación (DTD o XSD).

---

## 📑 Leyenda (Índice de Contenido)
1. [¿Qué es XML?](#1-que-es-xml)
2. [Usos Principales de XML](#2-usos-principales-de-xml)
3. [Estructura de un documento XML](#3-estructura-de-un-documento-xml)
   - [3.1. Estructura Física (Bloques del documento)](#31-estructura-fisica-bloques-del-documento)
   - [3.2. Estructura Lógica (Árbol Dirigido)](#32-estructura-logica-arbol-dirigido)
4. [Componentes de un documento XML](#4-componentes-de-un-documento-xml)
   - [4.1. Etiquetas (Tags)](#41-etiquetas-tags)
   - [4.2. Elementos](#42-elementos)
   - [4.3. Atributos (Reglas vitales)](#43-atributos)
   - [4.4. Instrucciones de Procesamiento](#44-instrucciones-de-procesamiento)
   - [4.5. Entidades y Referencias a entidades](#45-entidades-y-referencias-a-entidades)
   - [4.6. Secciones CDATA](#46-secciones-cdata)
5. [Ejemplo Completo y Nomenclatura del Árbol XML](#5-ejemplo-completo-y-nomenclatura-del-arbol-xml)

---

<a id="1-que-es-xml"></a>
## 1. ¿Qué es XML?

**XML** (eXtensible Markup Language) es un subconjunto de **SGML**, simplificado y adaptado a Internet. 
*   **Legibilidad:** Es legible y comprensible para cualquier usuario aunque no tenga conocimientos de lenguajes de marcas, ya que las etiquetas aportan el significado al contenido que encierran ("Self-Describing").
*   **Metalenguaje:** No es un lenguaje de marcas estricto (como HTML con sus etiquetas fijas `<h1>`, `<body>`), sino un **metalenguaje**. Te permite inventar tus propias etiquetas para cubrir las necesidades de tu sistema.
*   **Origen:** Creado por el **W3C** (World Wide Web Consortium) a finales de los años 90 (1998).
*   **Finalidad:** Permite representar **información estructurada** en la web de modo que pueda ser almacenada, transmitida, procesada y visualizada por diversos tipos de aplicaciones y dispositivos, sin importar el lenguaje de programación (Python, Java, C#, PHP...) en el que estén escritos.

---

<a id="2-usos-principales-de-xml"></a>
## 2. Usos Principales de XML

El uso fundamental de XML se basa en el **almacenamiento y distribución neutral de información**:

1.  **Intercambio de información entre aplicaciones:** Rompe la barrera entre plataformas. Un servidor Linux con base de datos Oracle puede enviar datos limpios a un móvil iOS mediante un simple archivo de texto plano `.xml`.
2.  **Computación distribuida (Web Services clásicos, SOAP):** Intercambiar información entre distintas computadoras. Los documentos XML son **inocuos** y seguros, no pueden contener código maligno ejecutable.
3.  **Información empresarial y de configuración:** Utilizado masivamente para generar documentos de facturación (Facturae), archivos de configuración (tipo `pom.xml` de Maven, o el antiguo `AndroidManifest.xml` de Android) gracias a su rigidez jerárquica.

---

<a id="3-estructura-de-un-documento-xml"></a>
## 3. Estructura de un documento XML

Un documento XML consiste en un fichero de texto plano (extensión **`.xml`**) que contiene datos delimitados por etiquetas.

<a id="31-estructura-fisica-bloques-del-documento"></a>
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

<a id="32-estructura-logica-arbol-dirigido"></a>
### 3.2. Estructura Lógica (Árbol Dirigido)
Lógicamente, un XML tiene diseño de **árbol genealógico dirigido**. Todos los nodos, excepto uno, tienen un único padre.
*   **Nodo raíz:** El envoltorio total. Agrupa a todos los demás y **no tiene padre**. En el ejemplo anterior, sería `<persona>`. Solo puede haber 1 por documento.
*   **Nodos hermanos:** Aquellos que cuelgan del mismo padre en el mismo nivel (ej: `<nombre>` y `<apellidos>` son hermanos).
*   **Nodos descendientes/ascendientes:** Relación de hijos, nietos, padres o abuelos dependiendo de si se baja o se sube en la estructura.

---

<a id="4-componentes-de-un-documento-xml"></a>
## 4. Componentes de un documento XML

<a id="41-etiquetas-tags"></a>
### 4.1. Etiquetas (Tags)
Marcas que identifican un contenido encerrándolo entre `< >`. Son *Case-Sensitive* (distinguen mayúsculas y minúsculas).
1.  **Apertura (start-tag):** `<apartado>`
2.  **Cierre (end-tag):** Llevan barra diagonal inicial. `</apartado>`. Prohibido meter atributos aquí.
3.  **Vacías (empty-tag):** Llevan barra diagonal final. No encierran nada, solo dan estrucutra o portan atributos. `<salto-de-linea />`.

<a id="42-elementos"></a>
### 4.2. Elementos
Son el bloque estructural completo (Apertura + Contenido + Cierre).
```xml
<autor>Maria Jimenez</autor>
```

<a id="43-atributos"></a>
### 4.3. Atributos
Propiedades anexadas a las etiquetas de apertura. Forman la estructura **`nombre="valor"`**. Su valor DEBE ir obligatoriamente entre comillas siempre (simples o dobles, pero siempre comillas, aunque sea un número entero).
*   **Regla vital 1:** ¡NUNCA en etiquetas de cierre! Solo en apertura o vacías.
*   **Regla vital 2:** No puedes repetir el mismo atributo dos veces en la misma etiqueta.

> **EJEMPLO DE ATRIBUTOS CORRECTOS:**
> ```xml
> <!-- Correcto: -->
> <libro moneda="EUR" precio="25">El Quijote</libro>
> 
> <!-- INCORRECTO (precio no lleva comillas y moneda se repite): -->
> <libro moneda="EUR" moneda="USD" precio=25>El Quijote</libro>
> ```

<a id="44-instrucciones-de-procesamiento"></a>
### 4.4. Instrucciones de Procesamiento
Son directivas para el motor/navegador que va a leer el XML indicándole cómo procesarlo. Se delimitan por **`<? ... ?>`**.
```xml
<!-- Decirle al navegador (Edge/Chrome) que cargue el fichero de diseño CSS para mostrarlo bonito -->
<?xml-stylesheet type="text/css" href="estilo.css"?>
```

<a id="45-entidades-y-referencias-a-entidades"></a>
### 4.5. Entidades y Referencias a entidades
*   **Entidades (Definición):** Constantes globales para no repetir textos largos. Se declaran arriba en el DOCTYPE (`<!ENTITY ...>`).
*   **Referencias (Uso):** Invocar esa constante. Empiezan por **`&`** y terminan con **`;`**. 
Existen 5 entidades predefinidas para escapar caracteres conflictivos en XML:
    *   `&lt;` para poner un `<` (Less than)
    *   `&gt;` para poner un `>` (Greater than)
    *   `&amp;` para poner un `&` (Ampersand)

> **EJEMPLO DE ENTIDADES CONSTANTES:**
> ```xml
> <!-- 1. Declaramos la constante en la cabecera del DOCTYPE -->
> <!ENTITY copy "Copyright W3C 2024">
> <!-- 2. La usamos donde queramos y el procesador la sustituirá -->
> <footer>&copy; Todos los derechos reservados</footer>
> ```

<a id="46-secciones-cdata"></a>
### 4.6. Secciones CDATA (Character Data)
Cápsulas de seguridad blindadas. Cualquier texto escrito dentro de una sección `<![CDATA[ ... ]]>` será ignorado por el procesador XML. Permite meter trozos de código C++, HTML o JavaScript sin que el XML confunda los signos `<` y `>` de esos códigos con sus propias etiquetas XML.

> **EJEMPLO CDATA (Enseñando código HTML por XML):**
> ```xml
> <tutorial>
>     Las negritas en HTML se ponen así:
>     <![CDATA[ <strong>Texto destacado</strong> ]]>
> </tutorial>
> ```

---

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

**Clasificación de los Nodos según su representación en Árbol DOM:**
1.  **Nodo Raíz (Document Root):** Origen invisible, englobaría al `<?xml...` y todo lo demás (`/`).
2.  **Nodo Elemento:** Cualquier etiqueta estructural del árbol. Ejemplos: `<biblioteca>` (Root Element), `<libro>`, `<titulo>`, `<autor>`.
3.  **Nodo Atributo:** Propiedades anidadas en los elementos. Siempre hijos del Nodo Elemento. Ejemplos: `año="1973"`, `fechaNacimiento="28/03/1936"`.
4.  **Nodo de Texto:** La información pura alojada dentro de los elementos (hojas finales sin hijos). Ejemplos: `"Milan Kundera"`.
