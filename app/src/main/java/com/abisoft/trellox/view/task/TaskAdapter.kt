package com.abisoft.trellox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abisoft.trellox.databinding.ItemTaskBinding
import com.abisoft.trellox.model.TaskRow

class TaskAdapter(private val tasks: List<TaskRow>,
                  private val listener: KanbanAdapter.OnTaskClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Set click listener for the item
            binding.root.setOnClickListener {
                // Call the listener with the clicked task
                listener.onTaskClick(tasks[adapterPosition])
            }
        }
        fun bind(task: TaskRow) {
            binding.taskTitle.text = task.name

    }


}
}
