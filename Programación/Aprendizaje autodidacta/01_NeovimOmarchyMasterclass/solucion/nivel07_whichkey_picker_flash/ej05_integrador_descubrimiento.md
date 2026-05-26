# EJERCICIO 07.05 — Integrador: descubrimiento + picker + flash

> Teoría:   teoria/07_WhichKey_Picker_Flash.md (TODA)
> Verifica: bash scripts/verificar.sh 07 05

## Workflow real

Vas a simular un "día normal de trabajo" combinando los tres plugins.

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel07_whichkey_picker_flash/ej05_integrador_descubrimiento.md
```

## TODOs

**TODO 1 (con pista):** Sin cerrar este archivo, abre `INSTALL_BY_OS.md` con `<Space>` + `<Space>` y `instaroma` + Enter. Léelo brevemente.

**TODO 2 (con pista):** Vuelve a este archivo con `Ctrl-^`. Sustituye `STEP1_DONE` por `OK`.

**TODO 3 (con pista):** Pulsa `<Space>` + `/` y busca la cadena `MARCO_AQUI` — verás que solo aparece en este archivo más abajo. Pulsa `<Enter>` en el resultado: el cursor va directamente a esa línea. Sustituye `MARCO_AQUI` (que está más abajo) por `LLEGUE_GREP`.

**TODO 4 (LIBRE):** Pulsa `<Space>` + `?` para ver la lista de keymaps. Localiza el atajo para "Toggle Explorer" (Nivel 08, lo veremos pronto) y anótalo. Sustituye `TOGGLE_EXPLORER` por la tecla (la solución usa `e`, asumiendo `<leader>e`).

**TODO 5 (LIBRE):** Usa flash (`s`) para saltar a `MARCO_FLASH` más abajo. Cámbialo por `LLEGUE_FLASH` con `cw`.

**TODO 6 (LIBRE):** Cuenta cuántos buffers tienes abiertos (`<Space>` + `f` + `b` para listarlos). Sustituye `BUFFERS_ABIERTOS` por el número exacto (la solución usa `2`: este + INSTALL_BY_OS).

**TODO 7 (LIBRE):** Guarda y sal con `:wqa`.

---

INTEGRADOR_07
step1_recent: OK
marca_grep: LLEGUE_GREP
toggle_explorer_key: e
flash_objetivo: LLEGUE_FLASH
buffers_abiertos: 2
