#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/13_input_raton_trackpad.conf"

@test "13.01 archivo existe" { [ -f "$FILE" ]; }
@test "13.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "13.03 abre y cierra bloque input { }" {
    [ "$(grep -cE '^\s*input\s*\{\s*$' "$FILE")" -ge 1 ]
    [ "$(grep -cE '^\s*\}\s*$' "$FILE")" -ge 1 ]
}

@test "13.04 sensitivity declarado" {
    grep -qE '^\s*sensitivity\s*=' "$FILE"
}
@test "13.05 accel_profile = flat o adaptive" {
    grep -qE '^\s*accel_profile\s*=\s*(flat|adaptive)\s*$' "$FILE"
}
@test "13.06 force_no_accel declarado" {
    grep -qE '^\s*force_no_accel\s*=\s*(true|false)\s*$' "$FILE"
}
@test "13.07 follow_mouse declarado (0-3)" {
    grep -qE '^\s*follow_mouse\s*=\s*[0-3]\s*$' "$FILE"
}
@test "13.08 mouse_refocus declarado" {
    grep -qE '^\s*mouse_refocus\s*=\s*(true|false)\s*$' "$FILE"
}
@test "13.09 float_switch_override_focus declarado (0-2)" {
    grep -qE '^\s*float_switch_override_focus\s*=\s*[0-2]\s*$' "$FILE"
}
@test "13.10 natural_scroll declarado" {
    # OJO: tambien aparece dentro de touchpad { }; aceptamos cualquiera
    grep -qE 'natural_scroll\s*=' "$FILE"
}
@test "13.11 scroll_factor declarado" {
    grep -qE 'scroll_factor\s*=' "$FILE"
}

# Sub-bloque touchpad
@test "13.12 sub-bloque touchpad { } presente" {
    grep -qE '^\s*touchpad\s*\{\s*$' "$FILE"
}
@test "13.13 touchpad natural_scroll = true" {
    # Dentro del bloque touchpad, debe ser true
    awk '/touchpad\s*\{/,/\}/' "$FILE" | grep -qE 'natural_scroll\s*=\s*true'
}
@test "13.14 touchpad disable_while_typing = true" {
    awk '/touchpad\s*\{/,/\}/' "$FILE" | grep -qE 'disable_while_typing\s*=\s*true'
}
@test "13.15 touchpad tap-to-click = true" {
    awk '/touchpad\s*\{/,/\}/' "$FILE" | grep -qE 'tap-to-click\s*=\s*true'
}
@test "13.16 touchpad clickfinger_behavior = true" {
    awk '/touchpad\s*\{/,/\}/' "$FILE" | grep -qE 'clickfinger_behavior\s*=\s*true'
}
