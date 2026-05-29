#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 29: web + db en Compose, la web consulta la base.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$proj = "mc-ej29"
$file = Join-Path $ExercisePath "compose.yaml"
$url  = "http://localhost:8629"

function Cleanup { docker compose -p $proj -f $file down -v 2>$null | Out-Null }

try {
    if (-not (Test-Path $file)) { Write-Host "ERROR: falta compose.yaml en $ExercisePath"; exit 1 }
    Cleanup
    Write-Host ">> docker compose up -d --build ..."
    docker compose -p $proj -f $file up -d --build
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: 'docker compose up' fallo. Revisa tu compose.yaml."; exit 1 }

    $ok = $false
    for ($i = 1; $i -le 30; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing -Uri $url -TimeoutSec 3
            if ($r.Content -match "DB OK: 1") { $ok = $true; break }
        } catch { Start-Sleep -Seconds 1 }
    }
    if (-not $ok) { Write-Host "ERROR: $url no devolvio 'DB OK: 1' (la web no hablo con la db)."; exit 1 }
    Write-Host "   OK: la web consulto la base de datos por su nombre de servicio."
    exit 0
}
finally {
    Cleanup
}
