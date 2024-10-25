package com.abisoft.trellox.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abisoft.trellox.model.repository.AuthRepository
import com.abisoft.trellox.model.repository.LoginResponse
import com.abisoft.trellox.model.request.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> get() = _loginResult

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val result = authRepository.login(loginRequest)
            _loginResult.postValue(result)
        }
    }
}
