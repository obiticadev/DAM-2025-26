#!/usr/bin/env bash
# Test RUNTIME del ejercicio 32: interpolacion de variables desde .env.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej32"
FILE="$EXDIR/compose.yaml"
URL="http://localhost:8632"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d ..."
docker compose -p "$PROJ" -f "$FILE" up -d || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml/.env."; exit 1; }

# 1) Puerto interpolado (8632) responde
OK=0
for _ in $(seq 1 20); do
  if curl -fsS -o /dev/null "$URL" 2>/dev/null; then OK=1; break; fi
  sleep 1
done
[ "$OK" = "1" ] || { echo "ERROR: $URL no responde (revisa la interpolacion de WEB_PORT)."; exit 1; }
echo "   OK: el puerto 8632 (desde .env) responde."

# 2) Variable de entorno interpolada en el contenedor
WEBID=$(docker compose -p "$PROJ" -f "$FILE" ps -q web | tr -d '[:space:]')
ENVCFG=$(docker inspect --format '{{json .Config.Env}}' "$WEBID")
echo "$ENVCFG" | grep -q "MENSAJE=hola desde dotenv" || { echo "ERROR: el contenedor no tiene MENSAJE=hola desde dotenv (env: $ENVCFG)."; exit 1; }
echo "   OK: la variable MENSAJE se interpolo desde el .env."
exit 0
