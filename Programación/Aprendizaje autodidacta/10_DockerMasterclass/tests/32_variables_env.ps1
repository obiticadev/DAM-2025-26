#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 32: interpolacion de variables desde .env.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej32"
$file = Join-Path $ExercisePath "compose.yaml"
$url  = "http://localhost:8632"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d ..."
    docker compose -p $proj -f $file up -d
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml/.env."; exit 1 }

    # 1) Puerto interpolado (8632) responde
    $ok = $false
    for ($i = 1; $i -le 20; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
            if ($r.StatusCode -eq 200) { $ok = $true; break }
        } catch { Start-Sleep -Seconds 1 }
    }
    if (-not $ok) { Write-Host "ERROR: $url no responde (revisa la interpolacion de WEB_PORT)."; exit 1 }
    Write-Host "   OK: el puerto 8632 (desde .env) responde."

    # 2) Variable de entorno interpolada en el contenedor
    $webId = (docker compose -p $proj -f $file ps -q web).Trim()
    $envCfg = docker inspect --format '{{json .Config.Env}}' $webId
    if ($envCfg -notmatch "MENSAJE=hola desde dotenv") { Write-Host "ERROR: el contenedor no tiene MENSAJE=hola desde dotenv (env: $envCfg)."; exit 1 }
    Write-Host "   OK: la variable MENSAJE se interpolo desde el .env."
    exit 0
}
finally {
    Cleanup
}
