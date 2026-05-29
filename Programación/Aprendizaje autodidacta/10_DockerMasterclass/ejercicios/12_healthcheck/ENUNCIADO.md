# Ejercicio 12 — HEALTHCHECK

> **Teoría asociada**: [`teoria/11_Logs_y_Restart_Policies.md`](../../teoria/11_Logs_y_Restart_Policies.md)
> **Validación de tipo RUNTIME** (el script espera a que el contenedor llegue a `healthy`).

## Objetivo
Declarar un `HEALTHCHECK` que permita a Docker saber si el servicio está realmente **vivo y respondiendo**.

## Especificación técnica
- Imagen canónica: **`masterclass/ej12:latest`**.
- Base `nginx:alpine`.
- `app/index.html` servido por nginx.
- `HEALTHCHECK` con: `--interval=5s --timeout=3s --retries=3 --start-period=3s`, que comprueba `http://localhost/` con `wget --spider` y devuelve `exit 1` al fallar.

## Criterios de aceptación (lo que comprueba el script)
- La imagen **tiene** un HEALTHCHECK configurado (`docker inspect`).
- Al ejecutar el contenedor, su estado de salud llega a **`healthy`** en menos de ~40s.
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej12:latest .
docker run -d --name salud masterclass/ej12:latest
docker ps                       # mira la columna STATUS: (health: starting) -> (healthy)
docker inspect --format '{{json .State.Health}}' salud
docker rm -f salud
```

## Validar
```powershell
.\validar.ps1 12
```
```bash
./validar.sh 12
```
