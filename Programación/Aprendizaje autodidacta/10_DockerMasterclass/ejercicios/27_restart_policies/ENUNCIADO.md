# Ejercicio 27 — Políticas de reinicio

> **Teoría asociada**: [`teoria/11_Logs_y_Restart_Policies.md`](../../teoria/11_Logs_y_Restart_Policies.md)
> **Validación de tipo RUNTIME** (observa que `RestartCount` aumenta).

## Objetivo
Conocer las **restart policies** (`no`, `on-failure[:N]`, `always`, `unless-stopped`) y comprobar cómo Docker reinicia automáticamente un contenedor que falla.

## Especificación técnica
- Imagen canónica: **`masterclass/ej27:latest`**.
- Base `alpine:3.20`.
- Ejecuta un proceso que termina con error (`exit 1`) tras un segundo.

## Criterios de aceptación (lo que comprueba el script)
- El runner lo lanza con `--restart on-failure:3`.
- Tras unos segundos, `RestartCount` del contenedor es **≥ 2** (Docker lo reinició por los fallos).

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej27:latest .
docker run -d --name mc-ej27 --restart on-failure:3 masterclass/ej27:latest
Start-Sleep 8
docker inspect --format '{{.RestartCount}}' mc-ej27   # -> 2 o 3
docker rm -f mc-ej27
```

## Validar
```powershell
.\validar.ps1 27
```
```bash
./validar.sh 27
```
