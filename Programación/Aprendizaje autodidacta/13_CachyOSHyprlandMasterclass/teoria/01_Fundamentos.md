# Bloque I — Fundamentos y terreno

Este bloque es el cimiento. No tocamos estética, animaciones ni waybar
todavía. Antes de personalizar nada hay que entender **dónde vive la
configuración**, **cómo se escribe**, **cómo se modulariza** y **cómo se
versiona** para no perderla.

Cubre los ejercicios 01-07.

---

## 1. La jerarquía XDG Base Directory

### 1.1 Por qué existe

Antes de XDG, cada aplicación dejaba archivos en `~` con criterios propios:
`~/.foorc`, `~/.foo/`, `~/.config/foo`, `~/.local/foo`, `~/.cache/foo`.
Resultado: tu home como vertedero, copias de seguridad imposibles de
filtrar (¿qué es config y qué es caché?), y nada portable entre máquinas.

La **XDG Base Directory Specification** (de freedesktop.org) define
**cuatro raíces canónicas** controladas por variables de entorno, y dicta
qué tipo de archivo va en cada una.

### 1.2 Las cuatro raíces

| Variable | Default si no está seteada | Qué guarda | Versionar en git |
|---|---|---|---|
| `XDG_CONFIG_HOME` | `~/.config` | **Configuración** de aplicaciones del usuario. Editable a mano. | **SÍ** |
| `XDG_DATA_HOME` | `~/.local/share` | **Datos persistentes** generados por apps (themes instalados, fuentes, historiales largos, bases SQLite). | Selectivo |
| `XDG_STATE_HOME` | `~/.local/state` | **Estado** efímero pero útil entre sesiones (logs recientes, posición de ventana, último archivo abierto). | **NO** |
| `XDG_CACHE_HOME` | `~/.cache` | **Caché regenerable**. Si lo borras, la app sigue funcionando (más lenta el primer arranque). | **NO** |

Además existen dos rutas no-home:

| Variable | Default | Qué guarda |
|---|---|---|
| `XDG_RUNTIME_DIR` | `/run/user/$UID` | Sockets, pids, archivos efímeros de la sesión (sobrevive solo mientras hay sesión activa). |
| `XDG_DATA_DIRS` | `/usr/local/share:/usr/share` | Datos a nivel de sistema (los temas de iconos del sistema, por ejemplo). |
| `XDG_CONFIG_DIRS` | `/etc/xdg` | Config a nivel de sistema (defaults antes del override del usuario). |

### 1.3 Reglas operativas

- Una app **bien comportada** lee primero `$XDG_*_HOME/<app>/`, luego cae
  a los `*_DIRS` de sistema, y nunca escribe en `~` raíz.
- Si encuentras `~/.foorc` o `~/.foo/`, es legacy. Muchas apps soportan
  ambos por compatibilidad; siempre prefiere `~/.config/foo/`.
- Tu `~/.config` **es** un repo git porque concentra todo lo que merece
  versionado y nada que cambie cada segundo.
- Los `~/.cache` y `~/.local/state` se ignoran en git por diseño. Si
  versionas un binario SQLite de caché, vas a tener merge conflicts cada
  vez que abras Firefox.

### 1.4 Cómo verlo en tu máquina

```fish
env | grep ^XDG_
ls -la ~/.config | head -30
ls -la ~/.local/share | head -30
```

Si `env` no muestra `XDG_CONFIG_HOME`, **no pasa nada**: las apps
asumen el default. Solo se exporta cuando quieres mover la raíz a otro
sitio (raro, no lo hagas).

### 1.5 Qué hay en tu `~/.config` actualmente (auditado en tu repo)

```
hypr/         fish/         kitty/        micro/
autostart/    cachyos/      system/       (snapshot grub)
dolphinrc     mimeapps.list pavucontrol.ini
user-dirs.dirs user-dirs.locale
```

Falta (lo iremos añadiendo a lo largo del bootcamp):

- `waybar/`            — barra de estado
- `hyprlock.conf`      — pantalla de bloqueo (instalado, sin config)
- `hypridle.conf`      — gestor de inactividad
- `hyprpaper.conf`     — fondos
- `mako/`              — notificaciones
- `wofi/`              — estilo del launcher (tienes el binario pero no
                          el `style.css` propio)
- `starship.toml`      — prompt
- `fastfetch/`         — neofetch moderno
- `gtk-3.0/`, `gtk-4.0/`, `qt5ct/`, `qt6ct/` — theming

---

## 2. Hyprlang — la sintaxis de Hyprland

Hyprland no usa YAML, TOML ni JSON. Tiene su propio parser, **Hyprlang**,
diseñado para configuración declarativa de un compositor. Es estricto y
sin sorpresas una vez lo conoces.

### 2.1 Reglas léxicas

| Construcción | Sintaxis | Ejemplo |
|---|---|---|
| Comentario línea | `#` hasta el fin de línea | `# esto se ignora` |
| Asignación escalar | `clave = valor` | `border_size = 2` |
| Asignación múltiple (lista) | repetir la misma `clave =` | `bind = SUPER, Q, ...`<br>`bind = SUPER, W, ...` |
| Bloque | `nombre { ... }` | `general { gaps_in = 5 }` |
| Bloque anidado | `padre { hijo { ... } }` | `decoration { blur { enabled = true } }` |
| Variable | `$nombre = valor` y luego `$nombre` | `$mod = SUPER`<br>`bind = $mod, Q, ...` |
| Variable de entorno | `env = NAME,VALUE` | `env = GTK_THEME,Adwaita-dark` |
| Include | `source = ruta` (relativa al archivo que la contiene) | `source = ~/.config/hypr/binds.conf` |
| Keyword especial | `exec`, `exec-once`, `monitor`, `workspace`, `bind`, `bindm`, `windowrule`, `windowrulev2`, `layerrule`, `submap`, etc. | (ver bloques siguientes) |

### 2.2 Tipos de valor

Hyprlang infiere tipos. La directiva los espera estrictos:

| Tipo | Notación | Ejemplo |
|---|---|---|
| Entero | dígitos | `border_size = 2` |
| Flotante | con punto | `active_opacity = 0.95` |
| Booleano | `true`/`false`, `yes`/`no`, `on`/`off`, `1`/`0` | `enabled = true` |
| Color hex | `rgb(RRGGBB)` o `rgba(RRGGBBAA)` o `0xAARRGGBB` | `col.active_border = rgba(89b4faff)` |
| Gradiente | varios colores y un ángulo opcional | `col.active_border = rgba(89b4faff) rgba(f5c2e7ff) 45deg` |
| String | texto literal sin comillas en la mayoría de keywords | `kb_layout = es,us` |
| Lista | separada por comas | `kb_layout = es,us`<br>`kb_variant = dvorak,` |
| Vec2 | `X Y` separado por espacio | `cursor_inactive_timeout = 0` (no aplica aquí, pero ej. en `transform` o en `size` de windowrule) |
| Modmask | combinaciones `MOD1 MOD2` | `SUPER SHIFT` |

### 2.3 Orden y precedencia

- Lee el archivo de arriba a abajo. **La última asignación gana** para
  claves escalares.
- Para listas (`bind`, `monitor`, `windowrulev2`, `workspace`), **todas
  las líneas se acumulan**; no se sobreescriben unas a otras. Para
  eliminar una entrada concreta usas `unbind = ...` o repites la
  directiva con `,,,` vacío (cada keyword tiene su mecanismo; lo
  veremos en su bloque).
- Un `source = X` se evalúa **en el punto donde aparece**, como si el
  contenido de `X` se inyectara ahí. Útil para overrides: source de
  defaults primero, source de tweaks después.

### 2.4 Recarga

```fish
hyprctl reload
```

- Releerá `~/.config/hypr/hyprland.conf` (y todos sus `source =`).
- **No** mata procesos ya lanzados con `exec-once`. Si cambias un
  `exec-once`, hay que matar el proceso a mano o cerrar sesión.
- Si el archivo tiene un error de sintaxis, Hyprland muestra un toast
  rojo en pantalla con el número de línea y **mantiene la última config
  válida**. Tu sesión no muere por un typo.

### 2.5 Defaults reales (para que sepas qué estás cambiando)

Selección de los defaults más útiles a tener en cabeza:

| Directiva | Default | Implicación |
|---|---|---|
| `general:gaps_in` | `5` | Hueco entre ventanas |
| `general:gaps_out` | `20` | Hueco contra el borde del monitor |
| `general:border_size` | `1` | Borde de 1px |
| `general:col.active_border` | `0xffffffff` (blanco) | Tu ventana enfocada tiene borde blanco si no lo cambias |
| `general:col.inactive_border` | `0xff444444` (gris oscuro) | |
| `general:layout` | `dwindle` | Algoritmo de tiling |
| `decoration:rounding` | `0` | Esquinas cuadradas |
| `decoration:active_opacity` | `1.0` | Sin transparencia |
| `decoration:blur:enabled` | `true` | Pero solo se ve si hay opacidad < 1 |
| `decoration:blur:size` | `8` | Radio del blur |
| `decoration:blur:passes` | `1` | Más passes = más blur, más coste |
| `animations:enabled` | `true` | |
| `input:kb_layout` | `us` | Si no escribes `es`, tu `ñ` no funciona |
| `input:follow_mouse` | `1` | El foco sigue al ratón |
| `input:sensitivity` | `0.0` | Rango `-1.0` (lento) a `1.0` (rápido) |
| `gestures:workspace_swipe` | `false` | Habilítalo si tienes trackpad |
| `misc:disable_hyprland_logo` | `false` | Te aparece el logo en pantalla vacía |

> Lista no exhaustiva — cada bloque siguiente abrirá la suya entera.

---

## 3. Modularización con `source =`

Tu `hyprland.conf` actual tiene 83 líneas. Para cuando termines este
bootcamp tendrá 600+. Mantenerlo en un solo archivo es ingobernable.

### 3.1 Patrón recomendado

```
~/.config/hypr/
├── hyprland.conf              # punto de entrada — solo source = y orden
├── conf.d/
│   ├── 10-env.conf            # env vars (fcitx5, GTK, QT, cursor…)
│   ├── 20-monitors.conf       # monitor = ...
│   ├── 30-input.conf          # input { kb_layout, sensitivity, ... }
│   ├── 40-general.conf        # gaps, borders, layout
│   ├── 50-decoration.conf     # rounding, blur, shadow, opacity
│   ├── 60-animations.conf     # bezier + animations
│   ├── 70-windowrules.conf    # windowrulev2
│   ├── 80-workspaces.conf     # workspace = persistent...
│   ├── 90-binds.conf          # bind / bindm / bindl
│   └── 99-autostart.conf      # exec-once
└── scripts/
    └── (helpers fish/bash)
```

`hyprland.conf` queda así:

```conf
# === Entry point — solo orquestación ===
source = ~/.config/hypr/conf.d/10-env.conf
source = ~/.config/hypr/conf.d/20-monitors.conf
source = ~/.config/hypr/conf.d/30-input.conf
source = ~/.config/hypr/conf.d/40-general.conf
source = ~/.config/hypr/conf.d/50-decoration.conf
source = ~/.config/hypr/conf.d/60-animations.conf
source = ~/.config/hypr/conf.d/70-windowrules.conf
source = ~/.config/hypr/conf.d/80-workspaces.conf
source = ~/.config/hypr/conf.d/90-binds.conf
source = ~/.config/hypr/conf.d/99-autostart.conf
```

### 3.2 Por qué prefijos numéricos

- **Orden léxico** = orden de carga. `ls conf.d/` ya te da la
  secuencia mental.
- Espacios de 10 en 10 dejan sitio para insertar (`25-input-laptop.conf`
  entre 20 y 30 sin renumerar todo).
- El último archivo gana en escalares: pones tus overrides en `99-*`.

### 3.3 `source =` y rutas

- Acepta `~` y variables shell **no expandidas por Hyprland** —
  expánde solo `~`. Para `$HOME` etc. necesitarías evaluar tú.
- Rutas relativas se resuelven respecto a `XDG_CONFIG_HOME/hypr/`,
  no respecto al archivo que las invoca.
- Si la ruta no existe, Hyprland lanza un warning y sigue. Buena
  práctica: define un `source =` solo cuando el archivo ya existe.
- Puedes usar globs: `source = ~/.config/hypr/conf.d/*.conf` (versiones
  modernas de Hyprland, ≥ 0.39). Si tu versión es vieja, lista uno por
  uno.

### 3.4 Anti-patrones

- **No mezcles** secciones del mismo bloque entre archivos. Si abres
  `general { ... }` en `40-general.conf` y otro `general { ... }` en
  `99-overrides.conf`, **funciona** (Hyprland fusiona), pero te
  vuelves loco rastreando qué archivo setea qué. Mejor: keyword
  escalar suelta en el archivo de override (`gaps_in = 8` sin bloque
  envolvente), que es equivalente a sobreescribir.
- **No metas binds dentro de bloques**. `bind` es top-level. Si lo
  pones dentro de `general { bind = ... }`, falla silenciosamente.

---

## 4. Variables y entorno

### 4.1 Variables Hyprlang (`$nombre`)

Son **macros textuales**. Reemplazo perezoso en el punto de uso.

```conf
$mod      = SUPER
$terminal = kitty
$menu     = wofi --show drun

bind = $mod, Return, exec, $terminal
bind = $mod, SPACE,  exec, $menu
```

- Scope global: una vez declaradas, viven hasta el fin del parsing.
- Pueden referirse a otras variables:
  ```conf
  $accent = rgba(89b4faff)
  $border = $accent
  ```
- **No** soportan expresiones (`$x + 5` no funciona).
- Por convención, usa minúscula para "user variables" y reserva
  `MAYUS` para directivas / modificadores.

### 4.2 Variables de entorno (`env =`)

Setean variables de entorno que **heredarán todos los hijos del
compositor**: tus terminales, tus apps lanzadas desde wofi, etc.

```conf
env = GTK_IM_MODULE,fcitx
env = QT_IM_MODULE,fcitx
env = XCURSOR_THEME,Bibata-Modern-Classic
env = XCURSOR_SIZE,24
env = HYPRCURSOR_THEME,Bibata-Modern-Classic
env = HYPRCURSOR_SIZE,24
env = QT_QPA_PLATFORMTHEME,qt5ct
env = MOZ_ENABLE_WAYLAND,1
env = ELECTRON_OZONE_PLATFORM_HINT,auto
```

Gotchas:

- Sintaxis con **coma** separando nombre y valor, **sin espacios**:
  `env = NAME,VALUE`. Espacios extra rompen.
- Hyprland setea estas vars **solo al arrancar**. Si las cambias y
  haces `hyprctl reload`, los procesos ya lanzados conservan las
  viejas. Para que las hereden, cierras sesión y vuelves a entrar.
- No es lo mismo que el `~/.pam_environment` o `~/.config/environment.d/`.
  Esos los setea systemd antes de Hyprland. Si necesitas la var **antes**
  del compositor (DM, login shell), va ahí, no en `hyprland.conf`.

### 4.3 Variables especiales que Hyprland inyecta

Estas las puedes leer desde tus scripts:

| Variable | Qué contiene |
|---|---|
| `HYPRLAND_INSTANCE_SIGNATURE` | ID único de la sesión actual. `hyprctl` lo necesita. |
| `HYPRLAND_CMD` | Línea de comando con la que arrancó. |
| `XDG_RUNTIME_DIR/hypr/$HIS/.socket.sock` | Socket de control. |
| `XDG_RUNTIME_DIR/hypr/$HIS/.socket2.sock` | Socket de eventos. |

Los sockets son la base de toda la integración (waybar, scripts, etc.).
Los exploras en el Bloque VIII.

---

## 5. Git para dotfiles — visión del bloque

El detalle técnico va en los ejercicios 05-07; aquí los principios.

### 5.1 Modelo: `~/.config` **es** el repo

Tu repo `obiticadev/CachyOS` ya hace esto: el `.git/` vive en
`~/.config/.git/`. **Ventajas**: cero indirección, `cd ~/.config && git
status` te dice todo, no hay symlinks que mantener. **Desventajas**:
todo lo que no quieras versionar **tiene** que estar en `.gitignore`
(navegadores, cachés, sockets…). Tu `.gitignore` actual ya lo cubre
bien.

Alternativa común: **GNU Stow**. El repo vive en `~/dotfiles/` y `stow
hypr` crea symlinks `~/.config/hypr -> ~/dotfiles/hypr`. Trae
modularidad por "paquete" y separa repo de filesystem, pero añade una
capa de symlinks que confunde a algunas apps (`realpath` resuelve raro).
La masterclass aprenderá Stow en el Bloque IX como alternativa, no
como reemplazo.

### 5.2 Commits semánticos (Conventional Commits)

Formato:

```
<tipo>(<scope opcional>): <resumen imperativo, <50 chars>>

[cuerpo opcional explicando el porqué]

[footer opcional: BREAKING CHANGE, refs, …]
```

Tipos canónicos:

| Tipo | Uso |
|---|---|
| `feat` | Funcionalidad nueva (nuevo módulo, nuevo bind, nuevo daemon) |
| `fix` | Corrige un bug de configuración |
| `refactor` | Mueves cosas sin cambiar comportamiento (modularizas en `conf.d/`) |
| `style` | Solo cambios visuales (colores, gaps, padding) |
| `docs` | README, comentarios |
| `chore` | Limpieza, `.gitignore`, dependencias |
| `revert` | Deshaces un commit previo |
| `perf` | Mejora rendimiento (reduces blur passes, etc.) |

Scopes habituales en dotfiles Hyprland: `hypr`, `waybar`, `kitty`,
`fish`, `lock`, `idle`, `wallpaper`, `theme`, `grub`.

Ejemplos válidos:

```
feat(waybar): añade módulo custom de batería con icono
fix(hypr): corrige posición del HDMI-A-1 (estaba solapando DP-1)
style(theme): migra paleta a Catppuccin Macchiato
refactor(hypr): mueve binds a conf.d/90-binds.conf
chore(gitignore): excluye fcitx5/conf/cached_layouts
```

Por qué importa:

- `git log --oneline` se vuelve un changelog legible sin esfuerzo.
- `git bisect` localiza qué commit rompió la sesión en segundos.
- Herramientas como `git-cliff` o `release-please` generan changelog
  automático a partir de estos prefijos.

### 5.3 Ramas para experimentar

```fish
cd ~/.config
git switch -c exp/animations-curva-nueva
# tocas, pruebas, recargas...
# si funciona:
git switch master
git merge --no-ff exp/animations-curva-nueva
git branch -d exp/animations-curva-nueva
# si no funciona:
git switch master
git branch -D exp/animations-curva-nueva
```

Convención de nombres:
- `exp/<tema>` — experimentos que pueden quedar en nada
- `feat/<tema>` — features que sí van a entrar
- `fix/<tema>` — bugs concretos

### 5.4 `.gitignore` para dotfiles

Tu `.gitignore` actual ya bloquea: navegadores, sockets, lockfiles,
cachés, dconf binario, sockets de input methods, tokens y env files,
historiales. Lo revisaremos a fondo en el ejercicio 06 y le añadiremos
algunos casos que aparecen al crecer (`waybar/.cache/`, `swww`
runtime, `cliphist` bases, etc.).

### 5.5 Hooks pre-commit (avance del Bloque IX)

Idea: antes de aceptar un commit, validar que `hyprctl reload --config
~/.config/hypr/hyprland.conf` no produce errores. Hook en `.git/hooks/
pre-commit`. Te ahorra commitear configs rotas. Lo construirás en el
ejercicio 66.

### 5.6 Recovery — los tres comandos que tienes que tener memorizados

```fish
git log --oneline -20                       # ¿desde dónde puedo restaurar?
git diff HEAD~1 -- hypr/hyprland.conf       # ¿qué cambió respecto a anteayer?
git checkout HEAD~1 -- hypr/hyprland.conf   # restauro SOLO ese archivo
```

Para el botón nuclear:

```fish
git reset --hard origin/master              # tu copia local = la remota
```

> Esto **descarta** cambios no commiteados. Si tienes algo a medias,
> `git stash` antes.

---

## 6. Convenciones que vamos a aplicar a partir de aquí

- Prefijo numérico en todos los archivos de `~/.config/hypr/conf.d/`.
- Una directiva, una decisión. Comentarios `#` cuando el porqué no es
  evidente.
- `$variables` Hyprlang para todo lo que aparezca dos o más veces.
- `env =` solo para vars que tienen que estar **antes** del primer
  proceso del compositor.
- Cada cambio significativo es un commit. "Cuando funcione le cambio
  el mensaje" no existe — `git commit --amend` antes del push.
- Antes de tocar `hyprland.conf` en caliente: commit del estado bueno.

Con esto listo, los siete ejercicios del bloque te obligan a:

1. Auditar tu `~/.config` real y XDG.
2. Escribir un archivo Hyprlang con todas las construcciones.
3. Migrar tu `hyprland.conf` actual a la estructura modular `conf.d/`.
4. Introducir variables Hyprlang y `env =` correctas para tu setup.
5. Inicializar (o auditar) el git de tus dotfiles con buena higiene.
6. Refinar tu `.gitignore`.
7. Adoptar commits semánticos con un helper script.

Adelante con `src/01_fundamentos/`.
