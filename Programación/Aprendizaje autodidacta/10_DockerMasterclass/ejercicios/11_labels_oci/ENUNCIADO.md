# Ejercicio 11 — LABELS y metadata OCI

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../teoria/04_Buenas_Practicas_y_Seguridad.md)

## Objetivo
Hacer que la imagen se **autodescriba** con las etiquetas estándar **OCI** (título, versión, origen, autores).

## Especificación técnica
- Imagen canónica: **`masterclass/ej11:latest`**.
- Base `alpine`.
- Etiquetas:
  | Clave | Valor |
  |---|---|
  | `org.opencontainers.image.title` | `masterclass-ej11` |
  | `org.opencontainers.image.version` | `1.0.0` |
  | `org.opencontainers.image.source` | `https://github.com/masterclass/docker` |
  | `org.opencontainers.image.authors` | `alumno@masterclass` |
- `CMD` imprime `imagen etiquetada`.

## Criterios de aceptación
- Las cuatro etiquetas OCI tienen exactamente esos valores (metadata test).
- **hadolint limpio**.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej11:latest .
docker run --rm masterclass/ej11:latest
docker image inspect masterclass/ej11:latest --format '{{json .Config.Labels}}'
```

## Validar
```powershell
.\validar.ps1 11
```
```bash
./validar.sh 11
```
