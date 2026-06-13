package com.example.todaymemo

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private var originalTasks: List<Task>, // 全データ
    private val onStatusChanged: (Task) -> Unit,
    private val onItemLongClicked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // 実際に画面に表示するリスト（最初は全データをコピー）
    private var filteredTasks: List<Task> = originalTasks

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBoxDone: CheckBox = view.findViewById(R.id.checkBoxDone)
        val textViewTaskTitle: TextView = view.findViewById(R.id.textViewTaskTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // 表示用リスト(filteredTasks)からデータを取得する
        val task = filteredTasks[position]
        holder.textViewTaskTitle.text = task.title
        
        holder.checkBoxDone.setOnCheckedChangeListener(null)
        holder.checkBoxDone.isChecked = task.isCompleted
        updateTextStyle(holder.textViewTaskTitle, task.isCompleted)

        holder.checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTextStyle(holder.textViewTaskTitle, isChecked)
            onStatusChanged(task)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClicked(task)
            true
        }
    }

    // 表示用リストの件数を返す
    override fun getItemCount(): Int = filteredTasks.size

    /**
     * リスト全体を更新する（データベースからの読み込み時に使う）
     */
    fun updateData(newTasks: List<Task>) {
        originalTasks = newTasks
        filteredTasks = newTasks
        notifyDataSetChanged()
    }

    /**
     * 検索ワードでリストを絞り込む
     */
    fun filter(query: String) {
        filteredTasks = if (query.isEmpty()) {
            originalTasks // 空なら全件表示
        } else {
            // 文字列が含まれるものだけ抽出（大文字小文字を区別しない）
            originalTasks.filter { 
                it.title.contains(query, ignoreCase = true) 
            }
        }
        notifyDataSetChanged() // 画面を更新！
    }

    private fun updateTextStyle(textView: TextView, isCompleted: Boolean) {
        if (isCompleted) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.setTextColor(Color.GRAY)
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            textView.setTextColor(Color.BLACK)
        }
    }
}