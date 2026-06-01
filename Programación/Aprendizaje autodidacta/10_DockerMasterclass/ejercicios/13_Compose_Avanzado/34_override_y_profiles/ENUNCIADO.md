# Ejercicio 34 — Perfiles (`profiles`)

> **Teoría asociada**: [`teoria/13_Compose_Avanzado.md`](../../../teoria/13_Compose_Avanzado.md)
> **Validación de tipo RUNTIME** (servicio opcional que solo arranca con su perfil).

## Objetivo
Tener servicios **opcionales** en el mismo Compose mediante `profiles`: solo arrancan al activar su perfil. (Concepto hermano: `compose.override.yaml`, que se fusiona automáticamente para ajustes locales.)

## Especificación técnica
- Proyecto Compose: **`masterclass/ej34`** (`-p mc-ej34`).
- Servicio `web` (`nginx:alpine`, puerto `8634:80`) — siempre activo.
- Servicio `herramientas` (`alpine:3.20`) — marcado con `profiles: ["debug"]`.

## Criterios de aceptación (lo que comprueba el script)
- `docker compose up -d` (sin perfil): arranca `web`, **no** arranca `herramientas`.
- `docker compose --profile debug up -d`: ahora **sí** arranca `herramientas`.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej34 up -d                  # solo 'web'
docker compose -p mc-ej34 --profile debug up -d  # ademas 'herramientas'
docker compose -p mc-ej34 down -v
```

## Validar
```powershell
.\validar.ps1 34
```
```bash
./validar.sh 34
```
