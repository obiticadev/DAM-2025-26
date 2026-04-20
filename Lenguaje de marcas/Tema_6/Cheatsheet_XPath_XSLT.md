# <a id="indice"></a> Cheatsheet Definitivo: XPath & XSLT

## 📚 Índice Rápido

1. [Flujo de Trabajo](#sec1)
2. [XPath - Navegación](#sec2)
3. [XSLT - Estructura Base](#sec3)
4. [Plantillas Avanzadas](#sec4)
5. [Formatos de Salida](#sec5)
6. [DOM vs SAX](#sec6)
7. [Patrones Rápidos](#sec7)
8. [Ejemplo Completo](#sec8)
9. [Decisiones Rápidas](#sec9)

---

## <a id="sec1"></a> ⚡ FLUJO DE TRABAJO

[Volver al Índice ↑](#indice)

```
XML Fuente ──┬──► XPath (selecciona nodos)
             │
Hoja XSLT ───┴──► XSLT Engine ──► HTML / XML / Text / PDF
```

---

## <a id="sec2"></a> 🎯 XPATH: Navegación en XML

[Volver al Índice ↑](#indice)

### Sintaxis de Ruta

| Sintaxis | Significado | Ejemplo |
|----------|-------------|---------|
| `/` | Raíz del documento | `/libreria` |
| `//` | Cualquier descendiente | `//libro` |
| `.` | Nodo actual | `.` |
| `..` | Nodo padre | `..` |
| `@` | Atributo | `@id`, `@precio` |
| `*` | Cualquier elemento | `/*`, `libro/*` |

### Ejes (Expresiones Completas)

| Abreviatura | Eje Completo | Descripción |
|-------------|--------------|-------------|
| `child::` | (por defecto) | Hijo directo |
| `parent::` | `..` | Nodo padre |
| `descendant::` | `//` | Todos los descendientes |
| `attribute::` | `@` | Atributos del nodo |
| `self::` | `.` | Nodo mismo |

### Predicados `[]` - Filtros

```xpath
libro[1]                    ► Primero elemento
libro[last()]               ► Último elemento
libro[@id='001']            ► Filtrar por atributo
libro[precio > 20]          ► Filtrar numérico
libro[position() mod 2 = 0] ► Elementos pares
```

> 🔴 **ÍNDICE 1-BASED**: XPath empieza en `[1]`, NO en `[0]`

### Funciones XPath Clave

| Categoría | Funciones |
|-----------|-----------|
| **Cadenas** | `string()`, `concat()`, `contains()`, `starts-with()`, `substring()`, `string-length()` |
| **Numéricas** | `count()`, `sum()`, `round()`, `floor()`, `ceiling()` |
| **Posición** | `last()`, `position()` |
| **Booleanas** | `not()`, `true()`, `false()` |

---

## <a id="sec3"></a> 🔄 XSLT: Estructura Base

[Volver al Índice ↑](#indice)

### Plantilla Mínima

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:output method="html" encoding="UTF-8" indent="yes"/>
  
  <xsl:template match="/">
    <!-- Punto de entrada -->
  </xsl:template>
  
</xsl:stylesheet>
```

### Instrucciones Esenciales

| Etiqueta | Función | Cuándo Usar |
|----------|---------|-------------|
| `<xsl:value-of select="ruta"/>` | Extrae valor texto | Mostrar datos |
| `<xsl:apply-templates/>` | Delega a plantillas | Reutilizar lógica |
| `<xsl:for-each select="ruta">` | Bucle iterativo | Listas simples |
| `<xsl:sort select="campo"/>` | Ordena nodos | Dentro de for-each/apply |
| `<xsl:if test="condicion">` | Condición simple | Un solo caso |
| `<xsl:choose>` | Switch/case | Múltiples casos |
| `<xsl:when test="cond">` | Caso del choose | — |
| `<xsl:otherwise>` | Default del choose | — |

### Ejemplo Choose

```xml
<xsl:choose>
  <xsl:when test="precio &lt; 10">Económico</xsl:when>
  <xsl:when test="precio &lt; 30">Medio</xsl:when>
  <xsl:otherwise>Premium</xsl:otherwise>
</xsl:choose>
```

---

## <a id="sec4"></a> 🛠️ PLANTILLAS AVANZADAS

[Volver al Índice ↑](#indice)

### Prioridad de Plantillas

| Patrón | Prioridad | Ejemplo |
|--------|-----------|---------|
| Predicado / Camino | **0.5** | `libro[@id]`, `cat/libro` |
| Nombre simple | **0.0** | `libro` |
| Wildcard / Raíz | **-0.5** | `*`, `/` |

**Regla**: Más específico gana.

### Modos (Reusar Plantillas)

```xml
<!-- Plantilla normal -->
<xsl:template match="libro">
  <p><xsl:value-of select="titulo"/></p>
</xsl:template>

<!-- Plantilla modo resumen -->
<xsl:template match="libro" mode="resumen">
  <li><xsl:value-of select="titulo"/></li>
</xsl:template>

<!-- Llamada -->
<xsl:apply-templates select="libro" mode="resumen"/>
```

### Variables y Parámetros

```xml
<!-- Variable: inmutable -->
<xsl:variable name="iva" select="0.21"/>
<xsl:value-of select="precio * $iva"/>

<!-- Parámetro: pasa argumentos -->
<xsl:param name="moneda" select="'EUR'"/>
```

### Importar/Incluir

| Instrucción | Prioridad | Uso |
|-------------|-----------|-----|
| `<xsl:include href="file.xsl"/>` | Misma prioridad | Modularizar |
| `<xsl:import href="file.xsl"/>` | Menor prioridad | Sobrescribible |

---

## <a id="sec5"></a> 📤 FORMATOS DE SALIDA

[Volver al Índice ↑](#indice)

| `method` | Uso | Características |
|----------|-----|-----------------|
| `html` | Páginas web | Salida directa para navegadores |
| `xml` | Pipeline datos | Reestructurar, filtrar |
| `text` | CSV, código | Solo valores, sin etiquetas |

### Para PDF (vía XSL-FO)

```
XML ──► XSLT ──► XSL-FO ──► Procesador FO (Apache FOP) ──► PDF
```

---

## <a id="sec6"></a> 🔧 DOM vs SAX (Cuando XSLT no alcanza)

[Volver al Índice ↑](#indice)

| Característica | DOM | SAX |
|----------------|-----|-----|
| **Memoria** | Carga TODO en RAM | Streaming línea a línea |
| **Acceso** | Aleatorio (árbol) | Solo hacia adelante |
| **Modificación** | ✅ Sí lee/escribe | ❌ Solo lectura |
| **Archivos grandes** | ❌ No apto | ✅ Ideal |
| **Cuándo usar** | Editor, modificaciones | Procesar GBs de datos |

---

## <a id="sec7"></a> 🧠 PATRONES RÁPIDOS

[Volver al Índice ↑](#indice)

### Tabla HTML desde XML

```xml
<table>
  <xsl:for-each select="libros/libro">
    <xsl:sort select="precio" order="descending"/>
    <tr>
      <td><xsl:value-of select="titulo"/></td>
      <td><xsl:value-of select="precio"/></td>
    </tr>
  </xsl:for-each>
</table>
```

### Atributos Condicionales

```xml
<div>
  <xsl:if test="@destacado='true'">
    <xsl:attribute name="class">destacado</xsl:attribute>
  </xsl:if>
  <xsl:value-of select="titulo"/>
</div>
```

### Selección Múltiples Nodos

```xml
<xsl:apply-templates select="libro|revista|periodico"/>
```

---

## ⚡ CHEATSHEET VISUAL

```
┌─────────────────────────────────────────────────────────┐
│  XPATH RUTAS                                            │
├─────────────────────────────────────────────────────────┤
│  /raiz/item        →  Hijo directo de raiz               │
│  //item            →  Item en cualquier nivel          │
│  item[1]           →  Primer item                        │
│  item[@id]         →  Items con atributo id              │
│  item[@id='x']     →  Items con id='x'                   │
│  item[precio>10]   →  Items con precio mayor que 10      │
└─────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  XSLT PLANTILLAS                                        │
├─────────────────────────────────────────────────────────┤
│  <xsl:template match="/">      →  Punto entrada        │
│  <xsl:value-of select="x"/>    →  Extraer valor        │
│  <xsl:for-each select="x">     →  Bucle                │
│  <xsl:if test="cond">          →  Condición simple     │
│  <xsl:apply-templates/>        →  Delegar procesamiento │
│  <xsl:sort select="campo"/>    →  Ordenar              │
└─────────────────────────────────────────────────────────┘
```

---

## <a id="sec8"></a> 📝 ESTRUCTURA XSLT COMPLETA (Ejemplo)

[Volver al Índice ↑](#indice)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" indent="yes"/>

  <!-- Plantilla raíz -->
  <xsl:template match="/">
    <html>
      <body>
        <xsl:apply-templates select="catalogo"/>
      </body>
    </html>
  </xsl:template>

  <!-- Plantilla catalogo -->
  <xsl:template match="catalogo">
    <h1>Catálogo</h1>
    <table>
      <xsl:for-each select="libro">
        <xsl:sort select="precio"/>
        <tr>
          <td><xsl:value-of select="titulo"/></td>
          <td><xsl:value-of select="precio"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </xsl:template>

</xsl:stylesheet>
```

---

## <a id="sec9"></a> 🎯 DECISIONES RÁPIDAS

[Volver al Índice ↑](#indice)

| Necesitas... | Solución |
|--------------|----------|
| Seleccionar nodos | **XPath** |
| Transformar a HTML | **XSLT** + `method="html"` |
| Transformar a XML | **XSLT** + `method="xml"` |
| Archivos enormes | **SAX** |
| Modificar XML | **DOM** |
| Reusar plantillas | **`mode`** o **`xsl:include`** |
| Condicionales | **`xsl:if`** o **`xsl:choose`** |
| Ordenar resultados | **`xsl:sort`** |

---

**✨ Recordatorio Final:**
- XPath = **SQL para XML** (selecciona datos)
- XSLT = **Motor de vistas** (transforma datos)
- Usa `apply-templates` para flexibilidad, `for-each` para simplicidad
