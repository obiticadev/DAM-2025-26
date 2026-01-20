# 1. Sintaxis y Depuración (El "System.out.println")

En Java usas la consola de la IDE. En JS, tu consola es la del **Navegador (F12)**.

*   **Tip:** No uses `alert()`, bloquea la ejecución. Usa `console.log()`.
*   **Template Literals:** Para concatenar strings con variables sin volverte loco con los `+`.

```javascript
let usuario = "Juan";
// En Java: "Hola " + usuario
console.log(`Hola ${usuario}, bienvenido al DOM`); // Usa comillas bac-ticks (atrás)
```

---

# 2. Variables: El puente desde Java

En JS no verás `int`, `String` o `boolean`. Usamos palabras clave:

*   **`const`**: Como el `final` de Java. Úsalo para referencias al DOM (botones, inputs) que no cambiarán.
*   **`let`**: Para variables que van a cambiar de valor (contadores, acumuladores).
*   **`var`**: **PROHIBIDO.** Es la forma antigua y da problemas de ámbito (scope).

```javascript
const pi = 3.1416; // No cambia
let contador = 0;  // Puede cambiar
```

---

# 3. Operadores de Comparación (Cuidado aquí)

En Java `==` compara valores. En JS existe el "Triple Igual".

*   **`==`**: Compara valor (hace conversión automática). `"5" == 5` es `true`.
*   **`===` (Estricto)**: Compara valor y TIPO. `"5" === 5` es `false`.
*   **Tip:** Usa **SIEMPRE `===`** para evitar errores silenciosos.

---

# 4. Estructuras de Control (Casi igual que Java)

Los `if`, `while` y `for` son idénticos. Pero para el DOM, el `for...of` es muy útil para recorrer listas de elementos.

```javascript
const parrafos = document.querySelectorAll("p");

for (let p of parrafos) {
    p.style.color = "blue"; // Pinta todos los párrafos de azul
}
```

---

# 5. Manipulación Avanzada del DOM

En los PDFs viste cómo seleccionar, pero te faltará saber cómo leer y escribir datos de verdad:

### A. Obtener vs Modificar contenido
*   **`.textContent`**: Solo texto (seguro).
*   **`.innerHTML`**: Renderiza HTML (si pones etiquetas, se crean).
*   **`.value`**: **CRUCIAL.** Para obtener lo que el usuario escribe en un `<input>`.

```javascript
const inputNombre = document.getElementById("nombreUsuario");
const divSalida = document.getElementById("mensaje");

let dato = inputNombre.value; // Captura lo escrito en el cuadro de texto
divSalida.textContent = `Hola ${dato}`; // Lo escribe en un div
```

### B. Gestión de Clases CSS (Mejor que modificar estilos uno a uno)
En DAM es mala práctica usar `element.style`. Lo profesional es crear una clase en CSS y ponerla/quitarla con JS.

```javascript
const caja = document.querySelector(".caja");

caja.classList.add("activo");      // Añade la clase CSS .activo
caja.classList.remove("oculto");   // Quita la clase
caja.classList.toggle("resaltar"); // Si la tiene la quita, si no la tiene la pone
```

---

# 6. Eventos: El objeto `event` y `preventDefault`

Cuando ocurre un evento, JS nos pasa un objeto con información.

*   **`e.target`**: Te dice exactamente qué elemento se clicó (muy útil si hay muchos botones).
*   **`e.preventDefault()`**: Evita que el navegador haga lo de siempre. **Indispensable en formularios.**

```javascript
const formulario = document.querySelector("form");

formulario.addEventListener("submit", function(e) {
    e.preventDefault(); // Evita que la página se recargue al dar a enviar
    console.log("Formulario enviado sin recargar la web");
});
```

---

# 7. Conversión de Tipos (Casting)

Como en Lenguaje de Marcas todo lo que viene de un `<input>` es un `String`, si quieres sumar números, tienes que "castear" como en Java:

```javascript
let num1 = document.getElementById("n1").value; // "5"
let num2 = document.getElementById("n2").value; // "10"

// Error: "5" + "10" = "510"
// Solución:
let suma = Number(num1) + Number(num2); // 15
```

---

# ★ Tips Finales para un estudiante de Java:

1.  **Orden en el HTML:** Pon tu `<script src="..."></script>` justo antes de cerrar el `</body>`. Así te aseguras de que el DOM ya existe cuando JS intente buscar elementos.
2.  **No busques tipos:** Si una función devuelve algo, no verás `public int suma()`. Verás `function suma()`. El tipo lo determina el `return`.
3.  **Arrays:** Son dinámicos (como un `ArrayList` de Java). Tienen métodos como `.push()` (añadir) o `.length`.
4.  **CamelCase:** Al igual que en Java, en JS todo es `camelCase` (ej: `getElementById`). CSS usa `kebab-case` (`background-color`), pero en JS eso se convierte a `backgroundColor`.

---

# 8. Generación de Aleatorios (Para Ejercicio 1)
En JS, `Math.random()` devuelve un decimal entre 0 y 1. Para colores o números enteros, necesitamos combinarlo con `Math.floor()` (redondear hacia abajo).

```javascript
// Ejemplo: Generar un color RGB aleatorio
function colorAleatorio() {
    let r = Math.floor(Math.random() * 256); // 0 a 255
    let g = Math.floor(Math.random() * 256);
    let b = Math.floor(Math.random() * 256);
    return `rgb(${r}, ${g}, ${b})`;
}
document.body.style.backgroundColor = colorAleatorio();
```

# 9. Trabajo con Múltiples Elementos (Para Ejercicios 4 y 10)
`querySelectorAll()` no devuelve un Array puro, sino una **NodeList**. Aunque se parece a una lista de Java, para modificar todos los elementos a la vez debes usar `.forEach()`.

```javascript
const parrafos = document.querySelectorAll("p");

parrafos.forEach(p => {
    p.style.fontSize = "24px"; // Se aplica a cada uno de los 6 párrafos
});
```

# 10. Atributos y Contenido (Para Ejercicios 4, 7 y 9)
Para cambiar imágenes o mostrar/ocultar menús:

*   **Atributos:** `elemento.src`, `elemento.id`, `elemento.href`.
*   **Visibilidad:** `elemento.style.display = "none"` (oculta) o `"block"` (muestra).
*   **Creación con texto:**
```javascript
let li = document.createElement("li");
li.textContent = "Nueva tarea"; // Mejor que innerHTML para texto plano
```

# 11. Tablas y Eliminación Específica (Para Ejercicios 3 y 8)
Para las tablas, creamos la fila (`tr`) y luego las celdas (`td`). Para eliminar, podemos usar `this` (en funciones tradicionales) o el objeto evento.

```javascript
// Crear fila de tabla
let fila = document.createElement("tr");
let celdaNombre = document.createElement("td");
celdaNombre.textContent = "Juan";
fila.appendChild(celdaNombre);

// Botón de eliminar dentro de la fila
let btnBorrar = document.createElement("button");
btnBorrar.textContent = "Eliminar";
btnBorrar.onclick = function() {
    // En Java sería como "this.getParent()"
    this.parentElement.parentElement.remove(); // Borra el <tr> (padre del td que es padre del botón)
};
```

# 12. Validación y Posicionamiento (Para Ejercicio 7)
Si quieres poner un mensaje de error **justo debajo** de un input y no al final de la página:

*   **`parentNode.insertBefore(nuevo, referencia)`**: Inserta el elemento `nuevo` antes del elemento de `referencia`.

```javascript
const nombreInput = document.getElementById("nombre");
const error = document.createElement("span");
error.textContent = "Campo obligatorio";
error.style.color = "red";

// Insertar el error justo antes del siguiente elemento del input
nombreInput.parentNode.insertBefore(error, nombreInput.nextSibling);
```

---

# ★ Tips "Pro" para tus Ejercicios:

1.  **Ejercicio 4 (Galería):** Al usar `querySelectorAll` en los thumbnails, añade el evento dentro del `forEach`. Así, cada miniatura sabrá que al ser clicada debe cambiar el `src` de la imagen grande.
2.  **Ejercicio 6 (Contador):** Crea una variable global `let contador = 0;`. En las funciones de los botones, actualiza la variable y luego haz un `h2.textContent = contador;`. Al final, usa un `if` para cambiar el `style.color`.
3.  **Ejercicio 9 (Menú):** Usa una clase CSS `.oculto { display: none; }` y en JS usa `lista.classList.toggle("oculto")`. Es mucho más limpio que andar cambiando el `style.display` a mano.
4.  **Diferencia con Java:** Recuerda que en JS puedes asignar una función a una variable o incluso al `onclick` directamente (como en el ejemplo de la tabla). No necesitas crear una clase "ManejadorDeEvento".
