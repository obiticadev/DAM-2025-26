#!/usr/bin/env bash
# Test RUNTIME del ejercicio 22: DNS interno en red definida por el usuario.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
NET="mc-ej22-net"
SRV="mc-ej22-api"

cleanup() {
  docker rm -f "$SRV" >/dev/null 2>&1 || true
  docker network rm "$NET" >/dev/null 2>&1 || true
}
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
echo ">> Creando red $NET y servidor 'api' ..."
docker network create "$NET" >/dev/null
docker run -d --name "$SRV" --network "$NET" --network-alias api nginx:alpine >/dev/null

sleep 2
echo ">> Lanzando el cliente en la misma red (resuelve 'api' por nombre) ..."
OUT=$(docker run --rm --network "$NET" "$TAG" api)
echo "$OUT"
echo "$OUT" | grep -q "conectado a api por DNS" || { echo "ERROR: el cliente no resolvio/conecto con 'api' por DNS (salida: $OUT)"; exit 1; }
echo "   OK: resolucion por nombre en red definida por el usuario."
exit 0
