# EJERCICIO 10.04 — grug-far: search & replace en proyecto

> Teoría:   teoria/10_Refactor_Mini_Conform_GrugFar.md (sección 7)
> Verifica: bash scripts/verificar.sh 10 04

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel10_refactor_mini/ej04_grug_far.md
```

## TODOs

**TODO 1 (con pista):** Pulsa `<leader>sr` (Search/Replace) para abrir grug-far. Verás los campos Search / Replace / Filter / Flags. Sustituye `GRUG_ABIERTO` (más abajo) por `OK`. Pulsa `<leader>q` para cerrarlo SIN aplicar.

**TODO 2 (con pista):** Abre grug-far de nuevo. En Search, escribe `EXPERIMENTO_GRUG`. NO ejecutes el replace todavía. Verás resultados (aparecen en este mismo archivo, líneas más abajo). Cierra el panel con `<leader>q`. Sustituye `GRUG_BUSCO` por `OK`.

**TODO 3 (LIBRE):** En este archivo, hay 3 ocurrencias de `EXPERIMENTO_GRUG` en el bloque GRUG_TEST. Cámbialas TODAS a `OK_GRUG` usando grug-far o `:%s/EXPERIMENTO_GRUG/OK_GRUG/g` (es lo mismo en alcance, grug-far brilla más en proyectos grandes).

**TODO 4 (LIBRE):** Guarda y sal.

---

GRUG
abierto: GRUG_ABIERTO
busco: GRUG_BUSCO

GRUG_TEST
linea1 EXPERIMENTO_GRUG aquí
linea2 EXPERIMENTO_GRUG aquí
linea3 EXPERIMENTO_GRUG aquí
