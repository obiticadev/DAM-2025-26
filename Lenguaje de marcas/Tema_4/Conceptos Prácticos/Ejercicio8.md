# Bloque 8: Tablas Dinámicas y Estructuras Complejas

### Concepto Clave: La Jerarquía de la Tabla
En una tabla de HTML, los elementos no pueden estar sueltos. JavaScript debe respetar el orden:
1.  **`<table>`**: El contenedor principal.
2.  **`<tr>`** (Table Row): La fila. Es el "padre" de los datos.
3.  **`<td>`** (Table Data): La celda. Es donde vive el texto.

Para añadir una fila con dos datos, el proceso en JS es: 
**Crear TR** -> **Crear TD 1** -> **Crear TD 2** -> **Meter los TD dentro del TR** -> **Meter el TR dentro de la Tabla**.

### Concepto Clave: Navegación "Abuelo" (`parentElement.parentElement`)
Cuando ponemos un botón de "Borrar" dentro de una celda:
*   `this` es el **Botón**.
*   `this.parentElement` es la celda (**TD**).
*   `this.parentElement.parentElement` es la fila completa (**TR**). 
Para borrar la fila entera desde el botón, tenemos que subir "dos pisos".

---

## 5 Ejercicios de Entrenamiento para el Nivel 8

### Ejercicio 8.1: La Fila Manual
**Objetivo:** Añadir una fila con un texto fijo para entender la creación de TD dentro de TR.

*   **HTML:**
```html
<table id="miTabla" border="1">
    <tr><th>Nombre</th></tr>
</table>
<button onclick="añadirFilaFija()">Añadir 'Juan'</button>
```

*   **JavaScript:**
```javascript
function añadirFilaFija() {
    // 1. Creamos la fila y la celda
    let fila = document.createElement("tr");
    let celda = document.createElement("td");
    
    // 2. Metemos texto en la celda
    celda.innerText = "Juan";
    
    // 3. Montamos la estructura: Celda dentro de Fila, Fila dentro de Tabla
    fila.appendChild(celda);
    document.getElementById("miTabla").appendChild(fila);
}
```

---

### Ejercicio 8.2: Formulario a Tabla (2 Columnas)
**Objetivo:** Recoger Nombre y Ciudad de dos inputs y meterlos en una fila nueva.

*   **HTML:**
```html
<input type="text" id="nom" placeholder="Nombre">
<input type="text" id="ciu" placeholder="Ciudad">
<button onclick="registrar()">Registrar</button>

<table id="tablaDatos" border="1">
    <tr><th>Nombre</th><th>Ciudad</th></tr>
</table>
```

*   **JavaScript:**
```javascript
function registrar() {
    let nombre = document.getElementById("nom").value;
    let ciudad = document.getElementById("ciu").value;

    let fila = document.createElement("tr");
    
    let td1 = document.createElement("td");
    td1.innerText = nombre;
    
    let td2 = document.createElement("td");
    td2.innerText = ciudad;

    fila.appendChild(td1);
    fila.appendChild(td2);
    
    document.getElementById("tablaDatos").appendChild(fila);
}
```

---

### Ejercicio 8.3: El Botón de "Eliminar Fila"
**Objetivo:** Añadir una tercera columna con un botón que borre **solo esa fila**.

*   **JavaScript (Ampliando la función anterior):**
```javascript
function registrarConBorrado() {
    // ... (creación de td1 y td2 igual que arriba)
    
    let td3 = document.createElement("td");
    let boton = document.createElement("button");
    boton.innerText = "Borrar";
    
    // Configuramos el botón para que al nacer ya sepa borrar a su abuelo (la fila)
    boton.onclick = function() {
        this.parentElement.parentElement.remove();
    };

    td3.appendChild(boton);
    fila.appendChild(td3);
    // ... (añadir fila a tabla)
}
```
*Nota: Aquí usamos una función anónima, pero en clase podrías usar `onclick="borrar(this)"` si el botón lo escribieras como HTML.*

---

### Ejercicio 8.4: Formato Condicional en Tabla
**Objetivo:** Si la edad introducida es menor de 18, pintar la fila de color rojo suave.

*   **JavaScript:**
```javascript
function añadirConColor() {
    let edad = document.getElementById("edadInput").value;
    let fila = document.createElement("tr");

    // Lógica de estilo (Bloque 5 y 6)
    if (edad < 18) {
        fila.style.backgroundColor = "lightpink";
    }

    // ... (crear celdas y añadir a tabla)
}
```

---

### Ejercicio 8.5: Contador de Filas Dinámico
**Objetivo:** Cada vez que añadimos o borramos una fila, actualizar un contador que diga "Hay X personas".

*   **JavaScript:**
```javascript
let contadorPersonas = 0;

function actualizarContador(n) {
    contadorPersonas += n;
    document.getElementById("total").innerText = "Total: " + contadorPersonas;
}

// En la función registrar() llamarías a: actualizarContador(1);
// En la función borrar() llamarías a: actualizarContador(-1);
```

---

### Guía de aprendizaje para Notion:

1.  **`createElement` repetido**: Es un error común intentar usar la misma variable de celda para dos columnas. **No funciona**. Debes crear un `document.createElement("td")` nuevo para cada columna de la fila.
2.  **Orden de inserción**: Siempre es más fácil llenar el TR con todos sus TD primero, y dejar el `tabla.appendChild(fila)` para el final. Así la fila aparece "de golpe" con todos sus datos.
3.  **Encabezados**: Recuerda que las tablas suelen tener una fila inicial fija en el HTML con `<th>` (Table Header) para que no se pierdan los títulos al añadir datos.

**¿Ves cómo se complica pero a la vez todo encaja?** Este ejercicio es la prueba de fuego de la asignatura.