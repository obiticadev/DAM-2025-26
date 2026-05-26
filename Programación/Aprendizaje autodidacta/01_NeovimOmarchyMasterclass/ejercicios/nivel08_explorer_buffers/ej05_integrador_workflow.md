# EJERCICIO 08.05 — Integrador: workflow real con explorer + buffers + picker

> Teoría:   teoria/08_File_Explorer_Y_Buffers.md (TODA)
> Verifica: bash scripts/verificar.sh 08 05

## Escenario simulado

Acabas de clonar el proyecto y quieres explorarlo eficientemente.

```bash
cd "01_NeovimOmarchyMasterclass"
nvim
```

## TODOs

**TODO 1 (con pista):** Sin abrir archivos al arrancar, pulsa `<leader>e` para ver la estructura. Cuenta cuántas CARPETAS de primer nivel hay (no subcarpetas). Sustituye `CARPETAS_TOP` por ese número (la solución usa `5` — teoria, ejercicios, solucion, scripts, y el archivo `README_GUIA_TERMINAL.md` no cuenta).

**TODO 2 (con pista):** Cierra el explorer con `<leader>e`. Abre este archivo con `<Space><Space>` + `integrador_workflow` + Enter. Sustituye `ABRI_CON_PICKER` por `OK`.

**TODO 3 (con pista):** Abre los archivos `teoria/06_*.md`, `teoria/07_*.md`, `teoria/08_*.md` con `<Space><Space>` (uno tras otro). Confirma con `:ls` que tienes 4 buffers. Sustituye `BUFFERS_4` por `OK`.

**TODO 4 (LIBRE):** Navega entre los 4 buffers con `<S-l>` y `<S-h>`. En cada uno, pulsa `gg` para asegurarte de que ves el inicio. Vuelve a este archivo. Sustituye `NAVEGUE` por `OK`.

**TODO 5 (LIBRE):** Cierra todos los buffers menos este con `<leader>bo`. Confirma con `:ls` que solo queda 1 buffer (este). Sustituye `LIMPIE_BUFFERS` por `OK`.

**TODO 6 (LIBRE):** Guarda y sal con `:wq`.

---

INTEGRADOR_08
carpetas_top: CARPETAS_TOP
abri_picker: ABRI_CON_PICKER
buffers_4: BUFFERS_4
navegue: NAVEGUE
limpie: LIMPIE_BUFFERS
