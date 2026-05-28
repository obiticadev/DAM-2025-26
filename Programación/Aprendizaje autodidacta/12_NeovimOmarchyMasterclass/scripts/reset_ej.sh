#!/usr/bin/env bash
# ============================================================
# reset_ej.sh — Restaura un ejercicio a su estado inicial
#
# Uso:
#   bash scripts/reset_ej.sh <nivel> <ej>
#
# Funcionamiento:
#   Cada ejercicio tiene un backup .orig generado la primera vez que se abre.
#   Este script restaura el ejercicio desde ese backup.
#
#   Si NO existe .orig (primera vez que se rompe), el script lo crea
#   a partir del archivo actual ANTES de hacer nada. Esto significa que
#   debes resetear ANTES de empezar a editar, o aceptar tu estado actual como
#   "original". En la práctica: usa este script sólo cuando hayas roto algo y
#   no antes de empezar.
# ============================================================
set -u

GREEN=$'\033[1;32m'
RED=$'\033[1;31m'
YELLOW=$'\033[1;33m'
RESET=$'\033[0m'

if [ $# -lt 2 ]; then
  echo "Uso: $0 <nivel> <ej>"
  echo "Ej:  $0 01 03"
  exit 1
fi

nivel=$(printf "%02d" "$1")
ej=$(printf "%02d" "$2")
root="$(cd "$(dirname "$0")/.." && pwd)"

nivel_dir=$(ls -d "$root/ejercicios/nivel${nivel}"_* 2>/dev/null | head -1)
if [ -z "$nivel_dir" ]; then
  echo "${RED}❌ No existe nivel${nivel}_*${RESET}"; exit 1
fi

src=$(ls "$nivel_dir"/ej${ej}_*.* 2>/dev/null | head -1)
if [ -z "$src" ]; then
  echo "${RED}❌ No existe ej${ej}_*.* en $nivel_dir${RESET}"; exit 1
fi

backup="${src}.orig"

if [ ! -f "$backup" ]; then
  cp "$src" "$backup"
  echo "${YELLOW}ℹ Creado backup inicial: $(basename "$backup")${RESET}"
  echo "${YELLOW}  (El estado actual del archivo es lo que se considerará 'original')${RESET}"
else
  cp "$backup" "$src"
  echo "${GREEN}✅ Ejercicio ${nivel}.${ej} restaurado al estado inicial${RESET}"
fi
