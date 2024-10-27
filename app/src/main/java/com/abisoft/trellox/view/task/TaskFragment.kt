package com.abisoft.trellox.view.task


import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abisoft.trellox.KanbanAdapter
import com.abisoft.trellox.R
import com.abisoft.trellox.model.KanbanColumn
import com.abisoft.trellox.model.Task
import com.abisoft.trellox.model.TaskRow
import com.abisoft.trellox.model.TokenManager
import com.abisoft.trellox.viewModel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskFragment : Fragment(R.layout.fragment_task), KanbanAdapter.OnTaskClickListener {

    private lateinit var kanbanRecyclerView: RecyclerView
    private var zoomedIn = false
    private lateinit var taskViewModel: TaskViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kanbanRecyclerView = view.findViewById(R.id.recyclerView)
        kanbanRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, true)

        val btnZoom: FloatingActionButton = view.findViewById(R.id.btn_zoom)
        btnZoom.setOnClickListener {
            toggleZoom()
        }

        val initialColumns = listOf(
            KanbanColumn("New", emptyList()),
            KanbanColumn("In Progress", listOf()),
            KanbanColumn("In Review", listOf())
        )

        val adapter = KanbanAdapter(initialColumns, this)
        kanbanRecyclerView = view.findViewById(R.id.recyclerView)
        kanbanRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        kanbanRecyclerView.adapter = adapter

        taskViewModel.newTasks.observe(viewLifecycleOwner) { newTasks ->
            updateRecyclerView(
                adapter,
                newTasks,
                taskViewModel.inProgressTasks.value,
                taskViewModel.reviewTasks.value
            )
        }

        taskViewModel.inProgressTasks.observe(viewLifecycleOwner) { inProgressTasks ->
            updateRecyclerView(
                adapter,
                taskViewModel.newTasks.value,
                inProgressTasks,
                taskViewModel.reviewTasks.value
            )
        }

        taskViewModel.reviewTasks.observe(viewLifecycleOwner) { doneTasks ->
            updateRecyclerView(
                adapter,
                taskViewModel.newTasks.value,
                taskViewModel.inProgressTasks.value,
                doneTasks
            )
        }


    }

    private fun toggleZoom() {
        zoomedIn = !zoomedIn

        val layoutParams = kanbanRecyclerView.layoutParams as RelativeLayout.LayoutParams
        if (zoomedIn) {
            layoutParams.height = (resources.displayMetrics.heightPixels * 0.8).toInt()
            layoutParams.width = (resources.displayMetrics.widthPixels * 0.8).toInt()
        } else {

            layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT
            layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
        }
        kanbanRecyclerView.layoutParams = layoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        val tokenManager = TokenManager(requireContext())
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            tasks.forEach { task ->
                Log.d("TaskFragment", "Task: ${task.name}, Description: ${task.name}")
                println("Ishladi ::: ${task}")
            }
        }

        taskViewModel.error.observe(viewLifecycleOwner) { error ->
            Log.e("TaskFragment", "Error: $error")

        }

        val bearerToken = tokenManager.getToken().toString() // Bu yerda tokenni o'rnating

        taskViewModel.fetchTasks(bearerToken)

        return view
    }

    private fun updateRecyclerView(
        adapter: KanbanAdapter,
        newTasks: List<Task>?,
        inProgressTasks: List<Task>?,
        doneTasks: List<Task>?
    ) {
        val updatedColumns = listOf(
            KanbanColumn("New", newTasks?.map {
                TaskRow(
                    name = it.name,
                    taskId = it.taskId,
                    index = it.index,
                    projectId = it.projectId,
                    projectName = it.projectName,
                    ownerId = it.ownerId,
                    ownerName = it.ownerName,
                    ownerAvatar = it.ownerAvatar,
                    executorId = it.executorId,
                    executorName = it.executorName,
                    executorAvatar = it.executorAvatar,
                    taskData = it.taskData,
                    termData = it.termData,
                    priority = it.priority,
                    status = it.status
                    )
            } ?: emptyList()),
            KanbanColumn("In Progress", inProgressTasks?.map {
                TaskRow(
                    name = it.name,
                    taskId = it.taskId,
                    index = it.index,
                    projectId = it.projectId,
                    projectName = it.projectName,
                    ownerId = it.ownerId,
                    ownerName = it.ownerName,
                    ownerAvatar = it.ownerAvatar,
                    executorId = it.executorId,
                    executorName = it.executorName,
                    executorAvatar = it.executorAvatar,
                    taskData = it.taskData,
                    termData = it.termData,
                    priority = it.priority,
                    status = it.status

                )
            } ?: emptyList()),
            KanbanColumn("In Review", doneTasks?.map {
                TaskRow(
                    name = it.name,
                    taskId = it.taskId,
                    index = it.index,
                    projectId = it.projectId,
                    projectName = it.projectName,
                    ownerId = it.ownerId,
                    ownerName = it.ownerName,
                    ownerAvatar = it.ownerAvatar,
                    executorId = it.executorId,
                    executorName = it.executorName,
                    executorAvatar = it.executorAvatar,
                    taskData = it.taskData,
                    termData = it.termData,
                    priority = it.priority,
                    status = it.status

                    )
            } ?: emptyList())
        )

        adapter.updateColumns(updatedColumns)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onTaskClick(task: TaskRow) {
            val bundle = Bundle().apply {
            putParcelable("key",task)

            }
        findNavController().navigate(R.id.action_taskFragment_to_detailFragment,bundle)    }

}
