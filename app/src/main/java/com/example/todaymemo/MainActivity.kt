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
        // XMLのレイアウトを画面にセット
        setContentView(R.layout.activity_main)

        // XMLの部品をIDで取得
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        // タスクを保存するリスト（アプリを閉じると消えます）
        val taskList = mutableListOf<String>()

        // リストとListViewを繋ぐアダプターの設定
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        listViewTasks.adapter = adapter

        // 「追加」ボタンが押された時の動作
        buttonAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()

            if (taskText.isNotEmpty()) {
                // リストにタスクを追加
                taskList.add(taskText)
                
                // アダプターにデータ更新を通知（画面に反映される）
                adapter.notifyDataSetChanged()
                
                // 入力欄を空にする
                editTextTask.text.clear()
            } else {
                // 何も入力されていない場合はトーストを表示
                Toast.makeText(this, "タスクを入力してください", Toast.LENGTH_SHORT).show()
            }
        }
    }
}