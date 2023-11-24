package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafantasyleague.R
import com.example.practicafantasyleague.datos.Bloque
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.Pais
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.Amarillo
import com.example.practicafantasyleague.ui.theme.Azul
import com.example.practicafantasyleague.ui.theme.Rojo
import com.example.practicafantasyleague.ui.theme.Verde
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun PaisComponente(paisFantasy: PaisFantasy) {
    val colorFondo = when (paisFantasy.bloque) {
        Bloque.OCCIDENTE -> Azul
        Bloque.BLOQUE_RUSO -> Verde
        Bloque.BLOQUE_CHINO -> Rojo
        Bloque.INDEPENDIENTE -> Amarillo
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(colorFondo)
    ) {
        Image(painter = painterResource(id = paisFantasy.pais.imagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(6.dp)

        ) {
            Text(text = paisFantasy.pais.nombre)
            Text(text = "VS ${paisFantasy.batallaVS} - ${formatoFecha(paisFantasy.fechaGuerra)}")
            Text(text = "${paisFantasy.batallasGanadas} batallas ganadas")
        }
    }
}

fun formatoFecha(fecha : Date) : String{
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(fecha)
}

@Preview
@Composable
fun previewPaisComponente() {
    PaisComponente(
        paisFantasy = PaisFantasy(
            Pais("Espiña", R.drawable.spain, R.drawable.spain, ""), Bloque.OCCIDENTE,
            ArrayList<Equipamiento>(), 14, "Francia", Date(10)
        )
    )
}