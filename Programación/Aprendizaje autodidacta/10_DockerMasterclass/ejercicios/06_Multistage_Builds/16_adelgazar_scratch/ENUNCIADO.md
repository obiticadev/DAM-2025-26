# Ejercicio 16 — Adelgazar al máximo (`scratch`)

> **Teoría asociada**: [`teoria/06_Multistage_Builds.md`](../../../teoria/06_Multistage_Builds.md)
> **Validación de tipo RUNTIME** (mide tamaño con umbral estricto).

## Objetivo
Llevar la imagen final a su mínimo absoluto usando `scratch`: **menos de 5 MB**.

## Especificación técnica
- Imagen canónica: **`masterclass/ej16:latest`**.
- Etapa `builder` con `golang:1.22-alpine` que compila un binario **estático** (`CGO_ENABLED=0`).
- Etapa final desde **`scratch`** con solo `/server`.
- `CMD ["/server"]`.

## Criterios de aceptación (lo que comprueba el script)
- La imagen **pesa menos de 5 MB**.
- Al ejecutarla imprime `Binario corriendo sobre scratch: imagen minima!`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej16:latest .
docker run --rm masterclass/ej16:latest
docker image ls masterclass/ej16
# Observa que no hay shell: esto fallara (no existe /bin/sh en scratch)
docker run --rm masterclass/ej16:latest sh ; echo "no hay shell en scratch"
```

## Validar
```powershell
.\validar.ps1 16
```
```bash
./validar.sh 16
```
