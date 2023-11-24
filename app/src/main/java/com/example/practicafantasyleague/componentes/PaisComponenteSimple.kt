package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicafantasyleague.datos.Bloque
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.ListaPaises
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.Amarillo
import com.example.practicafantasyleague.ui.theme.Azul
import com.example.practicafantasyleague.ui.theme.Rojo
import com.example.practicafantasyleague.ui.theme.Verde
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun PaisComponenteSimple(paisFantasy: PaisFantasy) {
    var seleccionado by remember { mutableStateOf(false) }

    val colorFondo = when (paisFantasy.bloque) {
        Bloque.OCCIDENTE -> Azul
        Bloque.BLOQUE_RUSO -> Verde
        Bloque.BLOQUE_CHINO -> Rojo
        Bloque.INDEPENDIENTE -> Amarillo
    }



    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(colorFondo)
            .padding(6.dp)
    ) {
        Image(
            painter = painterResource(id = paisFantasy.pais.bandera),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
                .size(80.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(6.dp)
        ) {
            Text(text = paisFantasy.pais.nombre)
            Text(text = "VS ${paisFantasy.batallaVS} - ${formatoFecha(paisFantasy.fechaGuerra)}")
            Text(text = "${paisFantasy.batallasGanadas} batallas ganadas")
        }
        Checkbox(
            checked = seleccionado,
            onCheckedChange = { seleccionado = !seleccionado }
        )
    }
}

fun formatoFecha(fecha: Date): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(fecha)
}

@Preview
@Composable
fun PreviewPaisComponenteSimple() {
    PaisComponenteSimple(
        paisFantasy = PaisFantasy(
            ListaPaises.spain, Bloque.OCCIDENTE,
            ArrayList<Equipamiento>(), 14, "Francia", Date(10)
        )
    )
}