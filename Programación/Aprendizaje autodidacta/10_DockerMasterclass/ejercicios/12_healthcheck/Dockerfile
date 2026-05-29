# ============================================================
#  EJERCICIO 12 - HEALTHCHECK (imagen: masterclass/ej12)
#
#  Un contenedor puede estar "Up" pero con la app colgada. El HEALTHCHECK
#  define como Docker COMPRUEBA periodicamente que el servicio responde de
#  verdad, marcando el estado healthy / unhealthy.
# ============================================================

# TODO 1: Parte de la imagen oficial nginx:alpine (ya trae 'wget' de busybox).

# TODO 2: Copia app/index.html a la raiz web de nginx /usr/share/nginx/html/

# TODO 3: Declara un HEALTHCHECK que cada 5s, con timeout de 3s, 3 reintentos
#         y un periodo inicial de gracia (start-period) de 3s, compruebe que
#         el servidor responde en http://localhost/  (usa wget en modo --spider
#         y devuelve exit 1 si falla).
