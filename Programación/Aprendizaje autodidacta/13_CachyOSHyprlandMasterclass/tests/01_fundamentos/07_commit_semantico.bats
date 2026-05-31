#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/07_commit_semantico.sh"

@test "07.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "07.02 shebang bash" {
    head -1 "$FILE" | grep -qE '^#!/usr/bin/env bash$'
}

@test "07.03 set -euo pipefail" {
    grep -qE '^\s*set\s+-euo\s+pipefail' "$FILE"
}

@test "07.04 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

@test "07.05 sin errores de sintaxis bash" {
    bash -n "$FILE"
}

@test "07.06 define ALLOWED_TYPES con los tipos esperados" {
    # Acepta tanto array como string con todos los tipos
    for t in feat fix refactor style docs chore revert perf; do
        grep -qE "\b${t}\b" "$FILE" || { echo "Tipo ausente: $t"; return 1; }
    done
}

@test "07.07 define KNOWN_SCOPES con scopes del bootcamp" {
    for s in hypr waybar wofi kitty fish; do
        grep -qE "\b${s}\b" "$FILE" || { echo "Scope ausente: $s"; return 1; }
    done
}

@test "07.08 define validate_type" {
    grep -qE '^\s*validate_type\s*\(\)' "$FILE"
}
@test "07.09 define validate_scope" {
    grep -qE '^\s*validate_scope\s*\(\)' "$FILE"
}
@test "07.10 define validate_subject" {
    grep -qE '^\s*validate_subject\s*\(\)' "$FILE"
}
@test "07.11 define interactive_mode" {
    grep -qE '^\s*interactive_mode\s*\(\)' "$FILE"
}
@test "07.12 define oneshot_mode" {
    grep -qE '^\s*oneshot_mode\s*\(\)' "$FILE"
}
@test "07.13 define do_commit" {
    grep -qE '^\s*do_commit\s*\(\)' "$FILE"
}
@test "07.14 define main" {
    grep -qE '^\s*main\s*\(\)' "$FILE"
}

@test "07.15 main usa el numero de args para decidir modo" {
    # Se espera referencia a $# en main
    grep -A 20 'main\s*()' "$FILE" | grep -qE '\$#'
}

@test "07.16 do_commit ejecuta git commit" {
    grep -A 30 'do_commit\s*()' "$FILE" | grep -qE 'git\s+commit'
}

# --- Test funcional con git real --------------------------------------------
@test "07.17 oneshot mode hace commit semantico valido (sandbox)" {
    skip_if_no_command git
    skip_if_no_command bash

    sandbox="$(mktemp -d)"
    cd "$sandbox"
    git init -q -b main
    git config user.email "test@example.com"
    git config user.name "Test"
    echo "hola" > foo.txt
    git add foo.txt

    run bash "$FILE" feat hypr "test de commit semantico"
    [ "$status" -eq 0 ] || { echo "$output"; return 1; }

    last="$(git log -1 --pretty=%s)"
    [ "$last" = "feat(hypr): test de commit semantico" ] || {
        echo "Mensaje actual: $last"
        return 1
    }

    cd /
    rm -rf "$sandbox"
}

@test "07.18 oneshot mode rechaza tipo invalido" {
    skip_if_no_command git

    sandbox="$(mktemp -d)"
    cd "$sandbox"
    git init -q -b main
    git config user.email "t@e.com"
    git config user.name "T"
    echo "x" > x ; git add x

    run bash "$FILE" feature hypr "no deberia commitear"
    [ "$status" -ne 0 ]
    # No debe haber commits
    run git log --oneline
    [ -z "$output" ]

    cd /
    rm -rf "$sandbox"
}

@test "07.19 oneshot mode rechaza subject que empieza con 'added '" {
    skip_if_no_command git
    sandbox="$(mktemp -d)"
    cd "$sandbox"
    git init -q -b main
    git config user.email "t@e.com" ; git config user.name "T"
    echo "x" > x ; git add x

    run bash "$FILE" feat hypr "added new bind"
    [ "$status" -ne 0 ]

    cd /
    rm -rf "$sandbox"
}

@test "07.20 oneshot mode rechaza subject > 72 chars" {
    skip_if_no_command git
    sandbox="$(mktemp -d)"
    cd "$sandbox"
    git init -q -b main
    git config user.email "t@e.com" ; git config user.name "T"
    echo "x" > x ; git add x

    long="$(printf 'a%.0s' {1..80})"
    run bash "$FILE" feat hypr "$long"
    [ "$status" -ne 0 ]

    cd /
    rm -rf "$sandbox"
}

@test "07.21 oneshot mode rechaza si no hay staged" {
    skip_if_no_command git
    sandbox="$(mktemp -d)"
    cd "$sandbox"
    git init -q -b main
    git config user.email "t@e.com" ; git config user.name "T"

    run bash "$FILE" feat hypr "no hay nada staged"
    [ "$status" -ne 0 ]

    cd /
    rm -rf "$sandbox"
}
