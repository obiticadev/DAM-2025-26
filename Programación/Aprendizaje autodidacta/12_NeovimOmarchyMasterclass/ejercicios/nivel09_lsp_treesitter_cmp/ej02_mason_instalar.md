# EJERCICIO 09.02 — Mason: instalar servidores

> Teoría:   teoria/09_Treesitter_LSP_Mason_Completion.md (sección 4)
> Verifica: bash scripts/verificar.sh 09 02

## Procedimiento

```bash
nvim ejercicios/nivel09_lsp_treesitter_cmp/ej02_mason_instalar.md
```

Dentro de nvim, abre Mason con `:Mason`. Navega con `2`/`3`/`4`/`5` para cambiar de pestaña.

## TODOs

**TODO 1 (con pista):** En Mason, pestaña 2 (LSP), busca con `/lua_ls`. Si no está instalado, pulsa `i`. Espera al ✓. Sustituye `LUA_LS_OK` por `OK`.

**TODO 2 (con pista):** En la misma pestaña, busca `marksman` (LSP de Markdown). Instálalo con `i`. Espera ✓. Sustituye `MARKSMAN_OK` por `OK`.

**TODO 3 (con pista):** Pestaña 5 (Formatter), busca `stylua` (formatter de Lua). Instala con `i`. Sustituye `STYLUA_OK` por `OK`.

**TODO 4 (LIBRE):** Abre `:MasonLog`. Solo confirma que existe y se abre — pulsa `:q` para cerrarlo. Sustituye `MASON_LOG` por `OK`.

**TODO 5 (LIBRE):** Anota cuántos elementos tienes instalados en total (línea "X/Y installed" en el panel Mason). Sustituye `TOTAL` por ese número (la solución usa `5` — los del nivel 06 + estos 3).

**TODO 6 (LIBRE):** Guarda y sal.

---

MASON_INSTALL
lua_ls: LUA_LS_OK
marksman: MARKSMAN_OK
stylua: STYLUA_OK
log: MASON_LOG
total: TOTAL
