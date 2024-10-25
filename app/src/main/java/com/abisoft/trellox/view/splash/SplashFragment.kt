package com.abisoft.trellox.view.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abisoft.trellox.R

class SplashFragment : Fragment(R.layout.fragment_splash) {
    val timer = object : CountDownTimer(4000, 1000) {

        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer.start()
    }
}