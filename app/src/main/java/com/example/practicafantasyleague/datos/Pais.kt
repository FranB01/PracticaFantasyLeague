package com.example.practicafantasyleague.datos

import com.example.practicafantasyleague.R

data class Pais(
    var nombre : String,
    var bandera : Int, // https:///
    var imagen : Int, // solo para la vista detallada
    var descripcion : Int, // solo para la vista detallada
){
    // toString devuelve solo el nombre (para el dropdownmenu de PantallaNuevoPais)
    override fun toString(): String {
        return nombre
    }
}
