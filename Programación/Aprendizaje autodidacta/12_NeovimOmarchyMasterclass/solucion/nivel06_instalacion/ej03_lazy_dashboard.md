# EJERCICIO 06.03 — Dashboard de Lazy.nvim (gestor de plugins)

> Teoría:   teoria/06_Instalacion_Omarchy_LazyVim.md (sección 5)
> Verifica: bash scripts/verificar.sh 06 03

## Cómo arrancar

```bash
nvim ejercicios/nivel06_instalacion/ej03_lazy_dashboard.md
```

## Chuleta — dashboard de :Lazy

Una vez ejecutes `:Lazy` dentro de nvim, te aparece un panel flotante con:

```
H — Help (ayuda del propio panel)
I — Install (instalar pendientes)
U — Update (actualizar todos)
S — Sync (Install + Update + Clean en una pasada)
C — Clean (borra plugins que ya no están en tu config)
X — Clear (limpia tareas en curso)
L — Log (ver log)
?  — toggle keymaps del panel

Para cerrar el panel: q
```

## TODOs

**TODO 1 (con pista):** Abre nvim y ejecuta `:Lazy`. Verás el panel.
- Pulsa `?` para ver los keymaps.
- Pulsa `q` para cerrar.

**TODO 2 (con pista):** Cuenta cuántos plugins están instalados. En el panel, el número aparece arriba (algo como `Loaded 35/40`). Sustituye `NUM_LOADED` por ese número total (ej: `40`). Si no sabes cuál es exactamente en tu config, escribe `40` (es el valor de la solución).

**TODO 3 (con pista):** Ejecuta `:Lazy sync` desde la línea de comandos directamente (sin abrir el panel). Espera a que termine. Sustituye `SYNC_STATE` por `OK`.

**TODO 4 (LIBRE):** En el dashboard de `:Lazy`, navega con `j/k` por la lista de plugins. Posiciónate sobre `which-key.nvim` y pulsa `<Enter>` — se abre detalle del plugin. Pulsa `q` para volver. Si pudiste verlo, sustituye `EXPLORADO_WK` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

DASHBOARD
plugins_loaded: 40
sync: OK
which_key_explorado: OK
