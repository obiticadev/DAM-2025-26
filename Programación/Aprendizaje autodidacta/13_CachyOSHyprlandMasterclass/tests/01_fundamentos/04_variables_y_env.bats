#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/01_fundamentos/04_variables_y_env.conf"

@test "04.01 archivo existe" {
    [ -f "$FILE" ]
}

@test "04.02 todos los TODOs estan resueltos" {
    assert_no_unresolved_todos "$FILE"
}

# --- TODO 01: variables Hyprlang --------------------------------------------
@test "04.03 \$mod = SUPER" {
    grep -qE '^\s*\$mod\s*=\s*SUPER\s*$' "$FILE"
}
@test "04.04 \$modAlt = SUPER ALT" {
    grep -qE '^\s*\$modAlt\s*=\s*SUPER\s+ALT\s*$' "$FILE"
}
@test "04.05 \$modShift = SUPER SHIFT" {
    grep -qE '^\s*\$modShift\s*=\s*SUPER\s+SHIFT\s*$' "$FILE"
}
@test "04.06 \$terminal = kitty" {
    grep -qE '^\s*\$terminal\s*=\s*kitty\s*$' "$FILE"
}
@test "04.07 \$menu = wofi --show drun" {
    grep -qE '^\s*\$menu\s*=\s*wofi\s+--show\s+drun\s*$' "$FILE"
}
@test "04.08 \$fileMgr = dolphin" {
    grep -qE '^\s*\$fileMgr\s*=\s*dolphin\s*$' "$FILE"
}
@test "04.09 \$browser = firefox" {
    grep -qE '^\s*\$browser\s*=\s*firefox\s*$' "$FILE"
}
@test "04.10 \$editor = micro" {
    grep -qE '^\s*\$editor\s*=\s*micro\s*$' "$FILE"
}
@test "04.11 \$lock = hyprlock" {
    grep -qE '^\s*\$lock\s*=\s*hyprlock\s*$' "$FILE"
}
@test "04.12 \$colorPicker = hyprpicker -a" {
    grep -qE '^\s*\$colorPicker\s*=\s*hyprpicker\s+-a\s*$' "$FILE"
}

# --- TODO 02: env fcitx5 -----------------------------------------------------
@test "04.13 env GTK_IM_MODULE,fcitx" {
    grep -qE '^\s*env\s*=\s*GTK_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "04.14 env QT_IM_MODULE,fcitx" {
    grep -qE '^\s*env\s*=\s*QT_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "04.15 env XMODIFIERS,@im=fcitx" {
    grep -qE '^\s*env\s*=\s*XMODIFIERS,@im=fcitx\s*$' "$FILE"
}
@test "04.16 env SDL_IM_MODULE,fcitx" {
    grep -qE '^\s*env\s*=\s*SDL_IM_MODULE,fcitx\s*$' "$FILE"
}
@test "04.17 env GLFW_IM_MODULE,ibus" {
    grep -qE '^\s*env\s*=\s*GLFW_IM_MODULE,ibus\s*$' "$FILE"
}

# --- TODO 03: cursor ---------------------------------------------------------
@test "04.18 env XCURSOR_THEME" {
    grep -qE '^\s*env\s*=\s*XCURSOR_THEME,\S+\s*$' "$FILE"
}
@test "04.19 env XCURSOR_SIZE numerico" {
    grep -qE '^\s*env\s*=\s*XCURSOR_SIZE,[0-9]+\s*$' "$FILE"
}
@test "04.20 env HYPRCURSOR_THEME" {
    grep -qE '^\s*env\s*=\s*HYPRCURSOR_THEME,\S+\s*$' "$FILE"
}
@test "04.21 env HYPRCURSOR_SIZE numerico" {
    grep -qE '^\s*env\s*=\s*HYPRCURSOR_SIZE,[0-9]+\s*$' "$FILE"
}

# --- TODO 04: toolkit theming -----------------------------------------------
@test "04.22 env QT_QPA_PLATFORMTHEME presente" {
    grep -qE '^\s*env\s*=\s*QT_QPA_PLATFORMTHEME,' "$FILE"
}
@test "04.23 env QT_QPA_PLATFORM wayland;xcb" {
    grep -qE '^\s*env\s*=\s*QT_QPA_PLATFORM,wayland;xcb\s*$' "$FILE"
}
@test "04.24 env QT_WAYLAND_DISABLE_WINDOWDECORATION,1" {
    grep -qE '^\s*env\s*=\s*QT_WAYLAND_DISABLE_WINDOWDECORATION,1\s*$' "$FILE"
}
@test "04.25 env GDK_BACKEND wayland,x11,*" {
    grep -qE '^\s*env\s*=\s*GDK_BACKEND,wayland,x11,\*\s*$' "$FILE"
}

# --- TODO 05: apps especificas ----------------------------------------------
@test "04.26 env MOZ_ENABLE_WAYLAND,1" {
    grep -qE '^\s*env\s*=\s*MOZ_ENABLE_WAYLAND,1\s*$' "$FILE"
}
@test "04.27 env ELECTRON_OZONE_PLATFORM_HINT" {
    grep -qE '^\s*env\s*=\s*ELECTRON_OZONE_PLATFORM_HINT,' "$FILE"
}
@test "04.28 env _JAVA_AWT_WM_NONREPARENTING,1" {
    grep -qE '^\s*env\s*=\s*_JAVA_AWT_WM_NONREPARENTING,1\s*$' "$FILE"
}

# --- TODO 06: exec-once fcitx5 ----------------------------------------------
@test "04.29 exec-once fcitx5 -d --replace" {
    grep -qE '^\s*exec-once\s*=\s*fcitx5\s+-d\s+--replace\s*$' "$FILE"
}
