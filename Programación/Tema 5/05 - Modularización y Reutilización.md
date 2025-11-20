# Guía de Modularización y Reutilización en Java

La **modularización** es una práctica esencial en el desarrollo de software que consiste en dividir un programa en partes más pequeñas y manejables denominadas **módulos**. En la Programación Orientada a Objetos (POO) en Java, estos módulos se organizan principalmente mediante **paquetes** (packages), lo que facilita el mantenimiento, la comprensión y la reutilización del código.

---

## 1. Importancia de la Modularización

Dividir el software en unidades lógicas ofrece beneficios críticos para el desarrollo:

*   **Facilidad de mantenimiento:** Al aislar funcionalidades, es más sencillo identificar, corregir y actualizar errores en partes específicas sin afectar al sistema completo.
*   **Reutilización del código:** Las clases y paquetes pueden usarse en múltiples proyectos, ahorrando tiempo y esfuerzo.
*   **Escalabilidad:** Los programas modulares son más fáciles de expandir. Cada módulo es una unidad independiente que puede crecer sin alterar otros componentes.
*   **Organización lógica:** Estructura el código de forma comprensible para desarrolladores actuales y futuros.

---

## 2. Organización del Código en Paquetes

Un **paquete** en Java es, físicamente, una carpeta que agrupa clases relacionadas. Su función es modularizar el programa y evitar conflictos de nombres (por ejemplo, dos clases llamadas `Coche` en librerías diferentes).

### Cómo crear y usar paquetes

**1. Estructura de Carpetas:**
Los paquetes deben reflejarse en el sistema de archivos.
```text
proyecto/
 └── vehiculos/       <-- Paquete
      ├── Coche.java
      ├── Moto.java
 └── principal/       <-- Paquete
      ├── Main.java
```

**2. Declaración (`package`):**
La primera línea del archivo Java debe indicar a qué paquete pertenece.
```java
package vehiculos;

public class Coche {
    // Contenido...
}
```

**3. Importación (`import`):**
Para usar una clase que está en otro paquete, se debe importar.
```java
package principal;

import vehiculos.Coche; // Importar la clase

public class Main {
    // Uso de Coche...
}
```

---

## 3. Reutilización de Clases

La capacidad de reutilizar código es uno de los mayores beneficios de la POO.

### Ventajas Clave
1.  **Ahorro de tiempo y esfuerzo:** Se integran clases ya probadas en nuevos proyectos con modificaciones mínimas.
2.  **Uniformidad:** Promueve la consistencia lógica entre diferentes proyectos.
3.  **Facilidad de mantenimiento:** Si se actualiza una clase reutilizada (por ejemplo, corrigiendo un bug), la mejora se refleja en todos los proyectos que la usan.

---

## 4. Modularización en la Práctica: Ejemplo Completo

A continuación se modela un sistema de gestión de vehículos utilizando herencia y organización por paquetes.

### Paso 1: Definir la Clase Base (`package vehiculos`)
Creamos una clase abstracta con los atributos y métodos comunes.

```java
package vehiculos;

public abstract class Vehiculo {
    protected String marca;
    protected int velocidad;

    public Vehiculo(String marca) {
        this.marca = marca;
        this.velocidad = 0;
    }

    public abstract void acelerar(); // Método abstracto

    public String getMarca() {
        return marca;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
```

### Paso 2: Extender la Clase Base (`package vehiculos`)
Clases concretas que heredan de `Vehiculo`.

**Clase Coche:**
```java
package vehiculos;

public class Coche extends Vehiculo {
    public Coche(String marca) {
        super(marca);
    }

    @Override
    public void acelerar() {
        velocidad += 20;
        System.out.println(marca + " ha acelerado. Velocidad actual: " + velocidad + " km/h.");
    }
}
```

**Clase Moto:**
```java
package vehiculos;

public class Moto extends Vehiculo {
    public Moto(String marca) {
        super(marca);
    }

    @Override
    public void acelerar() {
        velocidad += 30;
        System.out.println(marca + " ha acelerado. Velocidad actual: " + velocidad + " km/h.");
    }
}
```

### Paso 3: Programa Principal (`package principal`)
Importamos las clases necesarias para ejecutarlas.

```java
package principal;

import vehiculos.Coche;
import vehiculos.Moto;

public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche("Toyota");
        Moto miMoto = new Moto("Yamaha");

        miCoche.acelerar();
        miMoto.acelerar();
    }
}
```

**Salida esperada:**
```
Toyota ha acelerado. Velocidad actual: 20 km/h.
Yamaha ha acelerado. Velocidad actual: 30 km/h.
```

---

## 5. Ventajas de la Modularización (Resumen)

| Ventaja | Descripción |
| :--- | :--- |
| **Facilidad de Depuración** | Los errores son más fáciles de localizar al estar aislados en módulos independientes. |
| **Reutilización** | Las clases pueden importarse y usarse en múltiples proyectos. |
| **Escalabilidad** | Es sencillo agregar nuevas funcionalidades sin romper el código existente. |
| **Trabajo Colaborativo** | Cada desarrollador puede trabajar en un paquete o módulo separado sin generar conflictos. |

---

## 6. Ejercicios Propuestos

1.  Crea un paquete llamado `vehiculos` que contenga las clases `Coche` y `Moto`.
2.  Declara un paquete `principal` donde se importe y utilice la clase `Coche` del paquete `vehiculos`.
3.  Añade un método en la clase `Coche` que imprima su marca. Llama a este método desde la clase principal.
4.  Modifica la estructura del proyecto para incluir un nuevo paquete `transportes`. Mueve las clases `Coche` y `Moto` a este paquete.
5.  Diseña una clase base `Vehiculo` dentro del paquete `transportes`. Haz que `Coche` y `Moto` hereden de esta clase.
6.  Usa `import static` para acceder a un método estático de la clase `Math` y calcula la velocidad en km/h de un objeto `Moto`.
7.  Implementa un programa que utilice un array de objetos `Vehiculo` con elementos provenientes del paquete `transportes`.
8.  Modifica el paquete `principal` para incluir una clase `GestorDeVehiculos` que calcule el número total de vehículos creados.
9.  Crea un programa modularizado con paquetes separados para `empleados`, `clientes` y `operaciones`.
10. Usa paquetes para organizar un sistema educativo con clases `Profesor`, `Estudiante` y `Curso`. Cada clase debe estar en un paquete diferente.