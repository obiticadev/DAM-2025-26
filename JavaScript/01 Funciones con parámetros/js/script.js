console.log("Ejercicio 1: Función con parámetros");

const sayHello = name => {
    console.log("Hello " + name);
}

sayHello("Alice");
sayHello("Bob");

console.log("Ejercicio 2: Función con múltiples parámetros");

const calculateSquareArea = lado => lado * lado;

let area1 = calculateSquareArea(5);
console.log("El área del cuadrado es: " + area1);

let area2 = calculateSquareArea(10);
console.log("El área del cuadrado es: " + area2);

console.log("Ejercicio 3: Función con parámetros y retorno de valor");

const calculateTriangleArea = (base, height) => base * height / 2

let triangleArea1 = calculateTriangleArea(10, 5);
console.log("El área del triángulo es: " + triangleArea1);

let triangleArea2 = calculateTriangleArea(15, 8);
console.log("El área del triángulo es: " + triangleArea2);

console.log("Ejercicio 4: Función con parámetros opcionales");

const calculateCircleArea = radius => Math.PI * Math.pow(radius, 2);

let circleArea1 = calculateCircleArea(5);
console.log("El área del círculo es: " + circleArea1);

let circleArea2 = calculateCircleArea(10);
console.log("El área del círculo es: " + circleArea2);

console.log("Ejercicio 5: Función con parámetros por defecto");

const celsiusToFahrenheit = celsius => (celsius * 9 / 5) + 32;

let temp1 = celsiusToFahrenheit(0);
console.log("0 grados Celsius son " + temp1 + " grados Fahrenheit");

let temp2 = celsiusToFahrenheit(100);
console.log("100 grados Celsius son " + temp2 + " grados Fahrenheit");

console.log("Ejercicio 6: Función con parámetros rest");

const fahrenheitToCelsius = fahrenheit => (fahrenheit - 32) * 5 / 9;

let temp3 = fahrenheitToCelsius(32);
console.log("32 grados Fahrenheit son " + temp3 + " grados Celsius");

let temp4 = fahrenheitToCelsius(212);
console.log("212 grados Fahrenheit son " + temp4 + " grados Celsius");

const totalPrice = (price, tax) => price + (price * tax);

let price1 = totalPrice(100, 0.15);
console.log("El precio total es: " + price1);

let price2 = totalPrice(200, 0.20);
console.log("El precio total es: " + price2);

console.log("Ejercicio 7: Función con parámetros y concatenación de cadenas");

const writeMessage = (name, material, size, note) => name + "ha pedido una caja de " + material + " de tamaño " + size + ". " + note;

let order1 = writeMessage("Alice ", "cartón ", "mediano ", "Por favor, entregue antes del viernes.");
console.log(order1);

let order2 = writeMessage("Bob ", "plástico ", "grande ", "Necesito la caja para el lunes.");
console.log(order2);

console.log("Ejercicio 1: Promedio de tres parámetros");

const promedio = (num1, num2, num3) => {
    let suma = num1 + num2 + num3;
    let promedio = suma / 3;

    return promedio;
};

console.log(promedio(1, 5, 6));

console.log("Ejercicio 2: Descuento sobre el precio")

const descuentoPrecio = (descuento, precio) => {
    let descuentoReal = descuento / 100;
    let precioFinal = precio - (precio * descuentoReal);
    return precioFinal;
}

console.log(descuentoPrecio(20, 120));

console.log('Ejercicio 3: Concatenar con guión');

const concat = (string1, string2) => string1 + '-' + string2;

console.log(concat('Hola', 'Mundo'));

console.log('Ejercicio4 : Transformación de km a m');

const transformKMtoM = km => km * 1000;

console.log(transformKMtoM(1.23) + ' km');

console.log('Ejercicio 5: Horas a segundos');

const horasToSeconds = hours => hours * 3600;

console.log(horasToSeconds(2.0) + 's');

console.log('Ejercicio 6: Combinación de funciones');

const combinatedFunctions = input => {
    let metros = transformKMtoM(input);
    let seconds = horasToSeconds(1);
    return metros / seconds;
}

let velocidadKM = 35;
console.log(velocidadKM + ' Km/h es igual a ' + combinatedFunctions(35) + ' m/s');