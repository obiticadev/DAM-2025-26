function Hola() {
    alert("Hola Mundo");
}

function cambioDeColor() {
    let r = Math.floor(Math.random() * 256);
    let g = Math.floor(Math.random() * 256);
    let b = Math.floor(Math.random() * 256);
    document.body.style.backgroundColor = `rgb(${r}, ${g}, ${b})`;
}

function añadir() {
    let textoTarea = document.getElementById("name").value;
    let lista = document.getElementById("miLista");
    let newElement = document.createElement("li");

    newElement.style.cssText = "list-style-type: decimal; background-color: coral; color: black;";


    newElement.textContent = textoTarea;
    lista.append(newElement);

    document.getElementById("name").value = "";
}

function eliminar(botonPulsado){
    let listaFrutas = document.getElementById("ListaFrutas");
    listaFrutas.removeChild(botonPulsado.parentElement);
}