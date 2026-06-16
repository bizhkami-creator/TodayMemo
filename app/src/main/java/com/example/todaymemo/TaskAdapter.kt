package com.example.todaymemo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(
    private var originalTasks: List<Task>,
    private val onStatusChanged: (Task) -> Unit,
    private val onItemLongClicked: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var filteredTasks: List<Task> = originalTasks
    private var currentQuery: String = ""
    private var currentSortType: Int = 0

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
        applyFilterAndSort()
    }

    fun filter(query: String) {
        currentQuery = query
        applyFilterAndSort()
    }

    fun sortTasks(sortType: Int) {
        currentSortType = sortType
        applyFilterAndSort()
    }

    private fun applyFilterAndSort() {
        var result = if (currentQuery.isEmpty()) {
            originalTasks
        } else {
            originalTasks.filter { it.title.contains(currentQuery, ignoreCase = true) }
        }

        result = when (currentSortType) {
            1 -> result.sortedBy { it.title }
            2 -> result.sortedBy { it.isCompleted }
            else -> result.sortedByDescending { it.id }
        }

        filteredTasks = result
        notifyDataSetChanged()
    }

    private fun updateTextStyle(textView: TextView, isCompleted: Boolean) {
        val context = textView.context
        if (isCompleted) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.setTextColor(ContextCompat.getColor(context, R.color.gray_text))
        } else {
            textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            textView.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }
}