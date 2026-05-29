#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 20: version OCI + re-etiquetado conservando ID.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$base = "masterclass/ej20"
$extra = @("${base}:1.4.2", "${base}:1.4", "${base}:1")

function Cleanup { foreach ($t in $extra) { docker rmi $t 2>$null | Out-Null } }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # 1) Label version
    $ver = docker inspect --format '{{index .Config.Labels "org.opencontainers.image.version"}}' $Tag
    if ($ver -ne "1.4.2") { Write-Host "ERROR: la etiqueta de version no es 1.4.2 (encontrado: '$ver')"; exit 1 }
    Write-Host "   OK: org.opencontainers.image.version = 1.4.2"

    # 2) Funciona
    $out = docker run --rm $Tag
    if ($out -notmatch "1\.4\.2") { Write-Host "ERROR: el contenedor no imprimio 1.4.2 (salida: $out)"; exit 1 }
    Write-Host "   OK: la imagen imprime su version."

    # 3) Re-etiquetar conserva el mismo ID
    $id = (docker inspect --format '{{.Id}}' $Tag)
    foreach ($t in $extra) {
        docker tag $Tag $t | Out-Null
        $tid = (docker inspect --format '{{.Id}}' $t)
        if ($tid -ne $id) { Write-Host "ERROR: el tag $t tiene distinto ID."; exit 1 }
    }
    Write-Host "   OK: 1.4.2 / 1.4 / 1 comparten el mismo Image ID."
    exit 0
}
finally {
    Cleanup
}
