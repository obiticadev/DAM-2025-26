#!/usr/bin/env bash
# ============================================================
# verificar.sh — Comprueba un ejercicio del bootcamp
#
# Uso:
#   bash scripts/verificar.sh <nivel> <ej>
#
# Ejemplos:
#   bash scripts/verificar.sh 01 03      # nivel01, ejercicio 03
#   bash scripts/verificar.sh 14 02      # nivel14, ejercicio 02 (lanza mvn test)
#
# Funcionamiento:
#   - Por defecto hace `diff -u` entre ejercicios/.../ejMM_*.* y solucion/.../ejMM_*.*
#   - Para el nivel 14 (Boss Final) delega en `mvn test`.
# ============================================================
set -u

GREEN=$'\033[1;32m'
RED=$'\033[1;31m'
YELLOW=$'\033[1;33m'
DIM=$'\033[2m'
RESET=$'\033[0m'

usage() {
  echo "Uso: $0 <nivel> <ej>"
  echo "     $0 01 03    → verifica ejercicios/nivel01_*/ej03_*.* contra solucion/..."
  echo "     $0 14       → ejecuta 'mvn test' en el Boss Final"
  exit 1
}

[ $# -lt 1 ] && usage
nivel=$(printf "%02d" "$1" 2>/dev/null) || { echo "Nivel inválido: $1"; exit 1; }

# --- Caso especial: Boss Final ---
if [ "$nivel" = "14" ]; then
  cd "$(dirname "$0")/../ejercicios/nivel14_boss_final" || {
    echo "${RED}❌ No existe ejercicios/nivel14_boss_final${RESET}"; exit 1; }
  echo "${YELLOW}▶ Lanzando 'mvn clean test' en el Boss Final…${RESET}"
  mvn clean test
  rc=$?
  if [ $rc -eq 0 ]; then
    echo ""
    echo "${GREEN}✅ Boss Final: todos los tests en verde — ¡bootcamp completado!${RESET}"
  else
    echo ""
    echo "${RED}❌ Boss Final: hay tests rojos. Revisa el output de Maven arriba.${RESET}"
  fi
  exit $rc
fi

# --- Caso normal: diff de ejercicio vs solución ---
[ $# -lt 2 ] && usage
ej=$(printf "%02d" "$2" 2>/dev/null) || { echo "Ejercicio inválido: $2"; exit 1; }

# Resolver carpeta del nivel (glob por prefijo, ej: nivel01_*)
root="$(cd "$(dirname "$0")/.." && pwd)"
nivel_dir=$(ls -d "$root/ejercicios/nivel${nivel}"_* 2>/dev/null | head -1)
sol_dir=$(ls -d "$root/solucion/nivel${nivel}"_* 2>/dev/null | head -1)

if [ -z "$nivel_dir" ] || [ -z "$sol_dir" ]; then
  echo "${RED}❌ No encuentro carpetas nivel${nivel}_* en ejercicios/ o solucion/${RESET}"
  exit 1
fi

# Resolver archivo del ejercicio (cualquier extensión)
src=$(ls "$nivel_dir"/ej${ej}_*.* 2>/dev/null | head -1)
sol=$(ls "$sol_dir"/ej${ej}_*.* 2>/dev/null | head -1)

if [ -z "$src" ] || [ -z "$sol" ]; then
  echo "${RED}❌ No encuentro ej${ej}_*.* en $nivel_dir o su solución${RESET}"
  exit 1
fi

echo "${DIM}Comparando:${RESET}"
echo "  ${DIM}tu trabajo:${RESET} $src"
echo "  ${DIM}solución:  ${RESET} $sol"
echo ""

if diff -q "$sol" "$src" > /dev/null 2>&1; then
  echo "${GREEN}✅ Ejercicio ${nivel}.${ej} CORRECTO${RESET}"
  exit 0
else
  echo "${RED}❌ Ejercicio ${nivel}.${ej} INCORRECTO — diferencias:${RESET}"
  echo ""
  diff -u --color=always "$sol" "$src" 2>/dev/null || diff -u "$sol" "$src"
  echo ""
  echo "${YELLOW}💡 Pista:${RESET} Las líneas con '-' son lo esperado, las '+' son lo que tienes."
  echo "${DIM}   Si te has perdido, restaura con:  bash scripts/reset_ej.sh ${nivel} ${ej}${RESET}"
  exit 1
fi
