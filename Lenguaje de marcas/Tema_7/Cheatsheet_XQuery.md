# <a id="indice"></a> Cheatsheet Definitivo: XQuery

## 📚 Índice Rápido

1. [Sistemas de Almacenamiento XML](#sec1)
2. [XML en Bases de Datos Relacionales](#sec2)
3. [Introducción a XQuery](#sec3)
4. [Expresiones FLWOR](#sec4)
5. [XQUF - Actualización (CRUD)](#sec5)
6. [Extracción de Información](#sec6)
7. [Técnicas de Búsqueda](#sec7)
8. [XQuery Lenguaje Completo](#sec8)
9. [Herramientas y Bases de Datos](#sec9)

---

## <a id="sec1"></a> 🗄️ SISTEMAS DE ALMACENAMENTO XML

[Volver al Índice ↑](#indice)

| Tipo | Características | Ejemplos |
|------|-----------------|----------|
| **Ficheros** | Simple, sin consultas avanzadas | `.xml` individuales |
| **SGBDR** | SQL + soporte XML opcional | MySQL, PostgreSQL, Oracle |
| **NoSQL Documental** | Flexible, JSON/BSON/XML | MongoDB, CouchDB, MarkLogic |
| **NXD (XML Nativas)** | XML como unidad atómica, XQuery nativo | **BaseX**, eXist-db, MarkLogic |

> 💡 **Dato clave**: Las **NXD** preservan orden, comentarios e instrucciones de procesamiento. Si tu XML es irregular → usa NXD.

---

## <a id="sec2"></a> 🗃️ XML EN BASES DE DATOS RELACIONALES

[Volver al Índice ↑](#indice)

### 3 Estrategias

| Estrategia | Consulta Interna | Preserva Estructura | Uso |
|------------|------------------|---------------------|-----|
| **CLOB** (texto) | ❌ No | ✅ Sí | Solo guardar/recuperar XML completo |
| **Tipo XML nativo** | ✅ XPath/XQuery | ✅ Sí | Consultas sobre partes del XML |
| **Shredding** (mapeo) | ✅ SQL | ❌ No | Datos tabulares con SQL estándar |

> 💡 **Regla de decisión rápida**:
> - Solo guardar → **CLOB**
> - Consultas internas → **Tipo XML nativo**
> - Siempre tabular → **Shredding**

---

## <a id="sec3"></a> 🎯 INTRODUCCIÓN A XQUERY

[Volver al Índice ↑](#indice)

### Conceptos Fundamentales

- **XQuery = superconjunto de XPath**: Toda expresión XPath es válida en XQuery
- **Modelo XDM**: Árbol de nodos (XPath Data Model)
- **Versión actual**: XQuery 3.1 (2017) - soporta mapas, arrays, JSON

### Arquitectura

```
┌─────────────────────────────────────────┐
│           XQuery 3.1                   │
│  ┌─────────┐ ┌─────────┐ ┌──────────┐  │
│  │  FLWOR  │ │Construct│ │ Funciones│  │
│  └────┬────┘ └─────────┘ └──────────┘  │
│       │                                │
│  ┌────┴─────────────────────────┐       │
│  │     XPath 2.0 / 3.1        │       │
│  │  Rutas · Predicados · Func │       │
│  └────────────────────────────┘       │
└─────────────────────────────────────────┘
```

---

## <a id="sec4"></a> 🌸 EXPRESIONES FLWOR

[Volver al Índice ↑](#indice)

### Cláusulas FLWOR

| Cláusula | Función | ¿Obligatoria? |
|----------|---------|---------------|
| **FOR** | Itera secuencia → `$variable` | No |
| **LET** | Asigna expresión a variable | No |
| **WHERE** | Filtra con condición | No |
| **ORDER BY** | Ordena resultados | No |
| **RETURN** | Construye salida | **SÍ** |

### Ejemplo FLWOR Completo

```xquery
for $libro in doc('biblio.xml')/biblioteca/libro
let $precio := $libro/precio
where number($precio) > 10
order by $libro/titulo ascending
return
  <resultado>
    <titulo>{ $libro/titulo/text() }</titulo>
    <precio>{ $precio/text() }</precio>
  </resultado>
```

> 🔴 **Diferencia FOR vs LET**:
> - `for $x in secuencia` → **itera** (un ciclo por cada nodo)
> - `let $x := secuencia` → **asigna todo** de una vez, sin iterar

### Flujo FLWOR

```
FOR (itera) → LET (asigna) → WHERE (filtra) → ORDER BY (ordena) → RETURN (construye)
```

---

## <a id="sec5"></a> ✏️ XQUF - ACTUALIZACIÓN (CRUD)

[Volver al Índice ↑](#indice)

### Operaciones XQUF

| Expresión | Acción | Detalle |
|-----------|--------|---------|
| `insert node <n> into <dest>` | Insertar | Dentro del nodo destino |
| `insert node <n> before/after <dest>` | Insertar | Antes/después del nodo |
| `delete node <expr>` | Eliminar | Borra nodos |
| `replace node <n> with <nuevo>` | Reemplazar | **Nodo completo** (nombre + atributos + hijos) |
| `replace value of <n> with <val>` | Reemplazar | **Solo contenido textual** |
| `rename node <n> as <nombre>` | Renombrar | Cambia nombre del elemento/atributo |

> 🔴 **CRÍTICO - Diferencia replace**:
> - `replace node` = borra y crea nuevo nodo completo
> - `replace value of` = solo cambia el texto interno, mantiene posición y nombre

---

## <a id="sec6"></a> 📥 EXTRACCIÓN DE INFORMACIÓN

[Volver al Índice ↑](#indice)

### Rutas XPath Básicas

```xquery
doc('biblio.xml')/biblioteca/libro/titulo/text()   (: Todos los títulos :)
doc('biblio.xml')/biblioteca/libro[@id='1']        (: Libro con id='1' :)
doc('biblio.xml')//titulo                          (: Cualquier <titulo> :)
count(doc('biblio.xml')/biblioteca/libro)          (: Contar libros :)
```

### Funciones de Extracción

| Función | Uso |
|---------|-----|
| `{ $expr }` | Inserta valor dinámico en XML de salida |
| `data($nodo)` | Valor tipado (mejor que `text()`) |
| `text()` | Contenido textual plano |
| `current-date()` | Fecha actual para atributos |

> 💡 **Consejo de rendimiento**: Evita `//` en documentos grandes. Usa rutas absolutas (`/raiz/padre/hijo`) para aprovechar índices.

---

## <a id="sec7"></a> 🔍 TÉCNICAS DE BÚSQUEDA

[Volver al Índice ↑](#indice)

### Ejes de Navegación XPath

| Eje | Abreviatura | Descripción |
|-----|-------------|-------------|
| `child` | (default) | Hijos directos |
| `attribute` | `@` | Atributos del nodo |
| `descendant` | `//` | Todos los descendientes |
| `parent` | `..` | Nodo padre |
| `self` | `.` | Nodo actual |
| `ancestor` | (explícito) | Todos los ancestros |
| `following-sibling` | (explícito) | Hermanos posteriores |
| `preceding-sibling` | (explícito) | Hermanos anteriores |

### Predicados de Filtrado

```xquery
libro[1]                        (: Primero :)
libro[last()]                   (: Último :)
libro[position() <= 2]          (: Primeros 2 :)
libro[precio > 15]              (: Filtro numérico :)
libro[@id='001']                (: Filtro atributo :)
libro[precio > 10 and precio < 20]  (: Condición combinada :)
libro[editorial]                (: Existe hijo editorial :)
```

### Operadores de Comparación

| Tipo | Operadores | Uso |
|------|------------|-----|
| **Valor** | `eq`, `ne`, `lt`, `gt`, `le`, `ge` | Comparar valores atómicos individuales |
| **Generales** | `=`, `!=`, `<`, `>` | Comparar secuencias (más permisivos) |

### Funciones de Cadena Clave

```xquery
contains($s, $sub)              (: Contiene subcadena :)
starts-with($s, $pre)           (: Empieza con :)
ends-with($s, $suf)             (: Termina con :)
upper-case($s) / lower-case($s) (: Cambio caso :)
normalize-space($s)             (: Quitar espacios extra :)
matches($s, $regex)             (: Regex (ISBN, formatos) :)
tokenize($s, $regex)            (: Dividir cadena :)
```

### Funciones de Agregación

```xquery
count($secuencia)               (: Contar :)
sum($secuencia)                 (: Sumar :)
avg($secuencia)                 (: Media :)
max($secuencia)                 (: Máximo :)
min($secuencia)                 (: Mínimo :)
```

### Joins en XQuery (simulación SQL)

```xquery
for $libro in doc('libros.xml')//libro
let $autor := doc('autores.xml')//autor[@id = $libro/@id-autor]
where exists($autor)
return <resultado>{ $libro/titulo } - { $autor/nombre }</resultado>
```

> 💡 **Búsqueda insensible a mayúsculas**:
> ```xquery
> contains(lower-case($libro/titulo), 'quijote')
> ```

---

## <a id="sec8"></a> 🛠️ XQUERY COMO LENGUAJE COMPLETO

[Volver al Índice ↑](#indice)

### Prólogo de Consulta

```xquery
xquery version '3.1';
declare variable $IVA as xs:decimal := 0.21;

declare function local:precio-con-iva($precio as xs:decimal) as xs:decimal {
    $precio * (1 + $IVA)
};
```

### Tipos de Datos XQuery

| Categoría | Tipos |
|-----------|-------|
| **Numéricos** | `xs:integer`, `xs:decimal`, `xs:float`, `xs:double` |
| **Cadenas** | `xs:string` |
| **Fechas** | `xs:date`, `xs:dateTime`, `xs:duration` |
| **Booleanos** | `xs:boolean` |
| **Secuencias** | `item()*` (0+), `xs:integer+` (1+), `xs:string?` (0-1) |

### Control de Flujo

| Estructura | Sintaxis | Nota |
|--------------|----------|------|
| **If-then-else** | `if ($cond) then $v1 else $v2` | `else` es **obligatorio** |
| **Switch** | `switch ($expr) case $v1 return $r1 default return $rd` | XQuery 3.0+ |
| **Cuantificadores** | `some $x in $seq satisfies $cond` | Existe al menos uno |
| **Cuantificadores** | `every $x in $seq satisfies $cond` | Todos cumplen |
| **Try-catch** | `try { $expr } catch * { $fallback }` | XQuery 3.0+ |
| **Typeswitch** | `typeswitch ($val) ...` | Por tipo de dato |

> 🔴 **Else obligatorio**: Si no necesitas `else`, usa `else ()` (secuencia vacía).

### Módulos de Biblioteca (.xqm)

```xquery
(: Archivo: lib-utilidades.xqm :)
module namespace util = 'http://mi-empresa.com/utilidades';
declare function util:formato-precio($p as xs:decimal) as xs:string { ... };

(: Consulta principal :)
import module namespace util = 'http://mi-empresa.com/utilidades'
    at 'lib-utilidades.xqm';
```

---

## <a id="sec9"></a> 🛢️ HERRAMIENTAS Y BASES DE DATOS

[Volver al Índice ↑](#indice)

### Bases de Datos XML Nativas

| Herramienta | Tipo | XQuery | Notas |
|-------------|------|--------|-------|
| **BaseX** | Open Source (BSD) | 3.1 completo + XQUF | ⭐ **Recomendada para aula** |
| **eXist-db** | Open Source (LGPL) | XQuery + IDE web | Con Lucene integrado |
| **MarkLogic** | Empresarial | XQuery + JS + SPARQL | Distribuido, escalable |

### Comandos BaseX

```bash
basex -c 'CREATE DB mibd archivo.xml'   # Crear base de datos
basex consulta.xq                        # Ejecutar archivo .xq
basexhttp                                # Servidor HTTP REST
```

### API REST BaseX

| Método | Operación |
|--------|-----------|
| `GET ?query=...` | Ejecutar XQuery |
| `PUT` | Crear/actualizar documento |
| `DELETE` | Eliminar documento |
| `POST` | Consultas largas (cuerpo XML) |

**Ejemplo REST**:
```
GET http://localhost:8984/rest/mibd?query=//titulo
```

### Procesadores Embebidos

| Lenguaje | Herramienta | Capacidad |
|----------|-------------|-----------|
| Java | **Saxon** | XQuery 3.1 completo (Saxon-HE gratuita) |
| Python | **lxml** | XPath 1.0 (no FLWOR completo) |
| Python | **BaseX Client** | XQuery completo vía TCP |

### Tabla de Decisión

| Escenario | Herramienta |
|-----------|-------------|
| Aprendizaje / aula | **BaseX GUI** |
| Java empresarial | **Saxon + BaseX/MarkLogic** |
| Python | **BaseX Client** |
| Web / microservicios | **BaseX REST + Saxon** |

---

## ⚡ CHEATSHEET VISUAL

```
┌─────────────────────────────────────────────────────────┐
│  FLWOR EXPRESSION                                       │
├─────────────────────────────────────────────────────────┤
│  for $x in secuencia    →  Itera uno por uno            │
│  let $x := secuencia    →  Asigna todo de golpe        │
│  where condicion        →  Filtra                       │
│  order by campo         →  Ordena                      │
│  return expresion       →  Construye salida (obligatorio)│
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  XQUF - ACTUALIZACIONES                                 │
├─────────────────────────────────────────────────────────┤
│  insert node <n> into/after/before <dest>              │
│  delete node <expr>                                    │
│  replace node <n> with <nuevo>   (nodo completo)       │
│  replace value of <n> with <val> (solo contenido)      │
│  rename node <n> as <nombre>                            │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  TIPOS DE DATOS XQUERY                                  │
├─────────────────────────────────────────────────────────┤
│  xs:integer · xs:decimal · xs:string                   │
│  xs:date · xs:dateTime · xs:boolean                    │
│  item()* (0+) · item()+ (1+) · item()? (0-1)           │
└─────────────────────────────────────────────────────────┘
```

---

## 🎯 DECISIONES RÁPIDAS

| Necesitas... | Solución |
|--------------|----------|
| Consultar XML como si fuera SQL | **XQuery FLWOR** |
| Iterar sobre nodos | **FOR** |
| Asignar sin iterar | **LET** |
| Modificar XML (CRUD) | **XQUF** |
| Base de datos XML para aprender | **BaseX** |
| Procesador Java completo | **Saxon** |
| Búsqueda insensible a mayúsculas | `lower-case()` + `contains()` |
| Validar formato (ISBN, etc.) | `matches()` con regex |
| JOIN entre documentos | `for + let + where exists()` |
| Else vacío en if-then | `else ()` |

---

**✨ Recordatorios Clave:**
- **XQuery = XPath + FLWOR + Funciones**
- **FOR itera, LET asigna**
- **ELSE obligatorio** en if-then-else
- **BaseX** para aprender, **Saxon** para producción Java
