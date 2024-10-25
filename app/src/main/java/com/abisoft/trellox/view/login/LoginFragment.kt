package com.abisoft.trellox.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abisoft.trellox.R
import com.abisoft.trellox.data.api.AuthService
import com.abisoft.trellox.model.repository.AuthRepository
import com.abisoft.trellox.model.TokenManager
import com.abisoft.trellox.model.request.LoginRequest
import com.abisoft.trellox.viewModel.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val authService = AuthService.create()
        val tokenManager = TokenManager(application)
        val repository = AuthRepository(authService, tokenManager)
        val factory = LoginViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginButton: Button = view.findViewById(R.id.btn_login)
        loginButton.setOnClickListener {
            val loginRequest = LoginRequest("jhonhanks@gmail.com", "aA1234567")
            viewModel.login(loginRequest)
            println(loginRequest)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                // Tizimga kirish muvaffaqiyatli, keyingi fragmentga o'ting
                findNavController().navigate(R.id.action_loginFragment_to_taskFragment)
                // Masalan, TaskFragment ga o'tish
            } else {
                Toast.makeText(requireContext(), "Login or password incorrect", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}