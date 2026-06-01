#!/bin/sh
# Regla 12-factor: la app escribe sus logs a STDOUT/STDERR y deja que Docker
# los capture. NO escribe a ficheros propios. Docker los recoge segun el
# "log driver" configurado (por defecto json-file).
set -e
i=1
while [ "$i" -le 5 ]; do
  echo "log linea $i"
  i=$((i + 1))
done
echo "aviso por stderr" >&2
