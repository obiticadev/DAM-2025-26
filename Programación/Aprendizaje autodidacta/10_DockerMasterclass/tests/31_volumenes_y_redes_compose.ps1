#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 31: volumen nombrado + red propia en Compose.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej31"
$file = Join-Path $ExercisePath "compose.yaml"
$vol  = "mc-ej31_datos"
$net  = "mc-ej31_backend"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d ..."
    docker compose -p $proj -f $file up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1 }

    Start-Sleep -Seconds 2

    # 1) Volumen nombrado
    docker volume inspect $vol 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no existe el volumen $vol (declara el volumen nombrado 'datos')."; exit 1 }
    Write-Host "   OK: existe el volumen nombrado $vol."

    # 2) Red propia
    docker network inspect $net 2>$null | Out-Null
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no existe la red $net (declara la red 'backend')."; exit 1 }
    Write-Host "   OK: existe la red $net."

    # 3) El servicio db esta conectado a esa red
    $dbId = (docker compose -p $proj -f $file ps -q db).Trim()
    if (-not $dbId) { Write-Host "ERROR: no encuentro el contenedor del servicio 'db'."; exit 1 }
    $nets = docker inspect --format '{{range $k,$v := .NetworkSettings.Networks}}{{$k}} {{end}}' $dbId
    if ($nets -notmatch [regex]::Escape($net)) { Write-Host "ERROR: 'db' no esta conectado a $net (redes: $nets)."; exit 1 }
    Write-Host "   OK: el servicio db esta conectado a la red $net."
    exit 0
}
finally {
    Cleanup
}
