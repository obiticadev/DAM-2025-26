# EJERCICIO 11.05 — Integrador Git: workflow real

> Teoría:   teoria/11_Git_Gitsigns_LazyGit_Trouble.md (TODA)
> Verifica: bash scripts/verificar.sh 11 05

## Escenario

Has trabajado en 2 archivos y quieres hacer 2 commits separados, uno por archivo.

## TODOs

**TODO 1 (con pista):** Modifica este archivo y `ej05_secundario.txt`:
- En este `.md`: cambia `EJ05_PRIMARIO_PENDIENTE` por `EJ05_PRIMARIO_OK`.
- En `ej05_secundario.txt`: cambia `EJ05_SEC_PENDIENTE` por `EJ05_SEC_OK`.
- Guarda ambos (`:wa`).

**TODO 2 (con pista):** Pulsa `<leader>gg` (lazygit). Stagea SOLO `ej05_secundario.txt` con `<Space>`. Pulsa `c` y commit con mensaje `practica: 11.05 sec`. Sustituye `COMMIT_1` por `OK`.

**TODO 3 (LIBRE):** En lazygit, stagea ahora `ej05_integrador_git.md` (este archivo). Commit con mensaje `practica: 11.05 main`. Sustituye `COMMIT_2` por `OK`.

**TODO 4 (LIBRE):** Sal de lazygit con `q`. Comprueba con `:!git log --oneline -5` que tienes los 2 commits nuevos. Sustituye `VERIFICADO` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

REGISTROS
estado_primario: EJ05_PRIMARIO_OK
commit_sec: OK
commit_main: OK
verificado: OK
