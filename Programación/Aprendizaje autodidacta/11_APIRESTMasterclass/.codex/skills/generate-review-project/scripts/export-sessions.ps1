param(
    [string]$SheetUrl = "https://docs.google.com/spreadsheets/d/1FI0UpnT2YQAx89Cyi5KQXy1CTfnULCoacSbO5uYSXO8/edit?usp=sharing",
    [string]$OutputDir = "proyectos_repaso/_skill_cache",
    [string]$SheetName = "Sesiones"
)

$ErrorActionPreference = "Stop"

New-Item -ItemType Directory -Force -Path $OutputDir | Out-Null

if ($SheetUrl -notmatch "/spreadsheets/d/([^/]+)") {
    throw "Could not extract spreadsheet id from URL: $SheetUrl"
}

$spreadsheetId = $Matches[1]
$xlsxPath = Join-Path $OutputDir "plan_verano_2dam_2026.xlsx"
$exportUrl = "https://docs.google.com/spreadsheets/d/$spreadsheetId/export?format=xlsx"

Invoke-WebRequest -Uri $exportUrl -OutFile $xlsxPath -TimeoutSec 60

Add-Type -AssemblyName System.IO.Compression.FileSystem
$zip = [System.IO.Compression.ZipFile]::OpenRead((Resolve-Path $xlsxPath))

try {
    function Read-ZipText([string]$entryName) {
        $entry = $zip.GetEntry($entryName)
        if (-not $entry) { return $null }
        $reader = [System.IO.StreamReader]::new($entry.Open())
        try { return $reader.ReadToEnd() } finally { $reader.Close() }
    }

    function Select-Local($node, [string]$name) {
        return $node.SelectNodes("*[local-name()='$name']")
    }

    $workbookText = Read-ZipText "xl/workbook.xml"
    if (-not $workbookText) { throw "Workbook metadata not found in XLSX." }
    [xml]$workbookXml = $workbookText

    $sheetNodes = $workbookXml.SelectNodes("//*[local-name()='sheets']/*[local-name()='sheet']")
    $sheets = @()
    for ($i = 0; $i -lt $sheetNodes.Count; $i++) {
        $node = $sheetNodes[$i]
        $sheets += [pscustomobject]@{
            index = $i + 1
            name = $node.GetAttribute("name")
            sheetId = $node.GetAttribute("sheetId")
        }
    }

    $target = $sheets | Where-Object { $_.name -eq $SheetName } | Select-Object -First 1
    if (-not $target) {
        $available = ($sheets | ForEach-Object { $_.name }) -join ", "
        throw "Sheet '$SheetName' not found. Available sheets: $available"
    }

    $sharedStrings = @()
    $sharedText = Read-ZipText "xl/sharedStrings.xml"
    if ($sharedText) {
        [xml]$sharedXml = $sharedText
        foreach ($si in $sharedXml.SelectNodes("//*[local-name()='sst']/*[local-name()='si']")) {
            $parts = @()
            foreach ($textNode in $si.SelectNodes(".//*[local-name()='t']")) {
                $parts += $textNode.InnerText
            }
            $sharedStrings += ($parts -join "")
        }
    }

    $sheetText = Read-ZipText ("xl/worksheets/sheet{0}.xml" -f $target.index)
    if (-not $sheetText) { throw "Worksheet XML for '$SheetName' was not found." }
    [xml]$sheetXml = $sheetText

    $rows = @()
    foreach ($row in $sheetXml.SelectNodes("//*[local-name()='sheetData']/*[local-name()='row']")) {
        $cells = [ordered]@{}
        foreach ($cell in (Select-Local $row "c")) {
            $ref = $cell.GetAttribute("r")
            $type = $cell.GetAttribute("t")
            $valueNode = Select-Local $cell "v" | Select-Object -First 1
            $inlineNode = Select-Local $cell "is" | Select-Object -First 1
            $value = $null

            if ($type -eq "s" -and $valueNode) {
                $idx = [int]$valueNode.InnerText
                if ($idx -ge 0 -and $idx -lt $sharedStrings.Count) {
                    $value = $sharedStrings[$idx]
                }
            } elseif ($type -eq "inlineStr" -and $inlineNode) {
                $parts = @()
                foreach ($textNode in $inlineNode.SelectNodes(".//*[local-name()='t']")) {
                    $parts += $textNode.InnerText
                }
                $value = $parts -join ""
            } elseif ($valueNode) {
                $value = $valueNode.InnerText
            }

            if ($null -ne $value -and $value -ne "") {
                $cells[$ref] = $value
            }
        }

        if ($cells.Count -gt 0) {
            $rows += [pscustomobject]@{
                row = [int]$row.GetAttribute("r")
                cells = $cells
            }
        }
    }

    $sheetJson = Join-Path $OutputDir "workbook-sheets.json"
    $sessionsJson = Join-Path $OutputDir "sessions-cells.json"

    $sheets | ConvertTo-Json -Depth 6 | Set-Content -Encoding UTF8 $sheetJson
    [pscustomobject]@{
        sourceUrl = $SheetUrl
        sheetName = $SheetName
        exportedAt = (Get-Date).ToString("s")
        xlsxPath = (Resolve-Path $xlsxPath).Path
        rows = $rows
    } | ConvertTo-Json -Depth 20 | Set-Content -Encoding UTF8 $sessionsJson

    Write-Output "Wrote $sessionsJson"
    Write-Output "Wrote $sheetJson"
} finally {
    $zip.Dispose()
}
