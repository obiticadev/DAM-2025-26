# ============================================================
#  EJERCICIO 03 - Capas y cache de build (imagen: masterclass/ej03)
#
#  CLAVE: cada instruccion crea una CAPA. Docker reusa la cache de una capa
#  mientras nada por encima de ella cambie. Por eso el ORDEN importa: lo que
#  rara vez cambia debe ir ARRIBA; lo que cambia a menudo, ABAJO.
# ============================================================

# TODO 1: Parte de una imagen base de Linux minima.

# TODO 2: En una unica instruccion RUN (capa ESTABLE que casi nunca cambia),
#         crea el directorio /site y escribe dentro un fichero /site/BUILD.txt
#         que contenga exactamente el texto:  capa estable

# TODO 3: DESPUES de esa capa estable, copia el contenido de app/ (que cambia
#         con frecuencia) dentro de /site. Asi, al editar el HTML, Docker
#         reutiliza la capa del RUN y solo rehace la del COPY.

# TODO 4: Comprueba mentalmente el orden: ¿colocar el COPY antes del RUN
#         invalidaria la cache del RUN cada vez que toques el HTML? (no escribas
#         nada aqui, es para que razones el porque del orden anterior).
