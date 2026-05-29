#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 26: captura de logs y log driver json-file.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$c = "mc-ej26"

function Cleanup { docker rm -f $c 2>$null | Out-Null }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    Write-Host ">> Ejecutando con --log-driver json-file ..."
    docker run --name $c --log-driver json-file --log-opt max-size=1m $Tag | Out-Null

    $logs = docker logs $c 2>&1 | Out-String
    $lineas = ([regex]::Matches($logs, "log linea \d")).Count
    if ($lineas -lt 5) { Write-Host "ERROR: esperaba 5 lineas 'log linea N' en docker logs y hay $lineas."; Write-Host $logs; exit 1 }
    Write-Host "   OK: docker logs capturo las 5 lineas de stdout."

    $driver = (docker inspect --format '{{.HostConfig.LogConfig.Type}}' $c).Trim()
    if ($driver -ne "json-file") { Write-Host "ERROR: el log driver es '$driver', esperaba 'json-file'."; exit 1 }
    Write-Host "   OK: el contenedor usa el log driver json-file."
    exit 0
}
finally {
    Cleanup
}
