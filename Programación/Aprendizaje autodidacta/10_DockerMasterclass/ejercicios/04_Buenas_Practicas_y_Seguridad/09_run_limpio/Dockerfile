# ============================================================
#  EJERCICIO 09 - RUN limpio: instalar paquetes sin dejar basura
#  (imagen: masterclass/ej09)
#
#  Cada RUN crea una capa inmutable. Si instalas paquetes en una capa y
#  borras su cache en OTRA capa posterior, la basura YA quedo grabada y pesa.
#  Por eso instalar + limpiar debe ocurrir en la MISMA instruccion RUN.
#  hadolint vigila esto (DL3009, DL3015).
# ============================================================

# TODO 1: Parte de la imagen oficial debian en variante slim, con un tag
#         explicito (p.ej. la edicion estable "bookworm-slim").

# TODO 2: En UNA UNICA instruccion RUN, actualiza el indice de paquetes e
#         instala "curl" SIN los paquetes recomendados (mantenla minima).

# TODO 3: En esa MISMA instruccion RUN (encadenada con &&), elimina la cache
#         de listas de apt para que no quede grabada en la capa.

# TODO 4: Haz que el comando por defecto del contenedor muestre la version de
#         curl (forma de lista), demostrando que quedo instalado.
