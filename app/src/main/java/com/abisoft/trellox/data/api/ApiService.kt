package com.abisoft.trellox.data.api
import com.abisoft.trellox.model.repository.LoginResponse
import com.abisoft.trellox.model.request.LoginRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v2/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    companion object {
        fun create(): AuthService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://rstask.wiremockapi.cloud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AuthService::class.java)
        }
    }
}
