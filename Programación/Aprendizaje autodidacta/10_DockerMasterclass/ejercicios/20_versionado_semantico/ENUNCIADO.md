# Ejercicio 20 — Versionado semántico y tags

> **Teoría asociada**: [`teoria/14_Registries_y_Distribucion.md`](../../teoria/14_Registries_y_Distribucion.md)
> **Validación de tipo RUNTIME** (re-etiqueta y comprueba IDs).

## Objetivo
Grabar la versión en la imagen y entender que **un mismo ID** puede tener varios tags (`1.4.2`, `1.4`, `1`, `latest`).

## Especificación técnica
- Imagen canónica: **`masterclass/ej20:latest`**.
- Base `alpine`.
- `LABEL org.opencontainers.image.version=1.4.2` y `...title=masterclass-ej20`.
- `CMD` imprime `1.4.2`.

## Criterios de aceptación (lo que comprueba el script)
- La etiqueta `org.opencontainers.image.version` vale `1.4.2`.
- Al re-etiquetar la imagen como `1.4.2`, `1.4` y `1`, **todas comparten el mismo Image ID**.
- Al ejecutarla imprime `1.4.2`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej20:1.4.2 .
docker tag masterclass/ej20:1.4.2 masterclass/ej20:1.4
docker tag masterclass/ej20:1.4.2 masterclass/ej20:1
docker tag masterclass/ej20:1.4.2 masterclass/ej20:latest
docker images masterclass/ej20      # mismo IMAGE ID en las 4 filas
docker run --rm masterclass/ej20:1.4.2
```

## Validar
```powershell
.\validar.ps1 20
```
```bash
./validar.sh 20
```
