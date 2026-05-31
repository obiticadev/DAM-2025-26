#!/bin/sh
# Cliente de red: resuelve un host por su NOMBRE (DNS interno de Docker) y se
# conecta por HTTP. En una red definida por el usuario, Docker da DNS por nombre
# de contenedor; en la red bridge por defecto, NO.
set -e
HOST="${1:-api}"
echo ">> Resolviendo y conectando a: $HOST"
if curl -fsS "http://$HOST" >/dev/null 2>&1; then
  echo "conectado a $HOST por DNS"
else
  echo "ERROR: no se pudo resolver/conectar a $HOST" >&2
  exit 1
fi
