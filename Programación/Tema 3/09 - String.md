# Guía Completa de la Clase String en Java

En Java, las cadenas de texto se gestionan a través de la clase `String`. Es un tipo de dato fundamental para manipular y gestionar cualquier tipo de texto en un programa.

---

## 1. Creación de Strings

Existen dos formas principales de crear un objeto `String`:

### 1.1. Usando Literales de Cadena (Recomendado)

Esta es la forma más común y eficiente.
```java
String saludo = "Hola, Mundo";
```
Cuando se crea un `String` de esta manera, Java lo almacena en un área de memoria especial llamada "String Pool". Si creas otra variable con el mismo literal, Java reutilizará la misma instancia para ahorrar memoria.

### 1.2. Usando el Constructor `new`

Esta forma crea explícitamente un nuevo objeto `String` en la memoria, incluso si ya existe una cadena idéntica en el pool.
```java
String saludo = new String("Hola, Mundo");
```
Generalmente, no es necesario usar el constructor, a menos que necesites garantizar que tienes una instancia completamente nueva y separada del pool.

---

## 2. Propiedades Fundamentales

### Inmutabilidad
Los objetos `String` en Java son **inmutables**, lo que significa que una vez creados, no pueden ser modificados. Cada vez que "modificas" un `String` (por ejemplo, al concatenarlo), en realidad estás creando un **nuevo objeto `String`** en la memoria.

```java
String saludo = "Hola";
// La siguiente línea no modifica "Hola", sino que crea un nuevo objeto "Hola Mundo"
// y hace que la variable 'saludo' apunte a este nuevo objeto.
saludo = saludo + " Mundo";
```
Esta propiedad garantiza que las cadenas sean seguras y predecibles, pero puede ser ineficiente si necesitas realizar muchas modificaciones. Para esos casos, se utiliza `StringBuilder`.

---

## 3. Métodos Más Comunes de la Clase `String`

### Concatenación
*   **Con el operador `+`:**
    ```java
    String nombre = "Juan";
    String apellido = "Pérez";
    String nombreCompleto = nombre + " " + apellido; // "Juan Pérez"
    ```
*   **Con el método `.concat()`:**
    ```java
    String s1 = "Hola";
    String s2 = s1.concat(" Mundo"); // "Hola Mundo"
    ```

### Obtener Longitud
*   **`.length()`**: Devuelve el número de caracteres en la cadena.
    ```java
    String saludo = "Hola, Mundo";
    int longitud = saludo.length(); // Resultado: 11
    ```

### Comparación
*   **`.equals()`**: Compara si dos cadenas son exactamente iguales (sensible a mayúsculas).
    ```java
    String a = "Hola";
    String b = "hola";
    boolean sonIguales = a.equals(b); // Resultado: false
    ```
*   **`.equalsIgnoreCase()`**: Compara dos cadenas ignorando las diferencias entre mayúsculas y minúsculas.
    ```java
    String a = "Hola";
    String b = "hola";
    boolean sonIguales = a.equalsIgnoreCase(b); // Resultado: true
    ```

### Extracción de Subcadenas
*   **`.substring(int beginIndex, int endIndex)`**: Extrae una parte de la cadena. El `endIndex` no se incluye.
    ```java
    String saludo = "Hola, Mundo";
    String subcadena = saludo.substring(0, 4); // Resultado: "Hola"
    ```

### Búsqueda
*   **`.indexOf(String str)`**: Devuelve la posición (índice) de la primera aparición de un carácter o subcadena. Devuelve `-1` si no se encuentra.
    ```java
    String saludo = "Hola, Mundo";
    int posicion = saludo.indexOf("Mundo"); // Resultado: 6
    ```*   **`.contains(String str)`**: Devuelve `true` si la cadena contiene la subcadena especificada.
    ```java
    String frase = "Java es muy popular";
    boolean contieneJava = frase.contains("Java"); // Resultado: true
    ```

### Conversión de Mayúsculas y Minúsculas
*   **`.toUpperCase()`**: Convierte toda la cadena a mayúsculas.
    ```java
    String saludo = "Hola, Mundo";
    String mayusculas = saludo.toUpperCase(); // Resultado: "HOLA, MUNDO"
    ```
*   **`.toLowerCase()`**: Convierte toda la cadena a minúsculas.
    ```java
    String saludo = "Hola, Mundo";
    String minusculas = saludo.toLowerCase(); // Resultado: "hola, mundo"
    ```

### Reemplazo
*   **`.replace(char oldChar, char newChar)`**: Reemplaza todas las apariciones de un carácter por otro.
    ```java
    String saludo = "Hola, Mundo";
    String reemplazo = saludo.replace('o', 'a'); // Resultado: "Hala, Munda"
    ```

### Eliminación de Espacios en Blanco
*   **`.trim()`**: Elimina los espacios en blanco al principio y al final de la cadena.
    ```java
    String saludo = "   Hola, Mundo   ";
    String limpio = saludo.trim(); // Resultado: "Hola, Mundo"
    ```

### División de Cadenas
*   **`.split(String regex)`**: Divide una cadena en un array de subcadenas basándose en un delimitador.
    ```java
    String datos = "uno,dos,tres";
    String[] partes = datos.split(",");
    // partes es "uno", partes es "dos", partes es "tres"
    ```

---

## 4. Convertir Otros Tipos a `String`

*   **`String.valueOf(tipoDato)`**: Método estático para convertir cualquier tipo de dato (primitivo u objeto) a su representación en `String`.
    ```java
    int numero = 123;
    String cadenaNumero = String.valueOf(numero); // Resultado: "123"

    double decimal = 45.67;
    String cadenaDecimal = String.valueOf(decimal); // Resultado: "45.67"
    ```

---

## 5. Ejemplos Prácticos de `String`

### Ejemplo 1: Concatenar y transformar
```java
public class EjemploString {
    public static void main(String[] args) {
        String nombre = "Juan";
        String apellido = "Pérez";

        // Concatenar
        String nombreCompleto = nombre + " " + apellido;
        System.out.println("Nombre completo: " + nombreCompleto);

        // Transformar a mayúsculas
        System.out.println("Nombre en mayúsculas: " + nombreCompleto.toUpperCase());
    }
}
```
**Salida:**
```
Nombre completo: Juan Pérez
Nombre en mayúsculas: JUAN PÉREZ
```

### Ejemplo 2: Verificar si una cadena contiene una subcadena
```java
public class ContieneSubcadena {
    public static void main(String[] args) {
        String frase = "El lenguaje de programación Java es muy popular";

        // Verificar si la frase contiene la palabra "Java"
        if (frase.contains("Java")) {
            System.out.println("La frase contiene la palabra 'Java'");
        } else {
            System.out.println("La frase no contiene la palabra 'Java'");
        }
    }
}
```
**Salida:**
```
La frase contiene la palabra 'Java'
```

---

## 6. StringBuilder: La Alternativa Mutable

Cuando necesitas realizar muchas modificaciones a una cadena (como construir una cadena dentro de un bucle), usar `String` es ineficiente porque crea un nuevo objeto en cada operación. Para estos casos, Java ofrece `StringBuilder`.

### 6.1. Propiedades de `StringBuilder`

*   **Mutabilidad**: A diferencia de `String`, los objetos `StringBuilder` **sí pueden ser modificados** después de su creación. Las operaciones como `append` o `insert` modifican el objeto existente en lugar de crear uno nuevo.
*   **Eficiencia**: Es mucho más rápido y consume menos memoria que la concatenación de `String` en bucles o en operaciones complejas.
*   **No es "thread-safe"**: `StringBuilder` no está sincronizado, por lo que es ideal para usarse en un único hilo. Si necesitas seguridad en entornos con múltiples hilos, existe una clase similar llamada `StringBuffer` que es más lenta pero segura.

### 6.2. Creación de `StringBuilder`

Se crea siempre usando el constructor.
```java
// Un StringBuilder vacío
StringBuilder sb1 = new StringBuilder();

// Un StringBuilder con un texto inicial
StringBuilder sb2 = new StringBuilder("Hola");
```

### 6.3. Métodos Más Comunes de `StringBuilder`

*   **`.append(valor)`**: Añade el `valor` (que puede ser un `String`, `int`, `char`, etc.) al final de la secuencia.
    ```java
    StringBuilder sb = new StringBuilder("Hola");
    sb.append(" Mundo"); // sb ahora contiene "Hola Mundo"
    sb.append(123); // sb ahora contiene "Hola Mundo123"
    ```
*   **`.insert(int offset, valor)`**: Inserta el `valor` en la posición indicada.
    ```java
    StringBuilder sb = new StringBuilder("Hola Mundo");
    sb.insert(5, "gran "); // sb ahora contiene "Hola gran Mundo"
    ```
*   **`.replace(int start, int end, String str)`**: Reemplaza los caracteres en un rango específico por otra cadena.
    ```java
    StringBuilder sb = new StringBuilder("Hola Mundo");
    sb.replace(0, 4, "Adiós"); // sb ahora contiene "Adiós Mundo"
    ```
*   **`.delete(int start, int end)`**: Elimina los caracteres en un rango específico.
    ```java
    StringBuilder sb = new StringBuilder("Hola Mundo");
    sb.delete(0, 5); // sb ahora contiene "Mundo"
    ```
*   **`.reverse()`**: Invierte el orden de los caracteres.
    ```java
    StringBuilder sb = new StringBuilder("Hola");
    sb.reverse(); // sb ahora contiene "aloH"
    ```
*   **`.toString()`**: Convierte el `StringBuilder` a un objeto `String` inmutable. Este es el paso final cuando ya has terminado de construir tu cadena.
    ```java
    StringBuilder sb = new StringBuilder("Texto final");
    String resultado = sb.toString();
    ```

### 6.4. Ejemplo Práctico de `StringBuilder`

Este ejemplo demuestra por qué `StringBuilder` es ideal para construir cadenas en un bucle.

```java
public class EjemploStringBuilder {
    public static void main(String[] args) {
        String[] palabras = {"Java", "es", "eficiente", "con", "StringBuilder"};
        StringBuilder frase = new StringBuilder();

        // Construir la frase de manera eficiente
        for (int i = 0; i < palabras.length; i++) {
            frase.append(palabras[i]);
            if (i < palabras.length - 1) {
                frase.append(" ");
            }
        }

        // Convertir a String para su uso final
        String resultado = frase.toString();
        System.out.println(resultado); // "Java es eficiente con StringBuilder"
    }
}
```
