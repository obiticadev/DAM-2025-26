#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 23: app de Python conectada a PostgreSQL por red.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$net = "mc-ej23-net"
$db  = "mc-ej23-db"

function Cleanup {
    docker rm -f $db 2>$null | Out-Null
    docker network rm $net 2>$null | Out-Null
}

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    Write-Host ">> Levantando PostgreSQL ..."
    docker network create $net | Out-Null
    docker run -d --name $db --network $net --network-alias db `
        -e POSTGRES_PASSWORD=secreto postgres:16-alpine | Out-Null

    Write-Host ">> Lanzando la app (reintenta hasta que la BBDD acepte conexiones) ..."
    $out = docker run --rm --network $net `
        -e DB_HOST=db -e DB_USER=postgres -e DB_PASSWORD=secreto -e DB_NAME=postgres `
        $Tag
    Write-Host $out
    if ($out -notmatch "App conectada a PostgreSQL: 1") {
        Write-Host "ERROR: la app no confirmo la conexion (salida: $out)"; exit 1
    }
    Write-Host "   OK: la app se conecto a la base de datos por nombre de servicio."
    exit 0
}
finally {
    Cleanup
}
