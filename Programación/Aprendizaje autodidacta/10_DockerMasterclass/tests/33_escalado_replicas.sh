#!/usr/bin/env bash
# Test RUNTIME del ejercicio 33: 3 replicas en ejecucion.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej33"
FILE="$EXDIR/compose.yaml"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d ..."
docker compose -p "$PROJ" -f "$FILE" up -d || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1; }

sleep 2
N=$(docker compose -p "$PROJ" -f "$FILE" ps -q worker | grep -c .)
if [ "$N" -ne 3 ]; then echo "ERROR: esperaba 3 replicas de 'worker' y hay $N (usa deploy.replicas: 3)."; exit 1; fi
echo "   OK: 3 replicas de 'worker' en ejecucion."
exit 0
