package com.example.formula1fantasy.model

import androidx.annotation.DrawableRes

data class Piloto(
    val nombre: String,
    val carrera: String,
    val fecha: String,
    val puntaje: Int,
    @DrawableRes val imagen: Int? = null
)