package com.example.formula1fantasy.pantallas

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.formula1fantasy.R
import com.example.formula1fantasy.model.Piloto

@Composable
fun pantallaInicio(){
    Column() {
        Text()
        SearchBar()
        LazyColumn() {
            items(30){
                Piloto()
            }
        }
        Row() {
            ExtendedFloatingActionButton(
                text = TODO(),
                icon = TODO(),
                onClick = TODO(),
                textStyle = TODO(),
                minWidth = TODO(),
                minHeight = TODO(),
                startPadding = TODO(),
                endPadding = TODO(),
                iconPadding = TODO(),
                modifier = TODO(),
                expanded = TODO(),
                shape = TODO(),
                containerColor = TODO(),
                contentColor = TODO(),
                elevation = TODO()
            ) { }
            ExtendedFloatingActionButton() { }
        }
    }
}

@Composable
fun Piloto(
    piloto: Piloto
    ){
    Row() {
        Image(
            contentDescription = null,
            painter = painterResource(piloto.imagen)
        )
        Column() {
            Text(piloto.nombre)
            Text("$piloto.carrera, $piloto.fecha")
            Text(piloto.puntaje.toString())
        }
    }
}