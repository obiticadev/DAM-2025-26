#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 22: DNS interno en red definida por el usuario.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$net = "mc-ej22-net"
$srv = "mc-ej22-api"

function Cleanup {
    docker rm -f $srv 2>$null | Out-Null
    docker network rm $net 2>$null | Out-Null
}

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    Write-Host ">> Creando red $net y servidor 'api' ..."
    docker network create $net | Out-Null
    # El servidor se llama 'api' (alias de red), aunque el contenedor tenga otro nombre.
    docker run -d --name $srv --network $net --network-alias api nginx:alpine | Out-Null

    Start-Sleep -Seconds 2
    Write-Host ">> Lanzando el cliente en la misma red (resuelve 'api' por nombre) ..."
    $out = docker run --rm --network $net $Tag api
    Write-Host $out
    if ($out -notmatch "conectado a api por DNS") {
        Write-Host "ERROR: el cliente no resolvio/conecto con 'api' por DNS (salida: $out)"; exit 1
    }
    Write-Host "   OK: resolucion por nombre en red definida por el usuario."
    exit 0
}
finally {
    Cleanup
}
