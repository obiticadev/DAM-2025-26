#!/usr/bin/env bats
load '../test_helper'

FILE="${MASTERCLASS_ROOT}/src/02_core_hyprland/10_workspaces_persistentes.conf"

@test "10.01 archivo existe" { [ -f "$FILE" ]; }
@test "10.02 todos los TODOs estan resueltos" { assert_no_unresolved_todos "$FILE"; }

# DP-1 1-5
@test "10.03 ws 1 monitor:DP-1, default:true, persistent:true" {
    grep -qE '^\s*workspace\s*=\s*1\b.*monitor:DP-1.*default:true.*persistent:true' "$FILE"
}
@test "10.04 ws 2 monitor:DP-1 persistent:true" {
    grep -qE '^\s*workspace\s*=\s*2\b.*monitor:DP-1.*persistent:true' "$FILE"
}
@test "10.05 ws 3 monitor:DP-1 persistent:true" {
    grep -qE '^\s*workspace\s*=\s*3\b.*monitor:DP-1.*persistent:true' "$FILE"
}
@test "10.06 ws 4 monitor:DP-1" {
    grep -qE '^\s*workspace\s*=\s*4\b.*monitor:DP-1' "$FILE"
}
@test "10.07 ws 5 monitor:DP-1" {
    grep -qE '^\s*workspace\s*=\s*5\b.*monitor:DP-1' "$FILE"
}

# HDMI 6-10
@test "10.08 ws 6 monitor:HDMI-A-1 default:true persistent:true" {
    grep -qE '^\s*workspace\s*=\s*6\b.*monitor:HDMI-A-1.*default:true.*persistent:true' "$FILE"
}
@test "10.09 ws 7 monitor:HDMI-A-1 persistent:true" {
    grep -qE '^\s*workspace\s*=\s*7\b.*monitor:HDMI-A-1.*persistent:true' "$FILE"
}
@test "10.10 ws 8 monitor:HDMI-A-1" {
    grep -qE '^\s*workspace\s*=\s*8\b.*monitor:HDMI-A-1' "$FILE"
}
@test "10.11 ws 9 monitor:HDMI-A-1" {
    grep -qE '^\s*workspace\s*=\s*9\b.*monitor:HDMI-A-1' "$FILE"
}
@test "10.12 ws 10 monitor:HDMI-A-1" {
    grep -qE '^\s*workspace\s*=\s*10\b.*monitor:HDMI-A-1' "$FILE"
}

# special
@test "10.13 special:magic con on-created-empty kitty" {
    grep -qE '^\s*workspace\s*=\s*special:magic.*on-created-empty:.*kitty' "$FILE"
}

# named
@test "10.14 name:music en HDMI-A-1 con auto-launch" {
    grep -qE '^\s*workspace\s*=\s*name:music.*monitor:HDMI-A-1.*on-created-empty' "$FILE"
}
@test "10.15 name:notes en DP-1" {
    grep -qE '^\s*workspace\s*=\s*name:notes.*monitor:DP-1' "$FILE"
}
