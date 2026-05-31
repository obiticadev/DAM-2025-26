#!/usr/bin/env bash
# Test RUNTIME del ejercicio 12: el contenedor debe llegar a estado 'healthy'.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
CNAME="mc-ej12"

cleanup() { docker rm -f "$CNAME" >/dev/null 2>&1 || true; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) HEALTHCHECK declarado
HC=$(docker inspect --format '{{json .Config.Healthcheck}}' "$TAG")
if [ -z "$HC" ] || [ "$HC" = "null" ]; then
  echo "ERROR: la imagen no declara HEALTHCHECK."; exit 1
fi
echo "   OK: HEALTHCHECK declarado."

# 2) Debe llegar a healthy
cleanup
docker run -d --name "$CNAME" "$TAG" >/dev/null
status="starting"
for _ in $(seq 1 40); do
  status=$(docker inspect --format '{{.State.Health.Status}}' "$CNAME" 2>/dev/null | tr -d '[:space:]')
  [ "$status" = "healthy" ] && break
  if [ "$status" = "unhealthy" ]; then echo "ERROR: contenedor unhealthy."; exit 1; fi
  sleep 1
done
[ "$status" = "healthy" ] || { echo "ERROR: no llego a healthy (estado: $status)."; exit 1; }
echo "   OK: el contenedor alcanzo el estado healthy."
exit 0
