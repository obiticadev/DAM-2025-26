# Nivel 2: Metodos Default y Static en Interfaces

## El problema antes de Java 8

Antes de Java 8, si anadieras un nuevo metodo a una interfaz, **TODAS** las clases que la implementaban se rompian porque debian implementar el nuevo metodo. Esto hacia imposible evolucionar interfaces publicas.

## Metodos default

Java 8 introdujo los metodos `default`: metodos con cuerpo dentro de una interfaz.

```java
public interface Coleccion {
    void agregar(Object item);       // Abstracto (sin cuerpo)
    int tamano();                     // Abstracto
    
    // DEFAULT: tiene cuerpo, las clases lo heredan gratis
    default boolean estaVacia() {
        return tamano() == 0;
    }
}
```

### Reglas de los default:
1. Tienen la palabra clave `default` antes del tipo de retorno
2. Tienen cuerpo (implementacion)
3. Las clases que implementan la interfaz los **heredan automaticamente**
4. Las clases **pueden sobreescribirlos** si quieren un comportamiento diferente

### Sobreescribir un default:
```java
public class MiLista implements Coleccion {
    @Override
    public void agregar(Object item) { /* ... */ }
    
    @Override
    public int tamano() { /* ... */ }
    
    // Sobreescribimos el default
    @Override
    public boolean estaVacia() {
        // Nuestra version personalizada
        return tamano() <= 0;
    }
}
```

## El Diamond Problem

Que pasa si una clase implementa DOS interfaces que tienen el mismo metodo default?

```java
public interface A {
    default String saludo() { return "Hola desde A"; }
}

public interface B {
    default String saludo() { return "Hola desde B"; }
}

// ERROR DE COMPILACION si no resuelves el conflicto:
public class C implements A, B {
    // OBLIGATORIO: debes sobreescribir el metodo conflictivo
    @Override
    public String saludo() {
        // Puedes elegir una de las dos:
        return A.super.saludo();  // Llama al default de A
        // o: return B.super.saludo();
        // o: return "Mi propia version";
    }
}
```

## Metodos static en interfaces

Tambien desde Java 8, las interfaces pueden tener metodos `static`:

```java
public interface Validador {
    boolean esValido(String dato);
    
    // Metodo static: se llama con Validador.noVacio()
    static Validador noVacio() {
        return dato -> dato != null && !dato.isEmpty();
    }
    
    static Validador longitudMinima(int min) {
        return dato -> dato != null && dato.length() >= min;
    }
}
```

### Diferencias static vs default:

| Caracteristica       | default                | static                  |
|---------------------|------------------------|-------------------------|
| Se hereda?          | Si                     | No                      |
| Se sobreescribe?    | Si                     | No                      |
| Como se llama?      | objeto.metodo()        | Interfaz.metodo()       |
| Uso tipico          | Comportamiento comun   | Factories, utilidades   |

## Patron comun: Default que usa abstracto

Un patron muy potente es que el default llame a metodos abstractos:

```java
public interface Formateador {
    // Abstracto: la subclase define la transformacion
    String transformar(String texto);
    
    // Default: usa el abstracto para dar funcionalidad extra gratis
    default String transformarConPrefijo(String texto, String prefijo) {
        return prefijo + ": " + transformar(texto);
    }
    
    default String transformarYRecortar(String texto, int maxLong) {
        String resultado = transformar(texto);
        return resultado.length() > maxLong 
            ? resultado.substring(0, maxLong) + "..." 
            : resultado;
    }
}
```

Asi, quien implementa solo define `transformar()` y obtiene `transformarConPrefijo()` y `transformarYRecortar()` gratis.

## Evolucion de interfaces

Los defaults permiten anadir funcionalidad nueva a interfaces existentes sin romper implementaciones:

```java
// Version 1.0
public interface Repositorio {
    void guardar(Object entidad);
    Object buscar(int id);
}

// Version 2.0 - anadimos sin romper nada
public interface Repositorio {
    void guardar(Object entidad);
    Object buscar(int id);
    
    default void guardarTodos(List<Object> entidades) {
        entidades.forEach(this::guardar);  // Usa el abstracto existente
    }
}
```
