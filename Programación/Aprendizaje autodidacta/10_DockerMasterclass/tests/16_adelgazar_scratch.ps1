#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 16: imagen sobre scratch < 5 MB y funcional.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$limitMB = 5
$limit = $limitMB * 1024 * 1024

Write-Host ">> Construyendo imagen $Tag ..."
docker build -t $Tag $ExercisePath
if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

$out = docker run --rm $Tag
if ($out -notmatch "Binario corriendo sobre scratch") {
    Write-Host "ERROR: la app no imprimio lo esperado. Salida: $out"; exit 1
}
Write-Host "   OK: el binario funciona sobre scratch."

$size = [int64](docker image inspect --format '{{.Size}}' $Tag)
$sizeMB = [math]::Round($size / 1MB, 2)
Write-Host "   Tamano de la imagen: $sizeMB MB (limite: $limitMB MB)"
if ($size -ge $limit) {
    Write-Host "ERROR: la imagen supera $limitMB MB. ¿Compilaste estatico y usaste scratch?"; exit 1
}
Write-Host "   OK: imagen minima por debajo de $limitMB MB."
exit 0
