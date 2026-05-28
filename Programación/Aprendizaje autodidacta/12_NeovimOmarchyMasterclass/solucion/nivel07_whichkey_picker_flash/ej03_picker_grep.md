# EJERCICIO 07.03 — Grep global con picker (live grep)

> Teoría:   teoria/07_WhichKey_Picker_Flash.md (secciones 3, 4)
> Verifica: bash scripts/verificar.sh 07 03

## Objetivo

Encontrar TEXTO en cualquier archivo del proyecto en segundos.

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel07_whichkey_picker_flash/ej03_picker_grep.md
```

## TODOs

**TODO 1 (con pista):** Pulsa `<Space>` + `/` (grep live). Escribe `verificar.sh` y observa cuántas líneas del proyecto contienen esa cadena (aparece en cabeceras de ejercicios). Pulsa `<Esc>`. Sustituye `GREP_VERIFICAR_SH` por `OK`.

**TODO 2 (con pista):** Pulsa `<Space>` + `/`. Escribe `TODO 1 (con pista)`. Aparecen MUCHAS líneas (es la marca de cada ejercicio). Sustituye `GREP_TODOS` por `OK`.

**TODO 3 (con pista):** Pulsa `<Space>` + `s` + `w` (Search Word — busca la palabra bajo el cursor). Antes ponte sobre la palabra "ejercicio" de este archivo. Verás resultados. Pulsa `<Esc>`. Sustituye `SEARCH_WORD` por `OK`.

**TODO 4 (LIBRE):** Pulsa `<Space>` + `/`. Busca la palabra `Mermaid` que aparece en archivos de teoría. Cuenta aproximadamente cuántas teorías la usan (la solución usa `7`). Sustituye `MERMAID_COUNT` por ese número.

**TODO 5 (LIBRE):** Pulsa `<Space>` + `s` + `r` (Resume) para repetir la última búsqueda. Confirma que vuelves al picker con los mismos resultados. Pulsa `<Esc>`. Sustituye `RESUME` por `OK`.

**TODO 6 (LIBRE):** Guarda y sal.

---

PICKER_GREP
grep_verificar: OK
grep_todos: OK
search_word: OK
mermaid_count: 7
resume: OK
