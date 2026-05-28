#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/23_windowrulev2_picture_in_picture.conf"

@test "23.01 archivo existe" { [ -f "$FILE" ]; }
@test "23.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Firefox PIP
@test "23.03 firefox PIP float" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.04 firefox PIP pin" {
    grep -qE '^\s*windowrulev2\s*=\s*pin,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.05 firefox PIP size 640 360" {
    grep -qE '^\s*windowrulev2\s*=\s*size\s+640\s+360,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.06 firefox PIP move 100%-660 100%-380" {
    grep -qE '^\s*windowrulev2\s*=\s*move\s+100%-660\s+100%-380,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.07 firefox PIP noborder" {
    grep -qE '^\s*windowrulev2\s*=\s*noborder,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.08 firefox PIP noshadow" {
    grep -qE '^\s*windowrulev2\s*=\s*noshadow,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.09 firefox PIP keepaspectratio" {
    grep -qE '^\s*windowrulev2\s*=\s*keepaspectratio,\s*title:\^\(Picture-in-Picture\)\$' "$FILE"
}

# Brave/Chrome PIP via class
@test "23.10 brave PIP por class" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(Picture-in-Picture\)\$' "$FILE"
}
@test "23.11 brave PIP pin class" {
    grep -qE '^\s*windowrulev2\s*=\s*pin,\s*class:\^\(Picture-in-Picture\)\$' "$FILE"
}

# mpv miniplayer
@test "23.12 mpv PIP via title PIP" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(mpv\)\$,\s*title:.*PIP' "$FILE"
}
@test "23.13 mpv keepaspectratio" {
    grep -qE '^\s*windowrulev2\s*=\s*keepaspectratio,\s*class:\^\(mpv\)\$' "$FILE"
}

# OBS
@test "23.14 OBS float" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(com\\\.obsproject\\\.Studio\)\$' "$FILE"
}

# Zoom
@test "23.15 zoom Meeting float" {
    grep -qE '^\s*windowrulev2\s*=\s*float,\s*class:\^\(zoom\)\$,\s*title:\^\(Zoom Meeting\)\$' "$FILE"
}

# Widgets (eww/conky)
@test "23.16 eww no decoraciones" {
    for r in noblur noshadow noborder norounding noanim pin; do
        grep -qE "^\s*windowrulev2\s*=\s*${r},\s*class:\^\(eww-\.\*\)\\\$" "$FILE" \
            || { echo "Falta regla $r para eww"; return 1; }
    done
}
@test "23.17 conky nofocus" {
    grep -qE '^\s*windowrulev2\s*=\s*nofocus,\s*class:\^\(Conky\)\$' "$FILE"
}
