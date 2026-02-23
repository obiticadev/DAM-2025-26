const imgPrincipal = document.querySelector('#imagenPrincipal');
const imagenes = document.querySelectorAll('.imagen');

const cambiarTamaño = (e) => {
    const imgSelect = e.target.closest('img');
    const srcSelect = imgSelect.getAttribute('src');
    const srcPrincipal = imgPrincipal.getAttribute('src');
    imgPrincipal.setAttribute('src', srcSelect);
    imgSelect.setAttribute('src', srcPrincipal);
}

imagenes.forEach(element => {
    element.addEventListener('click', cambiarTamaño)
});