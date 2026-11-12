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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formula1fantasy.model.Escuderia

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCreacionJugador(modifier: Modifier = Modifier){
    var nombrePiloto by remember { mutableStateOf("") }
    var escuderiaSeleccionada by remember { mutableStateOf("") }
    var textoBusqueda by remember { mutableStateOf("") }
    var expandido by remember { mutableStateOf(false) }

    val escuderias = remember { Escuderia.entries.map { it.name } }

    Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
        Text("Introduce el nombre del piloto:")
        OutlinedTextField(
            value = nombrePiloto,
            onValueChange = { nombrePiloto = it },
            placeholder = { Text("Ej. Fernando Alonso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
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
