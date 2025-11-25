## 1. Fundamentos y Arquitectura

### ¿Qué es CSS?
**CSS (Cascading Style Sheets)** es el lenguaje de diseño para definir la visualización visual de documentos HTML. Sus principales ventajas técnicas son:
*   **Eficiencia:** Reutilización de estilos en múltiples páginas (DRY - Don't Repeat Yourself).
*   **Performance:** Carga más rápida al separar estructura (HTML) de presentación.
*   **Mantenibilidad:** Propagación centralizada de cambios.
*   **Compatibilidad:** Adaptación visual a distintos dispositivos (Responsive) sin cambiar el HTML.

### Anatomía de una Regla (Rule Set)
Una regla CSS se compone de un selector y un bloque de declaración.

*   **Sintaxis:** `selector { property: value; }`
*   **Selector:** El elemento HTML objetivo.
*   **Property:** El atributo visual a cambiar.
*   **Value:** El parámetro asignado.

```css
/* Ejemplo de sintaxis con múltiples propiedades */
h1 { 
    color: #36C;          /* Property: Value */
    font-weight: normal;  
    letter-spacing: .4em;
}
```

### Métodos de Integración
Ordenados de menor a mayor recomendación técnica.

1.  **Inline CSS (En línea):**
    *   Usa el atributo `style` dentro de la etiqueta HTML.
    *   **Desventaja:** Difícil de mantener, mezcla contenido y presentación.
    ```html
    <p style="color:red; font-size:20px;">Texto rojo inline</p>
    ```

2.  **Internal CSS (Interno):**
    *   Se define dentro de `<style>` en el `<head>` del documento.
    *   **Alcance:** Solo afecta a la página actual.
    ```html
    <head>
      <style>
        .red { color: red; }
      </style>
    </head>
    ```

3.  **External CSS (Externo) - **Estándar Profesional**:**
    *   Archivo independiente `.css` vinculado mediante `<link>`.
    *   **Ventaja:** Cachéable por el navegador y reutilizable en todo el sitio.
    ```html
    <link rel="stylesheet" type="text/css" href="/html/style.css">
    ```

---

## 2. Sistema de Unidades de Medida

Es crucial entender la diferencia entre unidades relativas y absolutas para el diseño responsivo.

### Unidades Relativas (Recomendadas para pantallas)
Dependen de otro valor (como el tamaño de letra del padre o el tamaño de la ventana).

| Unidad | Descripción Técnica | Referencia |
| :--- | :--- | :--- |
| **%** | Porcentaje relativo al valor de la propiedad del elemento padre. | Padre directo |
| **em** | Relativo al tamaño de fuente (`font-size`) del elemento. <br>**Nota:** `2em` = 2 veces el tamaño de fuente actual. | Font-size actual/padre |
| **ex** | Relativo a la altura de la letra "x" minúscula de la fuente actual (x-height). | Altura 'x' de la fuente |
| **vw** | 1% del ancho del viewport (ventana del navegador). | Viewport Width |
| **vh** | 1% de la altura del viewport. | Viewport Height |
| **vmin** | Toma el valor menor entre `vw` y `vh`. | Menor dimensión Viewport |

### Unidades Absolutas (Comunes en impresión)
Son medidas fijas que no escalan automáticamente.

| Unidad | Descripción Técnica | Equivalencia |
| :--- | :--- | :--- |
| **px** | Píxeles de pantalla (unidad "mágica" en CSS, varía según densidad). | Pantalla |
| **pt** | Puntos tipográficos. | 1pt = 1/72 pulgada |
| **pc** | Picas. | 1pc = 12pt |
| **cm** | Centímetros. | Físico |
| **mm** | Milímetros. | Físico |
| **in** | Pulgadas (Inches). | 1in = 96px = 2.54cm |

---

## 3. Selectores: La Guía Completa

Patrones para "apuntar" a elementos HTML específicos.

### Selectores Básicos
*   **Universal Selector (`*`):** Selecciona **todos** los elementos. Útil para resets globales.
    ```css
    * { margin: 0; padding: 0; }
    ```
*   **Element Selector (`tag`):** Selecciona todas las instancias de una etiqueta.
    ```css
    h1 { color: #36CFFF; }
    ```
*   **Class Selector (`.class`):** Selecciona elementos con el atributo `class="nombre"`. Reutilizable.
    ```css
    .black { color: #000000; }
    /* Específico: solo etiquetas h1 con clase black */
    h1.black { color: #000000; } 
    ```
*   **ID Selector (`#id`):** Selecciona el elemento con el atributo `id="nombre"`. Debe ser **único** por página.
    ```css
    #error { color: #ff0000; }
    ```

### Selectores de Combinación (Jerarquía)
*   **Descendant Selector (`A B`):** Selecciona `B` si está **dentro** de `A` (en cualquier nivel de profundidad).
    ```css
    ul em { color: green; } /* Selecciona <em> dentro de <ul> */
    ```
*   **Child Selector (`A > B`):** Selecciona `B` solo si es **hijo directo** de `A`.
    ```css
    body > p { color: #000000; }
    ```
*   **Adjacent Sibling Selector (`A + B`):** Selecciona `B` solo si está **inmediatamente después** de `A` (y comparten padre).
    ```css
    h1 + p { color: blue; } /* Solo el primer <p> tras el <h1> */
    ```
*   **General Sibling Selector (`A ~ B`):** Selecciona todos los `B` que estén después de `A` (hermanos).
    ```css
    h2 ~ p { font-style: italic; } /* Todos los <p> tras el <h2> */
    ```

### Selectores Avanzados y Agrupación
*   **Attribute Selector (`[attr]`):** Selecciona elementos basándose en la presencia o valor de un atributo.
    ```css
    input[type="text"] { border: 1px solid red; }
    input[type="submit"] { border: 1px solid green; }
    ```
*   **Grouping Selectors:** Aplica el mismo estilo a varios selectores separados por coma.
    ```css
    h1, h2, h3 { color: #36C; font-weight: normal; }
    ```

***

## 4. El Modelo de Caja (Box Model)

Concepto fundamental: **Cada elemento HTML es una caja rectangular**. El diseño web consiste en configurar las propiedades de estas cajas e interactuar entre ellas.

### Anatomía de la Caja (De adentro hacia afuera)
1.  **Content Area:** Donde aparecen texto e imágenes. (Definido por `width` y `height`).
2.  **Padding:** Espacio transparente **entre** el contenido y el borde.
3.  **Border:** Línea que rodea el padding y el contenido.
4.  **Margin:** Espacio transparente **externo** fuera del borde. Separa este elemento de sus vecinos.

### Cálculo del Tamaño Real (Importante)
Las propiedades `width` y `height` solo definen el **Área de Contenido**. Para saber cuánto espacio ocupa realmente el elemento en la pantalla, debes sumar todo:

*   **Ancho Total** = `width` + `padding-left/right` + `border-left/right` + `margin-left/right`
*   **Alto Total** = `height` + `padding-top/bottom` + `border-top/bottom` + `margin-top/bottom`

### Propiedades de Espaciado
*   **Padding:** Relleno interno.
*   **Margin:** Espaciado externo. Permite valores `auto` (útil para centrar horizontalmente: `margin: 0 auto;`).

**Sintaxis Shorthand (Regla del Reloj):**
Al definir 4 valores, el orden es: **Top → Right → Bottom → Left**.
```css
.box {
    /* Top: 25px, Right: 50px, Bottom: 75px, Left: 100px */
    padding: 25px 50px 75px 100px;
    
    /* Si das 1 valor: aplica a los 4 lados */
    margin: 20px; 
    
    /* Si das 2 valores: Vertical (Top/Bottom) | Horizontal (Left/Right) */
    margin: 10px 20px;
}
```

### Border Style
Define el tipo de línea del borde.
*   **Valores:** `solid`, `dashed` (líneas), `dotted` (puntos), `double` (doble línea), `groove`, `ridge`, `inset`, `outset` (efectos 3D).

```css
div {
    border-width: 5px;
    border-style: solid;
    border-color: black;
    /* Shorthand: width style color */
    border: 5px solid black; 
}
```

---

## 5. Tipografía y Texto

Controla la apariencia de la letra y la alineación de los párrafos.

### Fuentes (Fonts)
*   **Font Family:** Lista de prioridades. Si el navegador no tiene la primera, prueba la siguiente.
    *   **Tip:** Termina siempre con una familia genérica (`serif`, `sans-serif`, `monospace`).
    ```css
    body { font-family: Arial, Helvetica, sans-serif; }
    ```
*   **Font Style:** Define la inclinación.
    *   `normal`
    *   `italic` (cursiva de la fuente)
    *   `oblique` (versión inclinada artificialmente)
*   **Font Size:** Tamaño de la letra.
    *   `px`: Absoluto.
    *   `em`: Relativo al tamaño de fuente actual. `2em` = doble del tamaño actual.
    *   `%`: Porcentaje relativo al padre.
*   **Font Weight:** Grosor del trazo.
    *   Palabras clave: `normal`, `bold`, `bolder`, `lighter`.
    *   Numérico: `100` a `900` (`400` es normal, `700` es bold).

### Formato de Texto (Text)
*   **Text Align:** Alineación horizontal del contenido.
    *   `center`, `left`, `right`, `justify`.
*   **Text Decoration:** Decoración de línea.
    *   `none` (quita el subrayado a los enlaces), `underline`, `overline`, `line-through`.
*   **Text Transform:** Modificación de mayúsculas/minúsculas.
    *   `uppercase` (TODO MAYÚSCULAS), `lowercase`, `capitalize` (Primera Letra Mayúscula).
*   **Text Indent:** Sangría de la **primera línea** de un párrafo.
    ```css
    p { text-indent: 100px; }
    ```

---

## 6. Fondos (Backgrounds)

Propiedades para manejar el color y las imágenes de fondo de los elementos.

*   **Background Color:** Define un color de fondo sólido.
    ```css
    body { background-color: #f0e68c; }
    ```
*   **Background Image:** Establece una imagen.
    ```css
    /* Ruta relativa o absoluta */
    body { background-image: url("/examples/images/tile.png"); }
    ```
*   **Background Repeat:** Controla si la imagen se repite (mosaico).
    *   `repeat`: Se repite en X e Y (por defecto).
    *   `no-repeat`: Solo una vez.
    *   `repeat-x`: Solo horizontal.
    *   `repeat-y`: Solo vertical.
*   **Background Position:** Ubicación inicial de la imagen.
    *   Keywords: `top`, `bottom`, `center`, `left`, `right`.
    *   Coordenadas: `100% top`, `200px 50px`.
*   **Background Attachment:** Define si el fondo se mueve al hacer scroll.
    *   `scroll`: La imagen se mueve con el contenido (default).
    *   `fixed`: La imagen se queda **fija** en la ventana mientras el texto se mueve encima.

```css
/* Ejemplo completo */
body {
    background-image: url("texture.png");
    background-repeat: no-repeat;
    background-position: center;
    background-attachment: fixed;
}
```

***

## 7. Listas (Lists)

Personalización de las viñetas en listas desordenadas (`<ul>`) y numeradas (`<ol>`).

### Tipos de Marcador
*   `list-style-type`: Cambia el símbolo o numeración.
    *   Valores `<ul>`: `disc` (defecto), `circle`, `square`, `none` (quita la viñeta).
    *   Valores `<ol>`: `decimal` (1, 2, 3), `upper-roman` (I, II, III), `lower-alpha` (a, b, c).

### Imágenes como Marcador
*   `list-style-image`: Reemplaza el punto por una imagen personalizada.

```css
ul {
    /* Usa un cuadrado como viñeta */
    list-style-type: square; 
}
li.custom {
    /* Usa una imagen propia */
    list-style-image: url("/images/bullet.png");
}
```

---

## 8. Visualización y Flujo (Display vs Visibility)

Es vital distinguir cómo se comportan los elementos en el flujo del documento y cómo ocultarlos.

### Propiedad `display`
Cambia el comportamiento de la caja ("Caja" = elemento HTML).

*   **`block`**: El elemento ocupa todo el ancho disponible y fuerza un salto de línea (ej: `<div>`, `<p>`).
*   **`inline`**: Solo ocupa el ancho de su contenido. **No acepta** width, height ni márgenes verticales (ej: `<span>`, `<a>`).
*   **`inline-block`**: Híbrido. Fluye en línea con el texto, pero **sí acepta** width, height y márgenes (ej: botones, imágenes).
*   **`none`**: Elimina el elemento del documento. **No ocupa espacio**, es como si no existiera en el HTML.

### Propiedad `visibility`
Controla si se ve o no, pero afecta al espacio reservado.

*   **`visible`**: Valor por defecto.
*   **`hidden`**: El elemento es invisible, pero **sigue ocupando su espacio físico** en la maquetación (queda un hueco vacío).
*   **`collapse`**: Específico para tablas. Oculta una fila/columna completa.

---

## 9. Posicionamiento (Position & Float)

### Tipos de Posicionamiento (`position`)
Define cómo se ubica un elemento mediante coordenadas (`top`, `bottom`, `left`, `right`).

1.  **`static` (Default):** Flujo natural. Las coordenadas `top/left` **no tienen efecto**.
2.  **`relative`**: Se desplaza respecto a su **posición original**. El hueco original se mantiene preservado.
3.  **`absolute`**:
    *   Se elimina del flujo normal (no ocupa espacio).
    *   Se posiciona relativo al **ancestro posicionado más cercano** (cualquier padre que no sea `static`).
    *   Si no hay padres posicionados, se ubica respecto al documento (`body`).
4.  **`fixed`**:
    *   Se posiciona respecto a la **ventana del navegador (Viewport)** (0,0 es la esquina superior izquierda de la pantalla).
    *   No se mueve al hacer scroll.

### Capas (`z-index`)
Controla la profundidad (eje Z).
*   Solo funciona en elementos con `position` (`relative`, `absolute`, `fixed`).
*   Mayor número = más arriba (tapa a los inferiores).
*   Admite valores negativos.

```css
.caja-flotante {
    position: absolute;
    top: 50px;
    left: 100px;
    z-index: 10; /* Encima de todo */
}
```

### Flotantes (`float` y `clear`)
Herramienta clásica para alinear imágenes o crear columnas.
*   **`float: left/right`**: Saca el elemento del flujo y lo empuja al extremo. El texto "envuelve" al elemento flotado.
*   **`clear: left/right/both`**: Impide que un elemento tenga elementos flotantes a su lado. Restaura el flujo normal debajo del flotante.

---

## 10. Pseudo-elementos y Pseudo-clases

Selectores especiales para estados dinámicos o partes específicas del contenido.

### Pseudo-elementos (`::`)
Crean o estilizan "partes fantasma" del elemento.
*   `::first-line`: Aplica estilo solo a la primera línea de texto.
*   `::first-letter`: Aplica estilo a la letra capital.
*   `::before` y `::after`: Insertan contenido antes o después del elemento. **Obligatorio usar la propiedad `content`.**

```css
/* Añade un icono antes de cada h1 */
h1::before {
    content: url("icon.png"); 
    margin-right: 5px;
}
```

### Pseudo-clases (`:`)
Responden a eventos o estructura.

**Interacción (Links/Inputs):**
*   `:link`: Enlace no visitado.
*   `:visited`: Enlace ya visitado.
*   `:hover`: Cuando el ratón pasa por encima.
*   `:active`: Mientras se hace clic (botón presionado).
*   `:focus`: Cuando el elemento está seleccionado (ej. cursor en un input).

**Estructurales (Árbol DOM):**
*   `:first-child`: El elemento es el **primer hijo** de su padre.
*   `:last-child`: El elemento es el **último hijo** de su padre.
*   `:nth-child(n)`: Selecciona hijos específicos mediante fórmulas.
    *   `nth-child(2)`: El segundo hijo.
    *   `nth-child(odd)`: Impares (1, 3, 5...).
    *   `nth-child(2n)`: Pares.

```css
/* Pinta de gris las filas pares de una tabla (efecto cebra) */
table tr:nth-child(even) {
    background-color: #f2f2f2;
}
```