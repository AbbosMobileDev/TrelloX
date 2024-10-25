package com.abisoft.trellox.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abisoft.trellox.model.Task
import com.abisoft.trellox.data.di.RetrofitClient
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {

    private val apiService = RetrofitClient.getApiService()

    // API'dan kelgan ma'lumotlarni saqlash uchun LiveData
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    private val _newTasks = MutableLiveData<List<Task>>()
    val newTasks: LiveData<List<Task>> get() = _newTasks

    private val _inProgressTasks = MutableLiveData<List<Task>>()
    val inProgressTasks: LiveData<List<Task>> get() = _inProgressTasks

    private val _reviewTasks = MutableLiveData<List<Task>>()
    val reviewTasks: LiveData<List<Task>> get() = _reviewTasks

    // Xatolarni log qilish uchun
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Ma'lumotlarni olish uchun funksiya
    fun fetchTasks(token: String) {
        viewModelScope.launch {
            try {
                // Bearer token bilan API'ga so'rov yuborish
                val response = apiService.getAllTasks("Bearer $token")
                if (response.isSuccessful) {
                    _tasks.value = response.body()
                    println(tasks)
                    println(response.body())
                    val taskList = response.body() ?: emptyList()
                    _tasks.value = taskList

                    _newTasks.value = taskList.filter { it.status == "new" }
                    _inProgressTasks.value = taskList.filter { it.status == "in_progress" }
                    _reviewTasks.value = taskList.filter { it.status == "in_review" }
                } else {
                    _error.value = "Error: ${response.code()}"
                    println(response.code())
                }
            } catch (e: Exception) {
                _error.value = e.message
                println("fdvdfv")
            }
        }
    }
}
