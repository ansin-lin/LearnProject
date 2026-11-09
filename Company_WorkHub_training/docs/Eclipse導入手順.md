# Eclipse導入手順

1. EclipseでJDK 17をInstalled JREとして登録する。
2. `backend/pom.xml` を Existing Maven Projects としてImportする。
3. Compiler compliance levelが17であることを確認する。
4. Maven Project Updateを実行する。
5. `WorkHubApiApplication` を Spring Boot Appとして起動する。
6. Run ConfigurationのActive Profileは通常 `local` とする。

Eclipse固有ファイル `.project`、`.classpath`、`.settings/` はGit管理しない。
