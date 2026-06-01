# Ejercicio 19 — Distroless (imagen final sin shell)

> **Teoría asociada**: [`teoria/08_Escaneo_y_Vulnerabilidades.md`](../../../teoria/08_Escaneo_y_Vulnerabilidades.md)

## Objetivo
Entregar la imagen final sobre una base **distroless**: sin shell ni gestor de paquetes, reduciendo al mínimo la superficie de ataque.

## Especificación técnica
- Imagen canónica: **`masterclass/ej19:latest`**.
- Etapa `builder` (`golang:1.22-alpine`) compila `/server` estático.
- Etapa final: `gcr.io/distroless/static-debian12:nonroot` con solo `/server`.
- `CMD ["/server"]`.

## Criterios de aceptación
- `/server` existe y al ejecutarlo imprime `Corriendo en distroless: sin shell, minima superficie de ataque!`.
- En la imagen final **NO existe** `/bin/sh` (no hay shell).
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej19:latest .
docker run --rm masterclass/ej19:latest
# Esto FALLA porque no hay shell (es lo correcto en distroless):
docker run --rm masterclass/ej19:latest sh ; echo "sin shell"
```

## Validar
```powershell
.\validar.ps1 19
```
```bash
./validar.sh 19
```
