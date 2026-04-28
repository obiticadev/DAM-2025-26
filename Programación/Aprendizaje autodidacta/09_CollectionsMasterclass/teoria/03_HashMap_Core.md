# 03 — HashMap: Core y Operaciones Avanzadas

> **Referencia de ejercicios**: Ejercicio10 · Ejercicio11 · Ejercicio12 · Ejercicio13 · Ejercicio14 · Ejercicio15 · Ejercicio16

---

## 1. Qué es HashMap y cómo funciona internamente

`HashMap<K, V>` almacena pares clave-valor. La clave es **única** y se usa para calcular
un índice de bucket mediante su `hashCode()`. Con ese índice se localiza el par en O(1) promedio.

### Jerarquía de interfaces

```mermaid
classDiagram
    direction TB
    class Map~K_V~ {
        <<interface>>
        +put(K key, V value) V
        +get(Object key) V
        +remove(Object key) V
        +containsKey(Object key) boolean
        +containsValue(Object value) boolean
        +size() int
        +keySet() Set~K~
        +values() Collection~V~
        +entrySet() Set~Entry~K_V~~
        +getOrDefault(K key, V def) V
        +putIfAbsent(K key, V value) V
        +replace(K key, V value) V
        +replaceAll(BiFunction fn) void
        +compute(K key, BiFunction fn) V
        +computeIfAbsent(K key, Function fn) V
        +computeIfPresent(K key, BiFunction fn) V
        +merge(K key, V value, BiFunction fn) V
        +forEach(BiConsumer action) void
    }
    class AbstractMap~K_V~ {
        <<abstract>>
    }
    class HashMap~K_V~ {
        -Node[] table
        -int size
        -float loadFactor
        -int threshold
        +HashMap()
        +HashMap(int initialCapacity)
        +HashMap(int capacity, float loadFactor)
    }
    class Node~K_V~ {
        -int hash
        -K key
        -V value
        -Node~K_V~ next
    }

    Map~K_V~ <|.. AbstractMap~K_V~
    AbstractMap~K_V~ <|-- HashMap~K_V~
    HashMap~K_V~ *-- Node~K_V~
```

---

## 2. Mecanismo interno: hash → bucket → entry

```mermaid
flowchart TD
    A["put('nombre', 'Ana')"] --> B["Calcular hash de 'nombre'\nhash = key.hashCode()"]
    B --> C["Calcular índice\nindex = hash & (n-1)"]
    C --> D{"¿Bucket vacío?"}
    D -- Sí --> E["Crear nuevo Node y almacenar"]
    D -- No --> F{"¿Misma clave (equals)?"}
    F -- Sí --> G["Actualizar valor existente"]
    F -- No --> H{"¿Es LinkedList\no TreeNode?"}
    H -- LinkedList --> I["Agregar al final de la cadena"]
    H -- TreeNode --> J["Insertar en árbol rojo-negro\n(cuando cadena >= 8)"]

    style E fill:#d4edda
    style G fill:#cce5ff
    style I fill:#fff3cd
    style J fill:#f8d7da
```

**Parámetros clave:**

| Parámetro | Valor por defecto | Significado |
|---|---|---|
| `initialCapacity` | 16 | Número inicial de buckets |
| `loadFactor` | 0.75 | Umbral de rehashing (size > capacity × 0.75) |
| `threshold` | capacity × loadFactor | Cuando se supera, se duplican los buckets |

---

## 3. Ciclo de vida de put() y get()

```mermaid
sequenceDiagram
    participant C as Código
    participant HM as HashMap
    participant N as Nodo/Bucket

    Note over C,N: --- put(key, value) ---
    C->>HM: put("ciudad", "Madrid")
    HM->>HM: hash = "ciudad".hashCode()
    HM->>HM: index = hash & (capacity-1)
    HM->>N: ¿existe nodo en bucket[index]?
    N-->>HM: No
    HM->>N: Crea Node{key="ciudad", value="Madrid"}
    HM->>HM: size++
    HM-->>C: null (valor anterior)

    Note over C,N: --- get(key) ---
    C->>HM: get("ciudad")
    HM->>HM: hash = "ciudad".hashCode()
    HM->>HM: index = hash & (capacity-1)
    HM->>N: Busca en bucket[index] con equals()
    N-->>HM: Node encontrado
    HM-->>C: "Madrid"
```

---

## 4. Operaciones CRUD fundamentales

| Método | Retorna | Notas |
|---|---|---|
| `put(k, v)` | `V` anterior (o null) | Sobreescribe si la clave ya existe |
| `get(k)` | `V` o null | null si no existe |
| `remove(k)` | `V` eliminado (o null) | No lanza excepción si no existe |
| `containsKey(k)` | `boolean` | O(1) |
| `containsValue(v)` | `boolean` | O(n) — recorre todos los buckets |
| `size()` | `int` | Número de pares |
| `isEmpty()` | `boolean` | size() == 0 |
| `clear()` | `void` | Vacía el mapa |

---

## 5. Iteración sobre HashMap

```mermaid
flowchart LR
    HM["HashMap\n{A=1, B=2, C=3}"] --> KS["keySet()\nSet de claves"]
    HM --> VS["values()\nCollection de valores"]
    HM --> ES["entrySet()\nSet de Map.Entry\n(clave + valor juntos)"]

    KS --> FK["for (K key : map.keySet())"]
    VS --> FV["for (V val : map.values())"]
    ES --> FE["for (Map.Entry<K,V> e : map.entrySet())\ne.getKey() + e.getValue()"]
    HM --> FF["forEach((k, v) -> ...)"]
```

> **Preferir `entrySet()`** cuando necesitas clave y valor a la vez: una sola iteración
> frente a dos iteraciones si usas `keySet()` + `get(key)`.

---

## 6. Métodos condicionales — la nueva API de Map

### getOrDefault, putIfAbsent, replace

```
getOrDefault(clave, "N/A")     → valor si existe, "N/A" si no
putIfAbsent(clave, valor)      → inserta SOLO si la clave no existe; retorna null si inserción, valor anterior si ya existía
replace(clave, nuevoValor)     → actualiza SOLO si la clave existe; retorna valor anterior o null
replaceAll((k, v) -> v * 2)   → actualiza TODOS los valores con la función dada
```

### compute y merge

```mermaid
flowchart TD
    subgraph compute["compute(key, (k,v) -> ...)"]
        C1["v es null si la clave no existe"]
        C1 --> C2{"¿Función retorna null?"}
        C2 -- Sí --> C3["Elimina la entrada"]
        C2 -- No --> C4["Inserta o actualiza la entrada"]
    end

    subgraph computeIfAbsent["computeIfAbsent(key, k -> ...)"]
        A1{"¿Clave existe?"}
        A1 -- No --> A2["Calcula valor con la función\ny lo inserta"]
        A1 -- Sí --> A3["No hace nada"]
    end

    subgraph merge["merge(key, value, (oldV, newV) -> ...)"]
        M1{"¿Clave existe?"}
        M1 -- No --> M2["Inserta `value` directamente"]
        M1 -- Sí --> M3["Aplica función (oldValue, value)\ny actualiza"]
    end
```

**Ejemplo de conteo con merge:**
```
// Contar frecuencias de palabras
mapa.merge(palabra, 1, Integer::sum);
// Si la clave no existe → inserta 1
// Si existe → suma el valor actual + 1
```

---

## 7. HashMap con objetos propios como clave

Si usas tu propia clase como **clave**, debes sobrescribir `equals()` **y** `hashCode()`.

```mermaid
sequenceDiagram
    participant C as Código
    participant HM as HashMap
    participant OBJ as Tu Clase (clave)

    Note over HM: Para get(obj) y containsKey(obj)
    C->>HM: get(miObjeto)
    HM->>OBJ: miObjeto.hashCode()
    OBJ-->>HM: hash → bucket index
    HM->>OBJ: candidato.equals(miObjeto)
    OBJ-->>HM: true/false
    HM-->>C: valor o null

    Note over OBJ: ❌ Sin hashCode(): todos los objetos\nvan al mismo bucket → O(n)
    Note over OBJ: ❌ Sin equals(): dos objetos "iguales"\ngeneran entradas duplicadas
```

---

## 8. Agrupación manual con HashMap (equivalente a groupingBy)

```
// Agrupar lista de objetos por categoría
HashMap<String, ArrayList<Producto>> grupos = new HashMap<>();
for (Producto p : productos) {
    grupos.computeIfAbsent(p.getCategoria(), k -> new ArrayList<>()).add(p);
}
```

Este patrón es la base de `Collectors.groupingBy()` y cubre el 80% de los casos de uso
de HashMap anidados.

---

## 9. HashMap anidado: Map<K, Map<K, V>>

```mermaid
flowchart TD
    HM["HashMap&lt;Departamento, HashMap&lt;NombreEmpleado, Salario&gt;&gt;"]
    HM --> D1["Ingeniería"]
    HM --> D2["Marketing"]
    D1 --> E1["Ana → 45000"]
    D1 --> E2["Luis → 52000"]
    D2 --> E3["Marta → 38000"]
```

```
// Acceso:
double salario = mapa.get("Ingeniería").get("Ana");

// Inserción segura:
mapa.computeIfAbsent("RRHH", k -> new HashMap<>()).put("Pedro", 42000.0);
```

---

## Puntos clave para los ejercicios

- `HashMap` NO garantiza orden de iteración (usa `LinkedHashMap` si lo necesitas).
- `get()` retorna `null` tanto si la clave no existe como si el valor guardado es `null` — usa `containsKey()` para distinguir.
- Para contar/acumular: `merge(key, 1, Integer::sum)` es la forma más limpia.
- Para inicializar colecciones anidadas: `computeIfAbsent(key, k -> new ArrayList<>())`.
- Si el objeto es la clave: `equals()` + `hashCode()` son obligatorios.
