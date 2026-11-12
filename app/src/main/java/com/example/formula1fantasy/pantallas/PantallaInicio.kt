package com.example.formula1fantasy.pantallas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExpandedFullScreenSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.formula1fantasy.R
import com.example.formula1fantasy.model.Escuderia
import com.example.formula1fantasy.model.Piloto
import com.example.formula1fantasy.model.Posicion
import kotlinx.coroutines.launch
import kotlin.text.contains

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pantallaInicio(modifier: Modifier = Modifier){

    val listaPilotos = remember {
        mutableListOf(
            Piloto(
                nombre = "Max Verstappen",
                escuderia = Escuderia.RED_BULL,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 95,
                posicion = Posicion.PILOTO_1,
                imagen = R.drawable.verstappen
            ),
            Piloto(
                nombre = "Fernando Alonso",
                escuderia = Escuderia.ASTON_MARTIN,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 88,
                posicion = Posicion.PILOTO_1,
                imagen = R.drawable.alonso
            ),
            Piloto(
                nombre = "Lewis Hamilton",
                escuderia = Escuderia.FERRARI,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 84,
                posicion = Posicion.PILOTO_2,
                imagen = R.drawable.hamilton
            ),
            Piloto(
                nombre = "Charles Leclerc",
                escuderia = Escuderia.FERRARI,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 90,
                posicion = Posicion.PILOTO_1,
                imagen = R.drawable.leclerc
            ),
            Piloto(
                nombre = "Carlos Sainz",
                escuderia = Escuderia.WILLIAMS,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 86,
                posicion = Posicion.PILOTO_2,
                imagen = R.drawable.sainz
            ),
            Piloto(
                nombre = "Lando Norris",
                escuderia = Escuderia.MCLAREN,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 89,
                posicion = Posicion.PILOTO_1,
                imagen = R.drawable.norris
            ),
            Piloto(
                nombre = "Oscar Piastri",
                escuderia = Escuderia.MCLAREN,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 82,
                posicion = Posicion.PILOTO_2,
                imagen = R.drawable.piastri
            ),
            Piloto(
                nombre = "George Russell",
                escuderia = Escuderia.MERCEDES,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 85,
                posicion = Posicion.PILOTO_1,
                imagen = R.drawable.russell
            ),
            Piloto(
                nombre = "Franco Colapinto",
                escuderia = Escuderia.WILLIAMS,
                carrera = "GP de Baréin",
                fecha = "02/03/2026",
                puntaje = 75,
                posicion = Posicion.RESERVA,
                imagen = R.drawable.colapinto
            )
        )
    }
    Column(modifier = modifier) {
        Text("Bienvenido a tu fantasy league")

        val escuderias = mutableListOf<String>(
            Escuderia.RED_BULL.name,
            Escuderia.FERRARI.name,
            Escuderia.MERCEDES.name,
            Escuderia.MCLAREN.name,
            Escuderia.ASTON_MARTIN.name,
            Escuderia.ALPINE.name,
            Escuderia.WILLIAMS.name,
            Escuderia.RACING_BULLS.name,
            Escuderia.KICK_SAUBER.name,
            Escuderia.HAAS.name,
        )
        var buscado by remember { mutableStateOf("") }
        var estadoBarra = rememberSearchBarState()
        var estadoTexto = rememberTextFieldState()
        val scope = rememberCoroutineScope()
        var modoEliminar by remember { mutableStateOf(false) }

        SearchBar(
            state = estadoBarra,
            inputField = {
                SearchBarDefaults.InputField(
                    textFieldState = estadoTexto,
                    searchBarState = estadoBarra,
                    onSearch = {
                        buscado = estadoTexto.text.toString()
                        scope.launch { estadoBarra.animateToCollapsed() }
                    },
                    placeholder = {Text("Buscar...")},
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        ExpandedFullScreenSearchBar(
            state = estadoBarra,
            inputField = {
                SearchBarDefaults.InputField(
                    textFieldState = estadoTexto,
                    searchBarState = estadoBarra,
                    onSearch = {
                        buscado = estadoTexto.text.toString()
                        scope.launch {
                            estadoBarra.animateToCollapsed()
                        }
                    },
                    placeholder = {Text("Buscar...")},
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") }
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(escuderias) {
                    if (it.contains(estadoTexto.text, ignoreCase = true)) {
                        ListItem(
                            headlineContent = { Text(it) },
                            modifier = Modifier.clickable {
                                buscado = it
                                scope.launch { estadoBarra.animateToCollapsed() }
                            }
                        )
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(listaPilotos) {
                if (it.escuderia.name.contains(buscado)) {
                    ComPiloto(it)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExtendedFloatingActionButton(
                onClick = { /* Acción para añadir */ },
                icon = { Icon(Icons.Default.Add, contentDescription = "Añadir") },
                text = { Text("Añadir") },
                containerColor = Color.Gray //
            )

            ExtendedFloatingActionButton(
                onClick = { modoEliminar = true },
                icon = { Icon(Icons.Default.Delete, contentDescription = "Eliminar") },
                text = { Text("Eliminar") },

            )
        }
    }
}

@Composable
fun ComPiloto (piloto: Piloto){
    Row() {
        Image(
            contentDescription = null,
            painter = painterResource(piloto.imagen ?: R.drawable.ic_launcher_background)
        )
        Column {
            Text(piloto.nombre)
            Text(piloto.escuderia.name)
            Text("${piloto.carrera}, ${piloto.fecha}")
            Text(piloto.puntaje.toString())
        }
    }
}
