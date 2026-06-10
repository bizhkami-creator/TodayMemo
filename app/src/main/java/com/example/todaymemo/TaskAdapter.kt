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
    private val onStatusChanged: (Task) -> Unit,
    private val onItemLongClicked: (Task) -> Unit // 番号ではなくTask本体を渡すように変更
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
        
        holder.checkBoxDone.setOnCheckedChangeListener(null)
        holder.checkBoxDone.isChecked = task.isCompleted
        updateTextStyle(holder.textViewTaskTitle, task.isCompleted)

        holder.checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTextStyle(holder.textViewTaskTitle, isChecked)
            onStatusChanged(task)
        }

        holder.itemView.setOnLongClickListener {
            // 【Room移行ポイント】長押しされたタスク本体を渡す
            onItemLongClicked(task)
            true
        }
    }

    override fun getItemCount(): Int = taskList.size

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