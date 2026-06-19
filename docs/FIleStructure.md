# 📂 TodayMemo プロジェクト構造 (Day 18 終了時点)

Playストア審査中の、正式パッケージ名に変更済みの構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # applicationId: com.todaymemo.myapp, versionCode: 2
│   └── src/main/
│       ├── AndroidManifest.xml   # パーミッション、アプリ名、新パッケージ対応
│       ├── java/com/todaymemo/myapp/ # 正式パッケージ名配下のソースコード
│       │   ├── ui/theme/         # Compose用テーマ（プレースホルダー）
│       │   ├── MainActivity.kt
│       │   ├── Task.kt
│       │   ├── TaskDao.kt
│       │   ├── AppDatabase.kt
│       │   ├── TaskAdapter.kt
│       │   ├── TaskViewModel.kt
│       │   ├── TaskRepository.kt
│       │   └── TaskViewModelFactory.kt
│       └── res/
│           ├── mipmap-*          # アプリアイコン
│           ├── values/
│           │   ├── strings.xml   # ストア掲載文・多言語対応準備
│           │   └── themes.xml    # NoActionBar、文字視認性向上テーマ
│           └── layout/           # XMLレイアウト
├── docs/                         # プロジェクトドキュメント
├── img/                          # README・ストア用画像
└── FileStructure.md              # このファイル
```

---

## 📄 各層の役割詳細

### 1. View / UI
- **MainActivity / Adapter**: ユーザー操作の受付と表示。
- **Themes.xml**: ダークモードや特定端末での視認性問題を解決したプロ仕様の設定。

### 2. Logic / Data
- **ViewModel / Repository**: MVVMアーキテクチャによるクリーンなデータフロー。
- **Room (TaskDao/AppDatabase)**: 高速かつ安全なローカル保存。

### 3. Release Information
- **Application ID**: `com.todaymemo.myapp`
- **Version**: 1.1 (Code 2)
- **Status**: Google Play Store 審査中
