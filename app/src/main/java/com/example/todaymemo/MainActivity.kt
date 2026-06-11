package com.example.todaymemo

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private val taskList = mutableListOf<Task>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. ViewModelの準備 (製造機を通して作成)
        val taskDao = AppDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(taskDao)
        val factory = TaskViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val textViewEmptyMessage = findViewById<TextView>(R.id.textViewEmptyMessage)

        // 2. アダプターの設定 (イベントをViewModelに伝える)
        adapter = TaskAdapter(
            taskList,
            onStatusChanged = { updatedTask -> 
                viewModel.updateTask(updatedTask) // ViewModelにお願い
            },
            onItemLongClicked = { taskToDelete ->
                showDeleteDialog(taskToDelete, textViewEmptyMessage)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        // 3. 起動時にタスクを読み込む
        loadTasks(textViewEmptyMessage)

        // 4. 追加ボタンの動作
        fabAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                viewModel.addTask(taskText) {
                    // 追加が終わった後の処理
                    loadTasks(textViewEmptyMessage)
                    editTextTask.text.clear()
                }
            } else {
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * データの読み込み (MVVM化の暫定対応：後でLiveDataでさらに改善できます)
     */
    private fun loadTasks(emptyView: TextView) {
        // まだ暫定的にActivityで呼び出していますが、処理自体はViewModelに移せます
        lifecycleScope.launch {
            val tasks = withContext(Dispatchers.IO) {
                // ここも本来は ViewModel に持たせるのがベストです
                AppDatabase.getDatabase(this@MainActivity).taskDao().getAllTasks()
            }
            taskList.clear()
            taskList.addAll(tasks)
            adapter.notifyDataSetChanged()
            updateEmptyMessageVisibility(taskList, emptyView)
        }
    }

    private fun updateEmptyMessageVisibility(taskList: List<Task>, emptyView: View) {
        emptyView.visibility = if (taskList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showDeleteDialog(task: Task, emptyView: TextView) {
        AlertDialog.Builder(this)
            .setTitle("削除の確認")
            .setMessage("「${task.title}」を削除しますか？")
            .setPositiveButton("はい") { _, _ ->
                viewModel.deleteTask(task) {
                    loadTasks(emptyView) // 削除が終わったら再読み込み
                    Toast.makeText(this@MainActivity, "削除しました", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("いいえ", null)
            .show()
    }
}