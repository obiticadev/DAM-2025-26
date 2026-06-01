# Ejercicio 39 — ConfigMap, Secret, escalado y rolling update

> **Teoría asociada**: [`teoria/15_Intro_Kubernetes.md`](../../../teoria/15_Intro_Kubernetes.md)
> **Validación de tipo RUNTIME** (clúster `kind`, inyección de config/secret, escalado y rollout).
> **Requisitos**: `kind` y `kubectl`.

## Objetivo
Inyectar **configuración** (`ConfigMap`) y **secretos** (`Secret`) como variables de entorno en los Pods, **escalar** el Deployment y lanzar un **rolling update**.

## Especificación técnica
- Manifiesto en `k8s/app.yaml`:
  - `ConfigMap web-config` con `MENSAJE=hola desde configmap`,
  - `Secret web-secret` (Opaque, `stringData`) con `API_KEY=supersecreto`,
  - `Deployment web` (`nginx:alpine`, **2 réplicas**) que inyecta `MENSAJE` (configMapKeyRef) y `API_KEY` (secretKeyRef).

## Criterios de aceptación (lo que comprueba el script)
- Tras el `rollout`, un Pod tiene `MENSAJE=hola desde configmap` y `API_KEY=supersecreto` en su entorno.
- Al escalar a **3** réplicas, el Deployment queda con **3/3** listas.
- Un rolling update (cambio de imagen) completa el `rollout` sin error.

## Zona de Ejecución Master
```powershell
kind create cluster --name mc-ej39
kubectl --context kind-mc-ej39 apply -f k8s/app.yaml
kubectl --context kind-mc-ej39 rollout status deployment/web
kubectl --context kind-mc-ej39 scale deployment/web --replicas=3
kubectl --context kind-mc-ej39 set image deployment/web web=nginx:1.27-alpine
kubectl --context kind-mc-ej39 rollout status deployment/web
kind delete cluster --name mc-ej39
```

## Validar
```powershell
.\validar.ps1 39
```
```bash
./validar.sh 39
```
