package com.example.practicafantasyleague.datos

import java.util.Date

data class PaisFantasy (
    var pais: Pais,
    var bloque : Bloque,
    //var continente : List<Continente>,
    var equipamiento : List<Equipamiento>,
    var batallasGanadas : Int,
    var batallaVS : String,
    var fechaGuerra : Date,
)

enum class Bloque{
    OCCIDENTE, BLOQUE_RUSO, BLOQUE_CHINO, INDEPENDIENTE,
}

/*
enum class Continente{
    AMERICA, EUROPA, ASIA, AFRICA, OCEANIA
}
*/

enum class Equipamiento{
    INFANTERIA, TANQUES, APC, CAZAS, BARCOS
}