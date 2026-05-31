# Ejercicio 15 — Multi-stage básico

> **Teoría asociada**: [`teoria/06_Multistage_Builds.md`](../../teoria/06_Multistage_Builds.md)

## Objetivo
Compilar un binario Go en una etapa con toolchain y entregar una imagen final que **solo contiene el binario** (sin compilador ni código fuente).

## Especificación técnica
- Imagen canónica: **`masterclass/ej15:latest`**.
- Etapa `builder`: `golang:1.22-alpine`, compila `/server` (estático, `CGO_ENABLED=0`).
- Etapa final: `alpine:3.20`, recibe solo `/server`.
- `CMD ["/server"]`.

## Criterios de aceptación
- Existe `/server` y al ejecutarlo imprime `Hola desde un binario Go compilado en multi-stage!`.
- En la imagen final **NO existe** el compilador (`/usr/local/go/bin/go`).
- En la imagen final **NO existe** el código fuente (`/src/main.go`).
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej15:latest .
docker run --rm masterclass/ej15:latest
docker image ls masterclass/ej15            # compara con el peso de golang:1.22-alpine
docker run --rm masterclass/ej15:latest sh -c "command -v go || echo 'sin compilador (correcto)'"
```

## Validar
```powershell
.\validar.ps1 15
```
```bash
./validar.sh 15
```
