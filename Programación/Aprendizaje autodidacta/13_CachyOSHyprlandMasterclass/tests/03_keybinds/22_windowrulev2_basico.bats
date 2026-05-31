#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/22_windowrulev2_basico.conf"

@test "22.01 archivo existe" { [ -f "$FILE" ]; }
@test "22.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Diálogos
@test "22.03 float Open File en ingles" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*title:\^\(Open File\)\$' "$FILE"
}
@test "22.04 float Save File en ingles" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*title:\^\(Save File\)\$' "$FILE"
}
@test "22.05 float Abrir archivo en espanol" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*title:\^\(Abrir archivo\)\$' "$FILE"
}

# Apps floating
@test "22.06 kcalc float + center + size" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(org\\\.kde\\\.kcalc\)' "$FILE"
    grep -qE '^\s*windowrulev2\s*=\s*center,\s*class:\^\(org\\\.kde\\\.kcalc\)' "$FILE"
    grep -qE '^\s*windowrulev2\s*=\s*size\s+\S+\s+\S+,\s*class:\^\(org\\\.kde\\\.kcalc\)' "$FILE"
}
@test "22.07 pavucontrol float + size 600 500" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:.*pavucontrol' "$FILE"
    grep -qE '^\s*windowrulev2\s*=\s*size\s+600\s+500,\s*class:.*pavucontrol' "$FILE"
}
@test "22.08 nm-connection-editor float" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(nm-connection-editor\)\$' "$FILE"
}
@test "22.09 polkit stayfocused" {
    grep -qE '^\s*windowrulev2\s*=\s*stayfocused,\s*class:.*polkit' "$FILE"
}

# Auto-routing a workspaces
@test "22.10 firefox a ws 2 silent" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+2\s+silent,\s*class:\^\(firefox\)\$' "$FILE"
}
@test "22.11 IDE jetbrains a ws 3" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+3\s+silent,\s*class:\^\(jetbrains' "$FILE"
}
@test "22.12 VSCode a ws 3" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+3\s+silent,\s*class:\^\(Code\)\$' "$FILE"
}
@test "22.13 discord a ws 6" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+6\s+silent,\s*class:\^\(discord\)\$' "$FILE"
}
@test "22.14 spotify a ws 7" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+7\s+silent,\s*class:\^\(Spotify\)\$' "$FILE"
}
@test "22.15 steam a ws 5" {
    grep -qE '^\s*windowrulev2\s*=\s*workspace\s+5\s+silent,\s*class:\^\(steam\)\$' "$FILE"
}
@test "22.16 steam Friends List float" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(steam\)\$,\s*title:\^\(Friends List\)\$' "$FILE"
}

# Idleinhibit
@test "22.17 firefox idleinhibit fullscreen" {
    grep -qE '^\s*windowrulev2\s*=\s*idleinhibit\s+fullscreen,\s*class:\^\(firefox\)\$' "$FILE"
}
@test "22.18 mpv idleinhibit fullscreen" {
    grep -qE '^\s*windowrulev2\s*=\s*idleinhibit\s+fullscreen,\s*class:\^\(mpv\)\$' "$FILE"
}

# Opacity
@test "22.19 kitty opacity (active y inactive)" {
    grep -qE '^\s*windowrulev2\s*=\s*opacity\s+0?\.[0-9]+\s+0?\.[0-9]+,\s*class:\^\(kitty\)\$' "$FILE"
}
@test "22.20 firefox opacity 1.0 1.0" {
    grep -qE '^\s*windowrulev2\s*=\s*opacity\s+1\.0\s+1\.0,\s*class:\^\(firefox\)\$' "$FILE"
}
