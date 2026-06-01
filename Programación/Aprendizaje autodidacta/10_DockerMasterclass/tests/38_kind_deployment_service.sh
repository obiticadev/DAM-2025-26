#!/usr/bin/env bash
# Test RUNTIME del ejercicio 38: Deployment + Service en un cluster kind.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
CLUSTER="mc-ej38"
CTX="kind-$CLUSTER"
MANIFEST="$EXDIR/k8s/app.yaml"

cleanup() { kind delete cluster --name "$CLUSTER" >/dev/null 2>&1 || true; }
trap cleanup EXIT

command -v kind    >/dev/null 2>&1 || { echo "ERROR: falta 'kind'. Instalalo para este bloque de Kubernetes."; exit 1; }
command -v kubectl >/dev/null 2>&1 || { echo "ERROR: falta 'kubectl'. Instalalo para este bloque de Kubernetes."; exit 1; }
[ -f "$MANIFEST" ] || { echo "ERROR: falta k8s/app.yaml en $EXDIR"; exit 1; }

cleanup
echo ">> Creando cluster kind '$CLUSTER' (puede tardar ~1 min) ..."
kind create cluster --name "$CLUSTER" || { echo "ERROR: no se pudo crear el cluster kind."; exit 1; }

echo ">> kubectl apply -f k8s/app.yaml ..."
kubectl --context "$CTX" apply -f "$MANIFEST" || { echo "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1; }

echo ">> Esperando el rollout del Deployment ..."
kubectl --context "$CTX" rollout status deployment/web --timeout=120s || { echo "ERROR: el Deployment no quedo disponible."; exit 1; }

READY=$(kubectl --context "$CTX" get deploy web -o jsonpath='{.status.readyReplicas}')
[ "$READY" = "2" ] || { echo "ERROR: esperaba 2 replicas listas y hay '$READY'."; exit 1; }
echo "   OK: Deployment web con 2/2 replicas."

kubectl --context "$CTX" get svc web >/dev/null || { echo "ERROR: no existe el Service 'web'."; exit 1; }
echo "   OK: Service web creado."
exit 0
