#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 28: docker compose up + comprobacion HTTP.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej28"
$file = Join-Path $ExercisePath "compose.yaml"
$url  = "http://localhost:8628"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d ..."
    docker compose -p $proj -f $file up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1 }

    $ok = $false
    for ($i = 1; $i -le 20; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
            if ($r.Content -match "Compose funcionando") { $ok = $true; break }
        } catch { Start-Sleep -Seconds 1 }
    }
    if (-not $ok) { Write-Host "ERROR: $url no devolvio 'Compose funcionando'."; exit 1 }
    Write-Host "   OK: el servicio web responde con la pagina esperada."
    exit 0
}
finally {
    Cleanup
}
