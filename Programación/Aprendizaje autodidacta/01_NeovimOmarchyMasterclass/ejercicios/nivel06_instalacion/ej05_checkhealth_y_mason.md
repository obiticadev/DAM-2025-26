# EJERCICIO 06.05 — :checkhealth y :Mason

> Teoría:   teoria/06_Instalacion_Omarchy_LazyVim.md (secciones 5, 7)
> Verifica: bash scripts/verificar.sh 06 05

## Objetivo

Saber diagnosticar y saber dónde se instalan los LSPs/formatters/linters/debuggers.

## Chuleta

Dentro de nvim:

```vim
:checkhealth            " informe completo (largo)
:checkhealth lazy       " salud del gestor de plugins
:checkhealth provider   " clipboard, node, python
:checkhealth treesitter " parsers

:Mason                  " abre el panel de Mason
                        " ?  — ayuda del panel
                        " i  — instalar elemento bajo cursor
                        " X  — desinstalar
                        " /  — buscar
                        " q  — cerrar
```

## TODOs

**TODO 1 (con pista):** Ejecuta `:checkhealth lazy`. NO debe haber errores rojos. Si los hay, soluciónalos antes. Sustituye `LAZY_HEALTH` por `OK`.

**TODO 2 (con pista):** Ejecuta `:checkhealth provider`. Mira en concreto las secciones `clipboard` y `node`:
- Si `clipboard` está OK → sustituye `CLIP_HEALTH` por `OK`.
- Si está roto (verás un mensaje sobre tener que instalar `xclip`/`win32yank`/etc.) → sustituye `CLIP_HEALTH` por `KO`.

**TODO 3 (con pista):** Abre `:Mason`. Pulsa `2` para ir a la pestaña LSP. Pulsa `/` y escribe `lua_ls` — busca el LSP de Lua. Si lo encuentras, pulsa `i` para instalarlo (te conviene tenerlo para el resto del bootcamp). Espera a que termine. Pulsa `q` para cerrar. Sustituye `MASON_LUA_LS` por `OK` cuando lua_ls aparezca con marca "✓ installed".

**TODO 4 (LIBRE):** Ejecuta `:Mason` y, en la pestaña principal (`1`), instala también `stylua` (formatter para Lua). Pulsa `/stylua`, encuéntralo, `i` para instalar. Cuando esté con ✓, sustituye `MASON_STYLUA` por `OK`.

**TODO 5 (LIBRE):** Cuenta cuántos elementos tienes instalados en total (línea superior del panel Mason: "X / Y installed"). Sustituye `MASON_TOTAL` por ese número. (La solución usa `2` asumiendo que solo instalaste los dos del ejercicio.)

**TODO 6 (LIBRE):** Guarda y sal.

---

CHECKHEALTH_MASON
lazy_health: LAZY_HEALTH
clipboard_health: CLIP_HEALTH
mason_lua_ls: MASON_LUA_LS
mason_stylua: MASON_STYLUA
mason_total_instalados: MASON_TOTAL
