# Ejercicio 13 — Volúmenes y persistencia

> **Teoría asociada**: [`teoria/05_Volumenes_Fundamentos.md`](../../../teoria/05_Volumenes_Fundamentos.md) y [`teoria/10_Persistencia_y_Datos.md`](../../../teoria/10_Persistencia_y_Datos.md)
> **Validación de tipo RUNTIME**.

## Objetivo
Demostrar que los datos **persisten** en un **volumen nombrado** aunque se borre y recree el contenedor.

## Especificación técnica
- Imagen canónica: **`masterclass/ej13:latest`**.
- Base `alpine`.
- `/contador.sh` instalado y ejecutable; declara `/data` como **VOLUME**.
- El proceso principal es `/contador.sh` (incrementa y persiste un contador en `/data/contador.txt`).

## Criterios de aceptación (lo que comprueba el script)
- La imagen declara el **VOLUME `/data`**.
- Ejecutando 3 veces el contenedor (con `--rm`) montando el **mismo volumen nombrado**, el contador imprime `1`, luego `2`, luego `3` (persistencia).
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej13:latest .
docker volume create datos_ej13
docker run --rm -v datos_ej13:/data masterclass/ej13:latest   # Arranques registrados: 1
docker run --rm -v datos_ej13:/data masterclass/ej13:latest   # Arranques registrados: 2

# Sin volumen NO persiste (cada arranque vuelve a 1):
docker run --rm masterclass/ej13:latest
docker run --rm masterclass/ej13:latest

# Bind mount (carpeta del host) como alternativa:
docker run --rm -v "${PWD}/datos_host:/data" masterclass/ej13:latest

docker volume rm datos_ej13
```

## Validar
```powershell
.\validar.ps1 13
```
```bash
./validar.sh 13
```
