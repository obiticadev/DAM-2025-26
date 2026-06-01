#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 34: servicio opcional gobernado por un profile.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej34"
$file = Join-Path $ExercisePath "compose.yaml"

function Cleanup { docker compose -p $proj -f $file --profile debug down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup

    # 1) Sin perfil: web si, herramientas no
    Write-Host ">> up SIN perfil ..."
    docker compose -p $proj -f $file up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo."; exit 1 }
    Start-Sleep -Seconds 2

    $web = (docker compose -p $proj -f $file ps -q web).Trim()
    if (-not $web) { Write-Host "ERROR: el servicio 'web' deberia estar en marcha."; exit 1 }
    $tools = (docker compose -p $proj -f $file ps -q herramientas)
    if ($tools -and $tools.Trim() -ne "") { Write-Host "ERROR: 'herramientas' NO deberia arrancar sin el perfil debug."; exit 1 }
    Write-Host "   OK: sin perfil solo arranca 'web'."

    # 2) Con perfil debug: herramientas tambien
    Write-Host ">> up CON --profile debug ..."
    docker compose -p $proj -f $file --profile debug up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose --profile debug up' fallo."; exit 1 }
    Start-Sleep -Seconds 2

    $tools2 = (docker compose -p $proj -f $file --profile debug ps -q herramientas)
    if (-not $tools2 -or $tools2.Trim() -eq "") { Write-Host "ERROR: 'herramientas' deberia arrancar con --profile debug (revisa profiles)."; exit 1 }
    Write-Host "   OK: con --profile debug arranca tambien 'herramientas'."
    exit 0
}
finally {
    Cleanup
}
