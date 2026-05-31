# Ejercicio 01 — Hola Contenedor

> **Teoría asociada**: [`teoria/00_Que_Es_Un_Contenedor.md`](../../teoria/00_Que_Es_Un_Contenedor.md) y [`teoria/01_Imagenes_vs_Contenedores.md`](../../teoria/01_Imagenes_vs_Contenedores.md)

## Objetivo
Construir tu primera imagen Docker que **empaqueta y ejecuta** un script ya resuelto, y dominar el **ciclo de vida** de un contenedor con la CLI (`run` / `ps` / `logs` / `rm`).

## Especificación técnica
- Imagen canónica: **`masterclass/ej01:latest`**.
- La imagen parte de una base de Linux **mínima** (pocos MB).
- El script `app/saludo.sh` (ya resuelto) queda dentro de la imagen en `/saludo.sh`.
- El script es **ejecutable** dentro de la imagen.
- Al ejecutar el contenedor sin argumentos, arranca `/saludo.sh` como proceso principal y este imprime:
  ```
  Hola, Contenedor!
  Soy el proceso PID 1 dentro de una imagen Docker.
  ```

## Criterios de aceptación (lo que pondrá el test en VERDE)
- `/saludo.sh` **existe** en la imagen (file existence test).
- Ejecutar `/saludo.sh` imprime el texto `Hola, Contenedor!` (command test).
- El **CMD** de la imagen es `["/saludo.sh"]` (metadata test) — usa la forma de lista.

## Zona de Ejecución Master (experimenta con tus ojos)
```powershell
# 1) Construye la imagen
docker build -t masterclass/ej01:latest .

# 2) Ejecutala (ve la salida y borra el contenedor al salir)
docker run --rm masterclass/ej01:latest

# 3) Ejecuta en segundo plano para practicar el ciclo de vida
docker run -d --name hola masterclass/ej01:latest
docker ps -a                 # estado del contenedor (Exited)
docker logs hola             # relee su salida
docker rm hola               # borralo

# 4) Inspecciona la imagen
docker image ls masterclass/ej01
docker history masterclass/ej01:latest
```

## Validar el ejercicio
```powershell
.\validar.ps1 01      # Windows / PowerShell
```
```bash
./validar.sh 01       # WSL / Linux
```
Verás **`✅ EJERCICIO 01 SUPERADO`** cuando todo pase a verde.
