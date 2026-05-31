#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 14: la imagen debe pesar menos de 90 MB y funcionar.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$limitMB = 90
$limit = $limitMB * 1024 * 1024

Write-Host ">> Construyendo imagen $Tag ..."
docker build -t $Tag $ExercisePath
if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

# 1) Funciona
$out = docker run --rm $Tag
if ($out -notmatch "Imagen ligera funcionando") {
    Write-Host "ERROR: la app no imprimio lo esperado. Salida: $out"; exit 1
}
Write-Host "   OK: la app funciona."

# 2) Tamano
$size = [int64](docker image inspect --format '{{.Size}}' $Tag)
$sizeMB = [math]::Round($size / 1MB, 1)
Write-Host "   Tamano de la imagen: $sizeMB MB (limite: $limitMB MB)"
if ($size -ge $limit) {
    Write-Host "ERROR: la imagen es demasiado grande. Usa la base Alpine."; exit 1
}
Write-Host "   OK: la imagen esta por debajo de $limitMB MB."
exit 0
