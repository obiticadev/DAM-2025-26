#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/19_keybinds_window_ops.conf"

@test "19.01 archivo existe" { [ -f "$FILE" ]; }
@test "19.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# Resize con binde
@test "19.03 binde \$mod+CTRL+left resizeactive" {
    grep -qE '^\s*binde\s*=\s*\$mod\s+CTRL,\s*left,\s*resizeactive' "$FILE"
}
@test "19.04 binde \$mod+CTRL+right resizeactive" {
    grep -qE '^\s*binde\s*=\s*\$mod\s+CTRL,\s*right,\s*resizeactive' "$FILE"
}
@test "19.05 binde \$mod+CTRL+up resizeactive" {
    grep -qE '^\s*binde\s*=\s*\$mod\s+CTRL,\s*up,\s*resizeactive' "$FILE"
}
@test "19.06 binde \$mod+CTRL+down resizeactive" {
    grep -qE '^\s*binde\s*=\s*\$mod\s+CTRL,\s*down,\s*resizeactive' "$FILE"
}

# Swap
@test "19.07 swapwindow con \$mod+ALT en 4 flechas" {
    for d in left right up down; do
        grep -qE "^\s*bind\s*=\s*\\\$mod\s+ALT,\s*${d},\s*swapwindow" "$FILE" \
            || { echo "Falta swap $d"; return 1; }
    done
}

# Pin / z-order
@test "19.08 \$mod+A togglepin" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*A,\s*togglepin' "$FILE"
}
@test "19.09 \$mod+U alterzorder top" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*U,\s*alterzorder,\s*top' "$FILE"
}
@test "19.10 \$modShift+U alterzorder bottom" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*U,\s*alterzorder,\s*bottom' "$FILE"
}

# Groups
@test "19.11 \$mod+G togglegroup" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*G,\s*togglegroup' "$FILE"
}
@test "19.12 \$modShift+Tab changegroupactive f" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*Tab,\s*changegroupactive,\s*f' "$FILE"
}
@test "19.13 \$mod+CTRL+Tab changegroupactive b" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL,\s*Tab,\s*changegroupactive,\s*b' "$FILE"
}
@test "19.14 \$mod+SHIFT+G moveoutofgroup" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+SHIFT,\s*G,\s*moveoutofgroup' "$FILE"
}

# Move floating
@test "19.15 binde \$mod+CTRL+SHIFT+flechas moveactive" {
    for d in left right up down; do
        grep -qE "^\s*binde\s*=\s*\\\$mod\s+CTRL\s+SHIFT,\s*${d},\s*moveactive" "$FILE" \
            || { echo "Falta moveactive $d"; return 1; }
    done
}

# Toggle layout
@test "19.16 \$mod+CTRL+SHIFT+M ejecuta toggle-layout.fish" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL\s+SHIFT,\s*M,\s*exec,.*toggle-layout\.fish' "$FILE"
}

# Cycle / urgent
@test "19.17 \$mod+grave cyclenext" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*grave,\s*cyclenext' "$FILE"
}
@test "19.18 \$modShift+grave cyclenext prev" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*grave,\s*cyclenext,\s*prev' "$FILE"
}
@test "19.19 \$mod+CTRL+U focusurgentorlast" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL,\s*U,\s*focusurgentorlast' "$FILE"
}
