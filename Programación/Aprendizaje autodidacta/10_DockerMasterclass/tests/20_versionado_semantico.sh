#!/usr/bin/env bash
# Test RUNTIME del ejercicio 20: version OCI + re-etiquetado conservando ID.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
BASE="masterclass/ej20"
EXTRA=("${BASE}:1.4.2" "${BASE}:1.4" "${BASE}:1")

cleanup() { for t in "${EXTRA[@]}"; do docker rmi "$t" >/dev/null 2>&1 || true; done; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) Label version
VER=$(docker inspect --format '{{index .Config.Labels "org.opencontainers.image.version"}}' "$TAG")
[ "$VER" = "1.4.2" ] || { echo "ERROR: version no es 1.4.2 (encontrado: '$VER')"; exit 1; }
echo "   OK: org.opencontainers.image.version = 1.4.2"

# 2) Funciona
OUT=$(docker run --rm "$TAG")
echo "$OUT" | grep -q "1.4.2" || { echo "ERROR: no imprimio 1.4.2 (salida: $OUT)"; exit 1; }
echo "   OK: la imagen imprime su version."

# 3) Re-etiquetar conserva el ID
ID=$(docker inspect --format '{{.Id}}' "$TAG")
for t in "${EXTRA[@]}"; do
  docker tag "$TAG" "$t" >/dev/null
  TID=$(docker inspect --format '{{.Id}}' "$t")
  [ "$TID" = "$ID" ] || { echo "ERROR: el tag $t tiene distinto ID."; exit 1; }
done
echo "   OK: 1.4.2 / 1.4 / 1 comparten el mismo Image ID."
exit 0
