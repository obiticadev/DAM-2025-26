#!/bin/sh
# App YA RESUELTA. Cuenta cuantas veces se ha arrancado, guardando el estado
# en /data. Si /data es un volumen persistente, el contador SOBREVIVE a borrar
# el contenedor.
F=/data/contador.txt
n=0
[ -f "$F" ] && n=$(cat "$F")
n=$((n + 1))
echo "$n" > "$F"
echo "Arranques registrados: $n"
