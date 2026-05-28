# EJERCICIO 11.02 — gitsigns: stage/unstage de hunks

> Teoría:   teoria/11_Git_Gitsigns_LazyGit_Trouble.md (sección 2)
> Verifica: bash scripts/verificar.sh 11 02

## TODOs

**TODO 1 (con pista):** Modifica una línea de este archivo (cualquiera).
Por ejemplo cambia el texto "ORIGINAL_STAGE" por "STAGED". Verás el `~` en
el margen.

**TODO 2 (con pista):** Pulsa `<leader>ghs` para STAGEAR ese hunk. El símbolo
cambia (deja de ser `~` claramente). Comprueba con `:!git status` que el
archivo aparece como "modified" o "staged for commit". Sustituye
`STAGED_OK` por `OK`.

**TODO 3 (con pista):** Pulsa `<leader>ghu` para DES-STAGEAR. Sustituye
`UNSTAGED_OK` por `OK`.

**TODO 4 (LIBRE):** Modifica DOS líneas separadas del archivo (los símbolos
"BLOQUE_A" y "BLOQUE_B" — cambia el sufijo a "OK"). Stagea SOLO BLOQUE_A
con `<leader>ghs`. Comprueba con `:!git diff` que solo aparece el cambio
de BLOQUE_B (porque A ya está staged y `git diff` muestra solo unstaged).

**TODO 5 (LIBRE):** Guarda y sal sin commitear nada.

---

ORIGINAL_STAGE
BLOQUE_A_ORIGINAL
BLOQUE_B_ORIGINAL

REGISTROS
staged: STAGED_OK
unstaged: UNSTAGED_OK
