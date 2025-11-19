# Guía del Tipo de Dato enum en Java

En Java, el tipo de dato **`enum`** (abreviatura de enumeración) es un tipo especial que permite definir un conjunto de constantes predefinidas. Los `enum` son ideales para representar un grupo fijo de valores relacionados, como los días de la semana, los meses del año, los palos de una baraja o los estados de un proceso.

---

## 1. ¿Qué es un `enum`?

Un `enum` es, en esencia, una clase especial que representa un grupo de constantes.

### Características Clave
*   **Conjunto Fijo de Constantes:** Un `enum` tiene un número finito de valores posibles, que se definen en su propia declaración.
*   **Seguridad de Tipos (Type Safety):** Cada `enum` define su propio tipo de dato. Esto significa que una variable de tipo `Dia` solo puede contener los valores definidos en `Dia` (LUNES, MARTES, etc.), evitando errores por asignación de valores incorrectos (como un número o una cadena de texto).
*   **Son Objetos:** Aunque se usan como constantes, los valores de un `enum` son objetos. Esto les permite tener constructores, métodos y atributos, haciéndolos mucho más potentes que las constantes estáticas tradicionales.
*   **Instancias Limitadas:** No se pueden crear nuevas instancias de un `enum` usando la palabra clave `new`. Los únicos objetos disponibles son los que se definen como constantes dentro del `enum`.

---

## 2. Sintaxis y Uso Básico

### Declaración
Para declarar un `enum`, se utiliza la palabra clave `enum` seguida del nombre y una lista de sus constantes entre llaves `{}`.

```java
// Sintaxis básica
public enum NombreEnum {
    CONSTANTE1,
    CONSTANTE2,
    CONSTANTE3
}

// Ejemplo práctico
public enum Dia {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO
}
```

### Uso
Una vez definido, puedes declarar variables de ese tipo y asignarles una de las constantes.

```java
public class TestEnum {
    public static void main(String[] args) {
        // Declarar y asignar una variable de tipo Dia
        Dia diaActual = Dia.MARTES;

        // Los enums se pueden comparar de forma segura con ==
        if (diaActual == Dia.MARTES) {
            System.out.println("Hoy es Martes.");
        } else {
            System.out.println("Hoy no es Martes.");
        }
    }
}
```

---

## 3. Métodos Útiles de los `enum`

Los `enum` vienen con varios métodos integrados muy útiles.

*   `values()`: Devuelve un **array** que contiene todas las constantes del `enum` en el orden en que fueron declaradas. Es ideal para iterar sobre ellas.
*   `name()`: Devuelve el nombre de la constante en forma de `String` (ej. `"LUNES"`).
*   `ordinal()`: Devuelve la posición de la constante en la declaración del `enum`, comenzando desde `0`.

### Ejemplo de Iteración y Métodos
```java
public class RecorriendoEnum {
    public static void main(String[] args) {
        System.out.println("Días de la semana:");
        
        // Iterar sobre todas las constantes del enum Dia
        for (Dia dia : Dia.values()) {
            System.out.println(
                "Día: " + dia.name() + " - Posición: " + dia.ordinal()
            );
        }
        
        // Obtener una constante específica
        Dia unDia = Dia.MIERCOLES;
        System.out.println("\nAnálisis del día: " + unDia.name());
        System.out.println("Su posición es la: " + unDia.ordinal());
    }
}
```
**Salida:**
```
Días de la semana:
Día: LUNES - Posición: 0
Día: MARTES - Posición: 1
Día: MIERCOLES - Posición: 2
Día: JUEVES - Posición: 3
Día: VIERNES - Posición: 4
Día: SABADO - Posición: 5
Día: DOMINGO - Posición: 6

Análisis del día: MIERCOLES
Su posición es la: 2
```

---

## 4. Uso de `enum` en Sentencias `switch`

Los `enum` se integran perfectamente con la estructura `switch`, lo que permite escribir código mucho más limpio y legible que usar `if-else` con números o cadenas.

```java
public class TestSwitchEnum {
    public static void main(String[] args) {
        Dia diaActual = Dia.VIERNES;

        switch (diaActual) {
            case LUNES:
                System.out.println("Es lunes, inicio de semana.");
                break;
            case VIERNES:
                System.out.println("Es viernes, ¡último día laboral!");
                break;
            case SABADO:
            case DOMINGO:
                System.out.println("Es fin de semana.");
                break;
            default:
                System.out.println("Es un día de semana normal.");
                break;
        }
    }
}
```
**Salida:**
```
Es viernes, ¡último día laboral!
```

---

## 5. Ventajas de Usar `enum`

*   **Legibilidad y Mantenibilidad:** El código es mucho más fácil de leer y entender, ya que se usan nombres significativos (`Dia.LUNES`) en lugar de "números mágicos" (`1`) o cadenas (`"lunes"`).
*   **Seguridad de Tipos:** El compilador garantiza que solo se puedan asignar valores definidos en el `enum`, eliminando una clase entera de errores en tiempo de ejecución.
*   **Integración:** Funcionan a la perfección con estructuras de control como `switch` y se pueden recorrer fácilmente.