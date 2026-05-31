#!/usr/bin/env pwsh
# Test RUNTIME del ejercicio 24: persistencia de datos en volumen nombrado.
param(
    [string]$ExercisePath,
    [string]$Tag,
    [string]$Root
)
$ErrorActionPreference = "Stop"
$vol = "mc-ej24-data"
$c   = "mc-ej24-pg"

function Cleanup {
    docker rm -f $c 2>$null | Out-Null
    docker volume rm $vol 2>$null | Out-Null
}

function Wait-Ready {
    for ($i = 1; $i -le 30; $i++) {
        docker exec $c pg_isready -U postgres 2>$null | Out-Null
        if ($LASTEXITCODE -eq 0) { return $true }
        Start-Sleep -Seconds 1
    }
    return $false
}

function Count-Notas {
    return (docker exec $c psql -U postgres -tAc "SELECT count(*) FROM notas;").Trim()
}

try {
    Write-Host ">> Construyendo imagen $Tag ..."
    docker build -t $Tag $ExercisePath
    if ($LASTEXITCODE -ne 0) { Write-Host "ERROR: fallo el docker build."; exit 1 }

    Cleanup
    docker volume create $vol | Out-Null

    # --- Primer arranque: el seed siembra 1 fila -------------------------
    Write-Host ">> Arranque 1: volumen vacio, se ejecuta el seed ..."
    docker run -d --name $c --mount source=$vol,target=/var/lib/postgresql/data `
        -e POSTGRES_PASSWORD=secreto $Tag | Out-Null
    if (-not (Wait-Ready)) { Write-Host "ERROR: PostgreSQL no quedo listo a tiempo."; exit 1 }

    $n1 = Count-Notas
    if ($n1 -ne "1") { Write-Host "ERROR: tras el seed esperaba 1 fila y hay '$n1'."; exit 1 }
    Write-Host "   OK: el seed creo la tabla 'notas' con 1 fila."

    Write-Host ">> Insertando una 2a fila y destruyendo el contenedor ..."
    docker exec $c psql -U postgres -c "INSERT INTO notas (texto) VALUES ('fila persistente');" | Out-Null
    docker rm -f $c | Out-Null

    # --- Segundo arranque: MISMO volumen --------------------------------
    Write-Host ">> Arranque 2: contenedor NUEVO sobre el mismo volumen ..."
    docker run -d --name $c --mount source=$vol,target=/var/lib/postgresql/data `
        -e POSTGRES_PASSWORD=secreto $Tag | Out-Null
    if (-not (Wait-Ready)) { Write-Host "ERROR: PostgreSQL no quedo listo a tiempo (2)."; exit 1 }

    $n2 = Count-Notas
    if ($n2 -ne "2") { Write-Host "ERROR: esperaba 2 filas persistidas y hay '$n2'."; exit 1 }
    Write-Host "   OK: los datos persistieron en el volumen (2 filas)."
    exit 0
}
finally {
    Cleanup
}
