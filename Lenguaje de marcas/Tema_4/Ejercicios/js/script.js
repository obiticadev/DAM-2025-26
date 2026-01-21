/* ============================================================
   EJERCICIO 1: CAMBIAR COLOR DE FONDO
   ============================================================ */
function cambiarFondo() {
    // Generamos valores aleatorios para R, G y B
    let r = Math.floor(Math.random() * 256);
    let g = Math.floor(Math.random() * 256);
    let b = Math.floor(Math.random() * 256);
    
    // Aplicamos el color al body usando la propiedad style
    document.querySelector('h2').style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
}

/* ============================================================
   EJERCICIO 2: LISTA DE TAREAS DINÁMICA
   ============================================================ */
function añadirTarea() {
    let input = document.getElementById("inputTarea");
    let texto = input.value;

    if (texto !== "") {
        // 1. Crear el elemento li
        let nuevaTarea = document.createElement("li");
        // 2. Asignar el texto
        nuevaTarea.innerText = texto;
        // 3. Añadirlo a la lista ul
        let lista = document.getElementById("listaTareas");
        lista.appendChild(nuevaTarea);
        
        // Limpiar el input para la siguiente tarea
        input.value = "";
    } else {
        alert("Por favor, escribe algo.");
    }
}

/* ============================================================
   EJERCICIO 3: ELIMINAR ELEMENTOS
   ============================================================ */
function eliminarElemento(botonPulsado) {
    // 'this' en el HTML pasa el botón. El padre del botón es el 'li'.
    let liAEliminar = botonPulsado.parentElement;
    liAEliminar.remove(); 
    // Nota: también podrías usar: liAEliminar.parentNode.removeChild(liAEliminar);
}

/* ============================================================
   EJERCICIO 4: GALERÍA DE IMÁGENES
   ============================================================ */
function actualizarGaleria(urlMiniatura) {
    // Seleccionamos la imagen principal por su ID
    let imgPrincipal = document.getElementById("imgPrincipal");
    
    // Cambiamos el atributo src. 
    // Usamos querySelectorAll solo para demostrar que sabemos identificar elementos
    let todasLasMinis = document.querySelectorAll(".thumbnail");
    
    // Cambiamos la fuente de la grande por la de la pequeña pulsada
    imgPrincipal.src = urlMiniatura;
}

/* ============================================================
   EJERCICIO 5: MODIFICAR CLASES CSS
   ============================================================ */
function añadirRoja() {
    let caja = document.getElementById("cajaMuestra");
    caja.classList.add("rojo");
}

function quitarRoja() {
    let caja = document.getElementById("cajaMuestra");
    caja.classList.remove("rojo");
}

function alternarGrande() {
    let caja = document.getElementById("cajaMuestra");
    caja.classList.toggle("grande");
}

function ponerAzul() {
    let caja = document.getElementById("cajaMuestra");
    // Removemos roja para que no haya conflicto de colores y ponemos azul
    caja.classList.remove("rojo");
    caja.classList.add("azul");
}

/* 
   ¡CONSEJO PROFESIONAL! 
   Para los ejercicios 6 al 10, sigue esta misma estructura:
   1. Crea una <section> nueva en el HTML con su ID.
   2. Crea una función en este archivo JS que haga la lógica.
   3. Usa document.getElementById() para leer o escribir datos.
*/