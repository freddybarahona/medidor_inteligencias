package com.example.medidor_inteligencias.data.repository

import com.example.medidor_inteligencias.data.model.LoginRequest
import com.example.medidor_inteligencias.data.remote.AuthApi
import retrofit2.HttpException
import java.io.IOException

sealed interface LoginResult{
    //las 3 posibles respuestas del login
    data class Ok(val token: String, val nombre: String): LoginResult //200
    data object BadCredentials: LoginResult // 401
    data object Error: LoginResult // sin red 404, 500...
}


class AuthRepository(private val api: AuthApi){
    suspend fun login(email: String, password: String): LoginResult=try {
        val response = api.login(LoginRequest(email, password))
        LoginResult.Ok(response.token, response.nombre)
    } catch (e: HttpException){
        when (e.code()) {
            401 -> LoginResult.BadCredentials
            else -> LoginResult.Error
        }
    } catch (e: IOException){
        LoginResult.Error
    }
}