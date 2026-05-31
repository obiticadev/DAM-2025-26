#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/09_monitors_ultrawide.conf"

@test "09.01 archivo existe" { [ -f "$FILE" ]; }
@test "09.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "09.03 catch-all primero (antes que los monitores nombrados)" {
    catchall_line="$(grep -nE '^\s*monitor\s*=\s*,\s*preferred' "$FILE" | head -1 | cut -d: -f1)"
    dp1_line="$(grep -nE '^\s*monitor\s*=\s*DP-1' "$FILE" | head -1 | cut -d: -f1)"
    [ -n "$catchall_line" ] || { echo "Falta catch-all"; return 1; }
    [ -n "$dp1_line" ] || { echo "Falta DP-1"; return 1; }
    [ "$catchall_line" -lt "$dp1_line" ]
}

@test "09.04 DP-1 con 2560x1080@144 y posicion 0x0 escala 1" {
    grep -qE '^\s*monitor\s*=\s*DP-1,\s*2560x1080@144,\s*0x0,\s*1\b' "$FILE"
}

@test "09.05 DP-1 incluye vrr,2" {
    grep -qE '^\s*monitor\s*=\s*DP-1,.*vrr\s*,\s*2' "$FILE"
}

@test "09.06 DP-1 incluye bitdepth (8 o 10)" {
    grep -qE '^\s*monitor\s*=\s*DP-1,.*bitdepth\s*,\s*(8|10)' "$FILE"
}

@test "09.07 HDMI-A-1 con 1920x1080@60 escala 1" {
    grep -qE '^\s*monitor\s*=\s*HDMI-A-1,\s*1920x1080@60,\s*(2560x0|auto-right),\s*1\s*$' "$FILE"
}

@test "09.08 HDMI-A-1 NO tiene vrr (el 273V7 no soporta)" {
    ! grep -qE '^\s*monitor\s*=\s*HDMI-A-1.*vrr' "$FILE"
}

@test "09.09 descripcion comentada para futuro suspend-rename" {
    grep -qE '^\s*#\s*monitor\s*=\s*desc:.*LG' "$FILE"
}
