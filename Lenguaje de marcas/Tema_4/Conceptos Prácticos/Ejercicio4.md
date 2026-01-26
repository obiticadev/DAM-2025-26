# Bloque 4: Atributos y Selección Múltiple (Conceptos del Ejercicio 4)

### Concepto Clave 1: Atributos como propiedades
En JavaScript, una vez que tienes seleccionado un elemento, puedes acceder a sus atributos directamente con un punto. Los más comunes son:
*   `imagen.src = "nueva_foto.jpg";` -> Cambia la fuente de la imagen.
*   `enlace.href = "https://...";` -> Cambia el destino del link.
*   `input.type = "password";` -> Cambia un campo de texto a uno de contraseña.

### Concepto Clave 2: `querySelectorAll()`
A diferencia de `getElementById`, que solo busca **un** elemento (el único con ese ID), `querySelectorAll(".clase")` busca **todos** los que tengan esa clase y te los entrega en una lista (un "Array").
*   *Nota de profesor:* Aunque en este nivel solemos trabajar de uno en uno, saber que puedes "atrapar" a todos los párrafos o todas las fotos de golpe es vital.

---

## 5 Ejercicios de Entrenamiento para el Nivel 4

### Ejercicio 4.1: El Visor de Fotos (Galería básica)
**Objetivo:** Cambiar la imagen grande al hacer clic en botones de texto.

*   **HTML:**
```html
<img id="fotoPrincipal" src="https://picsum.photos/id/1/300/200" width="300">
<br>
<button onclick="cambiarFoto('https://picsum.photos/id/10/300/200')">Foto 1</button>
<button onclick="cambiarFoto('https://picsum.photos/id/20/300/200')">Foto 2</button>
```

*   **JavaScript:**
```javascript
function cambiarFoto(urlNueva) {
    let img = document.getElementById("fotoPrincipal");
    // Cambiamos el atributo src por el texto que recibimos entre paréntesis
    img.src = urlNueva;
}
```

---

### Ejercicio 4.2: Espía de Contraseñas
**Objetivo:** Un botón que "muestra" lo que hay escrito en un campo de contraseña cambiando su atributo `type`.

*   **HTML:**
```html
<input type="password" id="miClave" value="12345">
<button onclick="verClave()">Mostrar/Ocultar</button>
```

*   **JavaScript:**
```javascript
function verClave() {
    let input = document.getElementById("miClave");
    
    // Si es tipo password, lo pasamos a texto. Si no, lo volvemos a password.
    if (input.type == "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
}
```

---

### Ejercicio 4.3: Cambio de Imagen con "this" (Hover manual)
**Objetivo:** Que una imagen cambie cuando pasamos el ratón por encima y vuelva a la original al salir. Usaremos los eventos `onmouseover` y `onmouseout` (Página 8 de tu teoría).

*   **HTML:**
```html
<img src="https://picsum.photos/id/100/200" 
     onmouseover="intercambiar(this, 'https://picsum.photos/id/200/200')" 
     onmouseout="intercambiar(this, 'https://picsum.photos/id/100/200')" 
     width="200">
```

*   **JavaScript:**
```javascript
function intercambiar(elemento, url) {
    // Usamos 'elemento' porque recibimos 'this' (la imagen misma)
    elemento.src = url;
}
```

---

### Ejercicio 4.4: Buscador de Imágenes (Input a SRC)
**Objetivo:** El usuario escribe el número de una ID de foto y la imagen se actualiza.

*   **HTML:**
```html
<input type="number" id="idFoto" placeholder="Escribe un número (1-500)">
<button onclick="buscarFoto()">Cargar Foto</button>
<br>
<img id="resultado" src="" width="300" alt="Esperando búsqueda...">
```

*   **JavaScript:**
```javascript
function buscarFoto() {
    let id = document.getElementById("idFoto").value;
    let img = document.getElementById("resultado");
    
    // Construimos la URL usando el número del usuario
    img.src = "https://picsum.photos/id/" + id + "/300/200";
    img.alt = "Imagen con ID " + id;
}
```

---

### Ejercicio 4.5: El botón "Desactivador"
**Objetivo:** Usar el atributo `disabled`. Un botón que bloquea a otro.

*   **HTML:**
```html
<button id="botonMision" onclick="alert('¡Misión cumplida!')">BOTÓN CRÍTICO</button>
<hr>
<button onclick="bloquear()">Bloquear Botón</button>
<button onclick="desbloquear()">Desbloquear Botón</button>
```

*   **JavaScript:**
```javascript
function bloquear() {
    // El atributo disabled es un booleano (true/false)
    document.getElementById("botonMision").disabled = true;
}

function desbloquear() {
    document.getElementById("botonMision").disabled = false;
}
```

---

### Guía de aprendizaje para Notion:

1.  **Atributos vs Estilos**: No confundas `elemento.style.width` (que cambia el CSS y necesita "px") con `elemento.width` (que cambia el atributo del HTML y suele ser solo el número).
2.  **La importancia del `alt`**: Siempre que cambies un `src`, intenta cambiar también el `alt`. Es una buena práctica de accesibilidad que los profesores valoran mucho.
3.  **Rutas de imagen**: Si las imágenes están en tu ordenador, la ruta debe ser correcta (ej: `img/foto.jpg`). En estos ejercicios usamos URLs de internet para que siempre te funcionen.

**¿Qué te ha parecido?** Controlar los atributos es lo que permite que una página parezca una aplicación real (cambiar estados, mostrar/ocultar contraseñas, cargar fotos).