# Ejercicio 37 — Auditoría con `hadolint`

> **Teoría asociada**: [`teoria/04_Buenas_Practicas_y_Seguridad.md`](../../../teoria/04_Buenas_Practicas_y_Seguridad.md)
> **Validación de tipo CST** (build + `hadolint` como puerta + container-structure-test).

## Objetivo
Auditar y **limpiar** un Dockerfile real: parte de uno que funciona pero está lleno de malas prácticas y déjalo **hadolint-clean** sin cambiar su comportamiento.

## Especificación técnica
- Imagen canónica: **`masterclass/ej37:latest`**.
- Debe seguir imprimiendo `auditoria superada` al ejecutarse.
- Reglas que hay que resolver: `DL3006`, `DL3059`, `DL3015`, `DL3009`, `DL3020`, `DL3025`.
- (`DL3008`/`DL3018` están relajados en toda la masterclass: no hace falta fijar versiones exactas de paquetes.)

## Criterios de aceptación (lo que comprueba el script)
- La imagen **construye**.
- `hadolint` pasa **sin violaciones** (puerta obligatoria desde el ejercicio 07).
- Ejecutar la imagen imprime `auditoria superada`.

## Pista de objetivo (estructura limpia)
```dockerfile
FROM debian:bookworm-slim
RUN apt-get update \
 && apt-get install -y --no-install-recommends ca-certificates \
 && rm -rf /var/lib/apt/lists/*
COPY app/saludo.sh /saludo.sh
CMD ["sh", "/saludo.sh"]
```

## Validar
```powershell
.\validar.ps1 37
```
```bash
./validar.sh 37
```
