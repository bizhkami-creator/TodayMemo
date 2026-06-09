# 📂 TodayMemo プロジェクト構造 (Day 7 終了時点)

Material Designを採用し、RecyclerViewとCardViewを組み合わせたモダンな構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 依存関係（Gson, Material, RecyclerView）の定義
│   └── src/main/
│       ├── AndroidManifest.xml   # アプリの基本情報・Activity登録
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt   # 全体の司令塔（保存、空状態の制御、ダイアログ）
│       │   ├── Task.kt           # データモデル（名前、完了フラグ）
│       │   └── TaskAdapter.kt    # リストの表示職人（CardView、取り消し線）
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml  # 画面全体の配置（ConstraintLayout）
│           │   └── item_task.xml      # タスク1行分のデザイン（MaterialCardView）
│           └── values/
│               ├── colors.xml    # アプリの配色（紫、薄グレーなど）
│               ├── strings.xml   # 文字列（アプリ名など）
│               └── themes.xml    # MaterialComponentsのテーマ設定
└── gradle/
    └── libs.versions.toml        # ライブラリのバージョン管理
```

---

## 📄 各ファイルの主要な役割

### 1. ロジック層 (Kotlin)
- **MainActivity.kt**
    - `SharedPreferences` と `Gson` を使ってタスクを保存・復元。
    - タスクが0件のときに「今日やることはありません！」と表示する制御。
- **TaskAdapter.kt**
    - データの変更を検知して、CardViewの中身（テキストやチェック）を更新。
    - 完了したタスクに「取り消し線」を引くなどの見た目の制御。

### 2. デザイン層 (XML)
- **activity_main.xml**
    - キーボードが表示されても入力欄が隠れないよう、上部に操作系をまとめたレイアウト。
- **item_task.xml**
    - 角丸と影をつけた「カード形式」のデザイン。

### 3. 設定層
- **themes.xml**
    - `MaterialCardView` を動かすために必須の「MaterialComponents」テーマを適用。
