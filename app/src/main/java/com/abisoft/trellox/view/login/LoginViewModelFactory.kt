package com.abisoft.trellox.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abisoft.trellox.model.repository.AuthRepository
import com.abisoft.trellox.viewModel.LoginViewModel

class LoginViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

