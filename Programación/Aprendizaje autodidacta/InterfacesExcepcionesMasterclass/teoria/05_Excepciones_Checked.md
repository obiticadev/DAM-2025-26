# Nivel 5: Excepciones Checked

## Que es una excepcion?

Una excepcion es un **evento anormal** que ocurre durante la ejecucion de un programa. En lugar de que el programa explote sin control, Java te permite **capturar** el error y decidir que hacer.

## La jerarquia de excepciones

```
Throwable
├── Error                    (Errores graves del sistema, NO captures estos)
│   ├── OutOfMemoryError
│   └── StackOverflowError
│
└── Exception                (Errores recuperables)
    ├── IOException          (CHECKED)
    ├── SQLException         (CHECKED)
    ├── FileNotFoundException (CHECKED, hija de IOException)
    └── RuntimeException     (UNCHECKED, Nivel 6)
        ├── NullPointerException
        └── IllegalArgumentException
```

## Checked vs Unchecked

| Tipo      | Hereda de          | Compilador obliga a manejarla? | Ejemplo              |
|-----------|--------------------|---------------------------------|----------------------|
| Checked   | Exception          | SI                              | IOException          |
| Unchecked | RuntimeException   | NO                              | NullPointerException |

Las **checked** son errores que el compilador te OBLIGA a manejar porque son predecibles (un archivo podria no existir, una red podria fallar).

## try-catch basico

```java
try {
    // Codigo que puede fallar
    String contenido = leerArchivo("datos.txt");
} catch (IOException e) {
    // Que hacer si falla
    System.out.println("No se pudo leer: " + e.getMessage());
}
```

## Multiples catch

Puedes capturar diferentes tipos de excepcion:

```java
try {
    procesarArchivo("datos.csv");
} catch (FileNotFoundException e) {
    // Archivo no encontrado (mas especifico)
    System.out.println("Archivo no existe: " + e.getMessage());
} catch (IOException e) {
    // Otro error de I/O (mas general)
    System.out.println("Error de lectura: " + e.getMessage());
}
```

**IMPORTANTE**: Siempre pon las excepciones mas especificas ANTES que las generales.

## Multi-catch (Java 7+)

Si quieres el mismo manejo para varias excepciones:

```java
try {
    procesarDatos();
} catch (IOException | SQLException e) {
    // Mismo manejo para ambas
    System.out.println("Error de datos: " + e.getMessage());
}
```

## finally

El bloque `finally` se ejecuta SIEMPRE, pase lo que pase:

```java
FileReader reader = null;
try {
    reader = new FileReader("datos.txt");
    // usar reader...
} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    // SIEMPRE se ejecuta (haya error o no)
    if (reader != null) {
        try { reader.close(); } catch (IOException e) { /* ignorar */ }
    }
}
```

## Propagacion con throws

Si no quieres manejar la excepcion, puedes pasarla al llamador:

```java
// Este metodo DECLARA que puede lanzar IOException
public String leerArchivo(String ruta) throws IOException {
    // Si ocurre IOException, se propaga al llamador
    return new String(Files.readAllBytes(Path.of(ruta)));
}

// El llamador DEBE manejarla
public void procesar() {
    try {
        String datos = leerArchivo("config.txt");
    } catch (IOException e) {
        // Aqui la manejas
    }
}
```

## Crear excepcion checked custom

```java
public class DatosCorruptosException extends Exception {
    
    public DatosCorruptosException(String mensaje) {
        super(mensaje);
    }
    
    public DatosCorruptosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```

## Encadenamiento de excepciones

Cuando capturas una excepcion de bajo nivel y lanzas una de dominio:

```java
try {
    leerBaseDatos();
} catch (SQLException e) {
    // Envuelves la excepcion original como "causa"
    throw new DatosCorruptosException("Error leyendo usuarios", e);
}
```

Esto preserva el stack trace original, que es crucial para depurar.

## Metodos utiles de Exception

- `getMessage()` — Mensaje descriptivo
- `getCause()` — Excepcion que la causo (encadenamiento)
- `getStackTrace()` — Array de StackTraceElement
- `printStackTrace()` — Imprime el stack trace completo
- `initCause(Throwable)` — Establece la causa despues de construir
