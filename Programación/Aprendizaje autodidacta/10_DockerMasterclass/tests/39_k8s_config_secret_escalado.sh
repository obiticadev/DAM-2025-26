#!/usr/bin/env bash
# Test RUNTIME del ejercicio 39: ConfigMap + Secret + escalado + rolling update.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
CLUSTER="mc-ej39"
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

echo ">> kubectl apply ..."
kubectl --context "$CTX" apply -f "$MANIFEST" || { echo "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1; }

kubectl --context "$CTX" rollout status deployment/web --timeout=120s || { echo "ERROR: el Deployment no quedo disponible."; exit 1; }

# 1) ConfigMap + Secret inyectados en el Pod
POD=$(kubectl --context "$CTX" get pods -l app=web -o jsonpath='{.items[0].metadata.name}')
[ -n "$POD" ] || { echo "ERROR: no encuentro Pods de la app web."; exit 1; }
ENVS=$(kubectl --context "$CTX" exec "$POD" -- printenv)
echo "$ENVS" | grep -q "MENSAJE=hola desde configmap" || { echo "ERROR: el Pod no tiene MENSAJE del ConfigMap."; exit 1; }
echo "$ENVS" | grep -q "API_KEY=supersecreto"         || { echo "ERROR: el Pod no tiene API_KEY del Secret."; exit 1; }
echo "   OK: ConfigMap y Secret inyectados como variables de entorno."

# 2) Escalado a 3
kubectl --context "$CTX" scale deployment/web --replicas=3 >/dev/null
kubectl --context "$CTX" rollout status deployment/web --timeout=120s >/dev/null
READY=$(kubectl --context "$CTX" get deploy web -o jsonpath='{.status.readyReplicas}')
[ "$READY" = "3" ] || { echo "ERROR: tras escalar esperaba 3 replicas y hay '$READY'."; exit 1; }
echo "   OK: escalado a 3/3 replicas."

# 3) Rolling update
kubectl --context "$CTX" set image deployment/web web=nginx:1.27-alpine >/dev/null
kubectl --context "$CTX" rollout status deployment/web --timeout=120s || { echo "ERROR: el rolling update no completo."; exit 1; }
echo "   OK: rolling update completado sin caida."
exit 0
