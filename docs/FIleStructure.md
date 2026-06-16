# 📂 TodayMemo プロジェクト構造 (Day 13 終了時点)

MVVMアーキテクチャに加え、LiveDataによるリアクティブUIと徹底したクリーンアップを実施した構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 依存関係の整理（不要なGsonを削除）
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt        # 【View】LiveDataの監視とUI更新に専念
│       │   ├── TaskViewModel.kt       # 【ViewModel】LiveDataで最新データを公開
│       │   ├── TaskViewModelFactory.kt
│       │   ├── TaskRepository.kt      # 【Repository】
│       │   ├── Task.kt                # 【Model/Entity】toString等の整理
│       │   ├── TaskDao.kt             # 【DAO】LiveDataを返すように進化
│       │   ├── AppDatabase.kt
│       │   └── TaskAdapter.kt         # 【Adapter】色のリソース対応
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml  # 文字列のリソース化対応
│           │   └── item_task.xml
│           └── values/
│               ├── colors.xml         # アプリ全体の共通色の定義
│               ├── strings.xml        # 全メッセージの外部化
│               └── themes.xml         # テーマ設定
└── gradle/
    ├── libs.versions.toml        # 不要なライブラリ定義の削除
    └── gradle.properties
```

---

## 📄 各層の役割詳細 (MVVM)

### 1. View層 (Activity / Adapter / XML)
- **MainActivity.kt**: `viewModel.allTasks.observe` により、データの変更を自動検知して画面を更新します。手動の再読み込みロジックを廃止しました。
- **TaskAdapter.kt**: 検索とソートを統合管理。色の指定などを `colors.xml` から取得するように修正しました。

### 2. ViewModel層
- **TaskViewModel.kt**: `LiveData` を通じて、リポジトリから取得したデータを不変な状態でViewに提供します。

### 3. Repository層 / Model層
- **TaskDao.kt**: 戻り値を `LiveData<List<Task>>` にすることで、Roomによる自動監視を有効化しました。
- **Task.kt**: データベースのテーブル定義。クリーンアップにより保守性を高めました。
