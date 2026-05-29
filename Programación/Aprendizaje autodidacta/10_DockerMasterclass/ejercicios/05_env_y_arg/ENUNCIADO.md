# Ejercicio 05 — ENV y ARG (build-time vs runtime)

> **Teoría asociada**: [`teoria/02_Anatomia_Dockerfile.md`](../../teoria/02_Anatomia_Dockerfile.md)

## Objetivo
Distinguir **`ARG`** (variable disponible solo durante el build) de **`ENV`** (variable que vive en el contenedor en ejecución) y conectarlas.

## Especificación técnica
- Imagen canónica: **`masterclass/ej05:latest`**.
- `ARG APP_VERSION` con default `1.0.0`.
- `ENV APP_VERSION` toma el valor del ARG.
- `ENV APP_ENV=produccion`.
- El contenedor ejecuta `python /config.py`, que imprime ambas variables.

## Criterios de aceptación
- Metadato **Env** contiene `APP_ENV=produccion` y `APP_VERSION=1.0.0`.
- Al ejecutar el contenedor imprime:
  ```
  APP_ENV = produccion
  APP_VERSION = 1.0.0
  ```

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej05:latest .
docker run --rm masterclass/ej05:latest

# Sobreescribe la version en BUILD-TIME con --build-arg
docker build --build-arg APP_VERSION=2.5.0 -t masterclass/ej05:latest .
docker run --rm masterclass/ej05:latest

# Sobreescribe APP_ENV en RUNTIME con -e (sin reconstruir)
docker run --rm -e APP_ENV=staging masterclass/ej05:latest
docker image inspect masterclass/ej05:latest --format '{{.Config.Env}}'
```
> El test valida el build **por defecto** (`APP_VERSION=1.0.0`), así que no uses `--build-arg` al validar.

## Validar
```powershell
.\validar.ps1 05
```
```bash
./validar.sh 05
```
