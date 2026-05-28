# EJERCICIO 08.01 — Toggle del explorer y navegación

> Teoría:   teoria/08_File_Explorer_Y_Buffers.md (sección 2)
> Verifica: bash scripts/verificar.sh 08 01

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel08_explorer_buffers/ej01_explorer_toggle.md
```

## TODOs

**TODO 1 (con pista):** Pulsa `<leader>e` para abrir el explorer (toggle). Aparece panel lateral con el árbol del proyecto. Pulsa `<leader>e` otra vez para cerrarlo. Sustituye `TOGGLE` por `OK`.

**TODO 2 (con pista):** Vuelve a abrir el explorer con `<leader>e`. Navega con `j/k` hasta la carpeta `teoria/`. Pulsa `l` (o `Enter`) para expandir. Cuenta cuántos archivos `.md` ves dentro. Sustituye `TEORIA_FILES` por ese número (la solución usa `9` — del 00 al 08 ya generados).

**TODO 3 (con pista):** Sigue dentro del explorer. Navega hasta `teoria/00_Por_Que_Vim_Y_Omarchy.md` y pulsa `Enter` para abrirlo. Después vuelve a este archivo con `Ctrl-^`. Sustituye `ABRI_TEORIA00` por `OK`.

**TODO 4 (LIBRE):** Abre el explorer y, sin moverte de carpeta, pulsa `y` sobre el archivo `INSTALL_BY_OS.md`. Esto copia su path al registro. Cierra el explorer. Sustituye `YANK_PATH` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

EXPLORER_01
toggle: TOGGLE
teoria_files_count: TEORIA_FILES
abri_teoria00: ABRI_TEORIA00
yank_path: YANK_PATH
