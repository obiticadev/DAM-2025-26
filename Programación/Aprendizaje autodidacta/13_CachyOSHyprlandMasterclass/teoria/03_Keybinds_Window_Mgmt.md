# Bloque III — Keybinds y gestión de ventanas

Tu `hyprland.conf` actual tiene ~30 `bind = ...` apilados. Este bloque
te enseña la mecánica completa: la familia de keywords `bind*`, todos
sus flags, el catálogo de **dispatchers** (las acciones que un bind
puede ejecutar), `windowrulev2` para reaccionar a ventanas concretas,
`layerrule` para reglas sobre capas (waybar, wofi…), `submap` para
modos modales, y `hyprctl dispatch` como puente desde scripts.

Cubre los ejercicios 18-26.

---

## 1. Anatomía de un `bind`

Forma básica:

```
bind = MODS, KEY, DISPATCHER, ARGS
```

Ejemplo de tu config actual:

```
bind = $mainMod, Return, exec, kitty
```

- `MODS` = lista de modificadores separados por espacio. `SUPER`,
  `ALT`, `CTRL`, `SHIFT`, también `CAPS`, `NUM`. Puede estar vacío
  (`, KEY, ...`).
- `KEY` = nombre XKB de la tecla. Letras y números `Return`, `space`,
  `Escape`, `F1`-`F12`, flechas `left`/`right`/`up`/`down`, teclas
  multimedia `XF86AudioRaiseVolume`, números `1`-`0`, símbolos `comma`,
  `period`, `slash`, `grave`, `bracketleft`, etc.
- `DISPATCHER` = uno de los listados en §3.
- `ARGS` = argumentos del dispatcher.

---

## 2. La familia `bind*` — flags como sufijo

Hyprland tiene una familia entera de keywords, cada una con un flag
combinable:

| Keyword | Flag | Significado |
|---|---|---|
| `bind` | (ninguno) | Estándar. Se dispara al pulsar, no se repite. |
| `bindl` | `l` (locked) | Funciona también con la sesión bloqueada (hyprlock). Típico para multimedia. |
| `bindr` | `r` (release) | Se dispara al SOLTAR la tecla, no al pulsar. |
| `binde` | `e` (repeat) | Se repite mientras mantienes pulsado. Para resize/move continuos. |
| `bindm` | `m` (mouse) | Para binds con botones de ratón (`mouse:272` = izq, `mouse:273` = der). |
| `bindn` | `n` (non-consuming) | No "consume" el evento — la app debajo también lo recibe. Raro. |
| `bindi` | `i` (ignore mods) | Ignora modificadores adicionales (combinables). |
| `bindt` | `t` (transparent) | Compatibilidad con XWayland legacy. |
| `bindp` | `p` (passthrough) | Específico para submaps (ver §5). |

Los flags **se combinan**:

| Combinación | Uso típico |
|---|---|
| `bindle` | Multimedia que también se repite mientras mantienes (sub/sube volumen). |
| `bindel` | Idem (orden indiferente). |
| `bindre` | Triggers al soltar repetido (raro). |

Ejemplos canónicos:

```
# Multimedia, funciona en sesión bloqueada Y se repite si mantienes
bindle = , XF86AudioRaiseVolume, exec, wpctl set-volume @DEFAULT_AUDIO_SINK@ 5%+
bindle = , XF86AudioLowerVolume, exec, wpctl set-volume @DEFAULT_AUDIO_SINK@ 5%-

# Mute al pulsar (no repetir, no necesita locked si tu lock pause audio)
bindl = , XF86AudioMute, exec, wpctl set-mute @DEFAULT_AUDIO_SINK@ toggle

# Resize que continúa mientras mantienes flechas
binde = SUPER CTRL, left,  resizeactive, -20 0
binde = SUPER CTRL, right, resizeactive,  20 0

# Click-y-arrastra con SUPER (lo que ya tienes)
bindm = SUPER, mouse:272, movewindow
bindm = SUPER, mouse:273, resizewindow
```

### 2.1 Eliminar un bind

```
unbind = SUPER, M
```

Útil cuando un `source = ...` heredado tiene un bind que no quieres.

### 2.2 Limpiar TODO

```
unbind = , all
```

Mata todos los binds. Rara vez se usa salvo en submaps de "passthrough"
(VMs).

---

## 3. Catálogo de dispatchers

Esta es la lista completa por categoría. La memoria útil: no necesitas
recordarla — la consultas con `hyprctl dispatch -h`. Pero saber qué
existe te evita reinventar.

### 3.1 Apps y ciclo de vida

| Dispatcher | Args | Qué hace |
|---|---|---|
| `exec` | `CMD` | Lanza `CMD` (uses `bind = ..., exec, ...`). Acepta flags `[silent]`, `[float]`, `[tile]`, `[fullscreen]`, `[workspace N]`, `[move X Y]`, `[size W H]`. Multi: `[silent;tile]`. |
| `exec-once` | `CMD` | Solo en `exec-once = ...` (autostart). |
| `killactive` | — | Cierra la ventana enfocada. |
| `closewindow` | matcher | Cierra una ventana específica sin enfocarla. Matcher como en windowrulev2. |
| `exit` | — | Cierra la sesión Hyprland (¡tu `SUPER+M`!). |

### 3.2 Foco

| Dispatcher | Args | Qué hace |
|---|---|---|
| `movefocus` | `l/r/u/d` | Mueve foco a la ventana de esa dirección. |
| `focuswindow` | matcher | Da foco a la primera que matchea (ej. `class:^firefox$`). |
| `focusmonitor` | nombre o `l/r/u/d/+1/-1` | Salta de monitor. |
| `focuscurrentorlast` | — | Toggle entre ventana actual y última enfocada. |
| `focusurgentorlast` | — | Si hay urgent (mensaje pendiente), salta ahí; si no, lastfocus. |
| `cyclenext` | `[opts]` | Ciclar entre ventanas del workspace. Opts: `prev`, `tiled`, `floating`, `hist`. |
| `swapnext` | `prev`/(none) | Intercambia con la siguiente/anterior en orden de creación. |
| `alterzorder` | `top`/`bottom` | Cambia z-order de floating. |

### 3.3 Movimiento de ventanas

| Dispatcher | Args | Qué hace |
|---|---|---|
| `movewindow` | `l/r/u/d` o `mon:NAME` | Mueve ventana actual. |
| `swapwindow` | `l/r/u/d` | Intercambia con vecina. |
| `movewindowpixel` | `X Y, matcher` | Mueve N píxeles. Solo floating. |
| `moveactive` | `X Y` | Idem para la activa. |
| `resizewindowpixel` | `W H, matcher` | Resize por píxeles. Solo floating. |
| `resizeactive` | `W H` | Resize de la activa. Acepta `exact W H`. |
| `centerwindow` | (opcional `1`) | Centra la activa (solo floating). |
| `pin` | (matcher) | Pin floating: aparece en todos los ws. |
| `togglepin` | — | Toggle pin. |

### 3.4 Estado de ventana

| Dispatcher | Args | Qué hace |
|---|---|---|
| `togglefloating` | (matcher) | Toggle floating ↔ tile. |
| `settiled` | (matcher) | Fuerza tile. |
| `fullscreen` | `0`/`1`/`2` | 0=maximize (cover gaps/borders), 1=true fullscreen (cubre TODO incluido waybar), 2=toggle. Default = 0. |
| `fullscreenstate` | `INT INT` | Para apps que necesitan fullscreen "fake" (juegos en ventana sin barra). |
| `fakefullscreen` | — | Engaña a la app (le dice que es fullscreen) sin serlo. |
| `pseudo` | — | Toggle pseudo-tile (dwindle). |
| `togglesplit` | — | Toggle dirección split (dwindle). |
| `swapsplit` | — | Swap los dos hijos del split (dwindle). |

### 3.5 Workspaces

| Dispatcher | Args | Qué hace |
|---|---|---|
| `workspace` | N o nombre o `e+1`/`e-1`/`m+1`/`m-1`/`previous`/`empty`/`special:NAME`/`r+1`/`r-1` | Cambia. |
| `movetoworkspace` | N o nombre | Mueve ventana actual al ws N (y cambia). Acepta `+N`, `-N`, `e+N`. |
| `movetoworkspacesilent` | N | Mueve sin cambiar. |
| `togglespecialworkspace` | nombre | Toggle special workspace. |
| `renameworkspace` | `ID newname` | Renombra. |

### 3.6 Layouts

| Dispatcher | Args | Qué hace |
|---|---|---|
| `layoutmsg` | comando | Envia mensaje al layout activo. Master: `swapwithmaster`, `focusmaster`, `cyclenext`, `cycleprev`, `addmaster`, `removemaster`, `orientationleft`, etc. |
| `splitratio` | `±VALUE` | Cambia ratio del split (dwindle). `+0.1` ensancha, `-0.1` estrecha. |

### 3.7 Grupos (tabs)

Hyprland soporta "grupos" — varias ventanas apiladas como pestañas.

| Dispatcher | Args | Qué hace |
|---|---|---|
| `togglegroup` | — | Convierte ventana en grupo o disuelve. |
| `changegroupactive` | `f`/`b`/`N` | Pestaña forward/back/N. |
| `moveintogroup` | `l/r/u/d` | Anexa ventana adyacente al grupo. |
| `moveoutofgroup` | (opcional dir) | Saca de grupo. |
| `lockgroups` | `lock`/`unlock`/`toggle` | Lock impide split accidental. |
| `lockactivegroup` | `lock`/`unlock`/`toggle` | Idem solo en la activa. |

### 3.8 Especiales

| Dispatcher | Args | Qué hace |
|---|---|---|
| `dpms` | `on`/`off`/`toggle` `[monitor]` | Power off pantallas. |
| `pass` | matcher | Reenvía el siguiente keypress a esa ventana (passthrough). |
| `sendshortcut` | `MODS, KEY, matcher` | Envia un atajo a una ventana sin enfocarla. |
| `global` | `name` | Trigger global shortcut registrado vía portal (apps Wayland). |
| `bringactivetotop` | — | (deprecated) → usa `alterzorder, top`. |
| `lockscreen` | — | Bloquea (llama a `loginctl lock-session`). |
| `exec` | (`[flags]`) `CMD` | Ya cubierto en 3.1, mencionado aquí por completitud. |

### 3.9 Ejecutar dispatchers desde la terminal

Cualquier dispatcher se ejecuta con:

```fish
hyprctl dispatch <CMD> <ARGS>
hyprctl dispatch movetoworkspace 3
hyprctl dispatch togglespecialworkspace magic
hyprctl dispatch resizeactive 100 0
```

Útil para scripts. Lo formaliza el ejercicio 26.

---

## 4. Submaps — modos modales

Un **submap** es un "modo" donde los binds normales NO funcionan y solo
los del submap están activos. Como un layer de Vim. Ideal para
operaciones de resize/move o "modo passthrough" para VMs.

### 4.1 Sintaxis

```conf
# Entrar al submap "resize" con SUPER+R
bind = SUPER, R, submap, resize

# Empieza el submap
submap = resize

# Binds que solo viven dentro del submap
binde = , left,  resizeactive, -20 0
binde = , right, resizeactive,  20 0
binde = , up,    resizeactive,  0 -20
binde = , down,  resizeactive,  0  20

# Para salir
bind = , Escape, submap, reset
bind = , Return, submap, reset

# Cierra el submap
submap = reset
```

### 4.2 Reglas

- `submap = NAME` abre el submap; todo lo que escribas **debajo** de
  esa línea es parte del submap, hasta...
- `submap = reset` que cierra.
- Para salir del submap en runtime: dispatcher `submap` con arg
  `reset`.
- Mientras estás en el submap, Hyprland muestra el nombre en algún
  lado (depende de waybar o eww — hay un módulo `hyprland/submap`).
- Si el bind para salir es la única forma, ten **siempre** Escape como
  exit.

### 4.3 Casos de uso

- **Resize mode**: F-mode de vim para windows. Entras, redimensionas
  con flechas, sales con Escape.
- **Passthrough mode**: para una VM o juego donde quieres que
  SUPER+letras vayan al guest, no al host. Submap con `unbind = , all`
  y solo un bind para salir (típicamente SUPER+Escape).
- **Modes contextuales**: "mode multimedia" donde flechas controlan
  volumen y j/k canción anterior/siguiente.

---

## 5. `windowrulev2` — reglas por ventana

Versión moderna (v2). La v1 (`windowrule = ...`) está deprecated.

### 5.1 Sintaxis

```
windowrulev2 = RULE, MATCHER
```

Múltiples matchers separados por comas:

```
windowrulev2 = float, class:^(org\.kde\.kcalc)$
windowrulev2 = size 800 600, class:^(.*pavucontrol.*)$
windowrulev2 = workspace 2 silent, class:^(firefox)$, title:.*YouTube.*
```

### 5.2 Matchers (TODOS)

| Matcher | Tipo | Notas |
|---|---|---|
| `class:REGEX` | regex POSIX | App ID o WM_CLASS |
| `title:REGEX` | regex | Título de la ventana |
| `initialClass:REGEX` | regex | Class **al crearse** (algunas apps lo cambian luego, ej. Firefox al abrir un PDF) |
| `initialTitle:REGEX` | regex | Idem para title |
| `tag:STRING` | string | Tags personalizados (asignados con `tag` rule) |
| `xwayland:0/1` | bool | 1 = solo XWayland, 0 = solo nativo Wayland |
| `floating:0/1` | bool | |
| `pinned:0/1` | bool | |
| `fullscreen:0/1` | bool | |
| `workspace:NAME/N` | string/int | |
| `focus:0/1` | bool | Solo si tiene foco |
| `onworkspace:NAME/N` | string/int | Distinto a `workspace:`: matchea cuando la ventana se mueve a ese ws |

### 5.3 Reglas (TODAS las útiles)

**Estado**:

| Rule | Qué hace |
|---|---|
| `float` | Forzar floating |
| `tile` | Forzar tile |
| `fullscreen` | Abrir en fullscreen |
| `maximize` | Abrir maximizada |
| `pin` | Pin (aparece en todos los ws) |
| `stayfocused` | Esta ventana no pierde foco aunque cliques en otra |
| `noinitialfocus` | NO le des foco al crearse |
| `nofocus` | Nunca dar foco (raro, útil para overlays de OBS) |
| `nodim` | No dimming en `dim_inactive` |
| `noborder` | Sin borde |
| `noshadow` | Sin sombra |
| `noblur` | Sin blur |
| `norounding` | Sin rounding |
| `noanim` | Sin animación |
| `nomaxsize` | Ignora MAX_SIZE de la app |

**Posición / tamaño**:

| Rule | Args | Qué hace |
|---|---|---|
| `size` | `W H` | Tamaño inicial. Acepta `%`: `size 80% 70%`. |
| `minsize` | `W H` | Tamaño mínimo. |
| `maxsize` | `W H` | Máximo. |
| `move` | `X Y` o `cursor X Y` o `mouse` o `100% 0` o `center` o `onscreen` | Posición. `onscreen` evita salir de pantalla. |
| `center` | (opcional `1`) | Centra; con `1` centra también ignorando bordes del monitor. |
| `monitor` | `NAME` | Monitor destino. |
| `workspace` | `NAME/N [silent]` | Workspace destino. `silent` = no cambia el foco. |

**Decoración custom**:

| Rule | Args | Qué hace |
|---|---|---|
| `opacity` | `ACTIVE [INACTIVE [FULLSCREEN]]` | Transparencia (override de `decoration:active_opacity`). |
| `rounding` | `N` | Override del rounding para esta ventana. |
| `bordercolor` | `rgba(...)` o gradiente | Borde custom para esta ventana. |
| `bordersize` | `N` | Override de `general:border_size`. |
| `animation` | `STYLE` | Override de animación. |

**Otros**:

| Rule | Qué hace |
|---|---|
| `idleinhibit always/fullscreen/none` | Inhibir idle (no apagar pantalla viendo Netflix). |
| `tag +TAG` | Asignar un tag (usable en futuros matchers). |
| `dimaround` | Oscurecer el resto cuando esta ventana abre. Diálogos modales. |
| `keepaspectratio` | Mantener ratio al resize (video apps). |
| `nearestneighbor` | Scaling sin filter (pixel art, retro games). |
| `suppressevent fullscreen` | No reaccionar al request de fullscreen de esa app. |

### 5.4 Ejemplos canónicos

```
# Calculadora de KDE: flotante, centrada
windowrulev2 = float, class:^(org\.kde\.kcalc)$
windowrulev2 = center, class:^(org\.kde\.kcalc)$

# Pavucontrol: flotante 600x500
windowrulev2 = float, class:^(.*pavucontrol.*)$
windowrulev2 = size 600 500, class:^(.*pavucontrol.*)$
windowrulev2 = center, class:^(.*pavucontrol.*)$

# Firefox PIP (Picture-in-Picture): pin, tamaño, esquina inferior derecha
windowrulev2 = float,       title:^(Picture-in-Picture)$
windowrulev2 = pin,         title:^(Picture-in-Picture)$
windowrulev2 = size 640 360, title:^(Picture-in-Picture)$
windowrulev2 = move 100%-660 100%-380, title:^(Picture-in-Picture)$

# IntelliJ: open en ws 3
windowrulev2 = workspace 3 silent, class:^(jetbrains-idea)$

# Discord: open en ws 6, no robar foco
windowrulev2 = workspace 6 silent, class:^(discord)$

# Netflix/YouTube fullscreen: inhibir idle
windowrulev2 = idleinhibit fullscreen, class:^(firefox)$
windowrulev2 = idleinhibit fullscreen, class:^(brave-browser)$

# Steam library: tile
windowrulev2 = tile, class:^(steam)$

# Pero overlay y popups de Steam: flotante
windowrulev2 = float, class:^(steam)$, title:^(Friends List)$
windowrulev2 = float, class:^(steam)$, title:^(Steam Settings)$

# Spotify: pin a ws 7
windowrulev2 = workspace 7 silent, class:^(Spotify)$

# Dialogs: flotantes (típico para "Save As", "Open File")
windowrulev2 = float, title:^(Open File)$
windowrulev2 = float, title:^(Save File)$
windowrulev2 = float, title:^(Print)$

# Picture-in-picture genérico (kded, mpv-pip)
windowrulev2 = float, title:^(Picture-in-Picture)$
windowrulev2 = pin,   title:^(Picture-in-Picture)$
windowrulev2 = noborder, title:^(Picture-in-Picture)$
windowrulev2 = noshadow, title:^(Picture-in-Picture)$
```

### 5.5 Cómo averiguar `class` y `title`

```fish
hyprctl clients | grep -A1 -E '^Window'
# o
hyprctl activewindow
```

`class` viene de `WM_CLASS` (XWayland) o `app_id` (Wayland nativo).

---

## 6. `layerrule` — reglas para capas (waybar, wofi, mako)

Las layers son superficies que no son "ventanas" — están en otro plano
(layer-shell protocol). Waybar es una layer. Wofi mientras está abierto
es una layer. Las notificaciones de mako son layers.

```
layerrule = RULE, NAMESPACE
```

Namespaces típicos:

- `waybar`
- `gtk-layer-shell` (wofi default)
- `notifications` (mako)
- `selection` (slurp/grim)
- `swww-daemon` (wallpaper)
- `wofi`

Reglas:

| Rule | Qué hace |
|---|---|
| `blur` | Aplica blur tras la capa (si tu wallpaper se vea borroso bajo waybar) |
| `ignorezero` | Ignora alpha=0 al calcular blur |
| `noanim` | Sin animación de entrada |
| `dimaround` | Oscurece todo lo demás |
| `xray, 0/1` | El blur "ve" a través de la layer (xray=1 ve el wallpaper, no las apps) |
| `unset` | Quita una rule aplicada por waybar/wofi self-set |

Ejemplos:

```
layerrule = blur, waybar
layerrule = ignorezero, waybar
layerrule = blur, wofi
layerrule = ignorezero, wofi
layerrule = blur, notifications
```

---

## 7. Scratchpads — múltiples

Tu config actual usa UN scratchpad llamado `magic`. Pero puedes tener
varios para distintos propósitos.

```
# Declarar workspaces especiales
workspace = special:terminal,  on-created-empty:[silent] kitty
workspace = special:notes,     on-created-empty:[silent] kitty -e nvim ~/notes.md
workspace = special:music,     on-created-empty:[silent] spotify
workspace = special:files,     on-created-empty:[silent] dolphin

# Binds dedicados
bind = SUPER, T, togglespecialworkspace, terminal
bind = SUPER, N, togglespecialworkspace, notes
bind = SUPER, M, togglespecialworkspace, music
bind = SUPER, O, togglespecialworkspace, files

# Mover ventana ACTUAL a un special específico
bind = SUPER SHIFT, T, movetoworkspace, special:terminal
```

OJO: `SUPER+M` colisiona con tu bind actual (cierre de sesión).
Resuélvelo en el ejercicio 18.

---

## 8. Eventos IPC — leer estado desde scripts

Hyprland expone dos sockets:

| Socket | Para qué |
|---|---|
| `$XDG_RUNTIME_DIR/hypr/$HYPRLAND_INSTANCE_SIGNATURE/.socket.sock` | Comandos (lo que `hyprctl` usa internamente) |
| `$XDG_RUNTIME_DIR/hypr/$HYPRLAND_INSTANCE_SIGNATURE/.socket2.sock` | Eventos en streaming |

Para suscribirte a eventos:

```fish
socat -U - UNIX-CONNECT:$XDG_RUNTIME_DIR/hypr/$HYPRLAND_INSTANCE_SIGNATURE/.socket2.sock
```

Salida de ejemplo:

```
workspace>>3
focusedmon>>DP-1,3
activewindow>>kitty,foo@bar
openwindow>>0x...
closewindow>>0x...
movewindow>>0x...,3
fullscreen>>1
submap>>resize
```

Cada línea = un evento. Eventos disponibles:

`workspace`, `focusedmon`, `activewindow`, `activewindowv2`, `fullscreen`,
`monitorremoved`, `monitoradded`, `createworkspace`, `destroyworkspace`,
`moveworkspace`, `renameworkspace`, `activelayout`, `openwindow`,
`closewindow`, `movewindow`, `openlayer`, `closelayer`, `submap`,
`changefloatingmode`, `urgent`, `minimize`, `screencast`, `windowtitle`,
`togglegroup`, `moveintogroup`, `moveoutofgroup`, `ignoregrouplock`,
`lockgroups`, `configreloaded`, `pin`.

Usos típicos:

- Notificación al cambiar a un ws específico.
- Auto-mover ventanas con título dinámico.
- Logging para debug.
- Hooks tipo `i3-msg` style.

### 8.1 hyprctl en JSON

```fish
hyprctl -j activewindow
hyprctl -j clients
hyprctl -j workspaces
hyprctl -j monitors
```

Combinable con `jq` para scripting limpio.

---

## 9. Convenciones que aplicaremos

- `~/.config/hypr/conf.d/90-binds.conf` contiene **solo** `bind*` y
  `unbind`. Sin lógica, sin variables (las variables están en
  `10-env.conf`).
- `~/.config/hypr/conf.d/70-windowrules.conf` contiene **solo**
  `windowrulev2` y `layerrule`.
- `~/.config/hypr/conf.d/95-submaps.conf` (nuevo) contiene los
  `submap = ...` y sus binds internos.
- Los scratchpads están declarados en `80-workspaces.conf` (workspace
  =) y los binds dedicados en `90-binds.conf` (bind = ...).

Listo. Adelante con los ejercicios 18-26.
