# VS Code導入手順

Frontend担当はNode.js 22.13.xとnpm 10.xを使用する。推奨ExtensionはESLint、Prettier、TypeScript支援である。

```powershell
cd frontend
npm ci
npm run dev
```

Commit前に `npm run lint`、`npm run format:check`、`npm run test`、`npm run build` を実行する。
