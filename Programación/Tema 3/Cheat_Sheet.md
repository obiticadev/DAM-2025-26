# Cheat Sheet de Clases Utilitarias en Java

## String

La clase `String` gestiona cadenas de texto. Es **inmutable**, lo que significa que cada modificación crea un nuevo objeto.

### Creación

```java
// Recomendado (usa el "String Pool")
String s1 = "Hola";

// Crea un nuevo objeto siempre
String s2 = new String("Hola");
```

### Métodos Comunes

| Método | Descripción | Sintaxis de Ejemplo |
| :--- | :--- | :--- |
| **`length()`** | Devuelve el número de caracteres. | `int len = "Hola".length(); // 4` |
| **`equals(obj)`** | Compara si el contenido es idéntico (sensible a mayúsculas). | `"a".equals("A"); // false` |
| **`equalsIgnoreCase(str)`**| Compara el contenido ignorando mayúsculas/minúsculas. | `"a".equalsIgnoreCase("A"); // true` |
| **`concat(str)`** | Concatena una cadena al final. | `"Hola".concat(" Mundo"); // "Hola Mundo"` |
| **`toUpperCase()`** | Convierte toda la cadena a mayúsculas. | `"texto".toUpperCase(); // "TEXTO"` |
| **`toLowerCase()`** | Convierte toda la cadena a minúsculas. | `"TEXTO".toLowerCase(); // "texto"` |
| **`substring(begin, end)`**| Extrae una subcadena. El índice `end` no se incluye. | `"Hola Mundo".substring(0, 4); // "Hola"` |
| **`indexOf(str)`** | Devuelve el índice de la primera aparición (-1 si no lo encuentra). | `"Hola".indexOf("la"); // 2` |
| **`contains(str)`** | Devuelve `true` si la cadena contiene la subcadena. | `"Hola".contains("ol"); // true` |
| **`replace(old, new)`** | Reemplaza todas las apariciones de un carácter o cadena. | `"banana".replace('a', 'o'); // "bonono"` |
| **`trim()`** | Elimina espacios en blanco al principio y al final. | `"  hola  ".trim(); // "hola"` |
| **`split(regex)`** | Divide la cadena en un array usando un delimitador. | `"a,b,c".split(","); // ["a", "b", "c"]` |
| **`String.valueOf(dato)`**| Convierte cualquier tipo de dato a `String`. | `String.valueOf(123); // "123"` |

---

## StringBuilder

Alternativa **mutable** y eficiente a `String` para cuando se necesitan realizar muchas modificaciones.

### Creación

```java
StringBuilder sb = new StringBuilder("Texto inicial");
```

### Métodos Comunes

| Método | Descripción | Sintaxis de Ejemplo |
| :--- | :--- | :--- |
| **`append(valor)`** | Añade un valor (texto, número, etc.) al final. | `sb.append("!");` |
| **`insert(offset, valor)`**| Inserta un valor en una posición específica. | `sb.insert(0, "Inicio: ");` |
| **`replace(start, end, str)`**| Reemplaza los caracteres en un rango por otra cadena. | `sb.replace(0, 5, "Adiós");` |
| **`delete(start, end)`** | Elimina los caracteres en un rango específico. | `sb.delete(0, 4);` |
| **`reverse()`** | Invierte el orden de los caracteres. | `sb.reverse();` |
| **`toString()`** | Convierte el `StringBuilder` a un `String` final. | `String resultado = sb.toString();` |

---

## Math

Proporciona métodos estáticos para operaciones matemáticas comunes. No necesita ser instanciada.

### Funciones Principales

| Método | Descripción | Sintaxis de Ejemplo |
| :--- | :--- | :--- |
| **`Math.abs(x)`** | Devuelve el valor absoluto. | `Math.abs(-10); // 10` |
| **`Math.ceil(x)`** | Redondea hacia arriba al entero más cercano. | `Math.ceil(5.1); // 6.0` |
| **`Math.floor(x)`** | Redondea hacia abajo al entero más cercano. | `Math.floor(5.9); // 5.0` |
| **`Math.round(x)`** | Redondea al entero más cercano (redondeo estándar). | `Math.round(5.5); // 6` |
| **`Math.max(a, b)`** | Devuelve el mayor de dos números. | `Math.max(10, 20); // 20` |
| **`Math.min(a, b)`** | Devuelve el menor de dos números. | `Math.min(10, 20); // 10` |
| **`Math.pow(base, exp)`**| Calcula la potencia de un número. | `Math.pow(2, 3); // 8.0` |
| **`Math.sqrt(x)`** | Calcula la raíz cuadrada. | `Math.sqrt(16); // 4.0` |
| **`Math.random()`** | Devuelve un `double` aleatorio entre `0.0` (incluido) y `1.0` (excluido). | `Math.random(); // 0.123...` |
| **`Math.sin(rad)`** | Seno de un ángulo en radianes. | `Math.sin(Math.PI / 2); // 1.0` |
| **`Math.cos(rad)`** | Coseno de un ángulo en radianes. | `Math.cos(Math.PI); // -1.0` |
| **`Math.log(x)`** | Logaritmo natural (base `e`). | `Math.log(Math.E); // 1.0` |

### Constantes

*   **`Math.PI`**: El valor de Pi (3.14159...).
*   **`Math.E`**: La base de los logaritmos naturales (2.71828...).

---

## Character

Clase envoltorio (`wrapper`) que proporciona métodos estáticos para manipular el tipo primitivo `char`.

### Métodos de Verificación

| Método | Descripción | Sintaxis de Ejemplo |
| :--- | :--- | :--- |
| **`isLetter(ch)`** | `true` si el carácter es una letra. | `Character.isLetter('a'); // true` |
| **`isDigit(ch)`** | `true` si el carácter es un dígito. | `Character.isDigit('5'); // true` |
| **`isWhitespace(ch)`** | `true` si es un espacio en blanco (`' '`, `'\t'`, `'\n'`). | `Character.isWhitespace(' '); // true` |
| **`isLetterOrDigit(ch)`**| `true` si es una letra o un dígito. | `Character.isLetterOrDigit('$'); // false`|

### Métodos de Conversión

| Método | Descripción | Sintaxis de Ejemplo |
| :--- | :--- | :--- |
| **`toUpperCase(ch)`** | Convierte el carácter a mayúscula. | `Character.toUpperCase('c'); // 'C'` |
| **`toLowerCase(ch)`** | Convierte el carácter a minúscula. | `Character.toLowerCase('D'); // 'd'` |
| **`getNumericValue(ch)`**| Devuelve el valor `int` de un carácter numérico. | `Character.getNumericValue('7'); // 7` |