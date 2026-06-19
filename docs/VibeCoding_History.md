# 🌊 TodayMemo: Vibe Coding History (Day 1 - Day 18)

このファイルは、開発者とAI（Gemini Agent）が対話を通じて、ゼロからモダンなAndroidアプリを作り上げ、ストア公開申請まで至った「Vibe Coding」の全記録です。

## 📖 プロジェクトの軌跡

### 🟦 Phase 1: 基礎構築（Day 1 - Day 4）
**「まずは動くものを作る」**
- **Evolution**: 
  - メモリ上でのリスト管理から開始。
  - `SharedPreferences` と `Gson` を導入し、データが消えない「アプリとしての最低条件」をクリア。

### 🟩 Phase 2: モダンUIへの転換（Day 5 - Day 7）
**「リストからカードへ、プロの見た目へ」**
- **UI Breakthrough**: 
  - `MaterialCardView` と `FloatingActionButton (FAB)` を導入。
  - **UX改善**: キーボードによるFABの遮蔽問題を `ConstraintLayout` の制約調整で解決。

### 🟨 Phase 3: データベースとアーキテクチャ（Day 8 - Day 10）
**「スケーラブルな設計への挑戦」**
- **Architectural Shift**: 
  - Room Database への移行と Repository パターンの採用。
  - `TaskViewModel` による Activity の負担軽減と、依存注入の仕組みを構築。

### 🟧 Phase 4: 高度な機能と洗練（Day 11 - Day 13）
**「使い勝手の追求とリアクティブUI」**
- **Innovation**: 
  - リアルタイム検索と動的ソート（名前順・作成順・完了順）の統合。
  - **LiveData** の導入により、手動更新不要の自動同期システムを実現。

### 🟥 Phase 5: 公開への道（Day 14 - Day 18）
**「世界へ届ける最終仕上げ」**
- **Final Stretch**:
  - **通知機能**: スワイプで消えない「常駐型リマインダー」を実装。RedmiやXperia特有の挙動に対処。
  - **ストア申請**: 正式なID `com.todaymemo.myapp` への移行、バージョン 1.1 への更新、Playストアへの審査提出。

---

## 🛠 解決した主要なトラブル（Troubleshooting）

1.  **Notification Invisibility**: XiaomiやXperiaでの通知制限。➔ `setFullScreenIntent` や `cancel()` ➔ `notify()` の瞬き、チャンネルIDの更新により克服。
2.  **Xperia UI Visibility**: 文字が白抜きになる視認性問題。➔ テーマ (`themes.xml`) レベルでの文字色・ヒント色の明示的固定で解決。
3.  **Application ID Migration**: フォルダ構造の物理的再配置を伴うリファクタリング。➔ 一括置換とディレクトリ再構築によりビルド整合性を維持。

---

## 💡 Vibe Coding の総括
直感（Vibe）とコード（Code）の融合により、わずか18日間で「プロ仕様のアーキテクチャ」と「ストア公開クオリティのUX」を両立したアプリが誕生しました。AIとの対話は、単なるコード生成を超え、実務的なデバッグやリリース戦略のパートナーとしての役割を果たしました。

---
**TodayMemo v1.1 - Submitted on Day 18**
Created with ❤️ and AI.
