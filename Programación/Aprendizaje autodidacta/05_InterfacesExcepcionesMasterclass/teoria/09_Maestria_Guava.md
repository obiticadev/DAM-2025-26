# Nivel 9: Maestria Total + Google Guava

## Que es Google Guava?

Guava es una libreria de Google que extiende las capacidades de Java con utilidades profesionales. Incluye herramientas para validacion, colecciones, strings, I/O y mucho mas.

En este nivel nos centramos en las utilidades de **validacion y excepciones** de Guava.

## Preconditions: Validacion profesional

`com.google.common.base.Preconditions` ofrece metodos para validar argumentos de forma limpia:

### checkNotNull
```java
import com.google.common.base.Preconditions;

public void procesar(String nombre) {
    // Lanza NullPointerException si nombre es null
    Preconditions.checkNotNull(nombre, "El nombre no puede ser null");
    
    // Con formato (como String.format)
    Preconditions.checkNotNull(nombre, "El %s no puede ser null", "nombre");
}
```

### checkArgument
```java
public void setEdad(int edad) {
    // Lanza IllegalArgumentException si la condicion es false
    Preconditions.checkArgument(edad >= 0, "La edad no puede ser negativa: %s", edad);
    Preconditions.checkArgument(edad <= 150, "La edad %s no es realista", edad);
    this.edad = edad;
}
```

### checkState
```java
public void enviar(String mensaje) {
    // Lanza IllegalStateException si la condicion es false
    Preconditions.checkState(conectado, "No se puede enviar sin conexion activa");
    // enviar...
}
```

### checkElementIndex / checkPositionIndex
```java
public String obtener(int indice) {
    // Lanza IndexOutOfBoundsException si indice esta fuera de rango
    Preconditions.checkElementIndex(indice, lista.size(), "indice");
    return lista.get(indice);
}
```

## Comparacion: Manual vs Guava

### Validacion manual
```java
public void transferir(Cuenta origen, Cuenta destino, double cantidad) {
    if (origen == null) throw new NullPointerException("origen no puede ser null");
    if (destino == null) throw new NullPointerException("destino no puede ser null");
    if (cantidad <= 0) throw new IllegalArgumentException("cantidad debe ser positiva");
    if (!origen.isActiva()) throw new IllegalStateException("cuenta origen inactiva");
    // ...
}
```

### Con Guava Preconditions
```java
public void transferir(Cuenta origen, Cuenta destino, double cantidad) {
    checkNotNull(origen, "origen no puede ser null");
    checkNotNull(destino, "destino no puede ser null");
    checkArgument(cantidad > 0, "cantidad debe ser positiva: %s", cantidad);
    checkState(origen.isActiva(), "cuenta origen inactiva");
    // ...
}
```

Mas limpio, mas conciso, mismos tipos de excepcion.

## Throwables: Utilidades para excepciones

`com.google.common.base.Throwables` ofrece herramientas utiles:

### getStackTraceAsString
```java
try {
    operacionPeligrosa();
} catch (Exception e) {
    String stackTrace = Throwables.getStackTraceAsString(e);
    logger.error("Error completo:\n" + stackTrace);
}
```

### getRootCause
```java
try {
    operacionPeligrosa();
} catch (Exception e) {
    Throwable raiz = Throwables.getRootCause(e);
    System.out.println("Causa raiz: " + raiz.getMessage());
}
```

### getCausalChain
```java
try {
    operacionPeligrosa();
} catch (Exception e) {
    List<Throwable> cadena = Throwables.getCausalChain(e);
    cadena.forEach(t -> System.out.println(" -> " + t.getClass().getSimpleName()));
}
```

## Combinando todo: Sistema completo

En un proyecto real, combinas interfaces, excepciones custom y Guava:

```java
@FunctionalInterface
public interface Validador<T> {
    ResultadoValidacion validar(T objeto);
    
    default Validador<T> y(Validador<T> otro) {
        return obj -> {
            ResultadoValidacion r1 = this.validar(obj);
            ResultadoValidacion r2 = otro.validar(obj);
            return r1.combinar(r2);
        };
    }
}

public class ServicioUsuarios {
    public void registrar(Usuario usuario) {
        checkNotNull(usuario, "usuario no puede ser null");
        
        Validador<Usuario> validadorCompleto = 
            validarNombre.y(validarEmail).y(validarEdad);
        
        ResultadoValidacion resultado = validadorCompleto.validar(usuario);
        if (!resultado.esValido()) {
            throw new ValidacionException(resultado.getErrores());
        }
        
        // Guardar...
    }
}
```

Esto es programacion profesional: interfaces para abstraccion, excepciones para errores, Guava para validacion limpia.
