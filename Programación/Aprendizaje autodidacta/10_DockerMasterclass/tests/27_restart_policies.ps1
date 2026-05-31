#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 27: la restart policy reinicia un proceso que falla.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$c = "mc-ej27"

function Cleanup { docker rm -f $c 2>$null | Out-Null }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    Write-Host ">> Lanzando con --restart on-failure:3 ..."
    docker run -d --name $c --restart on-failure:3 $Tag | Out-Null

    $count = 0
    for ($i = 1; $i -le 30; $i++) {
        Start-Sleep -Seconds 1
        $count = [int](docker inspect --format '{{.RestartCount}}' $c)
        if ($count -ge 2) { break }
    }
    if ($count -lt 2) { Write-Host "ERROR: RestartCount = $count (esperaba >= 2). La politica de reinicio no actuo."; exit 1 }
    Write-Host "   OK: Docker reinicio el contenedor (RestartCount = $count)."
    exit 0
}
finally {
    Cleanup
}
