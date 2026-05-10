# 📘 Resumen Ultrarrápido — XSLT

> **Tiempo de lectura:** 12 minutos  
> **Objetivo:** dominar XSLT para transformar XML en HTML, CSV o XML

---

## 1. ¿Qué es XSLT?

XSLT transforma un XML de datos en **otro formato** (HTML, texto, XML...).

```mermaid
flowchart LR
    XML["📄 XML de datos"]
    XSL["🎨 Hoja XSLT"]
    PROC["⚙️ Procesador XSLT"]
    OUT["📋 Resultado"]

    XML --> PROC
    XSL --> PROC
    PROC --> OUT

    style XML fill:#1565c0,color:#fff
    style XSL fill:#e65100,color:#fff
    style PROC fill:#2e7d32,color:#fff
    style OUT fill:#6a1b9a,color:#fff
```

---

## 2. Anatomía de una Hoja XSLT

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="html" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <!-- Punto de entrada: aquí empieza todo -->
  </xsl:template>

</xsl:stylesheet>
```

```mermaid
flowchart TD
    A["xsl:stylesheet"] --> B["xsl:output method=html/xml/text"]
    A --> C["xsl:template match='/'"]
    C --> D["Tu HTML/XML de salida"]
    C --> E["xsl:apply-templates"]
    A --> F["xsl:template match='libro'"]
    F --> G["Genera contenido por cada libro"]

    style A fill:#e65100,color:#fff
    style C fill:#1565c0,color:#fff
    style F fill:#1565c0,color:#fff
```

---

## 3. Instrucciones Clave

| Instrucción | Qué hace |
|-------------|----------|
| `xsl:value-of select="ruta"` | Extrae el texto de un nodo |
| `xsl:apply-templates` | Activa el procesamiento de los hijos |
| `xsl:apply-templates select="ruta"` | Solo para ciertos nodos |
| `xsl:for-each select="ruta"` | Itera sobre los nodos |
| `xsl:sort select="campo"` | Ordena (dentro de for-each o apply-templates) |
| `xsl:if test="condición"` | Condicional simple (sin else) |
| `xsl:choose / when / otherwise` | Condicional múltiple (switch) |
| `xsl:variable name="x" select="..."` | Variable inmutable |
| `xsl:param name="x" select="..."` | Parámetro (sobreescribible desde fuera) |
| `xsl:call-template name="nombre"` | Llama a una plantilla nombrada |
| `xsl:text` | Emite texto literal preservando espacios |
| `xsl:element name="..."` | Crea un elemento con nombre dinámico |
| `xsl:attribute name="..."` | Crea un atributo dinámico |

---

## 4. for-each + sort

```mermaid
flowchart TD
    FE["xsl:for-each select='catalogo/libro'"]
    S1["xsl:sort select='precio' data-type='number' order='descending'"]
    BODY["Para cada libro ordenado: genera HTML"]

    FE --> S1
    S1 --> BODY

    style FE fill:#1565c0,color:#fff
    style S1 fill:#ff9800,color:#000
    style BODY fill:#4caf50,color:#fff
```

**Atributos de xsl:sort:**

| Atributo | Valores | Cuidado |
|----------|---------|---------|
| `select` | Expresión XPath | Campo por el que ordenar |
| `order` | `ascending` / `descending` | Defecto: ascending |
| `data-type` | `text` / `number` | ⚠️ Sin `number`, ordena como texto |

---

## 5. Condicionales

```mermaid
flowchart TD
    IF["xsl:if test='salario > 3000'"]
    IF -->|true| SHOW["Muestra (Senior)"]
    IF -->|false| NADA["No genera nada"]

    CH["xsl:choose"]
    CH --> W1["xsl:when test='salario > 3000' → Senior"]
    CH --> W2["xsl:when test='salario > 2000' → Medio"]
    CH --> OT["xsl:otherwise → Junior"]

    style IF fill:#ff9800,color:#000
    style CH fill:#9c27b0,color:#fff
```

> ⚠️ `xsl:if` NO tiene else. Si necesitas else → usa `xsl:choose`.

---

## 6. Variables y Parámetros

```xml
<!-- Variable: inmutable, se referencia con $ -->
<xsl:variable name="iva" select="0.21"/>
<xsl:value-of select="precio * (1 + $iva)"/>

<!-- Parámetro: tiene valor por defecto, sobreescribible -->
<xsl:param name="precioLimite" select="30"/>
```

> ⚠️ En atributos XSLT: `<` se escribe `&lt;` y `<=` se escribe `&lt;=`

---

## 7. Plantillas con mode

```mermaid
flowchart LR
    ROOT["template match='/'"]
    ROOT -->|"mode=resumen"| RES["template match='libro' mode='resumen'"]
    ROOT -->|"mode=detalle"| DET["template match='libro' mode='detalle'"]
    RES --> LI["Genera solo titulo en li"]
    DET --> ART["Genera titulo + autor + precio"]

    style ROOT fill:#e65100,color:#fff
    style RES fill:#1565c0,color:#fff
    style DET fill:#2e7d32,color:#fff
```

Mismo nodo, presentación distinta según el modo.

---

## 8. Plantillas Nombradas

```xml
<!-- Se define con name="" -->
<xsl:template name="cabecera">
  <header><h1>Mi App</h1></header>
</xsl:template>

<!-- Se llama explícitamente -->
<xsl:call-template name="cabecera"/>
```

| | `match="libro"` | `name="cabecera"` |
|-|------------------|-------------------|
| Se activa | Automáticamente al llegar a `<libro>` | Solo con `call-template` |
| Uso | Procesar nodos concretos | Reutilizar bloques de HTML |

---

## 9. Formatos de Salida

| `method` | Para qué | Clave |
|----------|----------|-------|
| `html` | Páginas web | Optimiza etiquetas HTML |
| `xml` | Otro XML | Mantiene declaración XML |
| `text` | CSV, texto plano | Solo emite texto, sin etiquetas |

Para CSV usa `xsl:text` y `&#10;` (salto de línea).

---

## 10. Elementos y Atributos Dinámicos

```xml
<!-- Valor de atributo con {} (AVT) -->
<div class="{categoria}">...</div>

<!-- Atributo dinámico con xsl:attribute -->
<article>
  <xsl:attribute name="class">agotado</xsl:attribute>
</article>
```

> ⚠️ `xsl:attribute` SIEMPRE antes del contenido del elemento.

---

## 11. Chuleta de Supervivencia

```
¿Quiero iterar?              → xsl:for-each select="..."
¿Quiero ordenar?             → xsl:sort select="..." (dentro del for-each)
¿Quiero un if simple?        → xsl:if test="..."
¿Quiero if/else?             → xsl:choose + xsl:when + xsl:otherwise
¿Quiero guardar un valor?    → xsl:variable name="x" select="..."
¿Quiero un parámetro?        → xsl:param name="x" select="..."
¿Quiero reutilizar HTML?     → xsl:template name="..." + xsl:call-template
¿Quiero dos vistas?          → mode="resumen" / mode="detalle"
¿Quiero CSV?                 → method="text" + xsl:text + &#10;
```

---

*Resumen basado en Bloques 6-13 de la guía teórica · Lenguaje de Marcas T6*
