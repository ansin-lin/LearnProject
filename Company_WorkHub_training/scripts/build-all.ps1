$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot

Push-Location (Join-Path $projectRoot 'backend')
try {
    .\mvnw.cmd -Pquality verify
} finally {
    Pop-Location
}

Push-Location (Join-Path $projectRoot 'frontend')
try {
    npm ci
    npm run lint
    npm run format:check
    npm run test
    npm run build
} finally {
    Pop-Location
}
