#!/usr/bin/env fish
# =============================================================================
# Ejercicio 26 — Scripts fish que orquestan Hyprland via `hyprctl`
# -----------------------------------------------------------------------------
# Objetivo: aprender a leer estado de Hyprland en JSON y reaccionar
# desde fish. Vas a implementar 4 funciones independientes que viven
# en este archivo:
#
#     1. toggle-layout       : alterna entre dwindle y master
#     2. send-to-empty       : mueve la ventana actual al primer ws vacío
#     3. show-active         : imprime info de la ventana enfocada
#     4. watch-submap        : escucha eventos socket2 y avisa de cambios
#                              de submap (útil cuando aún no tienes
#                              waybar con el módulo).
#
# Cuando los tests pasen y todo funcione, copias este archivo a:
#     ~/.config/hypr/scripts/hypr-helpers.fish
# Y añades en tu fish/config.fish:
#     source ~/.config/hypr/scripts/hypr-helpers.fish
#
# Referencia teórica: teoria/03_Keybinds_Window_Mgmt.md  §3.9, §8
# =============================================================================


# -----------------------------------------------------------------------------
# TODO 01: function toggle-layout
#   Lee el layout actual con:
#       hyprctl getoption general:layout -j | jq -r '.str'
#
#   Si devuelve "dwindle", cambia a master:
#       hyprctl keyword general:layout master
#   Si devuelve "master", cambia a dwindle.
#   En ambos casos, lanza una notificación visual:
#       hyprctl notify -1 2000 0 "Layout → master" (o dwindle)
#
#   La función debe estar en formato fish:
#       function toggle-layout --description "alterna dwindle <-> master"
#           ...
#       end
#
#   Por qué jq -r '.str': `hyprctl -j` te da JSON; la opción string
#   está en el campo `.str`. Sin -r te lo daría con comillas, no útil
#   para comparar.
# -----------------------------------------------------------------------------



# -----------------------------------------------------------------------------
# TODO 02: function send-to-empty
#   Encuentra el primer workspace numerado vacío (sin ventanas) y mueve
#   la ventana enfocada ahí.
#
#   Paso a paso:
#     1. Obtén la lista de workspaces con sus contadores:
#          hyprctl workspaces -j | jq '.[] | {id, windows}'
#     2. Filtra los que tienen windows=0 y id>0 (los positivos; los
#        especiales tienen id negativo):
#          hyprctl workspaces -j | jq '.[] | select(.windows == 0 and .id > 0) | .id' | sort -n | head -1
#     3. Si no encuentras ninguno vacío, usa el primer ws "implícito"
#        siguiente al máximo existente (también puedes usar `workspace,
#        empty` como dispatcher mágico — más simple):
#          hyprctl dispatch movetoworkspace empty
#
#   La versión MÁS simple usando el dispatcher mágico:
#       hyprctl dispatch movetoworkspace empty
#
#   Pero implementa la versión LARGA (con jq) en el TODO — es más
#   educativa y te enseña el patrón hyprctl-j+jq que reutilizarás.
#
#       function send-to-empty --description "mueve ventana actual al primer ws vacio"
#           ...
#       end
# -----------------------------------------------------------------------------



# -----------------------------------------------------------------------------
# TODO 03: function show-active
#   Imprime un resumen de la ventana enfocada:
#     - class
#     - title
#     - workspace
#     - posición  (at)
#     - tamaño    (size)
#     - floating: yes/no
#
#   Fuente:
#       hyprctl activewindow -j
#
#   Estructura de campos relevantes: `.class`, `.title`, `.workspace.id`,
#   `.workspace.name`, `.at[0]`, `.at[1]`, `.size[0]`, `.size[1]`,
#   `.floating`.
#
#   Formato sugerido (tabla):
#       class:     kitty
#       title:     fish ~/.config
#       workspace: 1 (DP-1)
#       position:  120,40
#       size:      1200x720
#       floating:  no
#
#       function show-active --description "info de la ventana enfocada"
#           ...
#       end
# -----------------------------------------------------------------------------



# -----------------------------------------------------------------------------
# TODO 04: function watch-submap
#   Suscribirse al socket2 de Hyprland y filtrar eventos `submap>>`.
#   Cuando recibas uno, lanza una notificación con el nombre del submap.
#   Si el evento es `submap>>` (vacío), significa "salí del submap".
#
#   Sintaxis socat:
#       socat -U - UNIX-CONNECT:$XDG_RUNTIME_DIR/hypr/$HYPRLAND_INSTANCE_SIGNATURE/.socket2.sock
#
#   Procesa con un while que lee línea a línea:
#       function watch-submap --description "notifica cambios de submap"
#           set -l sock $XDG_RUNTIME_DIR/hypr/$HYPRLAND_INSTANCE_SIGNATURE/.socket2.sock
#           if not test -S $sock
#               echo "Socket no encontrado: $sock" >&2
#               return 1
#           end
#           socat -U - UNIX-CONNECT:$sock | while read -l line
#               if string match -q 'submap>>*' -- $line
#                   set -l name (string replace 'submap>>' '' -- $line)
#                   if test -z "$name"
#                       hyprctl notify -1 1500 0 "submap: normal"
#                   else
#                       hyprctl notify -1 3000 0 "submap: $name"
#                   end
#               end
#           end
#       end
#
#   Cuando lo arranques (manualmente o desde autostart), te llegará un
#   toast cada vez que entres o salgas de un submap. Útil mientras no
#   tienes waybar.
#
#   Para auto-arrancarlo: en `99-autostart.conf`:
#       exec-once = fish -c watch-submap &
#
#   (Pero NO añadas ese exec-once aún — lo harás cuando hayas probado
#   que watch-submap funciona en interactivo.)
# -----------------------------------------------------------------------------



# -----------------------------------------------------------------------------
# TODO 05: HELP / LISTAR
#   Añade una función `hypr-helpers-help` que imprima la lista de
#   funciones disponibles y un one-liner de cada:
#
#       function hypr-helpers-help --description "muestra las funciones de este archivo"
#           echo "  toggle-layout    alterna dwindle <-> master"
#           echo "  send-to-empty    mueve ventana actual al primer ws vacio"
#           echo "  show-active      info de la ventana enfocada"
#           echo "  watch-submap     daemon: notifica cambios de submap"
#       end
#
#   Cuando hagas source de este archivo desde tu config.fish, tendrás
#   las 4 funciones disponibles + hypr-helpers-help para recordarlas.
# -----------------------------------------------------------------------------



# =============================================================================
# Zona de Ejecución Master
# -----------------------------------------------------------------------------
# Local:
#     bats tests/03_keybinds/26_hyprctl_dispatch_scripts.bats
#
# En tu máquina (después de copiar a ~/.config/hypr/scripts/hypr-helpers.fish):
#     # En tu fish/config.fish anade:
#     #     source ~/.config/hypr/scripts/hypr-helpers.fish
#
#     # Test interactivo:
#     toggle-layout     # debe cambiar de layout y notificar
#     show-active       # imprime info de la ventana enfocada
#     send-to-empty     # mueve a un ws nuevo
#     watch-submap &    # corre en background; entra al submap resize
#                       # con SUPER+R y deberías ver el toast
#
# Bind opcional (al final, en 90-binds.conf):
#     bind = $mod CTRL SHIFT, M, exec, fish -c toggle-layout
# (Ya lo declaraste en el ejercicio 19 — ahora apunta a algo real.)
# =============================================================================
