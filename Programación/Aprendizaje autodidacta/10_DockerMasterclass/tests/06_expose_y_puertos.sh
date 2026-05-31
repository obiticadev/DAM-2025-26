#!/usr/bin/env bash
# Test RUNTIME del ejercicio 06: levanta nginx, publica el puerto y hace curl.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
CNAME="mc-ej06"; PORT=8606

cleanup() { docker rm -f "$CNAME" >/dev/null 2>&1 || true; }
trap cleanup EXIT

echo ">> Construyendo imagen $TAG ..."
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# 1) EXPOSE 80
EXPOSED=$(docker inspect --format '{{json .Config.ExposedPorts}}' "$TAG")
case "$EXPOSED" in
  *80/tcp*) echo "   OK: EXPOSE 80 declarado." ;;
  *) echo "ERROR: la imagen no declara EXPOSE 80 (encontrado: $EXPOSED)"; exit 1 ;;
esac

# 2) LABEL ejercicio
LABEL=$(docker inspect --format '{{index .Config.Labels "ejercicio"}}' "$TAG")
if [ "$LABEL" != "06-expose-puertos" ]; then
  echo "ERROR: falta el LABEL ejercicio=06-expose-puertos (encontrado: '$LABEL')"; exit 1
fi
echo "   OK: LABEL ejercicio correcto."

# 3) Publica y comprueba HTTP
cleanup
docker run -d --name "$CNAME" -p "${PORT}:80" "$TAG" >/dev/null
ok=0
for _ in $(seq 1 20); do
  if curl -fs "http://localhost:${PORT}" 2>/dev/null | grep -q "Masterclass Docker"; then ok=1; break; fi
  sleep 0.5
done
[ "$ok" = "1" ] || { echo "ERROR: no se sirvio index.html en http://localhost:${PORT}"; exit 1; }
echo "   OK: nginx responde con el contenido esperado en el puerto ${PORT}."
exit 0
