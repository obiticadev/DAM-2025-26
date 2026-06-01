# ============================================================
#  EJERCICIO 24 - Datos persistentes (imagen: masterclass/ej24)
#
#  Un contenedor es efimero: si lo borras, sus datos se van. La PERSISTENCIA se
#  consigue con VOLUMENES NOMBRADOS, que viven fuera del ciclo de vida del
#  contenedor. Aqui construyes una imagen de PostgreSQL con un script de semilla
#  y el runner demostrara que, recreando el contenedor sobre el MISMO volumen,
#  los datos siguen ahi.
# ============================================================

# TODO 1: Parte de la imagen oficial  postgres:16-alpine

# TODO 2: Copia  app/seed.sql  a  /docker-entrypoint-initdb.d/seed.sql
#         (Postgres ejecuta ese directorio solo en el primer arranque, cuando
#          el volumen de datos esta vacio).

# (No se necesita CMD: la imagen base ya arranca el servidor PostgreSQL.)
