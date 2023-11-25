package com.example.practicafantasyleague.datos

import java.util.Date

class ListaPaisesFantasy {

    companion object {
        var lista = ArrayList<PaisFantasy>()

        fun cargarLista(){
            lista.add(paisF1)
        }

        val paisF1 = PaisFantasy(ListaPaises.spain, Alianza.OCCIDENTE, ArrayList<Equipamiento>(), 14, "Francia", Date(100000))


        fun guerrasTotales(pais : Pais) : Int{
            var i = 0
            lista.forEach { paisFantasyLista -> if (pais == paisFantasyLista.pais) i++ }
            return i
        }

        fun mediaBatallas(pais : Pais) : Double{
            var i = 0
            var batallasTotal = 0
            lista.forEach { paisFantasyLista ->
                if (pais == paisFantasyLista.pais){
                    batallasTotal += paisFantasyLista.batallasGanadas
                    i++
                }
            }
            return (batallasTotal.toDouble() / i.toDouble())
        }

        fun batallasMaximasGanadas(pais: Pais) : Int{
            var maxGanadas = 0
            lista.forEach { paisFantasyLista ->
                if (pais == paisFantasyLista.pais){
                    if (paisFantasyLista.batallasGanadas > maxGanadas) {
                        maxGanadas = paisFantasyLista.batallasGanadas
                    }
                }
            }
            return maxGanadas
        }
    }
}