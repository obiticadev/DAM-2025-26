<#
.SYNOPSIS
    Activa solo los bloques (modulos Maven) en los que estas trabajando,
    para que el language server de Java en VS Code no cargue los 26 modulos.

.DESCRIPTION
    Reescribe la seccion <modules> del pom.xml raiz dejando solo los bloques
    indicados. El resto de carpetas no se toca: solo dejan de importarse.

    Despues de ejecutarlo, en VS Code abre la paleta (Ctrl+Shift+P) y lanza
    "Java: Reload Projects" (o "Java: Clean Java Language Server Workspace"
    si fue un cambio grande).

.EXAMPLE
    .\bloque.ps1 b00          # trabajar solo en b00_http
    .\bloque.ps1 b04 b05      # dos bloques a la vez
    .\bloque.ps1 todos        # restaurar los 26 modulos (build completo / commit)
    .\bloque.ps1              # ver que bloques estan activos ahora
#>
param([string[]]$Bloques)

$ErrorActionPreference = "Stop"
$pomPath = Join-Path $PSScriptRoot "pom.xml"
$pom = Get-Content $pomPath -Raw -Encoding UTF8

# Todos los modulos disponibles = carpetas bNN_* con pom.xml propio
$todos = Get-ChildItem $PSScriptRoot -Directory |
    Where-Object { $_.Name -match '^b\d{2}_' -and (Test-Path (Join-Path $_.FullName "pom.xml")) } |
    Sort-Object Name | Select-Object -ExpandProperty Name

if (-not $Bloques) {
    $activos = [regex]::Matches($pom, '<module>([^<]+)</module>') | ForEach-Object { $_.Groups[1].Value }
    Write-Host "Bloques activos ($($activos.Count)/$($todos.Count)):" -ForegroundColor Cyan
    $activos | ForEach-Object { Write-Host "  - $_" }
    Write-Host "`nUso: .\bloque.ps1 b00 [b05 ...]   |   .\bloque.ps1 todos"
    exit 0
}

if ($Bloques -contains "todos") {
    $seleccion = $todos
} else {
    $seleccion = foreach ($b in $Bloques) {
        $match = $todos | Where-Object { $_ -eq $b -or $_ -like "$b*" }
        if (-not $match) {
            Write-Host "ERROR: no existe ningun bloque que empiece por '$b'." -ForegroundColor Red
            Write-Host "Disponibles: $($todos -join ', ')"
            exit 1
        }
        $match
    }
    $seleccion = $seleccion | Select-Object -Unique
}

$indent = "        "
$nuevaSeccion = "<modules>`r`n" + (($seleccion | ForEach-Object { "$indent<module>$_</module>" }) -join "`r`n") + "`r`n    </modules>"
$pom = [regex]::Replace($pom, '(?s)<modules>.*?</modules>', $nuevaSeccion)
[System.IO.File]::WriteAllText($pomPath, $pom, (New-Object System.Text.UTF8Encoding $false))

Write-Host "pom.xml actualizado. Bloques activos ($($seleccion.Count)/$($todos.Count)):" -ForegroundColor Green
$seleccion | ForEach-Object { Write-Host "  - $_" }
Write-Host "`nAhora en VS Code: Ctrl+Shift+P -> 'Java: Reload Projects'" -ForegroundColor Yellow
if ($Bloques -notcontains "todos") {
    Write-Host "Antes de hacer commit o 'mvn test' global: .\bloque.ps1 todos" -ForegroundColor Yellow
}
