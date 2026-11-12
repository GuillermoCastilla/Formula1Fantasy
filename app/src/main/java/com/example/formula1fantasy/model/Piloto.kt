package com.example.formula1fantasy.model

import androidx.annotation.DrawableRes

data class Piloto(
    val nombre: String,
    val escuderia: Escuderia,
    val carrera: String,
    val fecha: String,
    val puntaje: Int,
    val neumaticos: List<Neumaticos>,
    @DrawableRes val imagen: Int? = null
)
