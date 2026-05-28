#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/16_layout_dwindle.conf"

@test "16.01 archivo existe" { [ -f "$FILE" ]; }
@test "16.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "16.03 abre y cierra bloque dwindle { }" {
    grep -qE '^\s*dwindle\s*\{\s*$' "$FILE"
    grep -qE '^\s*\}\s*$' "$FILE"
}

@test "16.04 pseudotile = true" {
    grep -qE '^\s*pseudotile\s*=\s*true\s*$' "$FILE"
}
@test "16.05 preserve_split = true" {
    grep -qE '^\s*preserve_split\s*=\s*true\s*$' "$FILE"
}
@test "16.06 smart_split declarado" {
    grep -qE '^\s*smart_split\s*=\s*(true|false)\s*$' "$FILE"
}
@test "16.07 smart_resizing = true" {
    grep -qE '^\s*smart_resizing\s*=\s*true\s*$' "$FILE"
}
@test "16.08 force_split declarado (0/1/2)" {
    grep -qE '^\s*force_split\s*=\s*[012]\s*$' "$FILE"
}
@test "16.09 special_scale_factor float entre 0 y 1" {
    grep -qE '^\s*special_scale_factor\s*=\s*0\.[0-9]+' "$FILE"
}
@test "16.10 split_width_multiplier declarado" {
    grep -qE '^\s*split_width_multiplier\s*=' "$FILE"
}
@test "16.11 use_active_for_splits = true" {
    grep -qE '^\s*use_active_for_splits\s*=\s*true\s*$' "$FILE"
}
@test "16.12 default_split_ratio declarado" {
    grep -qE '^\s*default_split_ratio\s*=' "$FILE"
}
@test "16.13 no_gaps_when_only = 2" {
    grep -qE '^\s*no_gaps_when_only\s*=\s*2\s*$' "$FILE"
}
