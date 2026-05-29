#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 13: persistencia en un volumen nombrado.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$vol = "mc-ej13-data"

function Cleanup { docker volume rm -f $vol 2>$null | Out-Null }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # 1) VOLUME /data declarado
    $vols = docker inspect --format '{{json .Config.Volumes}}' $Tag
    if ($vols -notmatch '/data') { Write-Host "ERROR: la imagen no declara VOLUME /data (encontrado: $vols)"; exit 1 }
    Write-Host "   OK: VOLUME /data declarado."

    # 2) Persistencia en 3 arranques sobre el mismo volumen
    Cleanup
    docker volume create $vol | Out-Null
    for ($n = 1; $n -le 3; $n++) {
        $out = docker run --rm -v "${vol}:/data" $Tag
        if ($out -notmatch "Arranques registrados: $n") {
            Write-Host "ERROR: en el arranque $n se esperaba 'Arranques registrados: $n' y se obtuvo: $out"
            exit 1
        }
        Write-Host "   OK arranque ${n}: $out"
    }
    Write-Host "   OK: el contador persiste entre contenedores (volumen nombrado)."
    exit 0
}
finally {
    Cleanup
}
