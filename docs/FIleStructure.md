# 📂 TodayMemo プロジェクト構造 (Day 11 終了時点)

MVVMアーキテクチャを採用し、リアルタイム検索機能を搭載した高度なToDoアプリの構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 依存関係（Room, KSP, Material, ViewModel）
│   └── src/main/
│       ├── AndroidManifest.xml   # アプリの基本情報・Activity登録
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt        # 【View】表示・検索入力の監視・ダイアログ
│       │   ├── TaskViewModel.kt       # 【ViewModel】データ管理とビジネスロジック
│       │   ├── TaskViewModelFactory.kt# ViewModel生成の支援
│       │   ├── TaskRepository.kt      # 【Repository】データアクセス層
│       │   ├── Task.kt                # 【Model/Entity】タスクデータの定義
│       │   ├── TaskDao.kt             # 【DAO】Room操作の定義
│       │   ├── AppDatabase.kt         # 【Database】DBインスタンス管理
│       │   └── TaskAdapter.kt         # RecyclerViewの制御・フィルタリング
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml  # メイン画面（検索窓＋入力欄＋リスト）
│           │   └── item_task.xml      # タスク1行分のレイアウト
│           └── values/
│               ├── colors.xml         # 配色定義
│               ├── strings.xml        # 文字列リソース
│               └── themes.xml         # Materialテーマ設定
└── gradle/
    ├── libs.versions.toml        # バージョン管理
    └── gradle.properties         # KSP動作設定
```

---

## 📄 各層の役割詳細 (MVVM)

### 1. View層 (Activity / Adapter / XML)
- **MainActivity.kt**: UIの更新とユーザー操作（検索入力、ボタンクリック）を検知。`TextWatcher` を使用してリアルタイム検索を実現。
- **TaskAdapter.kt**: `originalTasks`（全データ）と `filteredTasks`（表示用）の2つのリストを持ち、`filter` メソッドで表示内容を制御。
- **activity_main.xml**: 検索用の `EditText` を追加し、キーボードが表示されても操作しやすいレイアウト。

### 2. ViewModel層
- **TaskViewModel.kt**: リポジトリを介してデータベース操作を行い、画面の状態を管理。
- **TaskViewModelFactory.kt**: 依存関係（Repository）を注入したViewModelを生成。

### 3. Repository層
- **TaskRepository.kt**: Roomへのデータアクセスの唯一の窓口。

### 4. Model層 (Database)
- **Task.kt / TaskDao.kt / AppDatabase.kt**: データの永続化を担当。
