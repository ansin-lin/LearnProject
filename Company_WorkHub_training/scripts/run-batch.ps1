param(
    [Parameter(Mandatory = $true)]
    [string]$JobName,
    [string]$BusinessDate = (Get-Date -Format 'yyyy-MM-dd'),
    [ValidateSet('local', 'integration')]
    [string]$Profile = 'local'
)

$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $PSScriptRoot
$backendRoot = Join-Path $projectRoot 'backend'
$additionalConfig = ([System.Uri](Join-Path $projectRoot 'config')).AbsoluteUri + '/'

Push-Location $backendRoot
try {
    .\mvnw.cmd -pl workhub-batch -am spring-boot:run "-Dspring-boot.run.profiles=$Profile" "-Dspring-boot.run.arguments=--spring.config.additional-location=$additionalConfig --spring.batch.job.name=$JobName --businessDate=$BusinessDate"
} finally {
    Pop-Location
}
