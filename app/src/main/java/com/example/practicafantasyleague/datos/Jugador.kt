package com.example.practicafantasyleague.datos

import java.util.Date

data class Jugador(
    var nombre : String,
    var equipo : String,
    var posiciones : ArrayList<Posicion>,
    var puntos : Int,
    var equipoVS : String, // equipo contra el que jugó
    var fecha : Date,
)

enum class Posicion{
    G, SG, SF, PF, C,
}


