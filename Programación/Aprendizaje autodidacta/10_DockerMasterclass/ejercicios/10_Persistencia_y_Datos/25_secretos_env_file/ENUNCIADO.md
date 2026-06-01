# Ejercicio 25 — Secretos por `--env-file`

> **Teoría asociada**: [`teoria/10_Persistencia_y_Datos.md`](../../../teoria/10_Persistencia_y_Datos.md)
> **Validación de tipo RUNTIME** (comprueba que el secreto NO está en la imagen y que llega en runtime).

## Objetivo
Manejar secretos correctamente: **fuera de la imagen**. Se inyectan al ejecutar con un fichero `--env-file` (que no se versiona), nunca con `ENV`/`ARG` en el Dockerfile.

## Especificación técnica
- Imagen canónica: **`masterclass/ej25:latest`**.
- Base `alpine:3.20`.
- Lee la variable de entorno `API_KEY`; si falta, falla con error.
- Si está presente, imprime una versión **enmascarada**: `Secreto cargado: ****<4 últimos> (longitud N)`.

## Criterios de aceptación (lo que comprueba el script)
- La imagen **no** contiene `API_KEY` en su configuración (`docker inspect` → `Config.Env`).
- Ejecutada **sin** el secreto, falla (código ≠ 0).
- Ejecutada con `--env-file`, imprime `Secreto cargado: ****<...>`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej25:latest .
"API_KEY=supersecreto1234" | Out-File -Encoding ascii secretos.env
docker run --rm --env-file secretos.env masterclass/ej25:latest   # -> ****1234
docker run --rm masterclass/ej25:latest                            # -> falla (sin secreto)
Remove-Item secretos.env
```

## Validar
```powershell
.\validar.ps1 25
```
```bash
./validar.sh 25
```
