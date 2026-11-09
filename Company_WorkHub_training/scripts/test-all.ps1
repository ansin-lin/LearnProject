$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot

Push-Location (Join-Path $projectRoot 'backend')
try {
    .\mvnw.cmd test
} finally {
    Pop-Location
}

Push-Location (Join-Path $projectRoot 'frontend')
try {
    npm run test
} finally {
    Pop-Location
}
