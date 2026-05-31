#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/08_monitors_basico.conf"

@test "08.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "08.02 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

@test "08.03 catch-all wildcard presente" {
    grep -qE '^\s*monitor\s*=\s*,\s*preferred\s*,\s*auto\s*,\s*1\s*$' "$FILE"
}

@test "08.04 eDP-1 explicito 1920x1080@60 en 0x0 escala 1" {
    grep -qE '^\s*monitor\s*=\s*eDP-1,\s*1920x1080@60,\s*0x0,\s*1\s*$' "$FILE"
}

@test "08.05 DP-4 4K con escala fraccional 1.25 y auto-right" {
    grep -qE '^\s*monitor\s*=\s*DP-4,\s*3840x2160@60,\s*auto-right,\s*1\.25\s*$' "$FILE"
}

@test "08.06 HDMI-A-2 rotado con transform 1" {
    grep -qE '^\s*monitor\s*=\s*HDMI-A-2,.*transform,\s*1\s*$' "$FILE"
}

@test "08.07 HDMI-A-3 mirror de eDP-1" {
    grep -qE '^\s*monitor\s*=\s*HDMI-A-3,.*mirror,\s*eDP-1\s*$' "$FILE"
}

@test "08.08 DP-5 disabled" {
    grep -qE '^\s*monitor\s*=\s*DP-5,\s*disable\s*$' "$FILE"
}

@test "08.09 declara exactamente 6 directivas monitor (no de mas)" {
    n="$(grep -cE '^\s*monitor\s*=' "$FILE")"
    [ "$n" -eq 6 ] || { echo "esperaba 6, encontre $n"; return 1; }
}
