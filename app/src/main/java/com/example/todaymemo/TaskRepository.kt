package com.example.todaymemo

/**
 * データの「調達」と「保存」を管理するクラス
 */
class TaskRepository(private val taskDao: TaskDao) {

    // すべてのタスクを取得
    fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }

    // タスクを追加
    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    // タスクを更新
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    // タスクを削除
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}