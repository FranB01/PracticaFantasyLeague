package com.example.practicafantasyleague.datos

class ListaPaisesFantasy {
    fun calcularEstadisticas(paisFantasy: PaisFantasy) {
        var i = 0 // Contador
        var mediaBatallas: Double
        var batallasTotal = 0
        var maxBatallasGanadas = 0
        lista.forEach { paisLista ->
            if (paisLista == paisFantasy) {
                batallasTotal += paisLista.batallasGanadas
                if (maxBatallasGanadas < paisLista.batallasGanadas) {
                    maxBatallasGanadas = paisLista.batallasGanadas
                }
                i++
            }
        }

        mediaBatallas = (batallasTotal.toDouble()) / (i.toDouble())
    }

    companion object {
        var lista = ArrayList<PaisFantasy>()


    }
}