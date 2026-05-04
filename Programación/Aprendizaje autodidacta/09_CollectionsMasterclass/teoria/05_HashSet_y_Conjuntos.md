# 05 — HashSet y Conjuntos

> Referencia: [Ejercicios 21–25] — `nivel07_hashset/`

---

## 1. ¿Qué es un Set?

Un `Set<E>` es una colección que **no permite elementos duplicados**. La interfaz `Set` extiende `Collection` pero no añade nuevos métodos; su contrato radica en la unicidad.

### Jerarquía de interfaces

```mermaid
classDiagram
    direction TB
    class Collection~E~ {
        <<interface>>
        +add(E e) boolean
        +remove(Object o) boolean
        +contains(Object o) boolean
        +size() int
        +isEmpty() boolean
        +clear() void
        +iterator() Iterator~E~
    }
    class Set~E~ {
        <<interface>>
        «sin métodos nuevos»
        Contrato: no duplicados
    }
    class SortedSet~E~ {
        <<interface>>
        +first() E
        +last() E
        +headSet(E toElement) SortedSet
        +tailSet(E fromElement) SortedSet
    }
    class NavigableSet~E~ {
        <<interface>>
        +floor(E e) E
        +ceiling(E e) E
        +lower(E e) E
        +higher(E e) E
        +descendingSet() NavigableSet
    }
    class HashSet~E~ {
        -HashMap delegate
    }
    class LinkedHashSet~E~ {
        -LinkedHashMap delegate
    }
    class TreeSet~E~ {
        -TreeMap delegate
    }

    Collection~E~ <|-- Set~E~
    Set~E~ <|-- SortedSet~E~
    SortedSet~E~ <|-- NavigableSet~E~
    Set~E~ <|.. HashSet~E~
    HashSet~E~ <|-- LinkedHashSet~E~
    NavigableSet~E~ <|.. TreeSet~E~
```

| Implementación | Orden | Coste add/contains/remove | Cuándo usarlo |
|---|---|---|---|
| `HashSet` | Ninguno | O(1) amortizado | Unicidad rápida, orden irrelevante |
| `LinkedHashSet` | Inserción | O(1) amortizado | Unicidad + iteración predecible |
| `TreeSet` | Natural o Comparator | O(log n) | Elementos ordenados, consultas por rango |

---

## 2. Estructura interna de HashSet

`HashSet` **delega internamente en un `HashMap`**. Cada elemento del set se almacena como **clave** del HashMap, con un valor dummy constante (`PRESENT`).

```mermaid
graph TD
    subgraph "HashSet"
        HS["HashSet<String>"]
    end
    subgraph "HashMap interno"
        HM["HashMap<String, Object>"]
        B0["bucket[2] → 'banana' → PRESENT"]
        B1["bucket[5] → 'manzana' → PRESENT"]
        B2["bucket[9] → 'pera' → PRESENT"]
    end
    HS --> HM
    HM --> B0
    HM --> B1
    HM --> B2
```

> Por eso `HashSet` tiene las mismas características de rendimiento que `HashMap` y los mismos requisitos sobre `equals()` y `hashCode()`.

---

## 3. Flujo de add(): ¿es duplicado?

```mermaid
sequenceDiagram
    participant C as Código
    participant HS as HashSet
    participant HM as HashMap interno

    C->>HS: add("banana")
    HS->>HM: put("banana", PRESENT)
    HM->>HM: hashCode("banana") → bucket[2]
    alt Bucket vacío
        HM-->>HS: null (no existía) → add retorna true
    else Bucket ocupado, equals() coincide
        HM-->>HS: PRESENT (ya existía) → add retorna false
    end
    HS-->>C: true / false
```

**Regla fundamental:** `add()` retorna `true` si el elemento fue añadido (no existía) y `false` si ya estaba.

---

## 4. Operaciones de conjunto (Álgebra de conjuntos)

Java mapea las operaciones matemáticas de conjuntos a métodos de `Collection`:

```mermaid
graph LR
    subgraph "A = {1, 2, 3, 4}"
        A1((1)) --- A2((2)) --- A3((3)) --- A4((4))
    end
    subgraph "B = {3, 4, 5, 6}"
        B3((3)) --- B4((4)) --- B5((5)) --- B6((6))
    end

    subgraph "Resultados"
        U["A ∪ B = {1,2,3,4,5,6}   → addAll()"]
        I["A ∩ B = {3,4}           → retainAll()"]
        D["A - B = {1,2}           → removeAll()"]
    end
```

| Operación matemática | Método Java | Modifica el set? |
|---|---|---|
| **Unión** A ∪ B | `a.addAll(b)` | Sí (sobre `a`) |
| **Intersección** A ∩ B | `a.retainAll(b)` | Sí (sobre `a`) |
| **Diferencia** A − B | `a.removeAll(b)` | Sí (sobre `a`) |
| **Subconjunto** A ⊆ B | `b.containsAll(a)` | No |
| **Disjuntos** A ∩ B = ∅ | `Collections.disjoint(a, b)` | No |

> **Cuidado:** `addAll`, `retainAll` y `removeAll` **modifican** el set sobre el que se invocan. Para evitar modificar el original, trabaja sobre una copia: `new HashSet<>(a)`.

---

## 5. equals() y hashCode() — Imprescindibles

Para usar objetos propios en un `HashSet`, **debes** sobrescribir `equals()` y `hashCode()`:

```mermaid
flowchart TD
    A["¿Quiero usar Producto en un HashSet?"] --> B{¿He sobrescrito equals y hashCode?}
    B -- Sí --> C["HashSet funciona correctamente:<br/>contains, add, remove..."]
    B -- No --> D["Comportamiento impredecible:<br/>duplicados falsos, contains() falla"]
    D --> E["Solución: sobrescribir ambos<br/>usando Objects.hash() y Objects.equals()"]
    E --> C
```

**Contrato obligatorio:**
- Si `a.equals(b)` → `a.hashCode() == b.hashCode()` (**siempre**)
- Si `a.hashCode() != b.hashCode()` → `!a.equals(b)` (**siempre**)
- Si `a.hashCode() == b.hashCode()` → `a.equals(b)` puede ser true o false (colisión)

---

## 6. Conversiones entre List y Set

```java
// List → Set  (eliminar duplicados)
ArrayList<String> lista = new ArrayList<>(List.of("A", "B", "A", "C", "B"));
HashSet<String> set = new HashSet<>(lista);   // → {"A", "B", "C"}

// Set → List  (volver a tener acceso por índice)
ArrayList<String> sinDuplicados = new ArrayList<>(set);
```

> Al convertir List → Set se pierde el orden (con HashSet) y los duplicados. Para conservar el orden de primera aparición, usa `LinkedHashSet`.

---

## 7. Patrones útiles

### Eliminar duplicados preservando orden
```java
ArrayList<String> original = new ArrayList<>(List.of("C", "A", "B", "A", "C"));
LinkedHashSet<String> sinDups = new LinkedHashSet<>(original);
ArrayList<String> resultado = new ArrayList<>(sinDups);
// resultado = ["C", "A", "B"]
```

### Verificar unicidad completa
```java
boolean todosUnicos = lista.size() == new HashSet<>(lista).size();
```

### Encontrar duplicados
```java
HashSet<String> vistos = new HashSet<>();
ArrayList<String> duplicados = new ArrayList<>();
for (String s : lista) {
    if (!vistos.add(s)) {
        duplicados.add(s);
    }
}
```

---

## Puntos clave para los ejercicios

- `HashSet` no permite duplicados; `add()` retorna `false` si el elemento ya estaba.
- Las operaciones de conjunto (`addAll`, `retainAll`, `removeAll`) **modifican** el set receptor.
- `equals()` + `hashCode()` son **obligatorios** para usar objetos propios en un HashSet.
- Para unicidad con orden de inserción → `LinkedHashSet`.
- Para unicidad con orden natural → `TreeSet`.
