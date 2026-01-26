El **Ejercicio 1** de tu teoría se centra en un concepto fundamental: **Acceder al estilo de un elemento a través del DOM**. 

Antes de empezar con los 5 ejercicios de práctica, documentemos la base teórica en tu Notion.

---

# Bloque 1: Manipulación Directa del Estilo (Conceptos del Ejercicio 1)

### Concepto Clave: El objeto `style`
En JavaScript, cuando seleccionamos un elemento del HTML (como el `body` o un `div`), este tiene una propiedad llamada `.style`. Dentro de esa propiedad, podemos encontrar todas las reglas de CSS que conocemos, pero escritas en formato **camelCase** (ejemplo: `background-color` en CSS pasa a ser `backgroundColor` en JS).

### La Línea Maestra:
`document.body.style.backgroundColor = "red";`

*   **`document`**: Es el objeto global que representa toda tu página web.
*   **`.body`**: Es un acceso directo al nodo `<body>`.
*   **`.style`**: Entramos en la "maleta" de estilos del elemento.
*   **`.backgroundColor`**: La propiedad específica que queremos cambiar.
*   **`= "red"`**: El nuevo valor (siempre entre comillas porque es una cadena de texto).

---

## 5 Ejercicios de Entrenamiento para el Nivel 1

He diseñado estos ejercicios para que escales desde lo más simple hasta empezar a usar lógica. Crea un fichero `practica1.html` y un `practica1.js` para probarlos.

### Ejercicio 1.1: El interruptor básico
**Objetivo:** Cambiar el fondo a un color fijo (por ejemplo, verde) al pulsar un botón. Es la base de todo.

*   **HTML:**
```html
<button onclick="ponerVerde()">Poner fondo verde</button>
```

*   **JavaScript:**
```javascript
function ponerVerde() {
    // Límite: Solo funciona para el color verde fijo.
    document.body.style.backgroundColor = "green";
}
```

---

### Ejercicio 1.2: El Selector Manual
**Objetivo:** En lugar de colores aleatorios, crearemos tres botones. Cada uno pasará un color diferente. Aquí aprenderás que las funciones pueden recibir información.

*   **HTML:**
```html
<button onclick="cambiarColor('red')">Rojo</button>
<button onclick="cambiarColor('blue')">Azul</button>
<button onclick="cambiarColor('yellow')">Amarillo</button>
```

*   **JavaScript:**
```javascript
function cambiarColor(colorElegido) {
    // 'colorElegido' es una variable que recibe el texto que enviamos desde el HTML.
    document.body.style.backgroundColor = colorElegido;
}
```

---

### Ejercicio 1.3: El "Modo Noche" (Toggle Lógico)
**Objetivo:** Un solo botón que cambie entre blanco y negro. Aquí introducimos la estructura `if / else` de tu teoría.

*   **HTML:**
```html
<button onclick="modoNoche()">Alternar Blanco/Negro</button>
```

*   **JavaScript:**
```javascript
function modoNoche() {
    // Consultamos el color actual. 
    // Importante: Al principio puede estar vacío, por eso preguntamos si es negro o no.
    if (document.body.style.backgroundColor == "black") {
        document.body.style.backgroundColor = "white";
    } else {
        document.body.style.backgroundColor = "black";
    }
}
```

---

### Ejercicio 1.4: Color por entrada de usuario (Input)
**Objetivo:** El usuario escribe el nombre de un color en inglés (o un código hexadecimal) y el fondo cambia. Aquí usamos la propiedad `.value`.

*   **HTML:**
```html
<input type="text" id="colorUsuario" placeholder="Escribe un color...">
<button onclick="aplicarColorUsuario()">Aplicar color</button>
```

*   **JavaScript:**
```javascript
function aplicarColorUsuario() {
    // 1. Buscamos el input por su ID
    let input = document.getElementById("colorUsuario");
    // 2. Extraemos el texto que el usuario escribió (.value)
    let color = input.value;
    // 3. Lo aplicamos al fondo
    document.body.style.backgroundColor = color;
}
```

---

### Ejercicio 1.5: El Generador de RGB (Casi Aleatorio)
**Objetivo:** En lugar de nombres de colores, usaremos números. Aprenderás a "construir" una cadena de texto dinámica.

*   **HTML:**
```html
<button onclick="fondoGrisAleatorio()">Poner un gris aleatorio</button>
```

*   **JavaScript:**
```javascript
function fondoGrisAleatorio() {
    // Generamos un número entre 0 y 255 (Concepto de variables de tu teoría)
    let tono = Math.floor(Math.random() * 256);
    
    // Construimos la frase "rgb(x, x, x)" concatenando el número
    // Límite: Aquí los tres valores son iguales, por lo que siempre será un tono de gris.
    let colorRGB = "rgb(" + tono + "," + tono + "," + tono + ")";
    
    document.body.style.backgroundColor = colorRGB;
}
```

---

### Guía de aprendizaje para ti:
1.  **Analiza el Ejercicio 1.4**: Es vital. Entender que `getElementById` te da el "objeto" y `.value` te da "lo que hay escrito dentro" es la clave de toda la asignatura.
2.  **Analiza el Ejercicio 1.5**: Fíjate cómo usamos las comillas y los signos `+`. JavaScript necesita que le entregues una frase que el navegador entienda (como `rgb(100,100,100)`).

**¿Qué debes hacer ahora?**
Copia estos ejercicios en tus ficheros, pruébalos uno a uno. Cuando entiendas perfectamente por qué el 1.4 necesita un ID y el 1.2 no, o por qué usamos `if` en el 1.3