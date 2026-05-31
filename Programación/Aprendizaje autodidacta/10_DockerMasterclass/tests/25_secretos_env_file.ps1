#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 25: secretos por --env-file, nunca en la imagen.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$envFile = Join-Path ([System.IO.Path]::GetTempPath()) "mc-ej25-secretos.env"

function Cleanup { Remove-Item $envFile -ErrorAction SilentlyContinue }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # 1) El secreto NO debe estar incrustado en la imagen
    $envCfg = docker inspect --format '{{json .Config.Env}}' $Tag
    if ($envCfg -match "API_KEY") { Write-Host "ERROR: API_KEY esta grabada en la imagen (Config.Env). Quitala del Dockerfile."; exit 1 }
    Write-Host "   OK: la imagen no contiene el secreto."

    # 2) Sin secreto -> debe fallar
    docker run --rm $Tag 2>$null | Out-Null
    if ($LASTEXITCODE -eq 0) { Write-Host "ERROR: deberia fallar al no recibir API_KEY."; exit 1 }
    Write-Host "   OK: sin el secreto, la imagen falla como se espera."

    # 3) Con --env-file -> imprime el secreto enmascarado
    "API_KEY=supersecreto1234" | Out-File -Encoding ascii $envFile
    $out = docker run --rm --env-file $envFile $Tag
    Write-Host $out
    if ($out -notmatch "Secreto cargado: \*\*\*\*1234") { Write-Host "ERROR: no leyo el secreto del --env-file (salida: $out)"; exit 1 }
    Write-Host "   OK: el secreto llega en runtime y se muestra enmascarado."
    exit 0
}
finally {
    Cleanup
}
