#!/bin/sh
# Lee un secreto del ENTORNO en tiempo de ejecucion. El secreto NUNCA debe
# estar incrustado en la imagen (ni con ENV ni con ARG): se inyecta al arrancar,
# normalmente con  --env-file . Aqui solo confirmamos que llego, mostrando una
# version enmascarada (jamas el valor completo en los logs).
set -e
if [ -z "${API_KEY:-}" ]; then
  echo "ERROR: falta el secreto API_KEY (debe inyectarse en runtime, no en la imagen)" >&2
  exit 1
fi
LEN=${#API_KEY}
SUFIJO=$(printf '%s' "$API_KEY" | tail -c 4)
echo "Secreto cargado: ****${SUFIJO} (longitud ${LEN})"
