# Bloque III — Clases con Arrays Bidimensionales

> Referencia para ejercicios `Ej13` a `Ej18` en `src/main/java/bloque3/`

## 1. Encapsular un array bidi dentro de una clase

El paso fundamental: en vez de trabajar con `int[][]` sueltos, los metemos dentro de una clase que gestiona su acceso.

```mermaid
classDiagram
    class Tablero {
        -int[][] celdas
        -int filas
        -int columnas
        +Tablero(int filas, int columnas)
        +getValor(int f, int c) int
        +setValor(int f, int c, int v) boolean
        +mostrar() String
    }
```

**Ventajas:**
- El array es `private` → nadie accede directamente
- Toda validación de rango se hace dentro de la clase
- Los métodos devuelven boolean/null si algo falla, nunca lanzan `ArrayIndexOutOfBoundsException`

## 2. Constructor con validaciones

```mermaid
flowchart TD
    A["Constructor(filas, columnas)"] --> B{"filas <= 0?"}
    B -- sí --> C["throw Excepcion"]
    B -- no --> D{"filas > MAX?"}
    D -- sí --> C
    D -- no --> E{"columnas <= 0?"}
    E -- sí --> C
    E -- no --> F{"columnas > MAX?"}
    F -- sí --> C
    F -- no --> G["Crear array"]
    G --> H["Inicializar celdas"]
```

**Patrón estándar:** Todas las validaciones ANTES de crear el array. Si alguna falla, lanzamos excepción y el objeto no se crea.

## 3. Excepciones personalizadas

```mermaid
classDiagram
    class IllegalArgumentException {
        <<Java Standard>>
    }
    class DimensionException {
        -String mensaje
        +DimensionException(String msg)
        +getMessage() String
    }
    IllegalArgumentException <|-- DimensionException
```

**Patrón:** La excepción custom extiende `IllegalArgumentException` (o `RuntimeException`). El constructor recibe el mensaje y lo pasa a `super(msg)`.

## 4. Composición: array bidimensional de objetos

Cuando cada celda no es un `int` sino un objeto con su propio estado:

```mermaid
classDiagram
    class Sala {
        -Butaca[][] butacas
        +Sala(int filas, int cols)
        +reservar(int f, int c) boolean
        +liberar(int id) boolean
        +mostrar() String
    }
    class Butaca {
        -static int contador
        -char estado
        -int idReserva
        +Butaca()
        +reservar() void
        +liberar() void
    }
    Sala *-- Butaca : contiene NxM
```

**Regla crítica:** Cada celda debe ser una instancia INDEPENDIENTE. Siempre bucle + `new`.

```mermaid
flowchart TD
    A["Crear array Butaca[filas][cols]"] --> B["for i = 0..filas"]
    B --> C["for j = 0..cols"]
    C --> D["butacas[i][j] = new Butaca()"]
    D --> C
    C --> B
```

## 5. Métodos de negocio sobre el array

```mermaid
flowchart LR
    subgraph "Reservar(fila, col)"
        R1["Validar rango"] --> R2{"Está libre?"}
        R2 -- sí --> R3["Marcar ocupado + asignar ID"]
        R3 --> R4["return true"]
        R2 -- no --> R5["return false"]
        R1 -- fuera --> R5
    end
    subgraph "Liberar(idReserva)"
        L1["Recorrer todo el array"] --> L2{"ID coincide?"}
        L2 -- sí --> L3["Marcar libre + borrar ID"]
        L3 --> L4["return true"]
        L2 -- no --> L1
        L1 -- fin --> L5["return false"]
    end
```

## 6. Representación visual (mostrar/toString)

```mermaid
flowchart TD
    A["StringBuilder sb"] --> B["Cabecera opcional"]
    B --> C["for cada fila"]
    C --> D["for cada celda"]
    D --> E["sb.append(celda.getEstado())"]
    E --> D
    D --> F["sb.append newline"]
    F --> C
    C --> G["return sb.toString()"]
```

Usa `StringBuilder` siempre. Nunca concatenes con `+` en bucles.
