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
            "España", R.drawable.spain, R.drawable.sevilla, ""
        )

        val italia = Pais(
            "Italia", R.drawable.italy, R.drawable.gesto_italiano, ""
        )

    }
}