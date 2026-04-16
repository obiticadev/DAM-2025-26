# 📚 Módulo 2: Algoritmos Base, Rendimiento y Técnicas de Punteros

> **Ejercicios cubiertos**: 16 – 30  
> **Código fuente**: `src/main/java/modulo2_algoritmos_rendimiento/`

---

## 2.1 Notación Big O — El Idioma del Rendimiento

La **notación Big O** describe el comportamiento asintótico de un algoritmo: cómo escala el tiempo (o espacio) de ejecución cuando el tamaño de la entrada crece hacia el infinito. No mide el tiempo real en segundos, sino la **tasa de crecimiento**.

### Jerarquía de Complejidades (de mejor a peor)

```mermaid
graph LR
    O1["O(1)\nConstante"] --> OLOG["O(log n)\nLogarítmica"]
    OLOG --> ON["O(n)\nLineal"]
    ON --> ONLOG["O(n log n)\nLinealítmica"]
    ONLOG --> ON2["O(n²)\nCuadrática"]
    ON2 --> O2N["O(2ⁿ)\nExponencial"]
    O2N --> ONF["O(n!)\nFactorial"]
    
    style O1 fill:#22c55e,color:#fff
    style OLOG fill:#84cc16,color:#fff
    style ON fill:#eab308,color:#000
    style ONLOG fill:#f97316,color:#fff
    style ON2 fill:#ef4444,color:#fff
    style O2N fill:#991b1b,color:#fff
    style ONF fill:#450a0a,color:#fff
```

### Tabla de Referencia Rápida

| Big O | Nombre | Ejemplo | n=1000 operaciones aprox. |
|-------|--------|---------|---------------------------|
| O(1) | Constante | Acceso array por índice | 1 |
| O(log n) | Logarítmica | Búsqueda binaria | 10 |
| O(n) | Lineal | Búsqueda lineal | 1,000 |
| O(n log n) | Linealítmica | Merge Sort, Quick Sort | 10,000 |
| O(n²) | Cuadrática | Bubble Sort, Selection Sort | 1,000,000 |
| O(2ⁿ) | Exponencial | Subconjuntos, fuerza bruta | 10^301 |

### Reglas de Oro para Calcular Big O

1. **Ignorar constantes**: O(2n) = O(n), O(500) = O(1).
2. **Domina el término mayor**: O(n² + n) = O(n²).
3. **Bucles secuenciales se SUMAN**: O(n) + O(m) = O(n + m).
4. **Bucles anidados se MULTIPLICAN**: O(n) × O(m) = O(n × m).

```mermaid
flowchart TD
    START["Analizar Algoritmo"] --> Q1{"¿Hay bucles?"}
    Q1 -->|No| CONST["O(1) Constante"]
    Q1 -->|Sí| Q2{"¿Bucles anidados?"}
    Q2 -->|No| Q3{"¿El bucle recorre todo n?"}
    Q3 -->|Sí| LINEAR["O(n) Lineal"]
    Q3 -->|Divide a la mitad cada vez| LOG["O(log n) Logarítmica"]
    Q2 -->|Sí| Q4{"¿Cuántos niveles?"}
    Q4 -->|2 niveles| QUAD["O(n²) Cuadrática"]
    Q4 -->|3 niveles| CUBIC["O(n³) Cúbica"]
    
    style START fill:#6366f1,color:#fff
    style CONST fill:#22c55e,color:#fff
    style LINEAR fill:#eab308,color:#000
    style LOG fill:#84cc16,color:#fff
    style QUAD fill:#ef4444,color:#fff
    style CUBIC fill:#991b1b,color:#fff
```

---

## 2.2 Algoritmos de Búsqueda

### Búsqueda Lineal (Linear Search) — O(n)

Recorrer el array elemento por elemento hasta encontrar el objetivo o agotar el array.

```mermaid
flowchart LR
    subgraph ARRAY["Array desordenado"]
        A["8"] --- B["3"] --- C["12"] --- D["5"] --- E["1"]
    end
    
    BUSCAR["Buscar: 5"] --> A
    A -->|"8 != 5"| B
    B -->|"3 != 5"| C
    C -->|"12 != 5"| D
    D -->|"5 == 5 ENCONTRADO"| RESULT["Indice 3"]
    
    style D fill:#22c55e,color:#fff
    style RESULT fill:#22c55e,color:#fff
```

### Búsqueda Binaria (Binary Search) — O(log n)

Requiere un array **ordenado**. En cada paso, descarta la mitad del array comparando con el elemento central.

```mermaid
flowchart TD
    subgraph PASO1["Paso 1: low=0, high=7, mid=3"]
        A1["1, 3, 5, |8|, 12, 15, 20, 25"]
    end
    
    BUSCAR["Buscar: 15"] --> PASO1
    PASO1 -->|"15 > 8 → buscar derecha"| PASO2
    
    subgraph PASO2["Paso 2: low=4, high=7, mid=5"]
        A2["12, |15|, 20, 25"]
    end
    
    PASO2 -->|"15 == 15 ENCONTRADO"| RESULT["Indice 5"]
    
    style RESULT fill:#22c55e,color:#fff
```

---

## 2.3 Algoritmos de Ordenamiento

### Bubble Sort — O(n²)

Compara pares adyacentes e intercambia si están desordenados. "Burbujea" los mayores hacia el final.

```mermaid
sequenceDiagram
    participant A as Array
    
    Note over A: [5, 3, 8, 1, 2]
    A->>A: Pasada 1: comparar pares adyacentes
    Note over A: [3, 5, 1, 2, 8] - 8 burbujeó al final
    A->>A: Pasada 2
    Note over A: [3, 1, 2, 5, 8] - 5 en su lugar
    A->>A: Pasada 3
    Note over A: [1, 2, 3, 5, 8] - Ordenado
```

### Selection Sort — O(n²)

En cada pasada, encuentra el mínimo del subarray no ordenado y lo coloca en su posición final.

```mermaid
flowchart TD
    S0["[|5|, 3, 8, 1, 2] → Buscar min en todo"] --> S1
    S1["[1, |3|, 8, 5, 2] → min=1, swap con pos 0"] --> S2
    S2["[1, 2, |8|, 5, 3] → min=2, swap con pos 1"] --> S3
    S3["[1, 2, 3, |5|, 8] → min=3, swap con pos 2"] --> S4
    S4["[1, 2, 3, 5, 8] → Ordenado"]
    
    style S4 fill:#22c55e,color:#fff
```

### Insertion Sort — O(n²) peor, O(n) mejor

Como ordenar cartas: toma cada elemento y lo inserta en su posición correcta dentro de la parte ya ordenada.

```mermaid
flowchart LR
    subgraph ORDENADO["Parte Ordenada"]
        O1["1"] --- O2["3"] --- O3["5"]
    end
    
    subgraph PENDIENTE["Por Insertar"]
        P1["2"] --- P2["8"]
    end
    
    P1 -->|"2 va entre 1 y 3"| ORDENADO
    
    style ORDENADO fill:#1e40af,color:#dbeafe
    style PENDIENTE fill:#7f1d1d,color:#fecaca
```

### Merge Sort — O(n log n)

Divide el array en mitades recursivamente hasta tener subarrays de 1 elemento, luego los fusiona (merge) en orden.

```mermaid
graph TD
    A["[5, 3, 8, 1, 2, 7, 4, 6]"] --> B["[5, 3, 8, 1]"]
    A --> C["[2, 7, 4, 6]"]
    B --> D["[5, 3]"]
    B --> E["[8, 1]"]
    C --> F["[2, 7]"]
    C --> G["[4, 6]"]
    D --> H["[5]"]
    D --> I["[3]"]
    E --> J["[8]"]
    E --> K["[1]"]
    
    H --> L["[3, 5] merge"]
    I --> L
    J --> M["[1, 8] merge"]
    K --> M
    
    L --> N["[1, 3, 5, 8] merge"]
    M --> N
    
    F --> O["[2, 7] merge"]
    G --> P["[4, 6] merge"]
    O --> Q["[2, 4, 6, 7] merge"]
    P --> Q
    
    N --> R["[1, 2, 3, 4, 5, 6, 7, 8] FINAL"]
    Q --> R
    
    style R fill:#22c55e,color:#fff
```

### Quick Sort — O(n log n) promedio, O(n²) peor

Elige un **pivote**, particiona el array en menores y mayores que el pivote, y repite recursivamente.

```mermaid
flowchart TD
    A["[5, 3, 8, 1, 2, 7, 4, 6] pivot=5"] --> PART
    
    subgraph PART["Partición"]
        LEFT["Menores: [3, 1, 2, 4]"]
        PIVOT["Pivote: [5]"]
        RIGHT["Mayores: [8, 7, 6]"]
    end
    
    LEFT -->|"Recursión"| LSORT["[1, 2, 3, 4]"]
    RIGHT -->|"Recursión"| RSORT["[6, 7, 8]"]
    
    LSORT --> FINAL["[1, 2, 3, 4, 5, 6, 7, 8]"]
    PIVOT --> FINAL
    RSORT --> FINAL
    
    style FINAL fill:#22c55e,color:#fff
    style PIVOT fill:#eab308,color:#000
```

---

## 2.4 Comparativa de Algoritmos de Ordenamiento

| Algoritmo | Mejor | Promedio | Peor | Espacio | Estable |
|-----------|-------|----------|------|---------|---------|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ Sí |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ No |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | ✅ Sí |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ Sí |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ No |
| Counting Sort | O(n + k) | O(n + k) | O(n + k) | O(k) | ✅ Sí |

> **Estable** = Preserva el orden relativo de elementos iguales.

---

## 2.5 Técnica: Two Pointers (Dos Punteros)

Usa dos punteros que se mueven estratégicamente por el array (generalmente uno desde el inicio y otro desde el final) para resolver problemas en O(n) que normalmente requerirían O(n²).

```mermaid
graph LR
    subgraph ARR["Array Ordenado: [1, 3, 5, 7, 9, 11]"]
        E0["1"] --- E1["3"] --- E2["5"] --- E3["7"] --- E4["9"] --- E5["11"]
    end
    
    LEFT["LEFT ptr = 0"] --> E0
    RIGHT["RIGHT ptr = 5"] --> E5
    
    NOTE["Buscar par que sume 12:\n1+11=12 ENCONTRADO"]
    
    style LEFT fill:#22c55e,color:#fff
    style RIGHT fill:#ef4444,color:#fff
    style NOTE fill:#6366f1,color:#fff
```

### Estrategia

```mermaid
flowchart TD
    A["left = 0, right = n-1"] --> B{"sum = arr[left] + arr[right]"}
    B -->|"sum == target"| FOUND["PAR ENCONTRADO"]
    B -->|"sum < target"| MOVL["left++ (necesitamos más)"]
    B -->|"sum > target"| MOVR["right-- (necesitamos menos)"]
    MOVL --> B
    MOVR --> B
    
    style FOUND fill:#22c55e,color:#fff
```

---

## 2.6 Técnica: Sliding Window (Ventana Deslizante)

Mantiene una "ventana" de tamaño fijo o variable que se desliza por el array, actualizando el resultado de forma incremental sin recalcular todo desde cero.

### Ventana de Tamaño Fijo

```mermaid
graph LR
    subgraph ARR["Array: [2, 1, 5, 1, 3, 2]"]
        E0["2"] --- E1["1"] --- E2["5"] --- E3["1"] --- E4["3"] --- E5["2"]
    end
    
    W1["Ventana 1: [2,1,5] sum=8"]
    W2["Ventana 2: [1,5,1] sum=7"]
    W3["Ventana 3: [5,1,3] sum=9"]
    W4["Ventana 4: [1,3,2] sum=6"]
    
    W1 --> W2
    W2 --> W3
    W3 --> W4
    
    style W3 fill:#22c55e,color:#fff
```

> **Clave**: Al deslizar, en vez de sumar k elementos de nuevo: `newSum = oldSum - salio + entro`. Esto convierte O(n×k) en O(n).

### Ventana de Tamaño Variable

```mermaid
flowchart TD
    A["Inicio: left=0, right=0"] --> B["Expandir right hasta cumplir condición"]
    B --> C{"¿Condición cumplida?"}
    C -->|Sí| D["Registrar resultado, contraer left"]
    C -->|No| E["Seguir expandiendo right"]
    D --> F{"¿Se puede mejorar?"}
    F -->|Sí| D
    F -->|No| E
    
    style D fill:#22c55e,color:#fff
```

---

## 2.7 Mapa de Ejercicios del Módulo 2

| Ejercicio | Concepto Principal | Dificultad |
|-----------|-------------------|------------|
| 16 | Búsqueda Lineal | ⭐ |
| 17 | Búsqueda Binaria (iterativa + recursiva) | ⭐⭐ |
| 18 | Bubble Sort | ⭐⭐ |
| 19 | Selection Sort | ⭐⭐ |
| 20 | Insertion Sort | ⭐⭐ |
| 21 | Merge Sort (divide and conquer) | ⭐⭐⭐⭐ |
| 22 | Quick Sort (partición Lomuto/Hoare) | ⭐⭐⭐⭐ |
| 23 | Counting Sort (no comparativo) | ⭐⭐⭐ |
| 24 | Análisis Big O práctico | ⭐⭐ |
| 25 | Two Pointers: Par con suma objetivo | ⭐⭐⭐ |
| 26 | Two Pointers: Palíndromos e inversiones | ⭐⭐ |
| 27 | Sliding Window: Suma máxima (tamaño fijo) | ⭐⭐⭐ |
| 28 | Sliding Window: Subcadena (tamaño variable) | ⭐⭐⭐⭐ |
| 29 | Búsqueda Binaria: Variantes avanzadas | ⭐⭐⭐ |
| 30 | Benchmark Comparativo de Sorting | ⭐⭐⭐ |

---

> **🔗 Código fuente**: Los 15 ejercicios de este módulo se encuentran en  
> `src/main/java/modulo2_algoritmos_rendimiento/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
