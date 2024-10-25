package com.abisoft.trellox

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abisoft.trellox.databinding.ItemColumnBinding
import com.abisoft.trellox.model.KanbanColumn
import com.abisoft.trellox.model.Task
import com.abisoft.trellox.model.TaskRow

class KanbanAdapter(
    var columns: List<KanbanColumn>,
    private val listener: OnTaskClickListener
) : RecyclerView.Adapter<KanbanAdapter.KanbanViewHolder>() {
    interface OnTaskClickListener {
        fun onTaskClick(task: TaskRow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KanbanViewHolder {
        val binding = ItemColumnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KanbanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KanbanViewHolder, position: Int) {
        val column = columns[position]
        holder.bind(column)
    }

    override fun getItemCount(): Int = columns.size

    inner class KanbanViewHolder(private val binding: ItemColumnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(column: KanbanColumn) {
            binding.columnTitle.text = column.title

            // Set up the RecyclerView for tasks in this column
            val taskAdapter = TaskAdapter(column.tasks, listener)
            binding.taskRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.taskRecyclerView.adapter = taskAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateColumns(newColumns: List<KanbanColumn>) {
        this.columns = newColumns
        notifyDataSetChanged() // Yangilanishni bildirish
    }


}
