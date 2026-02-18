/**
 * 
 * @param {HTMLFormElement} form 
 */

let contador = 0;
const calcular = (form) => {
    let monto = form.monto.value;
    let descuento = form.descuento.value;

    let precioFinal = monto * (100 - descuento) / 100;

    let contenedor = document.createElement('div')
    contenedor.style.border = '1px solid red';
    contenedor.style.padding = '10px'
    contenedor.innerHTML = `
    <h1>${precioFinal}€</h1>
    `;

    if (contador++ === 0) {
        document.body.appendChild(contenedor)

    }

    console.log(monto, descuento, precioFinal, contador);
}