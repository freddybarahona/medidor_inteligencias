package com.example.medidor_inteligencias.data.remote

import com.example.medidor_inteligencias.data.model.LoginRequest
import com.example.medidor_inteligencias.data.model.LoginResponse
import retrofit2.http.POST
import retrofit2.http.Body
//este archivo es un data class/file kind interface
interface AuthApi {
    //esta seria la cabecera de la peticion
    @POST("login") //sin "/" inicial: la url base ya termina en "/"
    suspend fun login(@Body body: LoginRequest): LoginResponse
}

