### 3. Estructura Básica de un Documento HTML

**HTML** (*Hyper Text Markup Language* o Lenguaje de Marcas de Hipertexto) es el lenguaje estándar para crear y organizar el contenido de las páginas web. Fue creado en 1989 por Tim Berners-Lee como una particularización de SGML.

Un documento HTML es interpretado por el navegador, lo que significa que los errores se detectan durante la ejecución. Su estructura fundamental se compone de las siguientes etiquetas:

```html
<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Contenido del encabezado: metadatos, título, etc. -->
</head>
<body>
    <!-- Contenido visible de la página: texto, imágenes, etc. -->
</body>
</html>```

| Etiqueta | Descripción |
| :--- | :--- |
| **`<!DOCTYPE html>`**| Obligatoria. Define el tipo de documento y la versión de HTML (en este caso, HTML5). Debe ser la primera línea. |
| **`<html>`** | Etiqueta raíz que engloba todo el contenido del documento. El atributo `lang` define el lenguaje de la página. |
| **`<head>`** | Contiene los metadatos del documento, que no son visibles pero afectan a su funcionamiento y SEO. |
| **`<body>`** | Contiene todo el contenido visible de la página web (texto, imágenes, enlaces, etc.). |

#### Sintaxis y Reglas de Estilo en HTML5

HTML5 es muy permisivo, pero para mantener un código limpio y profesional, se recomiendan las siguientes pautas:

*   **Usar minúsculas:** Escribir todas las etiquetas y atributos en minúsculas.
*   **Comillas en atributos:** Usar siempre comillas dobles para los valores de los atributos (ej: `href="pagina.html"`).
*   **Cerrar etiquetas:** Aunque no siempre es obligatorio, es una buena práctica cerrar las etiquetas sin contenido con una barra (ej: `<br />`, `<hr />`).
*   **Sangrado (Indentación):** Utilizar el sangrado para que el código sea más legible y muestre claramente la jerarquía de los elementos.

### 4. El Encabezado `<head>` y sus Metadatos

La etiqueta `<head>` contiene información clave para el navegador y los motores de búsqueda.

| Etiqueta | Atributo | Descripción |
| :--- | :--- | :--- |
| **`<title>`** | - | Define el título del documento, que aparece en la pestaña del navegador. Solo puede contener texto. |
| **`<meta>`** | `charset` | Define el juego de caracteres del documento. **`UTF-8`** es el estándar recomendado. |
| | `name`/`content`| Define metadatos como `author`, `description`, `keywords` y `viewport`. |
| | `http-equiv` | Utilizado por los servidores para obtener información sobre los encabezados de respuesta (ej: `Expires`).|
| **`<base>`** | `href`/`target` | Define una URL base para todos los enlaces relativos de la página y un comportamiento de apertura por defecto. |
| **`<link>`** | `href`/`rel` | Permite enlazar recursos externos, como hojas de estilo CSS (`rel="stylesheet"`). |

**Ejemplo de `<head>` completo:**
```html
<head>
    <meta charset="UTF-8" />
    <title>Título de la Página</title>
    <meta name="description" content="Ejemplo de etiquetas meta para mi página web.">
    <meta name="author" content="Mi Nombre">
    <meta name="keywords" content="html, desarrollo, web">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/estilos.css" rel="stylesheet" type="text/css" media="screen" />
</head>
```

### 5. Codificación de Caracteres (Charset)

La codificación de caracteres es el método que convierte un carácter de lenguaje natural en un símbolo que el ordenador puede interpretar. Estas "tablas" de conversión se llaman **charset**.

*   **ASCII:** Publicado en 1967, usa 7 bits para representar 128 caracteres. No incluye acentos, 'ñ' ni otros símbolos necesarios para el castellano.
*   **ISO-8859 (ASCII Extendido):** Usa 8 bits para representar 256 caracteres. Los primeros 128 coinciden con ASCII. Existen versiones regionales (ej: `ISO-8859-1` o `Latin-1` para Europa occidental).
*   **UNICODE:** Es el estándar actual, diseñado para recoger todos los caracteres de todos los alfabetos en una única tabla. La forma de codificación más común es **UTF-8**, que es la recomendada para todos los documentos HTML.

### 6. Cabeceras, Párrafos y Estructura de Texto

Estas etiquetas se utilizan para organizar el contenido principal dentro del `<body>`.

| Etiqueta | Descripción |
| :--- | :--- |
| **`<h1>` a `<h6>`** | Definen seis niveles de cabeceras o títulos, siendo `<h1>` el de mayor importancia y `<h6>` el de menor. |
| **`<p>`** | Estructura la información en párrafos. |
| **`<pre>`** | Muestra el texto preformateado, conservando espacios, tabulaciones y saltos de línea, ideal para mostrar código. |
| **`<br />`** | Fuerza un salto de línea. Es una etiqueta vacía (sin cierre). |
| **`<hr />`** | Dibuja una línea horizontal para separar contenido. También es una etiqueta vacía. |

**Ejemplo de uso:**
```html
<body>
    <h1>Título Principal</h1>
    <p>Este es un párrafo de introducción. Aquí hablamos del tema principal.</p>
    <hr />
    <h2>Subtítulo</h2>
    <p>Este es otro párrafo que detalla un punto específico.<br />Este texto aparece en una nueva línea.</p>
</body>
```

### 7. Formato de Texto (Estilos y Semántica)

HTML ofrece etiquetas para dar formato visual y significado semántico al texto.

| Etiqueta | Descripción | Visualización |
| :--- | :--- | :--- |
| `<b>` | Texto en negrita (solo visual). | **Negrita** |
| `<strong>` | Indica que el texto es de gran importancia (semántico). | **Negrita** |
| `<i>` | Texto en cursiva (solo visual). | *Cursiva* |
| `<em>` | Indica que el texto debe ser enfatizado (semántico). | *Cursiva* |
| `<u>` | Texto subrayado. | <u>Subrayado</u> |
| `<mark>` | Texto resaltado, como con un rotulador. | <mark>Resaltado</mark> |
| `<small>` | Texto más pequeño (ej. para derechos de autor). | <small>Pequeño</small> |
| `<sub>` | Subíndice. | H<sub>2</sub>O |
| `<sup>` | Superíndice. | X<sup>2</sup> |
| `<strike>` | Texto tachado (obsoleto, usar `<del>` o `<s>`). | ~~Tachado~~ |
| `<q>` | Cita corta en línea. | <q>Cita</q> |
| `<blockquote>`| Bloque de texto citado de otra fuente. |<blockquote>Bloque de cita</blockquote>|
| `<abbr>` | Abreviatura. El atributo `title` muestra el texto completo. | `<abbr title="etcétera">etc.</abbr>` |
| `<dfn>` | Término de definición. | `<dfn>HTML</dfn>` |

### 8. Listas en HTML

Las listas se utilizan para agrupar elementos relacionados.

#### Listas No Ordenadas `<ul>`
Se usan cuando el orden de los elementos no es importante. Los elementos se definen con `<li>`.

*   **Atributo `type`:** Permite cambiar el estilo de la viñeta (`disc`, `circle`, `square`).

```html
<ul type="square">
    <li>Elemento 1</li>
    <li>Elemento 2</li>
</ul>
```

#### Listas Ordenadas `<ol>`
Se usan cuando el orden de los elementos es relevante.

*   **Atributo `type`:** Cambia el tipo de numeración (`1`, `A`, `a`, `I`, `i`).
*   **Atributo `start`:** Define el número de inicio de la lista.

```html
<ol type="a" start="3">
    <li>Tercer elemento (c)</li>
    <li>Cuarto elemento (d)</li>
</ol>
```

#### Listas de Definición `<dl>`
Se usan para crear glosarios o listas de términos.

*   `<dl>`: Define la lista.
*   `<dt>`: Define el término.
*   `<dd>`: Define la descripción del término.

```html
<dl>
    <dt><b>HTML</b></dt>
    <dd>Lenguaje de Marcas de Hipertexto.</dd>
    <dt><b>CSS</b></dt>
    <dd>Hojas de Estilo en Cascada.</dd>
</dl>
```

### 9. Enlaces (Hiperenlaces)

Los enlaces (`<a>`) son el mecanismo para establecer relaciones entre páginas web y otros recursos.

*   **Tipos de enlaces:**
    *   **Internos:** Dirigen a un recurso del mismo sitio web.
    *   **Externos:** Dirigen a un recurso de otro sitio web.
*   **Tipos de URL:**
    *   **Absolutas:** Incluyen la referencia completa del recurso (ej: `http://www.ejemplo.com/pagina.html`).
    *   **Relativas:** Parten de la ubicación del documento actual (ej: `../imagenes/logo.png`).

#### Etiqueta `<a>` y sus Atributos

| Atributo | Descripción |
| :--- | :--- |
| **`href`** | **Obligatorio.** Indica la URL del recurso al que se enlaza. |
| **`target`** | Define dónde se abrirá el enlace. |
| | `_self` (por defecto): En el mismo marco. |
| | `_blank`: En una nueva ventana o pestaña. |
| | `_parent`: En el marco padre. |
| | `_top`: En el cuerpo completo de la ventana. |

#### Uso de la Etiqueta `<base>` para URLs Relativas
La etiqueta `<base>`, que se coloca dentro del `<head>`, permite definir una URL base para todos los enlaces relativos (`href`) del documento. Esto es muy útil para simplificar los enlaces cuando un sitio tiene muchas páginas que comparten el mismo dominio. También puede definir un `target` por defecto para todos los enlaces.

```html
<head>
    <title>Ejemplo con Base</title>
    <!-- Todos los enlaces relativos partirán de esta URL y se abrirán en una nueva pestaña -->
    <base href="http://www.camaramadrid.es/" target="_blank">
</head>
<body>
    <!-- Este enlace se resolverá como http://www.camaramadrid.es/es/web/guest/formacion -->
    <a href="/es/web/guest/formacion">Formación</a>

    <!-- Este enlace también usará la URL base -->
    <a href="/image/company_logo?img_id=27956">Logo</a>
</body>
```

#### Anclas para Navegación Interna

Se pueden crear enlaces a secciones específicas de una misma página utilizando el atributo `id`.

1.  **Crear el ancla (destino):** Se asigna un `id` único al elemento de destino.
    ```html
    <h2 id="seccion2">Sección 2</h2>
    ```
2.  **Crear el hiperenlace:** El `href` del enlace apunta a ese `id` precedido de `#`.
    ```html
    <a href="#seccion2">Ir a la Sección 2</a>
    ```

### 10. Imágenes en HTML

Para incluir imágenes en un documento HTML se utiliza la etiqueta `<img>`, que es una etiqueta vacía (no tiene etiqueta de cierre).

| Atributo | Descripción |
| :--- | :--- |
| **`src`** | **Obligatorio.** Especifica la ruta (URL) del archivo de imagen. |
| **`alt`** | **Obligatorio.** Proporciona un texto alternativo que se muestra si la imagen no se puede cargar. Es fundamental para la accesibilidad web. |
| `width` | Define el ancho de la imagen en píxeles. |
| `height`| Define el alto de la imagen en píxeles. |
| `border` | Define el grosor del borde. (*Nota: Este atributo está obsoleto, se recomienda usar CSS para los bordes*). |

**Ejemplo básico:**
```html
<h1>Lago Tahoe</h1>
<img src="img/lake.jpg" alt="Un paisaje del Lago Tahoe" width="400" border="1" />
```

#### Agrupación Semántica con `<figure>` y `<figcaption>`

Para asociar una imagen con un pie de foto o una leyenda de manera semántica, se utilizan las etiquetas `<figure>` y `<figcaption>`.

*   **`<figure>`:** Es un contenedor para cualquier contenido independiente (como imágenes, diagramas o vídeos) que se referencia desde el flujo principal del documento.
*   **`<figcaption>`:** Proporciona una leyenda o descripción para el contenido dentro de `<figure>`.

Este es el método moderno y correcto para añadir un pie de foto a una imagen.

**Ejemplo de uso:**
```html
<figure>
    <img src="img/lake.jpg" alt="Un paisaje del Lago Tahoe" width="400" />
    <figcaption>
        <i>El <b>lago Tahoe</b> en Sierra Nevada (Estados Unidos)</i>
    </figcaption>
</figure>
```

### 11. Audio y Video en HTML

HTML5 introdujo etiquetas específicas para incrustar contenido multimedia de forma nativa sin depender de plugins de terceros como Flash.

#### Audio con `<audio>`
La etiqueta `<audio>` permite incluir audio en una página. El atributo `controls` es fundamental para que el usuario pueda interactuar con el reproductor (play, pausa, volumen).

Para asegurar la compatibilidad entre navegadores, se utiliza la etiqueta `<source>` para proporcionar múltiples formatos de audio. El navegador utilizará el primer formato que reconozca.

```html
<audio controls>
  <source src="ogg/horse.ogg" type="audio/ogg">
  <source src="mp3/horse.mp3" type="audio/mpeg">
  Your browser does not support the audio element.
</audio>
```

#### Video con `<video>`
De forma similar, la etiqueta `<video>` permite incluir videos. Además del atributo `controls`, se pueden usar otros como `autoplay` para que el video comience automáticamente (generalmente requiere el atributo `muted` para funcionar en navegadores modernos) o `width` y `height` para definir sus dimensiones.

La etiqueta `<track>` permite añadir subtítulos o pistas de texto.

```html
<video width="400" controls autoplay muted>
  <source src="mp4/mov_bbb.mp4" type="video/mp4">
  <source src="ogg/mov_bbb.ogg" type="video/ogg">
  Your browser does not support HTML5 video.
</video>
```

#### Contenido Embebido con `<iframe>`
La etiqueta `<iframe>` permite incrustar un documento HTML completo dentro de otro. Es comúnmente utilizada para incluir contenido de terceros, como videos de YouTube, mapas de Google Maps o publicaciones de redes sociales.

| Atributo | Descripción |
| :--- | :--- |
| **`src`** | URL del documento o contenido a incrustar. |
| `width` | Ancho del marco. |
| `height`| Alto del marco. |
| `allowfullscreen` | Permite que el contenido del iframe se muestre en pantalla completa. |

**Ejemplo para un video de YouTube:**
```html
<iframe width="640" height="360"
  src="https://www.youtube.com/embed/fJ9rUzIMcZQ"
  allowfullscreen>
</iframe>
```

### 12. Tablas en HTML
Las tablas se utilizan para mostrar datos tabulados de manera organizada en filas y columnas.

#### Estructura Básica: `<table>`, `<tr>`, `<td>`
*   **`<table>`**: Define el contenedor de la tabla.
*   **`<tr>`**: Define una fila (*table row*).
*   **`<td>`**: Define una celda de datos (*table data*) dentro de una fila.

```html
<table border="1">
  <tr>
    <td>Fila 1, Columna 1</td>
    <td>Fila 1, Columna 2</td>
  </tr>
  <tr>
    <td>Fila 2, Columna 1</td>
    <td>Fila 2, Columna 2</td>
  </tr>
</table>
```

#### Cabeceras de Tabla con `<th>`
La etiqueta `<th>` (*table head*) se usa para definir las celdas de encabezado de la tabla. El texto dentro de `<th>` se muestra por defecto en negrita y centrado.

```html
<table border="1">
  <tr>
    <th>Titulo 1</th>
    <th>Titulo 2</th>
  </tr>
  <tr>
    <td>Fila 1, Columna 1</td>
    <td>Fila 1, Columna 2</td>
  </tr>
</table>
```
#### Fusión de Celdas: `colspan` y `rowspan`
*   **`colspan`**: Fusiona dos o más celdas en horizontal (a lo largo de varias columnas).
*   **`rowspan`**: Fusiona dos o más celdas en vertical (a lo largo de varias filas).

```html
<table border="1">
  <tr>
    <th>Titulo 1</th>
    <th>Titulo 2</th>
    <th>Titulo 3</th>
  </tr>
  <tr>
    <td rowspan="2">Fila 1 y 2, Columna 1</td>
    <td>Fila 1, Columna 2</td>
    <td>Fila 1, Columna 3</td>
  </tr>
  <tr>
    <td>Fila 2, Columna 2</td>
    <td>Fila 2, Columna 3</td>
  </tr>
  <tr>
    <td colspan="3">Fila 3, Columnas 1, 2 y 3</td>
  </tr>
</table>
```

#### Estructura Semántica de Tablas
Para mejorar la accesibilidad y la estructura, las tablas se pueden dividir en tres secciones:
*   **`<thead>`**: Define el encabezado de la tabla.
*   **`<tbody>`**: Define el cuerpo principal de la tabla.
*   **`<tfoot>`**: Define el pie de la tabla.

#### Título de la Tabla con `<caption>`
La etiqueta `<caption>` permite definir un título o leyenda para la tabla. Debe ser el primer elemento hijo de la etiqueta `<table>`.

```html
<table border="1">
  <caption>Ejemplo de tabla con estructura semántica</caption>
  <thead>
    <tr>
      <td colspan="2"><b><i>Cabecera de la tabla</i></b></td>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>Titulo 1</th>
      <th>Titulo 2</th>
    </tr>
    <tr>
      <td>Fila 1, Columna 1</td>
      <td>Fila 1, Columna 2</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="2"><b><i>Pie de la tabla</i></b></td>
    </tr>
  </tfoot>
</table>
```

### 13. Formularios en HTML

Los formularios son esenciales para la interacción con el usuario, permitiendo recoger información como datos de registro, comentarios o búsquedas.

#### La Etiqueta `<form>`
Es el contenedor principal de un formulario.

| Atributo | Descripción |
| :--- | :--- |
| **`action`** | Define la URL a la que se enviarán los datos del formulario para ser procesados. |
| **`method`**| Define el método HTTP que se usará para enviar los datos. Los valores comunes son `get` (los datos se añaden a la URL, visible) o `post` (los datos se envían en el cuerpo de la petición, oculto). |

```html
<form action="/procesar_datos" method="post">
  <!-- Controles del formulario aquí -->
</form>
```

#### Controles de Formulario con `<input>`
La etiqueta `<input>` es la más versátil y se utiliza para crear la mayoría de los controles de un formulario. Su comportamiento cambia según el valor de su atributo `type`.

**Atributos comunes de `<input>`:**

| Atributo | Descripción |
| :--- | :--- |
| **`type`** | Define el tipo de control (texto, contraseña, botón, etc.). |
| **`name`** | Asigna un nombre a la variable que se enviará al servidor. |
| `id` | Identificador único para el control, útil para asociarlo con una etiqueta `<label>`. |
| `value` | Define el valor inicial o el valor que se enviará. |
| `placeholder` | Muestra un texto de ayuda dentro del campo que desaparece al escribir. |
| `maxlength` | Define el número máximo de caracteres permitidos. |

**Valores comunes del atributo `type`:**

| Valor | Descripción |
| :--- | :--- |
| `text` | Campo de una línea de texto. |
| `password` | Campo de contraseña (oculta los caracteres). |
| `checkbox` | Casilla de verificación (permite selecciones múltiples). |
| `radio` | Botón de opción (solo una selección de un grupo con el mismo `name`). |
| `submit` | Botón para enviar el formulario. |
| `reset` | Botón para restablecer los valores del formulario. |
| `button` | Botón genérico, para usar con JavaScript. |
| `image` | Botón de envío con una imagen de fondo. |
| `email` | Campo para direcciones de correo electrónico (con validación básica). |
| `date` | Selector de fecha. |
| `number` | Campo numérico. |

#### Etiquetas y Agrupación

*   **`<label>`**: Asocia un texto descriptivo a un control del formulario. El atributo `for` de la etiqueta debe coincidir con el `id` del `input` para mejorar la accesibilidad.
*   **`<textarea>`**: Define un área de texto multilínea. Los atributos `rows` y `cols` definen su tamaño.
*   **`<select>` y `<option>`**: Crean una lista desplegable. `<select>` es el contenedor y cada `<option>` es una de las opciones.
*   **`<fieldset>` y `<legend>`**: `<fieldset>` agrupa controles relacionados visualmente, y `<legend>` proporciona un título a esa agrupación.

**Ejemplo de formulario completo:**

```html
<form action="" method="post">
  <fieldset>
    <legend>Datos Personales</legend>

    <label for="user">Usuario:</label>
    <input type="text" name="username" id="user" maxlength="20" placeholder="Nombre de usuario">
    <br/><br/>

    <label for="pass">Contraseña:</label>
    <input type="password" name="password" id="pass" maxlength="12" placeholder="Contraseña">
  </fieldset>

  <fieldset>
    <legend>Intereses</legend>
    <input type="checkbox" name="chkMath" id="chkMath" value="math">
    <label for="chkMath">Matemáticas</label><br>
    <input type="checkbox" name="chkIng" id="chkIng" value="ing" checked>
    <label for="chkIng">Inglés</label>
  </fieldset>

  <fieldset>
    <legend>Vehículo</legend>
    <label for="cars">Elige un coche:</label>
    <select name="dropCars" id="cars">
      <option value="volvo">Volvo</option>
      <option value="saab">Saab</option>
      <option value="audi" selected>Audi</option>
    </select>
  </fieldset>
  
  <br/>
  <input type="submit" value="Login">
</form>
```