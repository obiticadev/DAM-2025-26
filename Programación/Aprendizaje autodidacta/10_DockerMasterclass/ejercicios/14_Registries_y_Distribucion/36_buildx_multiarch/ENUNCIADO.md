# Ejercicio 36 — Imágenes multi-arquitectura con `buildx`

> **Teoría asociada**: [`teoria/14_Registries_y_Distribucion.md`](../../../teoria/14_Registries_y_Distribucion.md)
> **Validación de tipo RUNTIME** (publica a un registry local y revisa la manifest list).

## Objetivo
Construir y publicar una imagen para **varias arquitecturas** (`linux/amd64` y `linux/arm64`) bajo un mismo tag, usando `docker buildx`.

## Especificación técnica
- Imagen canónica: **`masterclass/ej36:latest`** (base `alpine:3.20`, sin pasos `RUN`).
- `CMD ["sh", "/saludo.sh"]` → imprime `imagen multi-arquitectura`.

## Criterios de aceptación (lo que comprueba el script)
- El runner crea un builder buildx, levanta `registry:2` en `localhost:5006` y construye con `--platform linux/amd64,linux/arm64 --push`.
- `docker buildx imagetools inspect` de la imagen publicada muestra **ambas** plataformas (`linux/amd64` y `linux/arm64`).

## Zona de Ejecución Master
```powershell
docker run -d -p 5006:5000 --name mc-registry registry:2
docker buildx create --name mc-builder --use
docker buildx build --platform linux/amd64,linux/arm64 `
  -t localhost:5006/masterclass/ej36:multi --push .
docker buildx imagetools inspect localhost:5006/masterclass/ej36:multi
docker buildx rm mc-builder; docker rm -f mc-registry
```

## Validar
```powershell
.\validar.ps1 36
```
```bash
./validar.sh 36
```
