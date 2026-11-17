package com.example.medidor_inteligencias.data.remote

import com.example.medidor_inteligencias.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory


//este archivo es un data class/file kind object
object ApiClient {
    //Convierte objetiso Kotlin en texto JSON ignora campos extra del backend
    private val json = Json { ignoreUnknownKeys = true }
    //capa de red: el log solo se activa en debug (BuildConfig.LOG_HTTP)
    private val okHttp = OkHttpClient.Builder().apply{
        if (BuildConfig.LOG_HTTP) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    //aqui entra tu environment: deug = mock, release = api real
    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttp)
        .addConverterFactory(json.asConverterFactory("application/json"
            .toMediaType()))
            .build()
    val auth: AuthApi = retrofit.create(AuthApi::class.java)
}

/*
Bloque	Para qué sirve

object ApiClient
singleton: una sola instancia en toda la app, no una por pantalla

Json { ignoreUnknownKeys }
si el backend manda un campo nuevo, no revienta

OkHttpClient + interceptor
imprime request/response en Logcat solo en debug

.baseUrl(BuildConfig.API_URL)
conecta con el environment que ya configuraste

asConverterFactory
el puente entre kotlinx.serialization y Retrofit

retrofit.create(AuthApi::class.java)
genera la clase concreta de tu interface


La gracia de haber puesto LOG_HTTP en el BuildConfig
es que el log de red se apaga solo en release, sin
tocar este archivo.
 */