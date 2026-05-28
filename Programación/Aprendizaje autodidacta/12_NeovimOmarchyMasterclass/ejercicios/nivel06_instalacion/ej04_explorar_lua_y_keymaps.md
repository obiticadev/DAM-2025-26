# EJERCICIO 06.04 — Explorar lua/ y descubrir keymaps

> Teoría:   teoria/06_Instalacion_Omarchy_LazyVim.md (secciones 2, 6, 9)
> Verifica: bash scripts/verificar.sh 06 04

## Objetivo

Familiarizarte con la estructura de archivos de tu config y con los keymaps maestros.

## Comandos a ejecutar

Desde una terminal (bash):

```bash
# Listar archivos de TU config
ls -la ~/.config/nvim/                      # Linux/Mac/WSL
ls -la "$LOCALAPPDATA/nvim/"                # Windows

# Listar plugins que defines tú (overrides)
ls -la ~/.config/nvim/lua/plugins/

# Dónde viven los plugins instalados
ls ~/.local/share/nvim/lazy/                # Linux/Mac/WSL
ls "$LOCALAPPDATA/nvim-data/lazy/"          # Windows
```

Dentro de nvim:

```vim
:Telescope keymaps    " no, esto es de telescope antiguo
:WhichKey             " abre el menú which-key principal
                      " (alternativa: pulsa <Space> y espera)
```

## TODOs

**TODO 1 (con pista):** Abre nvim. Pulsa `<Space>` (la tecla leader) y ESPERA un segundo. Se abre el menú which-key con las categorías (b, c, f, g, q, s, u, w, x, ...). NO pulses nada más. Pulsa `<Esc>` para salir. Si lo viste bien, sustituye `MENU_WK_VISTO` por `OK`.

**TODO 2 (con pista):** Pulsa `<Space>` + `f` y espera. Verás el sub-menú "Files" con opciones (`f` Find File, `g` Grep, `r` Recent, etc.). Cuenta cuántas entradas hay en ese submenú. Sustituye `WK_F_COUNT` por ese número aproximado (la solución usa `10`).

**TODO 3 (con pista):** Pulsa `<Esc>`. Luego `<Space>` + `?` que es "Buffer Keymaps" — te abre la lista de TODOS los keymaps activos. Esta es tu referencia eterna. Pulsa `<Esc>` o `q` para salir. Sustituye `KEYMAPS_LISTADOS` por `OK`.

**TODO 4 (LIBRE):** Mira en tu disco cuántos archivos `.lua` hay en `lua/plugins/` de tu config (puedes hacerlo desde terminal o desde nvim con `:!ls lua/plugins/` si la cwd es tu config). Sustituye `NUM_PLUGINS_LUA` por el número (en una config LazyVim limpia, suele ser 1, en Omarchy puede haber 5-10).

**TODO 5 (LIBRE):** Guarda y sal.

---

EXPLORACION
menu_wk: MENU_WK_VISTO
wk_f_count: WK_F_COUNT
keymaps_listados: KEYMAPS_LISTADOS
num_plugin_lua_files: NUM_PLUGINS_LUA
