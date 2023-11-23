package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicafantasyleague.R
import com.example.practicafantasyleague.datos.Bloque
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.Pais
import com.example.practicafantasyleague.datos.PaisFantasy
import java.util.Date

@Composable
fun PaisComponente(paisFantasy: PaisFantasy) {
    Row {
        Image(painter = painterResource(id = paisFantasy.pais.imagen), contentDescription = null)
        Column {
            Text(text = paisFantasy.pais.nombre)
            Text(text = "VS ${paisFantasy.batallaVS} - ${paisFantasy.fechaGuerra}")
            Text(text = "${paisFantasy.batallasGanadas} batallas ganadas")
        }
    }
}

@Preview
@Composable
fun previewPaisComponente(){
    PaisComponente(paisFantasy = PaisFantasy(Pais("Espiña", R.drawable.spain, R.drawable.spain, ""),Bloque.OCCIDENTE,
        ArrayList<Equipamiento>(), 14, "Francia", Date(10)
    ))
}