# Ejercicio 29 — Multi-servicio: web + db

> **Teoría asociada**: [`teoria/12_Compose_Fundamentos.md`](../../teoria/12_Compose_Fundamentos.md)
> **Validación de tipo RUNTIME** (`docker compose up` con dos servicios + `curl`).

## Objetivo
Orquestar **dos servicios** en un solo Compose: una app web y su base de datos. Compose les da una red común y la app alcanza a la base por su **nombre de servicio** (`db`).

## Especificación técnica
- Proyecto Compose: **`masterclass/ej29`** (`-p mc-ej29`).
- El `Dockerfile` del servicio web ya está hecho en `app/`. Tú completas `compose.yaml`:
  - servicio `db`: `postgres:16-alpine`, `POSTGRES_PASSWORD=secreto`,
  - servicio `web`: `build: ./app`, puerto `8629:8000`, variables `DB_HOST=db`, `DB_USER=postgres`, `DB_PASSWORD=secreto`, `DB_NAME=postgres`, y `depends_on: [db]`.

## Criterios de aceptación (lo que comprueba el script)
- `docker compose up -d --build` levanta `db` y `web`.
- `http://localhost:8629` responde `DB OK: 1` (la web habló con la base por nombre de servicio).

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej29 up -d --build
curl http://localhost:8629    # -> DB OK: 1
docker compose -p mc-ej29 down -v
```

## Validar
```powershell
.\validar.ps1 29
```
```bash
./validar.sh 29
```
