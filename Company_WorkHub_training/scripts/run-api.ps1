param(
    [ValidateSet('local', 'integration')]
    [string]$Profile = 'local'
)

$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
$backendRoot = Join-Path $projectRoot 'backend'
$additionalConfig = ([System.Uri](Join-Path $projectRoot 'config')).AbsoluteUri + '/'

Push-Location $backendRoot
try {
    .\mvnw.cmd -pl workhub-api -am spring-boot:run "-Dspring-boot.run.profiles=$Profile" "-Dspring-boot.run.arguments=--spring.config.additional-location=$additionalConfig"
} finally {
    Pop-Location
}
