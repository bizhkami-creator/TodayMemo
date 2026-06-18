# 📋 TodayMemo 要件定義書 (Requirements)

## 1. アプリ概要
「今日やるべきこと」をシンプルに管理し、直感的に操作できるAndroid向けToDoアプリ。

## 2. 機能要件 (Functional Requirements)
- **タスク管理**: 追加、削除、完了チェック、データの永続化。
- **検索・抽出**: リアルタイムキーワード検索、未完了タスクの抽出。
- **並び替え**: 作成順、名前順、完了状態順の動的ソート。
- **通知システム**: スワイプで消えない常駐通知、ロック画面へのTodo一覧表示。
- **ストア対応**: Google Play ストアのガイドラインに準拠した構成。

## 3. UI/UX要件 (UI/UX Requirements)
- **Material Design**: CardView, FAB, レスポンシブな上部入力レイアウト。
- **視覚的フィードバック**: 打消し線、トースト通知、削除確認ダイアログ。
- **アクセシビリティ**: 全端末（Xperia/Xiaomi等）での文字視認性の確保。
- **ブランディング**: アダプティブアイコンの採用、ストア掲載情報の整備。

## 4. 技術スタック (Technical Stack)
- **アーキテクチャ**: MVVM + Repository Pattern
- **データベース**: Room (SQLite) / LiveData
- **非同期処理**: Kotlin Coroutines
- **ビルドツール**: Gradle (Version Catalog), KSP

## 5. 公開要件 (Release Requirements)
- **配布形式**: Android App Bundle (AAB)
- **署名**: Google Play アプリ署名
- **プライバシー**: Data Safety（データ収集なし）の申告。
