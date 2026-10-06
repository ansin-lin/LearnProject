# Company WorkHub 開発用初期プロジェクト

这是交给学员的开发起始工程，不是已经完成的共通基盘。前端只提供 React 版，不包含 Vue，也没有业务画面。

## 1. 内容与边界

| 目录 | 内容 |
| --- | --- |
| frontend | React＋TypeScript＋MUI 启动页、前端依赖、构建及测试配置 |
| backend | Spring Boot 启动类、Maven 依赖、PostgreSQL／Redis 连接配置、启动及连接检查 |
| infra | 本地 PostgreSQL／Redis 的 Docker Compose 配置 |
| docs | 担当边界与验证记录 |

学员需要实现：共通响应、异常处理、参数校验处理、日志／traceId、认证／权限、前端通信封装、Route／Layout、Message、各业务画面／API／SQL、Migration 与业务测试。

仅安装对应依赖，不提供 Controller、Service、Mapper、统一响应类、JWT Filter、SecurityFilterChain、Axios 拦截器、Store、Route Guard 或业务数据库表。不要把启动页当作 SCR-001。

本工程不修改已确认的基本设计及详细设计。Library 的导入不代表功能已经实现。

## 2. 环境

- 项目目标：JDK 17。Eclipse 中设置 Installed JREs／Execution Environment 为 JavaSE-17。
- Maven Wrapper：3.9.14；首次使用需联网下载。Eclipse 可使用 m2e 导入 Maven 工程。
- Node.js：22.13+（22.x）或 24.x，建议团队统一安装同一个版本。
- pnpm：10.28.2。前端直接依赖固定版本，间接依赖由 pnpm-lock.yaml 固定。
- 本地容器：Docker Desktop，Linux containers。也可以自行安装 PostgreSQL 16 与 Redis 7.4.6。
- 本地端口：前端 5173、后端 8080、PostgreSQL 15432、Redis 16379。

后端版本按项目技术选型：Spring Boot 3.5.16、Java 17。Spring／JUnit／Jackson／PostgreSQL Driver／Flyway 等统一由 Spring Boot BOM 管理，不单独覆盖。

MyBatis Starter、Springdoc、JWT 库和构建工具的版本见 pom.xml。没有引入 MapStruct、Testcontainers、图表库或 E2E 工具。

## 3. 启动 PostgreSQL 与 Redis

Windows 请先将工程解压到简短的英文路径，例如 `C:\work\company-workhub`。当前文档归档目录包含较长中日文路径，本机曾出现包安装失败及覆盖率路径编码异常，不建议直接在归档目录开发。

在 infra 目录执行：

```powershell
Copy-Item .env.example .env
```

编辑 `.env`，设置自己的本地数据库和 Redis 密码。不要将真实密码提交到 Git；不要直接使用样例占位密码。

```powershell
docker compose up -d
docker compose ps
```

两个服务应显示 healthy。配置仅绑定 127.0.0.1，供本机练习；不是部署配置。数据库用户是本地初始化用户，正式应用账号分离由后续环境／安全设计落实。

创建的是空数据库，没有业务表或初始用户。Flyway 默认关闭，避免在 DB 详细设计实现前自动建表。

停止环境使用 `docker compose stop`。已有数据库密码不会因为修改 `.env` 自动更新；请按 PostgreSQL 账号管理方式修改，不要为解决密码问题随意删除 Volume。

## 4. 后端：命令行启动

在 backend 目录设置环境变量。密码与 infra/.env 一致：

```powershell
$env:DB_PASSWORD = '你的本地数据库密码'
$env:REDIS_PASSWORD = '你的本地Redis密码'
.\mvnw.cmd verify
.\mvnw.cmd spring-boot:run
```

Linux/macOS 使用 `sh ./mvnw`；通过 `export` 设置同名环境变量。

Spring Boot 不会自动读取 infra/.env。Compose 的 .env 与应用进程的环境变量需要分别设置。

连接其他本地实例时，可另外设置 DB_URL、DB_USERNAME、REDIS_HOST、REDIS_PORT、SERVER_PORT。更换后端端口后，同步修改 frontend/vite.config.ts 的开发代理目标。

默认允许在 DB／Redis 尚未运行时启动 Spring 上下文，便于先确认工程环境。**Started 日志不表示数据库连接成功**。连接验证见第 7 节。

### Eclipse

1. File → Import → Maven → Existing Maven Projects，选择 backend。
2. Project → Properties → Java Compiler，确认目标版本 17；Installed JREs 选择 JDK 17。
3. 如依赖尚未解析，执行 Maven → Update Project。
4. Run Configurations → Java Application，选择 `jp.co.workhub.WorkHubApplication`。
5. Environment 中加入 DB_PASSWORD、REDIS_PASSWORD，必要时加入其他连接变量。
6. 启动，确认控制台显示 Started WorkHubApplication。

## 5. 安全默认行为（务必阅读）

本工程没有实现项目登录／JWT／权限。

由于已引入 Spring Security，框架默认认证仍然生效：可能显示框架登录页，或者访问受保护路径时返回 401／重定向；控制台可能出现框架生成的临时开发密码。这不是 API-AUTH-001，不是业务用户，也不是验收规格。

不要为了启动方便加入全路径 permitAll、关闭全局安全检查或把临时密码写入配置。后续认证担当应按照已确认详设替换框架默认行为。

仅开放框架的健康检查；不暴露 env、配置、堆转储等管理接口。健康检查不返回连接细节。Swagger 依赖已安装但默认关闭，待认证／API 文档访问策略实现后启用。

本工程只绑定本机，禁止直接作为生产或共享测试环境部署。

## 6. React 前端

安装指定 pnpm（如果已有该版本则跳过）：

```powershell
npm install -g pnpm@10.28.2
```

在 frontend 目录执行：

```powershell
pnpm install --frozen-lockfile
pnpm dev
```

浏览器打开 http://127.0.0.1:5173，应显示 Company WorkHub 启动页。此页面没有调用后端，不代表认证／DB 已连通。

`/api` 和 `/actuator` 仅设置 Vite 本地反向代理，不改变业务 API 契约，也没有实现 Axios 共通通信。无需提前编写 CORS 业务配置。

```powershell
pnpm lint
pnpm format:check
pnpm test
pnpm test:coverage
pnpm build
```

React Router、Redux Toolkit、React Redux、Axios、React Hook Form、Yup、Day.js、MSW 已安装，但没有预先创建对应业务封装。MSW 尚未启动 Worker，也没有假登录数据。

## 7. 验证层次

| 检查 | 命令／地址 | 含义 |
| --- | --- | --- |
| 后端构建与启动测试 | `./mvnw.cmd verify` | 编译、Spring Context 测试、打包；不要求 DB／Redis 在线 |
| 后端存活 | `http://127.0.0.1:8080/actuator/health/liveness` | 应用进程启动，不代表 DB 可用 |
| 基础设施就绪 | `http://127.0.0.1:8080/actuator/health/readiness` | DB／Redis 实际可访问时应为 UP；未连接时 503 是预期结果 |
| 连接测试 | `./mvnw.cmd -Pconnection-check test` | SELECT 1 与 Redis PING，不建表、不写业务数据；需要先设置密码并启动服务 |
| 后端静态检查 | `./mvnw.cmd -Pquality verify` | 最小 Checkstyle 规则及 SpotBugs；不是完整 Coding 规范的替代品 |
| 前端启动测试 | `pnpm test` | 仅确认启动页能够渲染 |

JaCoCo 报告在 backend/target/site/jacoco，前端覆盖率在 frontend/coverage。没有设置业务覆盖率验收阈值；由项目测试方针决定。

## 8. 后续开发约定

- 共通功能各自指定主担当，其余人使用成果，不重复实现多套。
- 先明确共通接口与前置依赖，再展开业务 Coding。不要在本初始工程里推测新的规则。
- DB 担当完成 Migration 并 Review 后再启用 FLYWAY_ENABLED=true；不要使用自动更新 Schema 功能代替 DB 详设。
- API 204、文件下载和异常响应等必须参照基本设计；这里没有预置第二套规格。
- 新增依赖或修改版本需 Leader 确认，不删除 lockfile 重新解析。
- 本工程不包含实际业务的单体测试案例，学员仍需按设计书编写测试并保留证迹。

## 9. 技术来源

版本基线：上级目录的 Company_WorkHub_技术选型.md。

- Spring Boot 环境要求：https://docs.spring.io/spring-boot/3.5/system-requirements.html
- Vite 7 环境要求：https://v7.vite.dev/guide/migration
- MyBatis Starter：https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
- Springdoc：https://springdoc.org/

实际执行结果见 docs/検証結果.md；不要把未执行的连接测试标为通过。
