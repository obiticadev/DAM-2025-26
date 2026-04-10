# 📘 Nivel 05 — Matrices Bidimensionales en Java

---

## 1. ¿Qué es una Matriz en Java?

A diferencia de otros lenguajes donde una matriz es un bloque de memoria rectangular rígido, en **Java** una matriz es un **array de arrays**.

Esto significa que `int[][] matriz` es un array donde cada posición guarda una **referencia** a otro array de tipo `int[]`.

### Estructura en Memoria (Heap)

```mermaid
graph TD
    M["matriz (referencia)"] --> R["Array Principal (Filas)"]
    R --> F0["Fila [0] (int[])"]
    R --> F1["Fila [1] (int[])"]
    R --> F2["Fila [2] (int[])"]
    
    F0 --> D00["[0][0]"]
    F0 --> D01["[0][1]"]
    F1 --> D10["[1][0]"]
    F1 --> D11["[1][1]"]
    F2 --> D20["[2][0]"]
    F2 --> D21["[2][1]"]
```

---

## 2. Declaración e Inicialización

Existen varias formas de declarar una matriz según el conocimiento previo de los datos:

### 2.1 — Conociendo dimensiones (Vacía)
```java
int[][] grid = new int[3][4]; // 3 filas, 4 columnas. Todas a 0.
```

### 2.2 — Conociendo los datos (Literal)
```java
int[][] datos = {
    {1, 2, 3},  // Fila 0
    {4, 5, 6},  // Fila 1
    {7, 8, 9}   // Fila 2
};
```

> ⚠️ **Propiedad .length**: `datos.length` devuelve el número de **filas**. `datos[i].length` devuelve el número de **columnas** de esa fila específica.

---

## 3. Acceso y Recorrido (Row-Major Order)

El acceso se realiza mediante dos corchetes: `matriz[fila][columna]`. Por defecto, en Java recorremos de forma **Row-Major** (Fila a Fila).

### Algoritmo de Recorrido

```mermaid
flowchart TD
    start["Inicio"] --> loopF["Loop Filas (i = 0 a N-1)"]
    loopF --> loopC["Loop Columnas (j = 0 a M-1)"]
    loopC --> proc["Procesar matriz[i][j]"]
    proc --> loopC
    loopC -- "Fin Fila" --> loopF
    loopF -- "Fin Matriz" --> endf["Fin"]
```

---

## 4. Jagged Arrays (Arrays Irregulares)

Como una matriz es un array de arrays, las filas **no tienen por qué tener el mismo tamaño**. Esto se conoce como **Jagged Array**.

### Ejemplo: Triángulo de Ventas
```java
int[][] irregular = new int[3][];
irregular[0] = new int[2]; // Fila 0 tiene 2 cols
irregular[1] = new int[5]; // Fila 1 tiene 5 cols
irregular[2] = new int[1]; // Fila 2 tiene 1 col
```

### Representación Visual Jagged

```mermaid
graph LR
    subgraph Memoria
    R["Principal"] --> F0["[0, 0]"]
    R --> F1["[0, 0, 0, 0, 0]"]
    R --> F2["[0]"]
    end
```

---

## 5. Operaciones de Diagonales

En matrices **cuadradas** (Filas == Columnas), las diagonales son elementos clave.

| Diagonal | Condición de Índice | Ejemplo (3x3) |
| :--- | :--- | :--- |
| **Principal** | `i == j` | `(0,0), (1,1), (2,2)` |
| **Secundaria** | `j == (N - 1 - i)` | `(0,2), (1,1), (2,0)` |

### Visualización Diagonales
```mermaid
flowchart TD
    subgraph Cuadrado
    P1["[0,0]"] --- S1["[0,2]"]
    P2["[1,1]"]
    S3["[2,0]"] --- P3["[2,2]"]
    end
```

---

## 6. Patrones de Recorrido Avanzado

### 6.1 — Recorrido en Serpiente (Zig-Zag)
Consiste en recorrer filas pares de izquierda a derecha y filas impares de derecha a izquierda.

### 6.2 — Recorrido en Espiral (Caracol)
Se procesa el marco exterior y se reduce progresivamente hacia el centro. Requiere 4 índices de control: `top`, `bottom`, `left`, `right`.

```mermaid
stateDiagram-v2
    [*] --> Derecha: filaSuperior
    Derecha --> Abajo: colDerecha
    Abajo --> Izquierda: filaInferior
    Izquierda --> Arriba: colIzquierda
    Arriba --> Derecha: capaSiguiente
```

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto Principal |
|---|---|---|
| 21 | `Ej21_CreacionYRecorrido2D.java` | Iteración anidada básica |
| 22 | `Ej22_SumaFilasColumnasMedias.java` | Reducción de datos por ejes |
| 23 | `Ej23_DiagonalesPrincipalSecundaria.java` | Lógica de índices en matrices cuadradas |
| 24 | `Ej24_MatrizIdentidadYSimetrica.java` | Verificación de propiedades matemáticas |
| 25 | `Ej25_JaggedArrays.java` | Gestión de filas con tamaños variables |
| 26 | `Ej26_MatrizEspiralRecorrido.java` | Algoritmos de recorrido complejos |
