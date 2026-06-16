# 🌊 TodayMemo: Vibe Coding History (Day 1 - Day 14)

このファイルは、開発者とAI（Gemini Agent）が対話を通じて、ゼロからモダンなAndroidアプリを作り上げた「Vibe Coding」の全記録です。

## 📖 プロジェクトの軌跡

### 🟦 Phase 1: 基礎構築（Day 1 - Day 4）
**「まずは動くものを作る」**
- **User**: 「Android StudioでToDoアプリを作りたい」
- **Agent**: 基本的な `EditText`、`Button`、`ListView` の構成を提案。
- **Evolution**: 
  - メモリ上でのリスト管理から開始。
  - `SharedPreferences` と `Gson` を導入し、データが消えない「アプリとしての最低条件」をクリア。
  - `Task` クラスを導入し、「完了状態」を持てるようにデータ構造を定義。

### 🟩 Phase 2: モダンUIへの転換（Day 5 - Day 7）
**「リストからカードへ、プロの見た目へ」**
- **User**: 「チェックボックスを付けたい」「RecyclerViewにしたい」
- **Agent**: `RecyclerView.Adapter` と `ViewHolder` パターンの解説と実装。
- **UI Breakthrough**: 
  - `MaterialCardView` と `FloatingActionButton (FAB)` を導入。
  - **トラブル**: テーマ設定ミスでクラッシュ ➔ `NoActionBar` と `MaterialComponents` への修正で解決。
  - **UX改善**: キーボードで入力欄が隠れる問題を `ConstraintLayout` の制約調整で解決。

### 🟨 Phase 3: データベースとアーキテクチャ（Day 8 - Day 10）
**「スケーラブルな設計への挑戦」**
- **User**: 「Room Databaseを使いたい」「MVVMを学びたい」
- **Agent**: Entity, DAO, Database の3種の神器を解説。
- **Architectural Shift**: 
  - Repository パターンを導入し、データアクセスを分離。
  - `TaskViewModel` を作成し、Activityの負担を軽減。
  - `ViewModelFactory` による依存注入の仕組みを構築。

### 🟧 Phase 4: 高度な機能と洗練（Day 11 - Day 13）
**「使い勝手の追求とリファクタリング」**
- **User**: 「検索したい」「並び替えたい」「整理したい」
- **Agent**: 
  - `TextWatcher` を使ったリアルタイム検索を提案。
  - `Spinner` による動的ソートと、検索ロジックとの統合。
  - **LiveData** の導入により、データの自動同期（リアクティブUI）を実現。
- **Clean Code**: 日本語や色のハードコードを排除し、すべてリソースファイルへ移行。

### 🟥 Phase 5: プロジェクト完成（Day 14）
**「一つの作品として結実」**
- **User**: 「GitHubに載せられるようにしたい」
- **Agent**: スクリーンショット付きの `README.md` 作成。全ドキュメントの同期。

---

## 🛠 解決した主要なトラブル（Troubleshooting）

1.  **Material Components Crash**: テーマを `MaterialComponents` 系にしないと `MaterialCardView` が使えない罠。➔ `themes.xml` の修正で克服。
2.  **KSP JVM Signature Error**: Kotlin 2.2 と Room (KSP) の相性問題。➔ `suspend` の一時的な解除と ViewModel での制御により、機能を損なわずに回避。
3.  **UI Overlapping**: キーボードによるFABの遮蔽。➔ 入力エリアを画面上部に固定するデザイン変更で、最高のUXを実現。

---

## 💡 Vibe Coding のまとめ
この14日間、ユーザーの「こうしたい」という直感（Vibe）を、AIが技術的な構造（Code）に翻訳し続けることで、最短距離で高品質なアプリに到達しました。

- **学びの最大化**: 常に「なぜそうするのか」を対話しながら進めることで、コピー＆ペーストではない深い理解を伴う開発となりました。
- **品質の維持**: リファクタリングを工程に組み込むことで、初心者向けプロジェクトでありながら、中身はプロ仕様の設計を実現しました。

---
**TodayMemo v1.0 - Completed on Day 14**
Created with ❤️ and AI.
