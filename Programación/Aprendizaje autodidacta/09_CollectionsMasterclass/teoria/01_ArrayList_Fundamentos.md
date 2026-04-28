# 01 — ArrayList: Fundamentos

> **Referencia de ejercicios**: Ejercicio01 · Ejercicio02 · Ejercicio03 · Ejercicio04 · Ejercicio05 · Ejercicio06

---

## 1. ¿Qué es ArrayList?

`ArrayList<E>` es la implementación más usada de la interfaz `List`. Internamente almacena
elementos en un **array de objetos redimensionable**. Cuando el array se llena, Java crea
uno nuevo con un 50% más de capacidad y copia todos los elementos.

### Jerarquía de interfaces

```mermaid
classDiagram
    direction TB
    class Iterable~E~ {
        <<interface>>
        +iterator() Iterator~E~
    }
    class Collection~E~ {
        <<interface>>
        +add(E e) boolean
        +remove(Object o) boolean
        +size() int
        +contains(Object o) boolean
        +isEmpty() boolean
        +clear() void
    }
    class List~E~ {
        <<interface>>
        +get(int index) E
        +set(int index, E element) E
        +add(int index, E element) void
        +remove(int index) E
        +indexOf(Object o) int
        +subList(int from, int to) List~E~
    }
    class AbstractList~E~ {
        <<abstract>>
    }
    class ArrayList~E~ {
        -Object[] elementData
        -int size
        +ArrayList()
        +ArrayList(Collection c)
        +ArrayList(int initialCapacity)
        +trimToSize() void
        +ensureCapacity(int min) void
    }

    Iterable~E~ <|-- Collection~E~
    Collection~E~ <|-- List~E~
    List~E~ <|.. AbstractList~E~
    AbstractList~E~ <|-- ArrayList~E~
```

---

## 2. Memoria interna: cómo crece ArrayList

```mermaid
stateDiagram-v2
    [*] --> VacioCapacidad10 : new ArrayList()

    VacioCapacidad10 --> LlenoCapacidad10 : add() × 10 elementos
    LlenoCapacidad10 --> Redimensionando : add() elemento 11
    Redimensionando --> NuevoArray15 : Arrays.copyOf(array, size * 1.5)
    NuevoArray15 --> LlenoCapacidad15 : add() × 5 elementos
    LlenoCapacidad15 --> Redimensionando : add() elemento 16

    note right of Redimensionando
        O(n) para copiar
        Amortizado O(1) por add()
    end note
```

**Coste de operaciones:**

| Operación | Coste temporal |
|---|---|
| `get(i)` / `set(i, e)` | O(1) |
| `add(e)` al final | O(1) amortizado |
| `add(i, e)` en medio | O(n) — desplaza elementos |
| `remove(i)` | O(n) — desplaza elementos |
| `contains(o)` | O(n) — búsqueda lineal |

---

## 3. Operaciones CRUD básicas

### Crear y añadir (`add`)

```
lista.add("A")          →  ["A"]
lista.add("B")          →  ["A", "B"]
lista.add(0, "X")       →  ["X", "A", "B"]   ← desplaza todo
lista.add(1, "Y")       →  ["X", "Y", "A", "B"]
```

### Leer (`get`) y modificar (`set`)

```
lista.get(0)            →  "X"
lista.set(0, "Z")       →  ["Z", "Y", "A", "B"]
```

### Eliminar (`remove`)

```
lista.remove(0)         →  "Z"  (retorna el eliminado)  → ["Y", "A", "B"]
lista.remove("A")       →  true (elimina la primera ocurrencia) → ["Y", "B"]
```

### Flujo de remove(int index)

```mermaid
sequenceDiagram
    participant C as Código
    participant AL as ArrayList
    participant A as Array interno

    C->>AL: remove(1)
    AL->>A: Guarda elemento[1] → valor a retornar
    AL->>A: System.arraycopy(array, 2, array, 1, size-2)
    AL->>A: array[size-1] = null  ← ayuda al GC
    AL->>AL: size--
    AL-->>C: retorna el valor guardado
```

---

## 4. Formas de iterar un ArrayList

### Comparativa visual

```mermaid
flowchart TD
    A[Iterar ArrayList] --> B{¿Necesito el índice?}
    B -- Sí --> C[for-i: for int i=0; i<lista.size; i++]
    B -- No --> D{¿Necesito modificar durante iteración?}
    D -- No --> E[for-each: for String s : lista]
    D -- Sí --> F{¿Avanzar y retroceder?}
    F -- No --> G[Iterator: it.next / it.remove]
    F -- Sí --> H[ListIterator: it.previous / it.nextIndex]
```

---

## 5. Búsqueda y filtrado

| Método | Descripción | Retorna |
|---|---|---|
| `indexOf(o)` | Primera posición de `o` | `int` (-1 si no existe) |
| `lastIndexOf(o)` | Última posición de `o` | `int` |
| `contains(o)` | ¿Existe `o`? | `boolean` |
| `subList(from, to)` | Vista parcial `[from, to)` | `List<E>` (backed view) |
| `removeIf(pred)` | Elimina todos los que cumplan el predicado | `boolean` |

> **Cuidado con `subList`**: retorna una **vista** del ArrayList original, no una copia.
> Modificar la sublista modifica el ArrayList original. Para una copia real:
> `new ArrayList<>(lista.subList(from, to))`

---

## 6. Ordenación con Comparator

```mermaid
flowchart LR
    A[ArrayList desordenado] --> B[Collections.sort o lista.sort]
    B --> C{¿Comparator definido?}
    C -- No --> D[Orden natural: Comparable.compareTo]
    C -- Sí --> E[Usa Comparator.compare]
    D --> F[ArrayList ordenado ↑]
    E --> F
    F --> G{reversed?}
    G -- No --> H[Ascendente]
    G -- Sí --> I[Descendente]
```

**Comparator encadenado:**
```
Comparator.comparing(String::length)
          .thenComparing(Comparator.naturalOrder())
```
Ordena primero por longitud; si hay empate, aplica orden alfabético.

---

## 7. ArrayList con objetos propios — equals y hashCode

Para que `contains()`, `remove(Object)` e `indexOf()` funcionen correctamente con tus
propias clases, **debes sobrescribir `equals()`**. Si sobrescribes `equals()`, Java también
exige que sobrescribas `hashCode()` (contrato de Java SE).

```mermaid
sequenceDiagram
    participant C as Código
    participant AL as ArrayList
    participant E as equals()

    C->>AL: contains(producto)
    loop Para cada elemento
        AL->>E: elemento.equals(producto)
        E-->>AL: true / false
    end
    AL-->>C: true si alguno retornó true
```

---

## 8. Operaciones de colección (bulk)

| Método | Acción |
|---|---|
| `addAll(Collection c)` | Añade todos los elementos de `c` al final |
| `addAll(int i, Collection c)` | Inserta todos desde la posición `i` |
| `removeAll(Collection c)` | Elimina todos los elementos que estén en `c` |
| `retainAll(Collection c)` | Conserva solo los que estén en `c` (intersección) |
| `containsAll(Collection c)` | `true` si contiene todos los elementos de `c` |

---

## 9. Inmutabilidad y vistas no modificables

```
List<String> inmutable = List.of("A", "B", "C");   // Java 9+ — no permite null
List<String> envuelta  = Collections.unmodifiableList(lista);  // vista de otra lista
```

Intentar `add()`, `set()` o `remove()` sobre estas referencias lanza
`UnsupportedOperationException` en tiempo de ejecución.

---

## Puntos clave para los ejercicios

- `ArrayList` acepta `null` y elementos duplicados.
- El índice de acceso es base-0.
- `subList()` devuelve una vista; haz `new ArrayList<>(subList(...))` para copia independiente.
- Siempre sobrescribe `equals()` (y `hashCode()`) en tus clases antes de usarlas en una List.
- Prefiere `for-each` para lectura; usa `Iterator` o `removeIf` para eliminar durante iteración.
