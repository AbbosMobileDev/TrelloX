package com.abisoft.trellox.model

import android.content.Context
import com.abisoft.trellox.model.repository.User

class TokenManager (context: Context) {

    private val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("TOKEN", token).apply()
    }

    fun saveUser(user : User){
        sharedPreferences.edit().putString("USER", user.toString()).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }
}

