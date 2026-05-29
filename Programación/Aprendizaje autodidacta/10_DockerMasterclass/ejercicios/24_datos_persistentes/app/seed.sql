-- Script de inicializacion. PostgreSQL ejecuta lo que haya en
-- /docker-entrypoint-initdb.d/ SOLO cuando el volumen de datos esta VACIO
-- (primer arranque). En arranques posteriores con el mismo volumen NO se
-- vuelve a ejecutar: los datos ya persisten en el volumen.
CREATE TABLE IF NOT EXISTS notas (
    id    SERIAL PRIMARY KEY,
    texto TEXT NOT NULL
);

INSERT INTO notas (texto) VALUES ('nota inicial sembrada');
