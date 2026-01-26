# Bloque 9: Visibilidad y Estados de Pantalla

### Concepto Clave 1: La propiedad `display`
En CSS, la propiedad `display` controla cómo se posiciona un elemento. Para nosotros, los valores mágicos son:
*   **`none`**: El elemento desaparece por completo. No se ve y **no ocupa espacio**. Es como si no existiera.
*   **`block`**: El elemento vuelve a aparecer ocupando todo el ancho disponible.

### Concepto Clave 2: Consultar el estado antes de actuar
Para hacer un botón que "abre y cierra", primero tenemos que preguntar: "¿Cómo estás ahora?". 
Si está en `none`, lo pasamos a `block`. Si está en `block`, lo pasamos a `none`.

### Concepto Clave 3: Visibilidad por Clase (Más elegante)
Cambiar el `display` a mano funciona, pero es brusco (aparece de golpe). Los profesionales suelen usar una clase de CSS (como `.oculto { opacity: 0; }`) y jugar con `classList.toggle` para que el cambio pueda tener una transición suave.

---

## 5 Ejercicios de Entrenamiento para el Nivel 9

### Ejercicio 9.1: El Menú Desplegable Clásico
**Objetivo:** Un botón que muestra u oculta una lista de enlaces.

*   **HTML:**
```html
<button onclick="toggleMenu()">Menú ☰</button>
<ul id="menu" style="display: none;">
    <li>Inicio</li>
    <li>Productos</li>
    <li>Contacto</li>
</ul>
```

*   **JavaScript:**
```javascript
function toggleMenu() {
    let menu = document.getElementById("menu");

    if (menu.style.display == "none") {
        menu.style.display = "block";
    } else {
        menu.style.display = "none";
    }
}
```

---

### Ejercicio 9.2: El botón "Leer más..."
**Objetivo:** Mostrar un texto extra y cambiar el texto del propio botón de "Leer más" a "Cerrar".

*   **HTML:**
```html
<p>JavaScript es genial... 
   <span id="extra" style="display: none;">porque permite crear webs dinámicas.</span>
</p>
<button onclick="leerMas(this)">Leer más...</button>
```

*   **JavaScript:**
```javascript
function leerMas(boton) {
    let span = document.getElementById("extra");

    if (span.style.display == "none") {
        span.style.display = "inline"; // 'inline' para que siga en la misma línea
        boton.innerText = "Cerrar";
    } else {
        span.style.display = "none";
        boton.innerText = "Leer más...";
    }
}
```

---

### Ejercicio 9.3: Ventana de Ayuda (Modal simple)
**Objetivo:** Un div que flote sobre la página y se cierre con una "X".

*   **HTML:**
```html
<button onclick="abrirAyuda()">Necesito ayuda</button>

<div id="modalAyuda" style="display: none; border: 2px solid black; padding: 20px; position: fixed; top: 20%; background: white;">
    <p>¿En qué podemos ayudarte?</p>
    <button onclick="cerrarAyuda()">Cerrar [X]</button>
</div>
```

*   **JavaScript:**
```javascript
function abrirAyuda() {
    document.getElementById("modalAyuda").style.display = "block";
}

function cerrarAyuda() {
    document.getElementById("modalAyuda").style.display = "none";
}
```

---

### Ejercicio 9.4: FAQ (Preguntas Frecuentes)
**Objetivo:** Al hacer clic en una pregunta, aparece la respuesta. Usaremos el concepto de "this" y "nextElementSibling" (hermano siguiente).

*   **HTML:**
```html
<div class="pregunta" onclick="verRespuesta(this)" style="cursor:pointer; font-weight:bold;">
    ¿Es difícil aprender JS?
</div>
<div class="respuesta" style="display:none;">
    No, si practicas mucho cada día.
</div>
```

*   **JavaScript:**
```javascript
function verRespuesta(elemento) {
    // Buscamos al hermano que tiene justo debajo (la respuesta)
    let respuesta = elemento.nextElementSibling;
    
    if (respuesta.style.display == "none") {
        respuesta.style.display = "block";
    } else {
        respuesta.style.display = "none";
    }
}
```

---

### Ejercicio 9.5: Filtro de Contenido (Show/Hide por clase)
**Objetivo:** Un botón que oculta todos los elementos de una clase específica.

*   **HTML:**
```html
<button onclick="filtrar('fruta')">Ocultar/Mostrar Frutas</button>
<ul>
    <li class="fruta">Manzana</li>
    <li class="carne">Pollo</li>
    <li class="fruta">Pera</li>
</ul>
```

*   **JavaScript:**
```javascript
function filtrar(clase) {
    let elementos = document.querySelectorAll("." + clase);
    
    for (let i = 0; i < elementos.length; i++) {
        if (elementos[i].style.display == "none") {
            elementos[i].style.display = "list-item";
        } else {
            elementos[i].style.display = "none";
        }
    }
}
```

---

### Guía de aprendizaje para Notion:

1.  **`display: none` vs `visibility: hidden`**: 
    *   `display: none`: El elemento se quita del flujo. El resto de elementos suben para ocupar su sitio.
    *   `visibility: hidden`: El elemento no se ve, pero el hueco vacío se mantiene. (Como un hombre invisible ocupando una silla).
2.  **El valor inicial**: Si en tu CSS no has puesto `display: none`, la primera vez que intentes leerlo desde JS (`if (menu.style.display == "none")`) podría dar un resultado extraño. Es mejor asegurar el estado inicial en el atributo `style` del HTML o en el CSS.
3.  **Animaciones**: `display` no se puede animar (no puedes pasar de `none` a `block` lentamente). Si quieres animaciones, tendrás que usar `opacity` o `height`.

**¿Qué te ha parecido este bloque?** Es la base de casi todos los componentes modernos de navegación.