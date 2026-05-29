#!/usr/bin/env bash
# Test RUNTIME del ejercicio 25: secretos por --env-file, nunca en la imagen.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
ENVFILE="$(mktemp -t mc-ej25.XXXXXX.env)"

cleanup() { rm -f "$ENVFILE"; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) El secreto NO debe estar incrustado en la imagen
ENVCFG=$(docker inspect --format '{{json .Config.Env}}' "$TAG")
if echo "$ENVCFG" | grep -q "API_KEY"; then echo "ERROR: API_KEY esta grabada en la imagen (Config.Env). Quitala del Dockerfile."; exit 1; fi
echo "   OK: la imagen no contiene el secreto."

# 2) Sin secreto -> debe fallar
if docker run --rm "$TAG" >/dev/null 2>&1; then echo "ERROR: deberia fallar al no recibir API_KEY."; exit 1; fi
echo "   OK: sin el secreto, la imagen falla como se espera."

# 3) Con --env-file -> imprime el secreto enmascarado
echo "API_KEY=supersecreto1234" > "$ENVFILE"
OUT=$(docker run --rm --env-file "$ENVFILE" "$TAG")
echo "$OUT"
echo "$OUT" | grep -q "Secreto cargado: \*\*\*\*1234" || { echo "ERROR: no leyo el secreto del --env-file (salida: $OUT)"; exit 1; }
echo "   OK: el secreto llega en runtime y se muestra enmascarado."
exit 0
