# EJERCICIO 10.05 — todo-comments: gestionar marcadores

> Teoría:   teoria/10_Refactor_Mini_Conform_GrugFar.md (sección 8)
> Verifica: bash scripts/verificar.sh 10 05

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel10_refactor_mini/ej05_todo_comments.md
```

## TODOs

**TODO 1 (con pista):** Pulsa `<leader>st` (Search TODOs) para listar TODOs del proyecto. Verás MUCHOS (todos los TODOs de los ejercicios). Pulsa `<Esc>`. Sustituye `LISTADO_TODOS` (más abajo) por `OK`.

**TODO 2 (con pista):** En este archivo, hay un bloque más abajo con varios marcadores: TODO, FIXME, HACK, NOTE. Usa `]t` para saltar al siguiente TODO. Pulsa `]t` varias veces y observa cómo el cursor salta entre marcadores. Sustituye `TODO_NAVEGACION` por `OK`.

**TODO 3 (LIBRE):** Marca CUÁNTOS marcadores hay en el bloque MARCADORES de abajo. Cuenta solo los que están dentro de ese bloque (no este TODO 3 ni el resto del archivo). Sustituye `MARCADOR_COUNT` por el número (la solución usa `4`).

**TODO 4 (LIBRE):** Guarda y sal.

---

REGISTROS
listado: LISTADO_TODOS
navegacion: TODO_NAVEGACION
count: MARCADOR_COUNT

MARCADORES
- TODO: implementar la función
- FIXME: este bug es crítico
- HACK: solución provisional, refactorizar
- NOTE: revisar con el equipo
FIN_MARCADORES
