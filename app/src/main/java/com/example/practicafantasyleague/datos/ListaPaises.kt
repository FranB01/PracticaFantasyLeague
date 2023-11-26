package com.example.practicafantasyleague.datos

import com.example.practicafantasyleague.R

class ListaPaises{
    companion object{
        var lista = ArrayList<Pais>()
        fun cargarLista(){
            lista.add(spain)
            lista.add(italia)
            lista.add(venezuela)
            lista.add(mongolia)
            lista.add(usa)
            lista.add(china)
            lista.add(brasil)
        }

        val spain = Pais(
            "España", R.drawable.spain, R.drawable.callarse_catalufos_hd, R.string.descripcion_spain
        )

        val italia = Pais(
            "Italia", R.drawable.italy, R.drawable.gesto_italiano, R.string.descripcion_italia
        )

        val venezuela = Pais(
            "Venezuela", R.drawable.venezuela_flag, R.drawable.comida_venezolana, R.string.descripcion_venezuela
        )

        val mongolia = Pais(
            "Mongolia", R.drawable.mongolia_flag, R.drawable.throat_singing, R.string.descripcion_mongolia
        )

        val usa = Pais(
            "Estados Unidos", R.drawable.usa_flag, R.drawable.average_american, R.string.descripcion_usa
        )

        val china = Pais(
            "China", R.drawable.china_flag, R.drawable.china_tiananmen_nothing, R.string.descripcion_china
        )

        val brasil = Pais(
            "Brasil", R.drawable.brazil_flag, R.drawable.dia_normal_en_brasil, R.string.descripcion_brasil
        )

    }
}