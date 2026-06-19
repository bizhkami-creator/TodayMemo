package com.todaymemo.myapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 画面の「データ」と「状態」を管理する司令塔
 */
class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    // データベースの全タスク（自動更新されるリスト）
    val allTasks: LiveData<List<Task>> = repository.allTasks

    /**
     * 未完了タスクを取得（通知用などの一時的な取得に使う）
     */
    suspend fun getIncompleteTasks(): List<Task> {
        return repository.getIncompleteTasks()
    }

    /**
     * タスクを追加する
     */
    fun addTask(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newTask = Task(title = title)
            repository.insertTask(newTask)
        }
    }

    /**
     * チェック状態を更新する
     */
    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    /**
     * タスクを削除する
     */
    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}