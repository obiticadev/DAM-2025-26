# ============================================================
#  EJERCICIO 36 - Imagenes multi-arquitectura con buildx
#  (imagen: masterclass/ej36)
#
#  Una imagen puede publicarse para VARIAS arquitecturas (amd64, arm64...) bajo
#  un mismo tag: el registry guarda una "manifest list" y cada maquina descarga
#  la variante que le toca.  docker buildx  construye y publica todas a la vez.
#  Mantenemos el Dockerfile SIN pasos RUN para no necesitar emulacion (QEMU):
#  asi se compila para cualquier arquitectura sin ejecutar binarios ajenos.
# ============================================================

# TODO 1: Parte de una base ligera con tag explicito: alpine:3.20

# TODO 2: Copia  app/saludo.sh  a  /saludo.sh  (solo COPY, sin RUN).

# TODO 3: Comando por defecto: ejecutar el script con el interprete,
#         en forma de lista:  ["sh", "/saludo.sh"]
#         (asi no hace falta el bit de ejecucion ni un RUN chmod).
