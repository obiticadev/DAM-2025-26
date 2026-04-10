# 📘 Nivel 04 — Búsqueda en Arrays

---

## 1. Búsqueda Lineal vs Búsqueda Binaria

La búsqueda es la operación más frecuente sobre arrays. Elegir el algoritmo correcto marca la diferencia entre O(n) y O(log n).

| Aspecto | Búsqueda Lineal | Búsqueda Binaria |
|---|---|---|
| **Complejidad** | O(n) | O(log n) |
| **Requisito** | Ninguno (funciona en desordenados) | Array ORDENADO |
| **Estrategia** | Recorrer uno a uno | Dividir el espacio a la mitad |

### Comparación de rendimiento

| n elementos | Lineal (peor caso) | Binaria (peor caso) |
|---|---|---|
| 10 | 10 comparaciones | 4 comparaciones |
| 100 | 100 | 7 |
| 1.000 | 1.000 | 10 |
| 1.000.000 | 1.000.000 | 20 |
| 1.000.000.000 | 1.000.000.000 | 30 |

---

## 2. Búsqueda Lineal — Paso a Paso

```mermaid
flowchart LR
    A["i=0: 10 distinto 30 ❌"]
    B["i=1: 50 distinto 30 ❌"]
    C["i=2: 30 == 30 ✅"]
    R["Encontrado en indice 2"]

    A --> B --> C --> R
```

### Variantes de búsqueda lineal

| Variante | Descripción |
|---|---|
| **Primera ocurrencia** | Devolver el primer índice donde aparece |
| **Última ocurrencia** | Recorrer de derecha a izquierda |
| **Todas las ocurrencias** | Devolver un array con todos los índices |
| **Contar ocurrencias** | Contar cuántas veces aparece |

---

## 3. Búsqueda Binaria — El Poder de Dividir

**Requisito obligatorio**: el array DEBE estar **ordenado**.

### 3.1 Ejemplo paso a paso

Buscar `23` en `{2, 5, 8, 12, 16, 23, 38, 56, 72, 91}`:

```mermaid
flowchart TD
    A["Paso 1: low=0, high=9, mid=4 → arr[4]=16 < 23"]
    B["Paso 2: low=5, high=9, mid=7 → arr[7]=56 > 23"]
    C["Paso 3: low=5, high=6, mid=5 → arr[5]=23 ✅"]
    R["Encontrado en indice 5 — Solo 3 comparaciones para 10 elementos"]

    A -->|"buscar derecha: low=5"| B
    B -->|"buscar izquierda: high=6"| C
    C --> R
```

### 3.2 Fórmula segura del punto medio

| Fórmula | Segura | Problema |
|---|---|---|
| `mid = (low + high) / 2` | ❌ | `low + high` puede causar **overflow** con enteros grandes |
| `mid = low + (high - low) / 2` | ✅ | Aritméticamente segura, nunca desborda |

### 3.3 Versión iterativa

```mermaid
flowchart TD
    I1["low = 0, high = length - 1"]
    I2{"low <= high?"}
    I3["mid = low + (high - low) / 2"]
    I4{"arr[mid] == target?"}
    I5["return mid ✅"]
    I6{"arr[mid] < target?"}
    I7["low = mid + 1"]
    I8["high = mid - 1"]
    I9["return -1 (no encontrado)"]

    I1 --> I2
    I2 -->|"Sí"| I3 --> I4
    I2 -->|"No"| I9
    I4 -->|"Sí"| I5
    I4 -->|"No"| I6
    I6 -->|"Sí"| I7 --> I2
    I6 -->|"No"| I8 --> I2
```

### 3.4 Versión recursiva

```mermaid
flowchart TD
    R1["buscar(arr, target, low, high)"]
    R2{"low > high?"}
    R3["return -1"]
    R4["mid = low + (high - low) / 2"]
    R5{"arr[mid] == target?"}
    R6["return mid ✅"]
    R7{"arr[mid] < target?"}
    R8["buscar(arr, target, mid+1, high)"]
    R9["buscar(arr, target, low, mid-1)"]

    R1 --> R2
    R2 -->|"Sí (caso base)"| R3
    R2 -->|"No"| R4 --> R5
    R5 -->|"Sí"| R6
    R5 -->|"No"| R7
    R7 -->|"Sí"| R8
    R7 -->|"No"| R9
```

---

## 4. Variantes de Búsqueda Binaria

### 4.1 Primera y última ocurrencia

Cuando hay **duplicados**, la búsqueda binaria estándar puede devolver cualquier ocurrencia. Las variantes permiten encontrar la **primera** o la **última**.

Dado `arr = {1, 3, 3, 3, 3, 5, 7}`:

| Operación | Resultado |
|---|---|
| Primera ocurrencia de `3` | Índice **1** |
| Última ocurrencia de `3` | Índice **4** |
| Lower bound de `3` | Índice **1** (primer `>= 3`) |
| Upper bound de `3` | Índice **5** (primer `> 3`) |

### 4.2 Tabla de comportamiento

| Variante | Descripción | Si encontramos target |
|---|---|---|
| **Primera ocurrencia** | Primer índice con ese valor | Guardar resultado y seguir buscando a la **izquierda** |
| **Última ocurrencia** | Último índice con ese valor | Guardar resultado y seguir buscando a la **derecha** |
| **Lower bound** | Primer índice con valor `>= target` | Si `arr[mid] >= target` → `high = mid - 1` |
| **Upper bound** | Primer índice con valor `> target` | Si `arr[mid] <= target` → `low = mid + 1` |

---

## 5. Búsqueda en Matriz 2D Ordenada

Cuando una matriz tiene filas y columnas **ordenadas** (cada fila está ordenada de izquierda a derecha, y cada columna de arriba a abajo), se puede buscar desde la **esquina superior derecha**.

### 5.1 Ejemplo: buscar 14

Matriz de ejemplo:

| | Col 0 | Col 1 | Col 2 | Col 3 |
|---|---|---|---|---|
| **Fila 0** | 1 | 4 | 7 | 11 |
| **Fila 1** | 2 | 5 | 8 | 12 |
| **Fila 2** | 3 | 6 | 9 | 16 |
| **Fila 3** | 10 | 13 | 14 | 17 |

### 5.2 Algoritmo Staircase paso a paso

```mermaid
flowchart TD
    S1["Empezar en esquina sup-der: fila=0 col=3 → valor=11"]
    S2["14 > 11 → bajar: fila=1 col=3 → valor=12"]
    S3["14 > 12 → bajar: fila=2 col=3 → valor=16"]
    S4["14 < 16 → izquierda: fila=2 col=2 → valor=9"]
    S5["14 > 9 → bajar: fila=3 col=2 → valor=14 ✅"]

    S1 --> S2 --> S3 --> S4 --> S5
```

**Complejidad**: O(filas + columnas) — mucho mejor que O(filas × columnas).

### 5.3 Reglas del algoritmo

| Condición | Acción |
|---|---|
| Valor actual **== target** | ¡Encontrado! |
| Valor actual **> target** | Moverse a la **izquierda** (col--) |
| Valor actual **< target** | Moverse **abajo** (row++) |
| Fuera de límites | No encontrado → devolver null |

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto Principal |
|---|---|---|
| 17 | `Ej17_BusquedaLineal.java` | Búsqueda secuencial y variantes |
| 18 | `Ej18_BusquedaBinaria.java` | Binaria iterativa y recursiva |
| 19 | `Ej19_BusquedaBinariaVariantes.java` | Primera/última ocurrencia, lower/upper bound |
| 20 | `Ej20_BusquedaEnMatriz.java` | Búsqueda staircase en matriz 2D ordenada |
