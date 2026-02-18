/**
 * 
 * @param {HTMLLIElement} elemento 
 */

const subir = (elemento) => {
    const lista = elemento.parentElement;
    const anterior = elemento.previousElementSibling;

    lista.insertBefore(elemento, anterior);
}