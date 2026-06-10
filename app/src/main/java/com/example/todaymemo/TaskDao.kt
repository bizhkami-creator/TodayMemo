package com.example.todaymemo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * データベースへの操作（追加・削除・取得など）を定義する窓口
 */
@Dao
interface TaskDao {

    // すべてのタスクをIDの降順（新しい順）で取得する
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): List<Task>

    // 新しいタスクを追加する
    @Insert
    fun insertTask(task: Task)

    // タスクの内容（完了フラグなど）を更新する
    @Update
    fun updateTask(task: Task)

    // 指定したタスクを削除する
    @Delete
    fun deleteTask(task: Task)
}