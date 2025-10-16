# Guía de la Estructura de Control switch en Java

El comando `switch` es una estructura de control que permite evaluar una variable y compararla con una lista de posibles valores (`case`). Es una alternativa más limpia y, a veces, más eficiente que una larga cadena de sentencias `if-else if-else` cuando todas las condiciones dependen del valor de una única variable.

---

## 1. Sintaxis Clásica de `switch`

Esta es la forma tradicional de la estructura `switch`, disponible en todas las versiones de Java.

### Estructura
```java
switch (variable) {
    case valor1:
        // Código a ejecutar si variable == valor1
        break;
    case valor2:
        // Código a ejecutar si variable == valor2
        break;
    // ... más casos
    default:
        // Código a ejecutar si no coincide con ningún caso anterior
        break;
}
```

### Componentes Clave
*   **`variable`**: La variable o expresión a evaluar. Puede ser de tipo `byte`, `short`, `char`, `int`, `String` (desde Java 7), o un `enum`.
*   **`case valorX`**: Una etiqueta que define un valor con el que se comparará la variable.
*   **`break`**: **Fundamental**. Termina la ejecución del `switch`. Si se omite, la ejecución continuará con el siguiente `case` (comportamiento conocido como *fall-through*).
*   **`default`**: Bloque opcional que se ejecuta si la variable no coincide con ninguno de los `case`. Es una buena práctica incluirlo siempre.

### Ejemplo Básico (con `int`)
```java
int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;
    case 2:
        System.out.println("Martes");
        break;
    case 3:
        System.out.println("Miércoles");
        break;
    default:
        System.out.println("Día inválido");
        break;
}
```
**Salida:**
```
Miércoles
```

### Ejemplo con `String` (a partir de Java 7)
```java
String fruta = "manzana";

switch (fruta) {
    case "manzana":
        System.out.println("Es una manzana.");
        break;
    case "banana":
        System.out.println("Es una banana.");
        break;
    default:
        System.out.println("Fruta desconocida.");
        break;
}
```
**Salida:**
```
Es una manzana.
```

---

## 2. El Comportamiento "Fall-Through"

Ocurre cuando se omite la sentencia `break` en un `case`. La ejecución "cae" y continúa con las instrucciones del siguiente `case` sin volver a evaluar la condición. **Generalmente, es un error**, pero puede usarse intencionadamente para agrupar casos.

### Ejemplo de Fall-Through
```java
int numero = 2;

switch (numero) {
    case 1:
        System.out.println("Uno");
    case 2:
        System.out.println("Dos"); // La ejecución comienza aquí
    case 3:
        System.out.println("Tres"); // Continúa aquí
    default:
        System.out.println("Número desconocido"); // Y termina aquí
}
```
**Salida:**
```
Dos
Tres
Número desconocido
```

### Uso Intencional para Agrupar Casos
```java
int mes = 4;

switch (mes) {
    case 12:
    case 1:
    case 2:
        System.out.println("Invierno.");
        break;
    case 3:
    case 4:
    case 5:
        System.out.println("Primavera.");
        break;
    // ... otros casos
    default:
        System.out.println("Mes inválido.");
        break;
}
```
**Salida:**
```
Primavera.
```
Aquí, los casos 3, 4 y 5 ejecutan el mismo bloque de código.

---

## 3. `switch` Moderno (Enhanced Switch - Desde Java 14)

Las versiones modernas de Java introdujeron una sintaxis mejorada que es más segura y concisa.

### Características
*   Usa una flecha (`->`) en lugar de dos puntos (`:`).
*   **No necesita `break`**. El código de cada `case` está aislado, eliminando el riesgo de *fall-through* accidental.
*   Permite agrupar casos en una misma línea, separados por comas.
*   Puede ser usado como una **expresión** que devuelve un valor.

### Sintaxis como Sentencia
```java
switch (variable) {
    case valor1, valor2 -> {
        // Código para valor1 y valor2
    }
    case valor3 -> System.out.println("Línea única"); // No necesita llaves
    default -> {
        // Código por defecto
    }
}
```

### Sintaxis como Expresión (Devuelve un Valor)
```java
String resultado = switch (variable) {
    case 1 -> "Uno";
    case 2 -> "Dos";
    default -> "Otro número";
}; // ¡No olvides el punto y coma al final!
```

### Ejemplo de `switch` como Expresión
```java
int dia = 7;

String diaSemana = switch (dia) {
    case 1 -> "Lunes";
    case 2 -> "Martes";
    case 3 -> "Miércoles";
    case 4 -> "Jueves";
    case 5 -> "Viernes";
    case 6 -> "Sábado";
    case 7 -> "Domingo";
    default -> "Número inválido";
};

System.out.println("El día de la semana es: " + diaSemana);
```
**Salida:**
```
El día de la semana es: Domingo
```

---

## 4. `switch` vs. `if-else`

| Característica | `switch` | `if-else if` |
| :--- | :--- | :--- |
| **Legibilidad** | Muy claro cuando se compara una sola variable con múltiples valores constantes. | Puede volverse complejo y difícil de leer con muchas condiciones. |
| **Rendimiento** | Potencialmente más rápido, ya que puede usar una "tabla de saltos" para ir directamente al `case` correcto. | Evalúa las condiciones secuencialmente una por una. |
| **Flexibilidad** | Limitado a comparar una variable contra valores constantes de tipos específicos. | Puede evaluar cualquier tipo de expresión booleana (rangos, múltiples variables, etc.). |

**Conclusión:** Usa `switch` cuando tengas una única variable para comparar con un conjunto de valores discretos. Usa `if-else` para condiciones más complejas, como rangos de valores (`nota > 90`).

---

## 5. Buenas Prácticas

1.  **Usa `break` (en `switch` clásico):** Si usas la sintaxis con `:`, no olvides el `break` a menos que el *fall-through* sea intencional.
2.  **Incluye `default`:** Siempre añade un caso `default` para manejar valores inesperados y evitar un comportamiento indefinido.
3.  **Prefiere el `switch` Moderno:** Si tu versión de Java lo permite (14+), usa la sintaxis con `->`. Es más segura, legible y potente.
4.  **Agrupa Casos:** Para evitar código duplicado, agrupa los `case` que deben ejecutar la misma lógica.