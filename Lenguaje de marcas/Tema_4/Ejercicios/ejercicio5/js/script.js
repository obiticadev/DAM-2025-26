const contenedor = document.getElementById('contenedorPrincipal');
const btnAgrandar = document.getElementById('agrandar');
const btnReducir = document.getElementById('reducir');
const btnCambiarColor = document.getElementById('cambiarColor');
const botones = document.querySelectorAll('button');

const agrandar = () => {
    /*  
    let estilosContenedor = window.getComputedStyle(contenedor);
    let anchoContenedor = parseInt(estilosContenedor.width) + 50;
    console.log(anchoContenedor);
    contenedor.style.width = `${anchoContenedor}px`; 
    */
    if (contenedor.classList.value !== '') {
        contenedor.classList.remove('reducir');
    }
    contenedor.classList.toggle('agrandar')
    console.log(contenedor.classList.value);

}

const reducir = () => {
    contenedor.classList.toggle('reducir')
}
const cambiarColor = () => {
    contenedor.classList.toggle('rojo')
}

botones.forEach(btn => {
    switch (btn.id) {
        case 'agrandar':
            btn.addEventListener('click', agrandar)
            break;
        case 'reducir':
            btn.addEventListener('click', reducir)
            break;
        case 'cambiarColor':
            btn.addEventListener('click', cambiarColor)
            break;

        default:
            break;
    }

});

