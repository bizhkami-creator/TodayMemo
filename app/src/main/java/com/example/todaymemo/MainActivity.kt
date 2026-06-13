package com.example.todaymemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val taskDao = AppDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(taskDao)
        val factory = TaskViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val textViewEmptyMessage = findViewById<TextView>(R.id.textViewEmptyMessage)

        // 1. アダプターを初期化 (最初は空のリスト)
        adapter = TaskAdapter(
            emptyList(),
            onStatusChanged = { updatedTask -> 
                viewModel.updateTask(updatedTask)
            },
            onItemLongClicked = { taskToDelete ->
                showDeleteDialog(taskToDelete, textViewEmptyMessage)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        // 2. 検索窓の文字入力を監視する
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 文字が入力されるたびにアダプターのフィルタを呼ぶ
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        loadTasks(textViewEmptyMessage)

        fabAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                viewModel.addTask(taskText) {
                    loadTasks(textViewEmptyMessage)
                    editTextTask.text.clear()
                    // 追加後に検索ワードをクリアして全件表示に戻す
                    editTextSearch.text.clear()
                }
            } else {
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadTasks(emptyView: TextView) {
        lifecycleScope.launch {
            val tasks = withContext(Dispatchers.IO) {
                AppDatabase.getDatabase(this@MainActivity).taskDao().getAllTasks()
            }
            // 【変更】リストを直接いじらず、アダプターの更新関数を呼ぶ
            adapter.updateData(tasks)
            updateEmptyMessageVisibility(tasks, emptyView)
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
                    loadTasks(emptyView)
                    Toast.makeText(this@MainActivity, "削除しました", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("いいえ", null)
            .show()
    }
}