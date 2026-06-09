# 今日やることメモアプリ 開発計画（2週間）

## 目的

Androidアプリ開発の基礎を学ぶ。

学習対象

* Kotlin
* Jetpack Compose
* ViewModel
* Room Database
* Git/GitHub
* AI活用開発フロー

---

## Day1 環境準備

- [x] Android Studioプロジェクト作成
- [x] GitHubリポジトリ作成
- [x] 初回Push
- [x] フォルダ構成確認

学んだこと
- MainActivity.kt が処理担当
- activity_main.xml が画面担当


---

## Day2 Compose基礎

- [x] タイトル表示
- [x] 入力欄（EditText）追加
- [x] 追加ボタン作成
- [x] タスク一覧表示
- [x] ボタン押下で一覧に追加
- [x] VSCでのGit管理方法を理解

学んだこと
- EditTextで文字入力を受け取れる
- Buttonにクリックイベントを設定できる
- ListView（またはRecyclerView）にデータを表示できる
- KotlinコードとXMLレイアウトが連携して動く

Git Commit
- Add basic todo input screen


---

## Day3 一覧表示

### 作業

* List作成
* LazyColumn表示

### 成果

```text
□ 月次決算
□ メール返信
```

### 学習内容

* List
* LazyColumn

---

## Day4

- [x] SharedPreferences学習
- [x] タスク保存
- [x] タスク読み込み
- [x] アプリ再起動後も保持

学んだこと
- ArrayListはメモリ上の一時データ
- SharedPreferencesは端末内保存
- 起動時に読み込みが必要
- データ変更時に保存が必要
- 
---

## Day5

### 実施内容
- [x] Taskクラスを作成
- [x] ArrayList<String> から ArrayList<Task> に変更
- [x] CheckBoxを表示
- [x] 完了状態 completed を true / false で管理
- [x] 完了状態をSharedPreferencesに保存
- [x] 完了済みタスクに取り消し線を表示
- [x] 完了済みタスクの文字色を薄くした

### 学んだこと
- data classで複数の情報をまとめて管理できる
- Booleanで完了/未完了を表せる
- CheckBoxで状態を切り替えられる
- 文字列だけでなくオブジェクトを保存するには工夫が必要
- Adapterで一覧の見た目を制御できる

### Git履歴
Commit:
Add task completion feature

### 次回やること（Day6）
- ListViewからRecyclerViewへ移行
- AdapterとViewHolderを学ぶ

---

## Day6

### 目標
ListViewからRecyclerViewへ移行し、Android標準的な一覧表示を学ぶ

### 実施内容
- [x] RecyclerViewを導入
- [x] item_task.xmlを作成
- [x] TaskAdapter.ktを作成
- [x] ViewHolderを実装
- [x] RecyclerViewとAdapterを接続
- [x] タスク追加機能を移植
- [x] CheckBox機能を移植
- [x] 完了状態の保存機能を移植
- [x] 完了済みタスクの見た目変更を移植
- [x] 長押し削除機能を移植
- [x] SharedPreferences保存との連携確認

### 学んだこと

#### RecyclerView
- Androidで標準的に使われる一覧表示コンポーネント
- ListViewより柔軟で高速
- 大量データでも効率よく表示できる

#### Adapter
- データと画面表示をつなぐ役割
- TaskデータをRecyclerViewへ表示する
- データ変更時は `notifyDataSetChanged()` などで更新する

#### ViewHolder
- CheckBoxやTextViewなどのViewを保持する仕組み
- `findViewById()` の呼び出し回数を減らし高速化できる
- RecyclerViewのパフォーマンス向上に重要

#### 責務分離
- TaskAdapter
  - 画面表示
  - CheckBox状態変更
  - 長押しイベント通知
- MainActivity
  - データ管理
  - SharedPreferences保存
  - 削除ダイアログ表示

#### リファクタリング
- 機能を変えずに内部構造を改善すること
- 今回は見た目は同じままListViewからRecyclerViewへ変更した

### 困ったこと
- RecyclerViewとAdapterの関係が最初理解しづらかった
- ViewHolderの役割が分かりにくかった
- ListView時代のコードをどこへ移植するか迷った

### 動作確認
- [x] タスク追加
- [x] タスク削除
- [x] チェック状態変更
- [x] 完了タスクの取り消し線
- [x] SharedPreferences保存
- [x] アプリ再起動後の復元

### Git履歴

Commit:
Migrate Todo app from ListView to RecyclerView

---

## Day7

### 目標

UIを改善する

### 実施内容

* [ ] Material Design導入
* [ ] ボタンデザイン改善
* [ ] 余白調整
* [ ] アイコン追加

### 学ぶこと

* Material Design
* Layout設計
* UI/UX基礎

### Git Commit

* Improve UI design

---

## Day8

### 目標

Room Databaseを学ぶ

### 実施内容

* [ ] Room概要理解
* [ ] Entity作成
* [ ] DAO作成
* [ ] Database作成

### 学ぶこと

* SQLite
* Room
* Entity
* DAO

### Git Commit

* Add Room Database setup

---

## Day9

### 目標

SharedPreferencesからRoomへ移行する

### 実施内容

* [ ] タスク保存をRoom化
* [ ] タスク読込をRoom化
* [ ] 動作確認

### 学ぶこと

* CRUD
* 非同期処理
* 永続化設計

### Git Commit

* Migrate persistence to Room

---

## Day10

### 目標

アーキテクチャを学ぶ

### 実施内容

* [ ] Repository作成
* [ ] ViewModel導入
* [ ] データ管理整理

### 学ぶこと

* MVVM
* ViewModel
* Repository Pattern

### Git Commit

* Introduce MVVM architecture

---

## Day11

### 目標

検索機能を追加する

### 実施内容

* [ ] 検索欄追加
* [ ] リアルタイム検索
* [ ] フィルタリング

### 学ぶこと

* SearchView
* List Filtering

### Git Commit

* Add search functionality

---

## Day12

### 目標

並び替え機能を追加する

### 実施内容

* [ ] 作成順
* [ ] 完了順
* [ ] 名前順

### 学ぶこと

* Comparator
* ソート処理

### Git Commit

* Add sorting options

---

## Day13

### 目標

リファクタリングを行う

### 実施内容

* [ ] 不要コード削除
* [ ] コメント整理
* [ ] 命名統一
* [ ] フォルダ構成整理

### 学ぶこと

* リファクタリング
* 保守性向上

### Git Commit

* Refactor project structure

---

## Day14

### 目標

プロジェクトを完成させる

### 実施内容

* [ ] 最終テスト
* [ ] README作成
* [ ] スクリーンショット取得
* [ ] GitHub整理

### 学ぶこと

* リリース準備
* ドキュメント作成

### Git Commit

* Release v1.0

---

## このTodoアプリで習得する技術

* Kotlin基礎
* Activity
* Layout XML
* Event処理
* ListView
* RecyclerView
* SharedPreferences
* Room Database
* MVVM
* Git
* GitHub
* Material Design
* Android Studio
* Geminiを使ったVibe Coding
