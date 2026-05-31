# EJERCICIO 11.01 — gitsigns básico

> Teoría:   teoria/11_Git_Gitsigns_LazyGit_Trouble.md (sección 2)
> Verifica: bash scripts/verificar.sh 11 01

## Pre-requisito

Estás en un repo git. Si no, hazlo:
```bash
cd "01_NeovimOmarchyMasterclass/../.."   # o donde quieras
git init && git add . && git commit -m "init"
```

## Procedimiento

Abre nvim con este archivo. Después haz un cambio CUALQUIERA en él (ej: añade una línea en blanco). Verás un símbolo `~` o `+` en el margen izquierdo.

## TODOs

**TODO 1 (con pista):** Modifica este archivo: añade una línea con el texto `LINEA_NUEVA_GITSIGNS` justo encima del bloque REGISTROS. Verás que en el margen izquierdo aparece un signo (típicamente `+` para línea añadida).

**TODO 2 (con pista):** Pulsa `]h` para saltar al siguiente hunk (cambio). Estás sobre el cambio que acabas de hacer. Sustituye `JUMP_HUNK` por `OK`.

**TODO 3 (con pista):** Pulsa `<leader>ghp` (preview hunk). Aparece popup con el diff. Pulsa `q` para cerrar. Sustituye `PREVIEW_HUNK` por `OK`.

**TODO 4 (LIBRE):** Pulsa `<leader>ghb` (blame line) sobre cualquier línea ANTIGUA del archivo (que no hayas modificado). Verás info de blame (autor, commit, fecha). Sustituye `BLAME` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

REGISTROS
jump_hunk: JUMP_HUNK
preview_hunk: PREVIEW_HUNK
blame: BLAME
