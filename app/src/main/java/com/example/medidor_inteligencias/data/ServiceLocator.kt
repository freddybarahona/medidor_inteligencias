package com.example.medidor_inteligencias.data

import com.example.medidor_inteligencias.data.remote.ApiClient
import com.example.medidor_inteligencias.data.repository.AuthRepository


object ServiceLocator {
    // lazy = se crea solo la primera vez que alguien lo pide
    val authRepository: AuthRepository by lazy {
        AuthRepository(ApiClient.auth)
    }
}