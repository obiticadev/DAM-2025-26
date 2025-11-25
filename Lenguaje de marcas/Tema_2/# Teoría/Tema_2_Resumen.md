## 1. Multimedia (Audio, Video e Iframes)
Elementos nativos para la reproducción de medios sin necesidad de plugins externos (como Flash).

### Etiqueta `<audio>`
Permite la incrustación de sonido. Se recomienda usar la etiqueta interna `<source>` para ofrecer múltiples formatos (fallback) si el navegador no soporta el primero.
*   **Atributos clave:** `controls` (interfaz de usuario), `autoplay`, `loop`, `muted`.

```html
<audio controls>
    <source src="horse.ogg" type="audio/ogg">
    <source src="mp3/horse.mp3" type="audio/mpeg">
    Your browser does not support the audio element.
</audio>
```

### Etiqueta `<video>`
Similar al audio pero con propiedades visuales.
*   **Atributos clave:** `width`, `height`, `poster` (imagen de portada), `controls`, `autoplay`, `muted`.
*   **Subtítulos:** Se usa la etiqueta `<track>` dentro del video.

```html
<video width="400" controls autoplay muted>
    <source src="mp4/mov_bbb.mp4" type="video/mp4">
    <source src="ogg/mov_bbb.ogg" type="video/ogg">
    Your browser does not support HTML5 video.
</video>
```

### Etiqueta `<iframe>`
Crea un marco en línea para incrustar contenido externo (ej. videos de YouTube, mapas).
*   **Sintaxis:** `<iframe src="URL" width="X" height="Y"></iframe>`

---

## 2. Estructura del Documento y Sintaxis HTML5

HTML (Hyper Text Markup Language) es un lenguaje de marcado interpretado por el navegador.

### Skeleton Básico (HTML5)
La estructura mínima requerida para un documento válido.
*   `<!DOCTYPE html>`: Define la gramática HTML5 (sin DTD estricto/transicional como en HTML4).
*   `<html>`: Raíz del documento. Se recomienda definir el idioma con `lang`.
*   `<head>`: Metadatos (no visibles).
*   `<body>`: Contenido visible.

```html
<!DOCTYPE html>
<html lang="es">
  <head>
    <!-- Metadatos aquí -->
  </head>
  <body>
    <!-- Contenido aquí -->
  </body>
</html>
```

### Reglas de Estilo (Buenas Prácticas)
Aunque HTML5 es permisivo (no obliga a cerrar ciertas etiquetas o usar comillas), para mantener un código profesional y escalable se debe:
*   **Tip:** Escribir etiquetas y atributos siempre en **minúsculas**.
*   **Tip:** Usar siempre **comillas dobles** `""` para los valores de atributos.
*   **Tip:** Cerrar explícitamente todas las etiquetas (incluso las vacías como `<br/>` o `<img/>`) o usar la sintaxis XML.
*   **Tip:** Mantener un **sangrado (indentación)** consistente.

---

## 3. Codificación de Caracteres (Charset)

Sistema que traduce caracteres del lenguaje natural a binario. Es crítico elegir el correcto para evitar errores de visualización (ej: ).

### Evolución de los estándares
1.  **ASCII (7 bits):** Solo 128 caracteres. Inglés básico. Sin acentos ni ñ.
2.  **ISO-8859 / ASCII Extendido (8 bits):** 256 caracteres. Regionalizado.
    *   `ISO-8859-1 (Latin-1)`: Europa Occidental (incluye ñ y vocales con tilde).
    *   `ISO-8859-15 (Latin-9)`: Actualización del Latin-1 con el símbolo del Euro (€).
3.  **UNICODE (Estándar Actual):** Tabla única universal con >50,000 símbolos (todos los idiomas, emojis, etc.).

### Codificaciones UNICODE
*   **UTF-8:** Orientada a Byte, longitud variable. **Es el estándar absoluto en la web moderna**.
*   **UTF-16 / UTF-32:** Longitud variable de 16 bits y fija de 32 bits respectivamente.

**Tip:** Aunque la teoría es extensa, en la práctica siempre debes configurar tu documento en **UTF-8** para asegurar compatibilidad global.

***

Aquí tienes la **Parte 2 de HTML (Puntos 4, 5 y 6)**. Esta sección cubre la configuración invisible de la página (metadatos) y los bloques constructivos básicos de contenido visual (texto e imágenes).

***

## 4. Metadatos y Encabezado (`<head>`)

El `<head>` contiene información técnica que **no se renderiza** directamente en la página, pero controla cómo el navegador procesa, indexa y visualiza el documento.

### Etiquetas Esenciales
*   **`<title>`**: Título en la pestaña del navegador/favoritos. **Crucial para SEO**.
*   **`<meta>`**: Define metadatos.
    *   `charset`: Codificación (siempre "UTF-8").
    *   `name="viewport"`: Controla el escalado en móviles (Responsive Design).
    *   `name="description"`: Resumen que muestran los buscadores (Google) en los resultados.
    *   `http-equiv`: Simula encabezados de respuesta HTTP (ej. refrescar página).
*   **`<link>`**: Vincula recursos externos (principalmente CSS). Atributos: `rel="stylesheet"`, `href="ruta"`, `media="screen"`.
*   **`<base>`**: Define una URL base predeterminada para todos los enlaces relativos del documento (afecta a `href` y `target`).

```html
<head>
    <title>Título de la Página - Importante SEO</title>
    
    <!-- Configuración Técnica -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- SEO -->
    <meta name="description" content="Breve descripción para Google">
    <meta name="keywords" content="html, tutorial, cheat sheet">
    <meta name="author" content="Nombre del Autor">
    
    <!-- Recursos Externos -->
    <link rel="stylesheet" href="css/estilos.css">
    
    <!-- Base URL (Opcional) -->
    <base href="https://www.misitio.com/" target="_blank">
</head>
```

---

## 5. Bloques de Texto y Estructura

Elementos para jerarquizar y formatear el contenido textual.

### Cabeceras (`headings`)
Etiquetas `<h1>` a `<h6>`. Definen la jerarquía del contenido.
*   **Tip:** Usa solo un `<h1>` por página (título principal) por razones de SEO y semántica.

### Párrafos y Formato Literal
*   **`<p>`**: Párrafo estándar. Agrega margen vertical automáticamente.
*   **`<pre>`**: Texto pre-formateado. **Respeta** los espacios en blanco y saltos de línea del código fuente (ideal para mostrar código).

### Saltos y Divisiones
*   **`<br/>`**: Salto de línea forzado (Empty tag).
*   **`<hr/>`**: Línea horizontal divisoria (cambio temático).

```html
<h1>Título Principal (H1)</h1>
<h2>Subtítulo (H2)</h2>

<p>Este es un párrafo normal. Los espacios      extra se ignoran.</p>

<hr/> <!-- Línea separadora -->

<pre>
Este texto    respeta
    los espacios y
        saltos de línea.
</pre>
```

---

## 6. Imágenes y Figuras

Incrustación de contenido gráfico estático.

### Etiqueta `<img>`
Etiqueta vacía (sin cierre `</img>`).
*   **`src` (Source):** Ruta de la imagen (absoluta o relativa).
*   **`alt` (Alternative Text):** Texto descriptivo si la imagen falla o para lectores de pantalla (Accesibilidad). **Obligatorio en validación**.
*   **`width` / `height`:** Dimensiones en píxeles. Recomendado definirlas para evitar saltos en la carga (Layout Shift).

### Semántica: `<figure>` y `<figcaption>`
Forma moderna de asociar una imagen con un pie de foto o título descriptivo.

```html
<!-- Imagen simple -->
<img src="logo.png" alt="Logotipo de la empresa" width="200" height="50">

<!-- Imagen con leyenda semántica -->
<figure>
    <img src="paisaje.jpg" alt="Vista del Lago Tahoe" width="400">
    <figcaption>Fig.1 - El lago Tahoe en Sierra Nevada.</figcaption>
</figure>
```

***

Aquí tienes la **Parte 3 de HTML (Puntos 7, 8 y 9)**. Esta sección se centra en la conectividad (enlaces), la organización de datos (listas) y la riqueza semántica del texto.

He desglosado las etiquetas de formato de texto diferenciando claramente entre las que son puramente visuales y las que aportan significado semántico, tal como detallan tus diapositivas.

***

## 7. Enlaces e Hipervínculos

El mecanismo fundamental de la web. Permite navegar entre documentos o a partes específicas de ellos.

### Etiqueta `<a>` (Anchor)
Define el enlace.
*   **`href` (Hypertext Reference):** La URL destino. Puede ser **Absoluta** (http://...) o **Relativa** (ruta local).
*   **`target`:** Define dónde se abre el enlace.
    *   `_self`: En la misma pestaña (default).
    *   `_blank`: En una **nueva pestaña** o ventana.
    *   `_parent`: En el marco padre.
    *   `_top`: En el cuerpo completo de la ventana (rompe iframes).

### Anclas (Navegación Interna)
Permite saltar a una sección específica dentro de la misma página.
1.  Se define el destino con el atributo `id` (o antiguamente `name`).
2.  Se enlaza añadiendo una almohadilla `#` seguida del id en el `href`.

```html
<!-- Enlace Externo -->
<a href="https://www.google.es" target="_blank">Ir a Google</a>

<!-- Enlace Interno (Ancla) -->
<a href="#seccion1">Ir al inicio</a>

<!-- Destino del Ancla -->
<h1 id="seccion1">Cabecera de la página</h1>
```

---

## 8. Listas

Organización de elementos. Pueden anidarse unas dentro de otras.

### Listas No Ordenadas (`<ul>`)
Muestra viñetas (bullets).
*   **Etiquetas:** `<ul>` (contenedor) y `<li>` (elemento de lista).
*   **Atributo `type`:** Cambia el estilo de la viñeta (`disc`, `circle`, `square`).

```html
<ul type="square">
    <li>Elemento 1</li>
    <li>Elemento 2</li>
</ul>
```

### Listas Ordenadas (`<ol>`)
Muestra una secuencia numérica o alfabética.
*   **Etiquetas:** `<ol>` y `<li>`.
*   **Atributo `type`:** Define el contador (`1`, `a`, `A`, `i`, `I`).
*   **Atributo `start`:** Define el número por el que empieza la cuenta.

```html
<!-- Lista con números romanos empezando en 4 (IV) -->
<ol type="I" start="4">
    <li>Elemento IV</li>
    <li>Elemento V</li>
</ol>
```

### Listas de Definición (`<dl>`)
Estructura tipo diccionario o glosario.
*   **`<dl>`**: Definition List (Contenedor).
*   **`<dt>`**: Definition Term (Término a definir).
*   **`<dd>`**: Definition Description (Descripción).

---

## 9. Formato de Texto: Físico vs Semántico

HTML ofrece etiquetas para alterar la apariencia del texto. Es importante distinguir entre las que solo cambian el aspecto ("Físicas") y las que dan significado al navegador/buscadores ("Semánticas").

### Formato Físico (Solo Visual)
Modifican la estética sin aportar valor semántico extra.
*   **`<b>`**: Negrita (Bold).
*   **`<i>`**: Cursiva (Italic).
*   **`<u>`**: Subrayado (Underline).
*   **`<strike>`**: Tachado.
*   **`<big>` / `<small>`**: Aumenta o disminuye el tamaño de fuente.
*   **`<sup>` / `<sub>`**: Superíndice ($m^2$) y Subíndice ($H_2O$).

### Formato Semántico (Significado + Visual)
Indican la naturaleza del contenido. **Recomendado por estándares modernos**.
*   **`<strong>`**: Importancia fuerte (Visualmente negrita).
*   **`<em>`**: Énfasis relevante (Visualmente cursiva).
*   **`<mark>`**: Resaltado (Fondo amarillo tipo rotulador).
*   **`<q>`**: Cita corta en línea (Añade comillas automáticamente).
*   **`<blockquote>`**: Cita en bloque (Indenta el texto, usado para citas largas).
*   **`<dfn>`**: Definición de un término.

### Abreviaturas
*   **`<abbr>`**: Abreviatura. El atributo `title` muestra el significado completo al pasar el ratón.
*   **`<acronym>`**: Siglas/Acrónimos (Funcionamiento similar a abbr, usa `title`).

```html
<p>
  El agua (H<sub>2</sub>O) es <strong>muy importante</strong>.
  <br>
  Como dijo <abbr title="Señor">Sr.</abbr> Smith: <q>Esto es una cita</q>.
</p>

<blockquote>
    Esto es un bloque de cita independiente desplazado a la derecha.
</blockquote>
```

***

Aquí tienes la **Parte 4 (Final)**. Esta sección cubre las dos estructuras más complejas e interactivas de HTML: la presentación de datos tabulares y la recolección de información del usuario.

***

## 10. Tablas (Tables)

Estructuras para organizar datos en filas y columnas.
*   **Nota:** No deben usarse para maquetar el diseño de la página (layout), solo para datos tabulares.

### Estructura Básica
*   **`<table>`**: Contenedor principal.
*   **`<tr>` (Table Row)**: Define una fila.
*   **`<td>` (Table Data)**: Define una celda estándar dentro de una fila.
*   **`<th>` (Table Header)**: Define una celda de encabezado (negrita y centrada por defecto).
*   **`<caption>`**: Define el título de la tabla.

### Agrupación Semántica
Permite dividir la tabla en secciones lógicas (útil para imprimir o escroling).
*   **`<thead>`**: Cabecera (contiene los títulos de columnas).
*   **`<tbody>`**: Cuerpo (contiene los datos).
*   **`<tfoot>`**: Pie (resúmenes o totales).

### Atributos de Fusión y Espaciado
*   **`colspan="n"`**: Fusiona **columnas** (expande la celda horizontalmente).
*   **`rowspan="n"`**: Fusiona **filas** (expande la celda verticalmente).
*   **`cellpadding`**: Espacio interno (relleno) dentro de la celda.
*   **`cellspacing`**: Espacio externo entre celdas.

```html
<table border="1">
    <caption>Listado de Ventas</caption>
    <thead>
        <tr>
            <th>Producto</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Lápiz</td>
            <td>1.50€</td>
        </tr>
        <tr>
            <!-- Esta celda ocupa 2 columnas -->
            <td colspan="2">Sin stock en almacén</td>
        </tr>
    </tbody>
</table>
```

---

## 11. Formularios (Forms)

El mecanismo principal para capturar datos del usuario y enviarlos al servidor.

### Etiqueta `<form>`
Contenedor de los controles.
*   **`action`**: La URL a donde se enviarán los datos.
*   **`method`**: Cómo se envían los datos.
    *   `get`: Los datos van en la URL (visible). Para búsquedas.
    *   `post`: Los datos van en el cuerpo de la petición (oculto). Para contraseñas/datos sensibles.

### Etiqueta `<input>`
El control más versátil. Su comportamiento depende del atributo `type`.
*   **Atributos Comunes:**
    *   `name`: Nombre de la variable que recibe el servidor (**Obligatorio** para enviar datos).
    *   `id`: Identificador único (para CSS y labels).
    *   `value`: Valor por defecto.
    *   `placeholder`: Texto de ayuda (fantasma) que desaparece al escribir.
    *   `maxlength`: Límite de caracteres.

**Tipos de Input (`type="..."`):**
| Tipo | Descripción |
| :--- | :--- |
| `text` / `password` | Texto simple / Texto oculto (puntos). |
| `number` / `range` | Campo numérico / Deslizador. |
| `email` / `url` | Valida formato de correo o web automáticamente. |
| `date` / `time` | Selector de fecha / hora. |
| `checkbox` | Casilla de verificación (permite selección múltiple). Usa `checked`. |
| `radio` | Botón de opción (selección única). Deben compartir el mismo `name`. Usa `checked`. |
| `file` | Subida de archivos. |
| `hidden` | Campo invisible para el usuario pero se envía al servidor. |
| `submit` / `reset` | Botón para enviar formulario / Botón para limpiar campos. |

### Otros Controles
*   **`<label>`**: Etiqueta de texto para un input. Mejora la accesibilidad.
    *   **Vinculación:** El atributo `for` del label debe coincidir con el `id` del input.
*   **`<textarea>`**: Campo de texto multilínea.
    *   Atributos: `rows` (filas de alto), `cols` (columnas de ancho).
*   **`<select>` y `<option>`**: Menú desplegable.
    *   `selected`: Pre-selecciona una opción.
    *   `multiple`: Permite elegir varias opciones (con Ctrl/Cmd).
*   **`<fieldset>` y `<legend>`**: Agrupa controles visualmente con un borde y un título (`legend`) integrado en el borde.

```html
<form action="/login.php" method="post">
    <fieldset>
        <legend>Datos de Acceso</legend>
        
        <label for="usr">Usuario:</label>
        <input type="text" id="usr" name="username" placeholder="Tu email aquí">
        <br>
        
        <label for="pwd">Contraseña:</label>
        <input type="password" id="pwd" name="password">
        <br>
        
        <!-- Selección Única -->
        <label>Género:</label>
        <input type="radio" name="gender" value="M" checked> Mujer
        <input type="radio" name="gender" value="H"> Hombre
        <br>
        
        <!-- Desplegable -->
        <select name="pais">
            <option value="es">España</option>
            <option value="mx" selected>México</option>
        </select>
        
        <input type="submit" value="Entrar">
    </fieldset>
</form>
```

***

