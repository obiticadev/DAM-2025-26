# EJERCICIO 04.01 — Buffers

> Teoría:   teoria/04_Buffers_Ventanas_Tabs.md (sección 2)
> Verifica: bash scripts/verificar.sh 04 01

## Cómo arrancar este ejercicio

Desde la raíz del bootcamp, abre los TRES archivos a la vez:

```bash
nvim ejercicios/nivel04_buffers_ventanas/ej01_buffers.md \
     ejercicios/nivel04_buffers_ventanas/ej01_buffers_extra1.txt \
     ejercicios/nivel04_buffers_ventanas/ej01_buffers_extra2.txt
```

Esto crea 3 buffers en memoria pero solo te muestra el primero.

## Chuleta

```
:ls         lista buffers
:b 2        salta al buffer 2
:bn :bp     siguiente / anterior buffer
Ctrl-^      buffer alterno (el último que visitaste)
:bd         descarga (cierra) el buffer actual
```

## TODOs

**TODO 1 (con pista):** Ejecuta `:ls` y verifica que ves 3 buffers en la lista. Identifica el número de cada uno.

**TODO 2 (con pista):** Salta al buffer `ej01_buffers_extra1.txt` con `:b extra1<Tab>` (tab autocompleta) o `:b 2`. Dentro, sustituye la palabra `RELLENO_1` por `OK1` (recuerda: `ciw`). Guarda con `:w`.

**TODO 3 (con pista):** Vuelve al buffer alterno con `Ctrl-^`. Deberías haber vuelto a este `ej01_buffers.md`.

**TODO 4 (LIBRE):** Salta a `ej01_buffers_extra2.txt` (usa `:bn` o `:b extra2<Tab>`). Sustituye `RELLENO_2` por `OK2`. Guarda con `:w`.

**TODO 5 (LIBRE):** En este mismo archivo, sustituye la palabra `MARK_ESTE` (más abajo) por `COMPLETADO`. Luego guarda TODOS los buffers con un solo comando: `:wa`.

**TODO 6 (LIBRE):** Cierra TODO con `:qa`.

---

MARK_ESTE
