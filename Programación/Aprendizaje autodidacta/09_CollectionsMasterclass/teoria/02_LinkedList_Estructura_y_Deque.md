# 02 — LinkedList: Estructura, Deque y Queue

> **Referencia de ejercicios**: Ejercicio07 · Ejercicio08 · Ejercicio09

---

## 1. Qué es LinkedList y qué interfaces implementa

`LinkedList<E>` es una lista **doblemente enlazada** (doubly-linked list). Cada elemento
vive en un nodo que conoce al nodo anterior y al siguiente. A diferencia de ArrayList,
**no hay un array interno**: los nodos se distribuyen por el heap.

### Jerarquía de interfaces

```mermaid
classDiagram
    direction TB
    class List~E~ {
        <<interface>>
    }
    class Queue~E~ {
        <<interface>>
        +offer(E e) boolean
        +poll() E
        +peek() E
    }
    class Deque~E~ {
        <<interface>>
        +addFirst(E e) void
        +addLast(E e) void
        +peekFirst() E
        +peekLast() E
        +pollFirst() E
        +pollLast() E
        +push(E e) void
        +pop() E
    }
    class AbstractSequentialList~E~ {
        <<abstract>>
    }
    class LinkedList~E~ {
        -Node~E~ first
        -Node~E~ last
        -int size
    }
    class Node~E~ {
        -E item
        -Node~E~ next
        -Node~E~ prev
    }

    List~E~ <|.. AbstractSequentialList~E~
    Queue~E~ <|-- Deque~E~
    AbstractSequentialList~E~ <|-- LinkedList~E~
    Deque~E~ <|.. LinkedList~E~
    LinkedList~E~ *-- Node~E~
```

> `LinkedList` implementa a la vez `List` **y** `Deque`. Esto la hace única: puede funcionar
> como lista indexada, como cola FIFO y como pila LIFO.

---

## 2. Estructura interna: nodos doblemente enlazados

```mermaid
flowchart LR
    HEAD["first\n(cabeza)"] --> N1
    subgraph N1["Nodo A"]
        P1["prev = null"] ~~~ V1["item = 'A'"] ~~~ NX1["next →"]
    end
    N1 --> N2
    subgraph N2["Nodo B"]
        P2["← prev"] ~~~ V2["item = 'B'"] ~~~ NX2["next →"]
    end
    N2 --> N3
    subgraph N3["Nodo C"]
        P3["← prev"] ~~~ V3["item = 'C'"] ~~~ NX3["next = null"]
    end
    N3 --> TAIL["last\n(cola)"]
```

**Coste de operaciones:**

| Operación | Coste | Razón |
|---|---|---|
| `addFirst()` / `addLast()` | O(1) | Solo actualiza `first` o `last` |
| `removeFirst()` / `removeLast()` | O(1) | Misma razón |
| `get(i)` | O(n) | Traversal desde el extremo más cercano |
| `add(i, e)` en medio | O(n) | Búsqueda del nodo + O(1) enlace |
| `contains(o)` | O(n) | Búsqueda lineal |

---

## 3. LinkedList como Deque (pila doble)

`Deque` (Double Ended Queue) permite operar en **ambos extremos** de la colección.

```mermaid
flowchart LR
    OP_ADD_FIRST["addFirst() / push()"] -- "inserta ←" --> FRENTE
    FRENTE["[ A ][ B ][ C ][ D ]"] -- "extrae →" --> OP_POLL_FIRST["pollFirst() / pop()"]
    FRENTE -- "consulta →" --> OP_PEEK_FIRST["peekFirst()"]

    OP_ADD_LAST["addLast()"] -- "inserta →" --> COLA["[ A ][ B ][ C ][ D ]"]
    COLA -- "extrae ←" --> OP_POLL_LAST["pollLast()"]
    COLA -- "consulta ←" --> OP_PEEK_LAST["peekLast()"]
```

### Mapa de métodos equivalentes

| Acción | Método Deque | Equivalente List |
|---|---|---|
| Insertar al frente | `addFirst(e)` / `push(e)` | `add(0, e)` |
| Insertar al final | `addLast(e)` | `add(e)` |
| Consultar frente (sin extraer) | `peekFirst()` | `get(0)` |
| Consultar final (sin extraer) | `peekLast()` | `get(size-1)` |
| Extraer frente | `pollFirst()` / `pop()` | `remove(0)` |
| Extraer final | `pollLast()` | `remove(size-1)` |

> `peek*` devuelve `null` si la lista está vacía.  
> `get*` lanza `NoSuchElementException` si está vacía.

---

## 4. LinkedList como Queue (cola FIFO)

En una cola FIFO los elementos entran por un extremo y salen por el otro.

```mermaid
sequenceDiagram
    participant P as Productor
    participant Q as LinkedList (Queue)
    participant C as Consumidor

    P->>Q: offer("pedido-1")
    P->>Q: offer("pedido-2")
    P->>Q: offer("pedido-3")
    Note over Q: ["pedido-1", "pedido-2", "pedido-3"]
    C->>Q: peek()
    Q-->>C: "pedido-1" (sin extraer)
    C->>Q: poll()
    Q-->>C: "pedido-1" (extraído)
    Note over Q: ["pedido-2", "pedido-3"]
    C->>Q: poll()
    Q-->>C: "pedido-2"
    Note over Q: ["pedido-3"]
```

### Diferencia entre offer/poll y add/remove

| Método | Si falla (cola llena/vacía) |
|---|---|
| `add(e)` | Lanza `IllegalStateException` |
| `offer(e)` | Retorna `false` |
| `remove()` | Lanza `NoSuchElementException` |
| `poll()` | Retorna `null` |
| `element()` | Lanza `NoSuchElementException` |
| `peek()` | Retorna `null` |

> Para `LinkedList` (cola no acotada), `add` y `offer` son equivalentes.
> En colas acotadas (ej: `ArrayBlockingQueue`) la diferencia es crítica.

---

## 5. LinkedList vs ArrayList — ¿cuándo usar cada uno?

```mermaid
flowchart TD
    A[¿Qué operación domina?] --> B{Acceso aleatorio por índice}
    A --> C{Inserción/eliminación frecuente en extremos}
    A --> D{Inserción/eliminación frecuente en el medio}

    B -- "get(i) O(1)" --> E["✅ Usa ArrayList"]
    C -- "addFirst/pollFirst O(1)" --> F["✅ Usa LinkedList como Deque/Queue"]
    D -- "Búsqueda O(n) + enlace O(1)" --> G{¿El índice ya es conocido?}
    G -- No --> H["⚠️ Ambos son O(n) — prefiere ArrayList por localidad de caché"]
    G -- Sí --> I["LinkedList puede ser ventajoso si ya tienes el nodo"]
```

### Tabla de decisión rápida

| Escenario | Ganador |
|---|---|
| Leer elemento en posición `i` | `ArrayList` |
| Añadir/eliminar al final | Empate (ambos O(1) amortizado) |
| Añadir/eliminar al principio | `LinkedList` |
| Simular una cola FIFO | `LinkedList` (o `ArrayDeque`) |
| Simular una pila LIFO | `LinkedList` (o `ArrayDeque`) |
| Mucho acceso aleatorio + pocos inserts | `ArrayList` |
| Muchos inserts/deletes + poco acceso | `LinkedList` |

---

## 6. Iteración en LinkedList

**Preferir siempre** `for-each`, `iterator()` o `listIterator()` sobre `get(i)` en un bucle.

```
// MAL — O(n²) para LinkedList
for (int i = 0; i < lista.size(); i++) {
    System.out.println(lista.get(i));  // cada get() hace traversal
}

// BIEN — O(n)
for (String s : lista) {
    System.out.println(s);  // el iterator avanza nodo a nodo
}
```

---

## Puntos clave para los ejercicios

- `LinkedList` implementa `List` **y** `Deque`: úsala con el tipo de referencia correcto.
- `addFirst`/`pollFirst` = stack LIFO; `addLast`/`pollFirst` = queue FIFO.
- `peek*` es seguro (retorna null); `get*`/`element()` lanza excepción.
- **Nunca** accedas por índice (`get(i)`) dentro de un bucle en LinkedList.
- El acceso por índice en ArrayList es O(1); en LinkedList es O(n).
