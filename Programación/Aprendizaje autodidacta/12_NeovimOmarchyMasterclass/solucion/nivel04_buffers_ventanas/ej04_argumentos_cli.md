# EJERCICIO 04.04 — Argumentos de línea de comandos

> Teoría:   teoria/04_Buffers_Ventanas_Tabs.md (sección 5)
> Verifica: bash scripts/verificar.sh 04 04

## Cómo arrancar

Este ejercicio se centra en arrancar nvim con DIFERENTES argumentos.
Hazlo en orden con cada arranque:

```bash
# Arranque 1 — abre los 3 archivos como buffers (solo ves el primero)
nvim ejercicios/nivel04_buffers_ventanas/ej04_*.txt
# Dentro: :ls debe mostrar 3 buffers.

# Arranque 2 — split horizontal de los 3 (lado a lado verticalmente)
nvim -o ejercicios/nivel04_buffers_ventanas/ej04_*.txt
# Verás los 3 en splits horizontales.

# Arranque 3 — split vertical
nvim -O ejercicios/nivel04_buffers_ventanas/ej04_*.txt

# Arranque 4 — uno por tab
nvim -p ejercicios/nivel04_buffers_ventanas/ej04_*.txt

# Arranque 5 — vamos directos a la línea 5 del primero
nvim +5 ejercicios/nivel04_buffers_ventanas/ej04_a.txt
```

## TODOs

**TODO 1 (con pista):** Tras hacer el "Arranque 4" (`-p`), estarás con 3 tabs.
- En la tab 1 (ej04_a.txt): sustituye `RELLENO_A` por `OK_A`. Guarda.
- En la tab 2 (ej04_b.txt): sustituye `RELLENO_B` por `OK_B`. Guarda.
- En la tab 3 (ej04_c.txt): sustituye `RELLENO_C` por `OK_C`. Guarda.
- Sal con `:qa`.

**TODO 2 (LIBRE):** Abre este archivo y comprueba qué hacen los demás
arranques (`-o`, `-O`, `+N`). No cambian este `.md`, son experimentación.

**TODO 3 (LIBRE):** En ESTE archivo (este .md), reemplaza la palabra `MARK_CLI` (más abajo) por `LISTO_CLI`. Guarda y sal.

---

LISTO_CLI
