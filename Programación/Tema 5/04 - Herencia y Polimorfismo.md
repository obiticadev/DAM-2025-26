# Guía de Herencia y Polimorfismo en Java

La **herencia** y el **polimorfismo** son mecanismos esenciales de la Programación Orientada a Objetos (POO). Permiten crear sistemas flexibles y reutilizables, donde las clases pueden derivar de otras y los objetos pueden comportarse de múltiples formas según el contexto.

---

## 1. ¿Qué es la Herencia?

Es el mecanismo que permite que una clase (**subclase** o hija) adquiera los atributos y métodos de otra clase existente (**superclase** o padre).

### Conceptos Básicos
*   **Clase Padre (Superclase):** Define las propiedades y comportamientos comunes.
*   **Clase Hija (Subclase):** Hereda de la clase padre y puede añadir nuevas funcionalidades o modificar las existentes.

### Ventajas Principales
*   **Reutilización de código:** Evita la redundancia al no tener que reescribir código común.
*   **Extensibilidad:** Facilita la ampliación de funcionalidades en las subclases.
*   **Organización:** Crea una estructura jerárquica lógica.

### Sintaxis en Java
Se utiliza la palabra clave `extends`.

```java
// Clase padre
class Animal { 
    String nombre;
    
    void comer() {
        System.out.println(nombre + " está comiendo.");
    }
}

// Clase hija
class Perro extends Animal { 
    void ladrar() {
        System.out.println(nombre + " está ladrando.");
    }
}

public class Main {
    public static void main(String[] args) {
        Perro miPerro = new Perro();
        miPerro.nombre = "Paquito";
        
        miPerro.comer(); // Método heredado
        miPerro.ladrar(); // Método propio
    }
}
```

---

## 2. Sobrescritura de Métodos (Overriding)

Ocurre cuando una subclase redefine un método heredado de la superclase para cambiar su comportamiento. Se utiliza la anotación `@Override` para indicar explícitamente esta acción.

### Reglas de la Sobrescritura
1.  El nombre, parámetros y tipo de retorno deben coincidir exactamente con el método original.
2.  El método sobrescrito no puede tener un modificador de acceso más restrictivo (ej. no se puede cambiar de `public` a `private`).

```java
class Animal {
    void hacerSonido() {
        System.out.println("El animal hace un sonido genérico.");
    }
}

class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("El perro ladra.");
    }
}
```

---

## 3. El Concepto de Polimorfismo

El polimorfismo permite que un mismo método tenga diferentes comportamientos según el objeto que lo utilice o los parámetros que reciba. En Java existen dos tipos principales:

### A. Polimorfismo en Tiempo de Compilación (Sobrecarga)
Consiste en definir múltiples métodos con el **mismo nombre** pero **diferentes parámetros** dentro de la misma clase.

```java
class Calculadora {
    // Suma enteros
    int sumar(int a, int b) {
        return a + b;
    }
    
    // Suma decimales
    double sumar(double a, double b) {
        return a + b;
    }
    
    // Suma tres números
    int sumar(int a, int b, int c) {
        return a + b + c;
    }
}
```

### B. Polimorfismo en Tiempo de Ejecución
Se basa en la **sobrescritura**. El método que se ejecuta depende del tipo del objeto instanciado, incluso si la variable de referencia es de la clase padre.

```java
public class Main {
    public static void main(String[] args) {
        // Referencias de tipo Animal, objetos de tipos distintos
        Animal miAnimal1 = new Perro();
        Animal miAnimal2 = new Gato(); // Asumiendo clase Gato extends Animal

        miAnimal1.hacerSonido(); // Salida: El perro ladra.
        miAnimal2.hacerSonido(); // Salida: El gato maúlla.
    }
}
```

---

## 4. Uso de `super` y Buenas Prácticas

### La palabra clave `super`
Se utiliza en la subclase para llamar a métodos o constructores de la superclase. Es útil cuando queremos extender la funcionalidad de un método heredado en lugar de reemplazarla totalmente.

```java
class Perro extends Animal {
    @Override
    void comer() {
        super.comer(); // Ejecuta el código de la clase padre
        System.out.println("El perro está comiendo su comida."); // Añade funcionalidad extra
    }
}
```

### Buenas Prácticas
1.  **Herencia con moderación:** No abuses de la herencia; a veces es mejor la composición (usar objetos dentro de otros).
2.  **Sobrescribir con propósito:** Solo modifica métodos si es necesario cambiar el comportamiento.
3.  **Uso correcto de `super`:** Garantiza que la lógica base de la clase padre se mantenga si es necesario.

---

## 5. Ejercicios Propuestos

1.  Implementa una clase `Animal` con un método `hacerSonido()`. Hereda esta clase en `Perro` y `Gato`, sobrescribiendo el método.
2.  Crea un programa donde se utilice un array de tipo `Animal` para almacenar objetos `Perro` y `Gato`.
3.  Implementa una clase `Vehiculo` con un atributo `marca` y un método `mover()`. Hereda en `Coche` y `Bicicleta`, sobrescribiendo el método `mover()`.
4.  Diseña un sistema con una clase base `Empleado` y dos clases derivadas `Gerente` y `Tecnico`. Añade un método `trabajar()` sobrescrito en cada subclase.
5.  Implementa un método genérico que reciba un objeto `Animal` y llame a su método `hacerSonido()`.
6.  Implementa una clase `Forma` con un método `calcularArea()`. Hereda en `Rectangulo` y `Circulo`, sobrescribiendo el método para calcular áreas específicas.
7.  Crea un programa que use polimorfismo para calcular el área de diferentes formas almacenadas en un array.
8.  Diseña una jerarquía de clases que simule un sistema de transporte (por ejemplo, `Vehiculo`, `Coche`, `Avion`).
9.  Implementa un ejemplo práctico de polimorfismo en tiempo de ejecución con una clase base `Instrumento` y subclases `Guitarra` y `Piano`.
10. Usa polimorfismo para implementar una función que imprima los detalles de cualquier clase derivada de `Empleado`.