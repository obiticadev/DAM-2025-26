# Ejercicio 02 — Tu primer Dockerfile real

> **Teoría asociada**: [`teoria/02_Anatomia_Dockerfile.md`](../../teoria/02_Anatomia_Dockerfile.md)

## Objetivo
Escribir un Dockerfile que parta de una imagen con **runtime de Python**, incruste el código y lo ejecute como proceso principal.

## Especificación técnica
- Imagen canónica: **`masterclass/ej02:latest`**.
- Base oficial con **Python 3.12** en variante ligera (`slim`).
- El script `app/saluda.py` (resuelto) queda en `/saluda.py`.
- La imagen define `PYTHONUNBUFFERED=1` para logs inmediatos.
- Al ejecutar el contenedor se imprime:
  ```
  === Mi primer Dockerfile real ===
  Hola desde Python dentro de un contenedor!
  Version de Python: 3.12.x
  ```

## Criterios de aceptación
- `/saluda.py` existe en la imagen.
- Ejecutar `python /saluda.py` imprime `Hola desde Python dentro de un contenedor!`.
- El **CMD** de la imagen es `["python", "/saluda.py"]`.
- La variable de entorno `PYTHONUNBUFFERED=1` está presente.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej02:latest .
docker run --rm masterclass/ej02:latest
docker run --rm masterclass/ej02:latest python --version   # sobreescribe el CMD
docker image inspect masterclass/ej02:latest --format '{{.Config.Cmd}}'
```

## Validar
```powershell
.\validar.ps1 02
```
```bash
./validar.sh 02
```
