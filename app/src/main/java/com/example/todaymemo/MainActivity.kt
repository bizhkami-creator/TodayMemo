package com.example.todaymemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        val taskList = loadTasks()

        // アダプター作成時に「保存処理」を渡すようにした
        val adapter = TaskAdapter(this, taskList) {
            saveTasks(taskList)
        }
        listViewTasks.adapter = adapter

        buttonAdd.setOnClickListener {
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

        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()
            saveTasks(taskList)
            Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
            true
        }
    }

    /**
     * 保存処理 (Gsonを使ってリストを丸ごとJSON文字列に変換)
     */
    private fun saveTasks(taskList: List<Task>) {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val gson = Gson()
        // リストを丸ごと一つの文字列（JSON）にする
        val json = gson.toJson(taskList)
        
        prefs.edit().putString("tasks_json", json).apply()
    }

    /**
     * 読み込み処理 (JSON文字列をTaskのリストに戻す)
     */
    private fun loadTasks(): MutableList<Task> {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val json = prefs.getString("tasks_json", null) ?: return mutableListOf()
        
        val gson = Gson()
        // JSON文字列を List<Task> 型に変換するための魔法の呪文
        val type = object : TypeToken<List<Task>>() {}.type
        return gson.fromJson(json, type)
    }
}