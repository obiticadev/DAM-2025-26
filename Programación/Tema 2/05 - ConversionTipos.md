# Conversión de Tipos (Casting) en Java

La conversión de tipos es el proceso de transformar un valor de un tipo de dato a otro. En Java, existen dos categorías principales:

1.  **Conversión Implícita (Widening):** Se realiza automáticamente cuando se convierte un tipo de dato de menor tamaño a uno de mayor tamaño. Es una conversión segura y sin pérdida de datos.
2.  **Conversión Explícita (Narrowing):** Requiere la intervención del programador mediante un "casting". Se usa para convertir un tipo de mayor tamaño a uno de menor tamaño y puede ocasionar pérdida de datos.

---

## 1. Conversión Implícita (Widening Casting)

Ocurre automáticamente cuando se asigna un valor de un tipo pequeño a una variable de un tipo más grande. Java maneja esto por sí solo porque no hay riesgo de perder información.

**Jerarquía de Conversión Implícita:**
```
byte -> short -> char -> int -> long -> float -> double
```

**Ejemplo 1: De `int` a `double`**
```java
int numeroEntero = 100;
// Se convierte 'numeroEntero' a double automáticamente antes de la asignación
double numeroDecimal = numeroEntero; 

System.out.println(numeroDecimal); // Salida: 100.0
```
Como `double` tiene más capacidad que `int`, la conversión es segura.

**Ejemplo 2: De `char` a `int`**
```java
char letra = 'A'; // El valor Unicode de 'A' es 65
// Se convierte el valor numérico del char a int
int codigoAscii = letra;

System.out.println(codigoAscii); // Salida: 65
```
Esto es posible porque `char` se almacena internamente como un número entero sin signo.

---

## 2. Conversión Explícita (Narrowing Casting)

Debe ser realizada manualmente por el programador. Se utiliza cuando se intenta asignar un valor de un tipo más grande a una variable de un tipo más pequeño. Esta operación puede causar pérdida de información (por ejemplo, truncamiento de decimales o desbordamiento de valores).

**Sintaxis:** `tipo_mas_pequeno variable = (tipo_mas_pequeno) variable_mas_grande;`

**Jerarquía de Conversión Explícita:**
```
double -> float -> long -> int -> char -> short -> byte
```

**Ejemplo 1: De `double` a `int` (Pérdida de precisión)**
```java
double numeroDecimal = 9.78;
// Se debe indicar explícitamente que queremos convertir a (int)
int numeroEntero = (int) numeroDecimal;

System.out.println(numeroEntero); // Salida: 9 (se pierde la parte decimal)
```

**Ejemplo 2: De `int` a `char`**
```java
int numero = 65;
// Se convierte el número 65 al carácter correspondiente en la tabla Unicode/ASCII
char letra = (char) numero;

System.out.println(letra); // Salida: A
```

---

## 3. Promoción de Tipos en Expresiones

Cuando se utilizan diferentes tipos de datos en una operación aritmética, Java los "promociona" automáticamente a un tipo más grande para evitar la pérdida de precisión durante el cálculo.

**Reglas principales:**
*   Si un operando es `double`, el otro se convierte a `double`.
*   Si no, si un operando es `float`, el otro se convierte a `float`.
*   Si no, si un operando es `long`, el otro se convierte a `long`.
*   En cualquier otro caso, ambos operandos se convierten a `int`.

**Ejemplo:**
```java
int a = 5;
double b = 4.5;
// La variable 'a' es promovida a double (5.0) para poder sumarse con 'b'
double resultado = a + b;

System.out.println(resultado); // Salida: 9.5
```

---

## 4. Conversiones con el Tipo `String`

Aunque `String` no es un tipo primitivo, es muy común necesitar convertir valores numéricos a `String` y viceversa.

### De Tipo Numérico a `String`

**Método 1: Concatenación (Conversión Automática)**
La forma más sencilla. Si unes un número con una cadena usando el operador `+`, el número se convierte automáticamente a `String`.

```java
int numero = 100;
String texto = "El número es: " + numero;
System.out.println(texto); // Salida: El número es: 100
```

**Método 2: `String.valueOf()`**
Este método convierte explícitamente cualquier tipo primitivo a su representación en `String`.

```java
double numero = 42.5;
String texto = String.valueOf(numero);
System.out.println(texto); // Salida: "42.5"
```

### De `String` a Tipo Numérico

Para esto, se utilizan métodos de las clases envolventes (`Integer`, `Double`, etc.).

**¡Importante!** Si la cadena no contiene un número válido, estos métodos lanzarán una excepción (`NumberFormatException`).

**Ejemplo 1: De `String` a `int`**
```java
String texto = "123";
int numero = Integer.parseInt(texto);
System.out.println(numero); // Salida: 123
```

**Ejemplo 2: De `String` a `double`**
```java
String texto = "45.67";
double numero = Double.parseDouble(texto);
System.out.println(numero); // Salida: 45.67
```
> **Nota:** No se puede hacer un "casting" directo entre `String` y tipos primitivos. `(int) "123"` o `(String) 123` son incorrectos y producirán un error de compilación. Siempre se deben usar los métodos mencionados.

---

### Ejemplo Práctico Completo

```java
public class EjemploConversionTipos {
    public static void main(String[] args) {
        // --- De numérico a String ---
        int numeroEntero = 50;
        String textoEntero = String.valueOf(numeroEntero); // Explícita
        System.out.println("int a String: " + textoEntero);

        double numeroDecimal = 25.75;
        String textoDecimal = "Valor: " + numeroDecimal; // Implícita por concatenación
        System.out.println("double a String: " + textoDecimal);

        // --- De String a numérico ---
        String textoNumerico = "300";
        int numeroConvertido = Integer.parseInt(textoNumerico);
        System.out.println("String a int: " + (numeroConvertido + 1)); // Se puede operar con él

        // --- De String a char ---
        String textoLetra = "H";
        char letra = textoLetra.charAt(0); // Se extrae el primer carácter
        System.out.println("String a char: " + letra);

        // --- De char a int (valor numérico) ---
        int valorAscii = (int) letra; // Casting explícito
        System.out.println("Valor ASCII de '" + letra + "': " + valorAscii);
    }
}
```

# Simplificado

## 1. Conversiones Numéricas

### De `int` a `double` (Automática / Widening)
Se convierte automáticamente un tipo pequeño a uno más grande sin riesgo de perder datos.

```java
// int numeroEntero = 100;
double numeroDecimal = numeroEntero; // Se añade .0 automáticamente
```

### De `double` a `int` (Manual / Narrowing)
Se debe forzar la conversión de un tipo grande a uno más pequeño. **Se pierde la parte decimal.**

```java
// double numeroDecimal = 9.78;
int enteroRecortado = (int) numeroDecimal; // Resultado: 9
```

### De `long` a `int` (Manual / Narrowing)
Requiere casting explícito. **Riesgo de perder valor** si el `long` es más grande de lo que un `int` puede almacenar.

```java
// long longLargo = 12345678901L;
int intCorto = (int) longLargo;
```

### De `int` a `byte` (Manual / Narrowing)
Requiere casting explícito. **Riesgo de desbordamiento** si el `int` está fuera del rango de `byte` (-128 a 127).

```java
// int numeroEntero = 120;
byte bytePequeño = (byte) numeroEntero;
```

---

## 2. Conversiones entre `char` y `int`

### De `char` (letra) a `int` (su código ASCII/Unicode)
Se obtiene el valor numérico del carácter de forma automática.

```java
// char unaLetra = 'A';
int codigoNumerico = unaLetra; // Resultado: 65
```

### De `int` (código) a `char` (letra)
Se convierte un valor numérico a su carácter correspondiente usando casting.

```java
// int unCodigo = 65;
char letraResultante = (char) unCodigo; // Resultado: 'A'
```

---

## 3. Conversiones de `String` a Tipos Numéricos (¡Usando Métodos!)

**Importante:** Nunca se usa casting directo como `(int) "123"`. Se utilizan los métodos `parse` de las clases envolventes (`Integer`, `Double`, etc.).

### De `String` a `int`
```java
// String textoNumero = "123";
int numeroParseado = Integer.parseInt(textoNumero);
```

### De `String` a `double`
```java
// String textoDecimal = "99.5";
double decimalParseado = Double.parseDouble(textoDecimal);
```

### De `String` a `float`
```java
// String textoFlotante = "12.34f";
float flotanteParseado = Float.parseFloat(textoFlotante);
```

### De `String` a `long`
```java
// String textoLargo = "1234567890";
long largoParseado = Long.parseLong(textoLargo);
```

---

## 4. Conversiones de Tipos Numéricos a `String`

### Método 1: Formal (`String.valueOf()`)
La forma explícita y recomendada.

```java
// int numeroEntero = 500;
String textoDesdeInt = String.valueOf(numeroEntero); // Resultado: "500"
```

### Método 2: Fácil (Concatenación)
El truco más común y rápido para convertir cualquier tipo a `String`.

```java
// double numeroDecimal = 49.99;
String textoDesdeDouble = "" + numeroDecimal; // Resultado: "49.99"
```

---

## 5. Extracción de `String` a `char`

No es una conversión de tipo directa, sino la **extracción** de un carácter de la cadena en una posición específica.

### De `String` a `char` (Obtener un carácter)
Se usa el método `.charAt(índice)`. El índice `0` es el primer carácter.

```java
// String miPalabra = "Hola";
char primerCaracter = miPalabra.charAt(0); // Resultado: 'H'
```