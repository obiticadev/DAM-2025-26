#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 12: el contenedor debe llegar a estado 'healthy'.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$cname = "mc-ej12"

function Cleanup { docker rm -f $cname 2>$null | Out-Null }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # 1) La imagen debe declarar un HEALTHCHECK
    $hc = docker inspect --format '{{json .Config.Healthcheck}}' $Tag
    if ([string]::IsNullOrWhiteSpace($hc) -or $hc -eq "null") {
        Write-Host "ERROR: la imagen no declara HEALTHCHECK."; exit 1
    }
    Write-Host "   OK: HEALTHCHECK declarado."

    # 2) El contenedor debe llegar a 'healthy'
    Cleanup
    docker run -d --name $cname $Tag | Out-Null
    $status = "starting"
    for ($i = 0; $i -lt 40; $i++) {
        $status = (docker inspect --format '{{.State.Health.Status}}' $cname).Trim()
        if ($status -eq "healthy")   { break }
        if ($status -eq "unhealthy") { Write-Host "ERROR: el contenedor quedo unhealthy."; exit 1 }
        Start-Sleep -Seconds 1
    }
    if ($status -ne "healthy") { Write-Host "ERROR: el contenedor no llego a healthy (estado: $status)."; exit 1 }
    Write-Host "   OK: el contenedor alcanzo el estado healthy."

    exit 0
}
finally {
    Cleanup
}
