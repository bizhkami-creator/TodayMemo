package com.example.todaymemo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var taskDao: TaskDao
    private val taskList = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(this)
        taskDao = db.taskDao()

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val textViewEmptyMessage = findViewById<TextView>(R.id.textViewEmptyMessage)

        adapter = TaskAdapter(
            taskList,
            onStatusChanged = { updatedTask -> 
                updateTaskInRoom(updatedTask)
            },
            onItemLongClicked = { taskToDelete ->
                // 【Room移行ポイント】タスクを渡してダイアログを表示
                showDeleteDialog(taskToDelete, textViewEmptyMessage)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        loadTasksFromRoom(textViewEmptyMessage)

        fabAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                val newTask = Task(title = taskText)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        taskDao.insertTask(newTask)
                    }
                    loadTasksFromRoom(textViewEmptyMessage)
                    editTextTask.text.clear()
                }
            } else {
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateTaskInRoom(task: Task) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                taskDao.updateTask(task)
            }
        }
    }

    private fun loadTasksFromRoom(emptyView: TextView) {
        lifecycleScope.launch {
            val tasksFromDb = withContext(Dispatchers.IO) {
                taskDao.getAllTasks()
            }
            taskList.clear()
            taskList.addAll(tasksFromDb)
            adapter.notifyDataSetChanged()
            updateEmptyMessageVisibility(taskList, emptyView)
        }
    }

    private fun updateEmptyMessageVisibility(taskList: List<Task>, emptyView: View) {
        emptyView.visibility = if (taskList.isEmpty()) View.VISIBLE else View.GONE
    }

    /**
     * Roomからタスクを削除する
     */
    private fun showDeleteDialog(task: Task, emptyView: TextView) {
        AlertDialog.Builder(this)
            .setTitle("削除の確認")
            .setMessage("「${task.title}」を削除しますか？")
            .setPositiveButton("はい") { _, _ ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        // データベースから削除
                        taskDao.deleteTask(task)
                    }
                    // 削除が終わったら一覧を再読み込み
                    loadTasksFromRoom(emptyView)
                    Toast.makeText(this@MainActivity, "削除しました", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("いいえ", null)
            .show()
    }
}