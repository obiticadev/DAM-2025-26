#!/usr/bin/env bash
# Test RUNTIME del ejercicio 16: imagen sobre scratch < 5 MB y funcional.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
LIMIT_MB=5
LIMIT=$((LIMIT_MB * 1024 * 1024))

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

OUT=$(docker run --rm "$TAG")
echo "$OUT" | grep -q "Binario corriendo sobre scratch" || { echo "ERROR: salida inesperada: $OUT"; exit 1; }
echo "   OK: el binario funciona sobre scratch."

SIZE=$(docker image inspect --format '{{.Size}}' "$TAG")
SIZE_MB=$((SIZE / 1024 / 1024))
echo "   Tamano de la imagen: ${SIZE_MB} MB (limite: ${LIMIT_MB} MB)"
if [ "$SIZE" -ge "$LIMIT" ]; then
  echo "ERROR: la imagen supera ${LIMIT_MB} MB. Compila estatico y usa scratch."; exit 1
fi
echo "   OK: imagen minima por debajo de ${LIMIT_MB} MB."
exit 0
