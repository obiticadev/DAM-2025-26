#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/02_hyprlang_sintaxis.conf"

@test "02.01 archivo del ejercicio existe" {
    [ -f "$FILE" ]
}

@test "02.02 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

# --- TODO 01: variables ------------------------------------------------------
@test "02.03 declara variable \$mod = SUPER" {
    grep -qE '^\s*\$mod\s*=\s*SUPER\b' "$FILE"
}
@test "02.04 declara variable \$terminal = kitty" {
    grep -qE '^\s*\$terminal\s*=\s*kitty\b' "$FILE"
}
@test "02.05 declara variable \$menu con wofi --show drun" {
    grep -qE '^\s*\$menu\s*=\s*wofi\s+--show\s+drun' "$FILE"
}
@test "02.06 declara variable \$fileMgr = dolphin" {
    grep -qE '^\s*\$fileMgr\s*=\s*dolphin\b' "$FILE"
}

# --- TODO 02: env fcitx5 -----------------------------------------------------
@test "02.07 env GTK_IM_MODULE = fcitx" {
    grep -qE '^\s*env\s*=\s*GTK_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "02.08 env QT_IM_MODULE = fcitx" {
    grep -qE '^\s*env\s*=\s*QT_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "02.09 env XMODIFIERS = @im=fcitx" {
    grep -qE '^\s*env\s*=\s*XMODIFIERS,@im=fcitx\s*$' "$FILE"
}
@test "02.10 env SDL_IM_MODULE = fcitx" {
    grep -qE '^\s*env\s*=\s*SDL_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "02.11 env GLFW_IM_MODULE = ibus" {
    grep -qE '^\s*env\s*=\s*GLFW_IM_MODULE,ibus\s*$' "$FILE"
}

# --- TODO 03: bloques --------------------------------------------------------
@test "02.12 bloque general { ... } con gaps_in 5" {
    grep -Pzo '(?s)general\s*\{[^}]*gaps_in\s*=\s*5' "$FILE" >/dev/null
}
@test "02.13 bloque general con gaps_out 10" {
    grep -Pzo '(?s)general\s*\{[^}]*gaps_out\s*=\s*10' "$FILE" >/dev/null
}
@test "02.14 bloque general con border_size 2" {
    grep -Pzo '(?s)general\s*\{[^}]*border_size\s*=\s*2' "$FILE" >/dev/null
}
@test "02.15 bloque general con layout dwindle" {
    grep -Pzo '(?s)general\s*\{[^}]*layout\s*=\s*dwindle' "$FILE" >/dev/null
}
@test "02.16 bloque decoration { blur { enabled = true } } anidado" {
    grep -Pzo '(?s)decoration\s*\{[^}]*blur\s*\{[^}]*enabled\s*=\s*true' "$FILE" >/dev/null
}

# --- TODO 04: binds ----------------------------------------------------------
@test "02.17 bind SUPER+Return ejecuta terminal" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*Return,\s*exec,\s*\$terminal' "$FILE"
}
@test "02.18 bind SUPER+SPACE ejecuta menu" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*SPACE,\s*exec,\s*\$menu' "$FILE"
}
@test "02.19 bind SUPER+E ejecuta fileMgr" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*E,\s*exec,\s*\$fileMgr' "$FILE"
}

# --- TODO 05: windowrulev2 ---------------------------------------------------
@test "02.20 windowrulev2 para org.kde.kcalc" {
    grep -qE '^\s*windowrulev2\s*=.*class:\^\(org\.kde\.kcalc\)\$' "$FILE"
}
@test "02.21 windowrulev2 con float y centrado (kcalc)" {
    # Acepta una sola regla combinada o dos reglas separadas con mismo matcher
    grep -qE '^\s*windowrulev2\s*=\s*float.*kcalc' "$FILE"
    grep -qE '^\s*windowrulev2\s*=\s*center.*kcalc' "$FILE"
}
@test "02.22 windowrulev2 para pavucontrol con size 600 500" {
    grep -qE '^\s*windowrulev2\s*=\s*size\s+600\s+500.*pavucontrol' "$FILE"
}

# --- TODO 06: bindl multimedia ----------------------------------------------
@test "02.23 bindl para XF86AudioRaiseVolume" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioRaiseVolume,\s*exec,\s*wpctl' "$FILE"
}
@test "02.24 bindl para XF86AudioLowerVolume" {
    grep -qE '^\s*bindl\s*=\s*,\s*XF86AudioLowerVolume,\s*exec,\s*wpctl' "$FILE"
}
@test "02.25 cabecera visual de seccion presente" {
    grep -qE '^\s*#\s*-+\s*SECCIÓN:' "$FILE"
}

# --- TODO 07: source ---------------------------------------------------------
@test "02.26 source = a 99-overrides.conf" {
    grep -qE '^\s*source\s*=\s*~/.config/hypr/conf\.d/99-overrides\.conf' "$FILE"
}
