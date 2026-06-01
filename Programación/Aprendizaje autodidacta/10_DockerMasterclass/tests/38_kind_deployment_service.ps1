#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 38: Deployment + Service en un cluster kind.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$cluster = "mc-ej38"
$ctx     = "kind-$cluster"
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

    Write-Host ">> kubectl apply -f k8s/app.yaml ..."
    kubectl --context $ctx apply -f $manifest
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1 }

    Write-Host ">> Esperando el rollout del Deployment ..."
    kubectl --context $ctx rollout status deployment/web --timeout=120s
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: el Deployment no quedo disponible."; exit 1 }

    $ready = (kubectl --context $ctx get deploy web -o jsonpath='{.status.readyReplicas}').Trim()
    if ($ready -ne "2") { Write-Host "ERROR: esperaba 2 replicas listas y hay '$ready'."; exit 1 }
    Write-Host "   OK: Deployment web con 2/2 replicas."

    kubectl --context $ctx get svc web | Out-Null
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no existe el Service 'web'."; exit 1 }
    Write-Host "   OK: Service web creado."
    exit 0
}
finally {
    Cleanup
}
