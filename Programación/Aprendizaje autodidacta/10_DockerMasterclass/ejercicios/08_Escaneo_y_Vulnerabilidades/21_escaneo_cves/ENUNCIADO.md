# Ejercicio 21 — Escaneo de vulnerabilidades y endurecimiento

> **Teoría asociada**: [`teoria/08_Escaneo_y_Vulnerabilidades.md`](../../../teoria/08_Escaneo_y_Vulnerabilidades.md)
> **Validación de tipo RUNTIME** (comprueba usuario no-root y, si está disponible, escanea CVEs).

## Objetivo
Construir una imagen **endurecida**: base reciente y parcheada, sin caché de paquetes y **sin correr como root**. Aprender a pasarle un escáner de CVEs (`docker scout`).

## Especificación técnica
- Imagen canónica: **`masterclass/ej21:latest`**.
- Base **`alpine:3.20`** (tag explícito, nunca `latest`).
- Aplicar parches de seguridad del sistema: `apk upgrade --no-cache`.
- Crear un usuario sin privilegios **`appuser`** y conmutar a él con `USER`.
- `CMD` imprime `imagen endurecida`.

## Criterios de aceptación (lo que comprueba el script)
- Al ejecutarla imprime `imagen endurecida`.
- El proceso **no corre como root** (`whoami` ≠ `root`).
- (Mejor esfuerzo) Si `docker scout` está instalado, se ejecuta un análisis de CVEs a modo informativo. Si no está disponible, el test lo omite sin fallar.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej21:latest .
docker run --rm masterclass/ej21:latest            # -> imagen endurecida
docker run --rm masterclass/ej21:latest whoami     # -> appuser
docker scout cves masterclass/ej21:latest          # informe de CVEs (si lo tienes)
```

## Validar
```powershell
.\validar.ps1 21
```
```bash
./validar.sh 21
```
