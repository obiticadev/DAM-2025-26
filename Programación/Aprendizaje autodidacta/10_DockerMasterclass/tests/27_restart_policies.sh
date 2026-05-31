#!/usr/bin/env bash
# Test RUNTIME del ejercicio 27: la restart policy reinicia un proceso que falla.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
C="mc-ej27"

cleanup() { docker rm -f "$C" >/dev/null 2>&1 || true; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
echo ">> Lanzando con --restart on-failure:3 ..."
docker run -d --name "$C" --restart on-failure:3 "$TAG" >/dev/null

COUNT=0
for _ in $(seq 1 30); do
  sleep 1
  COUNT=$(docker inspect --format '{{.RestartCount}}' "$C")
  if [ "$COUNT" -ge 2 ]; then break; fi
done
if [ "$COUNT" -lt 2 ]; then echo "ERROR: RestartCount = $COUNT (esperaba >= 2). La politica de reinicio no actuo."; exit 1; fi
echo "   OK: Docker reinicio el contenedor (RestartCount = $COUNT)."
exit 0
