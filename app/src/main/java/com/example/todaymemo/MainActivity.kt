package com.example.todaymemo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. ViewModelと依存関係のセットアップ
        val taskDao = AppDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(taskDao)
        val factory = TaskViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        // 2. UI部品の取得
        val spinnerSort = findViewById<Spinner>(R.id.spinnerSort)
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val textViewEmptyMessage = findViewById<TextView>(R.id.textViewEmptyMessage)

        // 3. アダプターの初期化
        adapter = TaskAdapter(
            emptyList(),
            onStatusChanged = { updatedTask -> 
                viewModel.updateTask(updatedTask)
            },
            onItemLongClicked = { taskToDelete ->
                showDeleteDialog(taskToDelete)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        // 4. LiveDataの監視（データの自動更新を実現）
        viewModel.allTasks.observe(this) { tasks ->
            // DBが更新されたらここが呼ばれる
            adapter.updateData(tasks)
            updateEmptyMessageVisibility(tasks, textViewEmptyMessage)
        }

        // 5. リスナーの設定（検索・並び替え）
        spinnerSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.sortTasks(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 6. タスク追加
        fabAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                viewModel.addTask(taskText)
                editTextTask.text.clear()
                editTextSearch.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.msg_input_task), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateEmptyMessageVisibility(taskList: List<Task>, emptyView: View) {
        emptyView.visibility = if (taskList.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun showDeleteDialog(task: Task) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_delete_title))
            .setMessage(getString(R.string.dialog_delete_msg, task.title))
            .setPositiveButton(getString(R.string.btn_yes)) { _, _ ->
                viewModel.deleteTask(task)
                Toast.makeText(this, getString(R.string.msg_deleted), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.btn_no), null)
            .show()
    }
}