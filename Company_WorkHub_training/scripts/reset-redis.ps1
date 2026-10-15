param(
    [switch]$Force
)

$ErrorActionPreference = 'Stop'

if (-not $Force) {
    throw 'Redisの保存データを削除します。実行する場合は -Force を指定してください。'
}

$projectRoot = Split-Path -Parent $PSScriptRoot
$composeFile = Join-Path $projectRoot 'infra\redis\compose.yml'

docker compose -f $composeFile down -v
docker compose -f $composeFile up -d
docker compose -f $composeFile ps
