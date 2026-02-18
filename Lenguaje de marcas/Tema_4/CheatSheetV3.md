# CheatSheet V3: Herramientas del DOM (Estructurada)

Esta guía recopila y organiza las herramientas fundamentales del DOM (Document Object Model) mencionadas en el Tema 4. Está diseñada para estudiar de forma bloqueada, desde la selección de elementos hasta la manipulación dinámica y gestión de eventos.

---

## Bloque 1: Selección de Elementos
Antes de hacer nada, necesitamos "agarrar" los elementos del HTML con los que queremos trabajar.

### 1. `document.getElementById()`
La forma más rápida y directa de seleccionar un elemento único.

*   **Construcción:** `document.getElementById(id)`
*   **Parámetros:**
    *   `id` (String): El identificador único del elemento HTML (sin el `#`).
*   **Retorna:** `HTMLElement` (Elemento específico si se conoce, o `null`).
*   **Descripción:** Busca en todo el documento el elemento que tenga el atributo `id` especificado. Como los IDs deben ser únicos, solo devuelve uno.
*   **Tipado JSDoc / TS:**
    *   *Opción Específica (Mejor)*: `@returns {HTMLInputElement}`, `@returns {HTMLDivElement}`, etc.
    *   *Opción General*: `@returns {HTMLElement}`

**Ejemplo:**
```javascript
// HTML: <h1 id="tituloPrincipal">Hola Mundo</h1>
/** @type {HTMLHeadingElement} */
const titulo = document.getElementById("tituloPrincipal");

// Ahora 'titulo' es el objeto h1 y podemos modificarlo
console.log(titulo); 
```

#### Ejercicio 1 (Selección Básica)
**Objetivo:** Crear un input y un botón. Al pulsar el botón, mostrar en un `console.log` el elemento input seleccionado por su ID.
```javascript
// Tu código aquí:
// 1. Crea en HTML <input id="miInput"> <button id="btn">Ver</button>
// 2. En JS selecciona el input y muéstralo en consola al hacer click.
```

---

### 2. `document.querySelector()`
El "navaja suiza" de la selección. Permite usar selectores de CSS.

*   **Construcción:** `document.querySelector(selector)`
*   **Parámetros:**
    *   `selector` (String): Un selector CSS válido (ej: `.clase`, `#id`, `div > p`, `input[type='text']`).
*   **Retorna:** `Element` / `HTMLElement` (el *primer* elemento que coincida) o `null`.
*   **Descripción:** Muy potente porque permite seleccionar elementos complejos sin necesidad de ponerles un ID, usando la misma lógica que en CSS.
*   **Tipado JSDoc:**
    *   *Opción Específica*: `@returns {HTMLButtonElement}` (si sabes que buscas un botón).
    *   *Opción General*: `@returns {Element}`

**Ejemplo:**
```javascript
// HTML: <div class="tarjeta">...</div> <div class="tarjeta">...</div>
const primeraTarjeta = document.querySelector(".tarjeta"); 

// Solo selecciona la PRIMERA tarjeta que encuentre.
primeraTarjeta.style.border = "1px solid red";
```

#### Ejercicio 2 (Selector CSS)
**Objetivo:** Tienes un `ul` con varios `li`. El primero tiene la clase `.destacado`. Usa `querySelector` para seleccionar solo ese `li` y cambiarle el color de fondo a amarillo.
```javascript
// Tu código aquí...
```

---

### 3. `document.querySelectorAll()`
Para cuando necesitas modificar muchos elementos a la vez.

*   **Construcción:** `document.querySelectorAll(selector)`
*   **Parámetros:**
    *   `selector` (String): Selector CSS (ej: `p`, `.item`, `li`).
*   **Retorna:** `NodeList` (Una lista de elementos, similar a un Array).
*   **Descripción:** Busca *todos* los elementos que coincidan. **Importante:** No puedes modificar la lista directamente (ej: `lista.style` da error), debes recorrerla con un bucle `for` o `forEach`.
*   **Tipado JSDoc:**
    *   *General*: `@returns {NodeList}` o `@returns {NodeListOf<HTMLElement>}`

**Ejemplo:**
```javascript
// HTML: <p>Texto 1</p> <p>Texto 2</p>
/** @type {NodeListOf<HTMLParagraphElement>} */
const parrafos = document.querySelectorAll("p");

// Recorremos la lista para pintar todos
parrafos.forEach(p => {
    p.style.color = "blue"; 
});
```

#### Ejercicio 3 (Barrido Masivo)
**Objetivo:** Crea una lista de 5 tareas (`li`). Usa `querySelectorAll` para seleccionar todas y, usando un bucle, añade un borde gris a cada una.
```javascript
// Recuerda usar .forEach() o un bucle for clásico
```

---

## Bloque 2: Manipulación de Contenido
Una vez seleccionados, cambiamos lo que hay dentro.

### 4. `element.textContent`
Para leer o escribir texto plano.

*   **Construcción:** `elemento.textContent`
*   **Parámetros:** (Al asignar) Un `String` con el nuevo texto.
*   **Retorna:** (Al leer) El texto que contiene la etiqueta (`String`).
*   **Descripción:** Es la forma segura de cambiar texto. Ignora cualquier etiqueta HTML que intentes meter (la escribirá literalmente).
*   **Tipado JSDoc:**
    *   Si haces una función que recibe texto: `@param {string} texto`

**Ejemplo:**
```javascript
const saludo = document.getElementById("saludo");
saludo.textContent = "Hola, <b>usuario</b>"; 
// En pantalla se verá: Hola, <b>usuario</b> (no pone negrita, escribe las etiquetas)
```

#### Ejercicio 4 (Cambiador de Texto)
**Objetivo:** Crea un `h1` con texto "Bienvenido". Al pulsar un botón, selecciona el `h1` y cambia su `textContent` a "Hasta luego".
```javascript
// Tu código aquí...
```

---

### 5. `element.innerHTML`
Para inyectar HTML (cuidado con la seguridad, pero útil).

*   **Construcción:** `elemento.innerHTML`
*   **Parámetros:** (Al asignar) Un `String` que puede contener etiquetas HTML.
*   **Retorna:** El contenido HTML interno como texto (`String`).
*   **Descripción:** Interpreta las etiquetas. Útil para crear estructuras rápidas o vaciar un contenedor (`.innerHTML = ""`).

**Ejemplo:**
```javascript
const caja = document.getElementById("caja");
caja.innerHTML = "<ul><li>Item 1</li><li>Item 2</li></ul>";
// El navegador dibuja la lista con viñetas reales.
```

#### Ejercicio 5 (Renderizado Dinámico)
**Objetivo:** Tienes un `div` vacío. Usa JS para inyectarle dentro un título `<h3>` y un párrafo `<p>` usando `innerHTML`.
```javascript
// Tu código aquí...
```

---

### 6. `element.value`
Fundamental para formularios.

*   **Construcción:** `input.value`
*   **Parámetros:** (Al asignar) El valor que quieres que aparezca en el campo (`String` o `Number`).
*   **Retorna:** (Al leer) El texto actual escrito en el input (`String`).
*   **Descripción:** Se usa en `<input>`, `<textarea>` y `<select>`. `textContent` NO funciona en inputs, hay que usar `.value`.
*   **Tipado JSDoc:**
    *   Este atributo pertenece a elementos de formulario.
    *   *Opción Específica*: `@param {HTMLInputElement} input` o `@param {HTMLTextAreaElement} area`
    *   *Opción General (No recomendada aquí)*: `HTMLElement` no suele tener `value` por defecto en TS estricto, mejor usar los específicos.

**Ejemplo:**
```javascript
const entrada = document.getElementById("nombre");
const boton = document.getElementById("btn");

boton.onclick = function() {
    alert("Escribiste: " + entrada.value);
};
```

#### Ejercicio 6 (Espejo Mágico)
**Objetivo:** Crea un `<input type="text">` y un `<p>`. Haz que cuando pulses un botón, el texto del párrafo (`textContent`) sea exactamente lo que hay escrito en el input (`value`).
```javascript
// Combina: getElementById, .value y .textContent
```

---

## Bloque 3: Estilos y Clases (CSS en JS)

### 7. `element.style`
Modificación de estilos en línea (directos).

*   **Construcción:** `elemento.style.propiedadCSS`
*   **Parámetros:** Un `String` con el valor CSS.
*   **Retorna:** `CSSStyleDeclaration` (el objeto de estilos).
*   **Descripción:** Las propiedades van en **camelCase**. `background-color` se convierte en `backgroundColor`. `font-size` en `fontSize`.

**Ejemplo:**
```javascript
/** @param {HTMLElement} elemento */
function ponerRojo(elemento) {
    elemento.style.color = "red";
    elemento.style.fontSize = "50px"; 
}
```

#### Ejercicio 7 (Semáforo Simple)
**Objetivo:** Un `div` cuadrado (100x100px) y tres botones: "Rojo", "Amarillo", "Verde". Al pulsar cada uno, cambia el `backgroundColor` del div usando `element.style`.
```javascript
// Recuerda: document.getElementById("caja").style.backgroundColor = ...
```

---

### 8. `element.classList` (add, remove, toggle)
La forma profesional de gestionar estilos.

*   **Construcción:**
    *   `elemento.classList.add("clase")`
    *   `elemento.classList.remove("clase")`
    *   `elemento.classList.toggle("clase")`
*   **Parámetros:** El nombre de la clase CSS (`String`).
*   **Retorna:** `DOMTokenList` (objeto lista de clases).
*   **Descripción:** Permite añadir o quitar clases definidas en tu hoja CSS, manteniendo el JS limpio.
*   **Tipado JSDoc:**
    *   Cualquier `Element` o `HTMLElement` tiene classList.

**Ejemplo:**
```javascript
// CSS: .oculto { display: none; }
const menu = document.getElementById("menu");

function alternarMenu() {
    menu.classList.toggle("oculto"); // Si está visible lo oculta, y viceversa
}
```

#### Ejercicio 8 (Modo Oscuro)
**Objetivo:** Define en CSS una clase `.dark-mode` con fondo negro y texto blanco. Crea un botón que active/desactive esa clase en el `body` usando `classList.toggle()`.
```javascript
// Tu código aquí...
```

---

## Bloque 4: Creación, Inserción y Modificación de Estructura

### 9. `document.createElement()`
Crea un elemento en memoria.

*   **Construcción:** `document.createElement(etiqueta)`
*   **Parámetros:** Nombre de etiqueta (`String`), ej: `"div"`.
*   **Retorna:** El objeto `HTMLElement` creado (específico según etiqueta).
*   **Tipado JSDoc:**
    *   `@returns {HTMLDivElement}` (si creas div).
    *   `@returns {HTMLLIElement}` (si creas li).

**Ejemplo:**
```javascript
const nuevoParrafo = document.createElement("p");
nuevoParrafo.textContent = "Soy un párrafo nuevo";
```

### 10. `parentElement.appendChild()` y variantes
Coloca el elemento creado en la página.

*   **Construcción:** `padre.appendChild(hijo)`
*   **Parámetros:** `Node` (el elemento hijo).
*   **Retorna:** El nodo insertado.
*   **Descripción:** Añade el elemento `hijo` al final de los hijos del elemento `padre`.
*   **Tipado JSDoc:**
    *   `@param {Node} hijo`

**Alternativa Moderna:** `padre.append(hijo, "texto")` admite varios nodos y texto a la vez.

**Ejemplo:**
```javascript
const cuerpo = document.body;
const nuevoBtn = document.createElement("button");
cuerpo.appendChild(nuevoBtn); 
```

### 11. `parentNode.insertBefore()`
Inserta un nodo antes de un nodo de referencia como hijo de un nodo padre especificado.

*   **Construcción:** `padre.insertBefore(nuevoNodo, nodoReferencia)`
*   **Parámetros:**
    *   `nuevoNodo`: El nodo que quieres insertar (`Node`).
    *   `nodoReferencia`: El nodo antes del cual se insertará (`Node`). Si es `null`, se inserta al final (como appendChild).
*   **Retorna:** El nodo insertado.
*   **Descripción:** Clásico y robusto. Permite posicionar elementos exactamente donde quieras.
*   **Tipado JSDoc:**
    *   `@param {Node} nuevoNodo`
    *   `@param {Node} nodoReferencia`

**Ejemplo:**
```javascript
const lista = document.getElementById("miLista");
const primerLi = lista.firstElementChild; 
const nuevoLi = document.createElement("li");
nuevoLi.textContent = "Soy el primero ahora";

// Insertamos nuevoLi ANTES de primerLi
lista.insertBefore(nuevoLi, primerLi);
```

### 12. `element.before()` y `element.after()`
Las formas modernas de insertar en hermanos (hermano anterior o posterior).

*   **Construcción:**
    *   `referencia.before(nuevo)`: Inserta EL NUEVO justo ANTES de la referencia (como hermano anterior).
    *   `referencia.after(nuevo)`: Inserta EL NUEVO justo DESPUÉS de la referencia (como hermano siguiente).
*   **Parámetros:** Nodos (`Node`) o cadenas de texto (`String`).
*   **Descripción:** Mucho más intuitivas que `insertBefore` para trabajar con hermanos.
*   **Tipado JSDoc:**
    *   Funcionan sobre cualquier `ChildNode` (`Element`, `CharacterData`, etc).

**Ejemplo:**
```javascript
const imagenCentral = document.querySelector("img");
const titulo = document.createElement("h2");
titulo.textContent = "Foto del día";

// Pone el título ENCIMA de la imagen (hermano anterior)
imagenCentral.before(titulo); 

// Pone un pie de foto DEBAJO de la imagen (hermano siguiente)
const pie = document.createElement("span");
pie.textContent = "Foto tomada en Madrid";
imagenCentral.after(pie);
```

#### Ejercicio 9 (Lista Ordenada a Medida)
**Objetivo:** Lista inicial con "Uno" y "Tres".
1. Usa `insertBefore` o `before` para añadir "Cero" al principio.
2. Usa `insertBefore` (con lógica) o `after` para poner "Dos" entre el Uno y el Tres.
```javascript
// Pista: Busca el "Tres" y dile: tres.before(dos), o busca el "Uno" y dile: uno.after(dos).
```

---

### 13. `element.remove()`
Elimina un elemento del DOM.

*   **Construcción:** `elemento.remove()`
*   **Retorna:** `undefined`.
*   **Descripión:** Borra el elemento sobre el que se llama.
*   **Tipado JSDoc:** Método de `ChildNode`.

**Ejemplo:**
```javascript
document.getElementById("viejo").remove();
```

---

## Bloque 5: Eventos

### 14. `element.addEventListener()`
*   **Construcción:** `elemento.addEventListener(evento, funcion)`
*   **Parámetros:** `String` tipo, `Function` listener.
*   **Tipado JSDoc:**
    *   `@param {string} tipo`
    *   `@param {EventListener} listener`

### 15. `event.preventDefault()`
*   **Construcción:** `event.preventDefault()`
*   **Descripción:** Evita la acción nativa (enviar formulario, seguir enlace).
*   **Tipado JSDoc:**
    *   `@param {Event} event`: Opción General.
    *   `@param {SubmitEvent} event`: Específico para formularios.
    *   `@param {MouseEvent} event`: Específico para clicks.

**Ejemplo Final Completo:**
```javascript
/** 
 * Función que valida un formulario
 * @param {SubmitEvent} e - Evento de envío
 */
function validar(e) {
    e.preventDefault();
    /** @type {HTMLInputElement} */
    const input = document.getElementById("nombre");
    
    if(input.value === "") { 
        alert("Error!"); 
    }
}
document.querySelector("form").addEventListener("submit", validar);
```

#### Ejercicio Final (Gestor de Invitados)
**Objetivo Integra todo:**
1. Formulario con input ("Nombre invitado") y botón "Añadir".
2. Lista `ul` vacía debajo.
3. Al enviar:
    *   Evita recarga (`preventDefault`).
    *   Si input vacío -> Borde rojo (`classList.add`).
    *   Si input ok -> Crea `li` con nombre.
    *   Añade botón "Borrar" dentro del `li`. Al clickarlo, se borra el `li` (`remove`).
    *   Inserta el nuevo invitado AL PRINCIPIO de la lista (`prepend` o `insertBefore` con `firstChild`).

```javascript
// ¡Ánimo! Este ejercicio cubre el 90% de lo que harás en JS del DOM.
```

---

# 📚 Apéndice: Lista Maestra de JSDoc para el DOM

Aquí tienes una referencia rápida de los tipos que más usarás al documentar tus funciones. Usar JSDoc (`/** ... */`) permite que VS Code te ayude con el autocompletado.

### 🔹 1. Elementos Genéricos (Los comodines)
Úsalos cuando no sepas exactamente qué etiqueta HTML te llegará, o cuando quieras que tu función sirva para cualquier cosa.

| Tipo JSDoc | Descripción | Cuándo usarlo |
| :--- | :--- | :--- |
| **`HTMLElement`** | Cualquier elemento estándar de HTML. | Es el estándar por defecto. Tiene `.style`, `.classList`, `.hidden`, etc. |
| **`Element`** | La base de todo (incluye XML/SVG). | Úsalo si trabajas con SVGs o XML, si no, mejor `HTMLElement`. |
| **`Node`** | Cualquier nodo (elementos, texto, comentarios). | Al usar `appendChild`, `insertBefore` o recorrer el árbol del DOM a bajo nivel. |

### 🔹 2. Elementos Específicos (Los precisos)
Úsalos siempre que puedas. Te darán acceso a propiedades únicas (como `.value` en inputs o `.href` en enlaces) sin errores de TypeScript.

| Tipo JSDoc | Se usa para etiquetas... | Propiedad Clave que desbloquea |
| :--- | :--- | :--- |
| **`HTMLInputElement`** | `<input>` | `.value`, `.checked`, `.type`, `.placeholder` |
| **`HTMLButtonElement`** | `<button>` | `.disabled`, `.type` |
| **`HTMLImageElement`** | `<img>` | `.src`, `.alt`, `.width`, `.height` |
| **`HTMLAnchorElement`** | `<a>` (Enlaces) | `.href`, `.target` |
| **`HTMLFormElement`** | `<form>` | `.submit()`, `.reset()`, `.method` |
| **`HTMLDivElement`** | `<div>` | Propiedades estándar de contenedor. |
| **`HTMLParagraphElement`** | `<p>` | Propiedades de texto. |
| **`HTMLTextAreaElement`** | `<textarea>` | `.value`, `.rows`, `.cols` |
| **`HTMLSelectElement`** | `<select>` | `.value`, `.selectedIndex`, `.options` |
| **`HTMLLIElement`** | `<li>` | `.value` (en listas ordenadas). |
| **`HTMLTableElement`** | `<table>` | `.rows`, `.insertRow()` |

**Ejemplo de uso:**
```javascript
/**
 * Pone el foco en el input y borra su contenido
 * @param {HTMLInputElement} campo - El input a limpiar
 */
function limpiarInput(campo) {
    campo.value = ""; // VS Code sabe que tiene .value
    campo.focus();
}
```

### 🔹 3. Colecciones (Listas de cosas)
Cuando usas `querySelectorAll` o navegas por hijos.

| Tipo JSDoc | Descripción | Origen común |
| :--- | :--- | :--- |
| **`NodeList`** | Lista de nodos (puede incluir texto). | `document.querySelectorAll()` |
| **`NodeListOf<HTMLElement>`** | Lista específica solo de elementos HTML. | `document.querySelectorAll("div")` (Más preciso) |
| **`HTMLCollection`** | Colección viva de elementos. | `document.getElementsByClassName()`, `element.children` |

### 🔹 4. Eventos (Lo que ocurre)
Vital para documentar el parámetro `e` o `event` en tus Listeners.

| Tipo JSDoc | Descripción | Propiedad Clave |
| :--- | :--- | :--- |
| **`Event`** | Evento genérico. | `e.preventDefault()`, `e.target` |
| **`MouseEvent`** | Clicks, movimientos ratón. | `e.clientX`, `e.clientY`, `e.button` |
| **`KeyboardEvent`** | Teclado. | `e.key` (ej: "Enter"), `e.code` |
| **`SubmitEvent`** | Envío de formularios. | `e.target` (es el formulario), `e.submitter` |
| **`InputEvent`** | Al escribir en un campo. | `e.data` (el carácter escrito) |

**Ejemplo de uso:**
```javascript
/**
 * Detecta si se pulsó Enter
 * @param {KeyboardEvent} e - El evento de teclado
 */
function checkEnter(e) {
    if (e.key === "Enter") {
        console.log("Pulsaste Enter");
    }
}
```
