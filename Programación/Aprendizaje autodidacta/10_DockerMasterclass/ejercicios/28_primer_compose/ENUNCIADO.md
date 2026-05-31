# Ejercicio 28 — Tu primer Compose

> **Teoría asociada**: [`teoria/12_Compose_Fundamentos.md`](../../teoria/12_Compose_Fundamentos.md)
> **Validación de tipo RUNTIME** (`docker compose up` + `curl`).

## Objetivo
Pasar del `docker run` manual a una descripción **declarativa** en `compose.yaml`. Un solo comando levanta el servicio.

## Especificación técnica
- Proyecto Compose: **`masterclass/ej28`** (el runner usa `-p mc-ej28`).
- Fichero **`compose.yaml`** con un servicio `web`:
  - imagen `nginx:alpine`,
  - puerto `8628:80`,
  - monta `./app` en `/usr/share/nginx/html` en `:ro`.

## Criterios de aceptación (lo que comprueba el script)
- `docker compose up -d` levanta el servicio `web`.
- `http://localhost:8628` responde con el texto `Compose funcionando`.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej28 up -d
curl http://localhost:8628
docker compose -p mc-ej28 down -v
```

## Validar
```powershell
.\validar.ps1 28
```
```bash
./validar.sh 28
```
