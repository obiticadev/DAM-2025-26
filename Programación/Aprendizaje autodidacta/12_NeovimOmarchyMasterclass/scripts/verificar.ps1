# ============================================================
# verificar.ps1 — Comprueba un ejercicio del bootcamp (Windows PowerShell)
#
# Uso:
#   pwsh scripts/verificar.ps1 <nivel> <ej>
#
# Ejemplos:
#   pwsh scripts/verificar.ps1 01 03     # nivel01, ejercicio 03
#   pwsh scripts/verificar.ps1 14        # nivel14, ejercicio Boss Final (mvn test)
# ============================================================

param(
    [Parameter(Mandatory = $true, Position = 0)] [string] $Nivel,
    [Parameter(Mandatory = $false, Position = 1)] [string] $Ej
)

$ErrorActionPreference = "Stop"

# Padding a 2 dígitos
$nivel = $Nivel.PadLeft(2, '0')

$root = Split-Path -Parent $PSScriptRoot

# --- Caso especial: Boss Final ---
if ($nivel -eq "14") {
    $bossDir = Join-Path $root "ejercicios\nivel14_boss_final"
    if (-not (Test-Path $bossDir)) {
        Write-Host "❌ No existe $bossDir" -ForegroundColor Red
        exit 1
    }
    Write-Host "▶ Lanzando 'mvn clean test' en el Boss Final…" -ForegroundColor Yellow
    Push-Location $bossDir
    try {
        mvn clean test
        $rc = $LASTEXITCODE
    } finally { Pop-Location }
    if ($rc -eq 0) {
        Write-Host ""
        Write-Host "✅ Boss Final: todos los tests en verde — ¡bootcamp completado!" -ForegroundColor Green
    } else {
        Write-Host ""
        Write-Host "❌ Boss Final: hay tests rojos. Revisa el output de Maven arriba." -ForegroundColor Red
    }
    exit $rc
}

# --- Caso normal: diff de ejercicio vs solución ---
if (-not $Ej) {
    Write-Host "Uso: pwsh scripts/verificar.ps1 <nivel> <ej>" -ForegroundColor Yellow
    exit 1
}
$ej = $Ej.PadLeft(2, '0')

$nivelDir = Get-ChildItem -Path (Join-Path $root "ejercicios") -Directory -Filter "nivel${nivel}_*" | Select-Object -First 1
$solDir   = Get-ChildItem -Path (Join-Path $root "solucion")   -Directory -Filter "nivel${nivel}_*" | Select-Object -First 1

if (-not $nivelDir -or -not $solDir) {
    Write-Host "❌ No encuentro carpetas nivel${nivel}_* en ejercicios\ o solucion\" -ForegroundColor Red
    exit 1
}

$src = Get-ChildItem -Path $nivelDir.FullName -Filter "ej${ej}_*.*" | Select-Object -First 1
$sol = Get-ChildItem -Path $solDir.FullName   -Filter "ej${ej}_*.*" | Select-Object -First 1

if (-not $src -or -not $sol) {
    Write-Host "❌ No encuentro ej${ej}_*.* en $($nivelDir.FullName) o su solución" -ForegroundColor Red
    exit 1
}

Write-Host "Comparando:" -ForegroundColor DarkGray
Write-Host "  tu trabajo: $($src.FullName)" -ForegroundColor DarkGray
Write-Host "  solución:   $($sol.FullName)" -ForegroundColor DarkGray
Write-Host ""

$srcContent = Get-Content $src.FullName -Raw -ErrorAction SilentlyContinue
$solContent = Get-Content $sol.FullName -Raw -ErrorAction SilentlyContinue

if ($srcContent -eq $solContent) {
    Write-Host "✅ Ejercicio ${nivel}.${ej} CORRECTO" -ForegroundColor Green
    exit 0
} else {
    Write-Host "❌ Ejercicio ${nivel}.${ej} INCORRECTO — diferencias:" -ForegroundColor Red
    Write-Host ""
    $diff = Compare-Object `
        (Get-Content $sol.FullName) `
        (Get-Content $src.FullName) `
        -IncludeEqual:$false
    foreach ($line in $diff) {
        if ($line.SideIndicator -eq '<=') {
            Write-Host "- $($line.InputObject)" -ForegroundColor Red
        } else {
            Write-Host "+ $($line.InputObject)" -ForegroundColor Green
        }
    }
    Write-Host ""
    Write-Host "💡 Pista: Las líneas con '-' son lo esperado, las '+' lo que tienes." -ForegroundColor Yellow
    Write-Host "   Si te has perdido, restaura con: bash scripts/reset_ej.sh ${nivel} ${ej}" -ForegroundColor DarkGray
    exit 1
}
