const button = document.querySelector('#ejercicio1');

const cambiarColor = () => {
    const r = Math.floor(Math.random()*256);
    const g = Math.floor(Math.random()*256);
    const b = Math.floor(Math.random()*256);

    document.body.style.backgroundColor = `rgb(${r},${g},${b})`;
}

button.addEventListener('click', cambiarColor)



