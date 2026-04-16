# Nivel 8: Try-with-Resources y AutoCloseable

## El problema del cierre manual

Antes de Java 7, cerrar recursos era tedioso y propenso a errores:

```java
FileReader reader = null;
try {
    reader = new FileReader("datos.txt");
    // usar reader...
} catch (IOException e) {
    // manejar error
} finally {
    if (reader != null) {
        try {
            reader.close();  // Y si close() tambien falla?
        } catch (IOException e) {
            // Que hacemos aqui? Se complica...
        }
    }
}
```

## Try-with-resources (Java 7+)

Java cierra automaticamente cualquier recurso que implemente `AutoCloseable`:

```java
try (FileReader reader = new FileReader("datos.txt")) {
    // usar reader...
} catch (IOException e) {
    // manejar error
}
// reader.close() se llama AUTOMATICAMENTE al salir del try
```

## AutoCloseable vs Closeable

```java
// AutoCloseable: interfaz base (Java 7)
public interface AutoCloseable {
    void close() throws Exception;
}

// Closeable: extiende AutoCloseable, pero close() lanza IOException
public interface Closeable extends AutoCloseable {
    void close() throws IOException;
}
```

| Aspecto              | AutoCloseable          | Closeable              |
|---------------------|------------------------|------------------------|
| Paquete             | java.lang              | java.io                |
| close() lanza       | Exception              | IOException            |
| Uso tipico          | Cualquier recurso      | Recursos de I/O        |
| Idempotente?        | No necesariamente      | Si (se puede llamar varias veces) |

## Implementar AutoCloseable

```java
public class ConexionSimulada implements AutoCloseable {
    private boolean abierta = false;
    
    public void abrir() {
        this.abierta = true;
        System.out.println("Conexion abierta");
    }
    
    public String consultar(String query) {
        if (!abierta) throw new IllegalStateException("Conexion cerrada");
        return "Resultado de: " + query;
    }
    
    @Override
    public void close() {
        this.abierta = false;
        System.out.println("Conexion cerrada automaticamente");
    }
}

// Uso:
try (ConexionSimulada conn = new ConexionSimulada()) {
    conn.abrir();
    String resultado = conn.consultar("SELECT * FROM users");
}
// close() se llama automaticamente aqui
```

## Multiples recursos

Puedes declarar varios recursos separados por punto y coma:

```java
try (FileReader fr = new FileReader("entrada.txt");
     BufferedReader br = new BufferedReader(fr);
     FileWriter fw = new FileWriter("salida.txt")) {
    
    String linea;
    while ((linea = br.readLine()) != null) {
        fw.write(linea.toUpperCase() + "\n");
    }
}
// Se cierran en orden INVERSO: fw, br, fr (LIFO)
```

## Orden de cierre: LIFO

Los recursos se cierran en orden **inverso** al que se declararon (Last In, First Out):

```
Apertura: Recurso1 -> Recurso2 -> Recurso3
Cierre:   Recurso3 -> Recurso2 -> Recurso1
```

Esto tiene sentido porque los recursos posteriores suelen depender de los anteriores (ej: `BufferedReader` depende de `FileReader`).

## Suppressed exceptions en detalle

Si el bloque `try` lanza una excepcion Y `close()` tambien lanza otra:

```java
public class RecursoProblematico implements AutoCloseable {
    public void usar() {
        throw new RuntimeException("Error en uso");
    }
    
    @Override
    public void close() {
        throw new RuntimeException("Error al cerrar");
    }
}

try (RecursoProblematico r = new RecursoProblematico()) {
    r.usar();  // Lanza "Error en uso"
}              // close() lanza "Error al cerrar"
catch (RuntimeException e) {
    // e.getMessage() = "Error en uso" (la principal)
    // e.getSuppressed()[0].getMessage() = "Error al cerrar" (la suprimida)
}
```

La excepcion del bloque try es la **principal**. La de close() se anade como **suppressed**.

## Patron Decorador con recursos

Es comun envolver recursos:

```java
try (InputStream is = new FileInputStream("datos.gz");
     GZIPInputStream gzis = new GZIPInputStream(is);
     InputStreamReader isr = new InputStreamReader(gzis, "UTF-8");
     BufferedReader br = new BufferedReader(isr)) {
    
    // Leer archivo comprimido linea a linea
    br.lines().forEach(System.out::println);
}
```

## Combinar interfaces con AutoCloseable

```java
public interface RecursoGestionado extends AutoCloseable {
    void inicializar();
    boolean estaActivo();
    
    default void ejecutarConSeguridad(Runnable tarea) {
        if (!estaActivo()) {
            throw new IllegalStateException("Recurso no activo");
        }
        tarea.run();
    }
}
```

Asi puedes tener clases que son a la vez funcionales (via interfaz) y gestionables (via AutoCloseable).
