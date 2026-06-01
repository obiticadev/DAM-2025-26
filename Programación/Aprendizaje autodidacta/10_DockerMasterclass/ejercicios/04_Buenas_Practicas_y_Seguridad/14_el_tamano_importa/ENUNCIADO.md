# Ejercicio 14 — El tamaño importa (full vs slim vs alpine)

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../../teoria/04_Buenas_Practicas_y_Seguridad.md)
> **Validación de tipo RUNTIME** (mide el tamaño de la imagen).

## Objetivo
Elegir la **base más ligera** para que la imagen final pese **menos de 90 MB**.

## Especificación técnica
- Imagen canónica: **`masterclass/ej14:latest`**.
- Base: variante **Alpine** de Python 3.12.
- `WORKDIR /app`, app copiada, `PYTHONUNBUFFERED=1`, `CMD ["python","app.py"]`.

## Criterios de aceptación (lo que comprueba el script)
- La imagen **pesa menos de 90 MB**.
- Al ejecutarla imprime `Imagen ligera funcionando. El tamano importa!`.
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej14:latest .
docker run --rm masterclass/ej14:latest
docker image ls masterclass/ej14
# Compara tu el peso de las distintas bases:
docker image ls python --filter "reference=python:3.12*"
```

## Validar
```powershell
.\validar.ps1 14
```
```bash
./validar.sh 14
```
