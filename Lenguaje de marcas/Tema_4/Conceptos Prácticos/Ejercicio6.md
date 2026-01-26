# Bloque 6: Lógica de Estado y Estilos Condicionales (Ejercicio 6)

### Concepto Clave: Variables Globales vs. Locales
Este es el concepto que más cuesta al principio. 
*   **Variable Global (Afuera de la función):** El navegador la "recuerda" siempre. Es como una libreta donde vas anotando puntos.
*   **Variable Local (Dentro de la función):** Se crea al pulsar el botón y se destruye al terminar la función. Es como un papel sucio que tiras cada vez.
*   *Para un contador necesitamos una Global.*

### Concepto Clave: Comparadores (`if / else if / else`)
Para cambiar colores según el valor, usamos los comparadores de tu teoría (Página 8): `>` (mayor), `<` (menor), `==` (igual).

---

## 5 Ejercicios de Entrenamiento para el Nivel 6

### Ejercicio 6.1: El Contador de Votos
**Objetivo:** El ejercicio base. Sumar, restar y cambiar color (Verde > 0, Rojo < 0, Negro = 0).

*   **HTML:**
```html
<button onclick="cambiar(1)">+</button>
<button onclick="cambiar(-1)">-</button>
<h2 id="puntos">0</h2>
```

*   **JavaScript:**
```javascript
let total = 0; // GLOBAL: No se borra

function cambiar(cantidad) {
    total = total + cantidad;
    let pantalla = document.getElementById("puntos");
    pantalla.innerText = total;

    // Lógica de colores (Condicionales)
    if (total > 0) {
        pantalla.style.color = "green";
    } else if (total < 0) {
        pantalla.style.color = "red";
    } else {
        pantalla.style.color = "black";
    }
}
```

---

### Ejercicio 6.2: El Semáforo de Temperatura
**Objetivo:** Un input donde pones los grados y un div cambia de color (Azul < 15, Naranja 15-30, Rojo > 30).

*   **HTML:**
```html
<input type="number" id="grados" placeholder="Temperatura...">
<button onclick="analizarClima()">Analizar</button>
<div id="termo" style="width: 50px; height: 50px; border: 1px solid;"></div>
```

*   **JavaScript:**
```javascript
function analizarClima() {
    let temp = document.getElementById("grados").value;
    let caja = document.getElementById("termo");

    if (temp < 15) {
        caja.style.backgroundColor = "blue";
    } else if (temp >= 15 && temp <= 30) {
        caja.style.backgroundColor = "orange";
    } else {
        caja.style.backgroundColor = "red";
    }
}
```

---

### Ejercicio 6.3: Validador de Stock (Límites)
**Objetivo:** Un contador que no permita bajar de 0 ni subir de 10.

*   **JavaScript:**
```javascript
let stock = 5;

function ajustarStock(n) {
    // Solo sumamos si no nos pasamos de los límites
    if (stock + n >= 0 && stock + n <= 10) {
        stock = stock + n;
        document.getElementById("numStock").innerText = stock;
    } else {
        alert("Límite de stock alcanzado");
    }
}
```

---

### Ejercicio 6.4: Calculadora de Descuentos (Estilo dinámico)
**Objetivo:** Si el precio final es mayor a 100€, el texto se pone en negrita y verde.

*   **JavaScript:**
```javascript
function calcular() {
    let precio = document.getElementById("precio").value;
    let resultado = document.getElementById("total");
    
    if (precio > 100) {
        resultado.style.fontWeight = "bold";
        resultado.style.color = "green";
        resultado.innerText = "¡Envío gratis! Total: " + precio + "€";
    } else {
        resultado.style.fontWeight = "normal";
        resultado.style.color = "black";
        resultado.innerText = "Total: " + precio + "€";
    }
}
```

---

### Ejercicio 6.5: El "Adivina el número" (Feedback visual)
**Objetivo:** Comparar el número del usuario con uno secreto y dar pistas de color.

*   **JavaScript:**
```javascript
let secreto = 7;

function adivinar() {
    let num = document.getElementById("intento").value;
    let pista = document.getElementById("pista");

    if (num == secreto) {
        pista.innerText = "¡Correcto!";
        pista.style.backgroundColor = "gold";
    } else {
        pista.innerText = "Sigue intentando...";
        pista.style.backgroundColor = "lightgray";
    }
}
```

---

### Guía de aprendizaje para Notion:

1.  **Manipulación de estilos CSS desde JS:** Recuerda que las propiedades con guion se escriben en CamelCase: `font-weight` -> `fontWeight`, `background-color` -> `backgroundColor`.
2.  **El orden de los `if`:** JavaScript lee de arriba a abajo. Si la primera condición se cumple, ignora el resto. Pon siempre las condiciones más específicas primero.
3.  **Conversión automática:** Aunque `input.value` devuelve texto, JavaScript es inteligente y si haces una resta o suma, intenta convertirlo a número. Pero ten cuidado: `"5" + 1` puede dar `51` (unión de textos), mientras que `"5" - 1` da `4`.