param(
    [switch]$Execute,
    [int]$MaxMoves = 0,
    [switch]$FilesOnly
)

$ErrorActionPreference = 'Stop'
$root = '\\100.74.167.38\allshares\MEDIA_PLEX\& Descargas\ARR'
$protected = @(
    '#Seeder',
    'No se lo digas a nadie (2023) S01 1080p A3P WEB-DL H264 ESP-ENG AAC 2.0 SUBS',
    'Radarr',
    'Sonarr',
    'Readarr'
)
$categories = @('Series','Peliculas','Anime','Dibujos','Otros')

function Get-Category([string]$name) {
    if ($protected -contains $name) { return 'SKIP' }
    if ($categories -contains $name) { return 'SKIP' }

    if ($name -match '(?i)DAN DA DAN|Dragon Ball|Hunter[\. _xX]*Hunter|Spy[ _\.xX]*Family|\[Erai-raws\].*Spy|\[Kashikoi\].*Spy') { return 'Anime' }
    if ($name -match '(?i)Avatar\.The\.Last\.Airbender|LooneyThing|Greatest\.Cartoons|PatoAventuras|The\.Super\.Mario\.Bros\.Movie') { return 'Dibujos' }
    if ($name -match '(?i)(S\d{2}E\d{2}|\bS\d{2}\b|\[Cap\.\d+\])') { return 'Series' }
    if ($name -match '(?i)Curso de Proxmox|Brazzers') { return 'Otros' }

    return 'Peliculas'
}

$items = Get-ChildItem -LiteralPath $root -Force | Sort-Object Name
$topNames = @{}
foreach ($item in $items) { $topNames[$item.Name] = $true }

$categoryNames = @{}
foreach ($category in $categories) { $categoryNames[$category] = @{} }
if (-not $FilesOnly) {
    foreach ($category in $categories) {
        if ($topNames.ContainsKey($category)) {
            $folder = Join-Path $root $category
            foreach ($child in Get-ChildItem -LiteralPath $folder -Force) {
                $categoryNames[$category][$child.Name] = $true
            }
        }
    }
}

$plan = foreach ($item in $items) {
    $category = Get-Category $item.Name
    if ($category -eq 'SKIP') {
        [pscustomobject]@{ Action='SKIP'; Category=''; Name=$item.Name; Source=$item.FullName; Destination=''; Exists=$false; IsDirectory=$item.PSIsContainer }
        continue
    }
    $exists = $categoryNames[$category].ContainsKey($item.Name)
    $destination = Join-Path (Join-Path $root $category) $item.Name
    [pscustomobject]@{ Action='MOVE'; Category=$category; Name=$item.Name; Source=$item.FullName; Destination=$destination; Exists=$exists; IsDirectory=$item.PSIsContainer }
}

$summary = $plan | Group-Object Action,Category | Sort-Object Name | ForEach-Object { '{0}: {1}' -f $_.Name,$_.Count }
$summary

$collisions = @($plan | Where-Object { $_.Action -eq 'MOVE' -and $_.Exists })
if ($collisions.Count -gt 0) {
    'COLLISIONS_SKIPPED:'
    $collisions | ForEach-Object { $_.Category + ' -> ' + $_.Name }
}

$log = Join-Path $PSScriptRoot ('arr_organize_' + (Get-Date -Format 'yyyyMMdd_HHmmss') + '.csv')
$plan | Export-Csv -LiteralPath $log -NoTypeInformation -Encoding UTF8
'PLAN_LOG=' + $log

if (-not $Execute) {
    'DRY_RUN_ONLY'
    exit 0
}

foreach ($category in $categories) {
    $folder = Join-Path $root $category
    if (-not (Test-Path -LiteralPath $folder)) {
        New-Item -ItemType Directory -Path $folder | Out-Null
    }
}

$moves = @($plan | Where-Object { $_.Action -eq 'MOVE' -and -not $_.Exists })
if ($FilesOnly) { $moves = @($moves | Where-Object { -not $_.IsDirectory }) }
if ($MaxMoves -gt 0) { $moves = @($moves | Select-Object -First $MaxMoves) }

$total = $moves.Count
$index = 0
$failed = @()
foreach ($entry in $moves) {
    $index++
    Write-Output ("MOVE {0}/{1}: {2} -> {3}" -f $index,$total,$entry.Name,$entry.Category)
    try {
        Move-Item -LiteralPath $entry.Source -Destination (Join-Path $root $entry.Category) -ErrorAction Stop
    }
    catch {
        $failed += [pscustomobject]@{ Name=$entry.Name; Category=$entry.Category; Error=$_.Exception.Message }
        Write-Output ("SKIPPED_LOCKED_OR_ERROR: {0}" -f $entry.Name)
    }
}
if ($failed.Count -gt 0) {
    $failLog = Join-Path $PSScriptRoot ('arr_organize_failed_' + (Get-Date -Format 'yyyyMMdd_HHmmss') + '.csv')
    $failed | Export-Csv -LiteralPath $failLog -NoTypeInformation -Encoding UTF8
    'FAILED_LOG=' + $failLog
}
'DONE'
'PROTECTED_LEFT_UNTOUCHED=#Seeder'
'PROTECTED_LEFT_UNTOUCHED=No se lo digas a nadie (2023) S01 1080p A3P WEB-DL H264 ESP-ENG AAC 2.0 SUBS'
'ARR_FOLDERS_LEFT_UNTOUCHED=Radarr,Sonarr,Readarr'

