### 1. Introducción a CSS

**CSS** (*Cascade Style Sheet* u Hojas de Estilo en Cascada) es un lenguaje de diseño que se utiliza para definir el aspecto y la presentación de una página web. Separar el contenido (HTML) de la presentación (CSS) ofrece múltiples ventajas.

*   **Ahorro de tiempo:** Se definen los estilos una vez y se pueden reutilizar en múltiples páginas.
*   **Carga rápida:** Al separar los estilos, los navegadores pueden cachear el archivo CSS, lo que acelera la carga de las páginas.
*   **Mantenimiento sencillo:** Modificar un estilo en un único archivo CSS propaga el cambio a todas las páginas que lo utilizan.
*   **Estilos más completos:** CSS ofrece muchas más propiedades y posibilidades de diseño que los antiguos atributos de estilo de HTML.
*   **Compatibilidad entre dispositivos:** Permite adaptar la visualización de una misma página HTML a diferentes dispositivos (escritorio, tablet, móvil) simplemente cambiando la hoja de estilos.

### 2. Sintaxis de CSS

Las hojas de estilo CSS se componen de **reglas de estilo**, que siguen una sintaxis específica para aplicar formato a los elementos HTML.

#### Reglas de Estilo
Cada regla consta de tres partes fundamentales:

*   **Selector:** Apunta al elemento o elementos HTML a los que se les aplicará el estilo.
*   **Propiedad (Property):** Es el atributo de estilo que se desea modificar (ej: `color`, `font-size`, `border`).
*   **Valor (Value):** Es el valor que se asigna a la propiedad.

La estructura general es:
```css
selector {
  propiedad: valor;
}
```

**Ejemplo de una regla:**
```css
table {
  border: 1px solid #C00;
}
```
En este caso, `table` es el selector, `border` es la propiedad, y `1px solid #C00` son los valores asignados a esa propiedad.

#### Tipos de Selectores
Existen diferentes tipos de selectores para apuntar a los elementos con gran precisión:

| Selector | Ejemplo | Descripción |
| :--- | :--- | :--- |
| **Universal** | `* { color: #000; }` | Selecciona **todos** los elementos de la página. |
| **de Etiqueta/Elemento**| `h1 { color: #36CFFF; }`| Selecciona todos los elementos de un tipo específico (ej: todos los `<h1>`). |
| **Descendente** | `ul em { color: #000; }` | Selecciona un elemento que está dentro de otro (ej: un `<em>` dentro de un `<ul>`). |
| **de Clase** | `.clase { ... }` | Selecciona todos los elementos que tienen el atributo `class="clase"`. |
| | `h1.clase { ... }` | Selecciona solo los `<h1>` que tienen `class="clase"`. |
| **de ID** | `#identificador { ... }`| Selecciona el elemento único que tiene el atributo `id="identificador"`. |
| **Hijo Directo** | `body > p { ... }` | Selecciona los elementos que son hijos directos de otro (ej: un `<p>` que es hijo directo de `<body>`). |
| **Hermano Adyacente**| `h2 + p { ... }` | Selecciona el elemento que está inmediatamente después de otro (ej: un `<p>` justo después de un `<h2>`). |
| **de Atributo** | `input[type="text"] { ... }` | Selecciona elementos que tienen un atributo y valor específicos. |

#### Reglas con Múltiples Estilos y Agrupación
*   **Múltiples estilos:** Se pueden aplicar varias propiedades a un mismo selector, separándolas con punto y coma (`;`).

    ```css
    h1 {
      color: #36C;
      font-weight: normal;
      letter-spacing: .4em;
      margin-bottom: 1em;
      text-transform: lowercase;
    }
    ```

*   **Agrupación de selectores:** Para aplicar las mismas reglas a diferentes selectores, se pueden agrupar separándolos por comas (`,`).

    ```css
    h1, h2, h3 {
      color: #36C;
      font-weight: normal;
      text-transform: lowercase;
    }

    #content, #footer, #supplement {
      position: absolute;
      left: 510px;
      width: 200px;
    }
    ```

### 3. Unidades de Medida en CSS

CSS utiliza diversas unidades para definir tamaños, distancias y colores.

| Unidad | Descripción |
| :--- | :--- |
| **`%`** | Porcentaje relativo al valor de otro elemento (generalmente el padre). |
| **`cm` / `mm` / `in`** | Unidades absolutas: centímetros, milímetros o pulgadas. |
| **`em`** | Unidad relativa al tamaño de la fuente del elemento actual. `2em` significa dos veces el tamaño de la fuente. |
| **`ex`** | Unidad relativa a la altura "x" de la fuente del elemento (la altura de la letra 'x' minúscula). |
| **`pc` / `pt`** | Unidades absolutas: picas (1pc = 12pt) y puntos (1pt = 1/72 de pulgada). |
| **`px`** | Píxeles. Es una unidad relativa al dispositivo de visualización. |
| **`vh`** | 1% de la altura del *viewport* (el área visible del navegador). |
| **`vw`** | 1% del ancho del *viewport*. |
| **`vmin`** | El valor más pequeño entre `1vw` o `1vh`. |


### 4. Métodos para Incluir CSS en HTML

Existen tres formas de aplicar las reglas de estilo CSS a un documento HTML.

#### 1. CSS en Línea (Inline CSS)
Consiste en utilizar el atributo `style` directamente dentro de una etiqueta HTML. Este método tiene la máxima prioridad, pero no es recomendable porque mezcla contenido y presentación.

```html
<p style="color:red; font-size:20px;">Este es un párrafo rojo y grande.</p>
```

#### 2. CSS Interno (Internal CSS)
Consiste en definir los estilos dentro de una etiqueta `<style>` en el `<head>` del documento HTML. Estos estilos solo se aplican a la página en la que están definidos.

```html
<!DOCTYPE html>
<html>
<head>
  <title>CSS Interno</title>
  <style type="text/css">
    .red {
      color: red;
    }
    .thick {
      font-size: 20px;
    }
  </style>
</head>
<body>
  <p class="red">Este es un párrafo rojo.</p>
  <p class="thick green">Este es un párrafo grueso y verde.</p>
</body>
</html>
```

#### 3. CSS Externo (External CSS)
Es el método más común y recomendado. Consiste en definir todos los estilos en un archivo separado con extensión `.css` y luego enlazarlo desde el `<head>` de las páginas HTML mediante la etiqueta `<link>`.

**Archivo `style.css`:**
```css
.red {
  color: red;
}
.thick {
  font-size: 20px;
}
.green {
  color: green;
}
```

**Archivo `index.html`:**
```html
<!DOCTYPE html>
<html>
<head>
  <title>CSS Externo</title>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <p class="red">Este es un párrafo rojo.</p>
  <p class="thick green">Este es un párrafo grueso y verde.</p>
</body>
</html>
```