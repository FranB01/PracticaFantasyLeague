package com.example.practicafantasyleague.datos

data class Pais(
    var nombre : String,
    var bandera : Int,
    var imagen : Int, // solo para la vista detallada, source todas: https://www.countryflags.com/
    var descripcion : String, // solo para la vista detallada
)
