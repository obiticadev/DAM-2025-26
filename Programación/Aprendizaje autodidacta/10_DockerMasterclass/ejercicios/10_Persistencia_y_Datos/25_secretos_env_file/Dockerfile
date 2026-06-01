# ============================================================
#  EJERCICIO 25 - Secretos por --env-file (imagen: masterclass/ej25)
#
#  REGLA DE ORO: los secretos NO van en la imagen. Nada de ENV API_KEY=... ni
#  ARG con la contrasena: quedarian grabados en las capas para siempre. El
#  secreto se inyecta al EJECUTAR, normalmente con un fichero --env-file que NO
#  se sube al repositorio. Esta imagen solo LEE el secreto del entorno.
# ============================================================

# TODO 1: Parte de una base ligera con tag explicito: alpine:3.20

# TODO 2: Copia  app/leer_secreto.sh  a  /leer_secreto.sh  y dale permisos de
#         ejecucion.

# TODO 3: Comando por defecto: ejecutar  /leer_secreto.sh  (forma de lista).
#         (NO declares aqui la variable API_KEY: debe venir del entorno).
