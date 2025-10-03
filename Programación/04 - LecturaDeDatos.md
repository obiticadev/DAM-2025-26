# 04 - LecturaDeDatos

La clase `Scanner` en Java es una herramienta fundamental para leer datos de entrada desde diversas fuentes, como el teclado, archivos o cadenas de texto.

### 1. Importar la Clase `Scanner`

Para utilizar `Scanner`, primero debes importarla desde el paquete `java.util`.

```java
import java.util.Scanner;
```

### 2. Crear un Objeto `Scanner`

Para leer la entrada del teclado, crea una instancia de `Scanner` utilizando `System.in`.

```java
Scanner scanner = new Scanner(System.in);
```

### 3. Lectura de Diferentes Tipos de Datos

`Scanner` ofrece varios métodos para leer distintos tipos de datos.

#### 3.1. Leer un Número Entero (`int`)

Usa el método `nextInt()` para leer un valor entero.

```java
System.out.print("Ingresa un número entero: ");
int numero = scanner.nextInt();
System.out.println("El número ingresado es: " + numero);
```

#### 3.2. Leer un Número Decimal (`double` o `float`)

Utiliza `nextDouble()` o `nextFloat()` para leer números decimales.

```java
System.out.print("Ingresa un número decimal: ");
double decimal = scanner.nextDouble();
System.out.println("El número decimal ingresado es: " + decimal);
```

#### 3.3. Leer una Cadena de Texto Completa (`String`)

El método `nextLine()` lee una línea completa de texto, incluyendo espacios.

```java
System.out.print("Ingresa tu nombre completo: ");
String nombreCompleto = scanner.nextLine();
System.out.println("Tu nombre es: " + nombreCompleto);
```

#### 3.4. Leer una Sola Palabra (`String`)

Para leer una única palabra (hasta el primer espacio), utiliza el método `next()`.

```java
System.out.print("Ingresa una palabra: ");
String palabra = scanner.next();
System.out.println("La palabra ingresada es: " + palabra);
```

#### 3.5. Leer un Valor Booleano (`boolean`)

El método `nextBoolean()` se encarga de leer un valor `true` o `false`.

```java
System.out.print("¿Te gusta Java? (true/false): ");
boolean respuesta = scanner.nextBoolean();
System.out.println("Tu respuesta fue: " + respuesta);
```

#### 3.6. Leer un Carácter (`char`)

`Scanner` no tiene un método directo para leer un solo carácter. Sin embargo, puedes lograrlo combinando `next()` y `charAt(0)`.

```java
System.out.print("Ingresa un carácter: ");
char caracter = scanner.next().charAt(0);
System.out.println("El carácter ingresado es: " + caracter);
```

### 4. Manejo de Errores Comunes

Si un usuario introduce un tipo de dato incorrecto, se producirá una `InputMismatchException`. Para evitar que el programa se detenga, puedes usar un bloque `try-catch`.

```java
try {
    System.out.print("Ingresa un número entero: ");
    int numero = scanner.nextInt();
    System.out.println("El número ingresado es: " + numero);
} catch (java.util.InputMismatchException e) {
    System.out.println("Error: Debes ingresar un número entero.");
}
```

### 5. Solucionar el Problema de `nextLine()` Después de `nextInt()` o `nextDouble()`

Un problema común es que después de usar `nextInt()` o `nextDouble()`, una llamada posterior a `nextLine()` parece ser omitida. Esto sucede porque los métodos numéricos no consumen el carácter de nueva línea (`\n`). La solución es añadir una llamada extra a `nextLine()` para consumir ese salto de línea pendiente.

```java
System.out.print("Ingresa tu edad: ");
int edad = scanner.nextInt();

scanner.nextLine(); // Limpia el buffer de entrada

System.out.print("Ingresa tu nombre: ");
String nombre = scanner.nextLine();
System.out.println("Hola " + nombre + ", tienes " + edad + " años.");
```

### 6. Uso de Delimitadores Personalizados

Por defecto, `Scanner` utiliza los espacios en blanco como delimitadores. Puedes cambiar este comportamiento con el método `useDelimiter()`.

```java
String datos = "Juan,25,Mexico";
Scanner lector = new Scanner(datos);
lector.useDelimiter(",");

String nombre = lector.next();
int edad = lector.nextInt();
String pais = lector.next();

System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", País: " + pais);
lector.close();
```

### 7. Cerrar el Objeto `Scanner`

Es una buena práctica cerrar el objeto `Scanner` cuando ya no lo necesites para liberar los recursos del sistema, especialmente si estás leyendo desde un archivo.

```java
scanner.close();
```

### 8. Resumen de Métodos Comunes

| Método                | Descripción                                        |
| --------------------- | -------------------------------------------------- |
| `nextInt()`           | Lee un número entero (`int`).                      |
| `nextDouble()`        | Lee un número decimal de doble precisión (`double`). |
| `nextFloat()`         | Lee un número decimal de precisión simple (`float`). |
| `nextLine()`          | Lee una línea completa de texto.                   |
| `next()`              | Lee una palabra (hasta un espacio en blanco).      |
| `nextBoolean()`       | Lee un valor booleano (`true` o `false`).          |
| `nextLong()`          | Lee un número largo (`long`).                      |
| `nextByte()`          | Lee un byte (`byte`).                              |
| `nextShort()`         | Lee un número corto (`short`).                     |
| `hasNext()`           | Verifica si hay otro token de entrada disponible.  |
| `useDelimiter(String)`| Establece un delimitador personalizado para la entrada. |
| `close()`             | Cierra el objeto `Scanner` y libera los recursos.  |
