#!/bin/sh
# Proceso "inestable": vive un instante y termina con error (exit 1). Sirve para
# ver como una politica de reinicio (--restart on-failure) hace que Docker lo
# vuelva a levantar automaticamente, incrementando RestartCount.
echo "arrancando proceso inestable..."
sleep 1
echo "fallo simulado, saliendo con error" >&2
exit 1
