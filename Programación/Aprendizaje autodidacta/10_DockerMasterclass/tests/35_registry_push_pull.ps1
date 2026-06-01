#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 35: push/pull contra un registry:2 local.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$reg   = "mc-ej35-registry"
$ref   = "localhost:5005/masterclass/ej35:1.0"

function Cleanup {
    docker rm -f $reg 2>$null | Out-Null
    docker rmi $ref 2>$null | Out-Null
}

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    Write-Host ">> Levantando registry:2 en localhost:5005 ..."
    docker run -d -p 5005:5000 --name $reg registry:2 | Out-Null
    Start-Sleep -Seconds 2

    Write-Host ">> tag + push ..."
    docker tag $Tag $ref
    docker push $ref
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el push al registry."; exit 1 }

    Write-Host ">> Borrando copias locales y haciendo pull ..."
    docker rmi $ref 2>$null | Out-Null
    docker pull $ref
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el pull desde el registry."; exit 1 }

    $out = docker run --rm $ref
    Write-Host $out
    if ($out -notmatch "imagen desde el registry local") { Write-Host "ERROR: la imagen descargada no imprimio lo esperado (salida: $out)"; exit 1 }
    Write-Host "   OK: push y pull correctos; la imagen descargada funciona."
    exit 0
}
finally {
    Cleanup
}
