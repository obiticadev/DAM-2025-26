# Ejercicio 38 — Kubernetes con kind: Deployment + Service

> **Teoría asociada**: [`teoria/15_Intro_Kubernetes.md`](../../../teoria/15_Intro_Kubernetes.md)
> **Validación de tipo RUNTIME** (crea un clúster `kind`, aplica el manifiesto y comprueba el estado).
> **Requisitos**: tener instalados `kind` y `kubectl`.

## Objetivo
Dar el salto de Docker a Kubernetes: declarar el **estado deseado** con un `Deployment` (2 réplicas) y exponerlo con un `Service`.

## Especificación técnica
- Manifiesto en `k8s/app.yaml`:
  - `Deployment` `web` con **2 réplicas** de `nginx:alpine`,
  - `Service` `web` de tipo `ClusterIP` que apunta al puerto 80.

## Criterios de aceptación (lo que comprueba el script)
- El runner crea un clúster `kind` (`mc-ej38`), aplica `k8s/app.yaml` y espera el `rollout`.
- El `Deployment web` queda con **2/2** réplicas listas.
- Existe el `Service web`.

## Zona de Ejecución Master
```powershell
kind create cluster --name mc-ej38
kubectl --context kind-mc-ej38 apply -f k8s/app.yaml
kubectl --context kind-mc-ej38 rollout status deployment/web
kubectl --context kind-mc-ej38 get deploy,svc
kind delete cluster --name mc-ej38
```

## Validar
```powershell
.\validar.ps1 38
```
```bash
./validar.sh 38
```
