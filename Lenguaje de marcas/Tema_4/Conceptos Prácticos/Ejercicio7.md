# Bloque 7: Validación de Formularios y Mensajes Dinámicos

### Concepto Clave 1: El evento `onsubmit`
En los formularios, el evento principal no suele ser un `onclick` en un botón, sino un `onsubmit` en la etiqueta `<form>`. 
*   **Importante:** Por defecto, los formularios intentan enviar los datos y recargar la página. En JavaScript de primero de DAM, a veces usamos un botón normal con `type="button"` para evitar que la página se recargue mientras validamos.

### Concepto Clave 2: `insertBefore()`
Hasta ahora usábamos `appendChild()` (añadir al final). Pero en un formulario, si hay un error en el campo "Nombre", el mensaje de error debe aparecer **justo debajo** de ese campo, no al final de la página.
*   **Sintaxis:** `padre.insertBefore(nuevoElemento, elementoDeReferencia);`
*   Dice: "Pon este mensaje de error *antes* del siguiente campo o después del actual".

### Concepto Clave 3: Limpieza de errores previos
Antes de validar, siempre debemos borrar los mensajes de error antiguos. Si no lo hacemos, cada vez que el usuario pulse "Enviar", se irán acumulando mensajes rojos infinitamente.

---

## 5 Ejercicios de Entrenamiento para el Nivel 7

### Ejercicio 7.1: El "Campo Obligatorio" (Básico)
**Objetivo:** Si el input está vacío, crear un pequeño texto rojo debajo.

*   **HTML:**
```html
<div id="grupoNombre">
    <input type="text" id="nombreUsuario" placeholder="Tu nombre...">
    <button type="button" onclick="validarSimple()">Enviar</button>
</div>
```

*   **JavaScript:**
```javascript
function validarSimple() {
    let input = document.getElementById("nombreUsuario");
    let contenedor = document.getElementById("grupoNombre");

    if (input.value == "") {
        let error = document.createElement("span");
        error.innerText = "Este campo es obligatorio";
        error.style.color = "red";
        contenedor.appendChild(error);
    }
}
```

---

### Ejercicio 7.2: Validador de Edad (Lógica + Creación)
**Objetivo:** Si el usuario es menor de 18, mostrar un aviso de "Acceso denegado".

*   **JavaScript:**
```javascript
function comprobarEdad() {
    let edad = document.getElementById("edadInput").value;
    let zonaMensaje = document.getElementById("resultado");
    
    // Limpiamos el mensaje anterior si existía
    zonaMensaje.innerHTML = "";

    if (edad < 18) {
        let aviso = document.createElement("p");
        aviso.innerText = "Lo siento, debes ser mayor de edad.";
        aviso.className = "error-estilo"; // Supongamos que esta clase es roja en CSS
        zonaMensaje.appendChild(aviso);
    }
}
```

---

### Ejercicio 7.3: Validación de Email (Busca de caracteres)
**Objetivo:** Comprobar si el texto contiene una "@". Usaremos el método `.includes()`.

*   **JavaScript:**
```javascript
function validarEmail() {
    let correo = document.getElementById("email").value;
    
    if (!correo.includes("@")) {
        let msg = document.createElement("div");
        msg.innerText = "El correo no es válido (falta @)";
        msg.style.color = "orange";
        // Insertamos al final del formulario
        document.getElementById("miForm").appendChild(msg);
    }
}
```

---

### Ejercicio 7.4: Limpieza Automática (El "Reset")
**Objetivo:** Aprender a borrar todos los mensajes de error de golpe antes de volver a validar.

*   **JavaScript:**
```javascript
function limpiarErrores() {
    // Buscamos TODOS los elementos que hayamos creado con la clase 'msg-error'
    let errores = document.querySelectorAll(".msg-error");
    
    // Los recorremos uno a uno y los borramos
    for (let i = 0; i < errores.length; i++) {
        errores[i].remove();
    }
}
```
*Nota: Aunque los bucles `for` son del siguiente tema, es la forma profesional de limpiar múltiples errores.*

---

### Ejercicio 7.5: Formulario Completo (Estilo Ejercicio 7 de la práctica)
**Objetivo:** Validar dos campos y poner cada error donde corresponde.

*   **HTML:**
```html
<form id="registro">
    <div id="caja1">
        <input type="text" id="user" placeholder="Usuario">
    </div>
    <div id="caja2">
        <input type="email" id="mail" placeholder="Email">
    </div>
    <button type="button" onclick="validarTodo()">Registrar</button>
</form>
```

*   **JavaScript:**
```javascript
function validarTodo() {
    // 1. Limpiamos posibles errores anteriores (opcional pero recomendado)
    
    let user = document.getElementById("user").value;
    let mail = document.getElementById("mail").value;

    if (user == "") {
        crearError("caja1", "Nombre vacío");
    }
    if (mail == "") {
        crearError("caja2", "Email vacío");
    }
}

// Función auxiliar para no repetir código (Mentalidad de Senior)
function crearError(idPadre, texto) {
    let mensaje = document.createElement("span");
    mensaje.innerText = texto;
    mensaje.style.color = "red";
    mensaje.style.display = "block";
    document.getElementById(idPadre).appendChild(mensaje);
}
```

---

### Guía de aprendizaje para Notion:

1.  **`appendChild` vs `insertBefore`**: En formularios, el orden visual es clave. Si quieres que el error salga *arriba* del input, usa `insertBefore`. Si quieres que salga *debajo*, usa `appendChild` dentro de un `div` que contenga ese input.
2.  **La importancia de `type="button"`**: Si usas `type="submit"`, el formulario intentará enviar los datos a un servidor. Como ahora estamos solo con JS de cliente, usamos `button` para que nada se mueva sin nuestro permiso.
3.  **Variables Locales**: En estos ejercicios, `user` y `mail` son variables que solo nos importan en el momento de pulsar el botón. No necesitan ser globales.

**¿Qué te parece?** Validar formularios es el "pan de cada día" de un desarrollador web. 
