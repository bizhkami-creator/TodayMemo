# 今日やることメモアプリ 開発計画（2週間）

## 目的
Androidアプリ開発の基礎を網羅的に習得する。

学習対象
* Kotlin / Coroutines
* XML Layout (ConstraintLayout, CardView, Material Design)
* RecyclerView (Adapter, ViewHolder)
* Room Database (Entity, DAO, Singleton)
* MVVM Architecture (ViewModel, Repository, Factory)
* Git/GitHub / AI活用開発フロー (Vibe Coding)

---

## Day1 - Day2：プロジェクト開始と基本UI
- [x] Android Studioプロジェクト作成
- [x] タイトル表示 (`TextView`)
- [x] 入力欄 (`EditText`) と追加ボタン (`Button`) の配置
- [x] ボタン押下でメモリ上のリスト (`ArrayList<String>`) に追加
- [x] 初期の `ListView` による一覧表示実装

学んだこと
* ActivityとXMLレイアウトの紐付け (`setContentView`, `findViewById`)
* 基本的なイベントリスナー (`setOnClickListener`)

---

## Day3 - Day4：データの永続化基礎
- [x] `SharedPreferences` の学習と導入
- [x] `Gson` ライブラリによるオブジェクト ⇄ JSON変換の実装
- [x] 起動時の自動読み込みと更新時の自動保存

学んだこと
* メモリ（揮発性）とストレージ（非揮発性）の違い
* データのシリアライズ（JSON化）による複雑なデータの保存方法

---

## Day5 - Day6：モダンなリスト表示への進化
- [x] `Task` クラス（Entity）の作成
- [x] `ListView` から `RecyclerView` に完全移行
- [x] 1行分のカスタムレイアウト (`item_task.xml`) 作成
- [x] `ViewHolder` パターンの実装による描画の最適化
- [x] 完了チェックボックスの状態管理と取り消し線の動的表示

学んだこと
* Viewの再利用（Recycle）によるパフォーマンス向上
* アダプターパターンの深い理解
* ビット演算による `Paint` フラグの操作

---

## Day7：Material DesignによるUI改善
- [x] `MaterialCardView` によるリストのカード化
- [x] `FloatingActionButton (FAB)` の導入
- [x] 配色（`colors.xml`）とタイポグラフィの調整
- [x] `ConstraintLayout` を活用した、キーボードに隠れないレスポンシブレイアウト
- [x] タスク0件時の「空状態メッセージ」表示制御

学んだこと
* Material Components ライブラリの使い方
* ユーザー体験（UX）を考慮したレイアウト設計

---

## Day8 - Day9：本格データベース（Room）への移行
- [x] `Room Database` の導入と KSP 設定
- [x] `Entity`, `DAO`, `AppDatabase` (シングルトン) の実装
- [x] `Coroutines` (`lifecycleScope`, `Dispatchers.IO`) による非同期処理
- [x] `SharedPreferences` から `Room` への完全なデータ移行

学んだこと
* リレーショナルデータベースの基礎（SQL, 主キー, CRUD）
* メインスレッドを止めない非同期プログラミングの重要性

---

## Day10：MVVMアーキテクチャの導入
- [x] `TaskRepository` によるデータアクセス層の分離
- [x] `TaskViewModel` によるビジネスロジックの集約
- [x] `TaskViewModelFactory` による引数付きViewModelの生成
- [x] `MainActivity` からのロジック排除（表示への専念）

学んだこと
* MVVM（Model-View-ViewModel）による関心の分離
* ViewModelのライフサイクル（画面回転への耐性）
* ボイラープレートコード（Factory）の必要性

---

## Day11：LiveDataによるリアクティブUI（次回予定）
- [ ] `LiveData` または `StateFlow` の導入
- [ ] データベース変更時の自動画面更新
- [ ] `loadTasks` の手動呼び出しを廃止

---

## 現時点での達成度
Day 1 から Day 10 まで、計画通り完了しました。アプリは「単に動くもの」から「プロ仕様の設計を持つモダンなアプリ」へと進化しています。
