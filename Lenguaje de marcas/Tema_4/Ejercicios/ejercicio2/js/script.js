console.log('Ejercicio 2: Agregar elementos a una lista');

const entrada = document.getElementById('entrada');
const boton = document.querySelector('#boton');
const lista = document.querySelector('#lista')

const agregarElemento = () => {
    if (entrada.value.trim() != "") {
        const newItem = document.createElement('li');
        newItem.textContent = entrada.value.trim();
        console.log(newItem.value);
        lista.appendChild(newItem);
        entrada.value = '';
    }
}

boton.addEventListener('click', agregarElemento)