package com.example.formula1fantasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.formula1fantasy.pantallas.PantallaCreacionJugador
import com.example.formula1fantasy.pantallas.PantallaInicio
import com.example.formula1fantasy.ui.theme.Formula1FantasyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Formula1FantasyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //PantallaInicio(modifier = Modifier.padding(innerPadding))
                    PantallaCreacionJugador()
                }
            }
        }
    }
}
