# 📘 Nivel 05 — Comandos Ex, macros y marks

---

## 1. ¿Qué son los "comandos Ex"?

Todo lo que empieza con `:` es un **comando Ex** (de su antepasado `ex`, el editor de línea pre-`vi`). Estos comandos operan sobre **rangos de líneas** y son la herramienta más potente de Vim para cambios masivos.

```
:[rango]comando[!]   [argumentos]
```

> **La clave mental:** los comandos Ex no necesitan que muevas el cursor. Aplican una operación a un RANGO de líneas declarado al inicio. Por eso son ideales para "haz X en todas las líneas que…".

---

## 2. Sustitución — `:s` el comando que más usarás

Sintaxis:

```vim
:[rango]s/buscar/reemplazar/[flags]
```

| Flag | Efecto |
|---|---|
| `g` | global en la línea (no solo la primera coincidencia) |
| `c` | confirma cada cambio (`y`/`n`/`a`/`q`/`l`) |
| `i` | ignora mayúsculas |
| `I` | sensible a mayúsculas (anula `ignorecase`) |
| `e` | no muestra error si no encuentra |
| `n` | NO sustituye, solo CUENTA |

Ejemplos canónicos:

```vim
:s/foo/bar/             reemplaza la primera 'foo' de la línea actual
:s/foo/bar/g            reemplaza TODAS las 'foo' de la línea actual
:%s/foo/bar/g           reemplaza TODAS las 'foo' del archivo
:%s/foo/bar/gc          igual pero con confirmación
:%s/\<foo\>/bar/g       solo palabras completas 'foo'
:%s/foo/bar/gi          ignora mayúsculas
:5,15s/foo/bar/g        solo entre las líneas 5 y 15
:.,$s/foo/bar/g         desde la actual hasta el final
:'a,'bs/foo/bar/g       entre la mark 'a y la mark 'b
```

> **Para el examen mental:** `%` = todo el archivo. `.` = línea actual. `$` = última línea. `'a` = línea de mark a. Estos rangos valen para CUALQUIER comando Ex (`:%d`, `:%y`, `:%norm`, `:%g/`...).

> **Truco:** `:&` repite la última sustitución en la línea actual. `:&&` repite con los mismos flags.

---

## 3. Rangos — la sintaxis maestra

| Rango | Significado |
|---|---|
| `:` (vacío) | línea actual |
| `:.` | línea actual (explícito) |
| `:$` | última línea |
| `:%` | todo el archivo (= `:1,$`) |
| `:5` | línea 5 |
| `:5,10` | líneas 5 a 10 |
| `:.,+3` | actual y las 3 siguientes |
| `:.,-3` | actual y las 3 anteriores |
| `:'a,'b` | desde mark `a` hasta mark `b` |
| `:'<,'>` | desde inicio hasta fin de la última selección visual |
| `:g/patrón/` | TODAS las líneas que coinciden con `patrón` |
| `:v/patrón/` | todas las líneas que NO coinciden (igual que `:g!/patrón/`) |

---

## 4. El comando global `:g` — operación masiva por patrón

`:g/patrón/comando` ejecuta `comando` sobre cada línea que matchea `patrón`. Es brutalmente potente:

```vim
:g/TODO/d                 borra TODAS las líneas que contienen TODO
:g/^$/d                   borra todas las líneas EN BLANCO
:g/^/move 0               invierte el orden del archivo (cada línea al inicio)
:g/error/y A              copia (append) todas las líneas con 'error' al registro 'a'
:v/keep/d                 borra todas las líneas que NO contienen 'keep'
```

> **Truco profesional:** para "extraer todas las líneas de log con ERROR a un buffer nuevo":
> ```vim
> :let @a=''                              limpia registro 'a'
> :g/ERROR/y A                            añade cada línea al registro 'a'
> :new                                    nuevo buffer
> "ap                                     pega
> ```

---

## 5. Macros — grabar y reproducir una secuencia

Una macro es **una secuencia de teclas grabada** que puedes reproducir. Se guardan en registros (los mismos `a-z`).

```vim
qa                      empieza a grabar en el registro 'a'
…secuencia de teclas…
q                       deja de grabar
@a                      reproduce la macro 'a' UNA vez
@@                      reproduce la ÚLTIMA macro ejecutada
5@a                     reproduce la macro 'a' CINCO veces
:reg a                  ver el contenido de la macro 'a'
```

> **Workflow canónico:**
> 1. Sitúate en la primera línea donde quieres aplicar el cambio.
> 2. `qa` — empieza a grabar.
> 3. Haz el cambio EXACTO (incluyendo bajar a la siguiente línea al final).
> 4. `q` — para de grabar.
> 5. `100@a` — aplica 100 veces (o las que necesites).

> **La regla de oro:** termina la macro CON UN MOVIMIENTO que la deje preparada para la siguiente iteración (ej: `j0` al final = baja una línea y va al inicio). Si no, la repetición se atasca.

### Ejemplo: añadir `;` al final de N líneas

```
1. Posiciónate en la primera línea sin ';'
2. qa          (empieza a grabar en 'a')
3. A;          (Append al fin de línea + escribe ';' )
4. Esc         (sales de Insert)
5. j           (bajas una línea — IMPORTANTE)
6. q           (para de grabar)
7. 10@a        (repite 10 veces)
```

---

## 6. Marks — marcadores de posición

Un mark guarda una posición (línea + columna) en un registro.

| Comando | Qué hace |
|---|---|
| `ma` | crea mark `a` en la posición actual |
| `mA` | crea mark `A` GLOBAL (persiste entre archivos, incluso entre sesiones) |
| `'a` | salta a la LÍNEA de la mark `a` (al primer no-blanco) |
| `` `a `` | salta a la POSICIÓN exacta de la mark `a` (línea + columna) |
| `:marks` | lista todas las marks activas |
| `:delmarks a b c` | borra las marks a, b, c |
| `:delmarks!` | borra TODAS las marks de a-z del buffer |

### Marks especiales automáticas

| Mark | Significado |
|---|---|
| `''` (dos comillas) | última posición ANTES del último salto |
| `'.` | línea del último cambio |
| `'^` | última posición de inserción |
| `'[` `']` | inicio/fin del último cambio o yank |
| `'<` `'>` | inicio/fin de la última selección Visual |
| `'0`–`'9` | últimos 10 archivos visitados (al abrir Vim) |

> **Para el examen:** después de un salto grande (ej: `gg` o `/patrón`), `` `` `` (dos backticks) te lleva DE VUELTA a donde estabas. Es el undo del cursor.

---

## 7. Comando `:norm` — ejecuta teclas Normal en un rango

`:norm` (`:normal`) ejecuta una secuencia de teclas como si las pulsaras en modo Normal, en cada línea del rango:

```vim
:5,15norm A;        añade ';' al final de cada línea del rango
:%norm I// <Esc>    pre-fija '// ' al INICIO no-blanco de cada línea
:'<,'>norm dw       borra la primera palabra de cada línea seleccionada
```

> **Truco:** `:norm` + un macro `@a` reproduce la macro en un rango (`:5,15norm @a`).

---

## 8. `:read` y `:write` — leer y escribir partes

```vim
:r archivo            inserta el contenido de 'archivo' debajo de la línea actual
:r !comando           inserta la SALIDA de 'comando' (ej: :r !date)
:5,15w nuevo.txt      escribe las líneas 5-15 a 'nuevo.txt'
:'<,'>w archivo       escribe la selección Visual a 'archivo'
:w >> archivo         APPEND a un archivo existente
```

---

## 9. Diagrama mental del Nivel 05

```mermaid
flowchart TD
    A[Operación masiva] --> B{¿De qué tipo?}
    B -->|Reemplazar texto| C[:%s/foo/bar/g]
    B -->|Borrar líneas que matchean| D[:g/patrón/d]
    B -->|Conservar solo líneas que matchean| E[:v/patrón/d]
    B -->|Aplicar comando Normal a rango| F[:5,15norm A;]
    B -->|Repetir secuencia compleja| G[Macros: qa…q, @a, 5@a]
    
    H[Quiero volver a un sitio luego] --> I[ma — luego 'a o `a]
    J[Quiero volver al sitio anterior al salto] --> K[`` (dos backticks)]
```

---

## 10. Patrones canónicos a memorizar

| Quiero… | Comando |
|---|---|
| Reemplazar todo en el archivo | `:%s/foo/bar/g` |
| Reemplazar con confirmación | `:%s/foo/bar/gc` |
| Solo palabras completas | `:%s/\<foo\>/bar/g` |
| Borrar líneas vacías | `:g/^$/d` |
| Borrar líneas con TODO | `:g/TODO/d` |
| Numerar las líneas | `:%s/^/\=line(".").". "/` |
| Pasar todo el archivo a MAYÚSCULAS | `gg gU G` (combinación Normal) o `:%norm gUU` |
| Aplicar macro 'a' a líneas 1-100 | `:1,100norm @a` |
| Marcar este sitio para volver luego | `ma` |
| Volver al sitio antes del último salto | `` `` `` |

---

## Referencia de Ejercicios

| Ejercicio | Archivo | Concepto |
|---|---|---|
| 05.01 | `ej01_sustitucion_basica.txt` | `:s/`, `:%s/`, flags `g i c` |
| 05.02 | `ej02_rangos_y_globales.txt` | Rangos `5,15`, `%`, `:g/`, `:v/` |
| 05.03 | `ej03_macros.txt` | `qa…q`, `@a`, `5@a`, `:norm` |
| 05.04 | `ej04_marks.txt` | `ma`, `'a`, `` `a ``, `:marks`, `` `` `` |
| 05.05 | `ej05_integrador_vim_puro.txt` | Mezcla todo Nivel 05 + recap niveles 00-04 |
