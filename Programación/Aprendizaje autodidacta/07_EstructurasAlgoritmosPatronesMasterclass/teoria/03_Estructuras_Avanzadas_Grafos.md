# 📚 Módulo 3: Estructuras Avanzadas y Grafos

> **Ejercicios cubiertos**: 31 – 45  
> **Código fuente**: `src/main/java/modulo3_estructuras_avanzadas/`

---

## 3.1 Árboles Binarios

Un **árbol binario** es una estructura jerárquica donde cada nodo tiene como máximo **dos hijos**: izquierdo y derecho. El nodo superior se llama **raíz** (root).

### Anatomía de un Árbol Binario

```mermaid
graph TD
    R["Raíz: 50"] --> L["Izq: 30"]
    R --> RI["Der: 70"]
    L --> LL["Izq: 20"]
    L --> LR["Der: 40"]
    RI --> RIL["Izq: 60"]
    RI --> RIR["Der: 80"]
    
    style R fill:#6366f1,color:#fff
    style L fill:#8b5cf6,color:#fff
    style RI fill:#8b5cf6,color:#fff
    style LL fill:#a78bfa,color:#fff
    style LR fill:#a78bfa,color:#fff
    style RIL fill:#a78bfa,color:#fff
    style RIR fill:#a78bfa,color:#fff
```

### Terminología Clave

| Término | Definición |
|---------|-----------|
| **Raíz** | Nodo sin padre (el más alto) |
| **Hoja** | Nodo sin hijos |
| **Altura** | Aristas del camino más largo raíz → hoja |
| **Profundidad** | Aristas desde la raíz hasta el nodo |
| **Subárbol** | Árbol formado por un nodo y todos sus descendientes |
| **BST** | Binary Search Tree: izq < padre < der |

### Árbol Binario de Búsqueda (BST)

En un BST, para **todo** nodo N:
- Todos los valores del subárbol **izquierdo** son **menores** que N.
- Todos los valores del subárbol **derecho** son **mayores** que N.

```mermaid
graph TD
    N50["50"] --> N30["30 - menor"]
    N50 --> N70["70 - mayor"]
    N30 --> N20["20"]
    N30 --> N40["40"]
    N70 --> N60["60"]
    N70 --> N80["80"]
    
    style N50 fill:#059669,color:#fff
    style N30 fill:#2563eb,color:#fff
    style N70 fill:#dc2626,color:#fff
    style N20 fill:#2563eb,color:#fff
    style N40 fill:#2563eb,color:#fff
    style N60 fill:#dc2626,color:#fff
    style N80 fill:#dc2626,color:#fff
```

### Recorridos de un Árbol Binario

```mermaid
flowchart LR
    subgraph INORDER["Inorder LNR"]
        I["Izq → Nodo → Der"]
        IR["20, 30, 40, 50, 60, 70, 80"]
    end
    
    subgraph PREORDER["Preorder NLR"]
        P["Nodo → Izq → Der"]
        PR["50, 30, 20, 40, 70, 60, 80"]
    end
    
    subgraph POSTORDER["Postorder LRN"]
        PO["Izq → Der → Nodo"]
        POR["20, 40, 30, 60, 80, 70, 50"]
    end
    
    style INORDER fill:#059669,color:#fff
    style PREORDER fill:#2563eb,color:#fff
    style POSTORDER fill:#d97706,color:#fff
```

> **Dato clave**: El recorrido **Inorder** de un BST produce los elementos en **orden ascendente**.

### Operaciones BST y Complejidad

| Operación | Promedio | Peor caso (degenerado) |
|-----------|----------|------------------------|
| Buscar | O(log n) | O(n) |
| Insertar | O(log n) | O(n) |
| Eliminar | O(log n) | O(n) |
| Recorrido | O(n) | O(n) |

> Un BST **degenerado** es cuando todos los nodos van en una sola dirección (como una linked list). Por eso existen los árboles balanceados (AVL, Red-Black).

---

## 3.2 Hash Maps (Tablas de Dispersión)

Un **HashMap** almacena pares clave-valor y permite acceso en **O(1)** promedio. Internamente usa una **función hash** para convertir la clave en un índice del array de buckets.

### Funcionamiento Interno

```mermaid
flowchart TD
    KEY["Clave: 'Juan'"] --> HASH["hashCode = 2314539"]
    HASH --> INDEX["indice = hash % capacidad = 3"]
    INDEX --> BUCKET["buckets[3]"]
    BUCKET --> ENTRY["Entry: Juan → 25"]
    
    style KEY fill:#6366f1,color:#fff
    style HASH fill:#8b5cf6,color:#fff
    style INDEX fill:#a78bfa,color:#fff
    style BUCKET fill:#059669,color:#fff
    style ENTRY fill:#22c55e,color:#fff
```

### Colisiones: Encadenamiento (Chaining)

Cuando dos claves producen el **mismo índice**, se almacenan en una lista enlazada en ese bucket:

```mermaid
graph TD
    subgraph BUCKETS["Array de Buckets"]
        B0["[0] → null"]
        B1["[1] → null"]
        B2["[2] → Entry A"]
        B3["[3] → Entry B → Entry C"]
        B4["[4] → null"]
    end
    
    NOTE["B y C colisionaron en el indice 3"]
    
    style B3 fill:#dc2626,color:#fff
    style NOTE fill:#f97316,color:#fff
```

### Factor de Carga y Redimensionamiento

```mermaid
flowchart TD
    PUT["put clave valor"] --> CHECK{"loadFactor > 0.75?"}
    CHECK -->|No| INSERT["Insertar en bucket"]
    CHECK -->|Sí| RESIZE["Redimensionar: capacity x 2"]
    RESIZE --> REHASH["Recalcular indices de TODOS los entries"]
    REHASH --> INSERT
    
    style RESIZE fill:#dc2626,color:#fff
    style REHASH fill:#f97316,color:#fff
```

> **Load Factor** = size / capacity. Cuando supera 0.75, se duplica la capacidad y se re-hashean todas las entradas.

---

## 3.3 Heaps (Montículos)

Un **Heap** es un árbol binario completo que cumple la propiedad del heap:
- **Min-Heap**: El padre siempre es **menor o igual** que sus hijos.
- **Max-Heap**: El padre siempre es **mayor o igual** que sus hijos.

### Representación en Array

Un heap se implementa eficientemente sobre un **array** usando aritmética de índices:

```mermaid
graph TD
    subgraph TREE["Representacion en Arbol"]
        N10["10 - idx 0"] --> N15["15 - idx 1"]
        N10 --> N20["20 - idx 2"]
        N15 --> N25["25 - idx 3"]
        N15 --> N30["30 - idx 4"]
    end
    
    subgraph FARRAY["Representacion en Array"]
        A["[10, 15, 20, 25, 30]"]
    end
    
    style N10 fill:#22c55e,color:#fff
    style TREE fill:#1e293b,color:#e2e8f0
```

| Relación | Fórmula |
|----------|---------|
| Padre de i | `(i - 1) / 2` |
| Hijo izquierdo de i | `2 * i + 1` |
| Hijo derecho de i | `2 * i + 2` |

### Operaciones del Heap

```mermaid
flowchart TD
    subgraph INSERT["insert - Sift Up"]
        I1["Insertar al final del array"] --> I2["Comparar con padre"]
        I2 --> I3{"Viola propiedad?"}
        I3 -->|Sí| I4["Swap con padre, subir"]
        I3 -->|No| I5["Listo"]
        I4 --> I2
    end
    
    subgraph EXTRACT["extractMin - Sift Down"]
        E1["Guardar raiz como resultado"] --> E2["Mover ultimo a la raiz"]
        E2 --> E3["Comparar con hijos"]
        E3 --> E4{"Viola propiedad?"}
        E4 -->|Sí| E5["Swap con hijo menor, bajar"]
        E4 -->|No| E6["Retornar resultado"]
        E5 --> E3
    end
    
    style INSERT fill:#1e293b,color:#e2e8f0
    style EXTRACT fill:#1e293b,color:#e2e8f0
```

| Operación | Complejidad |
|-----------|-------------|
| insert | O(log n) |
| extractMin/Max | O(log n) |
| peek | O(1) |
| heapify (construir) | O(n) |

---

## 3.4 Grafos

Un **grafo** es un conjunto de **vértices** (nodos) conectados por **aristas** (edges). Son la estructura de datos más versátil y modelan redes, mapas, dependencias, etc.

### Tipos de Grafos

```mermaid
graph LR
    subgraph DIRIGIDO["Grafo Dirigido"]
        DA["A"] --> DB["B"]
        DA --> DC["C"]
        DB --> DD["D"]
        DC --> DD
    end
    
    subgraph NO_DIRIGIDO["Grafo No Dirigido"]
        NA["A"] --- NB["B"]
        NA --- NC["C"]
        NB --- ND["D"]
        NC --- ND
    end
    
    style DIRIGIDO fill:#1e293b,color:#e2e8f0
    style NO_DIRIGIDO fill:#1e293b,color:#e2e8f0
```

### Representaciones

```mermaid
graph TD
    subgraph LISTA["Lista de Adyacencia"]
        L0["A → [B, C]"]
        L1["B → [D]"]
        L2["C → [D]"]
        L3["D → []"]
    end
    
    subgraph MATRIZ["Matriz de Adyacencia"]
        M["  A B C D\nA 0 1 1 0\nB 0 0 0 1\nC 0 0 0 1\nD 0 0 0 0"]
    end
    
    style LISTA fill:#059669,color:#fff
    style MATRIZ fill:#2563eb,color:#fff
```

| Representación | Espacio | Agregar arista | Verificar arista | Vecinos |
|----------------|---------|----------------|------------------|---------|
| Lista Adyacencia | O(V + E) | O(1) | O(grado) | O(grado) |
| Matriz Adyacencia | O(V²) | O(1) | O(1) | O(V) |

### BFS (Breadth-First Search) — Recorrido en Amplitud

Explora todos los vecinos del nivel actual **antes** de avanzar al siguiente nivel. Usa una **Queue**.

```mermaid
sequenceDiagram
    participant Q as Queue
    participant V as Visitados
    
    Note over Q,V: Inicio desde A
    Q->>Q: enqueue(A)
    V->>V: marcar A visitado
    
    Q->>Q: dequeue() = A
    Note over Q: Vecinos de A: B, C
    Q->>Q: enqueue(B), enqueue(C)
    V->>V: marcar B, C
    
    Q->>Q: dequeue() = B
    Note over Q: Vecinos de B: D
    Q->>Q: enqueue(D)
    V->>V: marcar D
    
    Q->>Q: dequeue() = C
    Note over Q: Vecinos de C: D (ya visitado, skip)
    
    Q->>Q: dequeue() = D
    Note over Q: Sin vecinos no visitados
    
    Note over Q,V: Orden BFS: A, B, C, D
```

### DFS (Depth-First Search) — Recorrido en Profundidad

Explora lo más profundo posible **antes** de retroceder. Usa un **Stack** o recursión.

```mermaid
flowchart TD
    A["A (visitar)"] --> B["B (siguiente hijo)"]
    B --> D["D (profundizar)"]
    D --> BACK1["Backtrack a B"]
    BACK1 --> BACK2["Backtrack a A"]
    BACK2 --> C["C (siguiente hijo)"]
    C --> BACK3["Backtrack a A"]
    BACK3 --> FIN["Fin: A, B, D, C"]
    
    style A fill:#6366f1,color:#fff
    style FIN fill:#22c55e,color:#fff
```

| Algoritmo | Estructura | Encuentra camino mas corto | Complejidad |
|-----------|-----------|---------------------------|-------------|
| BFS | Queue | Si en grafos no ponderados | O(V + E) |
| DFS | Stack/Recursion | No garantiza | O(V + E) |

---

## 3.5 Mapa de Ejercicios del Módulo 3

| Ejercicio | Concepto Principal | Dificultad |
|-----------|-------------------|------------|
| 31 | Nodo de Árbol Binario y recorridos básicos | ⭐⭐ |
| 32 | BST: inserción y búsqueda | ⭐⭐⭐ |
| 33 | BST: recorridos Inorder, Preorder, Postorder | ⭐⭐ |
| 34 | BST: eliminación, altura, balanceo | ⭐⭐⭐⭐ |
| 35 | HashMap Manual desde cero | ⭐⭐⭐⭐ |
| 36 | Colisiones Hash (Chaining) | ⭐⭐⭐ |
| 37 | Redimensionamiento y Load Factor | ⭐⭐⭐ |
| 38 | HashSet Manual | ⭐⭐ |
| 39 | Min-Heap Manual | ⭐⭐⭐ |
| 40 | Max-Heap y Priority Queue | ⭐⭐⭐ |
| 41 | Heap Sort | ⭐⭐⭐ |
| 42 | Grafo con Lista de Adyacencia | ⭐⭐⭐ |
| 43 | BFS (Breadth-First Search) | ⭐⭐⭐ |
| 44 | DFS (Depth-First Search) | ⭐⭐⭐ |
| 45 | Camino más corto (BFS no ponderado) | ⭐⭐⭐⭐ |

---

> **🔗 Código fuente**: `src/main/java/modulo3_estructuras_avanzadas/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
