#!/usr/bin/env bash
# Helper común para todas las suites bats de la masterclass.
# Carga bats-support / bats-assert / bats-file si están disponibles
# y define utilidades reutilizables.

# --- Carga librerías opcionales ---------------------------------------------
# En CachyOS: paru -S bats-assert bats-support bats-file
_load_lib() {
    local lib="$1"
    # Rutas estándar de Arch / CachyOS
    for p in \
        "/usr/lib/bats/${lib}/load.bash" \
        "/usr/lib/${lib}/load.bash" \
        "/usr/share/${lib}/load.bash"; do
        if [[ -f "$p" ]]; then
            # shellcheck disable=SC1090
            source "$p"
            return 0
        fi
    done
    return 1
}

_load_lib bats-support || true
_load_lib bats-assert  || true
_load_lib bats-file    || true

# --- Resolución de rutas ----------------------------------------------------
# Raíz del proyecto: dos niveles por encima de este archivo (tests/..).
MASTERCLASS_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
export MASTERCLASS_ROOT

src_file() {
    echo "${MASTERCLASS_ROOT}/src/$1"
}

# --- Aserciones específicas Hyprlang ----------------------------------------

# assert_directive <archivo> <clave>
# Valida que <archivo> contiene una línea no comentada con `<clave> = ...`
assert_directive() {
    local file="$1" key="$2"
    [[ -f "$file" ]] || {
        echo "Archivo no existe: $file"
        return 1
    }
    if ! grep -Eq "^[[:space:]]*${key}[[:space:]]*=" "$file"; then
        echo "Falta directiva '${key} = ...' en $file"
        return 1
    fi
}

# assert_directive_value <archivo> <clave> <regex valor>
assert_directive_value() {
    local file="$1" key="$2" pattern="$3"
    grep -Eq "^[[:space:]]*${key}[[:space:]]*=[[:space:]]*${pattern}" "$file" || {
        echo "Directiva '${key}' no matchea /${pattern}/ en $file"
        grep -E "^[[:space:]]*${key}[[:space:]]*=" "$file" || true
        return 1
    }
}

# assert_no_unresolved_todos <archivo>
assert_no_unresolved_todos() {
    local file="$1"
    if grep -Eqn '^[[:space:]]*#[[:space:]]*TODO[[:space:]]*[0-9]+:' "$file"; then
        echo "Quedan TODOs sin resolver en $file:"
        grep -En '^[[:space:]]*#[[:space:]]*TODO[[:space:]]*[0-9]+:' "$file"
        return 1
    fi
}

# skip_if_no_hyprland — saltar tests que necesitan sesión Hyprland viva.
skip_if_no_hyprland() {
    if [[ -z "${HYPRLAND_INSTANCE_SIGNATURE:-}" ]]; then
        skip "No hay sesión Hyprland activa (HYPRLAND_INSTANCE_SIGNATURE vacío)."
    fi
    command -v hyprctl >/dev/null || skip "hyprctl no está instalado."
}

# skip_if_no_command <cmd>
skip_if_no_command() {
    command -v "$1" >/dev/null || skip "$1 no está instalado."
}
