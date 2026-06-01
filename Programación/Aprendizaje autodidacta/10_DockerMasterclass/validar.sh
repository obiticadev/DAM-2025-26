#!/usr/bin/env bash
# ============================================================================
#  RUNNER UNIVERSAL DE VALIDACION - Docker Masterclass (Bash / WSL / Linux)
#  Uso:  ./validar.sh <NN>     ej:  ./validar.sh 01
# ============================================================================
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT"

if [ $# -lt 1 ]; then echo "Uso: ./validar.sh <NN>"; exit 2; fi
NN=$(printf "%02d" "$((10#$1))")
TAG="masterclass/ej${NN}:latest"

C_CYAN='\033[0;36m'; C_RED='\033[0;31m'; C_GREEN='\033[0;32m'; C_YEL='\033[0;33m'; C_OFF='\033[0m'

header() { echo; echo -e "${C_CYAN}============================================================${C_OFF}"; echo -e "${C_CYAN}  $1${C_OFF}"; echo -e "${C_CYAN}============================================================${C_OFF}"; }
fail()   { echo; echo -e "${C_RED}============================================================${C_OFF}"; echo -e "${C_RED}  X  EJERCICIO ${NN} NO SUPERADO${C_OFF}"; echo -e "${C_RED}  $1${C_OFF}"; echo -e "${C_RED}============================================================${C_OFF}"; exit 1; }
pass()   { echo; echo -e "${C_GREEN}============================================================${C_OFF}"; echo -e "${C_GREEN}  OK  EJERCICIO ${NN} SUPERADO${C_OFF}"; echo -e "${C_GREEN}============================================================${C_OFF}"; exit 0; }

# --- Localiza la carpeta del ejercicio (ahora anidada por bloque) ----------
# Los ejercicios viven en  ejercicios/<bloque>/NN_nombre/ . Buscamos en
# profundidad y filtramos por ENUNCIADO.md para no confundir el ejercicio con
# la carpeta de bloque (que puede compartir prefijo numerico).
EXDIR=""
while IFS= read -r d; do
    if [ -f "$d/ENUNCIADO.md" ]; then EXDIR="$d"; break; fi
done < <(find ejercicios -type d -name "${NN}_*" | sort)
[ -z "$EXDIR" ] && fail "No existe ningun ejercicio ejercicios/**/${NN}_*"
header "VALIDANDO EJERCICIO ${NN}  ($(basename "$EXDIR"))"

# --- 1) Ruta RUNTIME -------------------------------------------------------
RUNTIME=$(find tests -maxdepth 1 -name "${NN}_*.sh" | head -n1 || true)
if [ -n "$RUNTIME" ]; then
    echo -e "${C_YEL}>> Test de tipo RUNTIME: $(basename "$RUNTIME")${C_OFF}"
    chmod +x "$RUNTIME"
    if "$RUNTIME" "$EXDIR" "$TAG" "$ROOT"; then pass; else fail "El script runtime devolvio errores."; fi
fi

# --- 2) Ruta CST -----------------------------------------------------------
YAML=$(find tests -maxdepth 1 -name "${NN}_*.yaml" | head -n1 || true)
[ -z "$YAML" ] && fail "No hay test (.yaml ni .sh) para el ejercicio ${NN}."

echo -e "${C_YEL}>> Construyendo imagen ${TAG} ...${C_OFF}"
if [ -f "$EXDIR/build.sh" ]; then
    chmod +x "$EXDIR/build.sh"; "$EXDIR/build.sh" "$TAG" "$EXDIR" || fail "Fallo el build personalizado."
else
    docker build -t "$TAG" "$EXDIR" || fail "Fallo 'docker build'. Revisa tu Dockerfile."
fi

if [ -f "$EXDIR/Dockerfile" ]; then
    echo -e "${C_YEL}>> Linter hadolint ...${C_OFF}"
    # DL3008/DL3018 (pin de versiones apt/apk) relajadas; el resto es gate estricto desde ej07.
    if docker run --rm -i hadolint/hadolint --ignore DL3008 --ignore DL3018 < "$EXDIR/Dockerfile"; then
        echo -e "${C_GREEN}   hadolint: limpio.${C_OFF}"
    else
        if [ "$((10#$NN))" -ge 7 ]; then
            fail "hadolint encontro violaciones (obligatorio limpiarlas desde el ej. 07)."
        else
            echo -e "${C_YEL}   (aviso) hadolint sugiere mejoras; en este bloque aun no es obligatorio.${C_OFF}"
        fi
    fi
fi

echo -e "${C_YEL}>> container-structure-test ($(basename "$YAML")) ...${C_OFF}"
docker run --rm \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "${ROOT}:/work" -w /work \
    gcr.io/gcp-runtimes/container-structure-test:latest \
    test --image "$TAG" --config "tests/$(basename "$YAML")" \
    || fail "container-structure-test: la imagen no cumple la especificacion."

pass
