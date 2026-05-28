#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/03_modularizar_source.conf"

EXPECTED_SOURCES=(
    "~/.config/hypr/conf.d/10-env.conf"
    "~/.config/hypr/conf.d/20-monitors.conf"
    "~/.config/hypr/conf.d/30-input.conf"
    "~/.config/hypr/conf.d/40-general.conf"
    "~/.config/hypr/conf.d/50-decoration.conf"
    "~/.config/hypr/conf.d/60-animations.conf"
    "~/.config/hypr/conf.d/70-windowrules.conf"
    "~/.config/hypr/conf.d/80-workspaces.conf"
    "~/.config/hypr/conf.d/90-binds.conf"
    "~/.config/hypr/conf.d/99-autostart.conf"
)

@test "03.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "03.02 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

@test "03.03 archivo solo contiene comentarios y directivas source" {
    # Cualquier linea no vacia y no comentada DEBE empezar por `source =`
    bad="$(grep -nvE '^\s*(#|$|source\s*=)' "$FILE" || true)"
    if [ -n "$bad" ]; then
        echo "Lineas no validas (deben ser comentario o source =):"
        echo "$bad"
        return 1
    fi
}

@test "03.04 declara los 10 source = esperados" {
    for s in "${EXPECTED_SOURCES[@]}"; do
        if ! grep -qE "^\s*source\s*=\s*${s//./\\.}\s*$" "$FILE"; then
            echo "Falta o tiene typo: source = $s"
            return 1
        fi
    done
}

@test "03.05 orden correcto: 10-env primero, 99-autostart ultimo" {
    first="$(grep -nE '^\s*source\s*=' "$FILE" | head -1)"
    last="$(grep -nE '^\s*source\s*=' "$FILE" | tail -1)"
    echo "$first" | grep -q '10-env.conf'
    echo "$last"  | grep -q '99-autostart.conf'
}

@test "03.06 los 10 sources aparecen en orden ascendente por prefijo numerico" {
    nums="$(grep -oE 'conf\.d/[0-9]+-' "$FILE" | grep -oE '[0-9]+')"
    sorted="$(echo "$nums" | sort -n)"
    [ "$nums" = "$sorted" ]
}
