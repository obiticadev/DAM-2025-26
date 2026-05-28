#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/06_gitignore_seguro.gitignore"

@test "06.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "06.02 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

# --- TODO 01 -----------------------------------------------------------------
@test "06.03 ignora vivaldi/" {
    grep -qE '^\s*vivaldi/\s*$' "$FILE"
}
@test "06.04 ignora opera/" {
    grep -qE '^\s*opera/\s*$' "$FILE"
}

# --- TODO 02 -----------------------------------------------------------------
@test "06.05 ignora **/runtime/" {
    grep -qE '^\s*\*\*/runtime/\s*$' "$FILE"
}
@test "06.06 ignora *.tmp" {
    grep -qE '^\s*\*\.tmp\s*$' "$FILE"
}

# --- TODO 03 -----------------------------------------------------------------
@test "06.07 ignora cliphist/" {
    grep -qE '^\s*cliphist/\s*$' "$FILE"
}
@test "06.08 ignora swww/" {
    grep -qE '^\s*swww/\s*$' "$FILE"
}
@test "06.09 ignora waybar/.cache/" {
    grep -qE '^\s*waybar/\.cache/\s*$' "$FILE"
}
@test "06.10 ignora nix/ (preparado)" {
    grep -qE '^\s*nix/\s*$' "$FILE"
}
@test "06.11 ignora libvirt/ (preparado)" {
    grep -qE '^\s*libvirt/\s*$' "$FILE"
}

# --- TODO 05: escape de espacios --------------------------------------------
@test "06.12 escapa 'Code Cache' con backslash" {
    grep -qE '^\s*\*/Code\\ Cache/\s*$' "$FILE"
}
@test "06.13 escapa 'Service Worker' con backslash" {
    grep -qE '^\s*\*/Service\\ Worker/\s*$' "$FILE"
}
@test "06.14 escapa 'Local Storage' con backslash" {
    grep -qE '^\s*\*/Local\\ Storage/\s*$' "$FILE"
}

# --- TODO 06: secretos extra -------------------------------------------------
@test "06.15 ignora **/*.key" {
    grep -qE '^\s*\*\*/\*\.key\s*$' "$FILE"
}
@test "06.16 ignora **/*.pem" {
    grep -qE '^\s*\*\*/\*\.pem\s*$' "$FILE"
}
@test "06.17 ignora **/api_key*" {
    grep -qE '^\s*\*\*/api_key\*\s*$' "$FILE"
}
@test "06.18 ignora **/*_token*" {
    grep -qE '^\s*\*\*/\*_token\*\s*$' "$FILE"
}
@test "06.19 ignora **/private*" {
    grep -qE '^\s*\*\*/private\*\s*$' "$FILE"
}

# --- TODO 07: logs rotados ---------------------------------------------------
@test "06.20 ignora *.log.*" {
    grep -qE '^\s*\*\.log\.\*\s*$' "$FILE"
}
@test "06.21 ignora **/Logs/ (mayuscula)" {
    grep -qE '^\s*\*\*/Logs/\s*$' "$FILE"
}

# --- TODO 08: history files de navegadores ----------------------------------
@test "06.22 ignora places.sqlite*" {
    grep -qE '^\s*\*\*/places\.sqlite\*\s*$' "$FILE"
}
@test "06.23 ignora Cookies*" {
    grep -qE '^\s*\*\*/Cookies\*\s*$' "$FILE"
}

# --- TODO 09: editores -------------------------------------------------------
@test "06.24 ignora .vscode/" {
    grep -qE '^\s*\.vscode/\s*$' "$FILE"
}
@test "06.25 ignora .idea/" {
    grep -qE '^\s*\.idea/\s*$' "$FILE"
}

# --- Integridad global -------------------------------------------------------
@test "06.26 mantiene las reglas originales esenciales" {
    for pat in 'mozilla/' 'chromium/' 'pulse/' '\*\.log' '\.env' 'gh/hosts\.yml'; do
        grep -qE "$pat" "$FILE" || { echo "Falta regla original: $pat"; return 1; }
    done
}
