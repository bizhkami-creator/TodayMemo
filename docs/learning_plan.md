# 今日やることメモアプリ 開発計画（2週間）

## 目的
Androidアプリ開発の基礎を網羅的に習得する。

学習対象
* Kotlin / Coroutines
* XML Layout (ConstraintLayout, CardView, Material Design)
* RecyclerView (Adapter, ViewHolder)
* Room Database (Entity, DAO, Singleton)
* MVVM Architecture (ViewModel, Repository, Factory, LiveData)
* Git/GitHub / AI活用開発フロー (Vibe Coding)

---

## Day1 - Day2：プロジェクト開始と基本UI
- [x] Android Studioプロジェクト作成
- [x] タイトル表示 (`TextView`)
- [x] 入力欄 (`EditText`) と追加ボタン (`Button`) の配置
- [x] 初期の `ListView` による一覧表示実装

---

## Day3 - Day4：データの永続化基礎
- [x] `SharedPreferences` の学習と導入
- [x] `Gson` ライブラリによるオブジェクト ⇄ JSON変換の実装
- [x] 起動時の自動読み込みと更新時の自動保存

---

## Day5 - Day6：モダンなリスト表示への進化
- [x] `Task` クラス（Entity）の作成
- [x] `ListView` から `RecyclerView` に完全移行
- [x] 1行分のカスタムレイアウト (`item_task.xml`) 作成
- [x] `ViewHolder` パターンの実装による描画の最適化
- [x] 完了チェックボックスの状態管理と取り消し線の動的表示

---

## Day7：Material DesignによるUI改善
- [x] `MaterialCardView` によるリストのカード化
- [x] `FloatingActionButton (FAB)` の導入
- [x] `ConstraintLayout` を活用した、キーボードに隠れないレスポシブレイアウト
- [x] タスク0件時の「空状態メッセージ」表示制御

---

## Day8 - Day9：本格データベース（Room）への移行
- [x] `Room Database` の導入と KSP 設定
- [x] `Entity`, `DAO`, `AppDatabase` (シングルトン) の実装
- [x] `Coroutines` (`lifecycleScope`, `Dispatchers.IO`) による非同期処理
- [x] `SharedPreferences` から `Room` への完全なデータ移行

---

## Day10：MVVMアーキテクチャの導入
- [x] `TaskRepository` によるデータアクセス層の分離
- [x] `TaskViewModel` によるビジネスロジックの集約
- [x] `TaskViewModelFactory` による引数付きViewModelの生成
- [x] `MainActivity` からのロジック排除（表示への専念）

---

## Day11：検索機能の追加
- [x] 検索用 `EditText` の配置
- [x] アダプター内でのフィルタリングロジック（`originalTasks` / `filteredTasks`）
- [x] `TextWatcher` によるリアルタイム検索の実装

---

## Day12：並び替え機能の実装
- [x] `Spinner` による並び替えUIの追加
- [x] 「作成順」「名前順」「完了状態順」のソートロジック実装
- [x] 検索と並び替えの統合 (`applyFilterAndSort`)

---

## Day13：リファクタリングとリアクティブUI
- [x] `LiveData` の導入によるデータの自動同期（監視）実装
- [x] `Gson` や古いコメント、未使用 `import` の完全削除
- [x] 全メッセージ・色のリソース化 (`strings.xml`, `colors.xml`)
- [x] ビルド不具合（KSPとKotlinバージョンの競合）の解消

学んだこと
* LiveDataによる「変更の自動検知」と画面更新の自動化
* ハードコードを避けることによるメンテナンス性の向上
* KSP/Kotlinなどの開発ツールの依存関係トラブルへの対処法

---

## Day14：プロジェクトを完成させる（次回予定）
- [ ] 最終動作確認
- [ ] プロジェクト成果物のまとめ（README作成等）

---

## 現時点での達成度
Day 1 から Day 13 まで完了。LiveDataの導入により、モダンなAndroid開発の標準的なスタイルが確立されました。
