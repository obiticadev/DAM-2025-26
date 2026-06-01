#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 36: build multi-arch con buildx + manifest list.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$reg     = "mc-ej36-registry"
$builder = "mc-ej36-builder"
$ref     = "localhost:5006/masterclass/ej36:multi"

function Cleanup {
    docker buildx use default 2>$null | Out-Null
    docker buildx rm $builder 2>$null | Out-Null
    docker rm -f $reg 2>$null | Out-Null
}

try {
    Cleanup
    Write-Host ">> Levantando registry:2 en localhost:5006 ..."
    docker run -d -p 5006:5000 --name $reg registry:2 | Out-Null
    Start-Sleep -Seconds 2

    Write-Host ">> Creando builder buildx (network=host) ..."
    docker buildx create --name $builder --driver-opt network=host --use --bootstrap | Out-Null
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: no se pudo crear el builder buildx."; exit 1 }

    Write-Host ">> build multi-arch (amd64 + arm64) y push ..."
    docker buildx build --platform linux/amd64,linux/arm64 -t $ref --push $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el build/push multi-arch."; exit 1 }

    Write-Host ">> Inspeccionando la manifest list ..."
    $out = docker buildx imagetools inspect $ref | Out-String
    Write-Host $out
    if ($out -notmatch "linux/amd64") { Write-Host "ERROR: falta la plataforma linux/amd64 en la manifest list."; exit 1 }
    if ($out -notmatch "linux/arm64") { Write-Host "ERROR: falta la plataforma linux/arm64 en la manifest list."; exit 1 }
    Write-Host "   OK: la imagen publica amd64 y arm64 bajo el mismo tag."
    exit 0
}
finally {
    Cleanup
}
