# Ejercicio 06 — EXPOSE y publicación de puertos

> **Teoría asociada**: [`teoria/02_Anatomia_Dockerfile.md`](../../teoria/02_Anatomia_Dockerfile.md) y [`teoria/09_Redes_Docker.md`](../../teoria/09_Redes_Docker.md)

## Objetivo
Servir una página estática con **nginx** dentro de un contenedor, **documentar** el puerto con `EXPOSE` y **publicarlo** al host con `-p`.

> **Validación de tipo RUNTIME**: este ejercicio no se valida con `container-structure-test`, sino con un script que **levanta el contenedor de verdad**, publica el puerto y hace `curl`.

## Especificación técnica
- Imagen canónica: **`masterclass/ej06:latest`**.
- Base: `nginx:alpine`.
- `app/index.html` se copia a `/usr/share/nginx/html/`.
- La imagen declara `EXPOSE 80`.
- La imagen lleva la etiqueta `ejercicio=06-expose-puertos`.

## Criterios de aceptación (lo que comprueba el script)
- La imagen **declara EXPOSE 80** (visible en `docker inspect`).
- La imagen lleva el **LABEL** `ejercicio=06-expose-puertos`.
- Al ejecutar con `-p 8606:80`, una petición HTTP a `http://localhost:8606` devuelve **200** y el contenido incluye `Masterclass Docker`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej06:latest .
docker run -d --name web -p 8080:80 masterclass/ej06:latest
# Abre http://localhost:8080 en el navegador, o:
curl http://localhost:8080
docker inspect masterclass/ej06:latest --format 'EXPOSE={{.Config.ExposedPorts}}  LABELS={{.Config.Labels}}'
docker rm -f web
```

## Validar
```powershell
.\validar.ps1 06
```
```bash
./validar.sh 06
```
