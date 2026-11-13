package com.example.formula1fantasy.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formula1fantasy.model.Escuderia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Desplegable(
    nombresPiloto: List<String>,
    pilotoSeleccionado: String,
    onPilotoSeleccionado: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        TextField(
            value = pilotoSeleccionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Selecciona un valor") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            nombresPiloto.forEach { piloto ->
                DropdownMenuItem(
                    text = { Text(piloto) },
                    onClick = {
                        onPilotoSeleccionado(piloto)
                        expandido = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCreacionJugador(modifier: Modifier = Modifier){
    var nombrePiloto by remember { mutableStateOf("") }
    var escuderiaSeleccionada by remember { mutableStateOf("") }
    var textoBusqueda by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }

    val escuderias = remember { Escuderia.entries.map { it.name } }
    val nombresPiloto = listOf(
        // Red Bull Racing
        "Max Verstappen",
        "Sergio Pérez",
        // Ferrari
        "Charles Leclerc",
        "Lewis Hamilton",
        // McLaren
        "Lando Norris",
        "Oscar Piastri",
        // Mercedes
        "George Russell",
        "Kimi Antonelli",
        // Aston Martin
        "Fernando Alonso",
        "Lance Stroll",
        // Williams
        "Carlos Sainz",
        "Alexander Albon",
        "Franco Colapinto",
        // Alpine
        "Pierre Gasly",
        "Jack Doohan",
        "Esteban Ocon",
        // Racing Bulls
        "Yuki Tsunoda",
        "Liam Lawson",
        // Haas
        "Oliver Bearman",
        "Esteban Ocon",
        // Kick Sauber
        "Nico Hülkenberg",
        "Gabriel Bortoleto",
        "Valtteri Bottas"
    )

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text("Introduce el nombre del piloto:")
        Desplegable(
            nombresPiloto = nombresPiloto,
            pilotoSeleccionado = nombrePiloto,
            onPilotoSeleccionado = { nombrePiloto = it }
        )

        Text("La escudería en la que pilota:")
        DockedSearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = textoBusqueda,
                    onQueryChange = { textoBusqueda = it },
                    onSearch = { expandido = false },
                    expanded = expandido,
                    onExpandedChange = { expandido = it },
                    placeholder = { Text("Buscar escudería...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") }
                )
            },
            expanded = expandido,
            onExpandedChange = { expandido = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            LazyColumn {
                val filtradas = escuderias.filter { it.contains(textoBusqueda, ignoreCase = true) }
                items(filtradas) { escuderia ->
                    ListItem(
                        headlineContent = { Text(escuderia) },
                        modifier = Modifier.clickable {
                            escuderiaSeleccionada = escuderia
                            textoBusqueda = escuderia
                            expandido = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun Preview(){
    PantallaCreacionJugador()
}
