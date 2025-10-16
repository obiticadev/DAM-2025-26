# Guía de la Clase Math en Java

La clase `Math` de Java, que se encuentra en el paquete `java.lang`, proporciona un conjunto de métodos estáticos para realizar operaciones matemáticas comunes. Al ser parte de `java.lang`, se importa automáticamente en todos los programas, por lo que no es necesario añadir una sentencia `import` para utilizarla.

---

## 1. Funciones Aritméticas y de Redondeo

Estos métodos se utilizan para operaciones básicas y para redondear números decimales.

*   `Math.abs(x)`: Devuelve el valor absoluto de `x`.
*   `Math.ceil(x)`: Redondea `x` hacia arriba al entero más cercano.
*   `Math.floor(x)`: Redondea `x` hacia abajo al entero más cercano.
*   `Math.round(x)`: Redondea `x` al entero más cercano (redondeo estándar).

### Ejemplo:
```java
public class FuncionesAritmeticas {
    public static void main(String[] args) {
        double numero = -5.75;
        System.out.println("Valor absoluto: " + Math.abs(numero));       // Salida: 5.75
        System.out.println("Redondeo hacia arriba (ceil): " + Math.ceil(numero)); // Salida: -5.0
        System.out.println("Redondeo hacia abajo (floor): " + Math.floor(numero)); // Salida: -6.0
        System.out.println("Redondeo estándar (round): " + Math.round(numero));   // Salida: -6
    }
}
```

---

## 2. Funciones Trigonométricas

Estos métodos operan con ángulos expresados en **radianes**.

*   `Math.sin(x)`: Devuelve el seno del ángulo `x`.
*   `Math.cos(x)`: Devuelve el coseno del ángulo `x`.
*   `Math.tan(x)`: Devuelve la tangente del ángulo `x`.

### Ejemplo:
```java
public class FuncionesTrigonometricas {
    public static void main(String[] args) {
        // 45 grados en radianes
        double anguloEnRadianes = Math.PI / 4; 
        
        System.out.println("Seno de 45°: " + Math.sin(anguloEnRadianes));   // Salida: 0.7071...
        System.out.println("Coseno de 45°: " + Math.cos(anguloEnRadianes)); // Salida: 0.7071...
        System.out.println("Tangente de 45°: " + Math.tan(anguloEnRadianes)); // Salida: 1.0
    }
}
```

---

## 3. Funciones Exponenciales y Logarítmicas

*   `Math.exp(x)`: Devuelve el número de Euler (`e`) elevado a la potencia `x`.
*   `Math.log(x)`: Devuelve el logaritmo natural (base `e`) de `x`.
*   `Math.log10(x)`: Devuelve el logaritmo en base 10 de `x`.

### Ejemplo:
```java
public class FuncionesLogaritmicas {
    public static void main(String[] args) {
        System.out.println("e^2: " + Math.exp(2));               // Salida: 7.389...
        System.out.println("Logaritmo natural de e: " + Math.log(Math.E)); // Salida: 1.0
        System.out.println("Logaritmo base 10 de 100: " + Math.log10(100)); // Salida: 2.0
    }
}
```

---

## 4. Raíz Cuadrada y Potencias

*   `Math.sqrt(x)`: Devuelve la raíz cuadrada de `x`.
*   `Math.pow(base, exponente)`: Devuelve el valor de `base` elevado al `exponente`.

### Ejemplo:
```java
public class RaizYPotencias {
    public static void main(String[] args) {
        System.out.println("Raíz cuadrada de 16: " + Math.sqrt(16)); // Salida: 4.0
        System.out.println("2 elevado a la 3: " + Math.pow(2, 3));    // Salida: 8.0
    }
}
```

---

## 5. Valor Mínimo y Máximo

*   `Math.min(a, b)`: Devuelve el menor de los dos números `a` y `b`.
*   `Math.max(a, b)`: Devuelve el mayor de los dos números `a` y `b`.

### Ejemplo:
```java
public class MinMax {
    public static void main(String[] args) {
        System.out.println("El mínimo entre 5 y 10 es: " + Math.min(5, 10)); // Salida: 5
        System.out.println("El máximo entre 5 y 10 es: " + Math.max(5, 10)); // Salida: 10
    }
}
```

---

## 6. Generación de Números Aleatorios

*   `Math.random()`: Devuelve un número `double` pseudoaleatorio mayor o igual que `0.0` y menor que `1.0`.

### Ejemplo: Generar un número entero aleatorio en un rango
Para generar un número entero entre `min` y `max` (ambos incluidos), se usa la siguiente fórmula:
```java
int min = 1;
int max = 100;
int numeroAleatorio = (int) (Math.random() * (max - min + 1)) + min;
System.out.println("Número aleatorio entre 1 y 100: " + numeroAleatorio);
```

---

## 7. Constantes Matemáticas

La clase `Math` también proporciona dos constantes `double` de alta precisión.

*   `Math.PI`: El valor de π (Pi).
*   `Math.E`: La base de los logaritmos naturales (`e`).

### Ejemplo:
```java
public class ConstantesMath {
    public static void main(String[] args) {
        System.out.println("Valor de PI: " + Math.PI); // Salida: 3.14159...
        System.out.println("Valor de e: " + Math.E);   // Salida: 2.71828...
    }
}
```