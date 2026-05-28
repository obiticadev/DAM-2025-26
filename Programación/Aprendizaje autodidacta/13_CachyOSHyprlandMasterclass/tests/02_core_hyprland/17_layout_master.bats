#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/17_layout_master.conf"

@test "17.01 archivo existe" { [ -f "$FILE" ]; }
@test "17.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "17.03 abre y cierra bloque master { }" {
    grep -qE '^\s*master\s*\{\s*$' "$FILE"
    grep -qE '^\s*\}\s*$' "$FILE"
}

@test "17.04 mfact float 0.0-1.0" {
    grep -qE '^\s*mfact\s*=\s*0\.[0-9]+\s*$' "$FILE"
}
@test "17.05 orientation declarada (left/right/top/bottom/center)" {
    grep -qE '^\s*orientation\s*=\s*(left|right|top|bottom|center)\s*$' "$FILE"
}
@test "17.06 new_status declarado" {
    grep -qE '^\s*new_status\s*=\s*(master|slave|inherit)\s*$' "$FILE"
}
@test "17.07 new_on_top declarado" {
    grep -qE '^\s*new_on_top\s*=\s*(true|false)\s*$' "$FILE"
}
@test "17.08 new_on_active declarado" {
    grep -qE '^\s*new_on_active\s*=\s*(before|after|none)\s*$' "$FILE"
}
@test "17.09 inherit_fullscreen = true" {
    grep -qE '^\s*inherit_fullscreen\s*=\s*true\s*$' "$FILE"
}
@test "17.10 slave_count_for_center_master declarado" {
    grep -qE '^\s*slave_count_for_center_master\s*=\s*[0-9]+' "$FILE"
}
@test "17.11 smart_resizing = true" {
    grep -qE '^\s*smart_resizing\s*=\s*true' "$FILE"
}
@test "17.12 special_scale_factor declarado" {
    grep -qE '^\s*special_scale_factor\s*=' "$FILE"
}
@test "17.13 comentario con hyprctl keyword para alternar layout" {
    grep -qE '^\s*#.*hyprctl\s+keyword\s+general:layout\s+master' "$FILE"
    grep -qE '^\s*#.*hyprctl\s+keyword\s+general:layout\s+dwindle' "$FILE"
}
