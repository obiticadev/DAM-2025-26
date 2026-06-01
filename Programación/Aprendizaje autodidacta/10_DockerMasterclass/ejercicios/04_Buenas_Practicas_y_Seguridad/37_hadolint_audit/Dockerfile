# ============================================================
#  EJERCICIO 37 - Auditoria con hadolint  (imagen: masterclass/ej37)
#
#  Este Dockerfile FUNCIONA (construye), pero esta lleno de malas practicas que
#  hadolint marca. Tu mision: ARREGLARLO hasta que  hadolint  pase limpio,
#  conservando el comportamiento (imprime "auditoria superada").
#
#  Pistas de las reglas que estan fallando ahora mismo:
#   - DL3006: la base no tiene TAG          -> usa  debian:bookworm-slim
#   - DL3059: dos RUN consecutivos          -> combinalos en uno con  &&  y  \
#   - DL3015: falta --no-install-recommends -> anadelo al  apt-get install
#   - DL3009: dejas la cache de apt          -> termina con  rm -rf /var/lib/apt/lists/*
#   - DL3020: usas ADD para un fichero       -> usa  COPY
#   - DL3025: CMD en forma de shell          -> pasa a forma de lista JSON
#
#  (Nota: DL3008/DL3018, el pin EXACTO de versiones de paquetes, estan
#   deliberadamente relajados en esta masterclass; no necesitas fijarlos.)
# ============================================================

# TODO: reescribe las 5 lineas de abajo para dejar hadolint limpio.
FROM debian
RUN apt-get update
RUN apt-get install -y ca-certificates
ADD app/saludo.sh /saludo.sh
CMD sh /saludo.sh
