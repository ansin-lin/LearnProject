$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
$composeFile = Join-Path $projectRoot 'infra\redis\compose.yml'

docker compose -f $composeFile up -d
docker compose -f $composeFile ps
