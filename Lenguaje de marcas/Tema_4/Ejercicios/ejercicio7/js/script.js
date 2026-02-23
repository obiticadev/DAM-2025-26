const formulario = document.querySelector('form');
const nombre = document.getElementById('nombre');
const correo = document.getElementById('email');
const lista = document.getElementById('lista');

/**
 * 
 * @param {SubmitEvent} e 
 */

const validar = (e) => {
    e.preventDefault();

    const error = formulario.querySelectorAll('.msg-error');
    error.forEach(element => {
        element.remove();
    });
    let name;
    let email;
    if (nombre.value.trim() === '') {
        const msg = document.createElement('p');
        msg.textContent = 'Introduce un nombre';
        msg.style.color = 'red';
        msg.classList.add('msg-error');
        formulario.appendChild(msg);
    } else {
        name = nombre.value.trim();
        nombre.value = '';
    }
    if (correo.value.trim() === '') {
        const msg = document.createElement('p');
        msg.textContent = 'Introduce un correo';
        msg.style.color = 'red';
        msg.classList.add('msg-error');
        formulario.appendChild(msg);
    } else {
        email = correo.value.trim();
        correo.value = '';
    }
    if (name !== undefined && email !== undefined) {
        let li = document.createElement('li');
        li.textContent = `El nombre es ${name} y el correo ${email}`;
        lista.appendChild(li);
    }
}

formulario.addEventListener('submit', validar);
