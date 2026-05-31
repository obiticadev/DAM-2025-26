# Ejercicio 24 — Datos persistentes con volúmenes nombrados

> **Teoría asociada**: [`teoria/10_Persistencia_y_Datos.md`](../../teoria/10_Persistencia_y_Datos.md)
> **Validación de tipo RUNTIME** (recrea el contenedor sobre el mismo volumen y verifica que los datos siguen).

## Objetivo
Demostrar que un **volumen nombrado** sobrevive a la destrucción del contenedor: los datos de la base persisten aunque borres y vuelvas a crear el contenedor.

## Especificación técnica
- Imagen canónica: **`masterclass/ej24:latest`**.
- Base `postgres:16-alpine`.
- Incluye `app/seed.sql` en `/docker-entrypoint-initdb.d/` (siembra la tabla `notas` con 1 fila en el primer arranque).

## Criterios de aceptación (lo que comprueba el script)
- Primer contenedor (volumen vacío): el seed crea la tabla `notas` con **1** fila.
- El runner inserta una **2ª** fila, borra el contenedor y crea uno **nuevo** con el **mismo volumen**.
- El nuevo contenedor encuentra **2** filas: los datos persistieron y el seed **no** se volvió a ejecutar.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej24:latest .
docker volume create mc-ej24-data
docker run -d --name pg --mount source=mc-ej24-data,target=/var/lib/postgresql/data `
  -e POSTGRES_PASSWORD=secreto masterclass/ej24:latest
# ... inserta filas, borra el contenedor, recrea con el mismo volumen ...
docker rm -f pg; docker volume rm mc-ej24-data
```

## Validar
```powershell
.\validar.ps1 24
```
```bash
./validar.sh 24
```
