# Ejercicio 10 — Usuario no-root (`USER`)

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../teoria/04_Buenas_Practicas_y_Seguridad.md)

## Objetivo
Aplicar el **principio de mínimo privilegio**: que el contenedor no corra como `root`.

## Especificación técnica
- Imagen canónica: **`masterclass/ej10:latest`**.
- Base `alpine`.
- Usuario `appuser` con UID `10001`, sin contraseña.
- La imagen fija `USER appuser`.
- `CMD` ejecuta `id`.

## Criterios de aceptación
- `whoami` dentro del contenedor devuelve `appuser`.
- `id -u` devuelve `10001`.
- Metadato **User** de la imagen = `appuser`.
- **hadolint limpio** (no debe avisar DL3002 *último usuario root*).

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej10:latest .
docker run --rm masterclass/ej10:latest            # -> uid=10001(appuser) ...
docker run --rm masterclass/ej10:latest whoami     # -> appuser
docker image inspect masterclass/ej10:latest --format 'USER={{.Config.User}}'
```

## Validar
```powershell
.\validar.ps1 10
```
```bash
./validar.sh 10
```
