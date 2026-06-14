# 📂 TodayMemo プロジェクト構造 (Day 12 終了時点)

MVVMアーキテクチャを採用し、リアルタイム検索と動的並び替え機能を備えた高度な構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 依存関係（Room, Material, ViewModel等）
│   └── src/main/
│       ├── AndroidManifest.xml   # アプリの基本設定
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt        # 【View】UI操作、検索・並び替えの監視
│       │   ├── TaskViewModel.kt       # 【ViewModel】ロジックと状態管理
│       │   ├── TaskViewModelFactory.kt# ViewModel生成
│       │   ├── TaskRepository.kt      # 【Repository】データアクセス
│       │   ├── Task.kt                # 【Model/Entity】データ定義
│       │   ├── TaskDao.kt             # 【DAO】Room操作定義
│       │   ├── AppDatabase.kt         # 【Database】DB本体
│       │   └── TaskAdapter.kt         # RecyclerView制御、検索・ソートロジック
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml  # メイン画面（Spinner/EditText等配置）
│           │   └── item_task.xml      # タスク1行分のレイアウト
│           ├── menu/
│           │   └── sort_menu.xml      # （予備）並び替えメニュー定義
│           └── values/
│               ├── colors.xml         # 色定義
│               ├── strings.xml        # 並び替え選択肢等の文字列
│               └── themes.xml         # NoActionBar設定
└── gradle/
    ├── libs.versions.toml        # バージョン管理
    └── gradle.properties         # KSP動作設定
```

---

## 📄 各層の役割詳細 (MVVM)

### 1. View層 (Activity / Adapter / XML)
- **MainActivity.kt**: UI操作の受付。`addTextChangedListener`（検索）と `onItemSelectedListener`（並び替え）を使用してアダプターに命令を出します。
- **TaskAdapter.kt**: 検索フィルタと並び替えロジックの統合。`applyFilterAndSort()` により、2つの機能の整合性を保ちます。
- **activity_main.xml**: `Spinner` を追加し、検索と並び替えを同時に操作できるUIを構築。

### 2. ViewModel層
- **TaskViewModel.kt**: 非同期でのデータベース操作（追加・更新・削除）を管理。

### 3. Repository層 / Model層
- データの永続化と、ビジネスルールに基づいたデータ提供を担当。
