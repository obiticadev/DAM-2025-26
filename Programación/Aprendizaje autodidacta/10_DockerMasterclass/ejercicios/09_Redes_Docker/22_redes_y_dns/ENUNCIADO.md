# Ejercicio 22 — Redes y DNS interno

> **Teoría asociada**: [`teoria/09_Redes_Docker.md`](../../../teoria/09_Redes_Docker.md)
> **Validación de tipo RUNTIME** (crea una red, lanza servidor + cliente y comprueba la resolución por nombre).

## Objetivo
Entender que en una **red definida por el usuario** los contenedores se ven entre sí **por su nombre** gracias al DNS interno de Docker, mientras que en la red `bridge` por defecto no.

## Especificación técnica
- Imagen canónica: **`masterclass/ej22:latest`**.
- Base **`alpine:3.20`** + `curl`.
- Es un cliente que recibe un host por argumento (por defecto `api`) y se conecta por HTTP.
- Al conectar con éxito imprime `conectado a <host> por DNS`.

## Criterios de aceptación (lo que comprueba el script)
- El runner crea una red `mc-ej22-net`, levanta un servidor `nginx` llamado **`api`** en ella y lanza tu imagen en la **misma red**.
- Tu contenedor resuelve `api` por nombre y imprime `conectado a api por DNS`.

## Zona de Ejecución Master
```powershell
docker build -t masterclass/ej22:latest .
docker network create mc-ej22-net
docker run -d --name api --network mc-ej22-net nginx:alpine
docker run --rm --network mc-ej22-net masterclass/ej22:latest api
# limpieza
docker rm -f api; docker network rm mc-ej22-net
```

## Validar
```powershell
.\validar.ps1 22
```
```bash
./validar.sh 22
```
