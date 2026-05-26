# EJERCICIO 07.01 — Explorar el universo con which-key

> Teoría:   teoria/07_WhichKey_Picker_Flash.md (sección 2)
> Verifica: bash scripts/verificar.sh 07 01

## Objetivo

Familiarizarte con los SUBMENÚS principales que which-key te ofrece. No memorices: explora.

## Procedimiento

Abre este archivo en nvim:

```bash
nvim ejercicios/nivel07_whichkey_picker_flash/ej01_whichkey_explora.md
```

A continuación cada TODO te pide pulsar `<Space>` + algo y ANOTAR qué viste. Para anotar, abandona el menú con `<Esc>`, navega a la línea del registro correspondiente, edita el valor.

## TODOs

**TODO 1 (con pista):** Pulsa `<Space>` y espera 1 segundo. Cuenta CATEGORÍAS (líneas con `+` o sub-grupos) que ves en el popup. Sustituye `CATEGORIAS_RAIZ` por ese número aproximado (la solución usa `12`). Pulsa `<Esc>`.

**TODO 2 (con pista):** Pulsa `<Space>` + `b` (Buffer). Identifica el comando para "Delete Buffer" — anota la TECLA exacta. Sustituye `WK_BUFFER_DELETE` por esa tecla (la solución usa `d`).

**TODO 3 (con pista):** Pulsa `<Esc>`, luego `<Space>` + `c` (Code). Identifica el atajo para "Rename" (símbolo). Sustituye `WK_CODE_RENAME` por esa tecla (la solución usa `r`).

**TODO 4 (LIBRE):** Explora `<Space>` + `g` (Git). Identifica el atajo para abrir LazyGit. Sustituye `WK_GIT_LAZYGIT` por la tecla.

**TODO 5 (LIBRE):** Pulsa `<Space>` + `?`. Aparece una lista enorme de keymaps activos. NO los memorices — solo confirma que la ves. Sustituye `WK_KEYMAPS_LIST` por `OK`.

**TODO 6 (LIBRE):** Guarda y sal.

---

WHICHKEY_EXPLORE
categorias_raiz: 12
buffer_delete: d
code_rename: r
git_lazygit: g
keymaps_list: OK
