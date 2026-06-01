# Ejercicio 35 — Registry local: push y pull

> **Teoría asociada**: [`teoria/14_Registries_y_Distribucion.md`](../../../teoria/14_Registries_y_Distribucion.md)
> **Validación de tipo RUNTIME** (levanta `registry:2`, hace push y pull).

## Objetivo
Conocer el ciclo de distribución de imágenes: `build` → `tag` (con el host del registry) → `push` → `pull`, usando un **registry privado local**.

## Especificación técnica
- Imagen canónica: **`masterclass/ej35:latest`** (base `alpine:3.20`).
- Al ejecutarse imprime `imagen desde el registry local`.

## Criterios de aceptación (lo que comprueba el script)
- El runner arranca `registry:2` en `localhost:5005`.
- Etiqueta tu imagen como `localhost:5005/masterclass/ej35:1.0`, hace **push**, borra las copias locales y hace **pull**.
- La imagen descargada se ejecuta e imprime `imagen desde el registry local`.

## Zona de Ejecución Master
```powershell
docker run -d -p 5005:5000 --name mc-registry registry:2
docker build -t masterclass/ej35:latest .
docker tag masterclass/ej35:latest localhost:5005/masterclass/ej35:1.0
docker push localhost:5005/masterclass/ej35:1.0
docker pull localhost:5005/masterclass/ej35:1.0
docker run --rm localhost:5005/masterclass/ej35:1.0
docker rm -f mc-registry
```

## Validar
```powershell
.\validar.ps1 35
```
```bash
./validar.sh 35
```
