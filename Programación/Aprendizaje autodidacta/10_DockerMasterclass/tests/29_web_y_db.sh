#!/usr/bin/env bash
# Test RUNTIME del ejercicio 29: web + db en Compose, la web consulta la base.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej29"
FILE="$EXDIR/compose.yaml"
URL="http://localhost:8629"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d --build ..."
docker compose -p "$PROJ" -f "$FILE" up -d --build || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1; }

OK=0
for _ in $(seq 1 30); do
  if curl -fsS "$URL" 2>/dev/null | grep -q "DB OK: 1"; then OK=1; break; fi
  sleep 1
done
[ "$OK" = "1" ] || { echo "ERROR: $URL no devolvio 'DB OK: 1' (la web no hablo con la db)."; exit 1; }
echo "   OK: la web consulto la base de datos por su nombre de servicio."
exit 0
