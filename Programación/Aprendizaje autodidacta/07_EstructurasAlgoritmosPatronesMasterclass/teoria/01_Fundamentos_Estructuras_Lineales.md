# 📚 Módulo 1: Fundamentos, Memoria y Estructuras Lineales

> **Ejercicios cubiertos**: 01 – 15  
> **Código fuente**: `src/main/java/modulo1_estructuras_lineales/`

---

## 1.1 ¿Qué es una Estructura de Datos?

Una **estructura de datos** es una forma organizada de almacenar y gestionar información en la memoria del ordenador para que pueda ser utilizada de forma eficiente. La elección de la estructura correcta puede marcar la diferencia entre un programa que responde en milisegundos y uno que tarda minutos.

### Clasificación General

```mermaid
graph TD
    A["Estructuras de Datos"] --> B["Lineales"]
    A --> C["No Lineales"]
    B --> D["Arrays"]
    B --> E["Linked Lists"]
    B --> F["Stacks"]
    B --> G["Queues"]
    C --> H["Árboles"]
    C --> I["Grafos"]
    C --> J["Hash Tables"]
    
    style A fill:#6366f1,color:#fff,stroke:#4f46e5
    style B fill:#8b5cf6,color:#fff,stroke:#7c3aed
    style C fill:#8b5cf6,color:#fff,stroke:#7c3aed
    style D fill:#a78bfa,color:#fff
    style E fill:#a78bfa,color:#fff
    style F fill:#a78bfa,color:#fff
    style G fill:#a78bfa,color:#fff
    style H fill:#a78bfa,color:#fff
    style I fill:#a78bfa,color:#fff
    style J fill:#a78bfa,color:#fff
```

---

## 1.2 La Memoria: Stack vs Heap

En Java, la memoria se divide en dos regiones fundamentales:

| Región | Almacena | Vida útil | Velocidad |
|--------|----------|-----------|-----------|
| **Stack** | Variables primitivas, referencias a objetos, frames de métodos | Se destruye al salir del scope | ⚡ Extrema |
| **Heap** | Objetos (instancias creadas con `new`) | Hasta que el Garbage Collector los reclama | 🐢 Más lenta |

```mermaid
graph LR
    subgraph STACK["📦 Stack Memory"]
        S1["int x = 5"]
        S2["String ref →"]
        S3["int arr ref →"]
    end
    
    subgraph HEAP["🗄️ Heap Memory"]
        H1["String: Hola"]
        H2["int array: 1,2,3"]
    end
    
    S2 --> H1
    S3 --> H2
    
    style STACK fill:#1e293b,color:#e2e8f0,stroke:#334155
    style HEAP fill:#0f172a,color:#e2e8f0,stroke:#1e293b
```

### Regla de Oro
- Las **primitivas** (`int`, `double`, `boolean`...) viven en el **Stack**.
- Los **objetos** (incluidos los arrays) viven en el **Heap**; en el Stack solo se guarda la **referencia** (puntero) hacia ellos.

---

## 1.3 Arrays: La Base de Todo

Un array es un bloque **contiguo** de memoria que almacena elementos del mismo tipo. Su tamaño es **fijo** una vez creado.

### Anatomía de un Array en Memoria

```mermaid
graph LR
    subgraph ARRAY["int datos de 5 posiciones"]
        I0["pos0 = 10"]
        I1["pos1 = 20"]
        I2["pos2 = 30"]
        I3["pos3 = 40"]
        I4["pos4 = 50"]
    end
    
    I0 --- I1 --- I2 --- I3 --- I4
    
    style ARRAY fill:#1e40af,color:#dbeafe,stroke:#1d4ed8
```

### Complejidad de Operaciones en Arrays

| Operación | Complejidad | ¿Por qué? |
|-----------|-------------|------------|
| Acceso por índice | **O(1)** | Cálculo directo: `base + index * tamaño_tipo` |
| Búsqueda lineal | **O(n)** | Hay que recorrer hasta encontrar |
| Inserción al final (si hay espacio) | **O(1)** | Solo escritura |
| Inserción en posición intermedia | **O(n)** | Hay que desplazar elementos |
| Eliminación en posición intermedia | **O(n)** | Hay que compactar el hueco |

### Array Dinámico: Estrategia de Redimensionamiento

Cuando queremos un array que **crezca** automáticamente, necesitamos implementar un **array dinámico**. La estrategia clásica es duplicar la capacidad cuando se llena:

```mermaid
flowchart TD
    A["add elemento"] --> B{"size == capacity?"}
    B -->|No| C["datos en size = elemento, size++"]
    B -->|Sí| D["Crear nuevo array con capacity x 2"]
    D --> E["Copiar todos los elementos al nuevo array"]
    E --> F["datos = nuevoArray"]
    F --> C
    
    style A fill:#059669,color:#fff,stroke:#047857
    style B fill:#d97706,color:#fff,stroke:#b45309
    style D fill:#dc2626,color:#fff,stroke:#b91c1c
```

> **Amortized Cost**: Aunque el redimensionamiento es O(n), ocurre con tan poca frecuencia que el coste promedio de `add()` es **O(1) amortizado**.

---

## 1.4 Linked Lists (Listas Enlazadas)

Una lista enlazada es una colección de **nodos** donde cada nodo contiene un dato y una referencia (puntero) al siguiente nodo. No necesita memoria contigua.

### Lista Enlazada Simple (Singly Linked List)

```mermaid
graph LR
    HEAD["HEAD"] --> N1["Nodo 1 - data: 10"]
    N1 --> N2["Nodo 2 - data: 20"]
    N2 --> N3["Nodo 3 - data: 30"]
    N3 --> NULL["null"]
    
    style HEAD fill:#7c3aed,color:#fff,stroke:#6d28d9
    style N1 fill:#4f46e5,color:#fff,stroke:#4338ca
    style N2 fill:#4f46e5,color:#fff,stroke:#4338ca
    style N3 fill:#4f46e5,color:#fff,stroke:#4338ca
    style NULL fill:#6b7280,color:#fff,stroke:#4b5563
```

### Lista Doblemente Enlazada (Doubly Linked List)

```mermaid
graph LR
    HEAD["HEAD"] --> N1
    N1["Nodo 1 - data: 10"] <--> N2["Nodo 2 - data: 20"]
    N2 <--> N3["Nodo 3 - data: 30"]
    N3 --> NULLR["null"]
    NULLL["null"] --> N1
    TAIL["TAIL"] --> N3
    
    style HEAD fill:#7c3aed,color:#fff
    style TAIL fill:#7c3aed,color:#fff
    style N1 fill:#2563eb,color:#fff,stroke:#1d4ed8
    style N2 fill:#2563eb,color:#fff,stroke:#1d4ed8
    style N3 fill:#2563eb,color:#fff,stroke:#1d4ed8
```

### Comparativa Array vs LinkedList

| Operación | Array | LinkedList |
|-----------|-------|------------|
| Acceso por índice | **O(1)** ✅ | **O(n)** ❌ |
| Insertar al inicio | **O(n)** ❌ | **O(1)** ✅ |
| Insertar al final | **O(1)** amortizado | **O(1)** con tail ✅ |
| Insertar en medio | **O(n)** | **O(1)** si tienes el nodo ✅ |
| Búsqueda | **O(n)** | **O(n)** |
| Memoria extra | Ninguna | Puntero/s por nodo |

### Detección de Ciclos: Algoritmo de Floyd (Tortuga y Liebre)

```mermaid
graph LR
    N1["Nodo 1"] --> N2["Nodo 2"]
    N2 --> N3["Nodo 3"]
    N3 --> N4["Nodo 4"]
    N4 --> N5["Nodo 5"]
    N5 --> N3
    
    style N3 fill:#dc2626,color:#fff
    style N4 fill:#dc2626,color:#fff
    style N5 fill:#dc2626,color:#fff
```

> Si hay un ciclo, la liebre (que avanza de 2 en 2) eventualmente **alcanzará** a la tortuga (que avanza de 1 en 1). Si la liebre llega a `null`, no hay ciclo.

---

## 1.5 Stacks (Pilas) — LIFO

Un Stack es una estructura **LIFO** (Last In, First Out): el último elemento que entra es el primero que sale. Piénsalo como una pila de platos.

### Operaciones Fundamentales

```mermaid
graph TB
    subgraph STACK["Stack - Pila"]
        direction TB
        E3["Elemento C - ultimo en entrar - TOP"]
        E2["Elemento B"]
        E1["Elemento A - primero en entrar"]
    end
    
    PUSH["push D"] -->|"Apila encima"| E3
    POP["pop"] -->|"Retira el TOP"| E3
    PEEK["peek"] -->|"Lee sin retirar"| E3
    
    style STACK fill:#1e293b,color:#e2e8f0,stroke:#334155
    style E3 fill:#ef4444,color:#fff
    style PUSH fill:#22c55e,color:#fff
    style POP fill:#f97316,color:#fff
    style PEEK fill:#3b82f6,color:#fff
```

| Operación | Descripción | Complejidad |
|-----------|-------------|-------------|
| `push(e)` | Insertar en la cima | **O(1)** |
| `pop()` | Retirar de la cima | **O(1)** |
| `peek()` | Ver la cima sin retirar | **O(1)** |
| `isEmpty()` | ¿Está vacía? | **O(1)** |
| `size()` | Número de elementos | **O(1)** |

### Caso de Uso Clásico: Validador de Paréntesis

```mermaid
sequenceDiagram
    participant Input as Entrada
    participant Stack as Stack
    participant Result as Resultado
    
    Input->>Stack: Caracter abre parentesis - push
    Input->>Stack: Caracter abre llave - push
    Input->>Stack: Caracter abre corchete - push
    Input->>Stack: Caracter cierra corchete - pop y match OK
    Input->>Stack: Caracter cierra llave - pop y match OK
    Input->>Stack: Caracter cierra parentesis - pop y match OK
    Stack->>Result: Stack vacio entonces VALIDO
```

---

## 1.6 Queues (Colas) — FIFO

Una Queue es una estructura **FIFO** (First In, First Out): el primer elemento que entra es el primero que sale. Piénsalo como la cola de un supermercado.

### Operaciones Fundamentales

```mermaid
graph LR
    ENQ["enqueue X"] -->|"Entra por detrás"| REAR
    
    subgraph QUEUE["Queue - Cola"]
        direction LR
        FRONT["FRONT"] --- E1["A"]
        E1 --- E2["B"]
        E2 --- E3["C"]
        E3 --- REAR["REAR"]
    end
    
    FRONT -->|"Sale por delante"| DEQ["dequeue"]
    
    style QUEUE fill:#1e293b,color:#e2e8f0
    style FRONT fill:#22c55e,color:#fff
    style REAR fill:#ef4444,color:#fff
    style ENQ fill:#3b82f6,color:#fff
    style DEQ fill:#f97316,color:#fff
```

| Operación | Descripción | Complejidad |
|-----------|-------------|-------------|
| `enqueue(e)` | Insertar al final | **O(1)** |
| `dequeue()` | Retirar del frente | **O(1)** |
| `peek()` | Ver el frente sin retirar | **O(1)** |
| `isEmpty()` | ¿Está vacía? | **O(1)** |

### Queue Circular (Circular Buffer)

La Queue Circular resuelve el problema de **desperdiciar memoria** cuando se hace `dequeue()` en un array. En lugar de desplazar elementos, usamos aritmética modular:

```mermaid
flowchart LR
    subgraph CIRCULAR["Queue Circular - capacity 5"]
        P0["pos0 = A"]
        P1["pos1 = B"]
        P2["pos2 = vacio"]
        P3["pos3 = vacio"]
        P4["pos4 = E"]
    end
    
    FRONT_PTR["front = 0"] --> P0
    REAR_PTR["rear = 2"] --> P2
    
    style CIRCULAR fill:#0f172a,color:#e2e8f0
    style FRONT_PTR fill:#22c55e,color:#fff
    style REAR_PTR fill:#ef4444,color:#fff
```

> **Fórmula de avance**: `(índice + 1) % capacity` — Esto hace que al llegar al final del array, vuelva al principio automáticamente.

---

## 1.7 Deque (Double-Ended Queue)

Un **Deque** permite insertar y retirar elementos por **ambos extremos** (frente y final). Es la fusión de un Stack y una Queue.

```mermaid
graph LR
    ADDF["addFirst"] --> FRONT
    
    subgraph DEQUE["Deque - Doble Cola"]
        direction LR
        FRONT["FRONT"] --- E1["A"]
        E1 --- E2["B"]
        E2 --- E3["C"]
        E3 --- REAR["REAR"]
    end
    
    REAR --> ADDL["addLast"]
    FRONT --> REMF["removeFirst"]
    REAR --> REML["removeLast"]
    
    style DEQUE fill:#1e293b,color:#e2e8f0
    style FRONT fill:#22c55e,color:#fff
    style REAR fill:#ef4444,color:#fff
```

Todas las operaciones del Deque son **O(1)** cuando se implementa correctamente con un array circular o una lista doblemente enlazada.

---

## 1.8 Mapa de Ejercicios del Módulo 1

| Ejercicio | Concepto Principal | Dificultad |
|-----------|-------------------|------------|
| 01 | Array Dinámico Manual (resize) | ⭐⭐ |
| 02 | Insertar en posición con desplazamiento | ⭐⭐ |
| 03 | Eliminar y compactar array | ⭐⭐ |
| 04 | Array Circular (Buffer) | ⭐⭐⭐ |
| 05 | Nodo simple y encadenamiento | ⭐ |
| 06 | LinkedList Simple desde cero | ⭐⭐⭐ |
| 07 | Doubly LinkedList completa | ⭐⭐⭐ |
| 08 | Detección de ciclos (Floyd) | ⭐⭐⭐⭐ |
| 09 | Invertir LinkedList (in-place) | ⭐⭐⭐ |
| 10 | Stack Manual con Array | ⭐⭐ |
| 11 | Stack con LinkedList | ⭐⭐ |
| 12 | Validador de Paréntesis | ⭐⭐⭐ |
| 13 | Queue Manual con Array | ⭐⭐ |
| 14 | Queue Circular | ⭐⭐⭐ |
| 15 | Deque Manual | ⭐⭐⭐⭐ |

---

> **🔗 Código fuente**: Los 15 ejercicios de este módulo se encuentran en  
> `src/main/java/modulo1_estructuras_lineales/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
