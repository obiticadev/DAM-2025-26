#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/01_xdg_audit.sh"

@test "01.01 archivo del ejercicio existe" {
    [ -f "$FILE" ]
}

@test "01.02 shebang bash correcto" {
    head -1 "$FILE" | grep -qE '^#!/usr/bin/env bash$'
}

@test "01.03 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

@test "01.04 implementa funcion xdg_var" {
    grep -qE '^[[:space:]]*xdg_var[[:space:]]*\(\)' "$FILE" \
        || grep -qE '^[[:space:]]*function[[:space:]]+xdg_var' "$FILE"
}

@test "01.05 menciona las 4 variables XDG base" {
    for v in XDG_CONFIG_HOME XDG_DATA_HOME XDG_STATE_HOME XDG_CACHE_HOME; do
        grep -q "$v" "$FILE" || { echo "Falta mención a $v"; return 1; }
    done
}

@test "01.06 detecta legacy files en HOME" {
    grep -qE '\.bashrc|\.zshrc|\.gitconfig|\.vimrc|\.tmux\.conf' "$FILE"
}

@test "01.07 imprime cabeceras con print_header" {
    # El script trae print_header definida; basta con que la use >= 4 veces
    [ "$(grep -c 'print_header ' "$FILE")" -ge 4 ]
}

@test "01.08 ejecuta sin errores de sintaxis bash" {
    bash -n "$FILE"
}

@test "01.09 ejecucion real produce salida con seccion XDG" {
    skip_if_no_command bash
    run bash "$FILE"
    [ "$status" -eq 0 ] || {
        echo "$output"
        return 1
    }
    echo "$output" | grep -qiE 'XDG'
}

@test "01.10 ejecucion real produce seccion git" {
    run bash "$FILE"
    [ "$status" -eq 0 ]
    echo "$output" | grep -qiE 'git'
}
