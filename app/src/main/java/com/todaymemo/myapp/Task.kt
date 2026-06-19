package com.todaymemo.myapp

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * タスク情報を保持するEntity（データベースのテーブル定義）
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    var isCompleted: Boolean = false
)