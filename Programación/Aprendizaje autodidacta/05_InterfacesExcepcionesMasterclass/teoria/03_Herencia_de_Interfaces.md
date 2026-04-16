# Nivel 3: Herencia de Interfaces

## Interfaces que extienden otras interfaces

Las interfaces pueden extender una o varias interfaces, creando jerarquias:

```java
public interface Leible {
    String leer();
}

public interface Escribible {
    void escribir(String contenido);
}

// Extiende UNA interfaz
public interface LeibleConFormato extends Leible {
    String leerFormateado(String formato);
}

// Extiende MULTIPLES interfaces
public interface Archivo extends Leible, Escribible {
    String getNombre();
}
```

Quien implemente `Archivo` debe implementar `leer()`, `escribir()` Y `getNombre()`.

## Cadenas de herencia

Puedes crear jerarquias profundas:

```
    Identificable          (getId)
         |
    Nombrable              (getId + getNombre)
         |
    Describible            (getId + getNombre + getDescripcion)
```

```java
public interface Identificable {
    String getId();
}

public interface Nombrable extends Identificable {
    String getNombre();
}

public interface Describible extends Nombrable {
    String getDescripcion();
}
```

## instanceof con interfaces

Puedes comprobar si un objeto implementa una interfaz:

```java
Object obj = obtenerAlgo();

if (obj instanceof Leible) {
    Leible leible = (Leible) obj;
    System.out.println(leible.leer());
}

// Java 16+: Pattern Matching
if (obj instanceof Leible leible) {
    System.out.println(leible.leer());
}
```

## Casting seguro

Siempre verifica con `instanceof` antes de hacer casting:

```java
// PELIGROSO (puede lanzar ClassCastException)
Leible leible = (Leible) objeto;

// SEGURO
if (objeto instanceof Leible leible) {
    leible.leer();
}
```

## Principio de Segregacion de Interfaces (ISP)

Es el "I" de SOLID. Dice: **"Ninguna clase debe verse obligada a implementar metodos que no usa"**.

### MAL: Interfaz gorda
```java
public interface Trabajador {
    void trabajar();
    void comer();
    void dormir();
    void programar();
    void disenar();
}
// Un disenador no necesita programar()
// Un robot no necesita comer() ni dormir()
```

### BIEN: Interfaces segregadas
```java
public interface Trabajable { void trabajar(); }
public interface Alimentable { void comer(); }
public interface Descanable { void dormir(); }
public interface Programable { void programar(); }
public interface Disenable { void disenar(); }

// Cada clase implementa SOLO lo que necesita
public class Programador implements Trabajable, Alimentable, Descanable, Programable { ... }
public class Robot implements Trabajable, Programable { ... }
```

## Interfaces genericas

Las interfaces pueden usar generics para ser reutilizables:

```java
public interface Transformable<T> {
    T transformar(T entrada);
}

public interface Convertidor<E, S> {
    S convertir(E entrada);
}

// Implementaciones concretas
public class DuplicadorTexto implements Transformable<String> {
    @Override
    public String transformar(String entrada) {
        return entrada + entrada;
    }
}
```

## Bounded type parameters

Puedes restringir los tipos genericos:

```java
// T debe ser Comparable
public interface Ordenable<T extends Comparable<T>> {
    List<T> ordenar(List<T> elementos);
}

// T debe implementar multiples interfaces
public interface Procesable<T extends Serializable & Comparable<T>> {
    T procesar(T entrada);
}
```

## Covarianza y contravarianza

Cuando una interfaz retorna un tipo generico:

```java
public interface Productor<T> {
    T producir();
}

// Si Gato extends Animal...
Productor<Gato> productorGatos = () -> new Gato();

// Puedes asignarlo a Productor<? extends Animal> (covarianza)
Productor<? extends Animal> productorAnimales = productorGatos;
```

Esto es util para trabajar con colecciones y streams de forma flexible.
