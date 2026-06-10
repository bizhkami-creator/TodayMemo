package com.example.todaymemo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * データベースの「1行」を表現するクラス
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,          // 自動で割り振られるID（出席番号）
    val title: String,        // タスク名
    var isCompleted: Boolean = false // 完了フラグ
) {
    // カスタムアダプターやログ用
    override fun toString(): String {
        return if (isCompleted) "[完了] $title" else title
    }
}