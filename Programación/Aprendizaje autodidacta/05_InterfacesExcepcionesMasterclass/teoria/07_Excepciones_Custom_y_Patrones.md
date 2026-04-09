# Nivel 7: Excepciones Personalizadas y Patrones

## Excepcion con campos extra

Las excepciones custom pueden llevar informacion adicional para quien las capture:

```java
public class ErrorDeValidacion extends RuntimeException {
    
    private final String campo;
    private final String codigoError;
    private final Severidad severidad;
    
    public enum Severidad { BAJA, MEDIA, ALTA, CRITICA }
    
    public ErrorDeValidacion(String campo, String mensaje, String codigoError, Severidad severidad) {
        super(mensaje);
        this.campo = campo;
        this.codigoError = codigoError;
        this.severidad = severidad;
    }
    
    // Getters...
}
```

## Jerarquia de excepciones custom

Crea una excepcion base para tu dominio y excepciones especificas que hereden:

```java
// Excepcion base del dominio
public class AppException extends RuntimeException {
    private final String codigo;
    
    public AppException(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }
    // ...
}

// Excepciones especificas
public class UsuarioNoEncontradoException extends AppException {
    public UsuarioNoEncontradoException(String id) {
        super("USR-404", "Usuario no encontrado: " + id);
    }
}

public class AccesoDenegadoException extends AppException {
    public AccesoDenegadoException(String recurso) {
        super("SEC-403", "Acceso denegado al recurso: " + recurso);
    }
}
```

## Patron: Traduccion de excepciones

Captura excepciones de bajo nivel y lanza excepciones de dominio:

```java
public class RepositorioUsuarios {
    public Usuario buscar(String id) {
        try {
            return baseDatos.query("SELECT * FROM usuarios WHERE id = ?", id);
        } catch (SQLException e) {
            // Traducimos: la capa de servicio no deberia saber de SQL
            throw new UsuarioNoEncontradoException(id);
        }
    }
}
```

## Excepcion con enum de codigos

```java
public enum CodigoError {
    CAMPO_REQUERIDO("VAL-001", "Campo obligatorio no proporcionado"),
    FORMATO_INVALIDO("VAL-002", "Formato del campo no valido"),
    RANGO_EXCEDIDO("VAL-003", "Valor fuera del rango permitido"),
    DUPLICADO("VAL-004", "El registro ya existe");
    
    private final String codigo;
    private final String descripcion;
    
    CodigoError(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    // Getters...
}

public class ValidacionException extends RuntimeException {
    private final CodigoError codigoError;
    
    public ValidacionException(CodigoError codigoError, String detalle) {
        super(codigoError.getDescripcion() + ": " + detalle);
        this.codigoError = codigoError;
    }
}
```

## Validador acumulativo

En vez de fallar en el primer error, acumula todos:

```java
public class ResultadoValidacion {
    private final List<String> errores = new ArrayList<>();
    
    public void agregarError(String error) { errores.add(error); }
    public boolean esValido() { return errores.isEmpty(); }
    public List<String> getErrores() { return Collections.unmodifiableList(errores); }
    
    public void lanzarSiInvalido() {
        if (!esValido()) {
            throw new ValidacionException("Errores: " + String.join(", ", errores));
        }
    }
}
```

## Suppressed exceptions

Cuando un error ocurre al cerrar un recurso Y otro error ya habia ocurrido:

```java
try (MiRecurso r = new MiRecurso()) {
    r.usar();         // Lanza ExcepcionA
} // r.close()        // Lanza ExcepcionB
// ExcepcionA es la principal
// ExcepcionB se anade como "suppressed"

catch (Exception e) {
    e.getSuppressed(); // [ExcepcionB]
}
```

## Rethrow enriquecido

```java
try {
    procesarPedido(pedido);
} catch (Exception e) {
    throw new ProcesoPedidoException(
        "Error procesando pedido " + pedido.getId() + 
        " del cliente " + pedido.getCliente(),
        e  // Preserva la causa original
    );
}
```

## Anti-patrones (LO QUE NUNCA DEBES HACER)

### 1. Catch vacio (tragar excepciones)
```java
// MAL: el error se pierde silenciosamente
try { algo(); } catch (Exception e) { }
```

### 2. Catch(Exception) generico
```java
// MAL: capturas TODO, incluso bugs
try { algo(); } catch (Exception e) { log(e); }
```

### 3. Pokemon Exception Handling ("Gotta catch 'em all")
```java
// MAL: no distingues tipos de error
try { todo(); } catch (Throwable t) { /* meh */ }
```

### 4. Usar excepciones para flujo de control
```java
// MAL: las excepciones son para errores, no para logica normal
try {
    int valor = Integer.parseInt(texto);
} catch (NumberFormatException e) {
    valor = 0; // Usa un if/else en vez de esto
}
```
