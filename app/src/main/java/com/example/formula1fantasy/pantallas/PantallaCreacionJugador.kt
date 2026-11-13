package com.example.formula1fantasy.pantallas


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.formula1fantasy.model.Escuderia
import com.example.formula1fantasy.model.Neumaticos
import com.example.formula1fantasy.model.Piloto
import kotlin.collections.setOf

@Composable
fun Fecha(datePickerState: DatePickerState) {
    Text("Selecciona la fecha de la carrera:")

    DatePicker(
        state = datePickerState,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Puntos(
    titulo: String,
    puntos: Float,
    onValueChange: (Float) -> Unit
){
    Text(titulo)
    Row(
        verticalAlignment =  Alignment.CenterVertically
    ) {
        Slider(
            value = puntos,
            onValueChange = { onValueChange(it) },
            valueRange = 0f..33f,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = puntos.toInt().toString(), // 👈 Lo convertimos a Int
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun SelectorRuedas(
    titulo: String,
    neumaticos: List<String>,
    neumaticosSeleccionados: Set<String>,
    onClick: (Set<String>) -> Unit
){
    Text(titulo)
    Column() {
        neumaticos.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = it in neumaticosSeleccionados,
                    onClick = {
                        if (it in neumaticosSeleccionados) onClick(neumaticosSeleccionados - it)
                        else onClick(neumaticosSeleccionados + it)
                    }
                )
                Text(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Desplegable(
    titulo: String,
    lista: List<String>,
    seleccionado: String,
    onSeleccionado: (String) -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Text(titulo)
    ExposedDropdownMenuBox(
        expanded = expandido,
        onExpandedChange = { expandido = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        TextField(
            value = seleccionado,
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
            lista.forEach { piloto ->
                DropdownMenuItem(
                    text = { Text(piloto) },
                    onClick = {
                        onSeleccionado(piloto)
                        expandido = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCreacionJugador(
    onPilotoCreado: (Piloto) -> Unit,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
){
    val scrollState =   rememberScrollState()

    var escuderiaSeleccionada by remember { mutableStateOf("") }
    val escuderias = Escuderia.entries.map { it.name.replace("_", " ") }

    val nombresPiloto = Escuderia.entries
        .find { it.name.replace("_", " ") == escuderiaSeleccionada }
        ?.pilotos ?: emptyList()
    var nombrePiloto by remember { mutableStateOf("") }

    val neumaticos = Neumaticos.entries.map { it.name.replace("_", " ") }
    var neumaticosSeleccionado by remember { mutableStateOf(setOf<String>()) }

    var puntos by remember { mutableFloatStateOf(0f) }

    var gpSeleccionado by remember { mutableStateOf("") }
    val grandesPremios = listOf(
        "GP de Baréin",
        "GP de Arabia Saudí",
        "GP de Australia",
        "GP de Japón",
        "GP de China",
        "GP de Miami",
        "GP de Emilia-Romaña",
        "GP de Mónaco",
        "GP de Canadá",
        "GP de España",
        "GP de Austria",
        "GP de Gran Bretaña",
        "GP de Hungría",
        "GP de Bélgica",
        "GP de Países Bajos",
        "GP de Italia",
        "GP de Azerbaiyán",
        "GP de Singapur",
        "GP de Estados Unidos",
        "GP de México",
        "GP de Brasil",
        "GP de Las Vegas",
        "GP de Catar",
        "GP de Abu Dabi"
    )


    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input
    )


    Column(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
        .verticalScroll(scrollState)){
        Desplegable(
            "La escudería en la que pilota:",
            escuderias,
            escuderiaSeleccionada,
            {
                escuderiaSeleccionada = it
                nombrePiloto = ""
            }
        )
        Desplegable(
            "Introduce el nombre del piloto:",
            lista = nombresPiloto,
            seleccionado = nombrePiloto,
            onSeleccionado = { nombrePiloto = it }
        )
        SelectorRuedas(
            "Elige las ruedas usadas",
            neumaticos,
            neumaticosSeleccionado,
            onClick = { setString ->
                neumaticosSeleccionado = setString
            }
        )
        Puntos(
            "Selecciona los puntos",
            puntos,
            onValueChange = {puntos = it}
        )
        Desplegable(
            "Selecciona el Gran Premio",
            grandesPremios,
            gpSeleccionado,
            {gpSeleccionado = it}
        )
        Fecha(
            datePickerState
        )
    }
}