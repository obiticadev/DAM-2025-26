#!/usr/bin/env python3
"""Servidor HTTP minimo que, en cada peticion, consulta la base de datos.

Lee la conexion del entorno (lo inyecta Compose). Responde 'DB OK: 1' si puede
ejecutar SELECT 1 contra PostgreSQL.
"""
import os
from http.server import BaseHTTPRequestHandler, HTTPServer

import psycopg

DSN = (
    f"host={os.environ.get('DB_HOST', 'db')} "
    f"user={os.environ.get('DB_USER', 'postgres')} "
    f"password={os.environ.get('DB_PASSWORD', '')} "
    f"dbname={os.environ.get('DB_NAME', 'postgres')}"
)


class Handler(BaseHTTPRequestHandler):
    def do_GET(self):  # noqa: N802
        try:
            with psycopg.connect(DSN, connect_timeout=3) as conn:
                with conn.cursor() as cur:
                    cur.execute("SELECT 1")
                    valor = cur.fetchone()[0]
            body = f"DB OK: {valor}".encode()
            self.send_response(200)
        except Exception as e:  # noqa: BLE001
            body = f"DB ERROR: {e}".encode()
            self.send_response(500)
        self.send_header("Content-Type", "text/plain")
        self.end_headers()
        self.wfile.write(body)

    def log_message(self, *args):  # silenciar el log de acceso
        pass


if __name__ == "__main__":
    HTTPServer(("0.0.0.0", 8000), Handler).serve_forever()
