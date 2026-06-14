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
    private var originalTasks: List<Task>,
    private val onStatusChanged: (Task) -> Unit,
    private val onItemLongClicked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var filteredTasks: List<Task> = originalTasks
    private var currentQuery: String = "" // 現在の検索ワードを保持
    private var currentSortType: Int = 0 // 0: 作成順, 1: 名前順, 2: 完了順

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
        val task = filteredTasks[position]
        holder.textViewTaskTitle.text = task.title
        
        holder.checkBoxDone.setOnCheckedChangeListener(null)
        holder.checkBoxDone.isChecked = task.isCompleted
        updateTextStyle(holder.textViewTaskTitle, task.isCompleted)

        holder.checkBoxDone.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            updateTextStyle(holder.textViewTaskTitle, isChecked)
            onStatusChanged(task)
            // チェック状態が変わったときも、現在のルールで並び替え直す
            applyFilterAndSort()
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClicked(task)
            true
        }
    }

    override fun getItemCount(): Int = filteredTasks.size

    fun updateData(newTasks: List<Task>) {
        originalTasks = newTasks
        applyFilterAndSort() // データを更新した時もフィルタとソートを適用
    }

    /**
     * 検索ワードを設定する
     */
    fun filter(query: String) {
        currentQuery = query
        applyFilterAndSort()
    }

    /**
     * 並び替えルールを設定する
     */
    fun sortTasks(sortType: Int) {
        currentSortType = sortType
        applyFilterAndSort()
    }

    /**
     * 検索と並び替えをまとめて実行する心臓部の関数
     */
    private fun applyFilterAndSort() {
        // 1. まずは検索フィルタをかける
        var result = if (currentQuery.isEmpty()) {
            originalTasks
        } else {
            originalTasks.filter { it.title.contains(currentQuery, ignoreCase = true) }
        }

        // 2. 次に並び替えを適用する
        result = when (currentSortType) {
            1 -> result.sortedBy { it.title } // 名前順
            2 -> result.sortedBy { it.isCompleted } // 完了順（false=0, true=1 なので未完了が先）
            else -> result.sortedByDescending { it.id } // デフォルト：作成順（IDが大きい順）
        }

        filteredTasks = result
        notifyDataSetChanged()
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