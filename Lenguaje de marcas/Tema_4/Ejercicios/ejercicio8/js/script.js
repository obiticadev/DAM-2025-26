const name = document.getElementById('name');
const edad = document.getElementById('edad');
const ciudad = document.getElementById('ciudad');
const formulario = document.querySelector('form');

/**
 * 
 * @param {SubmitEvent} e 
 */

const agregarElemento = (e) => {
    e.preventDefault();
    if (!document.querySelector('#tabla')) {
        let tabla = document.createElement('table');
        tabla.innerHTML = `
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Edad</th>
                <th>Ciudad</th>
                <th>Botón</th>
            </tr>
        </thead>
        <tbody id="cuerpo">

        </tbody>
        `;
        tabla.classList.add('table');
        document.body.appendChild(tabla);
    }
}

formulario.addEventListener('submit', agregarElemento);