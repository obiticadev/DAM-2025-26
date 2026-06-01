#!/usr/bin/env bash
# Test RUNTIME del ejercicio 34: servicio opcional gobernado por un profile.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej34"
FILE="$EXDIR/compose.yaml"

cleanup() { docker compose -p "$PROJ" -f "$FILE" --profile debug down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup

# 1) Sin perfil: web si, herramientas no
echo ">> up SIN perfil ..."
docker compose -p "$PROJ" -f "$FILE" up -d || { echo "ERROR: 'docker compose up' fallo."; exit 1; }
sleep 2
WEB=$(docker compose -p "$PROJ" -f "$FILE" ps -q web | tr -d '[:space:]')
[ -n "$WEB" ] || { echo "ERROR: el servicio 'web' deberia estar en marcha."; exit 1; }
TOOLS=$(docker compose -p "$PROJ" -f "$FILE" ps -q herramientas | grep -c . || true)
if [ "$TOOLS" -ne 0 ]; then echo "ERROR: 'herramientas' NO deberia arrancar sin el perfil debug."; exit 1; fi
echo "   OK: sin perfil solo arranca 'web'."

# 2) Con perfil debug: herramientas tambien
echo ">> up CON --profile debug ..."
docker compose -p "$PROJ" -f "$FILE" --profile debug up -d || { echo "ERROR: 'docker compose --profile debug up' fallo."; exit 1; }
sleep 2
TOOLS2=$(docker compose -p "$PROJ" -f "$FILE" --profile debug ps -q herramientas | grep -c . || true)
if [ "$TOOLS2" -lt 1 ]; then echo "ERROR: 'herramientas' deberia arrancar con --profile debug (revisa profiles)."; exit 1; fi
echo "   OK: con --profile debug arranca tambien 'herramientas'."
exit 0
