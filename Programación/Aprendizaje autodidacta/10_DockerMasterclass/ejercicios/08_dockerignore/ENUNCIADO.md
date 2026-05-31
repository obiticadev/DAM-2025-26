# Ejercicio 08 — `.dockerignore` y contexto de build

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../teoria/04_Buenas_Practicas_y_Seguridad.md)

## Objetivo
Controlar el **contexto de build**: evitar que ficheros sensibles o basura (logs, secretos, cachés) acaben dentro de la imagen. **El artefacto a rellenar aquí es `.dockerignore`, no el Dockerfile** (que ya viene resuelto).

## Especificación técnica
- Imagen canónica: **`masterclass/ej08:latest`**.
- El Dockerfile (resuelto) hace `COPY . /app`.
- Tu `.dockerignore` debe excluir: `*.log`, `secreto.txt`, `__pycache__/`, `*.md`, `Dockerfile`, `.dockerignore`.

## Criterios de aceptación
- `/app/servidor.py` **existe** en la imagen.
- `/app/secreto.txt` **NO existe**.
- `/app/depuracion.log` **NO existe**.
- `/app/__pycache__` **NO existe**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej08:latest .
docker run --rm masterclass/ej08:latest
# Comprueba que el secreto y los logs NO estan dentro:
docker run --rm masterclass/ej08:latest ls -la /app
```

## Validar
```powershell
.\validar.ps1 08
```
```bash
./validar.sh 08
```
