# Bloque II — Rotación y Transformación de Arrays Bidimensionales

> Referencia para ejercicios `Ej07` a `Ej12` en `src/main/java/bloque2/`

## 1. Transpuesta de una matriz

La transpuesta convierte filas en columnas y columnas en filas. Si la original es `MxN`, la transpuesta es `NxM`.

```
Original (2x3):        Transpuesta (3x2):
1  2  3                 1  4
4  5  6                 2  5
                        3  6
```

**Fórmula:** `transpuesta[j][i] = original[i][j]`

```mermaid
flowchart LR
    subgraph "Original 2x3"
        A["[0][0]=1"] --> B["[0][1]=2"] --> C["[0][2]=3"]
        D["[1][0]=4"] --> E["[1][1]=5"] --> F["[1][2]=6"]
    end
    subgraph "Transpuesta 3x2"
        G["[0][0]=1"] --> H["[0][1]=4"]
        I["[1][0]=2"] --> J["[1][1]=5"]
        K["[2][0]=3"] --> L["[2][1]=6"]
    end
    A -.-> G
    D -.-> H
    B -.-> I
    E -.-> J
    C -.-> K
    F -.-> L
```

**Clave:** El nuevo array tiene dimensiones invertidas: `new int[columnas][filas]`.

## 2. Rotación 90° en sentido horario

La matriz se gira como un reloj. La primera columna se convierte en la primera fila (leída de abajo a arriba).

```
Original:               Rotado 90° horario:
1  2  3                 7  4  1
4  5  6                 8  5  2
7  8  9                 9  6  3
```

**Fórmula:** `resultado[j][filas - 1 - i] = original[i][j]`

O equivalentemente: `resultado[col][filas - 1 - fila] = original[fila][col]`

```mermaid
flowchart TD
    subgraph "Paso a paso"
        S1["original[i][j]"] --> S2["La columna j se convierte en fila j"]
        S2 --> S3["La fila i se invierte: filas-1-i se convierte en columna"]
        S3 --> S4["resultado[j][filas-1-i]"]
    end
```

Para una matriz `MxN`, el resultado es `NxM`.

```mermaid
graph TD
    subgraph "Original 3x3"
        direction LR
        R1["1 2 3"]
        R2["4 5 6"]
        R3["7 8 9"]
    end
    subgraph "Rotado 90° →"
        direction LR
        T1["7 4 1"]
        T2["8 5 2"]
        T3["9 6 3"]
    end
    R1 --> T1
    R2 --> T2
    R3 --> T3
```

## 3. Rotación 90° en sentido antihorario

Giro contrario al reloj. La última columna se convierte en la primera fila.

```
Original:               Rotado 90° antihorario:
1  2  3                 3  6  9
4  5  6                 2  5  8
7  8  9                 1  4  7
```

**Fórmula:** `resultado[columnas - 1 - j][i] = original[i][j]`

```mermaid
flowchart TD
    subgraph "Diferencia clave"
        H["90° HORARIO: resultado[j][filas-1-i]"]
        AH["90° ANTIHORARIO: resultado[cols-1-j][i]"]
    end
    H --- NOTE1["La columna sube a fila, la fila se invierte a columna"]
    AH --- NOTE2["La columna se invierte a fila, la fila baja a columna"]
```

## 4. Rotación 180°

Es equivalente a rotar 90° dos veces, o simplemente invertir filas y columnas.

```
Original:               Rotado 180°:
1  2  3                 9  8  7
4  5  6                 6  5  4
7  8  9                 3  2  1
```

**Fórmula:** `resultado[filas - 1 - i][columnas - 1 - j] = original[i][j]`

```mermaid
flowchart LR
    A["original[0][0] = 1"] -->|"180°"| B["resultado[2][2] = 1"]
    C["original[0][2] = 3"] -->|"180°"| D["resultado[2][0] = 3"]
    E["original[1][1] = 5"] -->|"180°"| F["resultado[1][1] = 5 (centro no cambia)"]
```

**Atajo mental:** es como leer la matriz al revés (última fila primero, dentro de cada fila de derecha a izquierda).

## 5. Espejo horizontal y vertical

### Espejo horizontal (voltear arriba-abajo)

Las filas se invierten de orden: la primera pasa a ser la última.

```
Original:         Espejo horizontal:
1  2  3           7  8  9
4  5  6           4  5  6
7  8  9           1  2  3
```

**Fórmula:** `resultado[filas - 1 - i][j] = original[i][j]`

### Espejo vertical (voltear izquierda-derecha)

Las columnas se invierten: la primera pasa a ser la última.

```
Original:         Espejo vertical:
1  2  3           3  2  1
4  5  6           6  5  4
7  8  9           9  8  7
```

**Fórmula:** `resultado[i][columnas - 1 - j] = original[i][j]`

```mermaid
flowchart TD
    subgraph "Espejo Horizontal ↕"
        EH["Invertir orden de FILAS"]
        EH --> EHF["resultado[filas-1-i][j]"]
    end
    subgraph "Espejo Vertical ↔"
        EV["Invertir orden de COLUMNAS"]
        EV --> EVF["resultado[i][cols-1-j]"]
    end
```

## 6. Relación entre transformaciones

```mermaid
stateDiagram-v2
    [*] --> Original
    Original --> Rot90H: Rotar 90° horario
    Rot90H --> Rot180: Rotar 90° horario
    Rot180 --> Rot90AH: Rotar 90° horario
    Rot90AH --> Original: Rotar 90° horario

    Original --> Transpuesta: Transponer
    Transpuesta --> Original: Transponer

    Original --> EspejoH: Espejo horizontal
    EspejoH --> Original: Espejo horizontal

    Original --> EspejoV: Espejo vertical
    EspejoV --> Original: Espejo vertical

    note right of Rot180: 180° = 2x 90° horario
    note right of Rot90AH: 270° horario = 90° antihorario
```

**Equivalencias útiles:**
- Rotar 90° horario = Transponer + Espejo vertical
- Rotar 90° antihorario = Transponer + Espejo horizontal
- Rotar 180° = Espejo horizontal + Espejo vertical

## 7. Cheat sheet de fórmulas

| Transformación | Dimensión resultado | Fórmula |
|---|---|---|
| Transpuesta | `[cols][filas]` | `r[j][i] = o[i][j]` |
| Rotar 90° horario | `[cols][filas]` | `r[j][filas-1-i] = o[i][j]` |
| Rotar 90° antihorario | `[cols][filas]` | `r[cols-1-j][i] = o[i][j]` |
| Rotar 180° | `[filas][cols]` | `r[filas-1-i][cols-1-j] = o[i][j]` |
| Espejo horizontal | `[filas][cols]` | `r[filas-1-i][j] = o[i][j]` |
| Espejo vertical | `[filas][cols]` | `r[i][cols-1-j] = o[i][j]` |

```mermaid
flowchart TD
    A["¿Cambian las dimensiones?"] -->|SÍ| B["Transpuesta, Rot90H, Rot90AH"]
    A -->|NO| C["Rot180, EspejoH, EspejoV"]
    B --> D["resultado = new int[cols][filas]"]
    C --> E["resultado = new int[filas][cols]"]
```
