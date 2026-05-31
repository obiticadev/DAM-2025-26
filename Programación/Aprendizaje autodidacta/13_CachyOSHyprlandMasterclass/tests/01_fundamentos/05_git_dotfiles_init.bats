#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/05_git_dotfiles_init.sh"

@test "05.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "05.02 shebang bash" {
    head -1 "$FILE" | grep -qE '^#!/usr/bin/env bash$'
}

@test "05.03 set -euo pipefail" {
    grep -qE '^\s*set\s+-euo\s+pipefail' "$FILE"
}

@test "05.04 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

@test "05.05 sin errores de sintaxis bash" {
    bash -n "$FILE"
}

@test "05.06 define ensure_config_is_repo" {
    grep -qE '^\s*ensure_config_is_repo\s*\(\)' "$FILE"
}
@test "05.07 define ensure_git_identity" {
    grep -qE '^\s*ensure_git_identity\s*\(\)' "$FILE"
}
@test "05.08 define ensure_remote_origin" {
    grep -qE '^\s*ensure_remote_origin\s*\(\)' "$FILE"
}
@test "05.09 define ensure_ssh_works" {
    grep -qE '^\s*ensure_ssh_works\s*\(\)' "$FILE"
}
@test "05.10 define report_status" {
    grep -qE '^\s*report_status\s*\(\)' "$FILE"
}

@test "05.11 main llama a las 5 funciones" {
    # Heuristica: dentro del cuerpo del script las 5 funciones se invocan
    for fn in ensure_config_is_repo ensure_git_identity ensure_remote_origin ensure_ssh_works report_status; do
        # Al menos una llamada (definicion + invocacion ⇒ 2 ocurrencias)
        count="$(grep -cE "\b${fn}\b" "$FILE")"
        [ "$count" -ge 2 ] || { echo "$fn no se invoca en main()"; return 1; }
    done
}

@test "05.12 usa la URL SSH correcta del repo" {
    grep -q 'git@github.com:obiticadev/CachyOS.git' "$FILE"
}

@test "05.13 referencia el usuario y email esperados" {
    grep -q 'obiticadev' "$FILE"
    grep -q 'obitica.dev@gmail.com' "$FILE"
}

@test "05.14 usa git -C \$CONFIG_DIR (no cd globales)" {
    # Evita `cd ~/.config` suelto. Permite si esta dentro de una subshell ()
    if grep -qE '^[[:space:]]*cd[[:space:]]+\$CONFIG_DIR' "$FILE"; then
        echo "Evita 'cd \$CONFIG_DIR' a nivel top — usa 'git -C \$CONFIG_DIR ...'"
        return 1
    fi
}

@test "05.15 idempotencia: ejecucion en HOME falso no muere catastroficamente" {
    skip_if_no_command bash
    # Sandbox: HOME apuntando a tmp vacio. El script debe ABORTAR limpiamente
    # cuando no hay repo y el usuario diria que no — pero en bats no hay tty
    # interactivo, asi que aceptamos cualquier exit code != 0 que NO sea
    # de "syntax error".
    tmp="$(mktemp -d)"
    HOME="$tmp" XDG_CONFIG_HOME="$tmp/.config" run bash "$FILE" </dev/null
    rm -rf "$tmp"
    # exit 0 (idempotente, todo OK por casualidad) o exit >0 (abortado limpio)
    # ambos son aceptables. Lo que NO toleramos es exit 2 de bash (syntax error).
    [ "$status" -ne 2 ]
}
