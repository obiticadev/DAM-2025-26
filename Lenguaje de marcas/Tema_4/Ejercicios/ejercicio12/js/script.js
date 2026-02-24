const contenedorPadre = document.querySelector('#contenedorPadre');
const boton = document.querySelector('button');

const imagenWeb = 'https://www.evolmind.com/wp-content/uploads/2019/07/%C2%BFPor-que-es-util-la-imagen-como-recurso-educativo.webp';

const agregarContenedor = () => {

    for (let i = 0; i < 5; i++) {
        // 1. Crear el contenedor de la tarjeta
        let contenedor = document.createElement('div');
        contenedor.classList.add('tarjeta'); // Le damos una clase para CSS
        contenedor.style.position = 'relative'; // Necesario para posicionar la 'x'

        // 2. Crear la imagen
        let imagen = document.createElement('img');
        imagen.style.width = '70px';
        imagen.setAttribute('src', imagenWeb);

        // 3. Crear el título
        let titulo = document.createElement('h3');
        titulo.textContent = `Producto ${i + 1}`;

        // 4. Crear el precio
        let precio = document.createElement('p');
        precio.textContent = `${(Math.random() * 100).toFixed(2)}€`;

        // 5. Crear el botón de eliminar (la "X")
        let btnEliminar = document.createElement('button');
        btnEliminar.textContent = 'x';
        btnEliminar.classList.add('btn-X');

        // --- LÓGICA PARA ELIMINAR USANDO e.target ---
        btnEliminar.addEventListener('click', (e) => {
            // e.target es el botón que se ha pulsado
            // parentElement es el 'div' (el contenedor de la tarjeta)
            const tarjetaARemover = e.target.parentElement;
            tarjetaARemover.remove();
        });

        // 6. Montar la estructura (Meter todo dentro del contenedor)
        contenedor.appendChild(btnEliminar);
        contenedor.appendChild(imagen);
        contenedor.appendChild(titulo);
        contenedor.appendChild(precio);

        // 7. Meter la tarjeta completa en el DOM
        contenedorPadre.appendChild(contenedor);

        contenedor.addEventListener('mouseenter', (e) => {
            e.currentTarget.classList.add('tarjeta-hover');
        })

        contenedor.addEventListener('mouseleave', (e) => {
            e.currentTarget.classList.remove('tarjeta-hover');
        })
    }

}

boton.addEventListener('click', agregarContenedor);