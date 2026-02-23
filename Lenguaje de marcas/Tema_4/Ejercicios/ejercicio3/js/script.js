const lista = document.querySelector('#lista');

const eliminar = (e) => {
    const element = e.target.closest('li');
    element.remove();
}

lista.addEventListener('click', eliminar)