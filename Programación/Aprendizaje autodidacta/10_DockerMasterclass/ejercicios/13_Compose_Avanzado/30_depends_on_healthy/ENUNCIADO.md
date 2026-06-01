# Ejercicio 30 — `depends_on` con `service_healthy`

> **Teoría asociada**: [`teoria/13_Compose_Avanzado.md`](../../../teoria/13_Compose_Avanzado.md)
> **Validación de tipo RUNTIME** (comprueba que la db queda `healthy` y la web responde).

## Objetivo
Diferenciar **arrancar** de **estar listo**. Con un `healthcheck` en `db` y `depends_on: { db: { condition: service_healthy } }` en `web`, Compose no arranca la web hasta que la base acepta conexiones.

## Especificación técnica
- Proyecto Compose: **`masterclass/ej30`** (`-p mc-ej30`).
- El `Dockerfile` del web ya está en `app/`. Completa `compose.yaml`:
  - `db`: añade `healthcheck` con `pg_isready -U postgres` (interval 3s, timeout 3s, retries 5),
  - `web`: `depends_on` de `db` con `condition: service_healthy`.

## Criterios de aceptación (lo que comprueba el script)
- La `db` alcanza el estado **`healthy`**.
- `http://localhost:8630` responde `DB OK: 1`.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej30 up -d --build
docker compose -p mc-ej30 ps          # db: healthy
curl http://localhost:8630            # -> DB OK: 1
docker compose -p mc-ej30 down -v
```

## Validar
```powershell
.\validar.ps1 30
```
```bash
./validar.sh 30
```
