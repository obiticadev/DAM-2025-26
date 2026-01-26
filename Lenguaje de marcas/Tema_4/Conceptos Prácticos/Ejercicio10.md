Hasta ahora, si tenías 6 párrafos, tenías que darles un ID a cada uno y cambiarlos uno a uno. ¡Un horror! En este bloque aprenderás a decir: **"Eh, vosotros, todos los párrafos de la página: haced esto"**.

---

# Bloque 10: Selección Múltiple y Bucles de Estilo

### Concepto Clave 1: El "NodeList" (La bolsa de elementos)
Cuando usas `document.querySelectorAll("p")`, JavaScript no te devuelve un elemento, te devuelve una **colección** (parecida a un Array o lista). 
*   **¡Error común!**: No puedes hacer `lista.style.color = "red"`. Porque la "bolsa" no tiene color; el color lo tienen los objetos que hay *dentro* de la bolsa.

### Concepto Clave 2: El Bucle `for`
Para aplicar un cambio a todos, hay que ir uno por uno de forma ultra rápida. El bucle `for` hace exactamente eso:
1.  Mira cuántos elementos hay (`lista.length`).
2.  Entra en el primero (índice 0), le cambia el estilo.
3.  Pasa al siguiente hasta terminar.

### Concepto Clave 3: Unidad de medida
Al cambiar tamaños de fuente (`fontSize`), recuerda siempre poner la unidad de medida entre comillas: `"24px"`. Si pones solo el número, no funcionará.

---

## 5 Ejercicios de Entrenamiento para el Nivel 10

### Ejercicio 10.1: El "Pintor de Párrafos" (Básico)
**Objetivo:** Un botón que ponga todos los párrafos en color azul.

*   **HTML:**
```html
<p>Párrafo 1</p>
<p>Párrafo 2</p>
<button onclick="pintarTodo()">Pintar de Azul</button>
```

*   **JavaScript:**
```javascript
function pintarTodo() {
    // 1. Atrapamos todos los <p> en una variable
    let parrafos = document.querySelectorAll("p");

    // 2. Usamos un bucle para recorrer la lista
    for (let i = 0; i < parrafos.length; i++) {
        parrafos[i].style.color = "blue";
    }
}
```

---

### Ejercicio 10.2: El Zoom de Texto (Ejercicio 10 Real)
**Objetivo:** Tres botones para cambiar el tamaño de todos los párrafos a la vez.

*   **HTML:**
```html
<button onclick="cambiarTamaño('24px')">Grande</button>
<button onclick="cambiarTamaño('12px')">Pequeño</button>
<button onclick="cambiarTamaño('16px')">Reset</button>

<p>Texto de prueba 1</p>
<p>Texto de prueba 2</p>
```

*   **JavaScript:**
```javascript
function cambiarTamaño(tamaño) {
    let lista = document.querySelectorAll("p");

    for (let i = 0; i < lista.length; i++) {
        lista[i].style.fontSize = tamaño;
    }
}
```

---

### Ejercicio 10.3: Resaltador de Clase
**Objetivo:** Un botón que solo afecte a los elementos que tengan una clase específica (usando selectores de CSS en JS).

*   **HTML:**
```html
<div class="nota">Nota importante</div>
<p>Texto normal</p>
<div class="nota">Otra nota</div>
<button onclick="resaltarNotas()">Resaltar Notas</button>
```

*   **JavaScript:**
```javascript
function resaltarNotas() {
    // Seleccionamos por clase (fíjate en el punto .)
    let notas = document.querySelectorAll(".nota");

    for (let i = 0; i < notas.length; i++) {
        notas[i].style.backgroundColor = "yellow";
        notas[i].style.fontWeight = "bold";
    }
}
```

---

### Ejercicio 10.4: Estilo Cebra (Lógica de bucle)
**Objetivo:** Pintar las filas pares de una lista de un color y las impares de otro. Introducimos el operador `%` (resto de la división).

*   **JavaScript:**
```javascript
function modoCebra() {
    let items = document.querySelectorAll("li");

    for (let i = 0; i < items.length; i++) {
        if (i % 2 == 0) {
            items[i].style.backgroundColor = "#eee"; // Gris clarito
        } else {
            items[i].style.backgroundColor = "#fff"; // Blanco
        }
    }
}
```

---

### Ejercicio 10.5: El "Mudo" de Imágenes
**Objetivo:** Cambiar el atributo `opacity` de todas las imágenes de la página a la vez.

*   **HTML:**
```html
<img src="foto1.jpg" width="100">
<img src="foto2.jpg" width="100">
<button onclick="atenuarFotos()">Bajar intensidad</button>
```

*   **JavaScript:**
```javascript
function atenuarFotos() {
    let imagenes = document.querySelectorAll("img");

    for (let i = 0; i < imagenes.length; i++) {
        imagenes[i].style.opacity = "0.5";
    }
}
```

---

### Guía de aprendizaje para Notion:

1.  **¿Por qué usar `querySelectorAll`?**: Es el selector más flexible. Permite usar cualquier selector de CSS: `"p"`, `".clase"`, `"#id"`, o incluso combinaciones como `"div > p"`.
2.  **El índice `[i]`**: Es como el número de turno en la fila. Empezamos en `0` porque en programación siempre se empieza a contar desde ahí. `lista[0]` es el primer elemento de la página.
3.  **`lista.length`**: Es una propiedad automática. Si mañana añades 10 párrafos más al HTML, el bucle funcionará igual porque `length` se actualizará solo.