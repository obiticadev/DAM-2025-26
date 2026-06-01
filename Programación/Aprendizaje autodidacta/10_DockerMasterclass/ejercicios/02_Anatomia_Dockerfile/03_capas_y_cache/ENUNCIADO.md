# Ejercicio 03 — Capas y caché de build

> **Teoría asociada**: [`teoria/02_Anatomia_Dockerfile.md`](../../../teoria/02_Anatomia_Dockerfile.md)

## Objetivo
Entender que **cada instrucción es una capa cacheable** y aprender a **ordenar** el Dockerfile para maximizar el reuso de caché (lo estable arriba, lo volátil abajo).

## Especificación técnica
- Imagen canónica: **`masterclass/ej03:latest`**.
- Una capa **estable** (`RUN`) crea `/site/BUILD.txt` con el texto `capa estable`.
- Después, una capa **volátil** (`COPY`) trae `app/` (que incluye `index.html`) a `/site`.

## Criterios de aceptación
- Existe `/site/BUILD.txt` y su contenido incluye `capa estable`.
- Existe `/site/index.html` (copiado desde `app/`).

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej03:latest .

# Edita app/index.html y reconstruye: observa que el RUN sale de CACHE
# (veras "CACHED") y solo se rehace la capa del COPY.
docker build -t masterclass/ej03:latest .

# Mira las capas y su tamaño
docker history masterclass/ej03:latest
docker run --rm masterclass/ej03:latest cat /site/BUILD.txt
```

## Validar
```powershell
.\validar.ps1 03
```
```bash
./validar.sh 03
```
