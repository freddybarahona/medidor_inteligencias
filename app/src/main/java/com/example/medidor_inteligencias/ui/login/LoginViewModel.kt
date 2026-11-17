package com.example.medidor_inteligencias.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medidor_inteligencias.data.ServiceLocator
import com.example.medidor_inteligencias.data.repository.AuthRepository
import com.example.medidor_inteligencias.data.repository.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel (private val repo: AuthRepository = ServiceLocator.authRepository) : ViewModel() {
    var cargando by mutableStateOf(false)
        private set
    var mensaje by mutableStateOf<String?>(null)
        private set

    fun entrar(email: String, password: String){
        viewModelScope.launch {
            cargando = true
            mensaje = null

            mensaje = when (repo.login(email, password)){
                is LoginResult.Ok -> "Login Correcto"
                LoginResult.BadCredentials -> "Credenciales Invalidas"
                LoginResult.Error -> "No se pudo conectar"
            }

            cargando = false
        }
    }
}