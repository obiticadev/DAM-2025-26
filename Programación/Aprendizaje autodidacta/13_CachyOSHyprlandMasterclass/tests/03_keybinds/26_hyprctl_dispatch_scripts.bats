#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/26_hyprctl_dispatch_scripts.fish"

@test "26.01 archivo existe" { [ -f "$FILE" ]; }
@test "26.02 shebang fish" {
    head -1 "$FILE" | grep -qE '^#!/usr/bin/env fish$'
}
@test "26.03 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Funciones declaradas
@test "26.04 function toggle-layout declarada" {
    grep -qE '^\s*function\s+toggle-layout' "$FILE"
}
@test "26.05 function send-to-empty declarada" {
    grep -qE '^\s*function\s+send-to-empty' "$FILE"
}
@test "26.06 function show-active declarada" {
    grep -qE '^\s*function\s+show-active' "$FILE"
}
@test "26.07 function watch-submap declarada" {
    grep -qE '^\s*function\s+watch-submap' "$FILE"
}
@test "26.08 function hypr-helpers-help declarada" {
    grep -qE '^\s*function\s+hypr-helpers-help' "$FILE"
}

# toggle-layout: usa hyprctl getoption y keyword
@test "26.09 toggle-layout usa hyprctl getoption general:layout" {
    awk '/function\s+toggle-layout/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+getoption\s+general:layout'
}
@test "26.10 toggle-layout usa hyprctl keyword general:layout" {
    awk '/function\s+toggle-layout/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+keyword\s+general:layout'
}
@test "26.11 toggle-layout notifica" {
    awk '/function\s+toggle-layout/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+notify'
}

# send-to-empty
@test "26.12 send-to-empty usa jq" {
    awk '/function\s+send-to-empty/,/^end/' "$FILE" \
        | grep -qE 'jq'
}
@test "26.13 send-to-empty consulta hyprctl workspaces" {
    awk '/function\s+send-to-empty/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+workspaces'
}

# show-active
@test "26.14 show-active usa hyprctl activewindow -j" {
    awk '/function\s+show-active/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+activewindow\s+-j'
}
@test "26.15 show-active extrae .class .title .workspace" {
    awk '/function\s+show-active/,/^end/' "$FILE" \
        | grep -qE '\.class' && \
    awk '/function\s+show-active/,/^end/' "$FILE" \
        | grep -qE '\.title'
}

# watch-submap
@test "26.16 watch-submap usa socat con .socket2.sock" {
    awk '/function\s+watch-submap/,/^end/' "$FILE" \
        | grep -qE 'socat.*\.socket2\.sock'
}
@test "26.17 watch-submap filtra eventos submap>>" {
    awk '/function\s+watch-submap/,/^end/' "$FILE" \
        | grep -qE 'submap>>'
}
@test "26.18 watch-submap usa hyprctl notify" {
    awk '/function\s+watch-submap/,/^end/' "$FILE" \
        | grep -qE 'hyprctl\s+notify'
}

# Help
@test "26.19 help menciona todas las funciones" {
    awk '/function\s+hypr-helpers-help/,/^end/' "$FILE" > /tmp/_help.txt
    for fn in toggle-layout send-to-empty show-active watch-submap; do
        grep -qE "\b${fn}\b" /tmp/_help.txt || { echo "Falta $fn en help"; return 1; }
    done
}

# Sintaxis
@test "26.20 fish parse sin errores (si fish esta instalado)" {
    if ! command -v fish >/dev/null; then
        skip "fish no instalado"
    fi
    fish -n "$FILE"
}
