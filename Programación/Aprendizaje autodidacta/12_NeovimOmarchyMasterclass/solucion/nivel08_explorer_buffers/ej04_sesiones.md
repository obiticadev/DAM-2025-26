# EJERCICIO 08.04 — Sesiones (persistir entre cierres)

> Teoría:   teoria/08_File_Explorer_Y_Buffers.md (sección 4)
> Verifica: bash scripts/verificar.sh 08 04

## Procedimiento

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel08_explorer_buffers/ej04_sesiones.md
```

## TODOs

**TODO 1 (con pista):** Sin cerrar este archivo, abre dos más para tener un layout interesante:
- `<Space><Space>` + `INSTALL_BY_OS` + Enter
- `<Space><Space>` + `implementation_plan` + Enter

Confirma con `:ls` que tienes 3 buffers. Sustituye `SETUP_LAYOUT` por `OK`.

**TODO 2 (con pista):** Sal de nvim guardando todo con `:wqa`.

**TODO 3 (con pista):** Vuelve a abrir nvim desde el MISMO directorio:
```bash
cd "01_NeovimOmarchyMasterclass"
nvim
```
Pulsa `<leader>qs` (restaurar sesión del cwd). Deberían reaparecer los 3 buffers que dejaste abiertos. Sustituye `RESTAURADA` por `OK`. (Si tu config no tiene persistencia configurada por defecto, sustituye por `NO_DISPONIBLE` y avanza.)

**TODO 4 (LIBRE):** Vuelve a abrir este archivo de ejercicio (probablemente ya está abierto si la sesión cargó). Sustituye `VOLVI_A_EJ04` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal con `:wqa`.

---

SESIONES
setup_layout: OK
sesion_restaurada: OK
volvi_ej04: OK
