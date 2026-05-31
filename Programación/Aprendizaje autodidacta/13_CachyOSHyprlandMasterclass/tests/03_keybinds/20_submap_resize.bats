#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/20_submap_resize.conf"

@test "20.01 archivo existe" { [ -f "$FILE" ]; }
@test "20.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "20.03 bind de entrada \$mod+R submap resize" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*R,\s*submap,\s*resize' "$FILE"
}

@test "20.04 abre submap = resize" {
    grep -qE '^\s*submap\s*=\s*resize\s*$' "$FILE"
}

@test "20.05 binde flechas dentro del submap" {
    for d in left right up down; do
        grep -qE "^\s*binde\s*=\s*,\s*${d},\s*resizeactive" "$FILE" \
            || { echo "Falta binde flecha $d"; return 1; }
    done
}

@test "20.06 binde HJKL dentro del submap" {
    for k in h j k l; do
        grep -qE "^\s*binde\s*=\s*,\s*${k},\s*resizeactive" "$FILE" \
            || { echo "Falta binde tecla $k"; return 1; }
    done
}

@test "20.07 SHIFT+flechas con steps grandes (100)" {
    for d in left right up down; do
        grep -qE "^\s*binde\s*=\s*SHIFT,\s*${d},\s*resizeactive,\s*-?1?00\s+-?1?00" "$FILE" \
            || grep -qE "^\s*binde\s*=\s*SHIFT,\s*${d},\s*resizeactive,\s*-?100\s+0" "$FILE" \
            || grep -qE "^\s*binde\s*=\s*SHIFT,\s*${d},\s*resizeactive,\s*0\s+-?100" "$FILE" \
            || { echo "Falta SHIFT+$d con step 100"; return 1; }
    done
}

@test "20.08 presets 1/2/3 con resizeactive exact" {
    grep -qE '^\s*bind\s*=\s*,\s*1,\s*resizeactive,\s*exact\s+25%' "$FILE"
    grep -qE '^\s*bind\s*=\s*,\s*2,\s*resizeactive,\s*exact\s+50%' "$FILE"
    grep -qE '^\s*bind\s*=\s*,\s*3,\s*resizeactive,\s*exact\s+75%' "$FILE"
}

@test "20.09 salidas: Escape, Return, q, \$mod+R" {
    grep -qE '^\s*bind\s*=\s*,\s*Escape,\s*submap,\s*reset' "$FILE"
    grep -qE '^\s*bind\s*=\s*,\s*Return,\s*submap,\s*reset' "$FILE"
    grep -qE '^\s*bind\s*=\s*,\s*q,\s*submap,\s*reset' "$FILE"
    grep -qE '^\s*bind\s*=\s*\$mod,\s*R,\s*submap,\s*reset' "$FILE"
}

@test "20.10 cierra el submap (submap = reset al final)" {
    # La ultima ocurrencia de "submap =" no comentada debe ser "submap = reset"
    last="$(grep -nE '^\s*submap\s*=' "$FILE" | tail -1)"
    echo "$last" | grep -q 'submap\s*=\s*reset'
}
