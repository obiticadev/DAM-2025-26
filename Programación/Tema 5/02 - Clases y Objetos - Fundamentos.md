# Guía de Clases y Objetos: Fundamentos

En la **Programación Orientada a Objetos (POO)**, los conceptos de **clases** y **objetos** son los pilares fundamentales. Comprender su funcionamiento y relación es esencial para desarrollar aplicaciones correctamente, permitiendo modelar software basado en entidades del mundo real.

---

## 1. ¿Qué son las Clases y los Objetos?

### Clase
Es un **modelo o plantilla** que define un conjunto de características (atributos) y comportamientos (métodos) comunes para un grupo de objetos similares.
*   **Analogía:** La clase actúa como el **plano de un edificio**. No es el edificio en sí, sino las instrucciones para construirlo.
*   **Ejemplo:** La clase `Coche` describe propiedades genéricas como "color", "marca" y "número de ruedas", y acciones como "arrancar" y "frenar".

### Objeto
Es una **instancia de una clase**. Es una entidad específica y concreta creada a partir de la plantilla (clase), con valores propios.
*   **Analogía:** Los objetos son los **edificios construidos** basándose en el plano.
*   **Ejemplo:** Un objeto de la clase `Coche` puede ser un coche específico de color rojo, marca Toyota y con 4 ruedas.

---

## 2. Atributos y Métodos

Los objetos se componen internamente de dos tipos de elementos definidos en la clase:

### Atributos (Datos)
Son las **características** del objeto. Se representan mediante variables dentro de la clase que almacenan datos sobre las propiedades del objeto.
*   **Ejemplo en clase `Coche`:**
    *   `color` (tipo `String`): El color del vehículo.
    *   `marca` (tipo `String`): El fabricante.
    *   `numeroDeRuedas` (tipo `int`): La cantidad de ruedas.

### Métodos (Acciones)
Son las **acciones** que un objeto puede realizar. Se definen como funciones dentro de la clase y pueden interactuar con los atributos o realizar tareas específicas.
*   **Ejemplo en clase `Coche`:**
    *   `arrancar()`: Inicia el motor.
    *   `frenar()`: Detiene el coche.

---

## 3. Estado, Comportamiento y Propiedades

Un objeto combina tres elementos fundamentales que lo definen durante la ejecución del programa:

1.  **Estado:** Describe la **condición actual** del objeto determinada por los valores de sus atributos en un momento dado.
    *   *Ejemplo:* Un coche puede estar "apagado" o "en movimiento".
2.  **Propiedades:** Son las características inherentes del objeto, definidas por sus atributos.
    *   *Ejemplo:* Color, número de ruedas, marca.
3.  **Comportamiento:** Representa las acciones que el objeto es capaz de ejecutar (sus métodos).
    *   *Ejemplo:* Arrancar, girar, frenar.

---

## 4. Creación de Clases en Java

Para implementar una clase en Java, se sigue una estructura sintáctica específica:

1.  **Definir la Clase:** Uso de la palabra clave `class` + Nombre.
2.  **Definir Atributos:** Declaración de variables.
3.  **Definir Métodos:** Implementación de funciones.

### Estructura General
```java
class NombreDeLaClase {
    // Atributos
    TipoDeDato nombreAtributo1;
    TipoDeDato nombreAtributo2;

    // Métodos
    void metodo1() {
        // Código del método
    }

    void metodo2() {
        // Código del método
    }
}
```

---

## 5. Ejemplo Práctico Completo

A continuación se muestra un ejemplo completo modelando un **Monitor**, desde su definición hasta su uso en una clase principal.

### Definición de la Clase (`Monitor`)
```java
class Monitor {
    // Atributos
    String color;
    int ancho;
    int alto;

    // Métodos
    void encender() {
        System.out.println("El monitor está encendido.");
    }

    void apagar() {
        System.out.println("El monitor está apagado.");
    }
}
```

### Uso de la Clase (`Main`)
```java
public class Main {
    public static void main(String[] args) {
        // 1. Crear un objeto (instanciar) de la clase Monitor
        Monitor miMonitor = new Monitor();

        // 2. Asignar valores a los atributos
        miMonitor.color = "Negro";
        miMonitor.ancho = 1920;
        miMonitor.alto = 1080;

        // 3. Usar métodos del objeto
        miMonitor.encender();
        
        // Acceso a atributos para mostrar información
        System.out.println("El monitor tiene una resolución de " + 
                           miMonitor.ancho + "x" + miMonitor.alto + ".");
                           
        miMonitor.apagar();
    }
}
```

**Salida esperada en consola:**
```
El monitor está encendido.
El monitor tiene una resolución de 1920x1080.
El monitor está apagado.
```

---

## 6. Ejercicios Propuestos

Para afianzar los conocimientos, se proponen los siguientes ejercicios prácticos:

1.  Crea una clase `Coche` con atributos: `marca`, `modelo` y `año`.
2.  Define un objeto de la clase `Coche` llamado `miCoche`, asigna valores a sus atributos y muéstralos en consola.
3.  Añade un método `arrancar()` en la clase `Coche` que imprima: "El coche {marca} está arrancando".
4.  Crea una clase `Libro` con atributos `titulo` y `autor`.
5.  Define un objeto `miLibro` de la clase `Libro` y un método `mostrarInfo()` que imprima la información del libro.
6.  Implementa una clase `Calculadora` con un método `sumar(int a, int b)`.
7.  Modifica la clase `Calculadora` para incluir métodos de resta, multiplicación y división.
8.  Crea una clase `Estudiante` con atributos `nombre` y `notaFinal`. Implementa un método para comprobar si está aprobado (nota >= 5).
9.  Diseña una clase `Rectangulo` con atributos `ancho` y `alto`. Añade un método para calcular el área.
10. Implementa una clase `Monitor` con un atributo `resolucion` y métodos para encender y apagar.