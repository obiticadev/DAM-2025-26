<a id="indice"></a>
# Guía de Estudio — XPath, XSLT y XQuery
### Temas 6 y 7 · Lenguaje de Marcas

> **Cómo usar este documento:**
> Está pensado para leerlo de arriba a abajo la primera vez, como si fuera una historia.
> Cada bloque explica UNA sola idea, con ejemplos reales y un ejercicio para consolidarla.
> Si XML y tú no sois muy amigos, empezad por el principio — cada bloque construye sobre el anterior.
> El índice te sirve como mapa: de un vistazo sabes exactamente dónde estás y qué queda.

---

## Índice — el mapa completo

| # | Bloque | Parte |
|---|--------|-------|
| [Bloque 0](#bloque-0) | ¿Qué es esto y por qué existe? — La idea de fondo | Contexto |
| **PARTE I — XPath** | *El lenguaje para apuntar a cosas dentro de un XML* | |
| [Bloque 1](#bloque-1) | El árbol XML — cómo ve XPath un documento | XPath |
| [Bloque 2](#bloque-2) | Rutas de navegación — cómo llegar a un nodo | XPath |
| [Bloque 3](#bloque-3) | Ejes — las 10 direcciones que puedes tomar | XPath |
| [Bloque 4](#bloque-4) | Predicados — filtrar con condiciones `[ ]` | XPath |
| [Bloque 5](#bloque-5) | Funciones XPath — contar, sumar, buscar texto | XPath |
| **PARTE II — XSLT** | *El lenguaje para transformar un XML en otra cosa* | |
| [Bloque 6](#bloque-6) | ¿Qué es XSLT? — Plantillas y el procesador | XSLT |
| [Bloque 7](#bloque-7) | Estructura básica de una hoja XSLT | XSLT |
| [Bloque 8](#bloque-8) | Iterar y ordenar — `xsl:for-each` y `xsl:sort` | XSLT |
| [Bloque 9](#bloque-9) | Condicionales — `xsl:if` y `xsl:choose` | XSLT |
| [Bloque 10](#bloque-10) | Variables y parámetros — guardar valores | XSLT |
| [Bloque 11](#bloque-11) | Plantillas con `mode` y plantillas nombradas | XSLT |
| [Bloque 12](#bloque-12) | Formatos de salida — HTML, XML, texto, CSV | XSLT |
| [Bloque 13](#bloque-13) | Elementos y atributos dinámicos | XSLT |
| **PARTE III — DOM y SAX** | *Dos formas de procesar XML desde código* | |
| [Bloque 14](#bloque-14) | DOM vs SAX — cuándo usar cada uno | DOM/SAX |
| **PARTE IV — XQuery** | *El SQL de los documentos XML* | |
| [Bloque 15](#bloque-15) | Dónde vive el XML — sistemas de almacenamiento | XQuery |
| [Bloque 16](#bloque-16) | Introducción a XQuery y su relación con XPath | XQuery |
| [Bloque 17](#bloque-17) | FLWOR — el corazón de XQuery | XQuery |
| [Bloque 18](#bloque-18) | Funciones de búsqueda — cadenas y agregación | XQuery |
| [Bloque 19](#bloque-19) | Joins — cruzar dos documentos XML | XQuery |
| [Bloque 20](#bloque-20) | XQUF — insertar, borrar y actualizar nodos | XQuery |
| [Bloque 21](#bloque-21) | XQuery completo — prólogo, funciones y módulos | XQuery |
| [Apéndice](#apendice) | Errores típicos y trampas de examen | |

---

<a id="bloque-0"></a>
## Bloque 0 — ¿Qué es esto y por qué existe?

[↑ Volver al índice](#indice)

Imagina que tienes un documento XML con datos de un catálogo de libros. El XML es perfecto para guardar datos estructurados, pero solo es texto. Necesitas tres cosas:

1. **Apuntar** a partes concretas del XML → para eso existe **XPath**.
2. **Transformar** el XML en HTML (o en otro formato) → para eso existe **XSLT**.
3. **Consultar y modificar** XML almacenado en una base de datos → para eso existe **XQuery**.

La relación entre los tres:

```
XML de datos
    │
    ├─► XPath ──────────────► "dame el título del segundo libro"
    │
    ├─► XSLT (usa XPath) ──► convierte el XML en una página HTML
    │
    └─► XQuery (usa XPath) ► consulta una BD de documentos XML
```

> **La clave mental:** XPath es el bisturí. XSLT y XQuery son los cirujanos que lo usan.
> Si entiendes XPath, el resto encaja solo.

---

<a id="bloque-1"></a>
## Bloque 1 — El árbol XML: cómo ve XPath un documento

[↑ Volver al índice](#indice)

### Teoría

XPath no ve el XML como texto. Lo ve como un **árbol de nodos**, igual que el árbol de carpetas de tu ordenador: hay una raíz, de la que cuelgan ramas, de las que cuelgan más ramas.

Dado este XML de referencia (lo usaremos en casi todos los bloques):

```xml
<catalogo>
  <libro id="001" disponible="true">
    <titulo>El Quijote</titulo>
    <autor>Miguel de Cervantes</autor>
    <precio moneda="EUR">17.50</precio>
    <anio>1605</anio>
  </libro>
  <libro id="002" disponible="false">
    <titulo>Cien años de soledad</titulo>
    <autor>Gabriel García Márquez</autor>
    <precio moneda="EUR">22.00</precio>
    <anio>1967</anio>
  </libro>
  <libro id="003" disponible="true">
    <titulo>La sombra del viento</titulo>
    <autor>Carlos Ruiz Zafón</autor>
    <precio moneda="EUR">19.95</precio>
    <anio>2001</anio>
  </libro>
</catalogo>
```

XPath ve esto como:

```
/ (nodo raíz del documento — el padre de todo)
└── catalogo  (elemento raíz del XML)
    ├── libro  [id="001", disponible="true"]
    │   ├── titulo        → texto: "El Quijote"
    │   ├── autor         → texto: "Miguel de Cervantes"
    │   ├── precio [moneda="EUR"] → texto: "17.50"
    │   └── anio          → texto: "1605"
    ├── libro  [id="002", disponible="false"]
    │   └── ...
    └── libro  [id="003", disponible="true"]
        └── ...
```

**Tipos de nodos que existen:**

| Tipo | Qué es | Ejemplo |
|------|--------|---------|
| Nodo raíz (`/`) | El documento completo | Es único, no tiene nombre |
| Elemento | Una etiqueta XML | `<libro>`, `<titulo>` |
| Atributo | Un atributo de un elemento | `id="001"`, `moneda="EUR"` |
| Texto | El contenido entre etiquetas | `El Quijote`, `17.50` |
| Comentario | `<!-- ... -->` | Para XPath también son nodos |

> **Lo importante:** los **atributos son hijos de su elemento** pero NO son "elementos". Son un tipo distinto de nodo. Esto es clave para entender por qué se accede a ellos con `@`.

---

<a id="bloque-2"></a>
## Bloque 2 — Rutas de navegación: cómo llegar a un nodo

[↑ Volver al índice](#indice)

### Teoría

Una expresión XPath es como una dirección: `catalogo/libro/titulo` significa "dentro de catalogo, dentro de libro, dame titulo". El `/` es el separador de pasos, igual que en las rutas de carpetas.

**Dos tipos de rutas:**

| Tipo | Cómo empieza | Qué significa | Ejemplo |
|------|-------------|---------------|---------|
| **Absoluta** | `/` | Desde la raíz del documento | `/catalogo/libro/titulo` |
| **Relativa** | Nombre directo | Desde el nodo donde estés ahora | `libro/titulo` |

**Abreviaciones que debes memorizar:**

| Abreviatura | Significado completo | En cristiano |
|-------------|---------------------|--------------|
| `/` | Separador de pasos | Baja un nivel |
| `//` | `descendant-or-self::node()/` | En cualquier nivel del árbol |
| `.` | `self::node()` | El nodo actual |
| `..` | `parent::node()` | El padre del nodo actual |
| `@nombre` | `attribute::nombre` | El atributo "nombre" |
| `*` | Cualquier elemento hijo | Todos los hijos |
| `@*` | Cualquier atributo | Todos los atributos |
| `text()` | Solo nodos de texto | El contenido textual |
| `node()` | Cualquier tipo de nodo | Cualquier cosa |

**Ejemplos con el catálogo:**

| Expresión XPath | Qué devuelve |
|-----------------|--------------|
| `/catalogo/libro` | Los 3 elementos `<libro>` |
| `//titulo` | Los 3 elementos `<titulo>` (estén donde estén) |
| `/catalogo/libro[1]/titulo` | `<titulo>El Quijote</titulo>` |
| `//libro/@id` | Los atributos: `001`, `002`, `003` |
| `//precio/@moneda` | Todos los atributos moneda: `EUR`, `EUR`, `EUR` |
| `/catalogo/libro/titulo/text()` | El texto: `El Quijote`, `Cien años...`, `La sombra...` |

> **Diferencia crucial entre `/` y `//`:**
> - `/catalogo/libro` → solo libros que son hijos DIRECTOS de catalogo.
> - `//libro` → cualquier `<libro>` en cualquier nivel del documento.
> En nuestro ejemplo ambas darían lo mismo, pero en documentos con más profundidad marcan una diferencia enorme.

### Ejercicio 2.1 — Expresiones XPath básicas

Usando el `catalogo.xml` de arriba, escribe la expresión para:

- **a)** Todos los elementos `<titulo>` del documento.
- **b)** El atributo `id` del segundo libro.
- **c)** Todos los libros que tengan `disponible="true"`.
- **d)** El título del libro con `id="003"`.
- **e)** El número total de libros.
- **f)** La suma de todos los precios.
- **g)** Los títulos de libros con precio mayor que 18.

### Solución 2.1

```xpath
a) //titulo
   → Devuelve: <titulo>El Quijote</titulo>, <titulo>Cien años de soledad</titulo>, <titulo>La sombra del viento</titulo>

b) /catalogo/libro[2]/@id
   → Devuelve: 002

c) //libro[@disponible='true']
   → Devuelve: libro id="001" y libro id="003"

d) //libro[@id='003']/titulo
   → Devuelve: <titulo>La sombra del viento</titulo>

e) count(//libro)
   → Devuelve: 3

f) sum(//precio)
   → Devuelve: 59.45

g) //libro[precio > 18]/titulo
   → Devuelve: <titulo>Cien años de soledad</titulo>, <titulo>La sombra del viento</titulo>
```

> **Por qué en g) no ponemos `//precio > 18`:** XPath trabaja sobre nodos, no sobre valores directamente. `libro[precio > 18]` significa "el elemento libro tal que su hijo precio tiene valor numérico mayor que 18". XPath convierte automáticamente el texto del nodo a número al comparar con `>`.

---

<a id="bloque-3"></a>
## Bloque 3 — Ejes: las 10 direcciones que puedes tomar

[↑ Volver al índice](#indice)

### Teoría

Cuando estás en un nodo, puedes moverte en distintas **direcciones** dentro del árbol. Esas direcciones se llaman **ejes**. La forma completa de un paso XPath es:

```
eje::test-de-nodo[predicado]
```

Pero normalmente las abreviaciones hacen que no escribas el eje explícitamente (ej: `//titulo` en lugar de `descendant-or-self::node()/child::titulo`).

**Los ejes más importantes:**

| Eje | Dirección | Abreviatura | Ejemplo completo | Abreviado |
|-----|-----------|-------------|-----------------|-----------|
| `child` | Hijos directos | (defecto) | `child::libro` | `libro` |
| `attribute` | Atributos del nodo | `@` | `attribute::id` | `@id` |
| `parent` | Nodo padre | `..` | `parent::catalogo` | `..` |
| `self` | El propio nodo | `.` | `self::libro` | `.` |
| `descendant` | Todos los descendientes | `//` (parcial) | `descendant::titulo` | |
| `descendant-or-self` | Descendientes + yo mismo | `//` | `descendant-or-self::node()` | `//` |
| `ancestor` | Todos los antecesores | — | `ancestor::catalogo` | |
| `following-sibling` | Hermanos que vienen después | — | `following-sibling::libro` | |
| `preceding-sibling` | Hermanos que van antes | — | `preceding-sibling::libro` | |
| `following` | Todo lo que viene después en el doc | — | `following::nota` | |

**Diferencia clave que siempre confunde:**

```
Situado en <libro id="001">:

child::titulo           → solo <titulo> hijo directo de este libro
descendant::titulo      → <titulo> en cualquier nivel dentro de este libro
following-sibling::libro → los otros <libro> que vienen DESPUÉS de éste en el catálogo
```

**Ejemplo con tienda.xml (ejercicio 02):**

```xml
<tienda>
  <seccion nombre="Informatica">
    <producto><nombre>Ratón</nombre></producto>
    <producto><nombre>Teclado</nombre></producto>
  </seccion>
  <seccion nombre="Papeleria">
    <producto><nombre>Bolígrafo</nombre></producto>
  </seccion>
</tienda>
```

Contexto: estamos en la primera `<seccion>`:

| Expresión | Resultado |
|-----------|-----------|
| `child::producto` | `<producto>Ratón</producto>` y `<producto>Teclado</producto>` |
| `descendant::nombre` | `Ratón` y `Teclado` (todo lo que está dentro) |
| `following-sibling::seccion` | La sección `Papeleria` (hermana que viene después) |
| `/tienda/seccion[1]/producto[last()]/nombre` | `Teclado` (último producto de la primera sección) |

> **Truco para el examen:** si el enunciado dice "dentro de", usa `descendant`. Si dice "al mismo nivel pero después", usa `following-sibling`. Si dice "el padre", usa `parent` o `..`.

---

<a id="bloque-4"></a>
## Bloque 4 — Predicados: filtrar con condiciones `[ ]`

[↑ Volver al índice](#indice)

### Teoría

Los predicados son condiciones que van entre corchetes y **filtran** los nodos que selecciona la ruta. Solo pasan los nodos para los que la condición sea verdadera.

```xpath
libro[condición]   → de todos los libros, dame solo los que cumplan la condición
```

**Tipos de predicados:**

```xpath
libro[1]                          → Por posición: el primero
libro[last()]                     → El último
libro[position() = 2]             → El segundo (igual que [2])
libro[position() mod 2 = 0]       → Los de posición par (2, 4, 6...)

libro[@id]                        → Que tengan el atributo id (existe)
libro[@id='001']                  → Cuyo id valga exactamente '001'
libro[@disponible='true']         → Con disponible=true

libro[precio > 18]                → Cuyo precio sea mayor que 18
libro[precio = 22.00]             → Cuyo precio sea exactamente 22

libro[autor='Gabriel García Márquez']   → Del autor García Márquez
```

**Predicados compuestos (combinados con `and` / `or`):**

```xpath
libro[@disponible='true' and precio < 20]
→ Libros disponibles con precio menor de 20€

libro[precio > 15 or @id='001']
→ Libros con precio > 15 O cuyo id es '001'

libro[not(@disponible='false')]
→ Libros que NO tienen disponible=false
```

**Predicados de función:**

```xpath
libro[contains(autor, 'García')]
→ Libros cuyo autor contiene 'García'

libro[starts-with(@id, '00')]
→ Libros cuyo id empieza por '00'

libro[string-length(titulo) > 10]
→ Libros cuyo título tiene más de 10 caracteres
```

### Ejercicio 4.1 — Predicados sobre el catálogo

Escribe la expresión XPath para:

- **a)** El segundo libro disponible (disponible="true").
- **b)** Los libros con precio entre 15€ y 25€.
- **c)** El autor del último libro.
- **d)** Los libros que NO están disponibles.
- **e)** Los títulos de libros publicados después del año 1900.

### Solución 4.1

```xpath
a) //libro[@disponible='true'][2]
   → La sombra del viento (primer true=001, segundo true=003)

b) //libro[precio >= 15 and precio <= 25]
   → Los tres libros (17.50, 22.00, 19.95, todos entre 15 y 25)

c) /catalogo/libro[last()]/autor
   → Carlos Ruiz Zafón

d) //libro[@disponible='false']
   → El libro id="002" (Cien años de soledad)

e) //libro[anio > 1900]/titulo
   → Cien años de soledad (1967) y La sombra del viento (2001)
```

---

<a id="bloque-5"></a>
## Bloque 5 — Funciones XPath: contar, sumar, buscar texto

[↑ Volver al índice](#indice)

### Teoría

XPath incluye funciones de biblioteca para trabajar con texto, números y conjuntos de nodos.

**Funciones numéricas:**

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `count(nodos)` | Cuenta cuántos nodos hay | `count(//libro)` → 3 |
| `sum(nodos)` | Suma los valores | `sum(//precio)` → 59.45 |
| `round(n)` | Redondea al entero más cercano | `round(17.50)` → 18 |
| `floor(n)` | Redondea hacia abajo | `floor(17.90)` → 17 |
| `ceiling(n)` | Redondea hacia arriba | `ceiling(17.10)` → 18 |

**Funciones de cadena:**

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `string(nodo)` | Convierte a texto | `string(//precio[1])` → "17.50" |
| `contains(s, sub)` | ¿s contiene sub? | `contains(autor, 'García')` → true |
| `starts-with(s, pre)` | ¿s empieza por pre? | `starts-with(@id, '00')` → true |
| `substring(s, ini, len)` | Parte de la cadena | `substring('El Quijote', 1, 2)` → "El" |
| `string-length(s)` | Longitud | `string-length('El Quijote')` → 10 |
| `normalize-space(s)` | Elimina espacios extra | `normalize-space('  Ana  ')` → "Ana" |
| `concat(s1, s2, ...)` | Une cadenas | `concat(nombre, '-', puesto)` |
| `translate(s, de, a)` | Sustituye caracteres | `translate('abc', 'abc', 'ABC')` → "ABC" |

**Funciones booleanas:**

| Función | Qué hace |
|---------|----------|
| `not(expr)` | Niega una expresión |
| `true()` / `false()` | Constantes |

**Funciones de posición (dentro de conjuntos):**

| Función | Qué hace |
|---------|----------|
| `position()` | Posición del nodo actual en su contexto |
| `last()` | Cuántos nodos hay en el contexto |

**Ejemplos con `empleados.xml`:**

```xml
<empresa>
  <empleado>
    <nombre>  Ana López  </nombre>
    <email>ana@empresa.com</email>
    <puesto>Desarrolladora Senior</puesto>
  </empleado>
  <empleado>
    <nombre>Carlos Ruiz</nombre>
    <email>carlos@empresa.com</email>
    <puesto>Analista</puesto>
  </empleado>
</empresa>
```

### Ejercicio 5.1 — Funciones de cadena

Escribe las expresiones XPath para:

- **a)** Empleados cuyo email contenga "empresa.com".
- **b)** Empleados cuyo puesto empiece por "Desar".
- **c)** Longitud del nombre del primer empleado (tal cual aparece en el XML).
- **d)** Nombre del primer empleado eliminando espacios extra.
- **e)** Nombre y puesto del segundo empleado concatenados con " - ".

### Solución 5.1

```xpath
a) //empleado[contains(email, 'empresa.com')]
   → Devuelve ambos empleados (los dos tienen @empresa.com)

b) //empleado[starts-with(puesto, 'Desar')]
   → Devuelve el empleado con puesto "Desarrolladora Senior"
   (Nota: "Desar" coincide con el inicio de "Desarrolladora")

c) string-length(//empleado[1]/nombre)
   → Devuelve 12 (incluye los espacios "  Ana López  " = 2+3+1+5+2 = 13)
   Nota: el XML tiene "  Ana López  " con espacios, así que la longitud
   es la del string completo con espacios.

d) normalize-space(//empleado[1]/nombre)
   → Devuelve "Ana López" (sin los espacios del principio y del final)

e) concat(//empleado[2]/nombre, ' - ', //empleado[2]/puesto)
   → Devuelve "Carlos Ruiz - Analista"
```

---

<a id="bloque-6"></a>
## Bloque 6 — ¿Qué es XSLT? Plantillas y el procesador

[↑ Volver al índice](#indice)

### Teoría

XSLT (eXtensible Stylesheet Language Transformations) es un lenguaje para **transformar** documentos XML en otros formatos. Funciona así:

```
[XML de datos] + [Hoja XSLT] → [Procesador XSLT] → [Resultado: HTML, XML, texto...]
```

El procesador XSLT más común es **Saxon** (Java) o **libxslt** (Linux/macOS).

**La idea clave: las plantillas (templates)**

Una hoja XSLT es una lista de **plantillas**. Cada plantilla dice: "cuando el procesador encuentre este tipo de nodo, genera esto". El procesador recorre el XML y aplica la plantilla que corresponde a cada nodo.

```xml
<!-- Plantilla para el elemento <libro> -->
<xsl:template match="libro">
  <!-- aquí defines qué generar cuando encuentres un libro -->
  <div>
    <h2><xsl:value-of select="titulo"/></h2>
  </div>
</xsl:template>
```

**Dos tipos de plantillas:**

| Tipo | Atributo | Cómo se activa |
|------|----------|----------------|
| Con patrón | `match="expresión"` | Se activa automáticamente cuando el procesador llega a ese nodo |
| Nombrada | `name="nombre"` | Se llama explícitamente con `<xsl:call-template name="nombre"/>` |

---

<a id="bloque-7"></a>
## Bloque 7 — Estructura básica de una hoja XSLT

[↑ Volver al índice](#indice)

### Teoría

Toda hoja XSLT tiene esta anatomía obligatoria:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <!-- 1. Declarar el formato de salida -->
  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- 2. Plantilla raíz: punto de entrada del procesamiento -->
  <xsl:template match="/">
    <!-- Aquí empieza a generarse la salida -->
    <html>
      <body>
        <!-- Aplicar plantillas a los hijos del nodo raíz -->
        <xsl:apply-templates/>
      </body>
    </html>
  </xsl:template>

  <!-- 3. Plantillas para otros nodos -->
  <xsl:template match="libro">
    <p><xsl:value-of select="titulo"/></p>
  </xsl:template>

</xsl:stylesheet>
```

**Las instrucciones básicas que siempre usarás:**

| Instrucción | Qué hace | Ejemplo |
|-------------|----------|---------|
| `xsl:value-of select="ruta"` | Extrae el texto de un nodo | `<xsl:value-of select="titulo"/>` |
| `xsl:apply-templates` | Activa el procesamiento de los hijos | Motor del paradigma XSLT |
| `xsl:apply-templates select="ruta"` | Activa plantillas solo para los nodos de esa ruta | |
| `xsl:call-template name="nombre"` | Llama a una plantilla nombrada | |
| `xsl:for-each select="ruta"` | Itera sobre los nodos de esa ruta | |
| `xsl:sort select="campo"` | Ordena dentro de `for-each` o `apply-templates` | |

**`xsl:output` — opciones:**

| Atributo | Valores | Cuándo usarlo |
|----------|---------|---------------|
| `method` | `html`, `xml`, `text` | Siempre, define el formato |
| `encoding` | `UTF-8`, `ISO-8859-1`... | Siempre, define la codificación |
| `indent` | `yes`, `no` | Para que el resultado quede con sangría |
| `omit-xml-declaration` | `yes`, `no` | Para eliminar `<?xml ...?>` del resultado |

### Ejercicio 7.1 — Primera transformación XML → HTML

Transforma el `empleados.xml` en una tabla HTML:

```xml
<empresa>
  <empleado>
    <nombre>Pedro Vega</nombre>
    <departamento>Ventas</departamento>
    <salario>2800</salario>
  </empleado>
  <empleado>
    <nombre>Ana Torres</nombre>
    <departamento>IT</departamento>
    <salario>3200</salario>
  </empleado>
</empresa>
```

Requisitos: tabla con columnas Nombre, Departamento y Salario. Empleados ordenados alfabéticamente por nombre.

### Solución 7.1

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head><title>Empleados</title></head>
      <body>
        <table border="1">
          <tr>
            <th>Nombre</th>
            <th>Departamento</th>
            <th>Salario</th>
          </tr>
          <xsl:for-each select="empresa/empleado">
            <xsl:sort select="nombre"/>
            <tr>
              <td><xsl:value-of select="nombre"/></td>
              <td><xsl:value-of select="departamento"/></td>
              <td><xsl:value-of select="salario"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
```

> **Razonamiento:**
> 1. `match="/"` → Plantilla raíz: empieza aquí.
> 2. `xsl:for-each select="empresa/empleado"` → Itera sobre cada empleado.
> 3. `xsl:sort select="nombre"` → Antes de iterar, ordena por nombre (alfabéticamente, ascendente por defecto).
> 4. Dentro del bucle, `xsl:value-of select="nombre"` extrae el texto del elemento nombre del empleado ACTUAL.

---

<a id="bloque-8"></a>
## Bloque 8 — Iterar y ordenar: `xsl:for-each` y `xsl:sort`

[↑ Volver al índice](#indice)

### Teoría

`xsl:for-each` recorre un conjunto de nodos y ejecuta su contenido para cada uno. Dentro puedes añadir `xsl:sort` para ordenarlos.

```xml
<xsl:for-each select="catalogo/libro">
  <xsl:sort select="precio" data-type="number" order="descending"/>
  <!-- Esto se ejecuta para cada <libro>, ordenados por precio descendente -->
  <p>
    <xsl:value-of select="titulo"/>:
    <xsl:value-of select="precio"/> €
  </p>
</xsl:for-each>
```

**Atributos de `xsl:sort`:**

| Atributo | Valores | Descripción |
|----------|---------|-------------|
| `select` | expresión XPath | Por qué campo ordenar |
| `order` | `ascending` (defecto) / `descending` | Dirección |
| `data-type` | `text` (defecto) / `number` | Ordena como texto o como número |
| `case-order` | `upper-first` / `lower-first` | Cuándo importa mayúsculas |

> **Trampa frecuente:** si ordenas precios con `data-type="text"`, `19.95` va antes que `9.50` porque alfabéticamente `1` < `9`. Siempre usa `data-type="number"` para valores numéricos.

**Ordenar por varios campos:**

```xml
<xsl:for-each select="empresa/empleado">
  <xsl:sort select="departamento"/>
  <xsl:sort select="nombre"/>
  <!-- Primero ordena por departamento, luego por nombre dentro del mismo departamento -->
</xsl:for-each>
```

**`xsl:apply-templates` vs `xsl:for-each`:**

Ambos iteran, pero con filosofías distintas:

| | `xsl:for-each` | `xsl:apply-templates` |
|-|----------------|----------------------|
| Estilo | Imperativo (tú controlas el bucle) | Declarativo (el procesador busca la plantilla) |
| Cuándo usarlo | Cuando toda la lógica está en un sitio | Cuando hay plantillas separadas por tipo de nodo |
| Flexibilidad | Menor | Mayor (cada nodo puede tener su propia plantilla) |

---

<a id="bloque-9"></a>
## Bloque 9 — Condicionales: `xsl:if` y `xsl:choose`

[↑ Volver al índice](#indice)

### Teoría

**`xsl:if` — condicional simple:**

```xml
<xsl:if test="salario > 3000">
  <strong>(Senior)</strong>
</xsl:if>
```

Si la condición es verdadera, se genera el contenido. No hay "else". Si necesitas else, usa `xsl:choose`.

**`xsl:choose` — condicional múltiple (el switch de XSLT):**

```xml
<xsl:choose>
  <xsl:when test="salario > 3000">
    <span class="senior">Senior</span>
  </xsl:when>
  <xsl:when test="salario > 2000">
    <span class="medio">Medio</span>
  </xsl:when>
  <xsl:otherwise>
    <span class="junior">Junior</span>
  </xsl:otherwise>
</xsl:choose>
```

> - `xsl:when` = "si". Puede haber tantos como necesites.
> - `xsl:otherwise` = "en cualquier otro caso". Es opcional pero recomendable.
> - El procesador evalúa los `when` en orden y ejecuta el primero que sea verdadero.

**Condiciones habituales en exámenes:**

```xml
<!-- ¿Es el nodo actual el último? -->
<xsl:if test="position() = last()"> ... </xsl:if>

<!-- ¿El texto está vacío? -->
<xsl:if test="string-length(departamento) = 0"> Sin asignar </xsl:if>

<!-- ¿Existe el elemento hijo? -->
<xsl:if test="departamento"> ... </xsl:if>

<!-- ¿NO existe? -->
<xsl:if test="not(departamento)"> ... </xsl:if>

<!-- ¿El atributo tiene un valor concreto? -->
<xsl:if test="@disponible = 'true'"> ... </xsl:if>
```

### Ejercicio 9.1 — Tabla con formato condicional

Partiendo de la tabla del Ejercicio 7.1, añade:
- Si el salario es mayor de 3000€, mostrarlo en **negrita** con el texto "(Senior)".
- Si el departamento está vacío, mostrar "Sin asignar".

### Solución 9.1

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <html>
      <body>
        <table border="1">
          <tr><th>Nombre</th><th>Departamento</th><th>Salario</th></tr>
          <xsl:for-each select="empresa/empleado">
            <xsl:sort select="nombre"/>
            <tr>
              <td><xsl:value-of select="nombre"/></td>

              <!-- Departamento: condicional con xsl:choose -->
              <td>
                <xsl:choose>
                  <xsl:when test="string-length(departamento) = 0">
                    Sin asignar
                  </xsl:when>
                  <xsl:otherwise>
                    <xsl:value-of select="departamento"/>
                  </xsl:otherwise>
                </xsl:choose>
              </td>

              <!-- Salario: condicional con xsl:if -->
              <td>
                <xsl:if test="salario > 3000">
                  <strong>
                    <xsl:value-of select="salario"/> (Senior)
                  </strong>
                </xsl:if>
                <xsl:if test="not(salario > 3000)">
                  <xsl:value-of select="salario"/>
                </xsl:if>
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
```

> **Alternativa más elegante para el salario** usando `xsl:choose`:
> ```xml
> <td>
>   <xsl:choose>
>     <xsl:when test="salario > 3000">
>       <strong><xsl:value-of select="salario"/> (Senior)</strong>
>     </xsl:when>
>     <xsl:otherwise>
>       <xsl:value-of select="salario"/>
>     </xsl:otherwise>
>   </xsl:choose>
> </td>
> ```

---

<a id="bloque-10"></a>
## Bloque 10 — Variables y parámetros: guardar valores

[↑ Volver al índice](#indice)

### Teoría

**Variables (`xsl:variable`):**

Una variable guarda un valor para usarlo más tarde. En XSLT las variables son **inmutables**: una vez asignadas, no se pueden cambiar.

```xml
<!-- Variable con valor calculado -->
<xsl:variable name="iva" select="0.21"/>

<!-- Variable con contenido XML -->
<xsl:variable name="total" select="sum(//precio)"/>

<!-- Uso de la variable: se referencia con $ -->
<p>IVA aplicado: <xsl:value-of select="$iva * 100"/>%</p>
<p>Total: <xsl:value-of select="$total"/> EUR</p>
<p>Total con IVA: <xsl:value-of select="$total * (1 + $iva)"/> EUR</p>
```

> **Error típico:** intentar usar una variable antes de declararla, o escribir `iva` en lugar de `$iva` al referenciarla. **Siempre con `$`**.

**Parámetros (`xsl:param`):**

Los parámetros son como variables pero con un valor por defecto que puede ser sobreescrito desde fuera (por ejemplo, desde la línea de comandos del procesador).

```xml
<!-- Declarar parámetro con valor por defecto -->
<xsl:param name="precioMaximo" select="30"/>

<!-- Usar el parámetro -->
<xsl:if test="precio &lt;= $precioMaximo">
  <!-- precio menor o igual que el máximo -->
</xsl:if>
```

> **Ojo con `<` y `>`:** en XML, el carácter `<` está reservado y hay que escribirlo como `&lt;` dentro de un atributo. Igualmente `>` como `&gt;`. En XPath dentro de XSLT:
> - `<` → `&lt;`
> - `>` → `&gt;` (aunque `>` a veces funciona solo)
> - `<=` → `&lt;=`
> - `>=` → `&gt;=`

**Pasar parámetros a plantillas:**

```xml
<xsl:call-template name="formatearPrecio">
  <xsl:with-param name="valor" select="precio"/>
  <xsl:with-param name="moneda" select="'EUR'"/>
</xsl:call-template>

<xsl:template name="formatearPrecio">
  <xsl:param name="valor"/>
  <xsl:param name="moneda" select="'EUR'"/>
  <span><xsl:value-of select="$valor"/> <xsl:value-of select="$moneda"/></span>
</xsl:template>
```

### Ejercicio 10.1 — Variables y cálculo

Escribe una hoja XSLT que, para el catálogo de libros, muestre:
- El total de libros (usando una variable).
- El precio de cada libro con IVA del 21% (usando una variable para el IVA).
- Solo los libros con precio final (con IVA) menor que un parámetro externo `precioLimite` (valor por defecto: 30).

### Solución 10.1

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <!-- Parámetro externo con valor por defecto -->
  <xsl:param name="precioLimite" select="30"/>

  <!-- Variable global -->
  <xsl:variable name="iva" select="0.21"/>
  <xsl:variable name="totalLibros" select="count(//libro)"/>

  <xsl:template match="/">
    <html>
      <body>
        <h1>Catálogo — <xsl:value-of select="$totalLibros"/> libros</h1>
        <ul>
          <xsl:for-each select="catalogo/libro">
            <!-- Variable local dentro del bucle -->
            <xsl:variable name="precioConIva"
              select="precio * (1 + $iva)"/>

            <!-- Solo mostramos si el precio con IVA es menor que el límite -->
            <xsl:if test="$precioConIva &lt; $precioLimite">
              <li>
                <xsl:value-of select="titulo"/> —
                <xsl:value-of select="format-number($precioConIva, '#.00')"/> EUR
              </li>
            </xsl:if>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>
```

---

<a id="bloque-11"></a>
## Bloque 11 — Plantillas con `mode` y plantillas nombradas

[↑ Volver al índice](#indice)

### Teoría

**El problema:** quieres mostrar un libro de dos formas distintas en la misma página: una vez como elemento de una lista (vista resumen) y otra vez como artículo completo (vista detalle). Pero solo puedes tener una plantilla `match="libro"`.

**La solución: `mode`**

El atributo `mode` permite tener varias plantillas para el mismo nodo, cada una activada en un contexto diferente.

```xml
<!-- Plantilla resumen: solo muestra el título en una lista -->
<xsl:template match="libro" mode="resumen">
  <li><xsl:value-of select="titulo"/></li>
</xsl:template>

<!-- Plantilla detalle: muestra toda la información -->
<xsl:template match="libro" mode="detalle">
  <article>
    <h2><xsl:value-of select="titulo"/></h2>
    <p>Autor: <xsl:value-of select="autor"/></p>
    <p>Precio: <xsl:value-of select="precio"/> EUR</p>
  </article>
</xsl:template>

<!-- En la plantilla raíz, usamos cada modo donde corresponde -->
<xsl:template match="/">
  <html><body>
    <h2>Lista de títulos:</h2>
    <ul>
      <xsl:apply-templates select="catalogo/libro" mode="resumen"/>
    </ul>

    <h2>Fichas completas:</h2>
    <xsl:apply-templates select="catalogo/libro" mode="detalle"/>
  </body></html>
</xsl:template>
```

**Plantillas nombradas — reutilizar bloques:**

```xml
<!-- Plantilla nombrada: siempre lleva name="", no match -->
<xsl:template name="cabecera">
  <header>
    <h1>Mi catálogo — <xsl:value-of select="count(//libro)"/> libros</h1>
    <nav>...</nav>
  </header>
</xsl:template>

<!-- Se llama con xsl:call-template -->
<xsl:template match="/">
  <html><body>
    <xsl:call-template name="cabecera"/>
    <!-- resto del contenido -->
  </body></html>
</xsl:template>
```

> **Diferencia clave:**
> - `match="libro"` → se activa automáticamente cuando el procesador llega a un `<libro>`.
> - `name="cabecera"` → solo se ejecuta cuando explícitamente la llamas con `call-template`.

---

<a id="bloque-12"></a>
## Bloque 12 — Formatos de salida: HTML, XML, texto, CSV

[↑ Volver al índice](#indice)

### Teoría

El atributo `method` de `xsl:output` controla el tipo de salida:

| `method` | Cuándo usarlo | Cómo se genera |
|----------|--------------|----------------|
| `html` | Para páginas web | El procesador optimiza para HTML (cierra tags vacíos, etc.) |
| `xml` | Para otro XML o XML de datos | Mantiene la declaración XML |
| `text` | Para texto plano, CSV, JSON | Solo se emite texto, sin etiquetas |

**Salida XML → otro XML (filtrado/renombrado):**

```xml
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/">
  <libros-disponibles>
    <xsl:for-each select="catalogo/libro[@disponible='true']">
      <item ref="{@id}">
        <xsl:value-of select="titulo"/>
      </item>
    </xsl:for-each>
  </libros-disponibles>
</xsl:template>
```

> La notación `{@id}` dentro de un valor de atributo es una **expresión de valor**: inserta el valor de `@id` en ese punto. Solo funciona en valores de atributos, no en contenido de elementos (ahí usas `xsl:value-of`).

**Salida texto plano — CSV:**

```xml
<xsl:output method="text" encoding="UTF-8"/>

<xsl:template match="/">
  <!-- Cabecera -->
  <xsl:text>nombre,departamento,salario&#10;</xsl:text>
  <!-- Filas -->
  <xsl:for-each select="empresa/empleado">
    <xsl:value-of select="nombre"/>
    <xsl:text>,</xsl:text>
    <xsl:value-of select="departamento"/>
    <xsl:text>,</xsl:text>
    <xsl:value-of select="salario"/>
    <xsl:text>&#10;</xsl:text>
  </xsl:for-each>
</xsl:template>
```

> - `&#10;` es el código XML para salto de línea.
> - `xsl:text` emite texto literal preservando los espacios exactamente. Sin él, XSLT puede añadir espacios o saltos de línea no deseados.
> - Con `method="text"`, cualquier XML que pongas (como `<p>`) se ignorará como etiqueta y solo se emitirá su texto. No pongas HTML en salidas de texto.

### Ejercicio 12.1 — Generar CSV desde XML

Transforma el `empleados.xml` en un CSV con cabecera `nombre,departamento,salario` y un salto de línea al final de cada fila.

### Solución 12.1

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="text" encoding="UTF-8"/>

  <xsl:template match="/">
    <xsl:text>nombre,departamento,salario&#10;</xsl:text>
    <xsl:for-each select="empresa/empleado">
      <xsl:value-of select="nombre"/>
      <xsl:text>,</xsl:text>
      <xsl:value-of select="departamento"/>
      <xsl:text>,</xsl:text>
      <xsl:value-of select="salario"/>
      <xsl:text>&#10;</xsl:text>
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>
```

> **Resultado:**
> ```
> nombre,departamento,salario
> Pedro Vega,Ventas,2800
> Ana Torres,IT,3200
> ```

---

<a id="bloque-13"></a>
## Bloque 13 — Elementos y atributos dinámicos

[↑ Volver al índice](#indice)

### Teoría

A veces el nombre del elemento o del atributo que quieres generar no es fijo: depende de los datos del XML. Para eso existen `xsl:element` y `xsl:attribute`.

**Atributo con valor dinámico — notación `{}`:**

La forma más común. Las llaves `{}` dentro de un valor de atributo evalúan una expresión XPath:

```xml
<!-- @class toma el valor de un elemento del XML -->
<div class="{categoria}">
  <xsl:value-of select="nombre"/>
</div>
```

**`xsl:attribute` — cuando el nombre del atributo también es dinámico:**

```xml
<xsl:element name="producto">
  <xsl:attribute name="id">
    <xsl:value-of select="@id"/>
  </xsl:attribute>
  <xsl:attribute name="categoria">
    <xsl:value-of select="@categoria"/>
  </xsl:attribute>
  <xsl:value-of select="nombre"/>
</xsl:element>
```

> **Regla importante:** `xsl:attribute` debe aparecer ANTES de cualquier contenido del elemento. Si primero escribes el contenido y luego el atributo, el procesador dará error porque ya no puede añadir atributos a un elemento que ha empezado a tener contenido.

**Ejemplo de clase CSS condicional:**

```xml
<xsl:template match="producto">
  <article>
    <xsl:choose>
      <xsl:when test="stock = 0">
        <xsl:attribute name="class">agotado</xsl:attribute>
      </xsl:when>
      <xsl:when test="stock &lt;= 5">
        <xsl:attribute name="class">bajo</xsl:attribute>
      </xsl:when>
      <xsl:otherwise>
        <xsl:attribute name="class">ok</xsl:attribute>
      </xsl:otherwise>
    </xsl:choose>
    <h3><xsl:value-of select="nombre"/></h3>
    <p>Stock: <xsl:value-of select="stock"/></p>
  </article>
</xsl:template>
```

### Ejercicio 13.1 — Ejercicio Integrador (Inventario)

Transforma `inventario.xml` en HTML con estas condiciones:

```xml
<inventario>
  <producto id="P01" categoria="Hardware">
    <nombre>Teclado mecánico</nombre>
    <stock>15</stock>
    <precio>89.99</precio>
  </producto>
  <producto id="P02" categoria="Software">
    <nombre>Licencia Office</nombre>
    <stock>3</stock>
    <precio>149.00</precio>
  </producto>
  <producto id="P03" categoria="Hardware">
    <nombre>Monitor 27"</nombre>
    <stock>0</stock>
    <precio>349.00</precio>
  </producto>
  <producto id="P04" categoria="Periféricos">
    <nombre>Ratón inalámbrico</nombre>
    <stock>42</stock>
    <precio>35.50</precio>
  </producto>
</inventario>
```

Requisitos:
1. Parámetro `stockMinimo` con valor por defecto 5.
2. Productos ordenados por precio descendente.
3. Plantilla nombrada "cabecera" con título y número total de productos.
4. Clase CSS: `agotado` (stock=0), `bajo` (stock ≤ stockMinimo), `ok` (resto).
5. Pie de página con el precio total acumulado.

### Solución 13.1

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:param name="stockMinimo" select="5"/>

  <xsl:template match="/">
    <html>
      <head><title>Inventario</title></head>
      <body>
        <!-- Llamada a plantilla nombrada -->
        <xsl:call-template name="cabecera"/>

        <main>
          <xsl:for-each select="inventario/producto">
            <xsl:sort select="precio" data-type="number" order="descending"/>
            <xsl:apply-templates select="."/>
          </xsl:for-each>
        </main>

        <footer>
          <p>Total inventario: <xsl:value-of select="sum(//precio)"/> EUR</p>
        </footer>
      </body>
    </html>
  </xsl:template>

  <!-- Plantilla nombrada -->
  <xsl:template name="cabecera">
    <header>
      <h1>Inventario de productos</h1>
      <p>Total: <xsl:value-of select="count(//producto)"/> productos</p>
    </header>
  </xsl:template>

  <!-- Plantilla con mode para cada producto -->
  <xsl:template match="producto">
    <article>
      <!-- Clase CSS condicional -->
      <xsl:choose>
        <xsl:when test="stock = 0">
          <xsl:attribute name="class">agotado</xsl:attribute>
        </xsl:when>
        <xsl:when test="stock &lt;= $stockMinimo">
          <xsl:attribute name="class">bajo</xsl:attribute>
        </xsl:when>
        <xsl:otherwise>
          <xsl:attribute name="class">ok</xsl:attribute>
        </xsl:otherwise>
      </xsl:choose>

      <h3><xsl:value-of select="nombre"/></h3>
      <p>Precio: <xsl:value-of select="precio"/> EUR</p>
      <p>Stock: <xsl:value-of select="stock"/></p>
    </article>
  </xsl:template>

</xsl:stylesheet>
```

---

<a id="bloque-14"></a>
## Bloque 14 — DOM vs SAX: dos formas de procesar XML desde código

[↑ Volver al índice](#indice)

### Teoría

Cuando procesas XML desde un lenguaje de programación (Java, Python, C#), tienes dos grandes opciones:

**DOM — Document Object Model:**

DOM carga el XML completo en memoria como un árbol de objetos. Puedes navegar libremente, modificar nodos, añadir o borrar elementos.

- Analogía: es como cargar todo un libro en RAM y poder saltar a cualquier página.
- Ventaja: acceso aleatorio, fácil de usar, puedes modificar.
- Inconveniente: consume mucha memoria si el documento es grande.

**SAX — Simple API for XML:**

SAX lee el XML secuencialmente y lanza **eventos** conforme avanza. Tú defines un "manejador" que reacciona a esos eventos.

- Analogía: es como leer el libro página por página sin tenerlo todo en memoria, reaccionando a lo que lees.
- Ventaja: muy eficiente en memoria, puede procesar ficheros enormes.
- Inconveniente: solo avanza (no puedes volver atrás), no puedes modificar.

**Tabla comparativa — la que cae en examen:**

| Criterio | DOM | SAX |
|----------|-----|-----|
| Modelo | Árbol completo en memoria | Eventos en streaming |
| Memoria | Alta (carga todo el doc) | Muy baja |
| Velocidad | Más lenta al cargar | Muy rápida |
| Acceso | Aleatorio (cualquier nodo) | Secuencial (solo hacia adelante) |
| Modificación | Sí, fácil | No (solo lectura) |
| Facilidad de uso | Alta (API intuitiva) | Más compleja |
| Tamaño recomendado | Pequeño/mediano (<100 MB) | Cualquier tamaño |
| Relectura | Sí (el árbol persiste) | No (un único paso) |
| Caso de uso típico | Edición, transformación, XSLT | Indexación, extracción masiva |

> **Para el examen:** si te preguntan cuándo usar DOM: documentos pequeños, necesitas modificarlos, necesitas navegar en múltiples direcciones. Si te preguntan SAX: documentos grandes, solo necesitas leerlos una vez, importa la memoria.

---

<a id="bloque-15"></a>
## Bloque 15 — Dónde vive el XML: sistemas de almacenamiento

[↑ Volver al índice](#indice)

### Teoría

Cuando necesitas almacenar XML de forma persistente, tienes varias opciones:

**1. Sistema de ficheros (archivos .xml)**

La forma más simple: el XML es un fichero en el disco.

- Ventaja: universalidad, sin instalar nada.
- Inconveniente: no puedes hacer consultas eficientes sobre el contenido interno del XML.

**2. Base de datos relacional (SGBDR) + XML**

Los SGBDR modernos permiten guardar XML, de tres maneras:

| Estrategia | Cómo funciona | Ventaja | Inconveniente |
|------------|--------------|---------|---------------|
| **CLOB/texto** | El XML entero como campo de texto largo | Muy simple | No se puede consultar el interior |
| **Tipo XML nativo** | Columna de tipo `XML` que el motor entiende | Permite consultas XPath | Depende del SGBD |
| **Shredding** | Se descompone el XML en tablas relacionales | Consultas SQL estándar | Se pierde la jerarquía XML |

```sql
-- Ejemplo: columna de tipo XML en SQL Server
CREATE TABLE pedidos (
    id        INT PRIMARY KEY,
    datos_xml XML
);

-- Consulta XPath sobre la columna XML
SELECT datos_xml.query('/pedido/cliente/text()') AS cliente
FROM pedidos;
```

**3. Bases de datos NoSQL**

| Tipo | Características | Ejemplos |
|------|----------------|---------|
| Documentales | Almacenan JSON/XML con esquema flexible | MongoDB, CouchDB |
| Clave-Valor | Pares clave-valor, muy rápidas | Redis, DynamoDB |
| Columnares | Por columnas, ideal para analytics | Cassandra |
| Grafos | Nodos y relaciones | Neo4j |

**4. Bases de datos XML Nativas (NXD) — las que usan XQuery**

Diseñadas específicamente para XML. El documento XML es la unidad básica de almacenamiento. Utilizan XQuery y XPath nativamente.

- **BaseX**: gratuita, open source, muy usada en entornos educativos. Soporte XQuery 3.1 completo.
- **eXist-db**: gratuita, open source, con IDE web (eXide).
- **MarkLogic**: empresarial, muy potente, de pago.

> **Concepto clave:** una NXD trata el documento XML entero como una unidad, igual que una BD relacional trata una fila como unidad. No descompone el XML en tablas.

---

<a id="bloque-16"></a>
## Bloque 16 — Introducción a XQuery y su relación con XPath

[↑ Volver al índice](#indice)

### Teoría

XQuery es el estándar W3C para consultar y transformar XML. La analogía es clara:

```
SQL        es a  BD Relacional  lo que  XQuery  es a  BD XML nativa
```

XQuery es un **superconjunto de XPath**: toda expresión XPath válida es también XQuery válido. XQuery añade encima la capacidad de formular consultas completas, iterar, filtrar, ordenar y construir nuevos documentos.

**Los comentarios en XQuery se escriben así:**

```xquery
(: Esto es un comentario en XQuery :)
```

**Consulta XQuery simple (solo una expresión de ruta):**

```xquery
doc('biblioteca.xml')/biblioteca/libro/titulo
```

`doc('archivo.xml')` carga el documento XML. A partir de ahí, la navegación es XPath puro.

**Versiones:**

| Versión | Año | Novedades |
|---------|-----|-----------|
| XQuery 1.0 | 2007 | FLWOR, funciones, tipos XSD |
| XQuery 3.0 | 2014 | Funciones de orden superior, try-catch, switch |
| XQuery 3.1 | 2017 | Mapas, arrays, soporte JSON |

> **Para el examen:** XQuery es el lenguaje nativo de las BD XML. XPath es el lenguaje de navegación que XQuery usa internamente. XSLT también usa XPath pero para transformación de documentos estáticos, mientras XQuery está pensado para consultas dinámicas sobre bases de datos.

---

<a id="bloque-17"></a>
## Bloque 17 — FLWOR: el corazón de XQuery

[↑ Volver al índice](#indice)

### Teoría

FLWOR (se pronuncia "flower") es el equivalente XQuery del `SELECT ... FROM ... WHERE ... ORDER BY` de SQL. Son las iniciales de sus cinco cláusulas:

| Cláusula | Función | ¿Obligatoria? |
|----------|---------|---------------|
| **F**or | Itera sobre una secuencia, asignando cada nodo a una variable | No |
| **L**et | Asigna una expresión entera a una variable (sin iterar) | No |
| **W**here | Filtra según una condición booleana | No |
| **O**rder by | Ordena los resultados | No |
| **R**eturn | Define el resultado para cada iteración | **Sí siempre** |

**Analogía SQL → FLWOR:**

```
SQL:     SELECT titulo, precio FROM libros WHERE precio > 10 ORDER BY titulo
XQuery:  for $libro in //libro
         where number($libro/precio) > 10
         order by $libro/titulo ascending
         return <resultado>{ $libro/titulo }</resultado>
```

**Ejemplo completo con todos los pasos:**

```xquery
(: Libros con precio mayor a 10€, ordenados por título :)
for $libro in doc('biblioteca.xml')/biblioteca/libro
let $precio := $libro/precio
where number($precio) > 10
order by $libro/titulo ascending
return
  <resultado>
    <titulo>{ $libro/titulo/text() }</titulo>
    <autor>{ $libro/autor/text() }</autor>
    <precio>{ $precio/text() }</precio>
  </resultado>
```

**Explicación paso a paso:**

- **`for $libro in ...`**: por cada elemento `<libro>` del documento, la variable `$libro` toma su valor. Como en un bucle for.
- **`let $precio := $libro/precio`**: crea un alias. No itera, solo guarda la referencia al nodo `<precio>` del libro actual.
- **`where number($precio) > 10`**: filtra. Solo pasan los libros cuyo precio convertido a número supera 10.
- **`order by $libro/titulo ascending`**: ordena de A a Z por título.
- **`return <resultado>...</resultado>`**: para cada libro que pasa el filtro, genera este fragmento XML. Las llaves `{}` insertan valores dinámicos.

> **Diferencia `for` vs `let`:**
> - `for $x in (1, 2, 3)` → itera: `$x` toma 1, luego 2, luego 3. El `return` se ejecuta 3 veces.
> - `let $lista := (1, 2, 3)` → NO itera: `$lista` es la secuencia entera. El `return` se ejecuta 1 vez.

### Ejercicio 17.1 — FLWOR básico

Dado el XML de biblioteca:

```xml
<biblioteca>
  <libro id="1">
    <titulo>Don Quijote</titulo>
    <autor>Cervantes</autor>
    <precio>17.50</precio>
  </libro>
  <libro id="2">
    <titulo>Cien años de soledad</titulo>
    <autor>García Márquez</autor>
    <precio>22.00</precio>
  </libro>
  <libro id="3">
    <titulo>La metamorfosis</titulo>
    <autor>Kafka</autor>
    <precio>8.99</precio>
  </libro>
</biblioteca>
```

Escribe consultas XQuery para:
- **a)** Obtener todos los títulos de libros.
- **b)** Obtener solo los títulos de libros con precio > 10, ordenados por precio descendente.
- **c)** Generar un XML con el precio de cada libro más IVA (21%), solo de los que cuesten menos de 20€ antes de IVA.

### Solución 17.1

```xquery
(: a) Todos los títulos - expresión de ruta simple :)
doc('biblioteca.xml')/biblioteca/libro/titulo/text()

(: b) Con filtro y ordenación :)
for $libro in doc('biblioteca.xml')/biblioteca/libro
where number($libro/precio) > 10
order by $libro/precio descending
return $libro/titulo/text()

(: c) XML con IVA calculado :)
for $libro in doc('biblioteca.xml')/biblioteca/libro
let $precio := number($libro/precio)
where $precio < 20
return
  <oferta>
    <titulo>{ data($libro/titulo) }</titulo>
    <precio-sin-iva>{ $precio }</precio-sin-iva>
    <precio-con-iva>{ $precio * 1.21 }</precio-con-iva>
  </oferta>
```

> **`data()` vs `text()`:** ambos extraen el valor textual de un nodo. `data()` es más robusta con tipos XSD y es la preferida en XQuery. `text()` también funciona.

---

<a id="bloque-18"></a>
## Bloque 18 — Funciones de búsqueda: cadenas y agregación

[↑ Volver al índice](#indice)

### Teoría

XQuery tiene las mismas funciones que XPath, más algunas adicionales en su versión 3.x.

**Funciones de cadena en XQuery (búsqueda):**

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `contains(s, sub)` | ¿s contiene sub? | `contains($titulo, 'Quijote')` |
| `starts-with(s, pre)` | ¿s empieza por pre? | `starts-with($nombre, 'A')` |
| `ends-with(s, suf)` | ¿s termina por suf? | `ends-with($fichero, '.xml')` |
| `lower-case(s)` | Convierte a minúsculas | `lower-case($titulo)` |
| `upper-case(s)` | Convierte a mayúsculas | `upper-case($titulo)` |
| `normalize-space(s)` | Elimina espacios extra | `normalize-space($desc)` |
| `matches(s, regex)` | Comprueba expresión regular | `matches($isbn, '[0-9]{13}')` |
| `string-length(s)` | Longitud de la cadena | `string-length($nombre)` |
| `substring(s, ini, len)` | Parte de la cadena | `substring($texto, 1, 5)` |
| `tokenize(s, sep)` | Divide por separador | `tokenize($lista, ',')` |

**Funciones de agregación:**

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `count($seq)` | Cuenta ítems | `count(//libro)` |
| `sum($seq)` | Suma numérica | `sum(//precio)` |
| `avg($seq)` | Media aritmética | `avg(//precio)` |
| `max($seq)` | Máximo | `max(//precio)` |
| `min($seq)` | Mínimo | `min(//precio)` |
| `number($v)` | Convierte a número | `number($libro/precio)` |

**Búsqueda insensible a mayúsculas:**

```xquery
(: Siempre combina lower-case() con contains() para buscar texto :)
for $libro in doc('biblioteca.xml')//libro
where contains(lower-case($libro/titulo), 'quijote')
return $libro/titulo
```

**Estadísticas de un conjunto de datos:**

```xquery
let $precios := doc('biblioteca.xml')/biblioteca/libro/precio/text()
return
  <estadisticas>
    <total-libros>{ count($precios) }</total-libros>
    <precio-total>{ sum($precios) }</precio-total>
    <precio-minimo>{ min($precios) }</precio-minimo>
    <precio-maximo>{ max($precios) }</precio-maximo>
    <precio-medio>{ avg($precios) }</precio-medio>
  </estadisticas>
```

**Predicados con operadores de valor (XQuery es más estricto que XPath 1.0):**

En XQuery tienes dos tipos de operadores de comparación:

| Operadores generales | Operadores de valor |
|---------------------|---------------------|
| `=, !=, <, >, <=, >=` | `eq, ne, lt, gt, le, ge` |
| Comparan secuencias | Comparan valores atómicos (uno a uno) |
| Más permisivos | Más estrictos |

```xquery
(: Operador general: funciona si precio puede ser una secuencia :)
//libro[precio > 15]

(: Operador de valor: para comparación exacta de un solo valor :)
//libro[precio/text() eq '18.50']
```

---

<a id="bloque-19"></a>
## Bloque 19 — Joins: cruzar dos documentos XML

[↑ Volver al índice](#indice)

### Teoría

XQuery no tiene un `JOIN` explícito como SQL, pero puedes cruzar datos de dos documentos usando variables anidadas en FLWOR.

**Patrón del Join en XQuery:**

```xquery
for $libro in doc('libros.xml')//libro
let $autor := doc('autores.xml')//autor[@id = $libro/@id-autor]
where exists($autor)
return
  <resultado>
    <titulo>{ data($libro/titulo) }</titulo>
    <autor-nombre>{ data($autor/nombre) }</autor-nombre>
    <nacionalidad>{ data($autor/nacionalidad) }</nacionalidad>
  </resultado>
```

**Cómo funciona:**

1. `for $libro in ...` itera sobre cada libro.
2. `let $autor := doc('autores.xml')//autor[@id = $libro/@id-autor]` busca en el segundo documento el autor cuyo `@id` coincide con el `@id-autor` del libro actual. Es la "clave foránea".
3. `where exists($autor)` filtra los libros que tienen autor en el segundo documento. Sin esto, si no hay coincidencia, `$autor` está vacío y dará error.
4. El `return` construye el resultado mezclando datos de ambos documentos.

> **Equivalencia con SQL:**
> ```sql
> SELECT l.titulo, a.nombre, a.nacionalidad
> FROM libros l
> JOIN autores a ON l.id_autor = a.id
> ```

**`exists()` — función importante:**

```xquery
exists($autor)        → true si $autor contiene al menos un nodo
empty($autor)         → true si $autor está vacío
count($autor) > 0     → equivalente a exists()
```

---

<a id="bloque-20"></a>
## Bloque 20 — XQUF: insertar, borrar y actualizar nodos

[↑ Volver al índice](#indice)

### Teoría

XQuery Update Facility (XQUF) añade operaciones de modificación. Estas operaciones siguen el modelo de **evaluación pendiente**: todas las modificaciones se acumulan y se aplican al final, de forma atómica.

**Las 5 operaciones de XQUF:**

| Expresión | Qué hace | Sintaxis básica |
|-----------|----------|----------------|
| `insert node` | Inserta nuevos nodos | `insert node <nuevo> into/before/after <destino>` |
| `delete node` | Elimina nodos | `delete node <expresión>` |
| `replace node` | Reemplaza el nodo completo | `replace node <nodo> with <nuevo>` |
| `replace value of` | Cambia el contenido de un nodo | `replace value of <nodo> with <valor>` |
| `rename node` | Renombra el elemento | `rename node <nodo> as <nuevo-nombre>` |

**`insert node` — con las posiciones:**

```xquery
(: Insertar al final de un elemento padre (into = as last into) :)
insert node
  <libro id='4'>
    <titulo>Fortunata y Jacinta</titulo>
    <autor>Galdós</autor>
    <precio>22.00</precio>
  </libro>
into doc('biblioteca.xml')/biblioteca

(: Insertar antes de un elemento específico :)
insert node <editorial>Anaya</editorial>
before doc('biblioteca.xml')/biblioteca/libro[@id='1']/autor

(: Insertar después :)
insert node <editorial>Anaya</editorial>
after doc('biblioteca.xml')/biblioteca/libro[@id='1']/titulo

(: Insertar un atributo :)
insert node attribute stock { 'disponible' }
into doc('biblioteca.xml')/biblioteca/libro[@id='1']
```

**`replace value of` — cambiar el contenido:**

```xquery
(: Actualizar el precio de un libro :)
replace value of
    doc('biblioteca.xml')/biblioteca/libro[@id='2']/precio
with '14.50'

(: Actualizar el valor de un atributo :)
replace value of
    doc('biblioteca.xml')/biblioteca/libro[@id='1']/@id
with '10'
```

> **`replace value of` vs `replace node`:**
> - `replace value of`: cambia solo el CONTENIDO del nodo (el texto). El nodo sigue siendo el mismo.
> - `replace node`: sustituye el nodo COMPLETO, incluyendo su nombre, atributos e hijos.

**`delete node`:**

```xquery
(: Borrar un nodo concreto :)
delete node doc('biblioteca.xml')/biblioteca/libro[@id='3']

(: Borrar todos los libros agotados :)
delete node doc('biblioteca.xml')/biblioteca/libro[@stock='agotado']
```

### Ejercicio 20.1 — Operaciones XQUF

Escribe las expresiones XQUF para:
- **a)** Insertar un libro nuevo al final de la biblioteca (id=5, título "El señor de los anillos", autor "Tolkien", precio 25.00).
- **b)** Cambiar el precio del libro con id=2 a 19.99.
- **c)** Añadir un atributo `disponible="true"` al primer libro.
- **d)** Borrar todos los libros con precio mayor de 40.

### Solución 20.1

```xquery
(: a) Insertar nuevo libro :)
insert node
  <libro id='5'>
    <titulo>El señor de los anillos</titulo>
    <autor>Tolkien</autor>
    <precio>25.00</precio>
  </libro>
into doc('biblioteca.xml')/biblioteca

(: b) Actualizar precio :)
replace value of
    doc('biblioteca.xml')/biblioteca/libro[@id='2']/precio
with '19.99'

(: c) Añadir atributo :)
insert node attribute disponible { 'true' }
into doc('biblioteca.xml')/biblioteca/libro[1]

(: d) Borrar libros caros :)
delete node doc('biblioteca.xml')/biblioteca/libro[precio > 40]
```

---

<a id="bloque-21"></a>
## Bloque 21 — XQuery completo: prólogo, funciones y módulos

[↑ Volver al índice](#indice)

### Teoría

XQuery es un lenguaje completo, no solo un lenguaje de consultas. Puedes definir funciones propias, variables globales y organizarlo todo en módulos reutilizables.

**El prólogo — la cabecera de la consulta:**

```xquery
xquery version '3.1';

(: Declarar espacios de nombres :)
declare default element namespace 'http://www.ejemplo.com/biblioteca';
declare namespace lib = 'http://www.ejemplo.com/biblioteca';

(: Variables globales :)
declare variable $IVA as xs:decimal := 0.21;
declare variable $RUTA as xs:string := 'datos/';

(: Función de usuario :)
declare function local:precio-con-iva($precio as xs:decimal) as xs:decimal {
    $precio * (1 + $IVA)
};

(: Cuerpo de la consulta :)
for $libro in doc(concat($RUTA, 'biblioteca.xml'))//libro
return
  <libro-precio-final>{ local:precio-con-iva(number($libro/precio)) }</libro-precio-final>
```

**Tipos de datos XQuery (se usan en parámetros y variables tipadas):**

| Categoría | Tipos |
|-----------|-------|
| Numéricos | `xs:integer`, `xs:decimal`, `xs:float`, `xs:double` |
| Cadenas | `xs:string`, `xs:normalizedString` |
| Fechas | `xs:date`, `xs:time`, `xs:dateTime` |
| Booleanos | `xs:boolean` |
| Nodos | `element()`, `attribute()`, `text()`, `node()` |

**`if-then-else` en XQuery:**

```xquery
for $libro in doc('biblioteca.xml')//libro
return
  if (number($libro/precio) > 20)
  then <libro tipo='premium'>{ data($libro/titulo) }</libro>
  else <libro tipo='estandar'>{ data($libro/titulo) }</libro>
```

> **Diferencia con XSLT:** en XQuery el `else` es **obligatorio**. Si no necesitas nada en el else, puedes poner `else ()` (secuencia vacía).

**`switch` (XQuery 3.0+):**

```xquery
for $libro in doc('biblioteca.xml')//libro
return switch($libro/@categoria)
  case 'novela'  return <genero>Narrativa</genero>
  case 'poesia'  return <genero>Lírica</genero>
  case 'ensayo'  return <genero>No Ficción</genero>
  default        return <genero>Sin clasificar</genero>
```

**`try-catch` para manejo de errores (XQuery 3.0+):**

```xquery
try {
    let $doc := doc('archivo.xml')
    return $doc//titulo
} catch * {
    <error>{ $err:description }</error>
}
```

**Módulos de biblioteca:**

```xquery
(: Archivo: lib-utilidades.xqm :)
module namespace util = 'http://mi-empresa.com/utilidades';

declare function util:formato-precio($precio as xs:decimal) as xs:string {
    concat(format-number($precio, '#,##0.00'), ' €')
};

(: En la consulta principal que importa el módulo: :)
xquery version '3.1';
import module namespace util = 'http://mi-empresa.com/utilidades'
    at 'lib-utilidades.xqm';

for $libro in doc('biblioteca.xml')//libro
return util:formato-precio(number($libro/precio))
```

---

<a id="apendice"></a>
## Apéndice — Errores típicos y trampas de examen

[↑ Volver al índice](#indice)

### XPath — errores frecuentes

| Error | Qué está mal | Corrección |
|-------|-------------|------------|
| `/titulo` | Busca `titulo` como hijo directo de la raíz `/` del documento, no dentro del XML | `//titulo` o `/catalogo/libro/titulo` |
| `[@id=001]` | Comparación numérica en un atributo que es texto | `[@id='001']` (con comillas) |
| `libro[precio>18]/titulo` sin `//` | Solo funciona si estás en el contexto correcto | `//libro[precio>18]/titulo` |
| `position=1` | `position` es una función, no un valor | `position()=1` o simplemente `[1]` |

### XSLT — errores frecuentes

| Error | Causa | Solución |
|-------|-------|----------|
| Variables con `$` en el `select` | `<xsl:variable name="x" select="$iva * 2"/>` puede fallar si `$iva` no está en scope | Declara las variables en el nivel correcto (global si se usan en todo el fichero) |
| `<` dentro de `test=""` | El `<` es ilegal en XML | Escríbelo como `&lt;` |
| `xsl:attribute` después del contenido | El atributo debe ir antes de cualquier texto o elemento hijo | Mueve `xsl:attribute` al inicio del elemento |
| Plantilla con `name` en lugar de `match` | `<xsl:template name="libro">` no se activa automáticamente | Usa `match="libro"` para activación automática, o llámala con `call-template` |
| Variables inmutables | Intentar reasignar `$x` dentro de un bucle | En XSLT las variables no cambian. Usa `sum()`, `count()`, etc. para acumular |

### XQuery — errores frecuentes

| Error | Causa | Solución |
|-------|-------|----------|
| `if` sin `else` | En XQuery el `else` es obligatorio | Añade `else ()` si no necesitas nada |
| `for` y `let` confundidos | `for` itera, `let` no | `for $x in //libro` (itera) vs `let $lista := //libro` (asigna) |
| Olvidar `number()` al comparar | `$libro/precio > 10` compara el nodo, no el número | `number($libro/precio) > 10` |
| `replace value of` con múltiples nodos | Solo acepta un nodo exacto | Asegúrate de que la ruta devuelve exactamente 1 nodo |
| Olvidar `exists()` en joins | Si no hay coincidencia, `$autor` está vacío y puede dar error al acceder a sus hijos | Añade `where exists($autor)` |

### Tabla resumen — qué instrucción usar para qué

| Necesito... | En XPath | En XSLT | En XQuery |
|------------|---------|---------|-----------|
| Navegar al nodo X | Ruta directa: `/catalogo/libro` | `select="catalogo/libro"` | `doc()//libro` |
| Filtrar por condición | Predicado `[condición]` | `xsl:if` o predicado en `select` | `where condición` |
| Iterar sobre nodos | No aplica | `xsl:for-each` | `for $x in ...` |
| Ordenar | No aplica | `xsl:sort` | `order by` |
| Contar nodos | `count(//libro)` | `count(//libro)` | `count(//libro)` |
| Texto del nodo | `text()` o la ruta | `xsl:value-of select=".."` | `data()` o `text()` |
| Insertar nodo | No aplica | No aplica | `insert node ... into ...` |
| Borrar nodo | No aplica | No aplica | `delete node ...` |
| Crear elemento dinámico | No aplica | `xsl:element` o `{}` | Constructor directo: `<elem>{ expr }</elem>` |

---

## Referencia rápida de "¿cuándo uso qué?"

```
¿Necesito apuntar a un nodo dentro de un XML?
  → XPath

¿Necesito transformar un XML en HTML/CSV/otro XML?
  → XSLT (que usa XPath internamente)
     Estructura: <?xml version...> <xsl:stylesheet...> <xsl:output.../> <xsl:template match="/">...

¿Necesito consultar/modificar XML en una base de datos?
  → XQuery (que también usa XPath)
     Patrón: for $x in doc('file.xml')//elemento [where ...] [order by ...] return <resultado/>

¿Necesito procesar XML desde Java/Python?
  → DOM si el fichero es pequeño y necesito modificarlo
  → SAX si el fichero es grande y solo necesito leerlo
```

---

*Fin de la guía — T6 + T7 Lenguaje de Marcas*
