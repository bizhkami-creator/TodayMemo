TodayMemo/
├── app/
│   ├── build.gradle.kts          // アプリの設計図（GsonやAppCompatの追加）
│   └── src/main/
│       ├── AndroidManifest.xml   // アプリ全体の構成・設定ファイル
│       ├── java/com/example/todaymemo/
│       │   ├── MainActivity.kt   // メイン画面の処理（保存・読み込み・追加・削除）
│       │   ├── Task.kt           // タスクデータの型（名前と完了フラグ）
│       │   └── TaskAdapter.kt    // タスク一覧の表示制御（チェックボックス・取り消し線）
│       └── res/
│           ├── layout/
│           │   ├── activity_main.xml    // 画面全体のデザイン
│           │   └── list_item_task.xml   // タスク1行分のデザイン（カスタム）
│           └── values/
│               ├── colors.xml    // 色の定義
│               ├── strings.xml   // 文字列の定義
│               └── themes.xml    // アプリのテーマ設定（AppCompatActivity用）
└── gradle/
    └── libs.versions.toml        // ライブラリのバージョン管理（Gsonなど）