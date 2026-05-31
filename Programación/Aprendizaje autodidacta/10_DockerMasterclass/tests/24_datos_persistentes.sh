#!/usr/bin/env bash
# Test RUNTIME del ejercicio 24: persistencia de datos en volumen nombrado.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
VOL="mc-ej24-data"
C="mc-ej24-pg"

cleanup() {
  docker rm -f "$C" >/dev/null 2>&1 || true
  docker volume rm "$VOL" >/dev/null 2>&1 || true
}
trap cleanup EXIT

wait_ready() {
  for _ in $(seq 1 30); do
    if docker exec "$C" pg_isready -U postgres >/dev/null 2>&1; then return 0; fi
    sleep 1
  done
  return 1
}

count_notas() { docker exec "$C" psql -U postgres -tAc "SELECT count(*) FROM notas;" | tr -d '[:space:]'; }

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

cleanup
docker volume create "$VOL" >/dev/null

echo ">> Arranque 1: volumen vacio, se ejecuta el seed ..."
docker run -d --name "$C" --mount source="$VOL",target=/var/lib/postgresql/data \
  -e POSTGRES_PASSWORD=secreto "$TAG" >/dev/null
wait_ready || { echo "ERROR: PostgreSQL no quedo listo a tiempo."; exit 1; }

N1=$(count_notas)
[ "$N1" = "1" ] || { echo "ERROR: tras el seed esperaba 1 fila y hay '$N1'."; exit 1; }
echo "   OK: el seed creo la tabla 'notas' con 1 fila."

echo ">> Insertando una 2a fila y destruyendo el contenedor ..."
docker exec "$C" psql -U postgres -c "INSERT INTO notas (texto) VALUES ('fila persistente');" >/dev/null
docker rm -f "$C" >/dev/null

echo ">> Arranque 2: contenedor NUEVO sobre el mismo volumen ..."
docker run -d --name "$C" --mount source="$VOL",target=/var/lib/postgresql/data \
  -e POSTGRES_PASSWORD=secreto "$TAG" >/dev/null
wait_ready || { echo "ERROR: PostgreSQL no quedo listo a tiempo (2)."; exit 1; }

N2=$(count_notas)
[ "$N2" = "2" ] || { echo "ERROR: esperaba 2 filas persistidas y hay '$N2'."; exit 1; }
echo "   OK: los datos persistieron en el volumen (2 filas)."
exit 0
