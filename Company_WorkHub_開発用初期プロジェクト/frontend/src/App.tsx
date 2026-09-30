import { Container, Paper, Stack, Typography } from '@mui/material';

export default function App() {
  return (
    <Container maxWidth="md" sx={{ py: 8 }}>
      <Paper variant="outlined" sx={{ p: 4 }}>
        <Stack spacing={2}>
          <Typography variant="h4" component="h1">
            Company WorkHub
          </Typography>
          <Typography variant="h6">開発用初期プロジェクト（React）</Typography>
          <Typography>React・TypeScript・MUI の起動確認ページです。</Typography>
          <Typography color="text.secondary">
            認証・権限・共通処理・業務機能は未実装です。詳細設計に従って実装してください。
          </Typography>
        </Stack>
      </Paper>
    </Container>
  );
}
