package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.practicafantasyleague.datos.PaisFantasy

@Composable
fun PantallaPaisDetalle(paisFantasy: PaisFantasy) {
    Column {
        Text(text = "${paisFantasy.pais.nombre}, en el bando ${paisFantasy.alianza.name}, ")
        Image(painter = painterResource(id = paisFantasy.pais.imagen), contentDescription = null)
        Text(text = paisFantasy.pais.descripcion)
    }
}