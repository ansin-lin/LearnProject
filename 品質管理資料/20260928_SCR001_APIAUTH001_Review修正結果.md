# SCR-001／API-AUTH-001 Review修正結果

作成日：2026-09-28

変更ID：CHG-AUTH-20260928-01

基準：BL-2026-09-24、今回提示されたReview修正指示。対象は画面詳細設計1本、API詳細設計1本、関連基本設計4本（00・10・16・19）。基本設計21本について関連認証規則の影響検索を実施した。全機能の再監査、実装、テスト実行は対象外。

画面ファイルはプロジェクト内の正式ファイル `01_SCR-001_ログイン画面詳細設計書_React.xlsx` を使用した。「React(6)」名の別ファイルは提供資料中に存在しない。

## 修正ファイル

| ファイル | 改訂後 | 主な修正Sheet |
|---|---|---|
| 01_SCR-001_ログイン画面詳細設計書_React.xlsx | Ver.2.4 | 05_Component_State、06_Event処理、07_API_Mapping、08_Message_例外、09_単体テスト観点、文書管理 |
| 001_API-AUTH-001_ログインAPI詳細設計書.xlsx | Ver.2.1 | 02_API一覧～06_単体テスト観点、文書管理 |
| 00_基本設計書総合管理.xlsx | Ver.2.6 | 02_文書一覧、06_設計決定事項、文書管理 |
| 10_API共通と認証_基本設計書.xlsx | Ver.2.6 | 21_エラーコード、22_API処理・権限、文書管理 |
| 16_業務ルールと状態遷移_基本設計書.xlsx | Ver.2.6 | 28_業務ルール、文書管理 |
| 19_ログファイルRedisセキュリティ_基本設計書.xlsx | Ver.2.6 | 35_Redis、36_セキュリティ、文書管理 |

## 指摘対応表

| No. | 重要度 | 分類 | ファイル | Sheet・セル/位置 | 現在の記載（修正前） | 問題・根拠 | 影響範囲 | 具体的修正 | 関連資料 | 状態 |
|---|---|---|---|---|---|---|---|---|---|---|
| 1 | Major | 429境界 | SCR-001 | 06 D71、07 F43:I43、08 H18:J18、09 A60:L63 | 0以下を60秒へ置換 | 非負整数の0は待機不要 | State・Focus・再操作 | 0は即時再活性化後Password Focus。負数・非整数等のみ60。0／1／不正HeaderのUT追加 | 基設10／19_API共通I-O | 対応済 |
| 2 | Minor | Message正本 | SCR-001 | 04 F28:J31、08 G10:G13、09 B50/E50 | 正式MSG-Vを使用済みだが追跡欄に旧採番表現あり | 現在の採番状態との混同 | Validation | MSG-V-001/002とParameterを維持し、旧表現を正本参照へ置換 | 基設18／31V_ValidationMessage | 対応済 |
| 3 | Major | 遷移 | SCR-001 | 06 J38/J69 | 共通Navigation Error | 未定義の異常処理 | Route処理 | 事前に遷移先判定済みのnavigate異常欄を「－」へ変更 | 基設03／20_画面Route定義 | 対応済 |
| 4 | Minor | UT補強 | SCR-001 | 09 A28:L28、A62:L62 | Button表示・Enter一回送信の確認不足 | ITEM-003仕様の証跡不足 | 二重Submit・表示 | UT-019に送信中／終了後表示、UT-037にEnterと重複抑止を追加 | 04 ITEM-003、06 EVT-002 | 対応済 |
| 5 | Major | 試行制限 | API-AUTH-001・基設19 | API 04 Step3／Redis-01、06 UT-018/019/036～039；19 35行15～16 | IP/User単位・Window方式が未確定 | 担当者ごとに判定差異 | Redis・429 | IP/User独立2制限、各初回起点60秒固定窓・10回。2 Keyを同一Lua呼出で加算と初回TTL設定。いずれか超過で429 | 基設10 AUTH-4290・19／35・36 | 対応済 |
| 6 | Major | Lock境界 | API-AUTH-001・基設10/16/19 | API 03行65～66、04 Step5～7、05 SQL-04、06 UT-013～015/034/035/040～042；16 28行9 | 5回目Code、満了後起点が未確定 | 外部応答・DB状態に差異 | HTTP・SQL・TX・Redis | 1～4回401、5回目count=5＋30分Lockで423。Lock中照合なし。満了後次回照合前0/null、最初の失敗1/null。通常拒否Commit、System異常Rollback | BR-AUTH-002、TX-011、10／21 | 対応済 |
| 7 | Minor | 可読性 | API-AUTH-001 | 04 A9:L23 | 長文Block | 分岐・SQL・TXの追跡が難しい | 処理実装・Review | 14Stepに展開。処理・条件・SQL/Redis・正常/異常・Code・TX列を明示。正常系と補償処理を維持 | Review指示D-01 | 対応済 |
| 8 | Minor | SQL表現 | API-AUTH-001 | 05 I21 | 利用Index | 実行計画の保証と誤読される | SQL設計 | 「想定利用Index」へ変更 | Review指示D-02 | 対応済 |

## 決定・未決事項

| 管理ID | 状態 | 確定内容 | 記録先 |
|---|---|---|---|
| Q-AUTH-01 | Closed | IP/User独立2制限・60秒固定窓・各10回 | API改訂履歴Ver.2.1、基設19 |
| Q-AUTH-02 | Closed | 5回目423、満了後照合前Reset、最初の失敗は1 | API改訂履歴Ver.2.1、基設10/16/19 |

今回指示範囲の仕様未決事項は0件。正式な成果物・変更Baseline承認はProject Leaderの承認記録を必要とする。旧BLを無記録で再承認せず、00の変更管理欄と各改訂履歴に差分を登録した。

## Cross Review結果

| 確認事項 | 結果 |
|---|---|
| username：Trim、1～50 Code Point／password：非Trim、1～100 Code Point | 一致 |
| AUTH-4001/4004/4005/4290、COMMON-5000のHTTP | 401/423/403/429/500で一致 |
| AUTH-4001/4004/4005→MSG-E-019、AUTH-4290→MSG-E-008 | 一致。新規Message IDなし |
| Retry-After 0有効、負数不正、正数待機中Focusなし | 画面State・Event・Mapping・Message・UTで一致 |
| passwordMustChange | AUTH-001は参考、最終Session／権限／遷移はAUTH-004を維持 |
| SQL-04の満了Reset・失敗更新、履歴Commit、System異常Rollback | Flow・SQL・UT・BR-AUTH-002で一致 |
| 試行制限Counterと認証失敗Cache | 用途・Keyを分離。成功時はRate Counterを削除しない |
| Sheet構成・API JSON契約・Message ID体系 | 維持。Sheet追加／削除なし |
| 変更対象外セル・Formula・Drawing | 保存後の差分検査で維持を確認 |
| 基本設計残り17本 | 今回変更なし |
| 画面／APIの単体テスト観点 | 38件／42件。ID重複なし。テスト実施結果ではない |
| 正文の要確認・未確定・旧採番表現・未決ID・共通Navigation Error | 0件。Closed履歴を除く |

## 判定

今回のReview修正指示の範囲：**Review OK**。

修正対象の指摘はMajor 4件、Minor 4件、全件対応済み。残存Critical／Major／Minor／仕様確認事項は本範囲では0件。

最優先だった5回目LockとRetry-After=0を解消し、仕様・処理・SQL・テスト観点の対応を確認した。API契約と既存のSheet構成は維持している。

配布判定：**条件付き可分发**。変更成果物と関連基設の最終承認記録後に配布する。共通詳細設計など対象外成果物の全面再Review、Java／React／Redis実装の実行検証、他の画面・APIの認証共通仕様反映は本判定に含まない。

Gitへのcommit／pushは本作業では行っていない。変更前ファイルは作業領域にBackup保管済み。
