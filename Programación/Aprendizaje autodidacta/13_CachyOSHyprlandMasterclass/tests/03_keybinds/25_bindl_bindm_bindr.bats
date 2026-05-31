#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/25_bindl_bindm_bindr.conf"

@test "25.01 archivo existe" { [ -f "$FILE" ]; }
@test "25.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Volume XF86
@test "25.03 bindle XF86AudioRaiseVolume con wpctl" {
    grep -qE '^\s*bindle\s*=\s*,\s*XF86AudioRaiseVolume,\s*exec,\s*wpctl' "$FILE"
}
@test "25.04 bindle XF86AudioLowerVolume con wpctl" {
    grep -qE '^\s*bindle\s*=\s*,\s*XF86AudioLowerVolume,\s*exec,\s*wpctl' "$FILE"
}
@test "25.05 bindl XF86AudioMute toggle" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioMute,\s*exec,\s*wpctl set-mute' "$FILE"
}
@test "25.06 bindl XF86AudioMicMute toggle" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioMicMute,\s*exec,\s*wpctl set-mute' "$FILE"
}
@test "25.07 wpctl usa flag -l 1.0 para limitar volumen" {
    grep -qE 'wpctl set-volume -l 1\.0' "$FILE"
}

# Fallback CTRL
@test "25.08 fallback \$mod+CTRL+up sube volumen" {
    grep -qE '^\s*bindle\s*=\s*\$mod\s+CTRL,\s*up,\s*exec,\s*wpctl' "$FILE"
}
@test "25.09 fallback \$mod+CTRL+down baja volumen" {
    grep -qE '^\s*bindle\s*=\s*\$mod\s+CTRL,\s*down,\s*exec,\s*wpctl' "$FILE"
}

# Brillo
@test "25.10 brillo XF86MonBrightnessUp con brightnessctl" {
    grep -qE '^\s*bindle\s*=\s*,\s*XF86MonBrightnessUp,\s*exec,\s*brightnessctl' "$FILE"
}
@test "25.11 brillo XF86MonBrightnessDown con brightnessctl" {
    grep -qE '^\s*bindle\s*=\s*,\s*XF86MonBrightnessDown,\s*exec,\s*brightnessctl' "$FILE"
}

# Playback
@test "25.12 bindl XF86AudioPlay playerctl" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioPlay,\s*exec,\s*playerctl' "$FILE"
}
@test "25.13 bindl XF86AudioNext playerctl next" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioNext,\s*exec,\s*playerctl\s+next' "$FILE"
}
@test "25.14 bindl XF86AudioPrev playerctl previous" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioPrev,\s*exec,\s*playerctl\s+previous' "$FILE"
}
@test "25.15 fallback F5/F6/F7 con \$mod" {
    grep -qE '^\s*bindl\s*=\s*\$mod,\s*F5,\s*exec,\s*playerctl\s+previous' "$FILE"
    grep -qE '^\s*bindl\s*=\s*\$mod,\s*F6,\s*exec,\s*playerctl\s+play-pause' "$FILE"
    grep -qE '^\s*bindl\s*=\s*\$mod,\s*F7,\s*exec,\s*playerctl\s+next' "$FILE"
}

# Screenshots
@test "25.16 Print pantalla completa con grim a wl-copy" {
    grep -qE '^\s*bind\s*=\s*,\s*Print,\s*exec,\s*grim\s+-\s*\|\s*wl-copy' "$FILE"
}
@test "25.17 SHIFT+Print seleccion con slurp" {
    grep -qE '^\s*bind\s*=\s*SHIFT,\s*Print,\s*exec,\s*grim.*slurp.*wl-copy' "$FILE"
}
@test "25.18 \$mod+Print swappy" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Print,\s*exec,\s*grim.*slurp.*swappy' "$FILE"
}
@test "25.19 \$modShift+Print a archivo con timestamp" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+SHIFT,\s*Print,\s*exec,.*grim.*Screenshots.*date' "$FILE"
}

# Notificaciones mako
@test "25.20 \$mod+Z dismiss mako" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Z,\s*exec,\s*makoctl\s+dismiss' "$FILE"
}
@test "25.21 \$modShift+Z dismiss --all" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*Z,\s*exec,\s*makoctl\s+dismiss\s+--all' "$FILE"
}
