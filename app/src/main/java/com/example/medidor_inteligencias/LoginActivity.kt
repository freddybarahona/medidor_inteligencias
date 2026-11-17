package com.example.medidor_inteligencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.medidor_inteligencias.ui.theme.Medidor_inteligenciasTheme
class LoginActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Medidor_inteligenciasTheme{
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                        LoginForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginForm(modifier: Modifier = Modifier){
    // Estado del input (sobrevive a la rotación)
    //el by necesita el get y el set para funcionar
    var correo by rememberSaveable { mutableStateOf("") } //recuerda si giras la pantalla el estado no se pierde
    var passwordState = rememberTextFieldState()
    //columna con distribucion de filas
    Column (modifier= modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)){
        //el titulo que dice iniciar sesion
        Text(text ="Iniciar Sesion", style = MaterialTheme.typography.titleLarge)
        //el input de usuario con label usuario y placeholder, es una funcion predeterminada
        OutlinedTextField(
            value = correo,
            onValueChange =  { correo = it},
            modifier = Modifier.fillMaxWidth(),
            //es lo que aparece en la cabecera del input
            label = { Text("correo")},
            //es lo que aparece cuando esta vacio
            placeholder = { Text("ejemplo@hotmail.com")},
            //establece que es solo una linea
            singleLine = true,
            //establece que el teclado solo acepta email
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )

        PasswordField(state = passwordState)

        //boton de pase de sesion
        Button(
            onClick = { /* aquí irá la lógica del login */ },
            enabled = correo.isNotBlank() && passwordState.text.isNotBlank()
        ) {
            Text("Entrar")
        }
        Text( text = if (correo.isBlank()) "Escribe un correo" else  "Hola, $correo", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun PasswordField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    label: String = "Contraseña"
) {
    var visible by rememberSaveable { mutableStateOf(false) }

    OutlinedSecureTextField(
        state = state,
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        placeholder = { Text("hello1234@") },
        textObfuscationMode = if (visible) {
            TextObfuscationMode.Visible
        } else {
            TextObfuscationMode.RevealLastTyped
        },
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    imageVector = if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (visible) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        }
    )
}


/*
ServiceLocator  →  AuthRepository  →  ApiClient.auth  →  AuthApi.kt
       ↑                 ↑
LoginViewModel      decide Ok / BadCredentials / Error
       ↑
LoginForm (LoginActivity)
*/