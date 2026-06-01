# Ejercicio 23 — App conectada a una base de datos

> **Teoría asociada**: [`teoria/09_Redes_Docker.md`](../../../teoria/09_Redes_Docker.md)
> **Validación de tipo RUNTIME** (levanta PostgreSQL + tu app en la misma red).

## Objetivo
Conectar un contenedor de **aplicación** con un contenedor de **base de datos** por la red interna, usando el **nombre del servicio** como host y **variables de entorno** para las credenciales (nunca en la imagen).

## Especificación técnica
- Imagen canónica: **`masterclass/ej23:latest`**.
- Base `python:3.12-slim` + driver `psycopg[binary]`.
- Lee del entorno: `DB_HOST`, `DB_USER`, `DB_PASSWORD`, `DB_NAME`.
- Ejecuta `SELECT 1` y, al conectar, imprime `App conectada a PostgreSQL: 1`.

## Criterios de aceptación (lo que comprueba el script)
- El runner levanta un `postgres:16-alpine` llamado **`db`** en una red propia.
- Tu app, lanzada en la misma red con `DB_HOST=db`, conecta e imprime `App conectada a PostgreSQL: 1`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej23:latest .
docker network create mc-ej23-net
docker run -d --name db --network mc-ej23-net -e POSTGRES_PASSWORD=secreto postgres:16-alpine
docker run --rm --network mc-ej23-net `
  -e DB_HOST=db -e DB_USER=postgres -e DB_PASSWORD=secreto -e DB_NAME=postgres `
  masterclass/ej23:latest
docker rm -f db; docker network rm mc-ej23-net
```

## Validar
```powershell
.\validar.ps1 23
```
```bash
./validar.sh 23
```
