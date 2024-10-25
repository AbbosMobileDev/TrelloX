package com.abisoft.trellox.data.di

import com.abisoft.trellox.data.api.TaskService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://rstask.wiremockapi.cloud/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): TaskService {
        return getRetrofit().create(TaskService::class.java)
    }

}

