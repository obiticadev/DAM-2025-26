#!/usr/bin/env bash
# Test RUNTIME del ejercicio 23: app de Python conectada a PostgreSQL por red.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
NET="mc-ej23-net"
DB="mc-ej23-db"

cleanup() {
  docker rm -f "$DB" >/dev/null 2>&1 || true
  docker network rm "$NET" >/dev/null 2>&1 || true
}
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
echo ">> Levantando PostgreSQL ..."
docker network create "$NET" >/dev/null
docker run -d --name "$DB" --network "$NET" --network-alias db \
  -e POSTGRES_PASSWORD=secreto postgres:16-alpine >/dev/null

echo ">> Lanzando la app (reintenta hasta que la BBDD acepte conexiones) ..."
OUT=$(docker run --rm --network "$NET" \
  -e DB_HOST=db -e DB_USER=postgres -e DB_PASSWORD=secreto -e DB_NAME=postgres \
  "$TAG")
echo "$OUT"
echo "$OUT" | grep -q "App conectada a PostgreSQL: 1" || { echo "ERROR: la app no confirmo la conexion (salida: $OUT)"; exit 1; }
echo "   OK: la app se conecto a la base de datos por nombre de servicio."
exit 0
