#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 06: levanta nginx, publica el puerto y hace curl.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$cname = "mc-ej06"
$port  = 8606

function Cleanup { docker rm -f $cname 2>$null | Out-Null }

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    # 1) EXPOSE 80 declarado
    $exposed = docker inspect --format '{{json .Config.ExposedPorts}}' $Tag
    if ($exposed -notmatch '80/tcp') {
        Write-Host "ERROR: la imagen no declara EXPOSE 80 (encontrado: $exposed)"; exit 1
    }
    Write-Host "   OK: EXPOSE 80 declarado."

    # 2) LABEL ejercicio
    $label = docker inspect --format '{{index .Config.Labels "ejercicio"}}' $Tag
    if ($label -ne "06-expose-puertos") {
        Write-Host "ERROR: falta el LABEL ejercicio=06-expose-puertos (encontrado: '$label')"; exit 1
    }
    Write-Host "   OK: LABEL ejercicio correcto."

    # 3) Publica el puerto y comprueba la respuesta HTTP
    Cleanup
    docker run -d --name $cname -p "${port}:80" $Tag | Out-Null
    $ok = $false
    for ($i = 0; $i -lt 20; $i++) {
        try {
            $r = Invoke-WebRequest -UseBasicParsing "http://localhost:$port" -TimeoutSec 2
            if ($r.StatusCode -eq 200 -and $r.Content -match "Masterclass Docker") { $ok = $true; break }
        } catch { }
        Start-Sleep -Milliseconds 500
    }
    if (-not $ok) { Write-Host "ERROR: no se sirvio index.html en http://localhost:$port"; exit 1 }
    Write-Host "   OK: nginx responde 200 con el contenido esperado en el puerto $port."

    exit 0
}
finally {
    Cleanup
}
