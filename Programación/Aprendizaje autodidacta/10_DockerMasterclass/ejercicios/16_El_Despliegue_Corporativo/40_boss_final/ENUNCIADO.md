# BOSS FINAL (ejercicio 40) — De la imagen al clúster

> **Teoría asociada**: [`teoria/16_El_Despliegue_Corporativo.md`](../../../teoria/16_El_Despliegue_Corporativo.md)
> **Validación de tipo RUNTIME** (build + hadolint + Compose e2e + Kubernetes en kind).
> **Requisitos del bloque k8s**: `kind` y `kubectl` (si faltan, la fase de Kubernetes falla con un mensaje claro).

## Objetivo
Integrar **todo** el temario en un único entregable de producción:
1. una **imagen** multi-stage, sin root y con healthcheck (`Dockerfile`),
2. un **stack Compose** de 4 servicios (`proxy → api → cache`/`db`) con `depends_on: service_healthy`,
3. un **despliegue en Kubernetes** con `ConfigMap`, `Secret`, `Deployment` (2 réplicas) y `Service`.

La aplicación (`app/`, en Go, sin dependencias externas) y el `proxy/nginx.conf` ya están resueltos. Tu trabajo son los **tres artefactos de orquestación**.

## Entregables a completar
- **`Dockerfile`**: build multi-stage de `app/`, imagen final `alpine:3.20` con usuario `appuser`, `HEALTHCHECK` a `/health` y `CMD ["/api"]`.
- **`compose.yaml`**: `api` (build, `depends_on` cache+db sanos), `cache` (redis con healthcheck), `db` (ya hecho), `proxy` (nginx, puerto `8640:80`, monta `proxy/nginx.conf`).
- **`k8s/app.yaml`**: `ConfigMap` (`MENSAJE`), `Secret` (`API_KEY`), `Deployment api` (2 réplicas, imagen `masterclass/ej40:latest`, env desde config/secret) y su `Service`.

## Criterios de aceptación (lo que comprueba el script)
1. **Build**: la imagen `masterclass/ej40:latest` construye.
2. **hadolint**: el `Dockerfile` pasa limpio (puerta de calidad).
3. **Compose e2e**: `http://localhost:8640` devuelve `MENSAJE=boss final superado` y el contador `visitas` **incrementa** entre dos peticiones (la api habla con la cache).
4. **Kubernetes**: tras `kind load` + `kubectl apply`, el `Deployment api` queda **2/2**; un Pod tiene `MENSAJE` y `API_KEY` inyectados; la api responde dentro del clúster con `MENSAJE=boss final superado`.

## Zona de Ejecución Master
```powershell
# 1) Imagen + lint
docker build -t masterclass/ej40:latest .
Get-Content Dockerfile | docker run --rm -i hadolint/hadolint --ignore DL3008 --ignore DL3018

# 2) Stack Compose
docker compose -p mc-ej40 up -d --build
curl http://localhost:8640        # MENSAJE=boss final superado visitas=1
curl http://localhost:8640        # ... visitas=2
docker compose -p mc-ej40 down -v

# 3) Kubernetes con kind
kind create cluster --name mc-ej40
kind load docker-image masterclass/ej40:latest --name mc-ej40
kubectl --context kind-mc-ej40 apply -f k8s/app.yaml
kubectl --context kind-mc-ej40 rollout status deployment/api
kind delete cluster --name mc-ej40
```

## Validar
```powershell
.\validar.ps1 40
```
```bash
./validar.sh 40
```

¡Si esto pasa en verde, has pasado de **noob a pro**! 🐳
