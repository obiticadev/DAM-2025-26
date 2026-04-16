# Nivel 4: Interfaces Funcionales

## Que es una interfaz funcional?

Una interfaz funcional es una interfaz que tiene **exactamente UN metodo abstracto** (SAM = Single Abstract Method). Puede tener otros metodos default o static, pero solo uno abstracto.

```java
@FunctionalInterface  // Opcional pero recomendado. El compilador verifica.
public interface Operacion {
    double ejecutar(double a, double b);  // Solo UN metodo abstracto
}
```

## Por que importan?

Porque son la base de las **lambdas**. Una lambda es una forma corta de implementar una interfaz funcional:

```java
// Antes: clase anonima
Operacion suma = new Operacion() {
    @Override
    public double ejecutar(double a, double b) {
        return a + b;
    }
};

// Ahora: lambda
Operacion suma = (a, b) -> a + b;
```

## Interfaces funcionales de java.util.function

Java te da muchas interfaces funcionales listas para usar:

### Las 4 basicas:

| Interfaz         | Metodo           | Descripcion                    | Ejemplo                    |
|-----------------|------------------|--------------------------------|----------------------------|
| `Predicate<T>`  | `test(T) -> boolean` | Evalua una condicion       | `x -> x > 5`              |
| `Function<T,R>` | `apply(T) -> R`      | Transforma T en R          | `s -> s.length()`          |
| `Consumer<T>`   | `accept(T) -> void`  | Consume sin devolver nada  | `s -> System.out.println(s)` |
| `Supplier<T>`   | `get() -> T`         | Produce sin recibir nada   | `() -> new ArrayList<>()`  |

### Variantes Bi (dos parametros):

| Interfaz              | Metodo                  |
|----------------------|-------------------------|
| `BiPredicate<T,U>`   | `test(T, U) -> boolean` |
| `BiFunction<T,U,R>`  | `apply(T, U) -> R`      |
| `BiConsumer<T,U>`    | `accept(T, U) -> void`  |

### Operadores (entrada y salida del mismo tipo):

| Interfaz               | Metodo             | Equivalente a          |
|-----------------------|--------------------|------------------------|
| `UnaryOperator<T>`    | `apply(T) -> T`    | `Function<T, T>`       |
| `BinaryOperator<T>`   | `apply(T, T) -> T` | `BiFunction<T, T, T>`  |

## Composicion de funciones

### Predicate: and, or, negate
```java
Predicate<Integer> esPar = n -> n % 2 == 0;
Predicate<Integer> esPositivo = n -> n > 0;

Predicate<Integer> esParYPositivo = esPar.and(esPositivo);
Predicate<Integer> esParOPositivo = esPar.or(esPositivo);
Predicate<Integer> esImpar = esPar.negate();
```

### Function: andThen, compose
```java
Function<String, String> mayusculas = String::toUpperCase;
Function<String, String> exclamar = s -> s + "!";

// andThen: primero mayusculas, luego exclamar
Function<String, String> grito = mayusculas.andThen(exclamar);
grito.apply("hola");  // "HOLA!"

// compose: primero exclamar, luego mayusculas
Function<String, String> gritoV2 = mayusculas.compose(exclamar);
gritoV2.apply("hola");  // "HOLA!"
```

### Consumer: andThen
```java
Consumer<String> imprimir = System.out::println;
Consumer<String> logear = s -> logger.info(s);

Consumer<String> imprimirYLogear = imprimir.andThen(logear);
```

## Lambda como parametro y retorno

```java
// Como parametro
public static List<String> filtrar(List<String> lista, Predicate<String> criterio) {
    return lista.stream().filter(criterio).collect(Collectors.toList());
}

// Como retorno
public static Predicate<String> longitudMinima(int min) {
    return s -> s.length() >= min;
}

// Uso:
List<String> resultado = filtrar(nombres, longitudMinima(3));
```

## Method references

Cuando la lambda solo llama a un metodo existente, puedes usar method reference:

```java
// Lambda vs Method Reference
Function<String, Integer> f1 = s -> s.length();
Function<String, Integer> f2 = String::length;    // Equivalente

Consumer<String> c1 = s -> System.out.println(s);
Consumer<String> c2 = System.out::println;         // Equivalente
```

Tipos de method reference:
- `Clase::metodoStatico` — `Integer::parseInt`
- `Clase::metodoInstancia` — `String::toUpperCase` (el objeto es el primer param)
- `objeto::metodo` — `System.out::println`
- `Clase::new` — `ArrayList::new`
