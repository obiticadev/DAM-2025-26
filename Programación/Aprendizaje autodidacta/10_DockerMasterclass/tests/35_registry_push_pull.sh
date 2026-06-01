#!/usr/bin/env bash
# Test RUNTIME del ejercicio 35: push/pull contra un registry:2 local.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
REG="mc-ej35-registry"
REF="localhost:5005/masterclass/ej35:1.0"

cleanup() {
  docker rm -f "$REG" >/dev/null 2>&1 || true
  docker rmi "$REF" >/dev/null 2>&1 || true
}
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
echo ">> Levantando registry:2 en localhost:5005 ..."
docker run -d -p 5005:5000 --name "$REG" registry:2 >/dev/null
sleep 2

echo ">> tag + push ..."
docker tag "$TAG" "$REF"
docker push "$REF" || { echo "ERROR: fallo el push al registry."; exit 1; }

echo ">> Borrando copias locales y haciendo pull ..."
docker rmi "$REF" >/dev/null 2>&1 || true
docker pull "$REF" || { echo "ERROR: fallo el pull desde el registry."; exit 1; }

OUT=$(docker run --rm "$REF")
echo "$OUT"
echo "$OUT" | grep -q "imagen desde el registry local" || { echo "ERROR: la imagen descargada no imprimio lo esperado (salida: $OUT)"; exit 1; }
echo "   OK: push y pull correctos; la imagen descargada funciona."
exit 0
