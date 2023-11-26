package com.example.practicafantasyleague.nav

sealed class Rutas(val ruta: String) {
    object PantallaListaPaises : Rutas("Lista")
    object PantallaPaisDetalle : Rutas("Detalle/{idPais}") // id en el ArrayList
    object PantallaNuevoPais : Rutas("Nuevo")

}
