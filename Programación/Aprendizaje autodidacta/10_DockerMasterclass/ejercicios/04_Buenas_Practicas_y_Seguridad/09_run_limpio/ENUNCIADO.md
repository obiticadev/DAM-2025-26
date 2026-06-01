# Ejercicio 09 — RUN limpio (instalar sin dejar caché)

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../../teoria/04_Buenas_Practicas_y_Seguridad.md)

## Objetivo
Instalar paquetes del sistema **encadenando** `update`, `install` y limpieza en **una sola capa**, manteniendo la imagen mínima y pasando `hadolint`.

## Especificación técnica
- Imagen canónica: **`masterclass/ej09:latest`**.
- Base `debian:bookworm-slim`.
- Una única instrucción `RUN`: `apt-get update` + `apt-get install -y --no-install-recommends curl` + `rm -rf /var/lib/apt/lists/*`.
- `CMD` muestra la versión de curl.

## Criterios de aceptación
- **hadolint limpio** (en especial DL3009 *borra la caché de apt* y DL3015 *--no-install-recommends*).
- `curl` está instalado: ejecutar `curl --version` funciona y empieza por `curl`.
- Existe `/usr/bin/curl`.

> Nota: la regla DL3008 (fijar versión exacta de apt) está **relajada** en el runner; no necesitas pinear `curl=...`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej09:latest .
docker run --rm masterclass/ej09:latest
docker run --rm masterclass/ej09:latest curl --version
# Compara el tamaño con una instalacion "sucia" (sin limpiar listas):
docker image ls masterclass/ej09
```

## Validar
```powershell
.\validar.ps1 09
```
```bash
./validar.sh 09
```
