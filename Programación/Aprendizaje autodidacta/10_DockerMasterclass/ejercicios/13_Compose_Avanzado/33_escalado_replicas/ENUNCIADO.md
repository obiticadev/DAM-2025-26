# Ejercicio 33 — Escalado horizontal (réplicas)

> **Teoría asociada**: [`teoria/13_Compose_Avanzado.md`](../../../teoria/13_Compose_Avanzado.md)
> **Validación de tipo RUNTIME** (cuenta las réplicas en marcha).

## Objetivo
Escalar un servicio **sin estado** a varias **réplicas** idénticas, base del balanceo de carga.

## Especificación técnica
- Proyecto Compose: **`masterclass/ej33`** (`-p mc-ej33`).
- Servicio `worker` (`alpine:3.20`, `command: sleep infinity`).
- Declara **3 réplicas** con `deploy.replicas: 3`.

## Criterios de aceptación (lo que comprueba el script)
- Tras `docker compose up -d`, hay exactamente **3** contenedores del servicio `worker` en ejecución.

## Zona de Ejecución Master
```powershell
docker compose -p mc-ej33 up -d
docker compose -p mc-ej33 ps         # 3 filas de 'worker'
# alternativa en linea de comandos:
# docker compose -p mc-ej33 up -d --scale worker=3
docker compose -p mc-ej33 down -v
```

## Validar
```powershell
.\validar.ps1 33
```
```bash
./validar.sh 33
```
