#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 39: ConfigMap + Secret + escalado + rolling update.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$cluster  = "mc-ej39"
$ctx      = "kind-$cluster"
$manifest = Join-Path $ExercisePath "k8s/app.yaml"

function Have($cmd) { return [bool](Get-Command $cmd -ErrorAction SilentlyContinue) }
function Cleanup { kind delete cluster --name $cluster 2>$null | Out-Null }

try {
    if (-not (Have "kind"))    { Write-Host "ERROR: falta 'kind'. Instalalo para este bloque de Kubernetes."; exit 1 }
    if (-not (Have "kubectl")) { Write-Host "ERROR: falta 'kubectl'. Instalalo para este bloque de Kubernetes."; exit 1 }
    if (-not (Test-Path $manifest)) { Write-Host "ERROR: falta k8s/app.yaml en $ExercisePath"; exit 1 }

    Cleanup
    Write-Host ">> Creando cluster kind '$cluster' (puede tardar ~1 min) ..."
    kind create cluster --name $cluster
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no se pudo crear el cluster kind."; exit 1 }

    Write-Host ">> kubectl apply ..."
    kubectl --context $ctx apply -f $manifest
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1 }

    kubectl --context $ctx rollout status deployment/web --timeout=120s
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: el Deployment no quedo disponible."; exit 1 }

    # 1) ConfigMap + Secret inyectados en el Pod
    $pod = (kubectl --context $ctx get pods -l app=web -o jsonpath='{.items[0].metadata.name}').Trim()
    if (-not $pod) { Write-Host "ERROR: no encuentro Pods de la app web."; exit 1 }
    $envs = kubectl --context $ctx exec $pod -- printenv | Out-String
    if ($envs -notmatch "MENSAJE=hola desde configmap") { Write-Host "ERROR: el Pod no tiene MENSAJE del ConfigMap."; exit 1 }
    if ($envs -notmatch "API_KEY=supersecreto")         { Write-Host "ERROR: el Pod no tiene API_KEY del Secret."; exit 1 }
    Write-Host "   OK: ConfigMap y Secret inyectados como variables de entorno."

    # 2) Escalado a 3
    kubectl --context $ctx scale deployment/web --replicas=3 | Out-Null
    kubectl --context $ctx rollout status deployment/web --timeout=120s | Out-Null
    $ready = (kubectl --context $ctx get deploy web -o jsonpath='{.status.readyReplicas}').Trim()
    if ($ready -ne "3") { Write-Host "ERROR: tras escalar esperaba 3 replicas y hay '$ready'."; exit 1 }
    Write-Host "   OK: escalado a 3/3 replicas."

    # 3) Rolling update
    kubectl --context $ctx set image deployment/web web=nginx:1.27-alpine | Out-Null
    kubectl --context $ctx rollout status deployment/web --timeout=120s
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: el rolling update no completo."; exit 1 }
    Write-Host "   OK: rolling update completado sin caida."
    exit 0
}
finally {
    Cleanup
}
