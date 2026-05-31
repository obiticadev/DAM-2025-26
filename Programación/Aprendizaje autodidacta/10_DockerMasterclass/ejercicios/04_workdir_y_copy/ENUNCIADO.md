# Ejercicio 04 — WORKDIR + COPY

> **Teoría asociada**: [`teoria/02_Anatomia_Dockerfile.md`](../../teoria/02_Anatomia_Dockerfile.md)

## Objetivo
Organizar el filesystem de la imagen usando un **directorio de trabajo** (`WORKDIR`) y copiar el proyecto a él con rutas relativas.

## Especificación técnica
- Imagen canónica: **`masterclass/ej04:latest`**.
- Base oficial Python 3.12 `slim`.
- **WORKDIR** de la imagen = `/app`.
- El contenido de `app/` se copia al directorio de trabajo (destino `.`).
- El **CMD** ejecuta `python app.py` (ruta relativa al WORKDIR).

## Criterios de aceptación
- El metadato **Workdir** de la imagen es `/app`.
- Existe `/app/app.py`.
- Al ejecutar el contenedor, el script imprime que el directorio de trabajo es `/app` y `App ejecutada correctamente desde WORKDIR.`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej04:latest .
docker run --rm masterclass/ej04:latest
docker image inspect masterclass/ej04:latest --format 'WORKDIR={{.Config.WorkingDir}}'
docker run --rm masterclass/ej04:latest pwd
```

## Validar
```powershell
.\validar.ps1 04
```
```bash
./validar.sh 04
```
