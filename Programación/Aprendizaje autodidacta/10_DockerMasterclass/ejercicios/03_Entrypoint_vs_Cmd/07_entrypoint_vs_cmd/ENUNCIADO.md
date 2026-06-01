# Ejercicio 07 — ENTRYPOINT vs CMD

> **Teoría asociada**: [`teoria/03_Entrypoint_vs_Cmd.md`](../../../teoria/03_Entrypoint_vs_Cmd.md)
> **A partir de aquí `hadolint` debe quedar limpio** (puerta de calidad).

## Objetivo
Combinar un **ENTRYPOINT** (ejecutable fijo) con un **CMD** (argumentos por defecto sustituibles).

## Especificación técnica
- Imagen canónica: **`masterclass/ej07:latest`**.
- Base de Linux mínima.
- `app/saludar.sh` queda en `/saludar.sh` y es ejecutable.
- `ENTRYPOINT ["/saludar.sh"]`.
- `CMD ["Mundo"]`.

## Criterios de aceptación
- Metadato **Entrypoint** = `["/saludar.sh"]`, **Cmd** = `["Mundo"]`.
- `docker run masterclass/ej07` (sin args) imprime `Hola, Mundo!`.
- `docker run masterclass/ej07 Docker` imprime `Hola, Docker!`.
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej07:latest .
docker run --rm masterclass/ej07:latest            # -> Hola, Mundo!
docker run --rm masterclass/ej07:latest Docker     # -> Hola, Docker!  (CMD sustituido)
docker run --rm --entrypoint sh masterclass/ej07:latest -c "echo entrypoint sustituido"
docker image inspect masterclass/ej07:latest --format 'ENTRYPOINT={{.Config.Entrypoint}} CMD={{.Config.Cmd}}'
```

## Validar
```powershell
.\validar.ps1 07
```
```bash
./validar.sh 07
```
