# Ejercicio 26 — Logs y log drivers

> **Teoría asociada**: [`teoria/11_Logs_y_Restart_Policies.md`](../../../teoria/11_Logs_y_Restart_Policies.md)
> **Validación de tipo RUNTIME** (captura `docker logs` y comprueba el driver).

## Objetivo
Entender el modelo de logging de Docker: la app escribe a **stdout/stderr** y Docker los captura mediante un **log driver** configurable (por defecto `json-file`).

## Especificación técnica
- Imagen canónica: **`masterclass/ej26:latest`**.
- Base `alpine:3.20`.
- Emite 5 líneas `log linea N` por stdout y una línea por stderr.

## Criterios de aceptación (lo que comprueba el script)
- El runner ejecuta el contenedor con `--log-driver json-file` y consulta `docker logs`.
- Aparecen las **5** líneas `log linea N`.
- El driver de logs del contenedor es `json-file`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej26:latest .
docker run --name mc-ej26 --log-driver json-file --log-opt max-size=1m masterclass/ej26:latest
docker logs mc-ej26
docker inspect --format '{{.HostConfig.LogConfig.Type}}' mc-ej26   # -> json-file
docker rm -f mc-ej26
```

## Validar
```powershell
.\validar.ps1 26
```
```bash
./validar.sh 26
```
