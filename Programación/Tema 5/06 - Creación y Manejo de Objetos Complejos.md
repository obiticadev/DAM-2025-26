# Guía de Creación y Manejo de Objetos Complejos

En el desarrollo de aplicaciones reales, es fundamental garantizar que los objetos se inicialicen correctamente y saber gestionarlos en conjunto. Para ello, Java ofrece **constructores** para la inicialización y estructuras como **arrays** y **colecciones** para manejar múltiples instancias simultáneamente.

---

## 1. Métodos Constructores

Un **constructor** es un método especial que se ejecuta automáticamente cuando se crea una instancia de una clase (`new`). Su objetivo principal es inicializar el objeto con un estado válido.

### Características Principales
*   **Nombre:** Debe coincidir exactamente con el nombre de la clase.
*   **Sin Tipo de Retorno:** No devuelve nada, ni siquiera `void`.
*   **Ejecución Automática:** Se invoca al usar la palabra clave `new`.
*   **Sobrecarga:** Una clase puede tener múltiples constructores con diferentes parámetros.

### Tipos de Constructores

#### A. Constructor por Defecto
Es aquel que no recibe parámetros. Suele asignar valores predeterminados a los atributos.

```java
class Coche {
    String marca;
    int velocidad;

    // Constructor por defecto
    public Coche() {
        marca = "Sin marca";
        velocidad = 0;
    }
}
```

#### B. Constructor Parametrizado
Recibe argumentos para inicializar los atributos con valores específicos al momento de la creación.

```java
class Coche {
    String marca;
    int velocidad;

    // Constructor parametrizado
    public Coche(String marca, int velocidad) {
        this.marca = marca;           // Uso de 'this' para diferenciar
        this.velocidad = velocidad;   // atributo de parámetro
    }
}

// Uso:
// Coche miCoche = new Coche("Toyota", 120);
```

### Uso de la Palabra Clave `this`
Se utiliza dentro de una clase para referirse al **objeto actual**. Es esencial cuando los parámetros del constructor tienen el mismo nombre que los atributos de la clase, permitiendo diferenciarlos.

---

## 2. Manejo de Múltiples Objetos

Para trabajar con varios objetos a la vez, se utilizan estructuras de datos que agrupan instancias.

### A. Arrays de Objetos
Permiten almacenar un número fijo de objetos del mismo tipo.

```java
public class Main {
    public static void main(String[] args) {
        // Crear un array de 3 coches
        Coche[] coches = new Coche[3];
        
        coches[0] = new Coche("Toyota", 120);
        coches[1] = new Coche("Honda", 100);
        coches[2] = new Coche("Ford", 140);

        // Recorrer el array
        for (int i = 0; i < coches.length; i++) {
            coches[i].mostrarDatos();
        }
    }
}
```

### B. Colecciones (`ArrayList`)
Las colecciones como `ArrayList` son más flexibles que los arrays, ya que su tamaño puede cambiar dinámicamente (crecer o decrecer).

```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Crear una colección dinámica
        ArrayList<Coche> coches = new ArrayList<>();
        
        coches.add(new Coche("Toyota", 120));
        coches.add(new Coche("Honda", 100));
        coches.add(new Coche("Ford", 140));

        // Recorrer la colección (bucle for-each)
        for (Coche coche : coches) {
            coche.mostrarDatos();
        }
    }
}
```

---

## 3. Ejemplo Integral: Aplicación Modular

Este ejemplo combina **herencia**, **polimorfismo**, **constructores** (`super`) y **arrays** para gestionar un sistema de vehículos.

### Clase Base Abstracta (`Vehiculo`)
```java
abstract class Vehiculo {
    protected String marca;
    protected int velocidad;

    public Vehiculo(String marca) {
        this.marca = marca;
        this.velocidad = 0;
    }

    public abstract void acelerar();

    public void mostrarDatos() {
        System.out.println("Marca: " + marca + ", Velocidad: " + velocidad + " km/h");
    }
}
```

### Clases Derivadas (`Coche` y `Moto`)
```java
class Coche extends Vehiculo {
    public Coche(String marca) {
        super(marca); // Llama al constructor de la clase padre
    }

    @Override
    public void acelerar() {
        velocidad += 20; // Comportamiento específico
    }
}

class Moto extends Vehiculo {
    public Moto(String marca) {
        super(marca);
    }

    @Override
    public void acelerar() {
        velocidad += 30; // Comportamiento específico
    }
}
```

### Programa Principal (Polimorfismo)
```java
public class Main {
    public static void main(String[] args) {
        // Array de la clase base conteniendo objetos derivados
        Vehiculo[] vehiculos = new Vehiculo[3];
        
        vehiculos[0] = new Coche("Toyota");
        vehiculos[1] = new Moto("Yamaha");
        vehiculos[2] = new Coche("Honda");

        // Polimorfismo: Se ejecuta el método acelerar() correspondiente a cada objeto
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
            vehiculo.mostrarDatos();
        }
    }
}
```

**Salida esperada:**
```
Marca: Toyota, Velocidad: 20 km/h
Marca: Yamaha, Velocidad: 30 km/h
Marca: Honda, Velocidad: 20 km/h
```

---

## 4. Ejercicios Propuestos

1.  Diseña una clase `Persona` con atributos nombre y edad. Añade un constructor parametrizado para inicializar los atributos.
2.  Crea una clase `Empresa` que tenga un array de empleados. Añade métodos para agregar empleados y mostrar su información.
3.  Implementa una clase `Banco` con una colección dinámica (`ArrayList`) para gestionar cuentas bancarias.
4.  Diseña una clase `Inventario` que utilice un mapa (`HashMap`) para almacenar productos y sus cantidades.
5.  Usa un array de objetos `Forma` (heredado por `Rectangulo` y `Circulo`) para calcular áreas polimórficamente.
6.  Implementa un sistema de gestión de estudiantes que utilice una colección (`HashMap`) con sus nombres como clave y sus calificaciones como valor.
7.  Crea una clase `Vehiculo` que incluya un atributo tipo y métodos para filtrar y mostrar vehículos por tipo en un array.
8.  Diseña un programa modularizado con clases y paquetes que gestionen una tienda, incluyendo `Cliente`, `Producto` y `Pedido`.
9.  Crea un sistema que registre y actualice la velocidad de varios `Vehiculo` en tiempo real utilizando una lista dinámica.
10. Diseña un sistema donde los `Clientes` puedan realizar pedidos a través de una clase `Tienda`. Usa una colección para registrar los pedidos.