# 📂 TodayMemo プロジェクト構造 (Day 10 終了時点)

MVVMアーキテクチャを採用し、役割分担を明確にした保守性の高い構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 依存関係（Room, KSP, Material, ViewModel）
│   └── src/main/
│       ├── AndroidManifest.xml   # アプリの基本情報・Activity登録
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt        # 【View】画面表示と操作の受付（ダイアログ等）
│       │   ├── TaskViewModel.kt       # 【ViewModel】画面データの保持とビジネスロジック
│       │   ├── TaskViewModelFactory.kt# ViewModelの生成を支援する工場クラス
│       │   ├── TaskRepository.kt      # 【Repository】データ取得元の切り替え・管理
│       │   ├── Task.kt                # 【Model/Entity】タスクデータの定義
│       │   ├── TaskDao.kt             # 【DAO】Room操作の定義
│       │   ├── AppDatabase.kt         # 【Database】DBインスタンスの管理
│       │   └── TaskAdapter.kt         # RecyclerViewの制御
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml  # メイン画面のレイアウト
│           │   └── item_task.xml      # タスク1行分のレイアウト
│           └── values/
│               ├── colors.xml         # 色の定義
│               ├── strings.xml        # 文字列リソース
│               └── themes.xml         # Materialテーマ設定
└── gradle/
    ├── libs.versions.toml        # バージョン管理
    └── gradle.properties         # KSP動作設定
```

---

## 📄 各層の役割詳細 (MVVM)

### 1. View層 (Activity / Adapter / XML)
- **MainActivity.kt**: UIの更新とユーザー操作の検知に特化。データベースには直接触れず、ViewModelに依頼します。
- **TaskAdapter.kt**: `Task` オブジェクトを表示用に変換し、ViewHolderへ橋渡しします。

### 2. ViewModel層
- **TaskViewModel.kt**: 画面が必要なデータを保持し、ビジネスロジック（追加・更新・削除の命令）を管理します。Activityの再生成に影響を受けません。
- **TaskViewModelFactory.kt**: `TaskRepository` を `TaskViewModel` に正しく渡してインスタンス化するための部品です。

### 3. Repository層
- **TaskRepository.kt**: ViewModelとデータの具体的な保存先（Room）の仲介役です。将来的にネットワーク通信などを追加する場合もここが窓口になります。

### 4. Model層 (Database)
- **Task.kt / TaskDao.kt / AppDatabase.kt**: Room Databaseを利用したデータの永続化を担当します。
