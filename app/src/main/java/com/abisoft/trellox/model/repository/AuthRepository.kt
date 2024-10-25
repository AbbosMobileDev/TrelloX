package com.abisoft.trellox.model.repository

import com.abisoft.trellox.data.api.AuthService
import com.abisoft.trellox.model.TokenManager
import com.abisoft.trellox.model.request.LoginRequest

class AuthRepository(private val authService: AuthService, private val tokenManager: TokenManager) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        val response = authService.login(loginRequest)
        return if (response.isSuccessful) {
            response.body()?.let {
                tokenManager.saveToken(it.token)
                println("token saqladi ${ tokenManager.getToken()}")

                tokenManager.saveUser(it.user)
                println(it.user)
                it
            }
        } else {
            null
        }
    }
}
