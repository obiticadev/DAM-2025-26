#!/usr/bin/env bash
# Test RUNTIME del ejercicio 14: la imagen debe pesar menos de 90 MB y funcionar.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
LIMIT_MB=90
LIMIT=$((LIMIT_MB * 1024 * 1024))

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) Funciona
OUT=$(docker run --rm "$TAG")
echo "$OUT" | grep -q "Imagen ligera funcionando" || { echo "ERROR: la app no imprimio lo esperado. Salida: $OUT"; exit 1; }
echo "   OK: la app funciona."

# 2) Tamano
SIZE=$(docker image inspect --format '{{.Size}}' "$TAG")
SIZE_MB=$((SIZE / 1024 / 1024))
echo "   Tamano de la imagen: ${SIZE_MB} MB (limite: ${LIMIT_MB} MB)"
if [ "$SIZE" -ge "$LIMIT" ]; then
  echo "ERROR: la imagen es demasiado grande. Usa la base Alpine."; exit 1
fi
echo "   OK: la imagen esta por debajo de ${LIMIT_MB} MB."
exit 0
