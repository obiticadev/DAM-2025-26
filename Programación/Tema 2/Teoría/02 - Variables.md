# Guía de Variables y Tipos de Datos en Java

Las variables son contenedores en la memoria que almacenan datos. En Java, cada variable se define por:

*   **Un nombre (identificador):** Permite acceder al valor que contiene.
*   **Un tipo de dato:** Especifica qué clase de información puede guardar.
*   **Un rango de valores:** El conjunto de valores que puede admitir.

### 1. Identificadores y Convenciones de Nomenclatura

Un identificador es el nombre que le damos a una variable, método, clase, etc.

**Reglas de Estilo (Convenciones):**

*   **Distinción de mayúsculas:** Java es *case-sensitive*, por lo que `nombre` y `Nombre` son identificadores diferentes.
*   **Nombres descriptivos:** Los identificadores deben ser lo más claros y descriptivos posible.
*   **Caracteres no recomendados:** Por convención, no se suelen usar identificadores que comiencen con `$` o `_`.
*   **Palabras prohibidas:** No se pueden usar palabras reservadas del lenguaje (como `int`, `class`, `public`) ni los valores `true`, `false` y `null`.

**Tabla de Convenciones de Nomenclatura:**

| Identificador | Convención | Ejemplo |
| :--- | :--- | :--- |
| **Variable** | Comienza con minúscula. Si tiene varias palabras, las siguientes empiezan con mayúscula (*camelCase*). | `numeroAlumnos`, `sumaTotal` |
| **Constante** | En mayúsculas, separando las palabras con guion bajo (*UPPER_SNAKE_CASE*). | `TAM_MAX`, `PI` |
| **Clase** | Comienza con mayúscula (*PascalCase* o *UpperCamelCase*). | `String`, `MiClase`, `EjemploVariables`|
| **Método/Función**| Sigue la misma regla que las variables (*camelCase*). | `modificarValor()`, `obtenerNombre()`|

### 2. Palabras Reservadas

Son identificadores que tienen un significado especial para el compilador de Java y no se pueden usar para nombrar variables.

| | | | | |
| :--- | :--- | :--- | :--- | :--- |
| `abstract` | `default` | `if` | `protected` | `throw` |
| `assert` | `do` | `implements` | `public` | `throws` |
| `boolean` | `double` | `import` | `return` | `transient` |
| `break` | `else` | `instanceof` | `short` | `try` |
| `byte` | `enum` | `int` | `static` | `void` |
| `case` | `extends` | `interface` | `strictfp` | `volatile` |
| `catch` | `final` | `long` | `super` | `while` |
| `char` | `finally` | `native` | `switch` | `yield` |
| `class` | `float` | `new` | `synchronized`|
| `const` | `for` | `package` | `this` |
| `continue`| `goto` | `private` | |

### 3. Clasificación de Variables

Las variables se pueden clasificar según diferentes criterios:

#### Por su Ámbito (Scope)
Define en qué parte del programa la variable es accesible.

*   **Variables de Instancia (o Miembro):** Se declaran dentro de una clase, pero fuera de cualquier método. Pertenecen a un objeto de la clase.
*   **Variables Locales:** Se declaran dentro de un método o un bloque de código. Solo existen mientras se ejecuta ese método o bloque.

```java
public class EjemploVariables {
    final double PI = 3.14159; // Es una constante de instancia
    int x; // Es una variable de instancia

    // 'param' es un parámetro, que actúa como una variable local al método
    int obtenerX(int param) { 
        int valorAntiguo = this.x; // 'valorAntiguo' es una variable local
        this.x = param;
        return valorAntiguo;
    }
}
```

#### Por su Mutabilidad
*   **Variables:** Su valor puede cambiar durante la ejecución del programa.
*   **Constantes:** Su valor no puede cambiar una vez que ha sido asignado. Se declaran con la palabra clave `final`.

### 4. Tipos de Datos

#### Tipos de Datos Primitivos
Son los tipos más básicos, predefinidos en Java. Almacenan valores simples.

**Numéricos Enteros:**
*   `byte`: 1 byte. Rango: -128 a 127.
*   `short`: 2 bytes. Rango: -32,768 a 32,767.
*   `int`: 4 bytes. Es el más común para números enteros.
*   `long`: 8 bytes. Para números enteros muy grandes (termina con `L`).

**Numéricos de Punto Flotante (Decimales):**
*   `float`: 4 bytes. Precisión simple, para ahorrar memoria (termina con `f`).
*   `double`: 8 bytes. Doble precisión, es el tipo por defecto para decimales.

**Otros:**
*   `boolean`: Almacena solo dos valores: `true` o `false`.
*   `char`: 2 bytes. Almacena un único carácter Unicode (entre comillas simples `''`).

#### Tipos de Datos de Referencia (u Objetos)
No almacenan el dato directamente, sino una "referencia" (la dirección en memoria) a un objeto.

*   **String:** Secuencia de caracteres. Aunque parece un tipo primitivo por su uso, es una clase.
    ```java
    String saludo = "Hola, mundo!";
    ```
*   **Arreglos (Arrays):** Colección de valores del mismo tipo. Su tamaño es fijo.
    ```java
    int[] numeros = {1, 2, 3, 4, 5};
    ```
*   **Clases:** Plantillas para crear objetos. Cualquier clase que crees o uses (como `Scanner`) es un tipo de dato de referencia.
    ```java
    MiClase objeto = new MiClase();
    ```

**Tabla Resumen de Tipos Primitivos:**

| Tipo | Tamaño | Rango / Valor |
| :--- | :--- | :--- |
| `byte` | 1 byte | -128 a 127 |
| `short`| 2 bytes | -32,768 a 32,767 |
| `int` | 4 bytes | -2³¹ a 2³¹-1 |
| `long` | 8 bytes | -2⁶³ a 2⁶³-1 |
| `float` | 4 bytes | Precisión de 6-7 dígitos decimales |
| `double`| 8 bytes | Precisión de 15 dígitos decimales |
| `boolean`| 1 bit | `true` o `false` |
| `char` | 2 bytes | Caracteres Unicode (0 a 65,535) |


### 5. Declaración e Inicialización

*   **Declaración:** Especificar el tipo y el nombre de la variable. Java reserva un espacio en memoria.
    ```java
    int edad;
    String nombre;
    ```
*   **Inicialización:** Asignar un valor inicial a la variable.
    ```java
    edad = 25;
    nombre = "Juan";
    ```
*   **Declaración e Inicialización Combinada:** Es la práctica más común.
    ```java
    int edad = 25;
    double salario = 3500.75;
    char letra = 'A';
    ```
*   **Múltiples Variables:** Se pueden declarar varias variables del mismo tipo en una sola línea.
    ```java
    int a, b, c; // Declara tres enteros
    int x = 1, y = 2, z = 3; // Declara e inicializa tres enteros
    ```

### 6. Ejemplo Práctico Completo

```java
public class TiposDeDatosPrimitivos {
    public static void main(String[] args) {
        // --- Tipos numéricos enteros ---
        byte edad = 25;
        System.out.println("Valor byte (edad): " + edad);

        short anio = 2024;
        System.out.println("Valor short (año): " + anio);

        int poblacion = 1000000;
        System.out.println("Valor int (población): " + poblacion);

        long distanciaGalaxia = 9460730472580800L; // 'L' es necesaria
        System.out.println("Valor long (distancia en km): " + distanciaGalaxia);

        // --- Tipos numéricos con punto flotante (decimales) ---
        float temperatura = 36.6f; // 'f' es necesaria
        System.out.println("Valor float (temperatura corporal): " + temperatura);

        double pi = 3.141592653589793;
        System.out.println("Valor double (pi): " + pi);

        // --- Tipos booleanos y de caracteres ---
        boolean esProgramador = true;
        System.out.println("Valor boolean (es programador): " + esProgramador);

        char inicial = 'A';
        System.out.println("Valor char (inicial): " + inicial);
    }
}
```
