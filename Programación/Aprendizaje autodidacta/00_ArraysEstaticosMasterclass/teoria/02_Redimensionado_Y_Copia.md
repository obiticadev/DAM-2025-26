# 📘 Nivel 02 — Redimensionado y Copia de Arrays

---

## 1. El Problema Fundamental: Tamaño Fijo

En Java, un array tiene **tamaño inmutable** tras su creación. No existe operación nativa para "agrandar" o "encoger" un array. Para simular dinamismo, debemos:

1. Crear un **nuevo array** con la capacidad deseada.
2. **Copiar** los elementos del array original al nuevo.
3. **Reasignar** la referencia para que apunte al nuevo array.

#### Paso 1 — Array original lleno

```mermaid
flowchart LR
    A["[0]=10"] ~~~ B["[1]=20"] ~~~ C["[2]=30"]
```

#### Paso 2 — Crear nuevo array con más capacidad

```mermaid
flowchart LR
    D["[0]=__"] ~~~ E["[1]=__"] ~~~ F["[2]=__"] ~~~ G["[3]=__"] ~~~ H["[4]=__"] ~~~ I["[5]=__"]
```

#### Paso 3 — Copiar elementos y reasignar

```mermaid
flowchart LR
    J["[0]=10"] ~~~ K["[1]=20"] ~~~ L["[2]=30"] ~~~ M["[3]=__"] ~~~ N["[4]=__"] ~~~ O["[5]=__"]
```

> Tras la copia: `datos = nuevoArray;` — la referencia apunta al nuevo bloque.

---

## 2. Copia Manual (Elemento a Elemento)

La forma más básica de copiar un array es iterar posición por posición:

```mermaid
sequenceDiagram
    participant Original as original int[]
    participant Nuevo as copia int[]

    Note over Original: Contiene {10, 20, 30}
    Note over Nuevo: Creado con new int[3] - {0, 0, 0}

    loop i = 0 hasta original.length - 1
        Original->>Nuevo: copia[i] = original[i]
    end

    Note over Nuevo: Ahora contiene {10, 20, 30}
    Note over Original,Nuevo: Son INDEPENDIENTES - modificar uno NO afecta al otro
```

### Copia parcial
Puedes copiar solo un rango:
- Desde el índice `a` hasta el índice `b` (inclusive o exclusive, según convenio).
- El nuevo array tendrá tamaño `b - a` (o `b - a + 1` si incluyes `b`).

---

## 3. System.arraycopy — La Copia Nativa

Java ofrece un método nativo (implementado en C/C++ dentro de la JVM) para copiar bloques de memoria de forma eficiente:

```
System.arraycopy(origen, posOrigen, destino, posDestino, longitud)
```

| Parámetro | Significado |
|---|---|
| `origen` | Array fuente |
| `posOrigen` | Índice de inicio en el array fuente |
| `destino` | Array destino |
| `posDestino` | Índice de inicio en el destino |
| `longitud` | Cantidad de elementos a copiar |

#### Ejemplo visual

```mermaid
flowchart TD
    origen["origen: | A | B | C | D | E |"]
    destino_antes["destino antes: | _ | _ | _ | _ | _ | _ |"]
    llamada["System.arraycopy(origen, 1, destino, 2, 3)"]
    destino_despues["destino después: | _ | _ | B | C | D | _ |"]

    origen --> llamada
    destino_antes --> llamada
    llamada --> destino_despues
```

> `System.arraycopy` es significativamente más rápido que un bucle manual porque opera a nivel de bloques de memoria (memcpy nativo).

---

## 4. Crecimiento Geométrico (Estrategia de Duplicación)

Cuando un array se llena y necesitamos más espacio, ¿cuánto lo agrandamos?

### Estrategia Lineal (mala)
Agregar 1 posición cada vez → Cada inserción requiere copiar TODO el array. Coste acumulado: **O(n²)**.

### Estrategia Geométrica (óptima)
Duplicar la capacidad (×2) cuando se llena → Las copias son infrecuentes. Coste amortizado por inserción: **O(1)**.

#### Evolución del array con crecimiento ×2

```mermaid
flowchart TD
    C1["Cap: 2 — se llenan 2 posiciones"]
    C2["Cap: 4 — grow copió 2 elementos"]
    C3["Cap: 8 — grow copió 4 elementos"]
    C4["Cap: 16 — grow copió 8 elementos"]

    C1 -->|"LLENO → grow()"| C2
    C2 -->|"LLENO → grow()"| C3
    C3 -->|"LLENO → grow()"| C4
```

### ¿Por qué ×2?

> Total de copias tras n inserciones: `1 + 2 + 4 + 8 + ... + n/2 ≈ n`
> Total de inserciones: `n`
> **Coste amortizado por add(): O(n) / n = O(1)**
>
> Es la misma estrategia que usa internamente `java.util.ArrayList`. Nosotros la implementamos desde cero.

---

## 5. Reducción de Capacidad (Shrink)

Si eliminamos muchos elementos, el array puede quedar sobredimensionado (mucha memoria reservada para pocos datos).

**Regla de reducción**: cuando el tamaño lógico cae al **25%** de la capacidad, reducimos a la **mitad**.

```mermaid
stateDiagram-v2
    [*] --> Normal
    Normal --> Evaluacion: remove() reduce size
    Evaluacion --> Shrink: size <= capacity/4 AND capacity > MIN
    Evaluacion --> Normal: size > capacity/4
    Shrink --> Normal: Copiar a array mas pequeno
```

> Nunca reducir por debajo de una capacidad mínima (ej. 4 o 8).

---

## 6. Array Dinámico Completo — Arquitectura

Combinando crecimiento y reducción, podemos construir nuestro propio "ArrayList" sin usar `java.util`:

```mermaid
classDiagram
    class MiArrayList {
        - int[] datos
        - int size
        - int capacity
        - int CAPACIDAD_MINIMA
        + add(int valor) void
        + get(int index) int
        + set(int index, int valor) void
        + remove(int index) int
        + size() int
        + contains(int valor) boolean
        + isEmpty() boolean
        - grow() void
        - shrink() void
    }
```

**Invariantes:**
1. `size <= capacity`
2. `capacity >= CAPACIDAD_MINIMA`
3. `datos.length == capacity`
4. `grow()` cuando `size == capacity`
5. `shrink()` cuando `size <= capacity/4`

### Flujo de add(valor)

```mermaid
flowchart TD
    A["add(valor)"]
    B{"size == capacity?"}
    C["grow: crear array x2, copiar"]
    D["datos[size] = valor"]
    E["size++"]

    A --> B
    B -->|"Sí"| C
    B -->|"No"| D
    C --> D
    D --> E
```

### Flujo de remove(index)

```mermaid
flowchart TD
    A["remove(index)"]
    B["Guardar datos[index]"]
    C["Desplazar elementos a la izquierda"]
    D["size--"]
    E{"size <= capacity/4 AND capacity > MIN?"}
    F["shrink: crear array /2, copiar"]
    G["Fin"]

    A --> B --> C --> D --> E
    E -->|"Sí"| F --> G
    E -->|"No"| G
```

---

## 7. Independencia de Copias

Un error común es creer que al copiar un array, ambos son independientes. Esto es cierto para **tipos primitivos**, pero cuidado con **arrays de objetos**:

### Tipos primitivos — Copia INDEPENDIENTE

```mermaid
flowchart TD
    P1["original: {1, 2, 3}"]
    P2["copia: {1, 2, 3}"]
    P3["Modificar original[0] = 99"]
    P4["original: {99, 2, 3}"]
    P5["copia: {1, 2, 3} ✅ — NO cambia"]

    P1 --> P3 --> P4
    P2 --> P5
```

### Arrays de objetos — Copia SHALLOW

```mermaid
flowchart TD
    O1["original[0]"]
    O2["copia[0]"]
    S["String 'Hola' en Heap"]

    O1 -->|"referencia"| S
    O2 -->|"misma referencia"| S
```

> Para arrays de tipos primitivos (`int[]`, `double[]`, etc.), la copia elemento-a-elemento produce independencia total. Para arrays de objetos, la copia es **shallow** (ambos apuntan a los mismos objetos).

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto Principal |
|---|---|---|
| 06 | `Ej06_CopiaManual.java` | Copia elemento a elemento, independencia |
| 07 | `Ej07_SystemArraycopy.java` | System.arraycopy, offset, concatenación |
| 08 | `Ej08_CrecimientoGeometrico.java` | Auto-grow con duplicación de capacidad |
| 09 | `Ej09_ReduccionDeCapacidad.java` | Auto-shrink al 25% de ocupación |
| 10 | `Ej10_ArrayDinamicoCompleto.java` | MiArrayList completo desde cero |
