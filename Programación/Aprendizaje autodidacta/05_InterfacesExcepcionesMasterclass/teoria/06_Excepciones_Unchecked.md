# Nivel 6: Excepciones Unchecked (RuntimeException)

## Que son las unchecked?

Son excepciones que heredan de `RuntimeException`. El compilador NO te obliga a capturarlas ni declararlas con `throws`. Representan errores de **programacion** (bugs), no de circunstancias externas.

## Las mas comunes

### NullPointerException (NPE)
La mas odiada. Ocurre cuando intentas usar algo que es `null`:

```java
String nombre = null;
nombre.length();        // NPE!
nombre.toUpperCase();   // NPE!

List<String> lista = null;
lista.add("hola");      // NPE!
```

**Prevencion**:
```java
// Opcion 1: Comprobar antes
if (nombre != null) { nombre.length(); }

// Opcion 2: Optional (Java 8+)
Optional.ofNullable(nombre).map(String::length).orElse(0);

// Opcion 3: Objects.requireNonNull (falla rapido con mensaje claro)
Objects.requireNonNull(nombre, "El nombre no puede ser null");
```

### IllegalArgumentException
Para validar parametros de entrada:

```java
public void setEdad(int edad) {
    if (edad < 0 || edad > 150) {
        throw new IllegalArgumentException("Edad invalida: " + edad);
    }
    this.edad = edad;
}
```

### IllegalStateException
Cuando un objeto esta en un estado invalido para la operacion:

```java
public class Conexion {
    private boolean conectado = false;
    
    public void enviar(String datos) {
        if (!conectado) {
            throw new IllegalStateException("No se puede enviar sin conexion activa");
        }
        // enviar datos...
    }
}
```

### IndexOutOfBoundsException
Acceso a indice invalido:

```java
List<String> lista = List.of("A", "B", "C");
lista.get(5);  // IndexOutOfBoundsException
lista.get(-1); // IndexOutOfBoundsException
```

### NumberFormatException
Parseo de texto a numero fallido:

```java
Integer.parseInt("abc");     // NumberFormatException
Double.parseDouble("12.3x"); // NumberFormatException
```

### UnsupportedOperationException
Operacion no soportada (comun en colecciones inmutables):

```java
List<String> inmutable = List.of("A", "B");
inmutable.add("C");  // UnsupportedOperationException!

List<String> inmutable2 = Collections.unmodifiableList(lista);
inmutable2.remove(0);  // UnsupportedOperationException!
```

### ClassCastException
Casting incorrecto:

```java
Object obj = "texto";
Integer num = (Integer) obj;  // ClassCastException!
```

**Prevencion**: Usa `instanceof` antes del cast, o mejor aun, usa generics.

## Crear una excepcion unchecked custom

```java
public class SaldoInsuficienteException extends RuntimeException {
    
    private final double saldoActual;
    private final double cantidadSolicitada;
    
    public SaldoInsuficienteException(double saldoActual, double cantidadSolicitada) {
        super(String.format("Saldo insuficiente: tienes %.2f, necesitas %.2f", 
              saldoActual, cantidadSolicitada));
        this.saldoActual = saldoActual;
        this.cantidadSolicitada = cantidadSolicitada;
    }
    
    public double getSaldoActual() { return saldoActual; }
    public double getCantidadSolicitada() { return cantidadSolicitada; }
}
```

## Checked vs Unchecked: Cuando usar cada una?

| Usa Checked cuando...                        | Usa Unchecked cuando...                   |
|----------------------------------------------|-------------------------------------------|
| El error es externo y predecible             | El error es un bug del programador        |
| El llamador PUEDE recuperarse                | El llamador NO puede hacer nada util      |
| Fallo de red, archivo, BD                    | Null, indice invalido, argumento malo     |
| El compilador debe obligar al manejo         | Obligar al manejo ensuciaria el codigo    |

**Regla practica moderna**: La tendencia actual es preferir unchecked y documentar bien. Spring, Hibernate y la mayoria de frameworks modernos usan unchecked.
