#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/11_workspaces_reglas.conf"

@test "11.01 archivo existe" { [ -f "$FILE" ]; }
@test "11.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "11.03 ws 1 sin gaps (gapsin:0 gapsout:0)" {
    grep -qE '^\s*workspace\s*=\s*1\b.*gapsin:0.*gapsout:0' "$FILE"
}
@test "11.04 ws 2 con on-created-empty firefox" {
    grep -qE '^\s*workspace\s*=\s*2\b.*on-created-empty:\[silent\]\s*firefox' "$FILE"
}
@test "11.05 ws 3 persistente sin auto-launch" {
    grep -qE '^\s*workspace\s*=\s*3\b.*persistent:true\s*$' "$FILE"
}
@test "11.06 ws 4 con gapsout:5" {
    grep -qE '^\s*workspace\s*=\s*4\b.*gapsout:5' "$FILE"
}
@test "11.07 ws 5 sin decoracion (border:false rounding:false)" {
    grep -qE '^\s*workspace\s*=\s*5\b.*border:false' "$FILE"
    grep -qE '^\s*workspace\s*=\s*5\b.*rounding:false' "$FILE"
}
@test "11.08 ws 5 sin shadow" {
    grep -qE '^\s*workspace\s*=\s*5\b.*shadow:false' "$FILE"
}
@test "11.09 ws 6 con on-created-empty (discord o similar)" {
    grep -qE '^\s*workspace\s*=\s*6\b.*on-created-empty' "$FILE"
}
@test "11.10 ws 7 con auto-launch spotify" {
    grep -qE '^\s*workspace\s*=\s*7\b.*on-created-empty:\[silent\]\s*spotify' "$FILE"
}
@test "11.11 ws 8, 9, 10 declarados en HDMI-A-1" {
    for n in 8 9 10; do
        grep -qE "^\s*workspace\s*=\s*${n}\b.*monitor:HDMI-A-1" "$FILE" || {
            echo "Falta ws $n"; return 1; }
    done
}
@test "11.12 special:magic declarado con on-created-empty" {
    grep -qE '^\s*workspace\s*=\s*special:magic.*on-created-empty' "$FILE"
}
@test "11.13 al menos 11 declaraciones workspace=" {
    n="$(grep -cE '^\s*workspace\s*=' "$FILE")"
    [ "$n" -ge 11 ] || { echo "esperaba >=11, encontre $n"; return 1; }
}
