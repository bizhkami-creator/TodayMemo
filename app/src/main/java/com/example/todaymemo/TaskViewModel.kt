package com.example.todaymemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 画面の「データ」と「状態」を管理する司令塔
 */
class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    /**
     * タスクを追加する
     */
    fun addTask(title: String, onComplete: () -> Unit) {
        // viewModelScopeを使うと、ViewModelが消える時に自動でコルーチンも止めてくれる
        viewModelScope.launch(Dispatchers.IO) {
            val newTask = Task(title = title)
            repository.insertTask(newTask)
            
            // 処理が終わったら、メインスレッドで合図を送る
            launch(Dispatchers.Main) {
                onComplete()
            }
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
    fun deleteTask(task: Task, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
            
            launch(Dispatchers.Main) {
                onComplete()
            }
        }
    }
}