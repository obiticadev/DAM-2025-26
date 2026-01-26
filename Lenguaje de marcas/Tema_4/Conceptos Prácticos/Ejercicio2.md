# Bloque 2: Creación Dinámica de Elementos (Conceptos del Ejercicio 2)

### Concepto Clave: El flujo de creación (El "Limbo")
Cuando queremos añadir algo nuevo a la página (un párrafo, una imagen, un elemento de lista), JavaScript sigue un proceso de **3 pasos obligatorios**. Imagina que vas a adoptar un cachorro:

1.  **`createElement()`**: El cachorro nace (se crea en la memoria del ordenador, pero aún no está en tu casa).
2.  **Configuración**: Le pones nombre, collar o color (`innerText`, `className`, `id`).
3.  **`appendChild()`**: Lo traes a casa (lo enganchas a un elemento que ya existe en el HTML para que sea visible).

### La Línea Maestra:
`let nuevaEtiqueta = document.createElement("li");`
`contenedor.appendChild(nuevaEtiqueta);`

*   **`createElement("tag")`**: Crea una etiqueta vacía del tipo que le digas entre comillas.
*   **`appendChild(hijo)`**: Es un método que se aplica al **padre**. Dice: "Ey, tú, añade a este nuevo elemento al final de tus hijos".

---

## 5 Ejercicios de Entrenamiento para el Nivel 2

Para estos ejercicios, usaremos un contenedor vacío en el HTML donde "inyectaremos" las cosas.

### Ejercicio 2.1: Generador de Párrafos
**Objetivo:** Cada vez que pulses el botón, aparecerá un nuevo párrafo con el texto "Soy un párrafo nuevo".

*   **HTML:**
```html
<button onclick="crearParrafo()">Añadir Párrafo</button>
<div id="contenedorTexto"></div>
```

*   **JavaScript:**
```javascript
function crearParrafo() {
    // 1. Creamos el elemento en memoria (en el "limbo")
    let p = document.createElement("p");
    
    // 2. Le damos contenido
    p.innerText = "Soy un párrafo nuevo";
    
    // 3. Buscamos al "padre" y le entregamos al "hijo"
    let padre = document.getElementById("contenedorTexto");
    padre.appendChild(p);
    
    // Límite: Si pulsas 100 veces, se crearán 100 etiquetas <p> en el DOM.
}
```

---

### Ejercicio 2.2: La Lista de la Compra (Input + List)
**Objetivo:** Lo que el usuario escriba en un input se convertirá en un elemento `<li>` de una lista `<ul>`.

*   **HTML:**
```html
<input type="text" id="producto" placeholder="Escribe un producto...">
<button onclick="añadirALista()">Añadir a la lista</button>
<ul id="listaCompra"></ul>
```

*   **JavaScript:**
```javascript
function añadirALista() {
    let input = document.getElementById("producto");
    let texto = input.value;

    if (texto !== "") {
        let nuevoLi = document.createElement("li");
        nuevoLi.innerText = texto;
        
        let lista = document.getElementById("listaCompra");
        lista.appendChild(nuevoLi);
        
        input.value = ""; // Limpiamos para el siguiente
    }
}
```

---

### Ejercicio 2.3: Generador de Enlaces (Atributos)
**Objetivo:** Crear un enlace `<a>` dinámicamente. Aquí aprenderás que también podemos crear atributos como el `href`.

*   **HTML:**
```html
<button onclick="generarGoogle()">Crear enlace a Google</button>
<div id="espacioEnlaces"></div>
```

*   **JavaScript:**
```javascript
function generarGoogle() {
    let enlace = document.createElement("a");
    enlace.innerText = "Ir a Google";
    
    // Configuración de atributos (Importante)
    enlace.href = "https://www.google.com";
    enlace.target = "_blank"; // Para que abra en pestaña nueva
    
    let contenedor = document.getElementById("espacioEnlaces");
    contenedor.appendChild(enlace);
}
```

---

### Ejercicio 2.4: Tarjeta de Usuario (Estructura Anidada)
**Objetivo:** Crear un `div` que dentro tenga un `h3`. Aprenderás que un elemento creado puede ser a su vez padre de otro.

*   **HTML:**
```html
<button onclick="crearTarjeta()">Crear Tarjeta</button>
<div id="muro"></div>
```

*   **JavaScript:**
```javascript
function crearTarjeta() {
    // Creamos el contenedor de la tarjeta
    let tarjeta = document.createElement("div");
    tarjeta.style.border = "1px solid black";
    tarjeta.style.padding = "10px";
    
    // Creamos el título
    let titulo = document.createElement("h3");
    titulo.innerText = "Usuario Nuevo";
    
    // METEMOS EL TÍTULO DENTRO DE LA TARJETA (Hijo de su hijo)
    tarjeta.appendChild(titulo);
    
    // METEMOS LA TARJETA EN EL MURO
    document.getElementById("muro").appendChild(tarjeta);
}
```

---

### Ejercicio 2.5: Álbum de Fotos Dinámico
**Objetivo:** Crear una etiqueta `<img>` y asignarle una imagen aleatoria.

*   **HTML:**
```html
<button onclick="añadirFoto()">Añadir Foto Aleatoria</button>
<div id="galeria"></div>
```

*   **JavaScript:**
```javascript
function añadirFoto() {
    let imagen = document.createElement("img");
    
    // Usamos una web que da fotos aleatorias
    imagen.src = "https://picsum.photos/150"; 
    imagen.style.margin = "5px";
    imagen.style.borderRadius = "10px";
    
    let galeria = document.getElementById("galeria");
    galeria.appendChild(imagen);
    
    // Límite: No estamos controlando el tamaño del contenedor, las fotos se desbordarán si no hay CSS.
}
```

---

### Guía de aprendizaje para Notion:

1.  **La importancia del orden**: Si haces `padre.appendChild(hijo)` antes de ponerle el `innerText`, el elemento aparecerá vacío al principio (aunque luego lo cambies). Lo ideal es: **Crear -> Configurar -> Pegar**.
2.  **`appendChild` siempre añade al FINAL**: Si el padre ya tiene 3 hijos, el nuevo será el cuarto. (Existe `insertBefore` para ponerlo arriba, pero lo veremos más adelante).
3.  **Memoria**: Cada vez que llamas a `createElement`, el navegador reserva un poquito de RAM. En aplicaciones gigantes, hay que tener cuidado con crear miles de cosas sin control.

**¿Cómo lo ves?** Practica estos 5 ejemplos. Especialmente el **2.4**, porque entender que puedes meter un elemento dentro de otro elemento (ambos creados por JS) es la base para crear interfaces complejas.