package com.example.formula1fantasy.model

import androidx.compose.ui.graphics.Color

enum class Escuderia(
    val color: Color,
    val pilotos: List<String>
) {
    FERRARI(
        color = Color(0xFFE80020),
        pilotos = listOf("Charles Leclerc", "Lewis Hamilton", "Oliver Bearman")
    ),
    RED_BULL(
        color = Color(0xFF1E41FF),
        pilotos = listOf("Max Verstappen", "Liam Lawson", "Isack Hadjar")
    ),
    MCLAREN(
        color = Color(0xFFFF8000),
        pilotos = listOf("Lando Norris", "Oscar Piastri", "Pato O'Ward")
    ),
    MERCEDES(
        color = Color(0xFF00A19B),
        pilotos = listOf("George Russell", "Kimi Antonelli", "Mick Schumacher")
    ),
    ASTON_MARTIN(
        color = Color(0xFF229971),
        pilotos = listOf("Fernando Alonso", "Lance Stroll", "Felipe Drugovich")
    ),
    ALPINE(
        color = Color(0xFF0093CC),
        pilotos = listOf("Pierre Gasly", "Jack Doohan", "Victor Martins")
    ),
    WILLIAMS(
        color = Color(0xFF00A0DE),
        pilotos = listOf("Carlos Sainz", "Alexander Albon", "Franco Colapinto")
    ),
    RACING_BULLS(
        color = Color(0xFF6692FF),
        pilotos = listOf("Yuki Tsunoda", "Isack Hadjar", "Ayumu Iwasa")
    ),
    KICK_SAUBER(
        color = Color(0xFF52E252),
        pilotos = listOf("Nico Hülkenberg", "Gabriel Bortoleto", "Zane Maloney")
    ),
    HAAS(
        color = Color(0xFFB6BABD),
        pilotos = listOf("Esteban Ocon", "Oliver Bearman", "Pietro Fittipaldi")
    )
}