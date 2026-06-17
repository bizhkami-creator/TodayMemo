package com.example.todaymemo

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    companion object {
        const val CHANNEL_ID = "today_memo_ongoing_channel"
        const val ONGOING_NOTIFICATION_ID = 100 // 常駐通知用の固定ID
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "通知が許可されました", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        checkNotificationPermission()

        val taskDao = AppDatabase.getDatabase(this).taskDao()
        val repository = TaskRepository(taskDao)
        val factory = TaskViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        val spinnerSort = findViewById<Spinner>(R.id.spinnerSort)
        val editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAdd)
        val recyclerViewTasks = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        val textViewEmptyMessage = findViewById<TextView>(R.id.textViewEmptyMessage)

        adapter = TaskAdapter(
            emptyList(),
            onStatusChanged = { updatedTask -> 
                viewModel.updateTask(updatedTask)
                // 【常駐通知】完了チェック時も通知を更新
                updateOngoingNotification()
            },
            onItemLongClicked = { taskToDelete ->
                showDeleteDialog(taskToDelete)
            }
        )
        
        recyclerViewTasks.adapter = adapter
        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        viewModel.allTasks.observe(this) { tasks ->
            adapter.updateData(tasks)
            updateEmptyMessageVisibility(tasks, textViewEmptyMessage)
            // 【常駐通知】起動時やデータ変更時に通知を更新
            updateOngoingNotification()
        }

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

    /**
     * 常駐通知を更新・表示する
     */
    private fun updateOngoingNotification() {
        lifecycleScope.launch {
            // Roomの更新が完了するのをわずかに待つ
            delay(200)
            
            val incompleteTasks = withContext(Dispatchers.IO) {
                viewModel.getIncompleteTasks()
            }

            val contentText = if (incompleteTasks.isEmpty()) {
                "今日のTodoはありません"
            } else {
                incompleteTasks.take(3).joinToString("\n") { "□ ${it.title}" }
            }

            val intent = Intent(this@MainActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                this@MainActivity, 0, intent, PendingIntent.FLAG_IMMUTABLE
            )

            val builder = NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("今日のTodo（常駐中）")
                .setContentText(if (incompleteTasks.size > 3) "$contentText\n..." else contentText)
                .setStyle(NotificationCompat.BigTextStyle().bigText(contentText))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 常駐なので通常優先度でOK
                .setOngoing(true) // 【重要】スワイプで消せなくする
                .setContentIntent(pendingIntent)
                .setAutoCancel(false) // タップしても消えない

            if (ContextCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
                // 同じIDで通知を出し、内容を書き換える
                NotificationManagerCompat.from(this@MainActivity).notify(ONGOING_NOTIFICATION_ID, builder.build())
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TodayMemo常駐通知"
            val descriptionText = "タスクを通知欄に常に表示します"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                setShowBadge(false) // アイコン上のドットは出さない
            }
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
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
                // 削除後は allTasks.observe が自動で動き、updateOngoingNotification が呼ばれます
            }
            .setNegativeButton(getString(R.string.btn_no), null)
            .show()
    }
}