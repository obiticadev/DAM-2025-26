#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/15_general_gaps_borders.conf"

@test "15.01 archivo existe" { [ -f "$FILE" ]; }
@test "15.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Paleta
@test "15.03 declara variable \$base con hex de 8 chars" {
    grep -qE '^\s*\$base\s*=\s*[0-9a-fA-F]{8}\s*$' "$FILE"
}
@test "15.04 declara variable \$blue" {
    grep -qE '^\s*\$blue\s*=\s*[0-9a-fA-F]{8}\s*$' "$FILE"
}
@test "15.05 declara variable \$mauve" {
    grep -qE '^\s*\$mauve\s*=\s*[0-9a-fA-F]{8}\s*$' "$FILE"
}
@test "15.06 declara variable \$surface" {
    grep -qE '^\s*\$surface\s*=\s*[0-9a-fA-F]{8}\s*$' "$FILE"
}
@test "15.07 al menos 10 variables de paleta" {
    n="$(grep -cE '^\s*\$[a-z]+\s*=\s*[0-9a-fA-F]{8}\s*$' "$FILE")"
    [ "$n" -ge 10 ] || { echo "encontre $n variables, esperaba >=10"; return 1; }
}

# Bloque general
@test "15.08 abre y cierra bloque general { }" {
    grep -qE '^\s*general\s*\{\s*$' "$FILE"
    grep -qE '^\s*\}\s*$' "$FILE"
}

@test "15.09 gaps_in numerico" {
    grep -qE '^\s*gaps_in\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "15.10 gaps_out declarado" {
    grep -qE '^\s*gaps_out\s*=' "$FILE"
}
@test "15.11 gaps_workspaces declarado" {
    grep -qE '^\s*gaps_workspaces\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "15.12 border_size declarado" {
    grep -qE '^\s*border_size\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "15.13 no_border_on_floating declarado" {
    grep -qE '^\s*no_border_on_floating\s*=\s*(true|false)\s*$' "$FILE"
}
@test "15.14 col.active_border usa variables (gradiente)" {
    grep -qE '^\s*col\.active_border\s*=\s*rgba\(\$blue\)\s+rgba\(\$mauve\)' "$FILE"
}
@test "15.15 col.inactive_border usa \$surface" {
    grep -qE '^\s*col\.inactive_border\s*=\s*rgba\(\$surface\)' "$FILE"
}
@test "15.16 cursor_inactive_timeout declarado" {
    grep -qE '^\s*cursor_inactive_timeout\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "15.17 layout = dwindle" {
    grep -qE '^\s*layout\s*=\s*dwindle\s*$' "$FILE"
}
@test "15.18 allow_tearing declarado" {
    grep -qE '^\s*allow_tearing\s*=\s*(true|false)\s*$' "$FILE"
}
@test "15.19 resize_on_border = true" {
    grep -qE '^\s*resize_on_border\s*=\s*true\s*$' "$FILE"
}
@test "15.20 extend_border_grab_area declarado" {
    grep -qE '^\s*extend_border_grab_area\s*=\s*[0-9]+' "$FILE"
}
@test "15.21 hover_icon_on_border = true" {
    grep -qE '^\s*hover_icon_on_border\s*=\s*true' "$FILE"
}
@test "15.22 sub-bloque snap { } con enabled = true" {
    awk '/snap\s*\{/,/\}/' "$FILE" | grep -qE 'enabled\s*=\s*true'
}
