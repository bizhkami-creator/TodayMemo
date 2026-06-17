# 今日やることメモアプリ 開発計画（2週間+α）

## 目的
Androidアプリ開発の基礎から通知等の実用機能までを網羅的に習得する。

学習対象
* Kotlin / Coroutines
* XML Layout (ConstraintLayout, CardView, Material Design)
* RecyclerView (Adapter, ViewHolder)
* Room Database (Entity, DAO, Singleton)
* MVVM Architecture (ViewModel, Repository, Factory, LiveData)
* Notifications (Channel, Ongoing, PendingIntent)
* Theme & Style (Xperia UI Visibility Fix)
* Git/GitHub / AI活用開発フロー (Vibe Coding)

---

## Day1 - Day14：基礎から完成まで
- [x] プロジェクト開始、データの永続化（SharedPreferences ➔ Room）
- [x] UI/UX改善（Material Design, CardView, FAB, 検索, 並び替え）
- [x] 設計（MVVM導入, LiveDataによる自動同期）
- [x] リリース準備（README作成, ドキュメント整理）

---

## Day15：予備日・追加検討
- [x] 次のステップ「通知機能」の構想

---

## Day16：通知機能の実装とUIブラッシュアップ
- [x] 通知チャンネル（Notification Channel）の作成
- [x] Android 13以降の通知権限（POST_NOTIFICATIONS）対応
- [x] 常駐通知（Ongoing Notification）の実装
- [x] データの変更に合わせて通知内容を自動更新
- [x] Xperia等の端末での「白抜き文字」視認性問題の解決

学んだこと
* Android通知システムの仕組み（Channel, Builder, Manager）
* 各種メーカー（Xiaomi, Sony等）による通知制限と、それに対する実装上の工夫
* `setOngoing(true)` によるスワイプで消えない通知の作り方
* デバイスのダークモードやテーマ設定に左右されない「文字色・ヒント色」の明示的指定（色のハードコート回避とテーマ制御）

---

## 現時点での達成度
Day 1 から Day 16 まで完了。通知機能と、あらゆる端末で使いやすいUIの最適化が完了しました。
