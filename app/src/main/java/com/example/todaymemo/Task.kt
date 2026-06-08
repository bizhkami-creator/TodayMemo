package com.example.todaymemo

/**
 * タスクの情報をまとめて持つためのクラス
 * @param title タスクの名前
 * @param isCompleted 完了しているかどうか（true: 完了, false: 未完了）
 */
data class Task(
    val title: String,
    var isCompleted: Boolean = false // 最初は必ず「未完了」にする
) {
    // ListViewに表示される文字を定義する（これがないと変な英数字が表示されます）
    override fun toString(): String {
        return if (isCompleted) "[完了] $title" else title
    }
}