#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/14_gestures.conf"

@test "14.01 archivo existe" { [ -f "$FILE" ]; }
@test "14.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "14.03 abre y cierra bloque gestures { }" {
    grep -qE '^\s*gestures\s*\{\s*$' "$FILE"
    grep -qE '^\s*\}\s*$' "$FILE"
}

@test "14.04 workspace_swipe = true" {
    grep -qE '^\s*workspace_swipe\s*=\s*true\s*$' "$FILE"
}
@test "14.05 workspace_swipe_fingers (3 o 4)" {
    grep -qE '^\s*workspace_swipe_fingers\s*=\s*[34]\s*$' "$FILE"
}
@test "14.06 workspace_swipe_distance numerico" {
    grep -qE '^\s*workspace_swipe_distance\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "14.07 workspace_swipe_invert declarado" {
    grep -qE '^\s*workspace_swipe_invert\s*=\s*(true|false)\s*$' "$FILE"
}
@test "14.08 workspace_swipe_min_speed_to_force declarado" {
    grep -qE '^\s*workspace_swipe_min_speed_to_force\s*=\s*[0-9]+\s*$' "$FILE"
}
@test "14.09 workspace_swipe_cancel_ratio float" {
    grep -qE '^\s*workspace_swipe_cancel_ratio\s*=\s*0?\.[0-9]+\s*$' "$FILE"
}
@test "14.10 workspace_swipe_create_new declarado" {
    grep -qE '^\s*workspace_swipe_create_new\s*=\s*(true|false)\s*$' "$FILE"
}
@test "14.11 workspace_swipe_direction_lock declarado" {
    grep -qE '^\s*workspace_swipe_direction_lock\s*=\s*(true|false)\s*$' "$FILE"
}
@test "14.12 workspace_swipe_use_r declarado" {
    grep -qE '^\s*workspace_swipe_use_r\s*=\s*(true|false)\s*$' "$FILE"
}
@test "14.13 workspace_swipe_forever declarado" {
    grep -qE '^\s*workspace_swipe_forever\s*=\s*(true|false)\s*$' "$FILE"
}
