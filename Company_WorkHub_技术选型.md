# Company WorkHub 技术选型

## 共通技术

| 分类 | 技术 | 用途 |
|---|---|---|
| 后端语言 | Java 17 LTS | 日本企业项目常用的稳定版本 |
| 后端框架 | Spring Boot 3.5.16 | REST API、业务处理及依赖管理；使用 Spring Boot BOM 统一 Spring 依赖 |
| 安全认证 | Spring Security + JWT | 登录认证、角色权限及数据权限 |
| 数据访问 | MyBatis 3 + MyBatis-Spring-Boot-Starter | Mapper、SQL 与数据库访问 |
| 数据库 | PostgreSQL 16 | 业务数据保存 |
| 缓存 | Redis 7.4.6 | Token、权限及临时数据缓存 |
| API 文档 | Springdoc OpenAPI + Swagger UI | API 定义与联调 |
| 数据库版本管理 | Flyway | 按版本执行建表及数据变更 SQL |
| 参数校验 | Jakarta Validation | 请求参数和业务输入校验 |
| 日志 | SLF4J + Logback | 应用日志、异常日志及操作日志 |
| 单体测试 | JUnit 5 + Mockito + AssertJ | Service、Controller 及工具类测试 |
| 集成测试 | Spring Boot Test | API 与数据库结合测试 |
| 覆盖率 | JaCoCo | 单体测试覆盖率统计 |
| 静态检查 | Checkstyle + SpotBugs | Java 编码规范和缺陷检查 |
| 构建工具 | Maven | 编译、测试和打包 |
| 容器 | Docker + Docker Compose | PostgreSQL、Redis 和本地运行环境 |
| Web 服务器 | Nginx | 前端静态资源及 API 反向代理 |
| 版本管理 | Git + GitHub | Feature Branch、Pull Request 和 Review |

## 前端练习 A：Vue

| 分类 | 技术/库 | 用途 |
|---|---|---|
| 核心框架 | Vue 3.5.40 | Composition API |
| 开发语言 | TypeScript | 类型检查 |
| 构建工具 | Vite 7.3.1 | 开发服务器和打包 |
| UI 组件库 | Vuetify 3.13.0 | 表单、表格、弹窗、分页及布局 |
| 路由 | Vue Router | 页面路由和路由权限控制 |
| 状态管理 | Pinia | 用户、权限及共通状态 |
| HTTP 请求 | Axios | REST API 调用和共通拦截器 |
| 表单校验 | VeeValidate + Yup | 输入校验和校验信息管理 |
| 日期处理 | Day.js | 日期格式化和日期计算 |
| 单体测试 | Vitest + Vue Test Utils | 组件和共通逻辑测试 |
| API Mock | MSW | 后端未完成时的 API 模拟 |
| 代码规范 | ESLint + Prettier | 静态检查和格式化 |
| 包管理 | pnpm | 前端依赖管理 |

## 前端练习 B：React

| 分类 | 技术/库 | 用途 |
|---|---|---|
| 核心框架 | React 19.2.7 | Function Component + Hooks |
| 开发语言 | TypeScript | 类型检查 |
| 构建工具 | Vite 7.3.1 | 开发服务器和打包 |
| UI 组件库 | MUI（Material UI）7.3.11 | 表单、表格、弹窗、分页及布局 |
| 路由 | React Router | 页面路由和路由权限控制 |
| 状态管理 | Redux Toolkit + React Redux | 用户、权限及共通状态 |
| HTTP 请求 | Axios | REST API 调用和共通拦截器 |
| 表单管理 | React Hook Form | 表单状态和提交控制 |
| 表单校验 | Yup | Schema 校验 |
| 日期处理 | Day.js | 日期格式化和日期计算 |
| 单体测试 | Vitest + React Testing Library | 组件和共通逻辑测试 |
| API Mock | MSW | 后端未完成时的 API 模拟 |
| 代码规范 | ESLint + Prettier | 静态检查和格式化 |
| 包管理 | pnpm | 前端依赖管理 |

## 开发工具

| 分类 | 工具 |
|---|---|
| 后端 IDE | Eclipse IDE for Enterprise Java and Web Developers |
| 前端 IDE | Visual Studio Code |
| API 调试 | Postman / Swagger UI |
| 数据库工具 | DBeaver |
| Redis 工具 | RedisInsight |
| Git 工具 | Eclipse EGit / GitHub Desktop / SourceTree |
| Java 构建 | Maven |
| 浏览器调试 | Chrome DevTools |

## 本项目固定方针

- 前端练习分为 Vue 版和 React 版，两版共用同一套后端 API、权限、数据库和测试基线。
- Dashboard 使用 KPI Card、状态明细 Table 和通知 Table，不使用图表库。
- 不实施 E2E 自动化测试；前端以组件单体测试，后端以 JUnit/Mockito 单体测试和必要的 Spring Boot 集成测试为主。
- Java 固定为 17 LTS，数据库固定为 PostgreSQL 16，后端开发 IDE 固定为 Eclipse。
- Flyway、MapStruct、Testcontainers 按下方条件使用，不作为所有学员必须实现的功能。

## 版本固定与依赖管理

- 后端以 Spring Boot 3.5.16 的 dependency management 为基线；Spring Framework、Jackson、JUnit 等由 BOM 管理，不允许学员单独覆盖版本。
- 项目提交 Maven Wrapper、`pom.xml` 和有效的 `dependencyManagement`；Coding 开始后原则上不升级依赖，安全修正由 Project Leader 统一判断。
- Vue 与 React 分别维护独立的 `package.json` 和 `pnpm-lock.yaml`。直接依赖使用固定版本，不使用 `^`、`~`、`latest`。
- CI 和学员本地统一使用 `pnpm install --frozen-lockfile`，禁止删除 Lockfile 后自行重新解析依赖。
- 上表是教学项目的承认基线。新增库必须说明用途、License、维护状态、替代方案和对 Build/Test 的影响，经 Review 后才能导入。

## 可选技术

| 技术 | 使用条件 |
|---|---|
| MapStruct | DTO 与 Entity 字段较多、转换代码重复时使用；本练习可先手写转换 |
| Testcontainers | 需要使用真实 PostgreSQL 或 Redis 进行自动化集成测试时使用；不属于单体测试必需项 |
| SheetJS | 实现 Excel 导入或导出功能时再追加 |
