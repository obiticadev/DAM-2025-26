#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 30: db healthy + web arranca tras la condicion.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej30"
$file = Join-Path $ExercisePath "compose.yaml"
$url  = "http://localhost:8630"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d --build ..."
    docker compose -p $proj -f $file up -d --build
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1 }

    # 1) La db debe alcanzar estado healthy
    $dbId = (docker compose -p $proj -f $file ps -q db).Trim()
    if (-not $dbId) { Write-Host "ERROR: no encuentro el contenedor del servicio 'db'."; exit 1 }
    $healthy = $false
    for ($i = 1; $i -le 40; $i++) {
        $st = (docker inspect --format '{{.State.Health.Status}}' $dbId 2>$null)
        if ($st) { $st = $st.Trim() }
        if ($st -eq "healthy") { $healthy = $true; break }
        Start-Sleep -Seconds 1
    }
    if (-not $healthy) { Write-Host "ERROR: la db nunca llego a 'healthy' (revisa el healthcheck)."; exit 1 }
    Write-Host "   OK: la db esta healthy."

    # 2) La web responde
    $ok = $false
    for ($i = 1; $i -le 20; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
            if ($r.Content -match "DB OK: 1") { $ok = $true; break }
        } catch { Start-Sleep -Seconds 1 }
    }
    if (-not $ok) { Write-Host "ERROR: $url no devolvio 'DB OK: 1'."; exit 1 }
    Write-Host "   OK: la web arranco tras la db y respondio correctamente."
    exit 0
}
finally {
    Cleanup
}
