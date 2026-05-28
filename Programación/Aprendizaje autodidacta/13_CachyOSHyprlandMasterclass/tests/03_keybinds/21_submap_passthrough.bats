#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/21_submap_passthrough.conf"

@test "21.01 archivo existe" { [ -f "$FILE" ]; }
@test "21.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

@test "21.03 bind de entrada con \$mod+CTRL+ALT+P" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL\s+ALT,\s*P,\s*submap,\s*passthrough' "$FILE"
}

@test "21.04 abre submap = passthrough" {
    grep -qE '^\s*submap\s*=\s*passthrough\s*$' "$FILE"
}

@test "21.05 bind de salida \$mod+CTRL+ALT+P submap reset" {
    # En el submap, mismo combo cierra
    awk '/submap\s*=\s*passthrough/,/submap\s*=\s*reset/' "$FILE" \
        | grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL\s+ALT,\s*P,\s*submap,\s*reset'
}

@test "21.06 fallback de salida con XF86PowerOff" {
    awk '/submap\s*=\s*passthrough/,/submap\s*=\s*reset/' "$FILE" \
        | grep -qE '^\s*bind\s*=\s*,\s*XF86PowerOff,\s*submap,\s*reset'
}

@test "21.07 notificacion al entrar (toast Passthrough ON)" {
    grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL\s+ALT,\s*P,\s*exec,\s*hyprctl\s+notify.*Passthrough\s+ON' "$FILE"
}

@test "21.08 notificacion al salir (toast Passthrough OFF)" {
    awk '/submap\s*=\s*passthrough/,/submap\s*=\s*reset/' "$FILE" \
        | grep -qE '^\s*bind\s*=\s*\$mod\s+CTRL\s+ALT,\s*P,\s*exec,\s*hyprctl\s+notify.*Passthrough\s+OFF'
}

@test "21.09 NO declara Escape como salida (evita romper VM)" {
    awk '/submap\s*=\s*passthrough/,/submap\s*=\s*reset/' "$FILE" \
        | grep -qE '^\s*bind\s*=\s*,\s*Escape,\s*submap,\s*reset' && {
            echo "Escape esta declarado como salida — eliminalo (puede romper VMs)"
            return 1
        }
    true
}

@test "21.10 cierra el submap (submap = reset al final)" {
    last="$(grep -nE '^\s*submap\s*=' "$FILE" | tail -1)"
    echo "$last" | grep -q 'submap\s*=\s*reset'
}
