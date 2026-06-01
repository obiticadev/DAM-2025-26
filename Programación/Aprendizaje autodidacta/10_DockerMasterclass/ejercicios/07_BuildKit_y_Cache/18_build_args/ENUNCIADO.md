# Ejercicio 18 — Build args para versiones

> **Teoría asociada**: [`teoria/07_BuildKit_y_Cache.md`](../../../teoria/07_BuildKit_y_Cache.md)

## Objetivo
Parametrizar el Dockerfile con `ARG`: elegir la **versión de la base** y la **versión de la app** en tiempo de build.

## Especificación técnica
- Imagen canónica: **`masterclass/ej18:latest`**.
- `ARG PY_VERSION=3.12` **antes** del `FROM`, usado en `FROM python:${PY_VERSION}-alpine`.
- `ARG APP_VERSION=2.0.0` **después** del `FROM`, convertido en `ENV APP_VERSION`.
- `CMD ["python","/version.py"]`.

## Criterios de aceptación
- Metadato **Env** contiene `APP_VERSION=2.0.0` (build por defecto).
- Al ejecutar imprime `Python: 3.12.x` y `APP_VERSION: 2.0.0`.
- **hadolint limpio**.

> El runner valida el build **por defecto**; no pases `--build-arg`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej18:latest .
docker run --rm masterclass/ej18:latest

# Cambia versiones SIN tocar el Dockerfile:
docker build --build-arg PY_VERSION=3.11 --build-arg APP_VERSION=3.1.4 -t masterclass/ej18:test .
docker run --rm masterclass/ej18:test
```

## Validar
```powershell
.\validar.ps1 18
```
```bash
./validar.sh 18
```
