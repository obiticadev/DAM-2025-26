# 📘 Nivel 03 — Ordenación sobre Arrays

---

## 1. ¿Por qué ordenar?

Ordenar un array es una de las operaciones más fundamentales en programación. Un array ordenado permite:
- **Búsqueda binaria** O(log n) en vez de lineal O(n).
- **Eliminación de duplicados** en un solo recorrido.
- **Merge de arrays** de forma eficiente.
- **Presentación de datos** al usuario de forma legible.

En este nivel implementaremos **cinco algoritmos de ordenación desde cero**, sin usar `Arrays.sort()`.

---

## 2. Conceptos Comunes a Todos los Algoritmos

### 2.1 Operación de Swap

La operación más elemental: intercambiar dos elementos usando una variable temporal.

```mermaid
flowchart LR
    antes["ANTES: arr[i]=30, arr[j]=10"]
    proceso["temp=arr[i] → arr[i]=arr[j] → arr[j]=temp"]
    despues["DESPUÉS: arr[i]=10, arr[j]=30"]

    antes --> proceso --> despues
```

### 2.2 In-place vs Con espacio extra

| Categoría | Algoritmos | Descripción |
|---|---|---|
| **In-place** (O(1) espacio) | BubbleSort, SelectionSort, InsertionSort, QuickSort | Ordenan modificando el propio array |
| **Con espacio extra** (O(n)) | MergeSort | Necesita un array auxiliar del mismo tamaño |

### 2.3 Estabilidad

Un algoritmo es **estable** si mantiene el orden relativo de elementos con el mismo valor.

```mermaid
flowchart TD
    entrada["Entrada: 3a, 1, 3b, 2"]
    estable["Estable → 1, 2, 3a, 3b (orden relativo de 3a y 3b preservado)"]
    noestable["No estable → 1, 2, 3b, 3a (orden puede cambiar)"]

    entrada --> estable
    entrada --> noestable
```

### 2.4 Tabla comparativa de complejidad

| Algoritmo | Estable | In-place | Mejor caso | Caso medio | Peor caso | Espacio |
|---|---|---|---|---|---|---|
| Bubble Sort | ✅ Sí | ✅ Sí | O(n) | O(n²) | O(n²) | O(1) |
| Selection Sort | ❌ No | ✅ Sí | O(n²) | O(n²) | O(n²) | O(1) |
| Insertion Sort | ✅ Sí | ✅ Sí | O(n) | O(n²) | O(n²) | O(1) |
| QuickSort | ❌ No | ✅ Sí | O(n log n) | O(n log n) | O(n²) | O(log n) |
| MergeSort | ✅ Sí | ❌ No | O(n log n) | O(n log n) | O(n log n) | O(n) |

---

## 3. Bubble Sort — El Burbujeo

El elemento más grande "burbujea" hasta el final en cada pasada.

#### Pasada 1 — El mayor llega al final

```mermaid
flowchart LR
    A1["5, 3, 8, 1, 2"]
    A2["3, 5, 8, 1, 2"]
    A3["3, 5, 8, 1, 2"]
    A4["3, 5, 1, 8, 2"]
    A5["3, 5, 1, 2, 8 ✅"]

    A1 -->|"swap 5,3"| A2 -->|"5<8 ok"| A3 -->|"swap 8,1"| A4 -->|"swap 8,2"| A5
```

#### Pasada 2 — El segundo mayor

```mermaid
flowchart LR
    B1["3, 5, 1, 2, 8"]
    B2["3, 1, 2, 5, 8 ✅"]

    B1 -->|"swaps internos"| B2
```

**Optimización clave**: si en una pasada no se hace NINGÚN swap, el array ya está ordenado → salir con flag `swapped = false`.

---

## 4. Selection Sort — Selección del Mínimo

En cada iteración, busca el **mínimo** del subarray no ordenado y lo coloca en su posición.

```mermaid
flowchart TD
    S1["64, 25, 12, 22, 11 → min=11 → swap pos 0 y 4"]
    S2["11, 25, 12, 22, 64 → min=12 → swap pos 1 y 2"]
    S3["11, 12, 25, 22, 64 → min=22 → swap pos 2 y 3"]
    S4["11, 12, 22, 25, 64 → ya ordenado ✅"]

    S1 --> S2 --> S3 --> S4
```

**Característica**: siempre hace exactamente n-1 swaps (uno por iteración), aunque hace O(n²) comparaciones.

---

## 5. Insertion Sort — Inserción en Lugar

Simula cómo ordenarías una mano de cartas: tomas cada carta y la insertas en su lugar correcto entre las ya ordenadas.

```mermaid
flowchart TD
    I1["Ordenado: [5] | Pendiente: 3 8 1 2 → insertar el 3"]
    I2["Ordenado: [3, 5] | Pendiente: 8 1 2 → insertar el 8"]
    I3["Ordenado: [3, 5, 8] | Pendiente: 1 2 → insertar el 1"]
    I4["Ordenado: [1, 3, 5, 8] | Pendiente: 2 → insertar el 2"]
    I5["Ordenado: [1, 2, 3, 5, 8] → Completado ✅"]

    I1 --> I2 --> I3 --> I4 --> I5
```

**Ventaja**: es el más rápido de los O(n²) para arrays **casi ordenados** (mejor caso O(n)).

---

## 6. QuickSort — Divide y Vencerás con Pivot

### 6.1 Esquema general

```mermaid
flowchart TD
    A["Array completo"]
    B["Elegir PIVOT"]
    C["PARTITION: menores a la izq, mayores a la der"]
    D["Subarray izquierdo"]
    E["Subarray derecho"]
    F["Recursión hasta base case: size <= 1"]

    A --> B --> C
    C --> D --> F
    C --> E --> F
```

### 6.2 Partición Lomuto

```mermaid
flowchart TD
    P1["Input: 3, 7, 8, 5, 2, 1, 9, 5, 4 — pivot = 4 (último)"]
    P2["Recorrer con j: si arr[j] < pivot → swap con posición i, i++"]
    P3["Tras recorrido: 3, 2, 1, 5, 7, 8, 9, 5, 4"]
    P4["Swap pivot con arr[i]: 3, 2, 1, 4, 7, 8, 9, 5, 5"]
    P5["Pivot 4 queda en su posición FINAL (índice 3)"]

    P1 --> P2 --> P3 --> P4 --> P5
```

**Peor caso**: pivot siempre es el mínimo o máximo → O(n²). Se mitiga con "median-of-three" o pivot aleatorio.

---

## 7. MergeSort — Divide, Ordena, Fusiona

### 7.1 Fase de división

```mermaid
flowchart TD
    A["38, 27, 43, 3, 9, 82, 10"]
    B["38, 27, 43"]
    C["3, 9, 82, 10"]
    D["38"]
    E["27, 43"]

    A -->|"mitad izq"| B
    A -->|"mitad der"| C
    B -->|"mitad izq"| D
    B -->|"mitad der"| E
```

### 7.2 Operación Merge (fusión de dos mitades ordenadas)

```mermaid
sequenceDiagram
    participant L as Izquierda
    participant R as Derecha
    participant O as Resultado

    Note over L: [1, 5, 8]
    Note over R: [2, 4, 9]
    Note over O: []

    L->>O: 1 < 2 tomar 1
    R->>O: 2 < 5 tomar 2
    R->>O: 4 < 5 tomar 4
    L->>O: 5 < 9 tomar 5
    L->>O: 8 < 9 tomar 8
    R->>O: tomar 9 (ultimo)

    Note over O: Resultado final [1, 2, 4, 5, 8, 9]
```

---

## 8. Ordenación de Strings

Los mismos algoritmos funcionan con `String[]` si reemplazamos la comparación `<` / `>` con `compareTo()`:
- `a.compareTo(b) < 0` → a va antes que b (lexicográficamente).
- `a.compareTo(b) > 0` → a va después que b.
- `a.compareTo(b) == 0` → son iguales.

Para ignorar mayúsculas: `a.compareToIgnoreCase(b)`.

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto Principal |
|---|---|---|
| 11 | `Ej11_BubbleSort.java` | Burbujeo con optimización de flag |
| 12 | `Ej12_SelectionSort.java` | Selección del mínimo |
| 13 | `Ej13_InsertionSort.java` | Inserción en posición correcta |
| 14 | `Ej14_QuickSort.java` | Partición Lomuto, recursión |
| 15 | `Ej15_MergeSort.java` | Dividir, ordenar, fusionar |
| 16 | `Ej16_OrdenacionStrings.java` | Aplicar algoritmos a String[] |
