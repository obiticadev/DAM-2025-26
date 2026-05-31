#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 21: imagen endurecida (no-root) + escaneo CVEs best-effort.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"

Write-Host ">> Construyendo imagen $Tag ..."
docker build -t $Tag $ExercisePath
if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

# 1) Imprime "imagen endurecida"
$out = docker run --rm $Tag
if ($out -notmatch "imagen endurecida") { Write-Host "ERROR: no imprimio 'imagen endurecida' (salida: $out)"; exit 1 }
Write-Host "   OK: la imagen imprime 'imagen endurecida'."

# 2) No corre como root
$who = (docker run --rm $Tag whoami).Trim()
if ($who -eq "root" -or [string]::IsNullOrWhiteSpace($who)) { Write-Host "ERROR: el contenedor corre como root (whoami: '$who')"; exit 1 }
Write-Host "   OK: corre como usuario sin privilegios ('$who')."

# 3) Escaneo de CVEs (mejor esfuerzo): informativo, no bloquea
docker scout version 2>$null | Out-Null
if ($LASTEXITCODE -eq 0) {
    Write-Host ">> docker scout disponible: analizando CVEs (informativo) ..."
    docker scout cves $Tag 2>$null
} else {
    Write-Host "   AVISO: 'docker scout' no esta instalado; se omite el escaneo de CVEs."
}

exit 0
