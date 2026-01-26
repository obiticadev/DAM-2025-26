# Bloque 3: Eliminación de Elementos y el contexto `this`

### Concepto Clave 1: `remove()` vs `removeChild()`
Según tu teoría (Página 6, "DOM Structure Methods"), hay dos formas de quitar algo:
1.  **`elemento.remove()`**: Es la forma moderna. El elemento se elimina a sí mismo. Es como decirle a alguien: "Vete de aquí".
2.  **`padre.removeChild(hijo)`**: Es la forma clásica. El padre expulsa al hijo. Es como si el dueño de una casa echara a un invitado.

### Concepto Clave 2: La palabra mágica `this`
Este es el mayor secreto de los programadores. Cuando en el HTML ponemos `onclick="borrar(this)"`, ese `this` le envía a la función **el elemento exacto que ha sido pulsado**. 
*   Si tienes 100 botones iguales, `this` le dice a JavaScript: "He sido yo, el botón número 42".

### Concepto Clave 3: `parentElement`
Casi siempre, el botón de "Eliminar" está dentro de un `<li>` o un `<div>`. El botón no quiere borrarse a sí mismo, quiere borrar **el contenedor donde está**. Para eso usamos `this.parentElement` (mi padre).

---

## 5 Ejercicios de Entrenamiento para el Nivel 3

### Ejercicio 3.1: El Botón Suicida
**Objetivo:** Crear un botón que, al pulsarlo, desaparezca de la página.

*   **HTML:**
```html
<button onclick="suicidio(this)">Haz clic para borrarme</button>
```

*   **JavaScript:**
```javascript
function suicidio(botonPulsado) {
    // botonPulsado recibe el 'this' del HTML
    botonPulsado.remove(); 
    // Límite: Una vez borrado, no hay forma de recuperarlo sin recargar la página.
}
```

---

### Ejercicio 3.2: Eliminar fila de una lista (El clásico)
**Objetivo:** Una lista donde cada elemento tiene su propio botón de borrar. Usaremos `parentElement`.

*   **HTML:**
```html
<ul>
    <li>Manzana <button onclick="borrarFila(this)">Eliminar</button></li>
    <li>Pera <button onclick="borrarFila(this)">Eliminar</button></li>
    <li>Plátano <button onclick="borrarFila(this)">Eliminar</button></li>
</ul>
```

*   **JavaScript:**
```javascript
function borrarFila(boton) {
    // 1. 'boton' es el <button>
    // 2. 'boton.parentElement' es el <li> que lo contiene
    let fila = boton.parentElement;
    
    // 3. Borramos el <li> completo
    fila.remove();
}
```

---

### Ejercicio 3.3: Borrado por ID (A distancia)
**Objetivo:** Un botón que borra un elemento específico que está en otra parte de la pantalla. Aquí no usamos `this`, sino `getElementById`.

*   **HTML:**
```html
<div id="publicidad" style="background: yellow; padding: 20px;">
    Publicidad molesta
</div>
<button onclick="cerrarPublicidad()">Cerrar Anuncio</button>
```

*   **JavaScript:**
```javascript
function cerrarPublicidad() {
    let anuncio = document.getElementById("publicidad");
    
    // Verificamos si existe antes de borrar para evitar errores en consola
    if (anuncio) {
        anuncio.remove();
    }
}
```

---

### Ejercicio 3.4: Limpiar todo el contenedor (Reset)
**Objetivo:** Borrar todos los hijos de un contenedor de golpe. Aprenderás un truco rápido usando `innerHTML`.

*   **HTML:**
```html
<button onclick="limpiarTodo()">Borrar toda la lista</button>
<ul id="miLista">
    <li>Elemento 1</li>
    <li>Elemento 2</li>
    <li>Elemento 3</li>
</ul>
```

*   **JavaScript:**
```javascript
function limpiarTodo() {
    let lista = document.getElementById("miLista");
    
    // Truco: Al decir que el contenido interno es nada (""), 
    // el navegador borra automáticamente todos los hijos.
    lista.innerHTML = "";
}
```

---

### Ejercicio 3.5: Borrado condicional (Confirmación)
**Objetivo:** Preguntar al usuario antes de borrar. Introducimos el comando `confirm`.

*   **HTML:**
```html
<div id="archivoImportante" style="border: 1px red solid;">
    Documento_Confidencial.pdf
    <button onclick="intentarBorrar(this)">Eliminar</button>
</div>
```

*   **JavaScript:**
```javascript
function intentarBorrar(boton) {
    // confirm devuelve true si pulsas 'Aceptar' y false si pulsas 'Cancelar'
    let respuesta = confirm("¿Estás seguro de que quieres borrar este archivo?");
    
    if (respuesta == true) {
        boton.parentElement.remove();
        alert("Archivo borrado.");
    } else {
        alert("Operación cancelada.");
    }
}
```

---

### Guía de aprendizaje para Notion:

1.  **¿Por qué usar `this`?**: Porque te ahorra tener que ponerle un ID diferente a cada botón. Imagina una lista de 1000 productos; no vas a escribir 1000 IDs. Con `this`, una sola función sirve para todos.
2.  **`parentElement` es infinito**: Puedes hacer `this.parentElement.parentElement` para subir dos niveles en la jerarquía (por ejemplo, del botón a la celda y de la celda a la fila de una tabla).
3.  **Diferencia visual**: `remove()` hace que el espacio que ocupaba el elemento también desaparezca. Si solo quieres que no se vea pero que el espacio se mantenga, se usa CSS (`style.visibility = 'hidden'`), pero para los ejercicios actuales, usamos borrado total del DOM.

**¿Qué te parece este bloque?** Es fundamental para hacer aplicaciones que se sientan "limpias". 