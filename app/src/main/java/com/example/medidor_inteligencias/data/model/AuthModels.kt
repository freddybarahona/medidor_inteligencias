package com.example.medidor_inteligencias.data.model

import kotlinx.serialization.Serializable
//este archivo es un data class/file kind file
/*
* El backend (o tu mock de Postman) no entiende de objetos
* Kotlin: envía y recibe texto JSON. AuthModels es lo que
* convierte ese texto en objetos que puedes usar con punto
* (.token, .nombre) y viceversa.
*
* para esto sirve el auth models es un traductor de kotlin
* y tu codigo json
*
* @Serializable es la anotación del plugin de
* kotlinx.serialization: es la que genera el código que
* convierte el objeto ↔ JSON. Sin el plugin en el Gradle te
* saldrá Unresolved reference: Serializable
* */
@Serializable
data class LoginRequest(val email: String, val password: String)

@Serializable
data class LoginResponse(val token: String, val nombre: String, val id: Int)