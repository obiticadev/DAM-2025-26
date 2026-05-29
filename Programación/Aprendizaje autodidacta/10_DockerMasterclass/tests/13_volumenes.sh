#!/usr/bin/env bash
# Test RUNTIME del ejercicio 13: persistencia en un volumen nombrado.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
VOL="mc-ej13-data"

cleanup() { docker volume rm -f "$VOL" >/dev/null 2>&1 || true; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) VOLUME /data declarado
VOLS=$(docker inspect --format '{{json .Config.Volumes}}' "$TAG")
case "$VOLS" in
  */data*) echo "   OK: VOLUME /data declarado." ;;
  *) echo "ERROR: la imagen no declara VOLUME /data (encontrado: $VOLS)"; exit 1 ;;
esac

# 2) Persistencia en 3 arranques
cleanup
docker volume create "$VOL" >/dev/null
for n in 1 2 3; do
  OUT=$(docker run --rm -v "${VOL}:/data" "$TAG")
  if ! echo "$OUT" | grep -q "Arranques registrados: $n"; then
    echo "ERROR: en el arranque $n se esperaba 'Arranques registrados: $n' y se obtuvo: $OUT"; exit 1
  fi
  echo "   OK arranque ${n}: $OUT"
done
echo "   OK: el contador persiste entre contenedores (volumen nombrado)."
exit 0
