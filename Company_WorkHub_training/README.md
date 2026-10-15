# Company_WorkHub_training

Company WorkHub 的 React + Spring Boot 共同开发用初期代码基盘。

本基盘只提供可启动、可连接、可测试的工程骨架。共通响应、共通异常、参数校验、JWT认证、权限判定、业务API、业务画面、Batch Job及其单体测试均为学员任务，不在初期基盘中预先实现。

## 1. 技术与运行方式

| 区分 | 采用内容 |
| --- | --- |
| Backend | Java 17、Spring Boot 3.5.16、Maven、MyBatis、Spring Security、Spring Batch |
| Frontend | React、TypeScript、Vite、MUI、npm |
| DB | PostgreSQL 16；开发和单体测试阶段由每位学员本地安装 |
| Redis | Redis 7.4；开发和单体测试阶段由每位学员通过 Docker 启动 |
| 结合测试 | 连接 Leader 管理的共享 PostgreSQL / Redis |
| CI | GitHub Actions 只执行 build、静态检查和 test，不访问任何 AI API |

## 2. 目录结构

```text
Company_WorkHub_training/
├─ backend/
│  ├─ workhub-core/       Domain、Mapper接口、共通模型
│  ├─ workhub-api/        Web API应用
│  └─ workhub-batch/      Spring Batch应用
├─ frontend/              React应用
├─ config/                结合测试外部配置样例
├─ infra/redis/           本地Redis容器定义
├─ scripts/               构筑、测试、Redis及Batch执行脚本
└─ docs/                  环境构筑和项目运用说明
```

## 3. 必需软件

- Eclipse IDE 2026系或项目指定版
- JDK 17
- Node.js 22.13.x
- npm 10.x
- PostgreSQL 16
- Docker Desktop（只用于本地 Redis）
- Git

版本不一致时先修正开发环境。后台 Maven Enforcer 会拒绝非 Java 17，前台 npm 会拒绝不符合 `package.json` 的 Node/npm。

## 4. 初次构筑

以 PostgreSQL 管理用户执行：

```sql
CREATE ROLE workhub_app LOGIN PASSWORD 'workhub_local';
CREATE DATABASE company_workhub OWNER workhub_app ENCODING 'UTF8';
```

启动 Redis：

```powershell
.\scripts\start-redis.ps1
```

Backend：

```powershell
cd backend
.\mvnw.cmd clean verify
cd ..
.\scripts\run-api.ps1
```

API 默认使用 `local` Profile。访问 `http://127.0.0.1:8080/actuator/health` 确认状态。

Frontend：

```powershell
cd frontend
npm ci
npm run dev
```

访问 `http://127.0.0.1:5173`。开发服务器把 `/api` 与 `/actuator` 转发至后台。

## 5. 配置规则

- `application.yml`：全环境共通设置。
- `application-local.yml`：个人开发环境的固定本地连接设置。
- `application-test.yml`：不依赖外部 DB/Redis 的普通单体测试设置。
- `config/application-integration.yml`：结合测试连接信息，不提交 Git。
- `config/application-integration-example.yml`：可提交的样例。

结合测试时复制样例并填写 Leader 提供的连接信息，启动参数指定 `integration` Profile。密码不得写入已跟踪文件。

```powershell
.\scripts\run-api.ps1 -Profile integration
```

## 6. Batch

Batch 采用 Spring Batch，由外部命令按 Job 名启动；Job 的 Tasklet/Chunk 结构由详细设计决定。重复启动控制使用 Redis `SET NX`，具体实现由担当学员完成。

```powershell
.\scripts\run-batch.ps1 -JobName BATCH-001 -BusinessDate 2026-10-07
```

初期基盘没有业务 Job，因此在 Job 实装前该命令只用于确认启动方式。

## 7. 全体检查

```powershell
.\scripts\build-all.ps1
```

后台执行 test、Checkstyle、SpotBugs；前台执行依赖再现、ESLint、Prettier检查、Vitest和Production Build。

详细步骤见 [开发环境构筑](docs/開発環境構築.md)、[Git运用规则](docs/Git運用ルール.md) 和 [担当范围](docs/担当範囲.md)。
