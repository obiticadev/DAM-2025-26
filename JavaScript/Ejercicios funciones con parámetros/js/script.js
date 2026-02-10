console.log("Ejercicio 1: Función con parámetros");

function sayHello(name) {
    console.log("Hello " + name);
}

sayHello("Alice");
sayHello("Bob");

console.log("Ejercicio 2: Función con múltiples parámetros");

function calculateSquareArea(lado) {
    area = lado * lado;
    return area;
}

let area1 = calculateSquareArea(5);
console.log("El área del cuadrado es: " + area1);

let area2 = calculateSquareArea(10);
console.log("El área del cuadrado es: " + area2);

console.log("Ejercicio 3: Función con parámetros y retorno de valor");

function calculateTriangleArea(base, height) {
    area = (base * height) / 2;
    return area;
}

let triangleArea1 = calculateTriangleArea(10, 5);
console.log("El área del triángulo es: " + triangleArea1);

let triangleArea2 = calculateTriangleArea(15, 8);
console.log("El área del triángulo es: " + triangleArea2);

console.log("Ejercicio 4: Función con parámetros opcionales");

function calculateCircleArea(radius) {
    area = Math.PI * radius * radius;
    return area;
}

let circleArea1 = calculateCircleArea(5);
console.log("El área del círculo es: " + circleArea1);

let circleArea2 = calculateCircleArea(10);
console.log("El área del círculo es: " + circleArea2);

console.log("Ejercicio 5: Función con parámetros por defecto");

function celsiusToFahrenheit(celsius) {
    fahrenheit = (celsius * 9/5) + 32;
    return fahrenheit;
}

let temp1 = celsiusToFahrenheit(0);
console.log("0 grados Celsius son " + temp1 + " grados Fahrenheit");

let temp2 = celsiusToFahrenheit(100);
console.log("100 grados Celsius son " + temp2 + " grados Fahrenheit");

console.log("Ejercicio 6: Función con parámetros rest");

function fahrenheitToCelsius(fahrenheit) {
    celsius = (fahrenheit - 32) * 5/9;
    return celsius;
}

let temp3 = fahrenheitToCelsius(32);
console.log("32 grados Fahrenheit son " + temp3 + " grados Celsius");

let temp4 = fahrenheitToCelsius(212);
console.log("212 grados Fahrenheit son " + temp4 + " grados Celsius");

function totalPrice(price, tax) {
    total = price + (price * tax);
    return total;
}

let price1 = totalPrice(100, 0.15);
console.log("El precio total es: " + price1);

let price2 = totalPrice(200, 0.20);
console.log("El precio total es: " + price2);

console.log("Ejercicio 7: Función con parámetros y concatenación de cadenas");

function writeMessage(name, material, size, note) {

    let message = name + "ha pedido una caja de " + material + " de tamaño " + size + ". " + note
    return message;
}

let order1 = writeMessage("Alice ", "cartón ", "mediano ", "Por favor, entregue antes del viernes.");
console.log(order1);

let order2 = writeMessage("Bob ", "plástico ", "grande ", "Necesito la caja para el lunes.");
console.log(order2);