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
    private val taskList: List<Task>,
    private val onStatusChanged: () -> Unit,      // 保存用の合図
    private val onItemLongClicked: (Int) -> Unit // 削除用の合図
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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
        val task = taskList[position]
        holder.textViewTaskTitle.text = task.title
        
        // チェック状態の反映
        holder.checkBoxDone.setOnCheckedChangeListener(null) // リスナーの混線を防ぐ
        holder.checkBoxDone.isChecked = task.isCompleted

        // 見た目の更新
        updateTextStyle(holder.textViewTaskTitle, task.isCompleted)

        // チェックを切り替えた時の動作
        holder.checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTextStyle(holder.textViewTaskTitle, isChecked)
            onStatusChanged() // 保存してね！と合図を送る
        }

        // 長押しした時の動作
        holder.itemView.setOnLongClickListener {
            onItemLongClicked(holder.adapterPosition) // この番号を消してね！と合図を送る
            true
        }
    }

    override fun getItemCount(): Int = taskList.size

    // 見た目を更新する共通関数
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