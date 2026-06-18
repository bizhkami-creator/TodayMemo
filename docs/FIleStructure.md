# 📂 TodayMemo プロジェクト構造 (Day 17 終了時点)

リリース準備を整えた、商用レベルのToDoアプリ構成です。

```text
TodayMemo/
├── app/
│   ├── build.gradle.kts          # 署名設定・AAB書き出し定義
│   └── src/main/
│       ├── AndroidManifest.xml   # 権限宣言、アプリ名称、アイコンの登録
│       ├── java/com/example/todaymemo/ # MVVM + Repository構成のソースコード
│       └── res/
│           ├── mipmap-*          # 生成されたアプリアイコン画像
│           ├── values/
│           │   ├── strings.xml   # ストア用文言を含む文字列リソース
│           │   └── themes.xml    # 視認性改善後のカスタムテーマ
│           └── layout/           # 没入型レイアウト
├── docs/                         # プロジェクトドキュメント
└── img/                          # README用スクリーンショット
```

---

## 📄 各層の役割詳細

### 1. View / UI
- **MainActivity / Adapter**: ユーザー操作の窓口。
- **Adaptive Icon**: 機種ごとに形が変わるモダンなアイコン。

### 2. Logic / Data
- **ViewModel / Repository / Room**: データの流れを整理し、安定した動作を提供。

### 3. Release Assets
- **Store Assets**: Google Play Console にアップロードする掲載文と画像。
