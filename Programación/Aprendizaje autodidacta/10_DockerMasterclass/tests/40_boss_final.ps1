#!/usr/bin/env pwsh
# BOSS FINAL - validacion integral: build + hadolint + Compose e2e + Kubernetes.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj     = "mc-ej40"
$file     = Join-Path $ExercisePath "compose.yaml"
$manifest = Join-Path $ExercisePath "k8s/app.yaml"
$df       = Join-Path $ExercisePath "Dockerfile"
$url      = "http://localhost:8640"
$cluster  = "mc-ej40"
$ctx      = "kind-$cluster"

function Have($cmd) { return [bool](Get-Command $cmd -ErrorAction SilentlyContinue) }
function Cleanup {
    docker compose -p $proj -f $file down -v 2>$null | Out-Null
    kind delete cluster --name $cluster 2>$null | Out-Null
}

try {
    Cleanup

    # ---------- FASE 1: build ----------
    Write-Host "=== FASE 1/4: build de la imagen ==="
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # ---------- FASE 2: hadolint ----------
    Write-Host "=== FASE 2/4: hadolint ==="
    Get-Content $df | docker run --rm -i hadolint/hadolint --ignore DL3008 --ignore DL3018
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: hadolint encontro violaciones en el Dockerfile."; exit 1 }
    Write-Host "   OK: Dockerfile limpio."

    # ---------- FASE 3: Compose e2e ----------
    Write-Host "=== FASE 3/4: stack Compose (proxy->api->cache/db) ==="
    docker compose -p $proj -f $file up -d --build
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa compose.yaml."; exit 1 }

    $v1 = $null; $v2 = $null
    for ($i = 1; $i -le 40; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
            if ($r.Content -match "MENSAJE=boss final superado visitas=(\d+)") { $v1 = [int]$Matches[1]; break }
        } catch { }
        Start-Sleep -Seconds 2
    }
    if ($null -eq $v1) { Write-Host "ERROR: el proxy no devolvio la respuesta esperada de la api."; exit 1 }
    $r2 = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
    if ($r2.Content -match "visitas=(\d+)") { $v2 = [int]$Matches[1] }
    if ($null -eq $v2 -or $v2 -le $v1) { Write-Host "ERROR: el contador de visitas no incremento ($v1 -> $v2)."; exit 1 }
    Write-Host "   OK: api accesible por el proxy y la cache incrementa visitas ($v1 -> $v2)."
    docker compose -p $proj -f $file down -v | Out-Null

    # ---------- FASE 4: Kubernetes ----------
    Write-Host "=== FASE 4/4: Kubernetes (kind) ==="
    if (-not (Have "kind") -or -not (Have "kubectl")) {
        Write-Host "ERROR: faltan 'kind' y/o 'kubectl' para la fase de Kubernetes del Boss."; exit 1
    }
    kind create cluster --name $cluster
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no se pudo crear el cluster kind."; exit 1 }
    Write-Host ">> Cargando la imagen en el cluster (kind load) ..."
    kind load docker-image $Tag --name $cluster
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'kind load docker-image' fallo."; exit 1 }

    kubectl --context $ctx apply -f $manifest
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'kubectl apply' fallo. Revisa el manifiesto."; exit 1 }
    kubectl --context $ctx rollout status deployment/api --timeout=150s
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: el Deployment 'api' no quedo disponible."; exit 1 }

    $ready = (kubectl --context $ctx get deploy api -o jsonpath='{.status.readyReplicas}').Trim()
    if ($ready -ne "2") { Write-Host "ERROR: esperaba 2 replicas de 'api' y hay '$ready'."; exit 1 }
    Write-Host "   OK: Deployment api 2/2."

    $pod = (kubectl --context $ctx get pods -l app=api -o jsonpath='{.items[0].metadata.name}').Trim()
    $envs = kubectl --context $ctx exec $pod -- printenv | Out-String
    if ($envs -notmatch "MENSAJE=boss final superado") { Write-Host "ERROR: el Pod no tiene MENSAJE del ConfigMap."; exit 1 }
    if ($envs -notmatch "API_KEY=supersecreto")         { Write-Host "ERROR: el Pod no tiene API_KEY del Secret."; exit 1 }
    Write-Host "   OK: ConfigMap y Secret inyectados."

    $resp = kubectl --context $ctx exec $pod -- wget -qO- http://localhost:8080/ | Out-String
    if ($resp -notmatch "MENSAJE=boss final superado") { Write-Host "ERROR: la api en k8s no respondio correctamente (resp: $resp)."; exit 1 }
    Write-Host "   OK: la api responde dentro del cluster hablando con la cache."

    Write-Host ""
    Write-Host "  *** BOSS FINAL SUPERADO: de noob a pro ***"
    exit 0
}
finally {
    Cleanup
}
