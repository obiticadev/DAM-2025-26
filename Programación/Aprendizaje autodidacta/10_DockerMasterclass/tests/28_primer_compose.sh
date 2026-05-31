#!/usr/bin/env bash
# Test RUNTIME del ejercicio 28: docker compose up + comprobacion HTTP.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej28"
FILE="$EXDIR/compose.yaml"
URL="http://localhost:8628"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d ..."
docker compose -p "$PROJ" -f "$FILE" up -d || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1; }

OK=0
for _ in $(seq 1 20); do
  if curl -fsS "$URL" 2>/dev/null | grep -q "Compose funcionando"; then OK=1; break; fi
  sleep 1
done
[ "$OK" = "1" ] || { echo "ERROR: $URL no devolvio 'Compose funcionando'."; exit 1; }
echo "   OK: el servicio web responde con la pagina esperada."
exit 0
