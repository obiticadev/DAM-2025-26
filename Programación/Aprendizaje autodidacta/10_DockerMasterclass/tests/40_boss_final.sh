#!/usr/bin/env bash
# BOSS FINAL - validacion integral: build + hadolint + Compose e2e + Kubernetes.
set -uo pipefail
EXDIR="$1"; TAG="$2"; ROOT="${3:-}"
PROJ="mc-ej40"
FILE="$EXDIR/compose.yaml"
MANIFEST="$EXDIR/k8s/app.yaml"
DF="$EXDIR/Dockerfile"
URL="http://localhost:8640"
CLUSTER="mc-ej40"
CTX="kind-$CLUSTER"

cleanup() {
  docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null 2>&1 || true
  kind delete cluster --name "$CLUSTER" >/dev/null 2>&1 || true
}
trap cleanup EXIT

cleanup

# ---------- FASE 1: build ----------
echo "=== FASE 1/4: build de la imagen ==="
docker build -t "$TAG" "$EXDIR" || { echo "ERROR: fallo el docker build."; exit 1; }

# ---------- FASE 2: hadolint ----------
echo "=== FASE 2/4: hadolint ==="
docker run --rm -i hadolint/hadolint --ignore DL3008 --ignore DL3018 < "$DF" || { echo "ERROR: hadolint encontro violaciones en el Dockerfile."; exit 1; }
echo "   OK: Dockerfile limpio."

# ---------- FASE 3: Compose e2e ----------
echo "=== FASE 3/4: stack Compose (proxy->api->cache/db) ==="
docker compose -p "$PROJ" -f "$FILE" up -d --build || { echo "ERROR: 'docker compose up' fallo. Revisa compose.yaml."; exit 1; }

V1=""
for _ in $(seq 1 40); do
  RESP=$(curl -fsS "$URL" 2>/dev/null || true)
  if echo "$RESP" | grep -qE "MENSAJE=boss final superado visitas=[0-9]+"; then
    V1=$(echo "$RESP" | grep -oE "visitas=[0-9]+" | grep -oE "[0-9]+")
    break
  fi
  sleep 2
done
[ -n "$V1" ] || { echo "ERROR: el proxy no devolvio la respuesta esperada de la api."; exit 1; }
RESP2=$(curl -fsS "$URL" 2>/dev/null || true)
V2=$(echo "$RESP2" | grep -oE "visitas=[0-9]+" | grep -oE "[0-9]+")
if [ -z "$V2" ] || [ "$V2" -le "$V1" ]; then echo "ERROR: el contador de visitas no incremento ($V1 -> $V2)."; exit 1; fi
echo "   OK: api accesible por el proxy y la cache incrementa visitas ($V1 -> $V2)."
docker compose -p "$PROJ" -f "$FILE" down -v >/dev/null

# ---------- FASE 4: Kubernetes ----------
echo "=== FASE 4/4: Kubernetes (kind) ==="
command -v kind >/dev/null 2>&1 && command -v kubectl >/dev/null 2>&1 || { echo "ERROR: faltan 'kind' y/o 'kubectl' para la fase de Kubernetes del Boss."; exit 1; }

kind create cluster --name "$CLUSTER" || { echo "ERROR: no se pudo crear el cluster kind."; exit 1; }
echo ">> Cargando la imagen en el cluster (kind load) ..."
kind load docker-image "$TAG" --name "$CLUSTER" || { echo "ERROR: 'kind load docker-image' fallo."; exit 1; }

kubectl --context "$CTX" apply -f "$MANIFEST" || { echo "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1; }
kubectl --context "$CTX" rollout status deployment/api --timeout=150s || { echo "ERROR: el Deployment 'api' no quedo disponible."; exit 1; }

READY=$(kubectl --context "$CTX" get deploy api -o jsonpath='{.status.readyReplicas}')
[ "$READY" = "2" ] || { echo "ERROR: esperaba 2 replicas de 'api' y hay '$READY'."; exit 1; }
echo "   OK: Deployment api 2/2."

POD=$(kubectl --context "$CTX" get pods -l app=api -o jsonpath='{.items[0].metadata.name}')
ENVS=$(kubectl --context "$CTX" exec "$POD" -- printenv)
echo "$ENVS" | grep -q "MENSAJE=boss final superado" || { echo "ERROR: el Pod no tiene MENSAJE del ConfigMap."; exit 1; }
echo "$ENVS" | grep -q "API_KEY=supersecreto"         || { echo "ERROR: el Pod no tiene API_KEY del Secret."; exit 1; }
echo "   OK: ConfigMap y Secret inyectados."

RESP3=$(kubectl --context "$CTX" exec "$POD" -- wget -qO- http://localhost:8080/ || true)
echo "$RESP3" | grep -q "MENSAJE=boss final superado" || { echo "ERROR: la api en k8s no respondio correctamente (resp: $RESP3)."; exit 1; }
echo "   OK: la api responde dentro del cluster hablando con la cache."

echo ""
echo "  *** BOSS FINAL SUPERADO: de noob a pro ***"
exit 0
