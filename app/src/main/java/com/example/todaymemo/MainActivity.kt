package com.example.todaymemo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 部品の取得
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        // 【読み込み】アプリ起動時に保存されたデータを取得
        val taskList = loadTasks()

        // アダプターの設定
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listViewTasks.adapter = adapter

        // 「追加」ボタンの動作
        buttonAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()

            if (taskText.isNotEmpty()) {
                taskList.add(taskText)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()

                // 【保存】追加したらデータを保存
                saveTasks(taskList)
            } else {
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }

        // 【削除】タスクを「長押し」した時に削除する機能
        listViewTasks.setOnItemLongClickListener { _, _, position, _ ->
            // リストから削除
            taskList.removeAt(position)
            adapter.notifyDataSetChanged()

            // 【保存】削除した後の状態を保存
            saveTasks(taskList)

            Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
            true // イベントをここで終了させるという意味
        }
    }

    /**
     * データを保存する関数（引き出しにしまう）
     */
    private fun saveTasks(taskList: List<String>) {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val editor = prefs.edit()
        // StringのリストをSet形式に変換して保存（順番は保証されませんが、一番簡単な方法です）
        editor.putStringSet("tasks", taskList.toSet())
        editor.apply()
    }

    /**
     * データを読み込む関数（引き出しから出す）
     */
    private fun loadTasks(): MutableList<String> {
        val prefs = getSharedPreferences("TodayMemoPrefs", MODE_PRIVATE)
        val set = prefs.getStringSet("tasks", emptySet())
        // 保存されていたらリストに変換して返し、なければ空のリストを返す
        return set?.toMutableList() ?: mutableListOf()
    }
}