#!/usr/bin/env pwsh
# ============================================================================
#  RUNNER UNIVERSAL DE VALIDACION - Docker Masterclass (Windows / PowerShell)
#  Uso:  .\validar.ps1 <NN>     ej:  .\validar.ps1 01
# ============================================================================
param(
    [Parameter(Mandatory = $true, Position = 0)]
    [string]$Ejercicio
)

$ErrorActionPreference = "Stop"
$root = $PSScriptRoot
Set-Location $root

# --- Normaliza el numero a dos digitos (1 -> 01) ---------------------------
$nn = $Ejercicio.PadLeft(2, '0')

function Write-Header($txt) {
    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Cyan
    Write-Host "  $txt" -ForegroundColor Cyan
    Write-Host "============================================================" -ForegroundColor Cyan
}
function Fail($msg) {
    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Red
    Write-Host "  X  EJERCICIO $nn NO SUPERADO" -ForegroundColor Red
    Write-Host "  $msg" -ForegroundColor Red
    Write-Host "============================================================" -ForegroundColor Red
    exit 1
}
function Pass() {
    Write-Host ""
    Write-Host "============================================================" -ForegroundColor Green
    Write-Host "  OK  EJERCICIO $nn SUPERADO" -ForegroundColor Green
    Write-Host "============================================================" -ForegroundColor Green
    exit 0
}

# --- Localiza la carpeta del ejercicio (ahora anidada por bloque) ----------
# Los ejercicios viven en  ejercicios/<bloque>/NN_nombre/ . Buscamos en
# profundidad y filtramos por ENUNCIADO.md para no confundir el ejercicio con
# la carpeta de bloque (que puede compartir prefijo numerico).
$dir = Get-ChildItem -Path (Join-Path $root "ejercicios") -Directory -Recurse -Filter "$nn`_*" |
    Where-Object { Test-Path (Join-Path $_.FullName "ENUNCIADO.md") } |
    Select-Object -First 1
if (-not $dir) { Fail "No existe ningun ejercicio ejercicios/**/$nn`_*" }
$exPath = $dir.FullName
$tag = "masterclass/ej$nn`:latest"

Write-Header "VALIDANDO EJERCICIO $nn  ($($dir.Name))"

# --- 1) Ruta RUNTIME: si hay script .ps1 en tests/, el manda --------------
$runtime = Get-ChildItem -Path (Join-Path $root "tests") -Filter "$nn`_*.ps1" -ErrorAction SilentlyContinue | Select-Object -First 1
if ($runtime) {
    Write-Host ">> Test de tipo RUNTIME (red/volumen/compose/k8s): $($runtime.Name)" -ForegroundColor Yellow
    & $runtime.FullName -ExercisePath $exPath -Tag $tag -Root $root
    if ($LASTEXITCODE -eq 0) { Pass } else { Fail "El script de validacion runtime devolvio errores." }
}

# --- 2) Ruta CST: build -> hadolint -> container-structure-test ------------
$yaml = Get-ChildItem -Path (Join-Path $root "tests") -Filter "$nn`_*.yaml" -ErrorAction SilentlyContinue | Select-Object -First 1
if (-not $yaml) { Fail "No hay test (.yaml ni .ps1) para el ejercicio $nn en tests/." }

# 2a) Build (permite build.ps1 personalizado por ejercicio)
$customBuild = Join-Path $exPath "build.ps1"
Write-Host ">> Construyendo imagen $tag ..." -ForegroundColor Yellow
if (Test-Path $customBuild) {
    & $customBuild -Tag $tag -ExercisePath $exPath
    if ($LASTEXITCODE -ne 0) { Fail "Fallo el build personalizado (build.ps1)." }
} else {
    docker build -t $tag $exPath
    if ($LASTEXITCODE -ne 0) { Fail "Fallo 'docker build'. Revisa la sintaxis de tu Dockerfile." }
}

# 2b) hadolint (gate estricto a partir del ejercicio 07)
$dockerfile = Join-Path $exPath "Dockerfile"
if (Test-Path $dockerfile) {
    Write-Host ">> Linter hadolint ..." -ForegroundColor Yellow
    # DL3008/DL3018 (pin de versiones de apt/apk) se relajan: fijar versiones
    # exactas eternamente es impracticable en un repo didactico. El resto de
    # reglas SI son puerta de calidad estricta a partir del ejercicio 07.
    Get-Content $dockerfile | docker run --rm -i hadolint/hadolint --ignore DL3008 --ignore DL3018
    $hado = $LASTEXITCODE
    if ($hado -ne 0) {
        if ([int]$nn -ge 7) {
            Fail "hadolint encontro violaciones de buenas practicas (obligatorio limpiarlas desde el ejercicio 07)."
        } else {
            Write-Host "   (aviso) hadolint sugiere mejoras, pero en este bloque aun no es obligatorio." -ForegroundColor DarkYellow
        }
    } else {
        Write-Host "   hadolint: limpio." -ForegroundColor Green
    }
}

# 2c) container-structure-test
Write-Host ">> container-structure-test ($($yaml.Name)) ..." -ForegroundColor Yellow
docker run --rm `
    -v //var/run/docker.sock:/var/run/docker.sock `
    -v "${root}:/work" -w /work `
    gcr.io/gcp-runtimes/container-structure-test:latest `
    test --image $tag --config "tests/$($yaml.Name)"
if ($LASTEXITCODE -ne 0) { Fail "container-structure-test detecto que la imagen no cumple la especificacion." }

Pass
