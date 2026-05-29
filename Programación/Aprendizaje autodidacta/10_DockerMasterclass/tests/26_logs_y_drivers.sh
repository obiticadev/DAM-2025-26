#!/usr/bin/env bash
# Test RUNTIME del ejercicio 26: captura de logs y log driver json-file.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
C="mc-ej26"

cleanup() { docker rm -f "$C" >/dev/null 2>&1 || true; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
echo ">> Ejecutando con --log-driver json-file ..."
docker run --name "$C" --log-driver json-file --log-opt max-size=1m "$TAG" >/dev/null

LOGS=$(docker logs "$C" 2>&1)
LINEAS=$(echo "$LOGS" | grep -c "log linea [0-9]")
if [ "$LINEAS" -lt 5 ]; then echo "ERROR: esperaba 5 lineas 'log linea N' en docker logs y hay $LINEAS."; echo "$LOGS"; exit 1; fi
echo "   OK: docker logs capturo las 5 lineas de stdout."

DRIVER=$(docker inspect --format '{{.HostConfig.LogConfig.Type}}' "$C")
if [ "$DRIVER" != "json-file" ]; then echo "ERROR: el log driver es '$DRIVER', esperaba 'json-file'."; exit 1; fi
echo "   OK: el contenedor usa el log driver json-file."
exit 0
