#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/03_keybinds/24_scratchpad_multi.conf"

@test "24.01 archivo existe" { [ -f "$FILE" ]; }
@test "24.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# PARTE A: workspace = special:...
@test "24.03 workspace special:terminal" {
    grep -qE '^\s*workspace\s*=\s*special:terminal.*on-created-empty.*kitty' "$FILE"
}
@test "24.04 workspace special:notes con nvim ~/notes.md" {
    grep -qE '^\s*workspace\s*=\s*special:notes.*on-created-empty.*nvim\s+~/notes\.md' "$FILE"
}
@test "24.05 workspace special:music" {
    grep -qE '^\s*workspace\s*=\s*special:music.*on-created-empty' "$FILE"
}
@test "24.06 workspace special:files con dolphin" {
    grep -qE '^\s*workspace\s*=\s*special:files.*on-created-empty.*dolphin' "$FILE"
}

# PARTE B: binds toggle
@test "24.07 \$mod+T togglespecialworkspace terminal" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*T,\s*togglespecialworkspace,\s*terminal' "$FILE"
}
@test "24.08 \$mod+N togglespecialworkspace notes" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*N,\s*togglespecialworkspace,\s*notes' "$FILE"
}
@test "24.09 \$mod+M togglespecialworkspace music" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*M,\s*togglespecialworkspace,\s*music' "$FILE"
}
@test "24.10 \$mod+O togglespecialworkspace files" {
    grep -qE '^\s*bind\s*=\s*\$mod,\s*O,\s*togglespecialworkspace,\s*files' "$FILE"
}

# Binds movetoworkspace special:
@test "24.11 \$modShift+T movetoworkspace special:terminal" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*T,\s*movetoworkspace,\s*special:terminal' "$FILE"
}
@test "24.12 \$modShift+N movetoworkspace special:notes" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*N,\s*movetoworkspace,\s*special:notes' "$FILE"
}
@test "24.13 \$modShift+M movetoworkspace special:music" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*M,\s*movetoworkspace,\s*special:music' "$FILE"
}
@test "24.14 \$modShift+O movetoworkspace special:files" {
    grep -qE '^\s*bind\s*=\s*\$modShift,\s*O,\s*movetoworkspace,\s*special:files' "$FILE"
}

# PARTE C: windowrules para scratch-*
@test "24.15 size para class scratch-term" {
    grep -qE '^\s*windowrulev2\s*=\s*size\s+\S+\s+\S+,\s*class:\^\(scratch-term\)\$' "$FILE"
}
@test "24.16 size para class scratch-notes" {
    grep -qE '^\s*windowrulev2\s*=\s*size\s+\S+\s+\S+,\s*class:\^\(scratch-notes\)\$' "$FILE"
}
@test "24.17 size para class scratch-music" {
    grep -qE '^\s*windowrulev2\s*=\s*size\s+\S+\s+\S+,\s*class:\^\(scratch-music\)\$' "$FILE"
}
@test "24.18 center para scratch-* (wildcard)" {
    grep -qE '^\s*windowrulev2\s*=\s*center,\s*class:\^\(scratch-\.\*\)\$' "$FILE"
}
