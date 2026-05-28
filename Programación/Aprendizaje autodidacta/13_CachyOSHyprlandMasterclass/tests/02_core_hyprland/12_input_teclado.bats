#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/12_input_teclado.conf"

@test "12.01 archivo existe" { [ -f "$FILE" ]; }
@test "12.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "12.03 abre y cierra bloque input { }" {
    [ "$(grep -cE '^\s*input\s*\{\s*$' "$FILE")" -ge 1 ]
    [ "$(grep -cE '^\s*\}\s*$' "$FILE")" -ge 1 ]
}

@test "12.04 kb_layout = es,us" {
    grep -qE '^\s*kb_layout\s*=\s*es,us\s*$' "$FILE"
}
@test "12.05 kb_variant declarado (vacio o con valores)" {
    grep -qE '^\s*kb_variant\s*=' "$FILE"
}
@test "12.06 kb_options incluye grp:alt_shift_toggle" {
    grep -qE '^\s*kb_options\s*=.*grp:alt_shift_toggle' "$FILE"
}
@test "12.07 kb_options incluye caps:escape" {
    grep -qE '^\s*kb_options\s*=.*caps:escape' "$FILE"
}
@test "12.08 kb_options incluye compose:ralt" {
    grep -qE '^\s*kb_options\s*=.*compose:ralt' "$FILE"
}
@test "12.09 kb_model declarado (pc105 o similar)" {
    grep -qE '^\s*kb_model\s*=\s*pc10[45]\s*$' "$FILE"
}
@test "12.10 repeat_delay entre 100 y 500" {
    val="$(grep -oE 'repeat_delay\s*=\s*[0-9]+' "$FILE" | grep -oE '[0-9]+' | head -1)"
    [ -n "$val" ] && [ "$val" -ge 100 ] && [ "$val" -le 500 ]
}
@test "12.11 repeat_rate entre 20 y 60" {
    val="$(grep -oE 'repeat_rate\s*=\s*[0-9]+' "$FILE" | grep -oE '[0-9]+' | head -1)"
    [ -n "$val" ] && [ "$val" -ge 20 ] && [ "$val" -le 60 ]
}
@test "12.12 resolve_binds_by_sym = true" {
    grep -qE '^\s*resolve_binds_by_sym\s*=\s*true\s*$' "$FILE"
}
@test "12.13 numlock_by_default declarado (true o false)" {
    grep -qE '^\s*numlock_by_default\s*=\s*(true|false)\s*$' "$FILE"
}
@test "12.14 referencia comentada a device { } per-device" {
    grep -qE '^\s*#\s*device\s*\{' "$FILE"
}
