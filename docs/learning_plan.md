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

### 目標

Todoアプリらしい完了チェック機能を追加する

### 実施内容

* [ ] CheckBox追加
* [ ] タスク完了機能
* [ ] 完了状態の保存
* [ ] 完了タスクの見た目変更

### 学ぶこと

* CheckBox
* データモデル（Taskクラス）
* Boolean型
* オブジェクト管理

### Git Commit

* Add task completion feature

---

## Day6

### 目標

ListViewからRecyclerViewへ移行する

### 実施内容

* [ ] RecyclerView導入
* [ ] Adapter作成
* [ ] ViewHolder作成
* [ ] 既存機能の移植

### 学ぶこと

* RecyclerView
* Adapter
* ViewHolder
* Android標準UI設計

### Git Commit

* Migrate to RecyclerView

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
