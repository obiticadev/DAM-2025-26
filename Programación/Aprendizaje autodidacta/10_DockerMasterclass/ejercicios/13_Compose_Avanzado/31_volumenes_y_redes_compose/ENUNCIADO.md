# Ejercicio 31 — Volúmenes y redes en Compose

> **Teoría asociada**: [`teoria/13_Compose_Avanzado.md`](../../../teoria/13_Compose_Avanzado.md)
> **Validación de tipo RUNTIME** (comprueba que el volumen y la red se crean y se usan).

## Objetivo
Declarar de forma **explícita** un volumen nombrado y una red propia a nivel raíz del `compose.yaml`, y asignarlos a un servicio.

## Especificación técnica
- Proyecto Compose: **`masterclass/ej31`** (`-p mc-ej31`).
- Servicio `db` (`postgres:16-alpine`) que:
  - monta el volumen nombrado **`datos`** en `/var/lib/postgresql/data`,
  - se conecta a la red **`backend`**.
- A nivel raíz: declara el volumen `datos` y la red `backend` (driver `bridge`).

## Criterios de aceptación (lo que comprueba el script)
- Existe el volumen `mc-ej31_datos` y la red `mc-ej31_backend` (Compose prefija con el proyecto).
- El contenedor `db` está conectado a la red `mc-ej31_backend`.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej31 up -d
docker volume ls    | Select-String mc-ej31_datos
docker network ls   | Select-String mc-ej31_backend
docker compose -p mc-ej31 down -v
```

## Validar
```powershell
.\validar.ps1 31
```
```bash
./validar.sh 31
```
