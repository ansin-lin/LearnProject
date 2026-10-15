import { Container, Paper, Stack, Typography } from '@mui/material';

export default function App() {
  return (
    <Container maxWidth="md" sx={{ py: 8 }}>
      <Paper variant="outlined" sx={{ p: 4 }}>
        <Stack spacing={2}>
          <Typography variant="h4" component="h1">
            Company_WorkHub_training
          </Typography>
          <Typography variant="h6">共同開発用コード基盤（React）</Typography>
          <Typography>React・TypeScript・MUI の起動確認ページです。</Typography>
          <Typography color="text.secondary">
            認証・権限・共通処理・業務機能は未実装です。詳細設計に従って実装してください。
          </Typography>
        </Stack>
      </Paper>
    </Container>
  );
}
