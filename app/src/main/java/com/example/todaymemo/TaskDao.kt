package com.example.todaymemo

import androidx.lifecycle.LiveData
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

    // すべてのタスクを取得（LiveDataを返すように変更：自動更新の魔法！）
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}