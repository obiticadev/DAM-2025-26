# Bloque II — Core Hyprland

Las cinco familias de directivas que rigen "cómo se comporta tu sesión
en lo fundamental": **monitores**, **workspaces**, **input**,
**gestures** y **general / layouts**. Si dominas estas cinco, todo el
resto (estética, plugins, scripts) es decoración.

Cubre los ejercicios 08-17.

---

## 1. `monitor =` — declarar pantallas

Hyprland es **multi-monitor first-class**. Cada pantalla la declaras con
una directiva `monitor =`. Si no declaras ninguna, Hyprland intenta
auto-detectar todo (suele funcionar, pero el posicionamiento es
impredecible).

### 1.1 Sintaxis completa

```
monitor = NAME, RESOLUTION, POSITION, SCALE [, EXTRA...]
```

Forma extendida con flags:

```
monitor = NAME, RESOLUTION@HZ, POSITION, SCALE, transform, N, mirror, OTHER, bitdepth, 10, vrr, 1
```

### 1.2 Campos uno por uno

| Campo | Valores | Default | Notas |
|---|---|---|---|
| `NAME` | el ID del conector (`DP-1`, `HDMI-A-1`, `eDP-1`, `DP-3`, …) o `desc:<descripción>` | obligatorio | Ver `hyprctl monitors -j` |
| `RESOLUTION` | `WxH` o `WxH@HZ` o `preferred` o `highres` o `highrr` | `preferred` | `highres` = mayor resolución; `highrr` = mayor refresh a esa resolución |
| `POSITION` | `XxY` o `auto`, `auto-left`, `auto-right`, `auto-up`, `auto-down` | `0x0` | El origen `0x0` es esquina sup-izq del "canvas" lógico que une todos los monitores |
| `SCALE` | flotante `0.5`–`3.0` típico, o `auto` | `1.0` | Multiplicador HiDPI. `2.0` = pantalla "Retina" |
| `transform` | `0`-`7` | `0` | Rotación: `0`=normal, `1`=90°, `2`=180°, `3`=270°, `4`-`7`=flipped variants |
| `mirror, OTHER` | nombre de otro monitor | (sin mirror) | Espeja la salida |
| `bitdepth, 10` | `8` o `10` | `8` | 10-bit color (HDR-ish, necesita display compatible) |
| `vrr, MODE` | `0`=off, `1`=on, `2`=fullscreen-only | `0` | Variable Refresh Rate (FreeSync/G-Sync) |

### 1.3 Reglas operativas

- Reglas globales para "todos los monitores no listados explícitamente":
  ```conf
  monitor = , preferred, auto, 1
  ```
  La coma vacía como NAME = wildcard. Útil cuando enchufas un monitor
  nuevo y no quieres tener que añadir una línea.
- Para **desactivar** un monitor:
  ```conf
  monitor = HDMI-A-1, disable
  ```
- Para **forzar** que aparezca aunque no esté detectado al arranque
  (raro, útil con KVMs):
  ```conf
  monitor = HDMI-A-1, addreserved, 0, 0, 0, 0
  ```

### 1.4 Posicionamiento — entender el "canvas lógico"

Hyprland une todos los monitores en un plano cartesiano. Tu setup actual:

```
monitor = DP-1,     2560x1080@144, 0x0,    1
monitor = HDMI-A-1, 1920x1080@60,  2560x0, 1
```

- DP-1 ocupa el rectángulo `(0,0)` a `(2560, 1080)`.
- HDMI-A-1 empieza en `(2560, 0)` y va a `(2560+1920, 1080) = (4480,
  1080)`.
- Total canvas: `4480x1080`.

Si quisieras al HDMI **encima** del DP, en lugar de a la derecha:

```
monitor = DP-1,     2560x1080@144, 0x1080, 1
monitor = HDMI-A-1, 1920x1080@60,  320x0,  1   # centrado horizontalmente arriba
```

El `320` viene de `(2560-1920)/2`.

Con `auto-right`, `auto-left`, `auto-up`, `auto-down` te ahorras el
cálculo:

```
monitor = DP-1,     2560x1080@144, 0x0,        1
monitor = HDMI-A-1, 1920x1080@60,  auto-right, 1
```

### 1.5 Escala — la trampa del "ancho no entero"

Scale es un divisor del tamaño de la UI. Pero hay una restricción cruel
de Wayland: **el ancho efectivo (RES/SCALE) debe ser entero**.

- `2560x1080` con `1.0` → `2560x1080` ✅
- `2560x1080` con `1.25` → `2048x864` ✅ (porque `2560/1.25 = 2048`)
- `2560x1080` con `1.3` → `1969.23x830.77` ❌ Hyprland te lo redondea y
  ves un toast de warning.

Calculadora mental: divisas que dan entero para 2560 son `1.0, 1.25,
1.6, 2.0, 2.5, 5.0`. Para 1920: `1.0, 1.2, 1.5, 2.0, 2.4, 3.0`.

### 1.6 `hyprctl monitors` — leer el estado real

```fish
hyprctl monitors                # human readable
hyprctl monitors -j | jq        # JSON estructurado, parseable
hyprctl monitors all            # incluye los desconectados
```

Lo que muestra y para qué:

- `Monitor DP-1 (ID 0):` y `Monitor HDMI-A-1 (ID 1):` — los IDs son
  internos de Hyprland.
- `2560x1080@143.999Hz at 0x0` — confirma lo que negoció.
- `description: ...` — string descriptivo del fabricante. Útil cuando
  el conector cambia (`DP-1` se vuelve `DP-2` después de un suspend);
  puedes usar `monitor = desc:LG ULTRAGEAR ...` y no te enteras.
- `availableModes:` — lista de combinaciones `WxH@HZ` que el monitor
  reporta. Si tu res deseada NO está, no la puedes pedir.

### 1.7 Gotchas reales

- **HDMI-A-1 se va a "DP-3" tras un suspend/resume**: bug clásico de
  kernel/firmware. Solución: usa `desc:` en vez de el conector
  numerado.
- **Refresh negociado a 60 cuando pediste 144**: el cable HDMI 1.4 no
  da banda para 2560x1080@144. Cambia a DisplayPort o HDMI 2.0+.
- **VRR causa flicker en pantalla negra**: `vrr, 2` (fullscreen-only)
  suele ser el sweet spot para juegos sin estropear el escritorio.
- **Tras un cambio de monitor toda la sesión "se desplaza"**: porque
  Hyprland renumera workspaces a monitores. Soluciónalo con
  `workspace = ID, monitor:NAME, persistent:true` (sección 2).

---

## 2. `workspace =` y `wsbind =` — espacios de trabajo

### 2.1 Conceptos básicos

- Por defecto hay **10 workspaces numerados** (1-10). No están "pegados"
  a un monitor — al ir a un workspace, aparece en el monitor enfocado.
- Esto es un coñazo en multi-monitor: si tienes Spotify en ws 3 en
  HDMI-A-1 y abres ws 3 desde DP-1, "salta" al DP-1 perdiendo posición.
- Solución: **bindings persistentes**.

### 2.2 Sintaxis

```
workspace = WSID, PROP1:VAL1, PROP2:VAL2, ...
```

`WSID` puede ser:

| Forma | Significado |
|---|---|
| `1`, `2`, … | Workspace numerado |
| `name:dev`, `name:web`, … | Workspace con nombre |
| `special:music`, `special:scratchpad`, … | Workspace "especial" (scratchpad) |

### 2.3 Propiedades por workspace

| Prop | Valor | Qué hace |
|---|---|---|
| `monitor:NAME` | un monitor existente | Pega ese ws a ese monitor (no salta) |
| `default:true` | bool | Workspace por defecto cuando el monitor arranca |
| `persistent:true` | bool | El ws siempre existe (aunque esté vacío) |
| `gapsin:N` | int | Override de `gaps_in` para ESTE ws |
| `gapsout:N N N N` | int o 4-vec | Override de `gaps_out` |
| `border:true/false` | bool | Mostrar bordes en este ws |
| `rounding:true/false` | bool | Esquinas redondeadas en este ws |
| `decorate:true/false` | bool | Decoración (gaps + borders) en general |
| `shadow:true/false` | bool | Sombras |
| `on-created-empty:CMD` | string | Comando que se ejecuta cuando se crea vacío (ej. `[silent] firefox`) |
| `default:true` | bool | (ya mencionado) |

### 2.4 Ejemplos canónicos

**Pegar workspaces a monitores** (tu setup):

```
# DP-1 (ultrawide, izquierda)
workspace = 1, monitor:DP-1, default:true, persistent:true
workspace = 2, monitor:DP-1, persistent:true
workspace = 3, monitor:DP-1, persistent:true
workspace = 4, monitor:DP-1
workspace = 5, monitor:DP-1

# HDMI-A-1 (1080p, derecha)
workspace = 6, monitor:HDMI-A-1, default:true, persistent:true
workspace = 7, monitor:HDMI-A-1, persistent:true
workspace = 8, monitor:HDMI-A-1
workspace = 9, monitor:HDMI-A-1
workspace = 10, monitor:HDMI-A-1
```

Resultado: SUPER+1 te lleva al ws 1 SIEMPRE en DP-1. SUPER+6 SIEMPRE
en HDMI-A-1. Y "switch monitor" pasa a ser "switch workspace".

**Workspace con auto-launch**:

```
workspace = name:music, monitor:HDMI-A-1, on-created-empty:[silent] spotify
```

Cuando entras a `name:music` por primera vez, lanza Spotify en background.

**Workspace especial (scratchpad)**:

```
workspace = special:notes, on-created-empty:[silent] kitty -e nvim ~/notes.md
```

Y luego un bind `bind = SUPER, N, togglespecialworkspace, notes`.

### 2.5 `wsbind = MONITOR, WS` — versión vieja

Existe `wsbind` para pegar workspaces a monitores con sintaxis más
corta. Está obsoleta — usa `workspace = N, monitor:X` que es
explícita y soporta más props.

### 2.6 Switching y movimiento

Lo cubrimos en el Bloque III (binds), pero los dispatchers son:

- `workspace, N` → ir al ws N
- `movetoworkspace, N` → mover ventana actual al ws N (y switchear)
- `movetoworkspacesilent, N` → mover sin cambiar de foco
- `workspace, e+1` / `e-1` → siguiente / anterior **del mismo monitor**
- `workspace, m+1` / `m-1` → siguiente / anterior **global**
- `workspace, previous` → toggle entre los dos últimos

---

## 3. `input { ... }` — teclado, ratón, touchpad

Bloque **único** (todo dentro de `input { }`). Mezcla parámetros de
varias categorías.

### 3.1 Teclado

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `kb_layout` | string lista | `us` | Códigos XKB: `es`, `us`, `de`, `fr`, … |
| `kb_variant` | string lista | `""` | `dvorak`, `colemak`, `nodeadkeys`, `intl` |
| `kb_model` | string | `""` | `pc104`, `pc105`, `apple_alu_ansi`, … |
| `kb_options` | string CSV | `""` | Opciones XKB; las útiles abajo |
| `kb_rules` | string | `evdev` | No tocar |
| `kb_file` | path | `""` | Archivo `.xkb` custom (escenarios raros) |
| `numlock_by_default` | bool | `false` | Encender NumLock al iniciar |
| `repeat_rate` | int | `25` | Pulsaciones por segundo al mantener |
| `repeat_delay` | int (ms) | `600` | Ms hasta empezar a repetir |
| `resolve_binds_by_sym` | bool | `false` | Resuelve binds por símbolo (necesario en layouts non-US si no quieres `bind = , Y, ...` que no funciona en es) |

**Opciones XKB útiles** (`kb_options =`):

| Opción | Qué hace |
|---|---|
| `caps:escape` | CapsLock se vuelve Escape (vim users) |
| `caps:ctrl_modifier` | CapsLock = Ctrl |
| `caps:swapescape` | Swap entre Caps y Esc |
| `ctrl:nocaps` | CapsLock = Ctrl (alternativa) |
| `compose:ralt` | AltGr es la tecla compose |
| `grp:alt_shift_toggle` | Alt+Shift cicla layouts |
| `terminate:ctrl_alt_bksp` | Ctrl+Alt+Backspace mata sesión |
| `numpad:mac` | NumLock siempre on |

Combinas con coma: `kb_options = caps:escape,compose:ralt`.

**Layouts múltiples**:

```conf
kb_layout  = es,us
kb_variant = ,dvorak
kb_options = grp:alt_shift_toggle
```

Eso te da `es` (default) y `us-dvorak`, alternables con Alt+Shift.

### 3.2 Ratón

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `sensitivity` | float `-1.0..1.0` | `0.0` | Multiplica velocidad de cursor; `0` = sin alterar |
| `accel_profile` | string | `""` | `flat` (sin aceleración, gamers) o `adaptive` (default sistema) |
| `force_no_accel` | bool | `false` | Mata aceleración aunque el driver la quiera |
| `left_handed` | bool | `false` | Invierte botones |
| `follow_mouse` | int `0-3` | `1` | Modo de foco-sigue-ratón (ver abajo) |
| `mouse_refocus` | bool | `true` | Refocus al mover el ratón en multi-window |
| `float_switch_override_focus` | int `0-2` | `1` | Comportamiento con floating |
| `scroll_method` | string | `2fg` | `2fg`, `edge`, `on_button_down`, `no_scroll` |
| `natural_scroll` | bool | `false` | Mac-style scroll (invertido) |
| `scroll_button` | int | `0` | Botón para `on_button_down` |
| `scroll_factor` | float | `1.0` | Multiplicador de líneas por tick |

**`follow_mouse` modos**:

| Valor | Comportamiento |
|---|---|
| `0` | Foco solo cambia con click |
| `1` | El foco sigue al ratón (default) |
| `2` | Sigue al ratón pero el foco "duro" (apps reciben keypress) sigue ahí hasta click |
| `3` | Como `2` pero más agresivo |

Para "click-to-focus" estilo Windows: `follow_mouse = 0`.

### 3.3 Touchpad (sub-bloque)

```conf
input {
    touchpad {
        natural_scroll = true
        scroll_factor = 0.5
        disable_while_typing = true
        tap-to-click = true
        tap-and-drag = true
        drag_lock = false
        clickfinger_behavior = true     # 2 dedos = click der, 3 dedos = click med
        middle_button_emulation = false
    }
}
```

Útiles solo si tienes laptop.

### 3.4 Tablet (sub-bloque)

```conf
input {
    tablet {
        output = current               # current, all, o nombre de monitor
        region_position = 0 0
        region_size = 1920 1080
        relative_input = false
        left_handed = false
        active_area_size = 100 100     # mm, mapea solo a parte del tablet
        active_area_position = 0 0
    }
}
```

Para Wacom / XP-Pen / Huion.

### 3.5 Per-device (override individual)

Si tienes dos teclados (uno mecánico USB + el del portátil) y quieres
layouts distintos:

```conf
device {
    name = epomaker-rt100
    kb_layout = us
    kb_variant = colemak
}

device {
    name = at-translated-set-2-keyboard      # típico del laptop builtin
    kb_layout = es
}
```

Para saber el nombre exacto: `hyprctl devices`.

---

## 4. `gestures { ... }` — gestos de touchpad

Si no tienes laptop, salta. En laptop:

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `workspace_swipe` | bool | `false` | Swipe horizontal entre ws |
| `workspace_swipe_fingers` | int | `3` | Dedos necesarios (3 o 4) |
| `workspace_swipe_distance` | int (px) | `300` | Distancia para completar el swipe |
| `workspace_swipe_invert` | bool | `true` | Direction natural (`true` = como mac) |
| `workspace_swipe_min_speed_to_force` | int | `30` | Fuerza el cambio aunque no llegues a distance si vas rápido |
| `workspace_swipe_cancel_ratio` | float `0.0-1.0` | `0.5` | Si vuelves antes del 50% se cancela |
| `workspace_swipe_create_new` | bool | `true` | Permite swipe a un ws nuevo |
| `workspace_swipe_direction_lock` | bool | `true` | Una vez decidida la dirección, no la cambies |
| `workspace_swipe_forever` | bool | `false` | Permite seguir scrolleando entre ws sin parar |
| `workspace_swipe_use_r` | bool | `false` | `m+1`/`m-1` (global) en vez de `e+1`/`e-1` (mismo monitor) |
| `workspace_swipe_touch` | bool | `false` | Para pantallas táctiles |

Tu setup (desktop) no usará nada de esto.

---

## 5. `general { ... }` — gaps, borders, layout

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `gaps_in` | int (px) o 4-vec | `5` | Hueco entre ventanas. Vec: top right bottom left |
| `gaps_out` | int o 4-vec | `20` | Hueco contra el borde del monitor |
| `gaps_workspaces` | int | `0` | Hueco extra al swipear entre ws (efecto cinemático) |
| `border_size` | int | `1` | Grosor del borde en px |
| `no_border_on_floating` | bool | `false` | Floating sin borde |
| `col.active_border` | color/gradiente | `0xffffffff` | Borde de la ventana enfocada |
| `col.inactive_border` | color | `0xff444444` | Borde de las no-enfocadas |
| `col.nogroup_border` | color | `0xffffaaff` | Borde cuando grupos (tabbed) |
| `col.nogroup_border_active` | color | `0xffff00ff` | Idem activo |
| `cursor_inactive_timeout` | int (s) | `0` (off) | Esconde cursor tras N segundos quieto |
| `layout` | string | `dwindle` | `dwindle` o `master` |
| `allow_tearing` | bool | `false` | Habilita screen tearing (gamers, reduce input lag) |
| `resize_on_border` | bool | `false` | Cursor sobre el borde redimensiona |
| `extend_border_grab_area` | int | `15` | Área extra para "agarrar" el borde |
| `hover_icon_on_border` | bool | `true` | Cursor cambia al hover sobre el borde |
| `snap` | nested | (off) | Snap-to-windows tipo Windows. `snap { enabled = true, window_gap = 10, monitor_gap = 10 }` |

### 5.1 Gradientes de borde

```conf
col.active_border   = rgba(89b4faff) rgba(f5c2e7ff) 45deg
col.inactive_border = rgba(45475aff)
```

- Hasta 5 colores por gradiente (más no aporta).
- El ángulo es opcional (default `0deg` = horizontal izq→der).
- Si tu paleta es Catppuccin, los hex famosos: `89b4fa` blue,
  `f5c2e7` pink, `a6e3a1` green, `cba6f7` mauve, `f9e2af` yellow,
  `fab387` peach, `f38ba8` red.

---

## 6. `dwindle { ... }` — el layout por defecto

Dwindle = árbol binario. Cada ventana nueva divide el espacio del foco
en dos.

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `pseudotile` | bool | `false` | Activa modo "pseudo" (toggle por bind con `pseudo`) |
| `preserve_split` | bool | `false` | Mantén la dirección del split al cerrar ventanas |
| `force_split` | int `0-2` | `0` | `0`=auto, `1`=siempre izq/arriba, `2`=siempre der/abajo |
| `smart_split` | bool | `false` | Divide según donde pulses con el ratón |
| `smart_resizing` | bool | `true` | Resizing más inteligente respetando árbol |
| `permanent_direction_override` | bool | `false` | El split override del bind persiste |
| `special_scale_factor` | float `0.0-1.0` | `0.8` | Tamaño del scratchpad relativo al monitor |
| `split_width_multiplier` | float | `1.0` | Sesga splits a horizontal vs vertical |
| `use_active_for_splits` | bool | `true` | Splits respecto a la ventana enfocada |
| `default_split_ratio` | float `0.1-1.9` | `1.0` | Proporción del split |
| `no_gaps_when_only` | int `0-2` | `0` | `1` = sin gaps si solo hay una ventana; `2` = idem sin borders |

**Dispatchers relacionados** (binds del Bloque III):

- `togglesplit` — cambia el split del nodo actual entre H y V
- `pseudo` — toggle pseudo-tile
- `swapsplit` — intercambia las dos mitades del split

### 6.1 Visualizar el árbol

```fish
hyprctl getoption general:layout    # confirma 'dwindle'
hyprctl clients -j | jq             # estructura plana de clientes
```

No hay un "tree view" oficial. Para entenderlo: prueba abrir 4
terminales y observa cómo se subdividen.

---

## 7. `master { ... }` — el layout alternativo

Master = un panel "principal" grande y el resto en columna lateral.
Similar a el layout `Tall` de xmonad / Pop!_OS Cosmic.

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `allow_small_split` | bool | `false` | Permite > 1 master |
| `special_scale_factor` | float | `0.8` | Scratchpad |
| `mfact` | float `0.0-1.0` | `0.55` | % del monitor que ocupa el master |
| `new_status` | string | `slave` | `master`, `slave`, `inherit`: dónde aparece una ventana nueva |
| `new_on_top` | bool | `false` | Si `new_status=slave`, ¿arriba o abajo? |
| `new_on_active` | string | `none` | `before`, `after`, `none`: posición relativa al active |
| `orientation` | string | `left` | `left`, `right`, `top`, `bottom`, `center` |
| `inherit_fullscreen` | bool | `true` | Ventana nueva hereda fullscreen del previo |
| `slave_count_for_center_master` | int | `2` | Cuántas slaves antes de centrar master |
| `smart_resizing` | bool | `true` | |

### 7.1 Dispatchers de master

- `layoutmsg, swapwithmaster` — toggle ventana actual ↔ master
- `layoutmsg, focusmaster` — foco al master
- `layoutmsg, cyclenext` / `cycleprev` — ciclar entre slaves
- `layoutmsg, addmaster` / `removemaster` — añadir/quitar masters
- `layoutmsg, orientationleft` / `right` / `top` / `bottom` / `center` /
  `next` / `prev` — cambiar orientación en vivo

### 7.2 Cambiar de layout en vivo

`general:layout` puede cambiarse en runtime con:

```fish
hyprctl keyword general:layout master
hyprctl keyword general:layout dwindle
```

Pero la sesión empieza con lo que diga el `.conf`. Cambia ahí lo que
quieras que sea permanente.

---

## 8. `misc { ... }` — el cajón de sastre del Core

Selección de los útiles:

| Param | Tipo | Default | Notas |
|---|---|---|---|
| `disable_hyprland_logo` | bool | `false` | El logo en pantalla vacía |
| `disable_splash_rendering` | bool | `false` | Texto bajo el logo |
| `vfr` | bool | `true` | Variable Frame Rate del compositor (baja FPS si nada se mueve, ahorra batería) |
| `vrr` | int `0-2` | `0` | Variable Refresh Rate global (lo mismo que en `monitor =` pero default) |
| `mouse_move_enables_dpms` | bool | `false` | Mover el ratón "despierta" pantallas en DPMS off |
| `key_press_enables_dpms` | bool | `true` | Idem con teclado |
| `enable_swallow` | bool | `false` | "Window swallow": al abrir terminal hija (ej. nautilus), oculta la padre |
| `swallow_regex` | regex | `""` | Qué padres se "tragan" |
| `focus_on_activate` | bool | `false` | Si una app pide foco, ¿lo damos? `false` evita robos de foco |
| `animate_manual_resizes` | bool | `false` | Anima resize manual |
| `animate_mouse_windowdragging` | bool | `false` | Anima drag |
| `disable_autoreload` | bool | `false` | Recarga automática al editar `.conf` (default ON tras 0.40) |
| `new_window_takes_over_fullscreen` | int `0-2` | `0` | Una nueva ventana cuando hay fullscreen: 0=detrás, 1=desfullscreneamos, 2=respeta fullscreen |
| `allow_session_lock_restore` | bool | `false` | Permite "recuperar" la sesión si hyprlock cae |

---

## 9. Inspección y debugging

Los comandos que te ahorrarán horas:

```fish
hyprctl version                  # versión del compositor
hyprctl monitors                 # estado de monitores
hyprctl workspaces               # workspaces activos
hyprctl activewindow             # detalle de la ventana enfocada
hyprctl clients                  # todas las ventanas, sus posiciones
hyprctl devices                  # teclados, ratones, tablets detectados
hyprctl getoption <KEY>          # valor actual de cualquier directiva
hyprctl keyword <KEY> <VAL>      # cambia un valor EN VIVO (no persiste)
hyprctl reload                   # relee config
hyprctl dispatch <CMD> <ARGS>    # ejecuta un dispatcher (lo que hace bind)
hyprctl rollinglog               # últimas N líneas del log de Hyprland
hyprctl rollinglog -f            # tail -f del log
hyprctl globalshortcuts          # binds globales con XDG portal
hyprctl notify -1 5000 0 "msg"   # toast emergente (útil en scripts)
```

Casi cualquier consulta admite `-j` para JSON parseable con `jq`.

Log persistente: `~/.local/share/hyprland/hyprland.log` y
`hyprlandd.log` (versiones recientes).

---

## 10. Convenciones que aplicaremos a partir de aquí

- `~/.config/hypr/conf.d/20-monitors.conf` contiene **solo** `monitor =`.
- `~/.config/hypr/conf.d/80-workspaces.conf` contiene **solo**
  `workspace =`.
- `30-input.conf` contiene UN bloque `input { }` (con sub-bloques
  `touchpad`, `tablet` si aplican) y los `device { }` per-device.
- `40-general.conf` contiene UN bloque `general { }` y UN bloque
  `dwindle { }` o `master { }` (el que uses).
- Cualquier override puntual va en `99-overrides.conf` con la directiva
  escalar suelta (`gaps_in = 8`), no reabrir un bloque.

Listo. Adelante con los ejercicios 08-17.
