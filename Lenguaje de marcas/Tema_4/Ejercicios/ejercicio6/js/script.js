const botones = document.querySelectorAll('button');
const contador = document.querySelector('h2');

const aumentar = () => {
    contador.textContent++;
    console.log(contador.textContent);
    cambiarColor();
}

const reducir = () => {
    contador.textContent--;
    console.log(contador.textContent);
    cambiarColor();
}

const cambiarColor = () => {

    const valor = parseInt(contador.textContent);

    switch (true) {
        case (valor === 0):
            contador.style.color = 'black';
            break;
        case (valor > 0):
            contador.style.color = 'green';
            break;
        case (valor < 0):
            contador.style.color = 'red';
            break;

        default:
            break;
    }
}

cambiarColor();

botones.forEach(btn => {
    switch (btn.textContent) {
        case '+':
            btn.addEventListener('click', aumentar);
            break;
        case '-':
            btn.addEventListener('click', reducir);
            break;

        default:
            break;
    }
});