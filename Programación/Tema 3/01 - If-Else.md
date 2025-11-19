# Guía de la Estructura Condicional if-else en Java

El condicional `if-else` es una de las estructuras de control más fundamentales en la programación. Permite que un programa tome decisiones y ejecute diferentes bloques de código basados en si una condición es verdadera o falsa.

---

## 1. Estructura `if` Básica

El bloque `if` evalúa una única condición. Si el resultado de la condición es `true`, se ejecuta el código que está dentro de sus llaves `{}`. Si es `false`, el bloque de código se ignora y el programa continúa.

### Sintaxis
```java
if (condicion) {
    // Código que se ejecuta si la condición es verdadera
}
```

### Ejemplo
```java
int numero = 5;

if (numero > 0) {
    System.out.println("El número es positivo.");
}
```
**Salida:**
```
El número es positivo.
```
En este caso, la condición `numero > 0` (5 > 0) es verdadera, por lo que se ejecuta el `println`.

---

## 2. Estructura `if-else`

La estructura `if-else` proporciona un camino alternativo. Si la condición del `if` es `true`, se ejecuta su bloque de código. Si es `false`, se ejecuta el bloque de código del `else`.

### Sintaxis
```java
if (condicion) {
    // Código que se ejecuta si la condición es verdadera
} else {
    // Código que se ejecuta si la condición es falsa
}
```

### Ejemplo
```java
int numero = -5;

if (numero > 0) {
    System.out.println("El número es positivo.");
} else {
    System.out.println("El número es negativo o cero.");
}
```
**Salida:**
```
El número es negativo o cero.
```
Aquí, la condición `numero > 0` (-5 > 0) es falsa, por lo que se ejecuta el bloque del `else`.

---

## 3. Cadena `if-else if-else`: Múltiples Condiciones

Para evaluar varias condiciones de forma secuencial, se utiliza la estructura `if-else if-else`. El programa evalúa las condiciones en orden y ejecuta el primer bloque cuya condición sea `true`. Si ninguna de las condiciones es verdadera, se ejecuta el bloque `else` final (que es opcional).

### Sintaxis
```java
if (condicion1) {
    // Se ejecuta si la condicion1 es verdadera
} else if (condicion2) {
    // Se ejecuta si la condicion1 es falsa Y la condicion2 es verdadera
} else {
    // Se ejecuta si ninguna de las condiciones anteriores es verdadera
}
```

### Ejemplo
```java
int numero = 0;

if (numero > 0) {
    System.out.println("El número es positivo.");
} else if (numero < 0) {
    System.out.println("El número es negativo.");
} else {
    System.out.println("El número es cero.");
}
```
**Salida:**
```
El número es cero.
```
La primera condición (`numero > 0`) es falsa. La segunda (`numero < 0`) también es falsa. Por lo tanto, se ejecuta el bloque `else` final.

---

## 4. Anidación de `if-else`

Puedes colocar estructuras `if-else` dentro de otras para manejar lógicas más complejas. Esto se conoce como anidación.

### Ejemplo
```java
int numero = 8;

if (numero > 0) {
    // Bloque principal: el número es positivo
    System.out.println("El número es positivo.");

    // Bloque anidado: ¿es par o impar?
    if (numero % 2 == 0) {
        System.out.println("Y además es par.");
    } else {
        System.out.println("Y además es impar.");
    }
} else {
    // Bloque principal: el número es negativo o cero
    System.out.println("El número es negativo o cero.");
}
```
**Salida:**
```
El número es positivo.
Y además es par.
```
Primero se comprueba si el número es positivo. Como lo es, entra en el primer bloque y luego evalúa la condición anidada para determinar si es par.

---

## 5. Uso de Operadores en las Condiciones

Las condiciones de un `if` se construyen utilizando operadores relacionales (para comparar) y lógicos (para combinar condiciones).

### Operadores Relacionales
| Operador | Significado |
| :--- | :--- |
| `==` | Igual a |
| `!=` | Diferente de |
| `>` | Mayor que |
| `<` | Menor que |
| `>=` | Mayor o igual que |
| `<=` | Menor o igual que |

### Operadores Lógicos
| Operador | Significado |
| :--- | :--- |
| `&&` | Y lógico (AND) |
| `||` | O lógico (OR) |
| `!` | Negación lógica (NOT) |

### Ejemplo
```java
int a = 10;
int b = 20;
int c = 30;

// La condición usa el operador lógico AND (&&) para combinar dos comparaciones
if (a < b && b < c) {
    System.out.println("a es menor que b Y b es menor que c.");
} else {
    System.out.println("Las condiciones no se cumplen.");
}
```
**Salida:**
```
a es menor que b Y b es menor que c.
```

---

## 6. Buenas Prácticas

*   **Uso de llaves `{}`:** Utiliza siempre llaves para delimitar los bloques `if` y `else`, incluso si solo contienen una línea de código. Esto mejora la legibilidad y previene errores.
*   **Claridad en las condiciones:** Mantén las expresiones booleanas lo más simples y legibles posible.
*   **Evitar anidación excesiva:** Demasiados niveles de anidación pueden hacer que el código sea muy difícil de leer y depurar.
*   **Alternativa para `else if` múltiples:** Si tienes una larga cadena de `if-else if` para comparar una misma variable con diferentes valores, considera usar una estructura `switch`.