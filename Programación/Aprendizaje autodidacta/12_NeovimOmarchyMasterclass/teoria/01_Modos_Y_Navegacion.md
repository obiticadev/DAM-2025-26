# 📘 Nivel 01 — Movimiento por el texto

---

## 1. Por qué movimiento es lo primero (y lo más importante)

En Vim, **moverte rápido es media batalla**. Cada operador (borrar, copiar, cambiar) se aplica sobre un **movimiento** o un **text object**. Es decir, si dominas el movimiento, también dominas la edición: `d` (borrar) + `w` (palabra) = `dw` (borra palabra). Punto.

La gramática es siempre la misma:

```
[contador] [operador] [movimiento]
   2          d           w        → borra 2 palabras
   3          y           j        → copia esta línea + 3 más
              c           i"       → cambia el contenido entre comillas
```

> **La clave mental:** **No memorices "qué hace cada combinación"**, memoriza los OPERADORES y los MOVIMIENTOS por separado. La combinatoria sale sola.

---

## 2. Los movimientos básicos — hjkl

| Tecla | Dirección |
|---|---|
| `h` | izquierda |
| `j` | abajo |
| `k` | arriba |
| `l` | derecha |

```
        ↑ k
   h ← cursor → l
        ↓ j
```

> **Truco mnemónico:** `j` parece una flecha hacia abajo. Es la única que necesita mnemónico — el resto se aprende a base de uso.

> **Error típico:** Usar las flechas. En Vim funcionan, pero te alejan de la fila central. Cada vez que pulses una flecha durante el bootcamp, repítela 5 veces con `hjkl` como penitencia.

---

## 3. Movimiento por palabras — wbe

Las palabras son la unidad natural de navegación dentro de una línea.

| Tecla | A dónde te lleva |
|---|---|
| `w` | inicio de la **siguiente** palabra |
| `b` | inicio de la palabra **anterior** (back) |
| `e` | **final** de la palabra actual (end) |
| `W` `B` `E` | igual pero usando "WORDS" (separadas solo por espacios, incluyen puntuación) |

```
" Texto:  Hola, mundo cruel.  El gato come.
"         ^   ^  ^     ^   ^  ^  ^   ^
"         |   w  w     w   w  w  w   w   (w salta al inicio de la siguiente)
"         e   e         e        e  e    (e va al final de la actual)
```

> **Diferencia w vs W:** `w` considera la puntuación como palabra propia (`hola,` son tres "palabras": `hola`, `,`, lo que venga). `W` considera el bloque entero entre espacios como una sola WORD (`hola,` es una sola). En texto natural usa `w`; en código con muchos símbolos suele ser mejor `W`.

---

## 4. Movimiento dentro de la línea — 0 $ ^ g_

| Tecla | A dónde |
|---|---|
| `0` | primera columna de la línea (incluye indentación) |
| `^` | primer carácter NO blanco de la línea (skip indentación) |
| `$` | último carácter de la línea |
| `g_` | último carácter NO blanco de la línea |

```
"     ↓0   ↓^                          $↓
"     ····public static void main(...) {····
```

> **Truco:** En código, `^` y `g_` son casi siempre lo que quieres (saltar la indentación). En texto plano, `0` y `$`.

---

## 5. Movimiento por el archivo entero — gg G {n}G

| Tecla | A dónde |
|---|---|
| `gg` | primera línea del archivo |
| `G` | última línea del archivo |
| `{n}G` o `:{n}` | línea número `n` |
| `Ctrl-d` / `Ctrl-u` | media pantalla abajo / arriba |
| `Ctrl-f` / `Ctrl-b` | página completa abajo / arriba |
| `H` `M` `L` | top / middle / bottom de la pantalla visible |

> **Para el examen mental:** `:42` te lleva a la línea 42. Es lo mismo que `42G`. En código suele ser más cómodo escribir `:42` cuando el compilador te da un error con número de línea.

---

## 6. Saltos dentro de la línea — f F t T y ; ,

Los más infravalorados al principio. Cuando los domines, te vuelves rápido.

| Tecla | A dónde |
|---|---|
| `fx` | siguiente ocurrencia de la letra `x` en la línea (cursor encima de la `x`) |
| `Fx` | misma búsqueda hacia atrás |
| `tx` | hasta ANTES de la siguiente `x` (cursor justo antes) |
| `Tx` | hasta DESPUÉS de la anterior `x` (cursor justo después) |
| `;` | repite la última `f/F/t/T` hacia adelante |
| `,` | repite la última `f/F/t/T` hacia atrás |

```
" buscar la 'g' de 'gato':
" cursor aquí ↓
" El perro saluda al gato.
"  pulsas: fg
"                    ↑ cursor cae aquí
```

> **Combina con operadores:** `dt,` = borra hasta antes de la siguiente coma. `cf;` = cambia desde aquí hasta el siguiente `;` (incluido).

---

## 7. Búsqueda — / ? n N

| Tecla | Qué hace |
|---|---|
| `/patrón` + Enter | busca `patrón` hacia adelante |
| `?patrón` + Enter | busca `patrón` hacia atrás |
| `n` | repite la última búsqueda en la misma dirección |
| `N` | repite la última búsqueda en dirección **opuesta** |
| `*` | busca la palabra bajo el cursor hacia adelante |
| `#` | busca la palabra bajo el cursor hacia atrás |
| `:noh` | quita el resaltado |

`/` acepta regex de Vim (similar a regex POSIX, con algunas diferencias). Para regex "modo mágico" intuitivo: `/\v...`.

> **Trucos rápidos:**
> - `/\<palabra\>` busca solo la palabra completa.
> - `/\cTexto` busca insensible a mayúsculas (case-insensitive) puntualmente.
> - `:set ignorecase smartcase` activa búsqueda inteligente (mayúsculas matchean exactas, minúsculas matchean todo).

---

## 8. Saltos de bloque — { } ( ) [[ ]]

| Tecla | A dónde |
|---|---|
| `{` / `}` | párrafo anterior / siguiente (línea en blanco) |
| `(` / `)` | frase anterior / siguiente |
| `[[` / `]]` | inicio de función anterior / siguiente (en código con `{` en columna 0) |
| `%` | salta al `(` / `{` / `[` que cierra el actual (matching bracket) |

> `%` es oro puro para programadores: ponte sobre un `{` y pulsa `%` — saltas al `}` correspondiente. Igual con paréntesis y corchetes.

---

## 9. Diagrama-resumen mental

```mermaid
flowchart TD
    A[Necesito moverme] --> B{¿Cuánto?}
    B -->|1-2 caracteres| C[hjkl]
    B -->|Una palabra| D[w b e]
    B -->|Salto en línea hacia un carácter| E[f F t T]
    B -->|Inicio/fin de línea| F[0 ^ $ g_]
    B -->|Otra línea concreta| G[42G  o  :42]
    B -->|Buscar texto| H[/patrón  o  *]
    B -->|Inicio/fin de archivo| I[gg  o  G]
    B -->|Bracket pareja| J[%]
```

Memoriza este árbol. Cada vez que vayas a mover el cursor, pregúntate qué rama necesitas. En 2 semanas será automático.

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto |
|---|---|---|
| 01.01 | `ej01_hjkl_basico.txt` | Movimiento carácter a carácter sin flechas |
| 01.02 | `ej02_palabras_wbe.txt` | Salto por palabras `w b e W B E` |
| 01.03 | `ej03_linea_completa.txt` | Movimientos `0 ^ $ g_` y `gg G {n}G` |
| 01.04 | `ej04_busqueda_f_y_slash.txt` | Saltos `f t F T ; ,` y búsqueda `/ ? n N *` |
| 01.05 | `ej05_movimiento_integrador.txt` | Mezcla de todo lo anterior con marcadores-testigo |

> **Antes de empezar:** revisa el Nivel 00 si aún dudas en qué modo estás cuando abres `nvim`.
> Todos los ejercicios de este nivel se trabajan **sin entrar nunca en modo Insert** (excepto donde el TODO lo pida explícitamente).
