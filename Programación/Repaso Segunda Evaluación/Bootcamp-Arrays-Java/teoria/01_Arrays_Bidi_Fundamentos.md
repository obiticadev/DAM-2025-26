# Bloque I — Arrays Bidimensionales: Fundamentos

> Referencia para ejercicios `Ej01` a `Ej06` en `src/main/java/bloque1/`

## 1. ¿Qué es un array bidimensional?

Un array bidimensional es una **tabla de datos** organizada en filas y columnas. En Java, es literalmente un "array de arrays": cada posición del array exterior contiene otro array.

```java
int[][] matriz = new int[3][4]; // 3 filas, 4 columnas
```

En memoria, esto NO es una tabla plana. Es un array de 3 referencias, donde cada referencia apunta a un array de 4 enteros.

```mermaid
graph TD
    A["int[][] matriz"] --> B["fila 0: int[4]"]
    A --> C["fila 1: int[4]"]
    A --> D["fila 2: int[4]"]
    B --> B0["[0][0]"] 
    B --> B1["[0][1]"]
    B --> B2["[0][2]"]
    B --> B3["[0][3]"]
    C --> C0["[1][0]"]
    C --> C1["[1][1]"]
    C --> C2["[1][2]"]
    C --> C3["[1][3]"]
    D --> D0["[2][0]"]
    D --> D1["[2][1]"]
    D --> D2["[2][2]"]
    D --> D3["[2][3]"]
```

## 2. Declaración e inicialización

### Formas válidas

```java
// Forma 1: Solo declarar dimensiones (valores por defecto: 0 para int, null para objetos)
int[][] a = new int[filas][columnas];

// Forma 2: Inicialización directa con valores
int[][] b = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

// Forma 3: Array de objetos (¡CUIDADO! Cada posición se debe instanciar individualmente)
Asiento[][] sala = new Asiento[filas][columnas];
for (int i = 0; i < filas; i++) {
    for (int j = 0; j < columnas; j++) {
        sala[i][j] = new Asiento(); // NUNCA usar Arrays.fill con objetos
    }
}
```

### La trampa de `Arrays.fill` con objetos

```mermaid
graph LR
    subgraph "Arrays.fill - INCORRECTO"
        F0["fila[0]"] --> REF["Mismo Objeto"]
        F1["fila[1]"] --> REF
        F2["fila[2]"] --> REF
    end
    subgraph "Bucle + new - CORRECTO"
        G0["fila[0]"] --> OBJ0["Objeto A"]
        G1["fila[1]"] --> OBJ1["Objeto B"]
        G2["fila[2]"] --> OBJ2["Objeto C"]
    end
```

`Arrays.fill(fila, new Objeto())` crea UN solo objeto y pone la misma referencia en todas las posiciones. Modificar uno los modifica todos.

## 3. Dimensiones del array

```java
int filas = matriz.length;          // Número de filas
int columnas = matriz[0].length;    // Número de columnas (de la primera fila)
```

```mermaid
flowchart LR
    A["matriz.length = 3"] --> B["filas"]
    C["matriz[0].length = 4"] --> D["columnas"]
```

## 4. Recorridos fundamentales

### Por filas (el más natural)

```
→ → → →
→ → → →
→ → → →
```

```java
for (int i = 0; i < matriz.length; i++) {         // filas
    for (int j = 0; j < matriz[i].length; j++) {  // columnas
        // matriz[i][j]
    }
}
```

### Por columnas

```
↓ ↓ ↓ ↓
↓ ↓ ↓ ↓
↓ ↓ ↓ ↓
```

```java
for (int j = 0; j < matriz[0].length; j++) {      // columnas primero
    for (int i = 0; i < matriz.length; i++) {      // filas después
        // matriz[i][j]
    }
}
```

### Diagonal principal (solo matrices cuadradas)

```
↘
  ↘
    ↘
```

```java
for (int i = 0; i < matriz.length; i++) {
    // matriz[i][i]
}
```

### Diagonal inversa (solo matrices cuadradas)

```
      ↙
    ↙
  ↙
```

```java
int n = matriz.length;
for (int i = 0; i < n; i++) {
    // matriz[i][n - 1 - i]
}
```

```mermaid
flowchart TD
    subgraph "Recorrido por filas"
        direction LR
        R1["i=0, j=0→3"] --> R2["i=1, j=0→3"] --> R3["i=2, j=0→3"]
    end
    subgraph "Recorrido por columnas"
        direction LR
        C1["j=0, i=0→2"] --> C2["j=1, i=0→2"] --> C3["j=2, i=0→2"]
    end
    subgraph "Diagonal principal"
        direction LR
        D1["[0][0]"] --> D2["[1][1]"] --> D3["[2][2]"]
    end
```

## 5. Representación visual (pintar un array)

El patrón estándar para convertir un array bidi en String legible:

```mermaid
flowchart TD
    A[Crear StringBuilder] --> B[Bucle filas i]
    B --> C[Bucle columnas j]
    C --> D["sb.append(matriz[i][j] + espacio)"]
    D --> C
    C -- fin fila --> E["sb.append(salto de línea)"]
    E --> B
    B -- fin filas --> F["return sb.toString()"]
```

La idea clave: **StringBuilder** es la herramienta correcta para construir strings en bucles. Concatenar con `+` en un bucle crea objetos String temporales en cada iteración.

## 6. Validación de rango

Antes de acceder a `matriz[fila][columna]`, SIEMPRE valida:

```java
if (fila < 0 || fila >= matriz.length || columna < 0 || columna >= matriz[0].length) {
    // Fuera de rango → no acceder
}
```

```mermaid
flowchart TD
    A["Recibir fila, columna"] --> B{"fila < 0?"}
    B -- sí --> X["RECHAZAR"]
    B -- no --> C{"fila >= filas?"}
    C -- sí --> X
    C -- no --> D{"columna < 0?"}
    D -- sí --> X
    D -- no --> E{"columna >= columnas?"}
    E -- sí --> X
    E -- no --> F["ACCEDER a matriz[fila][columna]"]
```

## 7. Copia profunda vs referencia

```mermaid
graph TD
    subgraph "Copia por referencia (MAL)"
        A["int[][] copia = original"] 
        A --> SAME["Mismo array en memoria"]
        ORIG["original"] --> SAME
    end
    subgraph "Copia profunda (BIEN)"
        B["int[][] copia = new int[f][c]"]
        B --> NEW["Nuevo array independiente"]
        ORIG2["original"] --> OLD["Array original intacto"]
    end
```

Para copiar un array bidi hay que crear uno nuevo y copiar elemento a elemento:

```java
int[][] copia = new int[original.length][original[0].length];
for (int i = 0; i < original.length; i++) {
    for (int j = 0; j < original[i].length; j++) {
        copia[i][j] = original[i][j];
    }
}
```

## 8. Operaciones estadísticas

Para calcular sumas, máximos o mínimos **por fila** o **por columna**, el truco es saber qué índice se fija:

```mermaid
flowchart LR
    subgraph "Suma por fila i"
        SF["Fijar i, recorrer j=0..cols"]
    end
    subgraph "Suma por columna j"
        SC["Fijar j, recorrer i=0..filas"]
    end
```

- **Suma de fila i:** recorrer `j` de 0 a columnas, sumando `matriz[i][j]`
- **Suma de columna j:** recorrer `i` de 0 a filas, sumando `matriz[i][j]`
- **Máximo de fila i:** empezar con `matriz[i][0]`, comparar con cada `matriz[i][j]`
