#!/usr/bin/env bash
# Test RUNTIME del ejercicio 36: build multi-arch con buildx + manifest list.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
REG="mc-ej36-registry"
BUILDER="mc-ej36-builder"
REF="localhost:5006/masterclass/ej36:multi"

cleanup() {
  docker buildx use default >/dev/null 2>&1 || true
  docker buildx rm "$BUILDER" >/dev/null 2>&1 || true
  docker rm -f "$REG" >/dev/null 2>&1 || true
}
trap cleanup EXIT

cleanup
echo ">> Levantando registry:2 en localhost:5006 ..."
docker run -d -p 5006:5000 --name "$REG" registry:2 >/dev/null
sleep 2

echo ">> Creando builder buildx (network=host) ..."
docker buildx create --name "$BUILDER" --driver-opt network=host --use --bootstrap >/dev/null || { echo "ERROR: no se pudo crear el builder buildx."; exit 1; }

echo ">> build multi-arch (amd64 + arm64) y push ..."
docker buildx build --platform linux/amd64,linux/arm64 -t "$REF" --push "$EXDIR" || { echo "ERROR: fallo el build/push multi-arch."; exit 1; }

echo ">> Inspeccionando la manifest list ..."
OUT=$(docker buildx imagetools inspect "$REF")
echo "$OUT"
echo "$OUT" | grep -q "linux/amd64" || { echo "ERROR: falta la plataforma linux/amd64 en la manifest list."; exit 1; }
echo "$OUT" | grep -q "linux/arm64" || { echo "ERROR: falta la plataforma linux/arm64 en la manifest list."; exit 1; }
echo "   OK: la imagen publica amd64 y arm64 bajo el mismo tag."
exit 0
