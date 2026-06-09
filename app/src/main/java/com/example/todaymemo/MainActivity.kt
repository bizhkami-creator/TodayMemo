package com.example.todaymemo

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)

        val taskList = loadTasks()

        val adapter = TaskAdapter(
            taskList,
            onStatusChanged = { 
                saveTasks(taskList)
            },
            onItemLongClicked = { position ->
                showDeleteDialog(position, taskList, recyclerViewTasks.adapter!!)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        // FABが押された時の動作
        fabAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                val newTask = Task(title = taskText)
                taskList.add(newTask)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
                saveTasks(taskList)
            } else {
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDeleteDialog(position: Int, taskList: MutableList<Task>, adapter: RecyclerView.Adapter<*>) {
        AlertDialog.Builder(this)
            .setTitle("削除の確認")
            .setMessage("このタスクを削除しますか？")
            .setPositiveButton("はい") { _, _ ->
                taskList.removeAt(position)
                adapter.notifyDataSetChanged()
                saveTasks(taskList)
                Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("いいえ", null)
            .show()
    }

    private fun saveTasks(taskList: List<Task>) {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val json = Gson().toJson(taskList)
        prefs.edit().putString("tasks_json", json).apply()
    }

    private fun loadTasks(): MutableList<Task> {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val json = prefs.getString("tasks_json", null) ?: return mutableListOf()
        val type = object : TypeToken<List<Task>>() {}.type
        return Gson().fromJson(json, type)
    }
}