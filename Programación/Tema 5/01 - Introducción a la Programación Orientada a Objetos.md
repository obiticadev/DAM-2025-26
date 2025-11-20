# Guía de Introducción a la Programación Orientada a Objetos

La **Programación Orientada a Objetos (POO)** es un paradigma de programación que organiza el software en unidades llamadas **objetos**. La idea principal es modelar el mundo real en el software, facilitando la representación de entidades (concretas o abstractas) y la interacción entre ellas.

---

## 1. ¿Qué es la POO? Conceptos Básicos

Cada objeto representa una entidad y posee dos características principales:

*   **Propiedades (Atributos):** Datos que describen al objeto (ej. color, marca).
*   **Comportamientos (Métodos):** Acciones que el objeto puede realizar (ej. arrancar, frenar).

### Ejemplo del Mundo Real
Piensa en un **coche**.
*   **Atributos:** Color, marca, número de ruedas.
*   **Métodos:** Arrancar, frenar, girar.

En términos de programación:
*   El **Objeto** es el coche en sí mismo.
*   La **Clase** es su diseño conceptual o "molde" para crear coches.

### Importancia en el Desarrollo de Software
Este enfoque ha transformado el desarrollo, especialmente en proyectos complejos, debido a:
1.  **Modularidad:** Divide el programa en pequeñas unidades, haciéndolo más fácil de entender y modificar.
2.  **Reutilización:** Clases y métodos pueden usarse en diferentes proyectos.
3.  **Mantenimiento:** Las actualizaciones son más simples; los cambios se limitan a módulos específicos.
4.  **Adaptación al Mundo Real:** Permite modelar relaciones naturales (ej. un perro hereda de un animal).

---

## 2. POO vs. Programación Procedural

Aunque ambos paradigmas son útiles, la POO ofrece ventajas clave en escalabilidad y mantenimiento.

| Aspecto | Programación Procedural | Programación Orientada a Objetos |
| :--- | :--- | :--- |
| **Organización** | Secuencia lineal de funciones. | Código dividido en clases y objetos. |
| **Reutilización** | Limitada; requiere copiar y pegar. | Alta; clases y métodos reutilizables. |
| **Mantenimiento** | Dificultoso en proyectos grandes. | Más sencillo gracias a la modularización. |
| **Escalabilidad** | Difícil por dependencia entre funciones. | Alta, al trabajar con módulos independientes. |

---

## 3. Componentes Principales: Clases y Objetos

### Definiciones
*   **Clase:** Es un molde o plantilla que define los atributos y métodos comunes a un grupo de objetos.
*   **Objeto:** Es una instancia de una clase. Tiene valores específicos para los atributos definidos.

### Ejemplo en Java
Creación de una clase `Coche` y su instanciación en un objeto.

```java
class Coche {
    // Atributos
    String color;
    String marca;

    // Métodos
    void arrancar() {
        System.out.println("El coche está arrancando.");
    }
    void frenar() {
        System.out.println("El coche está frenando.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear un objeto de la clase Coche
        Coche miCoche = new Coche();
        miCoche.color = "Rojo";
        miCoche.marca = "Toyota";

        // Usar métodos del objeto
        miCoche.arrancar();
        miCoche.frenar();
    }
}
```

---

## 4. Pilares Fundamentales de la POO

### A. Encapsulación
Consiste en ocultar los datos internos de una clase y exponer solo las funcionalidades necesarias.
*   **Ventajas:** Protege datos de accesos indebidos y facilita el mantenimiento.

```java
class Coche {
    private String color; // Atributo encapsulado

    // Método getter
    public String getColor() {
        return color;
    }

    // Método setter
    public void setColor(String color) {
        this.color = color;
    }
}
```

### B. Herencia
Permite que una clase (**subclase**) herede atributos y métodos de otra clase (**superclase**), promoviendo la reutilización de código.

```java
class Animal {
    void comer() {
        System.out.println("El animal está comiendo.");
    }
}

class Perro extends Animal {
    void ladrar() {
        System.out.println("El perro ladra.");
    }
}

// Uso en Main:
// Perro miPerro = new Perro();
// miPerro.comer(); (Heredado)
// miPerro.ladrar(); (Propio)
```

### C. Polimorfismo
Permite que un mismo método tenga diferentes comportamientos según el contexto.
*   **Sobrecarga:** Mismo nombre, diferentes parámetros.
*   **Sobrescritura:** Subclases redefinen métodos de la superclase (usando `@Override`).

```java
class Gato extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("El gato maúlla.");
    }
}
```
*Nota: La anotación `@Override` indica explícitamente que se está sobrescribiendo un método, ayudando a prevenir errores.*

---

## 5. Ejercicios Propuestos

A continuación se listan los ejercicios prácticos y teóricos extraídos del documento para reforzar el aprendizaje:

1.  **(Teórico)** Explica con tus propias palabras qué es la POO y nombra al menos 3 ventajas.
2.  **(Teórico)** Compara las diferencias entre Programación Procedural y POO con ejemplos.
3.  Define una clase llamada `Persona` con atributos `nombre` y `edad`.
4.  Define un objeto de la clase `Persona` llamado `juan`, asigna valores y muéstralos en consola.
5.  Crea un método en `Persona` llamado `saludar` que imprima "Hola, soy {nombre}".
6.  ¿Qué sucede si intentas crear una clase con un método cuyo nombre no coincide con el de la clase? Investiga y pruébalo.
7.  Modifica la clase `Persona` para añadir un atributo `nacionalidad` con valor predeterminado.
8.  **(Teórico)** ¿Qué representa el concepto de "objeto" en la POO? Da un ejemplo del mundo real.
9.  ¿Por qué es importante la modularización en la POO? Apóyate en un ejemplo.
10. Diseña una jerarquía simple de clases con una base `Animal` y dos derivadas `Perro` y `Gato`.