# EJERCICIO 08.02 — CRUD de archivos desde el explorer

> Teoría:   teoria/08_File_Explorer_Y_Buffers.md (sección 2)
> Verifica: bash scripts/verificar.sh 08 02

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel08_explorer_buffers/ej02_explorer_crud.md
```

## TODOs

**TODO 1 (con pista):** Abre el explorer con `<leader>e`. Navega hasta la carpeta `ejercicios/nivel08_explorer_buffers/`. Pulsa `a` para crear nuevo archivo. Escribe `prueba_crud.txt` y pulsa Enter. El archivo se crea y se abre. Sustituye `CREADO` por `OK`.

**TODO 2 (con pista):** Estás dentro de `prueba_crud.txt`. Escribe (en Insert) la línea exacta: `Este archivo fue creado desde el explorer.` Guarda con `:w`.

**TODO 3 (con pista):** Vuelve al explorer. Posiciónate sobre `prueba_crud.txt`. Pulsa `r` para renombrar. Cámbiale el nombre a `renombrado_ok.txt`. Sustituye `RENOMBRADO` por `OK`.

**TODO 4 (LIBRE):** Vuelve al explorer. Posiciónate sobre `renombrado_ok.txt`. Pulsa `d` para borrarlo. Confirma. Sustituye `BORRADO` por `OK`. (Si tu explorer no permite borrar archivos no rastreados por git, hazlo desde terminal: `rm ejercicios/nivel08_explorer_buffers/renombrado_ok.txt`.)

**TODO 5 (LIBRE):** Vuelve a este archivo. Guarda y sal.

> Nota: el verificador NO comprueba que crearas/borraras el archivo (lo comprueba tu sistema). Verifica solo este archivo de registro.

---

EXPLORER_CRUD
crear_prueba: CREADO
renombrar_ok: RENOMBRADO
borrar_ok: BORRADO
