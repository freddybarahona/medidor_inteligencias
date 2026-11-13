package com.example.medidor_inteligencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.medidor_inteligencias.ui.theme.Medidor_inteligenciasTheme
/**
 * Pantalla principal. Solo arma la estructura; la UI real está en NameForm.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dibuja la app detras de las barras del sistema
        enableEdgeToEdge()
        //aqui empieza el mundo compose
        setContent {
            //aplica colores y tipografia de la app
            Medidor_inteligenciasTheme {
                // innerPadding = espacio que ocupan las barras de estado/navegación
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //llamado a funcion greeting
                    Greeting(
                        name = "freddy",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Composable: función que DIBUJA UI.
 * Recibe datos (name) y un Modifier opcional (por convención, siempre el último).
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        // "Hello $name!" usa interpolación: inserta el valor de name.
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * @Preview NO se ejecuta en el teléfono:
 * solo muestra este composable en el panel de Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    // el preview necesita el tema para verse igual
    Medidor_inteligenciasTheme {
        // aquí se puede probar con otros nombres
        Greeting("Android")
    }
}