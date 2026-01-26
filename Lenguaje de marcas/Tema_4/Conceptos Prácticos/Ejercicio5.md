Llegamos al último bloque de esta primera fase, y es **el más importante para trabajar en el mundo real**. 

Hasta ahora, en el Bloque 1, cambiábamos el estilo directamente con `.style.backgroundColor`. Eso funciona, pero tiene un problema: si quieres cambiar 10 cosas a la vez (color, borde, sombra, fuente), tu código JS se vuelve gigante y feo.

La solución profesional es **`classList`**. En lugar de cambiar los estilos uno a uno, creas una "clase" en CSS con todo el diseño y con JS solo dices: "Pon esta clase" o "Quita esta clase".

---

# Bloque 5: Gestión de Clases con `classList`

### Concepto Clave: Separación de responsabilidades
*   **CSS**: Se encarga de **cómo se ve** (define las clases).
*   **JS**: Se encarga de **cuándo ocurre** (pone o quita esas clases).

### Los 3 métodos fundamentales (Página 6 de tu teoría):
1.  **`elemento.classList.add("nombreClase")`**: Añade la clase. Si ya la tiene, no hace nada.
2.  **`elemento.classList.remove("nombreClase")`**: Quita la clase.
3.  **`elemento.classList.toggle("nombreClase")`**: El interruptor. Si la tiene la quita, y si no la tiene la pone.

### La Gran Ventaja:
Es mucho más limpio. Es como cambiarle el "traje" entero a un elemento de golpe en lugar de ir cosiéndole parches.

---

## 5 Ejercicios de Entrenamiento para el Nivel 5

Para estos ejercicios, primero necesitamos definir unas clases en tu **CSS**:
```css
/* Clases preparadas para los ejercicios */
.destacado { border: 5px solid gold; box-shadow: 0 0 20px yellow; transform: scale(1.05); }
.error { background-color: #ffcccc; border: 2px solid red; color: red; }
.exito { background-color: #ccffcc; border: 2px solid green; color: green; }
.invisible { display: none; }
.modo-lectura { font-size: 24px; line-height: 2; max-width: 600px; margin: auto; }
```

---

### Ejercicio 5.1: Botón de "Destacar" (Add/Remove)
**Objetivo:** Usar dos botones para poner y quitar un marco dorado a una imagen.

*   **HTML:**
```html
<img id="producto" src="https://picsum.photos/200" width="200">
<br>
<button onclick="resaltar()">Destacar</button>
<button onclick="normal()">Normal</button>
```

*   **JavaScript:**
```javascript
function resaltar() {
    let img = document.getElementById("producto");
    img.classList.add("destacado");
}

function normal() {
    let img = document.getElementById("producto");
    img.classList.remove("destacado");
}
```

---

### Ejercicio 5.2: El Semáforo de Validación (Exclusividad)
**Objetivo:** Cambiar entre una clase de "Error" y una de "Éxito". Aprenderás que para poner una, es mejor quitar la otra.

*   **HTML:**
```html
<div id="mensaje" class="caja">Estado del sistema...</div>
<button onclick="ponerError()">Simular Error</button>
<button onclick="ponerExito()">Simular Éxito</button>
```

*   **JavaScript:**
```javascript
function ponerError() {
    let div = document.getElementById("mensaje");
    div.classList.remove("exito"); // Limpiamos por si acaso
    div.classList.add("error");
}

function ponerExito() {
    let div = document.getElementById("mensaje");
    div.classList.remove("error"); // Limpiamos por si acaso
    div.classList.add("exito");
}
```

---

### Ejercicio 5.3: El Interruptor de Modo Lectura (Toggle)
**Objetivo:** Un solo botón que active o desactive un formato de texto grande y cómodo.

*   **HTML:**
```html
<button onclick="alternarLectura()">Modo Lectura On/Off</button>
<p id="textoLargo">Este es un texto largo que cambiará de formato...</p>
```

*   **JavaScript:**
```javascript
function alternarLectura() {
    let p = document.getElementById("textoLargo");
    // Si tiene la clase la quita, si no la tiene la pone automáticamente
    p.classList.toggle("modo-lectura");
}
```

---

### Ejercicio 5.4: Validación de longitud en vivo
**Objetivo:** Un input que se pone en rojo (clase error) si escribes menos de 5 caracteres.

*   **HTML:**
```html
<input type="text" id="usuario" placeholder="Mínimo 5 letras..." onkeyup="validar()">
```

*   **JavaScript:**
```javascript
function validar() {
    let input = document.getElementById("usuario");
    
    if (input.value.length < 5) {
        input.classList.add("error");
        input.classList.remove("exito");
    } else {
        input.classList.add("exito");
        input.classList.remove("error");
    }
}
```

---

### Ejercicio 5.5: Selección de tarjetas (Uso de "this")
**Objetivo:** Al hacer clic en una tarjeta, se queda "seleccionada" (con un borde).

*   **HTML:**
```html
<div class="tarjeta" onclick="seleccionar(this)" style="padding:10px; border:1px solid gray;">
    Tarjeta 1 (Haz clic)
</div>
<div class="tarjeta" onclick="seleccionar(this)" style="padding:10px; border:1px solid gray;">
    Tarjeta 2 (Haz clic)
</div>
```

*   **JavaScript:**
```javascript
function seleccionar(elemento) {
    // Usamos toggle para que al volver a clicar se deseleccione
    elemento.classList.toggle("destacado");
}
```

---

### Guía de aprendizaje para Notion:

1.  **`style` vs `classList`**: Usa `.style` para cosas que cambian constantemente (como la posición de un ratón o un color aleatorio). Usa `classList` para estados fijos (activo, inactivo, error, oculto).
2.  **El orden en CSS importa**: Si un elemento tiene dos clases que cambian lo mismo (como `error` y `exito`), ganará la que esté escrita **más abajo en tu fichero .css**. Por eso en el JS es mejor borrar una antes de poner la otra.
3.  **No pongas el punto**: Un error muy común es escribir `classList.add(".clase")`. **¡Error!** El punto solo se usa en los selectores de CSS. En JavaScript pones solo el nombre: `classList.add("clase")`.

---

**¡Enhorabuena!** Hemos completado los conceptos de los primeros 5 ejercicios de tu práctica. Con esto ya tienes una base muy sólida para empezar a montar aplicaciones interactivas.

A partir de aquí, los siguientes ejercicios de tu lista (6 al 10) empiezan a mezclar todo esto: eventos de teclado, formularios, tablas dinámicas...