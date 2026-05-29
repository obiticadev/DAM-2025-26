# Ejercicio 17 — Cache mounts con BuildKit

> **Teoría asociada**: [`teoria/07_BuildKit_y_Cache.md`](../../teoria/07_BuildKit_y_Cache.md)
> Requiere **BuildKit** (activado por defecto en Docker Desktop).

## Objetivo
Acelerar la compilación reutilizando un **cache mount** persistente de BuildKit, sin que esa caché acabe dentro de la imagen.

## Especificación técnica
- Imagen canónica: **`masterclass/ej17:latest`**.
- Primera línea del Dockerfile: `# syntax=docker/dockerfile:1`.
- Etapa `builder` (`golang:1.22-alpine`) compila `/server` con `--mount=type=cache,target=/root/.cache/go-build`.
- Etapa final `alpine:3.20` con solo `/server`.

## Criterios de aceptación
- La imagen construye correctamente (la sintaxis de cache mount es válida).
- `/server` existe y al ejecutarlo imprime `Compilado reutilizando la cache de BuildKit!`.
- En la imagen final **NO** hay caché de compilación (`/root/.cache/go-build` no existe).
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej17:latest .
# Cambia algo trivial y reconstruye: la 2a vez la compilacion reusa la cache
docker build -t masterclass/ej17:latest .
docker run --rm masterclass/ej17:latest
```

## Validar
```powershell
.\validar.ps1 17
```
```bash
./validar.sh 17
```
