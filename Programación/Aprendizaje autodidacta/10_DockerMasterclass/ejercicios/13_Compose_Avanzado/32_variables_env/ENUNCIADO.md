# Ejercicio 32 — Variables con `.env`

> **Teoría asociada**: [`teoria/13_Compose_Avanzado.md`](../../../teoria/13_Compose_Avanzado.md)
> **Validación de tipo RUNTIME** (comprueba la interpolación de puerto y variable).

## Objetivo
Sacar la configuración del `compose.yaml` a un fichero **`.env`** que Compose lee automáticamente y sustituye en las referencias `${...}`.

## Especificación técnica
- Proyecto Compose: **`masterclass/ej32`** (`-p mc-ej32`).
- El `.env` ya está dado (`WEB_PORT=8632`, `MENSAJE=hola desde dotenv`). Completa `compose.yaml`:
  - servicio `web` (`nginx:alpine`) con puerto `"${WEB_PORT}:80"`,
  - variable de entorno `MENSAJE: ${MENSAJE}`.

## Criterios de aceptación (lo que comprueba el script)
- `http://localhost:8632` responde (el puerto vino del `.env`).
- El contenedor `web` tiene la variable de entorno `MENSAJE=hola desde dotenv`.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej32 up -d
docker compose -p mc-ej32 config     # muestra las ${...} ya sustituidas
curl http://localhost:8632
docker compose -p mc-ej32 down -v
```

## Validar
```powershell
.\validar.ps1 32
```
```bash
./validar.sh 32
```
