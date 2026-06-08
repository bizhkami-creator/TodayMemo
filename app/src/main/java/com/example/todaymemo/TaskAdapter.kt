package com.example.todaymemo

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

/**
 * @param onStatusChanged チェック状態が変わった時に呼ばれる関数（MainActivityの保存処理を渡す用）
 */
class TaskAdapter(
    context: Context,
    private val taskList: List<Task>,
    private val onStatusChanged: () -> Unit
) : ArrayAdapter<Task>(context, 0, taskList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_task, parent, false)

        val task = getItem(position) ?: return view

        val checkBox = view.findViewById<CheckBox>(R.id.checkBoxDone)
        val textView = view.findViewById<TextView>(R.id.textViewTaskTitle)

        // データのセット
        textView.text = task.title
        
        // チェックボックスの状態反映（一度リスナーを解除）
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = task.isCompleted

        // --- 見た目の更新関数（共通で使うので関数にまとめます） ---
        fun updateTextStyle(isCompleted: Boolean) {
            if (isCompleted) {
                // 取り消し線を付ける
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                // 文字色をグレーにする
                textView.setTextColor(Color.GRAY)
            } else {
                // 取り消し線を消す
                textView.paintFlags = textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                // 文字色を黒（デフォルト）に戻す
                textView.setTextColor(Color.BLACK)
            }
        }

        // 最初の表示状態を反映
        updateTextStyle(task.isCompleted)

        // チェックを切り替えた時の動作
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
            
            // タップした瞬間に見た目を変える！
            updateTextStyle(isChecked)
            
            // 保存処理を呼ぶ
            onStatusChanged()
        }

        return view
    }
}