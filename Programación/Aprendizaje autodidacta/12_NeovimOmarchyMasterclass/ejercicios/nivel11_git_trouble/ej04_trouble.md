# EJERCICIO 11.04 — trouble.nvim: panel de diagnostics

> Teoría:   teoria/11_Git_Gitsigns_LazyGit_Trouble.md (sección 4)
> Verifica: bash scripts/verificar.sh 11 04

## Procedimiento

Para tener algo que mostrar en trouble, abre un archivo de Lua con errores:

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel09_lsp_treesitter_cmp/ej03_lsp_basico.lua
```

## TODOs

**TODO 1 (con pista):** Con `ej03_lsp_basico.lua` abierto (en su estado ORIGINAL, con el error de `undefinedaVariable`), pulsa `<leader>xX` (Trouble buffer diagnostics). Se abre panel con los errores del buffer. Sustituye `TROUBLE_BUF` por `OK`.

**TODO 2 (con pista):** Pulsa `<leader>xx` (Trouble workspace diagnostics). Más amplio: todos los errores del workspace. Pulsa `q` para cerrar. Sustituye `TROUBLE_WS` por `OK`.

**TODO 3 (con pista):** Pulsa `<leader>xt` (Trouble TODO list). Verás todos los `TODO:` del proyecto. Sustituye `TROUBLE_TODO` por `OK`.

**TODO 4 (LIBRE):** Abre `ej04_trouble.md` (este archivo) — ya estás en él. Edita REGISTROS y guarda. Sustituye `EDITADO` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

REGISTROS
buf: TROUBLE_BUF
ws: TROUBLE_WS
todo: TROUBLE_TODO
editado: EDITADO
