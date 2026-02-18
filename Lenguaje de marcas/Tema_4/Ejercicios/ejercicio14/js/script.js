/**
 * 
 * @param {HTMLFormElement} formulario 
 */
const cargarCV = (formulario) => {
    let newSection = document.getElementById('contenedor');

    let nombre = formulario.nombre.value;

    let correo = formulario.correo.value;
    let categoria = formulario.Clase.value;
    let texto = formulario.experience.value;

    newSection.innerHTML = `
        <h1>${nombre}</h1>
        <h2>Contacto: ${correo}</h2>
        <h2>Categoría: ${categoria}</h2>
        <ul>
            <li>${categoria}</li>
        </ul>
        <p>${texto}</p>
    `;

    console.log(nombre, correo, categoria, texto);
}