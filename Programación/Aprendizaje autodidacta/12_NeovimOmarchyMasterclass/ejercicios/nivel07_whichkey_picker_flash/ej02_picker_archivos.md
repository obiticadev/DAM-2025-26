# EJERCICIO 07.02 — Picker de archivos (snacks.picker)

> Teoría:   teoria/07_WhichKey_Picker_Flash.md (secciones 3, 4)
> Verifica: bash scripts/verificar.sh 07 02

## Objetivo

Abrir archivos del proyecto bootcamp en segundos sin moverte por carpetas. Dejar de usar `:e ruta/larga/`.

## Procedimiento — TRABAJA DESDE LA RAÍZ DEL BOOTCAMP

Abre nvim en la carpeta del bootcamp (importante para que el picker indexe TODO el proyecto):

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel07_whichkey_picker_flash/ej02_picker_archivos.md
```

## TODOs

**TODO 1 (con pista):** Pulsa `<Space>` + `<Space>` (espacio dos veces). Se abre el picker fuzzy de archivos. Escribe `instaroma` (sin pulsar Enter). Deberías ver que se filtra `INSTALL_BY_OS.md`. Pulsa `<Enter>` para abrirlo, mírale las primeras líneas, y vuelve atrás con `Ctrl-^` (buffer alterno). Sustituye `PICKER_FUZZY` por `OK`.

**TODO 2 (con pista):** Pulsa `<Space>` + `f` + `r` (Recent files). Deberías ver una lista con los archivos abiertos recientemente — el INSTALL_BY_OS.md aparece. Pulsa `<Esc>` para cerrar. Sustituye `PICKER_RECENT` por `OK`.

**TODO 3 (con pista):** Pulsa `<Space>` + `f` + `f` (Find File). Escribe `ej01` para filtrar los ejercicios 01 de cualquier nivel. Cuenta aproximadamente cuántos resultados ves. Sustituye `PICKER_EJ01_COUNT` por ese número (la solución usa `8` — 1 por cada nivel del 00 al 07 que ya tiene ej01 creado).

**TODO 4 (LIBRE):** Pulsa `<Space>` + `f` + `b` (Buffers). Lista los buffers abiertos. Pulsa `<Esc>`. Sustituye `PICKER_BUFFERS` por `OK`.

**TODO 5 (LIBRE):** Vuelve a `<Space><Space>`, escribe `boss` para buscar archivos del boss final. Como aún no hemos generado el nivel 14, debería estar vacío (o aparecer la teoría 14 si la generamos). Pulsa `<Esc>`. Sustituye `BOSS_BUSCADO` por `OK`.

**TODO 6 (LIBRE):** Guarda y sal con `:wqa`.

---

PICKER_FILES
fuzzy_space_space: PICKER_FUZZY
recent: PICKER_RECENT
ej01_resultados: PICKER_EJ01_COUNT
buffers: PICKER_BUFFERS
boss: BOSS_BUSCADO
