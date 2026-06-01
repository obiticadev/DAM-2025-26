#!/usr/bin/env bash
# Test RUNTIME del ejercicio 31: volumen nombrado + red propia en Compose.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej31"
FILE="$EXDIR/compose.yaml"
VOL="mc-ej31_datos"
NET="mc-ej31_backend"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d ..."
docker compose -p "$PROJ" -f "$FILE" up -d || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1; }

sleep 2

# 1) Volumen nombrado
docker volume inspect "$VOL" >/dev/null 2>&1 || { echo "ERROR: no existe el volumen $VOL (declara el volumen nombrado 'datos')."; exit 1; }
echo "   OK: existe el volumen nombrado $VOL."

# 2) Red propia
docker network inspect "$NET" >/dev/null 2>&1 || { echo "ERROR: no existe la red $NET (declara la red 'backend')."; exit 1; }
echo "   OK: existe la red $NET."

# 3) El servicio db esta conectado a esa red
DBID=$(docker compose -p "$PROJ" -f "$FILE" ps -q db | tr -d '[:space:]')
[ -n "$DBID" ] || { echo "ERROR: no encuentro el contenedor del servicio 'db'."; exit 1; }
NETS=$(docker inspect --format '{{range $k,$v := .NetworkSettings.Networks}}{{$k}} {{end}}' "$DBID")
echo "$NETS" | grep -q "$NET" || { echo "ERROR: 'db' no esta conectado a $NET (redes: $NETS)."; exit 1; }
echo "   OK: el servicio db esta conectado a la red $NET."
exit 0
