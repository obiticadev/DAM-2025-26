#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/18_keybinds_apps.conf"

@test "18.01 archivo existe" { [ -f "$FILE" ]; }
@test "18.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# SECCIÓN A: apps
@test "18.03 bind \$mod+Return ejecuta \$terminal" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Return,\s*exec,\s*\$terminal\s*$' "$FILE"
}
@test "18.04 bind \$mod+SPACE ejecuta \$menu" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*SPACE,\s*exec,\s*\$menu\s*$' "$FILE"
}
@test "18.05 bind \$mod+E ejecuta \$fileMgr" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*E,\s*exec,\s*\$fileMgr\s*$' "$FILE"
}
@test "18.06 bind \$mod+B ejecuta \$browser" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*B,\s*exec,\s*\$browser\s*$' "$FILE"
}
@test "18.07 bind \$mod+X ejecuta \$colorPicker" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*X,\s*exec,\s*\$colorPicker\s*$' "$FILE"
}

# SECCIÓN B: lifecycle
@test "18.08 \$mod+Q killactive" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Q,\s*killactive' "$FILE"
}
@test "18.09 \$mod+L exec \$lock" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*L,\s*exec,\s*\$lock' "$FILE"
}
@test "18.10 \$modAlt+SPACE power-menu" {
    grep -qE '^\s*bind\s*=\s*\$modAlt,\s*SPACE,\s*exec,.*power-menu' "$FILE"
}
@test "18.11 \$modShift+E exit" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*E,\s*exit' "$FILE"
}

# SECCIÓN C: estado
@test "18.12 \$mod+V togglefloating" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*V,\s*togglefloating' "$FILE"
}
@test "18.13 \$mod+F fullscreen,1" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*F,\s*fullscreen,\s*1' "$FILE"
}
@test "18.14 \$modShift+F fullscreen,0 (maximize)" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*F,\s*fullscreen,\s*0' "$FILE"
}
@test "18.15 \$mod+C centerwindow" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*C,\s*centerwindow' "$FILE"
}
@test "18.16 \$mod+P pseudo" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*P,\s*pseudo' "$FILE"
}
@test "18.17 \$mod+J togglesplit" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*J,\s*togglesplit' "$FILE"
}

# SECCIÓN D: foco
@test "18.18 movefocus en las 4 flechas con \$mod" {
    for d in left right up down; do
        grep -qE "^\s*bind\s*=\s*\\\$mod,\s*${d},\s*movefocus" "$FILE" \
            || { echo "Falta movefocus $d"; return 1; }
    done
}

# SECCIÓN E: mover
@test "18.19 movewindow con \$modShift en las 4 flechas" {
    for d in left right up down; do
        grep -qE "^\s*bind\s*=\s*\\\$modShift,\s*${d},\s*movewindow" "$FILE" \
            || { echo "Falta movewindow $d"; return 1; }
    done
}

# SECCIÓN F: workspaces
@test "18.20 \$mod + numeros 1-9 + 0 = workspace N" {
    for n in 1 2 3 4 5 6 7 8 9 0; do
        grep -qE "^\s*bind\s*=\s*\\\$mod,\s*${n},\s*workspace" "$FILE" \
            || { echo "Falta workspace bind para $n"; return 1; }
    done
}
@test "18.21 \$modShift + numeros 1-0 = movetoworkspace" {
    for n in 1 2 3 4 5 6 7 8 9 0; do
        grep -qE "^\s*bind\s*=\s*\\\$modShift,\s*${n},\s*movetoworkspace" "$FILE" \
            || { echo "Falta movetoworkspace para $n"; return 1; }
    done
}

@test "18.22 \$mod+CTRL+flechas = workspace e+/-1" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL,\s*right,\s*workspace,\s*e\+1' "$FILE"
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL,\s*left,\s*workspace,\s*e-1' "$FILE"
}

# SECCIÓN G: rueda y Tab
@test "18.23 rueda \$mod+mouse_down e+1" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*mouse_down,\s*workspace,\s*e\+1' "$FILE"
}
@test "18.24 \$mod+Tab workspace previous" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Tab,\s*workspace,\s*previous' "$FILE"
}

# SECCIÓN H: scratchpad
@test "18.25 \$mod+S togglespecialworkspace magic" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*S,\s*togglespecialworkspace,\s*magic' "$FILE"
}
@test "18.26 \$modShift+S movetoworkspace special:magic" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*S,\s*movetoworkspace,\s*special:magic' "$FILE"
}

# SECCIÓN I: reload
@test "18.27 \$modShift+R reload" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*R,\s*exec,\s*hyprctl\s+reload' "$FILE"
}
@test "18.28 \$mod+CTRL+R reload con notify" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL,\s*R,\s*exec,.*hyprctl\s+reload.*hyprctl\s+notify' "$FILE"
}

# SECCIÓN J: ratón
@test "18.29 bindm \$mod+mouse:272 movewindow" {
    grep -qE '^\s*bindm\s*=\s*\$mod,\s*mouse:272,\s*movewindow' "$FILE"
}
@test "18.30 bindm \$mod+mouse:273 resizewindow" {
    grep -qE '^\s*bindm\s*=\s*\$mod,\s*mouse:273,\s*resizewindow' "$FILE"
}
