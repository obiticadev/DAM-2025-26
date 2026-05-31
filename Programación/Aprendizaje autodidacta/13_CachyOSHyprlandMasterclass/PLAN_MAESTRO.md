# Plan Maestro — Masterclass CachyOS + Hyprland

Documento vivo. Refleja el plan completo del bootcamp y el estado de
cada ejercicio. Actualizado a medida que se entregan bloques.

---

## 0. Contexto

- **Objetivo**: dominar CachyOS + Hyprland sin depender de IA para
  cada cambio. Entender qué hace cada línea, mantener documentación
  propia y versionar todo con git.
- **Repo personal del usuario**: `git@github.com:obiticadev/CachyOS.git`
  (`~/.config` versionado directo, sin stow).
- **Hardware detectado**: LG ultrawide `DP-1 2560x1080@144` izquierda
  + Philips `HDMI-A-1 1920x1080@60` derecha.
- **Stack base CachyOS Hyprland edition**: hyprland, fish,
  kitty, micro, wofi, dolphin, fcitx5 (con justificación `input-
  method-v2` para kitty Wayland-nativo).

---

## 1. Variables del bootcamp

| Variable | Valor |
|---|---|
| `{LENGUAJE_TECNOLOGIA}` | CachyOS Linux + Hyprland (Hyprlang, Bash, Fish, TOML, CSS, JSONC) |
| `{CONCEPTOS_A_TRATAR}` | XDG, Hyprlang, monitors/workspaces, input/gestures, layouts, decoration, animations, keybinds, submaps, windowrulev2, hyprlock, hypridle, hyprpaper/swww, waybar, wofi/rofi, mako, kitty, fish/starship/fastfetch, GTK/Qt theming, portales XDG, autostart, hyprctl IPC, git semántico, stow, recovery |
| `{CANTIDAD_EJERCICIOS}` | **70** |
| `{SISTEMA_GESTOR_PAQUETES}` | `pacman` + `paru` (AUR) |
| `{PUNTO_DE_EJECUCION_IDE}` | `hyprctl reload` + observación en vivo |
| `{FRAMEWORK_TESTING}` | `bats-core` (con `bats-support`/`bats-assert`/`bats-file`) |

---

## 2. Reglas pedagógicas aplicadas

1. **Cero código resuelto**. Los TODOs son **instrucciones operativas
   sobre tu setup real** (no acertijos): qué decisión tomar, qué
   comando ejecutar, cómo verificar.
2. **Sin mermaid**. Documentación densa en prosa + tablas con todos
   los parámetros, tipos, defaults, rangos y ejemplos.
3. **Playground ejecutable**: cada ejercicio cierra con "Zona de
   Ejecución Master" con los comandos exactos para llevarlo a tu
   `~/.config/`.
4. **Tests bats estrictos**: ningún ejercicio se considera completo
   hasta que su suite pasa a verde.
5. **Flujo artesanal**: bloques entregados de uno en uno, con
   confirmación `siguiente` entre cada uno.
6. **Boss Final**: ejercicio 70 reconstruye TODO desde un `$HOME`
   vacío.
7. **Guía terminal base**: `README_GUIA_TERMINAL.md` documenta cómo
   instalar bats, ejecutar la suite, recovery, etc.

---

## 3. Estructura del proyecto

```
13_CachyOSHyprlandMasterclass/
├── README_GUIA_TERMINAL.md         # cómo levantar, ejecutar, testear
├── PLAN_MAESTRO.md                 # este documento
├── teoria/
│   ├── 01_Fundamentos.md
│   ├── 02_Core_Hyprland.md
│   ├── 03_Keybinds_Window_Mgmt.md
│   ├── 04_Estetica.md
│   ├── 05_Ecosistema_Visual.md
│   ├── 06_Lock_Idle.md
│   ├── 07_Terminal_Shell.md
│   ├── 08_Integracion_Sistema.md
│   ├── 09_Git_Dotfiles.md
│   ├── 10_Boss_Final.md
│   └── 99_Apendice_Errores.md
├── src/
│   ├── 01_fundamentos/
│   ├── 02_core_hyprland/
│   ├── 03_keybinds/
│   ├── 04_estetica/
│   ├── 05_ecosistema_visual/
│   ├── 06_lock_idle/
│   ├── 07_terminal_shell/
│   ├── 08_integracion_sistema/
│   ├── 09_git_dotfiles/
│   └── 10_boss_final/
└── tests/
    ├── test_helper.bash
    └── (mismas subcarpetas que src/)
```

---

## 4. Mapa de bloques

| # | Bloque | Ejs. | Estado |
|---|---|---|---|
| I | Fundamentos y terreno | 01-07 | **✅ Completado** |
| II | Core Hyprland | 08-17 | **✅ Completado** |
| III | Keybinds y window management | 18-26 | **✅ Completado** |
| IV | Estética | 27-35 | ⏳ Pendiente |
| V | Ecosistema visual (waybar/wofi/mako/wallpaper) | 36-45 | ⏳ Pendiente |
| VI | Lock & Idle | 46-51 | ⏳ Pendiente |
| VII | Terminal & shell | 52-58 | ⏳ Pendiente |
| VIII | Integración sistema | 59-64 | ⏳ Pendiente |
| IX | Git avanzado para dotfiles | 65-69 | ⏳ Pendiente |
| X | Boss Final | 70 | ⏳ Pendiente |

**Progreso global: 26 / 70 ejercicios (37%).**

---

## 5. Bloque I — Fundamentos y terreno (✅ 01-07)

Cimientos: XDG, Hyprlang, modularización con `source =`, variables y
env, git semántico para dotfiles, `.gitignore` robusto, helper
`dotcommit`.

| # | Archivo | Foco | Tests |
|---|---|---|---|
| 01 | `01_xdg_audit.sh` | Audita tu `~/.config` real, XDG vars, legacy files | 10 |
| 02 | `02_hyprlang_sintaxis.conf` | Todas las construcciones Hyprlang | 26 |
| 03 | `03_modularizar_source.conf` | Refactor a `conf.d/` con 10 sources | 6 |
| 04 | `04_variables_y_env.conf` | `10-env.conf` con fcitx5, cursor, GTK/Qt, Wayland-hints | 29 |
| 05 | `05_git_dotfiles_init.sh` | Audit idempotente del repo (SSH, identidad, remote) | 15 |
| 06 | `06_gitignore_seguro.gitignore` | Endurece tu `.gitignore` actual (waybar, swww, secrets) | 26 |
| 07 | `07_commit_semantico.sh` | Helper `dotcommit` con validación Conventional Commits | 21 |

**Total: 133 tests bats.**

Output principal: `~/.config/hypr/conf.d/10-env.conf` configurado,
git auditado, `dotcommit` instalado como alias en fish.

---

## 6. Bloque II — Core Hyprland (✅ 08-17)

Las 5 familias fundamentales: monitors, workspaces, input, gestures,
general/layouts. Tras este bloque, tu sesión luce ya con paleta y
workspaces pegados a monitores.

| # | Archivo | Foco | Tests |
|---|---|---|---|
| 08 | `08_monitors_basico.conf` | Sandbox: catch-all, transform, scale, mirror, disable | 9 |
| 09 | `09_monitors_ultrawide.conf` | Tu DP-1 + HDMI-A-1 con vrr, bitdepth, `desc:` preparado | 9 |
| 10 | `10_workspaces_persistentes.conf` | 1-5 pegados a DP-1, 6-10 a HDMI-A-1 | 15 |
| 11 | `11_workspaces_reglas.conf` | gaps/borders/auto-launch por workspace | 13 |
| 12 | `12_input_teclado.conf` | `kb_layout = es,us`, caps:escape, repeat 250/35 | 14 |
| 13 | `13_input_raton_trackpad.conf` | accel_profile flat + sub-bloque touchpad | 16 |
| 14 | `14_gestures.conf` | `workspace_swipe` (preparado para portátil) | 13 |
| 15 | `15_general_gaps_borders.conf` | Paleta Catppuccin Macchiato + gradientes | 22 |
| 16 | `16_layout_dwindle.conf` | preserve_split, no_gaps_when_only=2 | 13 |
| 17 | `17_layout_master.conf` | Master en paralelo + `hyprctl keyword` hot-swap | 13 |

**Total: 137 tests bats.**

Output principal: `20-monitors.conf`, `30-input.conf`, `35-gestures.conf`,
`40-general.conf` (con bloques general + dwindle + master), `80-workspaces.conf`
todos en sus posiciones.

---

## 7. Bloque III — Keybinds y window management (✅ 18-26)

Refactor de tus 30+ binds actuales + submaps modales + windowrulev2 +
scratchpads múltiples + scripts hyprctl.

| # | Archivo | Foco | Tests |
|---|---|---|---|
| 18 | `18_keybinds_apps.conf` | Reescribe `90-binds.conf` con variables, libera SUPER+M | 30 |
| 19 | `19_keybinds_window_ops.conf` | Resize continuo, swap, groups, pin, alterzorder | 19 |
| 20 | `20_submap_resize.conf` | Modo modal HJKL/flechas + presets 25/50/75% | 10 |
| 21 | `21_submap_passthrough.conf` | Modo VM/Blender con `$mod+CTRL+ALT+P` | 10 |
| 22 | `22_windowrulev2_basico.conf` | Diálogos, kcalc/pavu, auto-routing apps→ws, idleinhibit, opacity | 20 |
| 23 | `23_windowrulev2_picture_in_picture.conf` | Firefox/Brave/mpv PIP pinned esquina | 17 |
| 24 | `24_scratchpad_multi.conf` | 4 special workspaces (terminal/notes/music/files) | 18 |
| 25 | `25_bindl_bindm_bindr.conf` | wpctl, playerctl, grim/slurp/swappy, brillo, makoctl | 21 |
| 26 | `26_hyprctl_dispatch_scripts.fish` | 5 funciones fish: toggle-layout, send-to-empty, etc. | 20 |

**Total: 165 tests bats.**

Output principal: `90-binds.conf` modular completo, `95-submaps.conf`,
`70-windowrules.conf` con ~80 reglas, `~/.config/hypr/scripts/hypr-
helpers.fish` con funciones de orquestación.

---

## 8. Bloque IV — Estética (⏳ 27-35)

Decoration, blur, shadows, rounding, animations con beziers, layerrules
para waybar/wofi. Aquí tu desktop deja de ser "Hyprland por defecto"
y pasa a verse intencional.

| # | Archivo previsto | Foco |
|---|---|---|
| 27 | `27_decoration_rounding.conf` | rounding + smart_borders |
| 28 | `28_decoration_opacity.conf` | active_opacity, inactive_opacity, fullscreen_opacity, dim_inactive |
| 29 | `29_decoration_shadow.conf` | drop_shadow, range, render_power, color |
| 30 | `30_decoration_blur.conf` | size, passes, noise, contrast, brightness, xray |
| 31 | `31_blur_layerrules.conf` | Blur sobre waybar / wofi / notifications vía `layerrule` |
| 32 | `32_animations_base.conf` | enable + curves predefinidas (linear, easeOutCubic, …) |
| 33 | `33_animations_custom_bezier.conf` | Bezier propio para personalidad |
| 34 | `34_animations_workspaces_slide.conf` | Animar workspaces y specialWorkspace |
| 35 | `35_theming_color_palette.conf` | Variables `$rosewater`, `$base`… consolidadas y reutilizables |

Output esperado: `50-decoration.conf` y `60-animations.conf` listos.

---

## 9. Bloque V — Ecosistema visual (⏳ 36-45)

Las apps satélite que componen el desktop: waybar, wofi/rofi, mako,
hyprpaper/swww.

| # | Archivo previsto | Foco |
|---|---|---|
| 36 | `36_waybar_config_esqueleto.jsonc` | `modules-left/center/right`, posición, layer |
| 37 | `37_waybar_modulo_hyprland.jsonc` | `hyprland/workspaces`, `hyprland/window`, `hyprland/submap` |
| 38 | `38_waybar_modulos_sistema.jsonc` | cpu, memory, temperature, battery, network, pulseaudio, clock |
| 39 | `39_waybar_style.css` | CSS: colores Catppuccin, fuente Nerd, padding, hover |
| 40 | `40_waybar_custom_script.sh` | Módulo `custom/clima` con JSON propio |
| 41 | `41_wofi_style.css` | Tema wofi (drun + search) |
| 42 | `42_rofi_alternativa.rasi` | rofi-wayland como reemplazo opcional |
| 43 | `43_mako_config` | groups, urgency, anchor, on-button |
| 44 | `44_hyprpaper.conf` | preload, wallpaper por monitor |
| 45 | `45_swww_alternativa.sh` | swww init + transiciones animadas |

---

## 10. Bloque VI — Lock & Idle (⏳ 46-51)

| # | Archivo previsto | Foco |
|---|---|---|
| 46 | `46_hyprlock_basico.conf` | background, input-field básico |
| 47 | `47_hyprlock_widgets.conf` | label (reloj, user), shape, fuentes |
| 48 | `48_hyprlock_avatar_fondo.conf` | image + blur + paths reales de tu user |
| 49 | `49_hypridle_basico.conf` | listener timeout dpms off |
| 50 | `50_hypridle_suspend.conf` | suspend + lock-before-sleep |
| 51 | `51_integracion_lock_idle.sh` | Orquestar con `loginctl lock-session` |

---

## 11. Bloque VII — Terminal & Shell (⏳ 52-58)

| # | Archivo previsto | Foco |
|---|---|---|
| 52 | `52_kitty_fuentes_ligaduras.conf` | font_family, ligatures, symbol_map (Nerd Font) |
| 53 | `53_kitty_tema_catppuccin.conf` | `include` + paleta |
| 54 | `54_kitty_layouts_tabs.conf` | tab_bar_style, hints, kittens útiles |
| 55 | `55_fish_functions.fish` | Funciones propias + abbreviations |
| 56 | `56_fish_prompt_starship.toml` | Prompt minimalista con git |
| 57 | `57_fastfetch_config.jsonc` | Logo, módulos, colores |
| 58 | `58_kitty_remote_control.conf` | allow_remote_control + sockets |

---

## 12. Bloque VIII — Integración sistema (⏳ 59-64)

| # | Archivo previsto | Foco |
|---|---|---|
| 59 | `59_xdg_portals.conf` | xdg-desktop-portal-hyprland + gtk |
| 60 | `60_gtk_qt_theming.sh` | gsettings + qt5ct/qt6ct + envs |
| 61 | `61_cursor_iconos_fuentes.sh` | hyprcursor + icon theme |
| 62 | `62_mimeapps_defaults.list` | Defaults coherentes |
| 63 | `63_autostart_exec_once.conf` | Orden correcto + dependencias |
| 64 | `64_hyprctl_ipc_socket.sh` | Suscripción a eventos `hyprctl -j events` |

---

## 13. Bloque IX — Git avanzado para dotfiles (⏳ 65-69)

| # | Archivo previsto | Foco |
|---|---|---|
| 65 | `65_git_branch_experimental.sh` | Flujo: `exp/animations` → merge si funciona |
| 66 | `66_git_hook_pre_commit.sh` | `hyprctl reload --config` dry-check |
| 67 | `67_stow_migracion.sh` | Refactor a `stow` desde `~/.config` como repo |
| 68 | `68_recovery_drill.sh` | Simular config rota + restaurar |
| 69 | `69_commit_lint.sh` | Enforcer de conventional commits |

---

## 14. Bloque X — Boss Final (⏳ 70)

| # | Archivo previsto | Foco |
|---|---|---|
| 70 | `70_rebuild_desde_cero.sh` + `hyprland_completo.conf` | **Reconstrucción**: dado un `~/.config` vacío, monta TODA tu configuración (monitors reales, fcitx5, waybar, wofi, mako, hyprlock, hypridle, hyprpaper, kitty/fish/starship/fastfetch, theming GTK, portales, autostart, git inicializado). El test ejecuta en sandbox `$HOME` temporal y audita 40+ invariantes. |

---

## 15. Apéndice (creciente)

`teoria/99_Apendice_Errores.md` — catálogo de fallos reales con causa
raíz. Se actualiza al cierre de cada bloque:

- Sesión Hyprland que no arranca tras editar `.conf`
- Keybind muerto (modificadores que no funcionan)
- Blur tirando FPS en una GPU concreta
- fcitx5 no propaga env vars
- Monitor secundario en negro tras suspend
- hyprlock no autentica
- waybar sin iconos (Nerd Font no instalada)
- Portales XDG rotos en Electron / Discord
- Submap que no se cierra ("atascado")
- …

---

## 16. Flujo recomendado por bloque

1. **Leer** `teoria/0X_*.md` completo. Sin saltarte secciones.
2. **Abrir** cada archivo de `src/0X_*/NN_*.{conf,sh,fish,...}`.
3. **Resolver** los `# TODO:` en orden — son operacionales, no
   acertijos.
4. **Validar** con `bats tests/0X_*/NN_*.bats`.
5. **Aplicar** a tu `~/.config/` siguiendo "Zona de Ejecución Master".
6. **Commit** con `dotcommit <tipo> <scope> "<subject>"`.
7. **Pasar al siguiente ejercicio** solo cuando los anteriores estén
   verdes Y aplicados en tu máquina.

> Si rompes la sesión: `Ctrl+Alt+F3`, `cd ~/.config && git reset --
> hard HEAD~1`, vuelves al display manager.

---

## 17. Cómo actualizar este plan

Cuando se entrega un bloque nuevo:

1. Cambiar su estado en la tabla §4 de ⏳ a ✅.
2. Detallar su sección (§N) con la tabla de archivos + tests.
3. Sumar el conteo de tests al total.
4. Mover su "Apéndice de errores" recolectado al `99_Apendice_Errores.md`.
