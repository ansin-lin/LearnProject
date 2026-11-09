# Git運用ルール

- 1詳細設計書を原則1開発単位とし、対応するCodingと単体テストを同じTaskで追跡する。
- Commitには対象IDを含める。例：`SCR-001 ログイン画面を実装`。
- Design未確定事項を実装だけで決定しない。Q&Aまたは設計書修正を先に行う。
- Pull Requestには参照詳細設計、Test結果、影響範囲を記載する。
- `target/`、`node_modules/`、`dist/`、接続Password、個人IDE設定をCommitしない。
- Merge前に担当範囲のBuildとTestを成功させる。

Conflict解消時は双方の設計意図を確認し、単純に片側を採用しない。
