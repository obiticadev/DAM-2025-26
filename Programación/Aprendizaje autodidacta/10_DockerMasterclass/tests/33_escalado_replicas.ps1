#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 33: 3 replicas en ejecucion.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej33"
$file = Join-Path $ExercisePath "compose.yaml"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d ..."
    docker compose -p $proj -f $file up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1 }

    Start-Sleep -Seconds 2
    $ids = docker compose -p $proj -f $file ps -q worker
    $n = ($ids | Where-Object { $_.Trim() -ne "" } | Measure-Object).Count
    if ($n -ne 3) { Write-Host "ERROR: esperaba 3 replicas de 'worker' y hay $n (usa deploy.replicas: 3)."; exit 1 }
    Write-Host "   OK: 3 replicas de 'worker' en ejecucion."
    exit 0
}
finally {
    Cleanup
}
