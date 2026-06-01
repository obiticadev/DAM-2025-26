#!/usr/bin/env python3
"""Cliente minimo que se conecta a PostgreSQL leyendo la config del ENTORNO.

Nunca se ponen credenciales en el codigo: host, usuario, contrasena y base se
reciben por variables de entorno (las inyecta quien lanza el contenedor).
"""
import os
import sys
import time

import psycopg

HOST = os.environ.get("DB_HOST", "db")
USER = os.environ.get("DB_USER", "postgres")
PASSWORD = os.environ.get("DB_PASSWORD", "")
DBNAME = os.environ.get("DB_NAME", "postgres")

dsn = f"host={HOST} user={USER} password={PASSWORD} dbname={DBNAME}"

# La BBDD puede tardar unos segundos en aceptar conexiones: reintentamos.
for intento in range(1, 11):
    try:
        with psycopg.connect(dsn, connect_timeout=3) as conn:
            with conn.cursor() as cur:
                cur.execute("SELECT 1")
                valor = cur.fetchone()[0]
        print(f"App conectada a PostgreSQL: {valor}")
        sys.exit(0)
    except Exception as e:  # noqa: BLE001
        print(f"   (intento {intento}/10) aun no disponible: {e}", file=sys.stderr)
        time.sleep(2)

print("ERROR: no se pudo conectar a la base de datos", file=sys.stderr)
sys.exit(1)
