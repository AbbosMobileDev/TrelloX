package com.abisoft.trellox.data.api

import com.abisoft.trellox.model.Task
import com.google.android.gms.tasks.Tasks
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TaskService {
    @GET("api/v1/task/get_all_tasks")
    suspend fun getAllTasks(
        @Header("Authorization") token: String
    ): Response<List<Task>>
}
