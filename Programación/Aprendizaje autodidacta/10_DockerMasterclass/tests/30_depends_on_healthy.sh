#!/usr/bin/env bash
# Test RUNTIME del ejercicio 30: db healthy + web arranca tras la condicion.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej30"
FILE="$EXDIR/compose.yaml"
URL="http://localhost:8630"

cleanup() { docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true; }
trap cleanup EXIT

[ -f "$FILE" ] || { echo "ERROR: falta compose.yaml en $EXDIR"; exit 1; }
cleanup
echo ">> docker compose up -d --build ..."
docker compose -p "$PROJ" -f "$FILE" up -d --build || { echo "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1; }

# 1) La db debe alcanzar estado healthy
DBID=$(docker compose -p "$PROJ" -f "$FILE" ps -q db | tr -d '[:space:]')
[ -n "$DBID" ] || { echo "ERROR: no encuentro el contenedor del servicio 'db'."; exit 1; }
HEALTHY=0
for _ in $(seq 1 40); do
  ST=$(docker inspect --format '{{.State.Health.Status}}' "$DBID" 2>/dev/null | tr -d '[:space:]')
  if [ "$ST" = "healthy" ]; then HEALTHY=1; break; fi
  sleep 1
done
[ "$HEALTHY" = "1" ] || { echo "ERROR: la db nunca llego a 'healthy' (revisa el healthcheck)."; exit 1; }
echo "   OK: la db esta healthy."

# 2) La web responde
OK=0
for _ in $(seq 1 20); do
  if curl -fsS "$URL" 2>/dev/null | grep -q "DB OK: 1"; then OK=1; break; fi
  sleep 1
done
[ "$OK" = "1" ] || { echo "ERROR: $URL no devolvio 'DB OK: 1'."; exit 1; }
echo "   OK: la web arranco tras la db y respondio correctamente."
exit 0
