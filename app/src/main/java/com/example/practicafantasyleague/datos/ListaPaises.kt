package com.example.practicafantasyleague.datos

import com.example.practicafantasyleague.R

class ListaPaises{
    companion object{
        fun cargarLista(): ArrayList<Pais>{
            var lista = ArrayList<Pais>()
            lista.add(spain)
            lista.add(italia)

            return lista
        }

        val spain = Pais(
            "España", R.drawable.spain, R.drawable.sevilla, R.string.descripcion_spain
        )

        val italia = Pais(
            "Italia", R.drawable.italy, R.drawable.gesto_italiano, R.string.descripcion_italia
        )

        val venezuela = Pais(
            "Venezuela", R.drawable.venezuela_flag, R.drawable.comida_venezolana, R.string.descripcion_venezuela
        )

    }
}