#!/usr/bin/env bash
# Test RUNTIME del ejercicio 21: imagen endurecida (no-root) + escaneo CVEs best-effort.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) Imprime "imagen endurecida"
OUT=$(docker run --rm "$TAG")
echo "$OUT" | grep -q "imagen endurecida" || { echo "ERROR: no imprimio 'imagen endurecida' (salida: $OUT)"; exit 1; }
echo "   OK: la imagen imprime 'imagen endurecida'."

# 2) No corre como root
WHO=$(docker run --rm "$TAG" whoami | tr -d '[:space:]')
if [ "$WHO" = "root" ] || [ -z "$WHO" ]; then echo "ERROR: el contenedor corre como root (whoami: '$WHO')"; exit 1; fi
echo "   OK: corre como usuario sin privilegios ('$WHO')."

# 3) Escaneo de CVEs (mejor esfuerzo): informativo, no bloquea
if docker scout version >/dev/null 2>&1; then
  echo ">> docker scout disponible: analizando CVEs (informativo) ..."
  docker scout cves "$TAG" || true
else
  echo "   AVISO: 'docker scout' no esta instalado; se omite el escaneo de CVEs."
fi

exit 0
