# EJERCICIO 04.03 — Tabs (tabpages, no pestañas de archivo)

> Teoría:   teoria/04_Buffers_Ventanas_Tabs.md (sección 4)
> Verifica: bash scripts/verificar.sh 04 03

## Cómo arrancar

```bash
nvim ejercicios/nivel04_buffers_ventanas/ej03_tabs.md
```

## Chuleta

```
:tabnew ruta    abre 'ruta' en una NUEVA tab
:tabc           cierra la tab actual
:tabo           cierra TODAS las demás tabs (only)
gt              siguiente tab           gT  tab anterior
{n}gt           salta a la tab número n
:tabs           lista de tabs
```

## TODOs

**TODO 1 (con pista):** Crea una segunda tab con `ej03_tab2.txt`.
Pista: `:tabnew ejercicios/nivel04_buffers_ventanas/ej03_tab2.txt`.

**TODO 2 (con pista):** En la nueva tab (estás ya en ella), sustituye `RELLENO_TAB2` por `OK_TAB2`. Guarda con `:w`.

**TODO 3 (con pista):** Vuelve a la tab anterior con `gT` (mayúscula). Confirma que estás en `ej03_tabs.md`.

**TODO 4 (LIBRE):** Crea una tercera tab con `ej03_tab3.txt`. En ella, sustituye `RELLENO_TAB3` por `OK_TAB3`. Guarda.

**TODO 5 (LIBRE):** Salta directamente a la tab 1 con `1gt`. Sustituye `MARK_TAB1` (más abajo) por `LISTO_TAB1`. Guarda todos los buffers con `:wa`.

**TODO 6 (LIBRE):** Cierra todas las tabs con `:tabo` (deja solo la 1) y luego sal con `:qa`.

---

MARK_TAB1
