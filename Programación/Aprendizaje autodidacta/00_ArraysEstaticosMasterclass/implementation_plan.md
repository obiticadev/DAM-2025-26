# 🏗️ Hoja de Ruta — `00_ArraysEstaticosMasterclass`

**Bootcamp Autodidacta: Arrays Estáticos en Java — Dominio Fundamental**
50 Ejercicios · Maven · JUnit 5 · 10 Niveles · SIN Collections

---

## Estructura Raíz del Proyecto

```
00_ArraysEstaticosMasterclass/
├── README_GUIA_TERMINAL.md
├── pom.xml
├── teoria/
│   ├── 01_Fundamentos_Arrays.md
│   ├── 02_Redimensionado_Y_Copia.md
│   ├── 03_Ordenacion_Sobre_Arrays.md
│   ├── 04_Busqueda_En_Arrays.md
│   ├── 05_Arrays_Bidimensionales_Fundamentos.md
│   ├── 06_Transformaciones_De_Matrices.md
│   ├── 07_Arrays_Tridimensionales.md
│   ├── 08_Simulacion_Estructuras_Con_Arrays.md
│   ├── 09_Patrones_Avanzados_Sin_Collections.md
│   └── 10_Boss_Final_Gestor_Datos_En_Memoria.md
├── src/main/java/com/masterclass/arrays/
│   ├── nivel01/ ... nivel10/
└── src/test/java/com/masterclass/arrays/
    ├── nivel01/ ... nivel10/
```

---

## Paquete Base Maven

| Campo | Valor |
|---|---|
| `groupId` | `com.masterclass` |
| `artifactId` | `arrays-estaticos-masterclass` |
| `version` | `1.0.0-SNAPSHOT` |
| `java.version` | `25` |
| `junit.version` | `5.10.2` |
| `assertj.version` | `3.25.3` |

---

## Restricción Fundamental

> [!CAUTION]
> **PROHIBIDO** usar `java.util.Collections`, `ArrayList`, `HashMap`, `LinkedList`, `Arrays.sort()`, `Arrays.stream()` o cualquier clase/método de la API de Collections/Streams.
> Todo se construye manualmente con arrays primitivos (`int[]`, `String[]`, `int[][]`, `int[][][]`, etc.).

---

## Sílabo Completo por Niveles

---

### 📘 NIVEL 01 — Fundamentos de Arrays (Ejercicios 1–5)

> **Teoría**: `teoria/01_Fundamentos_Arrays.md`
> Diagramas Mermaid: Layout contiguo en memoria, ciclo de vida (declaración → inicialización → acceso), valores por defecto por tipo, índices y bounds.

| # | Archivo Ejercicio (`nivel01/`) | Archivo Test (`nivel01/`) | Descripción |
|---|---|---|---|
| 1 | `Ej01_DeclaracionInicializacion.java` | `Ej01_DeclaracionInicializacionTest.java` | Declarar arrays de distintos tipos (`int`, `double`, `String`, `boolean`). Inicialización con `new` vs literal `{}`. Comprobar valores por defecto (`0`, `0.0`, `null`, `false`). |
| 2 | `Ej02_RecorridoYOperaciones.java` | `Ej02_RecorridoYOperacionesTest.java` | Recorrer arrays con `for` clásico y `for-each`. Calcular suma, media, máximo y mínimo de un `int[]` manualmente, sin API. |
| 3 | `Ej03_InsercionEnPosicion.java` | `Ej03_InsercionEnPosicionTest.java` | Insertar un elemento en una posición concreta de un array, desplazando el resto hacia la derecha. Manejar el caso de array lleno. |
| 4 | `Ej04_EliminacionEnPosicion.java` | `Ej04_EliminacionEnPosicionTest.java` | Eliminar un elemento de una posición concreta, desplazando el resto hacia la izquierda. Controlar el tamaño lógico vs físico. |
| 5 | `Ej05_InversionDeArray.java` | `Ej05_InversionDeArrayTest.java` | Invertir un array in-place (sin crear otro array). Técnica de swap con dos punteros (inicio ↔ fin). |

---

### 📘 NIVEL 02 — Redimensionado y Copia (Ejercicios 6–10)

> **Teoría**: `teoria/02_Redimensionado_Y_Copia.md`
> Diagramas Mermaid: Heap con array original y array nuevo, proceso de copia y reasignación de referencia, tamaño lógico vs tamaño físico, política de crecimiento.

| # | Archivo Ejercicio (`nivel02/`) | Archivo Test (`nivel02/`) | Descripción |
|---|---|---|---|
| 6 | `Ej06_CopiaManual.java` | `Ej06_CopiaManualTest.java` | Copiar un array elemento a elemento en uno nuevo. Copia parcial (subarray de índice `a` a `b`). Demostrar que la copia es independiente del original. |
| 7 | `Ej07_SystemArraycopy.java` | `Ej07_SystemArraycopyTest.java` | Usar `System.arraycopy` para copias eficientes. Copia con offset, copia parcial, solapamiento. Comparar con copia manual. |
| 8 | `Ej08_CrecimientoGeometrico.java` | `Ej08_CrecimientoGeometricoTest.java` | Implementar un "array dinámico" manual: cuando se llena, crear uno nuevo con el doble de capacidad y copiar. Simular un `add()` que crece automáticamente. |
| 9 | `Ej09_ReduccionDeCapacidad.java` | `Ej09_ReduccionDeCapacidadTest.java` | Reducir un array: cuando el tamaño lógico cae al 25% de la capacidad, encoger a la mitad. Implementar `remove()` + `shrink()`. |
| 10 | `Ej10_ArrayDinamicoCompleto.java` | `Ej10_ArrayDinamicoCompletoTest.java` | Unir los ejercicios 8 y 9: construir un "MiArrayList" funcional sobre `int[]` con `add`, `get`, `set`, `remove`, `size`, `contains`, auto-grow y auto-shrink. Todo sin `java.util`. |

---

### 📘 NIVEL 03 — Ordenación sobre Arrays (Ejercicios 11–16)

> **Teoría**: `teoria/03_Ordenacion_Sobre_Arrays.md`
> Diagramas Mermaid: Visualización paso a paso de cada algoritmo, comparaciones y swaps, tabla Big-O comparativa, estabilidad vs inestabilidad.

| # | Archivo Ejercicio (`nivel03/`) | Archivo Test (`nivel03/`) | Descripción |
|---|---|---|---|
| 11 | `Ej11_BubbleSort.java` | `Ej11_BubbleSortTest.java` | Bubble Sort in-place. Optimización con flag de "ya ordenado". Contar comparaciones y swaps. |
| 12 | `Ej12_SelectionSort.java` | `Ej12_SelectionSortTest.java` | Selection Sort in-place. Buscar mínimo en subarray no ordenado, swap con posición actual. |
| 13 | `Ej13_InsertionSort.java` | `Ej13_InsertionSortTest.java` | Insertion Sort in-place. Desplazamiento hacia la derecha para insertar en su lugar. Eficiente en arrays casi-ordenados. |
| 14 | `Ej14_QuickSort.java` | `Ej14_QuickSortTest.java` | QuickSort recursivo in-place. Partición con pivot (último elemento). Caso base, caso recursivo, análisis del peor caso. |
| 15 | `Ej15_MergeSort.java` | `Ej15_MergeSortTest.java` | MergeSort con array auxiliar. Dividir, ordenar recursivamente, fusionar. Entender por qué necesita espacio extra O(n). |
| 16 | `Ej16_OrdenacionStrings.java` | `Ej16_OrdenacionStringsTest.java` | Aplicar Insertion Sort y QuickSort sobre `String[]` usando `compareTo()`. Ordenar nombres alfabéticamente sin `Arrays.sort`. |

---

### 📘 NIVEL 04 — Búsqueda en Arrays (Ejercicios 17–20)

> **Teoría**: `teoria/04_Busqueda_En_Arrays.md`
> Diagramas Mermaid: Búsqueda lineal vs binaria en diagrama de flujo, partición del espacio de búsqueda, first/last occurrence, complejidad O(n) vs O(log n).

| # | Archivo Ejercicio (`nivel04/`) | Archivo Test (`nivel04/`) | Descripción |
|---|---|---|---|
| 17 | `Ej17_BusquedaLineal.java` | `Ej17_BusquedaLinealTest.java` | Búsqueda lineal: encontrar índice de un elemento, contar ocurrencias, encontrar todos los índices donde aparece. |
| 18 | `Ej18_BusquedaBinaria.java` | `Ej18_BusquedaBinariaTest.java` | Búsqueda binaria iterativa y recursiva sobre array previamente ordenado. Devolver -1 si no existe. |
| 19 | `Ej19_BusquedaBinariaVariantes.java` | `Ej19_BusquedaBinariaVariantesTest.java` | Variantes: primera ocurrencia, última ocurrencia, "lower bound" (primer elemento ≥ valor), "upper bound" (primer elemento > valor). |
| 20 | `Ej20_BusquedaEnMatriz.java` | `Ej20_BusquedaEnMatrizTest.java` | Buscar un elemento en una matriz 2D ordenada por filas y columnas. Técnica de esquina superior derecha (staircase search). |

---

### 📘 NIVEL 05 — Arrays Bidimensionales: Fundamentos (Ejercicios 21–26)

> **Teoría**: `teoria/05_Arrays_Bidimensionales_Fundamentos.md`
> Diagramas Mermaid: Representación visual de una matriz en memoria (array de arrays en heap), recorrido por filas vs columnas, diferencia rectangular vs jagged, diagonales principal y secundaria.

| # | Archivo Ejercicio (`nivel05/`) | Archivo Test (`nivel05/`) | Descripción |
|---|---|---|---|
| 21 | `Ej21_CreacionYRecorrido2D.java` | `Ej21_CreacionYRecorrido2DTest.java` | Crear matrices rectangulares (`int[filas][columnas]`). Recorrer por filas, por columnas. Rellenar con patrones (secuencial, tablero de ajedrez). |
| 22 | `Ej22_SumaFilasColumnasMedias.java` | `Ej22_SumaFilasColumnasMediasTest.java` | Calcular la suma de cada fila, la suma de cada columna, la media global. Encontrar la fila con mayor suma y la columna con menor suma. |
| 23 | `Ej23_DiagonalesPrincipalSecundaria.java` | `Ej23_DiagonalesPrincipalSecundariaTest.java` | Extraer diagonal principal (`[i][i]`), diagonal secundaria (`[i][n-1-i]`). Sumar diagonales. Recorrer todas las diagonales paralelas. |
| 24 | `Ej24_MatrizIdentidadYSimetrica.java` | `Ej24_MatrizIdentidadYSimetricaTest.java` | Generar matriz identidad. Verificar si una matriz es simétrica (`m[i][j] == m[j][i]`). Construir la traspuesta. |
| 25 | `Ej25_JaggedArrays.java` | `Ej25_JaggedArraysTest.java` | Arrays irregulares (jagged): filas de distinta longitud. Crear un triángulo de Pascal usando jagged arrays. |
| 26 | `Ej26_MatrizEspiralRecorrido.java` | `Ej26_MatrizEspiralRecorridoTest.java` | Recorrer una matriz en espiral (exterior → interior). Generar una "spiral matrix" rellenando en orden espiral del 1 al N×M. |

---

### 📘 NIVEL 06 — Transformaciones de Matrices (Ejercicios 27–32)

> **Teoría**: `teoria/06_Transformaciones_De_Matrices.md`
> Diagramas Mermaid: Rotación 90° paso a paso (transponer + invertir filas), espejo horizontal/vertical, transformaciones compuestas, coordenadas antes/después.

| # | Archivo Ejercicio (`nivel06/`) | Archivo Test (`nivel06/`) | Descripción |
|---|---|---|---|
| 27 | `Ej27_Transposicion.java` | `Ej27_TransposicionTest.java` | Trasponer una matriz cuadrada in-place (swap `[i][j]` ↔ `[j][i]`). Trasponer una matriz rectangular (requiere nuevo array). |
| 28 | `Ej28_Rotacion90Grados.java` | `Ej28_Rotacion90GradosTest.java` | Rotar 90° en sentido horario: transponer + invertir cada fila. Rotar 90° anti-horario: transponer + invertir cada columna. Hacerlo in-place para cuadradas. |
| 29 | `Ej29_Rotacion180Y270.java` | `Ej29_Rotacion180Y270Test.java` | Rotar 180°: invertir filas + invertir columnas (o rotar 90° dos veces). Rotar 270°: equivalente a 90° anti-horario. Verificar equivalencias. |
| 30 | `Ej30_EspejoHorizontalVertical.java` | `Ej30_EspejoHorizontalVerticalTest.java` | Espejo horizontal: invertir el orden de las filas. Espejo vertical: invertir cada fila. Espejo diagonal (= transposición). |
| 31 | `Ej31_SubmatrizYMarco.java` | `Ej31_SubmatrizYMarcoTest.java` | Extraer una submatriz dada una esquina y dimensiones. Extraer el "marco" exterior de una matriz. Inyectar una submatriz dentro de otra. |
| 32 | `Ej32_SumaMultiplicacionMatrices.java` | `Ej32_SumaMultiplicacionMatricesTest.java` | Sumar dos matrices. Multiplicar dos matrices (A[m×n] × B[n×p] = C[m×p]). Verificar que las dimensiones son compatibles. |

---

### 📘 NIVEL 07 — Arrays Tridimensionales (Ejercicios 33–36)

> **Teoría**: `teoria/07_Arrays_Tridimensionales.md`
> Diagramas Mermaid: Cubo de datos con ejes (depth × rows × cols), acceso por plano/fila/columna, agrupación de capas, analogías con imágenes RGB y voxels.

| # | Archivo Ejercicio (`nivel07/`) | Archivo Test (`nivel07/`) | Descripción |
|---|---|---|---|
| 33 | `Ej33_Creacion3DRecorrido.java` | `Ej33_Creacion3DRecorridoTest.java` | Crear `int[depth][rows][cols]`. Recorrer todas las capas, filas y columnas. Rellenar con valores secuenciales. Calcular suma total. |
| 34 | `Ej34_OperacionesPorCapas.java` | `Ej34_OperacionesPorCapasTest.java` | Extraer una "capa" (plano 2D) del cubo. Sumar todas las capas en una sola matriz 2D resultado. Buscar el máximo en todo el cubo. |
| 35 | `Ej35_CuboRotaciones.java` | `Ej35_CuboRotacionesTest.java` | Rotar el cubo sobre distintos ejes: permutar depth↔rows, depth↔cols. Aplicar rotación 90° a cada capa 2D individualmente. |
| 36 | `Ej36_TablaTridimensional.java` | `Ej36_TablaTridimensionalTest.java` | Caso práctico: usar un `int[12][31][24]` para almacenar datos por mes/día/hora (ej. temperaturas). Consultas: media por mes, máximo por día, totales por hora. |

---

### 📘 NIVEL 08 — Simulación de Estructuras con Arrays (Ejercicios 37–43)

> **Teoría**: `teoria/08_Simulacion_Estructuras_Con_Arrays.md`
> Diagramas Mermaid: Map con arrays paralelos (keys[] + values[]), Map con `String[n][2]`, Stack LIFO con puntero top, Queue circular con head/tail, Set con búsqueda lineal.

| # | Archivo Ejercicio (`nivel08/`) | Archivo Test (`nivel08/`) | Descripción |
|---|---|---|---|
| 37 | `Ej37_MapConArraysParalelos.java` | `Ej37_MapConArraysParalelosTest.java` | Simular un `Map<String, Integer>` con dos arrays paralelos: `String[] keys` y `int[] values`. Implementar `put`, `get`, `containsKey`, `remove`. |
| 38 | `Ej38_MapConArray2D.java` | `Ej38_MapConArray2DTest.java` | Simular un `Map<String, String>` con un `String[n][2]` donde columna 0 = clave, columna 1 = valor. Mismas operaciones: `put`, `get`, `remove`, `keys()`. |
| 39 | `Ej39_StackSobreArray.java` | `Ej39_StackSobreArrayTest.java` | Pila (LIFO) sobre `int[]` fijo. Operaciones: `push`, `pop`, `peek`, `isEmpty`, `isFull`, `size`. Manejo de overflow (excepción o crecimiento). |
| 40 | `Ej40_QueueSobreArray.java` | `Ej40_QueueSobreArrayTest.java` | Cola (FIFO) sobre `int[]` con aritmética circular: `enqueue`, `dequeue`, `peek`, `isEmpty`, `isFull`. Punteros `head` y `tail` con módulo. |
| 41 | `Ej41_SetSobreArray.java` | `Ej41_SetSobreArrayTest.java` | Simular un `Set<String>` sobre `String[]`. No permitir duplicados. Operaciones: `add`, `contains`, `remove`, `size`, `toArray`. |
| 42 | `Ej42_MultiMapParalelo.java` | `Ej42_MultiMapParaleloTest.java` | Simular un MultiMap donde una clave puede tener múltiples valores: `String[] keys`, `int[][] values` (jagged). `put(key, value)` añade al grupo. `getAll(key)` devuelve todos. |
| 43 | `Ej43_TablaRegistros.java` | `Ej43_TablaRegistrosTest.java` | Tabla de registros tipo base de datos: `String[][] tabla` donde cada fila es un registro y cada columna es un campo. Implementar `insertRow`, `deleteRow`, `findByColumn`, `sortByColumn`. |

---

### 📘 NIVEL 09 — Patrones Avanzados Sin Collections (Ejercicios 44–49)

> **Teoría**: `teoria/09_Patrones_Avanzados_Sin_Collections.md`
> Diagramas Mermaid: Two pointers convergiendo, sliding window fija vs variable, merge de arrays, frecuencias con array de conteo, deduplicación in-place.

| # | Archivo Ejercicio (`nivel09/`) | Archivo Test (`nivel09/`) | Descripción |
|---|---|---|---|
| 44 | `Ej44_MergeDeArraysOrdenados.java` | `Ej44_MergeDeArraysOrdenadosTest.java` | Fusionar dos arrays ordenados en uno solo ordenado. Sin sort posterior: recorrido simultáneo con dos índices. |
| 45 | `Ej45_EliminacionDuplicados.java` | `Ej45_EliminacionDuplicadosTest.java` | Eliminar duplicados de un array ordenado in-place (two pointers). Eliminar duplicados de un array NO ordenado (con array auxiliar). |
| 46 | `Ej46_FrecuenciasSinMap.java` | `Ej46_FrecuenciasSinMapTest.java` | Contar frecuencia de cada elemento usando arrays paralelos (valores únicos + contadores). También: histograma con array de conteo para rango conocido. |
| 47 | `Ej47_InterseccionUnionDiferencia.java` | `Ej47_InterseccionUnionDiferenciaTest.java` | Dados dos arrays: calcular intersección, unión y diferencia. Todo manual con bucles y arrays resultado. |
| 48 | `Ej48_TwoPointerProblemas.java` | `Ej48_TwoPointerProblemasTest.java` | Técnica two-pointer: encontrar par que suma K en array ordenado, encontrar triplete que suma cero, verificar si un array es palíndromo. |
| 49 | `Ej49_SlidingWindow.java` | `Ej49_SlidingWindowTest.java` | Ventana deslizante sobre array: máxima suma de subarray de tamaño K, subarray más largo sin repetidos (con array auxiliar de flags). |

---

### 🏆 NIVEL 10 — **EL BOSS FINAL** (Ejercicio 50)

> **Teoría**: `teoria/10_Boss_Final_Gestor_Datos_En_Memoria.md`
> Diagramas Mermaid: Arquitectura completa del gestor, flujo de operaciones CRUD, estructura de la tabla interna, índice por clave con arrays paralelos, pipeline de queries.

| # | Archivo Ejercicio (`nivel10/`) | Archivo Test (`nivel10/`) | Descripción |
|---|---|---|---|
| 50 | `Ej50_GestorDatosEnMemoria.java` | `Ej50_GestorDatosEnMemoriaTest.java` | **Gestor de Datos Tabular en Memoria** construido ENTERAMENTE con arrays estáticos. |

#### Especificación del Boss Final — Ejercicio 50

El alumno deberá construir un **gestor de datos tabular en memoria** que funcione como una mini base de datos. **Prohibido usar** `ArrayList`, `HashMap`, `Collections`, `Arrays.sort()` o cualquier clase de `java.util`.

**Componentes exigidos (todo implementado sobre arrays):**
1. **TablaEnMemoria** — Almacenar registros como `String[][] datos` (filas × columnas). Auto-crecimiento del array cuando se llene.
2. **IndiceMap** — Índice de búsqueda rápida por clave primaria usando arrays paralelos `String[] claves` + `int[] posiciones` con búsqueda binaria (mantener ordenado).
3. **Motor de Consultas** — Ejecutar operaciones:
   - `INSERT(String[] registro)` — añadir fila con auto-grow.
   - `SELECT_BY_KEY(String clave)` — búsqueda O(log n) usando el índice.
   - `SELECT_ALL_SORTED_BY(int columna)` — devolver todos los registros ordenados por una columna (QuickSort sobre la tabla).
   - `DELETE_BY_KEY(String clave)` — eliminar y compactar.
   - `UPDATE(String clave, int columna, String nuevoValor)` — modificar campo.
4. **Estadísticas** — Para columnas numéricas: suma, media, máximo, mínimo (parseando Strings a int).
5. **Exportador** — Volcar la tabla completa como `String[][]` formateado y como representación textual tabular.

**Suite de tests**: La más rigurosa del bootcamp (≥ 20 tests), cubriendo:
- Inserción masiva (cientos de registros) con auto-crecimiento
- Búsquedas por clave (encontradas y no encontradas)
- Eliminación y verificación de compactación
- Ordenación por distintas columnas
- Actualizaciones y verificación de integridad
- Estadísticas numéricas correctas
- Estrés: insertar, borrar, buscar, ordenar en secuencia pesada

---

## Plan de Entrega por Bloques

| Bloque | Niveles | Ejercicios | Archivos a Generar |
|---|---|---|---|
| **Bloque I** | Nivel 01 + Nivel 02 | Ej. 1–10 | `pom.xml`, `README_GUIA_TERMINAL.md`, 2 teorías `.md`, 10 ejercicios `.java`, 10 tests `.java` |
| **Bloque II** | Nivel 03 + Nivel 04 | Ej. 11–20 | 2 teorías `.md`, 10 ejercicios `.java`, 10 tests `.java` |
| **Bloque III** | Nivel 05 + Nivel 06 | Ej. 21–32 | 2 teorías `.md`, 12 ejercicios `.java`, 12 tests `.java` |
| **Bloque IV** | Nivel 07 + Nivel 08 | Ej. 33–43 | 2 teorías `.md`, 11 ejercicios `.java`, 11 tests `.java` |
| **Bloque V** | Nivel 09 + Nivel 10 | Ej. 44–50 | 2 teorías `.md`, 7 ejercicios `.java`, 7 tests `.java` |

> Tras cada bloque pausaré y esperaré tu confirmación ("siguiente") para continuar con calidad manual.

---

## Convenciones del Proyecto

- **Nombre del proyecto**: `00_ArraysEstaticosMasterclass`
- **Paquete base**: `com.masterclass.arrays`
- **Sub-paquetes**: `nivel01`, `nivel02`, ..., `nivel10`
- **Cada `.java` de ejercicio**: contiene **≥ 7 TODOs** técnicos + `main()` ejecutable (Playground)
- **Cada `Test.java`**: viene **completo y funcional** (los tests fallan hasta que el alumno implemente los TODOs)
- **Teoría**: 100% conceptual con **diagramas Mermaid obligatorios**, cero código resuelto
- **Java**: 25 · Maven · JUnit 5.10.2 · AssertJ 3.25.3
- **CERO imports de `java.util.*`** en los ejercicios (salvo las excepciones estándar)

---

## Open Questions

> [!IMPORTANT]
> **¿Autorizo la construcción del Bloque I?**
> El Bloque I incluye: `pom.xml`, `README_GUIA_TERMINAL.md`, las teorías de Nivel 01 (`01_Fundamentos_Arrays.md`) y Nivel 02 (`02_Redimensionado_Y_Copia.md`), los 10 primeros ejercicios (esqueletos con ≥7 TODOs cada uno), y sus 10 archivos de tests completos.
>
> Responde **"siguiente"** o **"autorizo"** para que comience la generación de archivos reales.
