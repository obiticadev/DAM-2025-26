/* ============================================================
   EJERCICIO 1: COLOR DE FONDO ALEATORIO
   ============================================================ */
function ej1_cambiarFondo() {
    let r = Math.floor(Math.random() * 256);
    let g = Math.floor(Math.random() * 256);
    let b = Math.floor(Math.random() * 256);
    document.body.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")";
}

/* ============================================================
   EJERCICIO 2: LISTA DE TAREAS (Crear Elementos)
   ============================================================ */
function ej2_añadirTarea() {
    let input = document.getElementById("ej2_input");
    if (input.value != "") {
        let li = document.createElement("li"); // Creamos etiqueta
        li.innerText = input.value;            // Metemos texto
        document.getElementById("ej2_lista").appendChild(li); // Pegamos al padre
        input.value = ""; // Limpiamos
    }
}

/* ============================================================
   EJERCICIO 3: ELIMINAR (this y parentElement)
   ============================================================ */
function ej3_eliminar(boton) {
    // 'boton' es el elemento pulsado (this)
    // Su parentElement es el <li>. Lo borramos entero.
    boton.parentElement.remove();
}

/* ============================================================
   EJERCICIO 4: GALERÍA (Atributos)
   ============================================================ */
function ej4_verFoto(url) {
    // Cambiamos el atributo 'src' de la imagen grande
    document.getElementById("ej4_grande").src = url;
}

/* ============================================================
   EJERCICIO 5: CLASES (classList)
   ============================================================ */
function ej5_addRojo() {
    document.getElementById("ej5_caja").classList.add("rojo");
}
function ej5_toggleGrande() {
    document.getElementById("ej5_caja").classList.toggle("grande");
}
function ej5_reset() {
    let caja = document.getElementById("ej5_caja");
    caja.classList.remove("rojo", "grande");
}

/* ============================================================
   EJERCICIO 6: CONTADOR (Lógica + Style)
   ============================================================ */
let ej6_cuenta = 0; // Variable global para que no se borre el número
function ej6_operar(n) {
    ej6_cuenta += n;
    let visual = document.getElementById("ej6_valor");
    visual.innerText = ej6_cuenta;

    // Cambiamos color según valor (Teoría: Manipular .style.color)
    if (ej6_cuenta > 0) visual.style.color = "green";
    else if (ej6_cuenta < 0) visual.style.color = "red";
    else visual.style.color = "black";
}

/* ============================================================
   EJERCICIO 7: VALIDACIÓN (createElement + insertBefore)
   ============================================================ */
function ej7_validar() {
    // Limpiamos errores previos buscando por clase
    let viejos = document.querySelectorAll(".error-msg");
    for (let i = 0; i < viejos.length; i++) viejos[i].remove();

    let nom = document.getElementById("ej7_nombre");
    if (nom.value == "") {
        let err = document.createElement("span");
        err.className = "error-msg";
        err.innerText = "El nombre es obligatorio";
        // Lo pegamos en su grupo correspondiente
        document.getElementById("ej7_grupo_nombre").appendChild(err);
    }
}

/* ============================================================
   EJERCICIO 8: TABLA DINÁMICA (Jerarquía DOM)
   ============================================================ */
function ej8_añadir() {
    let nombre = document.getElementById("ej8_nom").value;
    let edad = document.getElementById("ej8_edad").value;

    if (nombre && edad) {
        let fila = document.createElement("tr"); // Fila (Padre)

        // Creamos celdas (Hijos)
        let td1 = document.createElement("td"); td1.innerText = nombre;
        let td2 = document.createElement("td"); td2.innerText = edad;
        let td3 = document.createElement("td");

        // Botón de borrar para cada fila
        let btn = document.createElement("button");
        btn.innerText = "Borrar";
        btn.onclick = function () { this.parentElement.parentElement.remove(); };

        td3.appendChild(btn);
        fila.appendChild(td1); fila.appendChild(td2); fila.appendChild(td3);
        document.getElementById("ej8_tabla").appendChild(fila);
    }
}

/* ============================================================
   EJERCICIO 9: MENÚ (Display o Clases)
   ============================================================ */
function ej9_toggleMenu() {
    let menu = document.getElementById("ej9_menu");
    // Intercambiamos clases para mostrar/ocultar
    if (menu.className == "menu-oculto") {
        menu.className = "menu-visible";
    } else {
        menu.className = "menu-oculto";
    }
}

/* ============================================================
   EJERCICIO 10: MÚLTIPLES (querySelectorAll + Bucle)
   ============================================================ */
function ej10_estilo(size) {
    // SELECTOR: Seleccionamos TODOS los <p> que están dentro del contenedor
    let parrafos = document.querySelectorAll("#ej10_contenedor p");

    // Como es una lista, hay que usar un bucle FOR (Teoría: Bucles)
    for (let i = 0; i < parrafos.length; i++) {
        parrafos[i].style.fontSize = size;
    }
}