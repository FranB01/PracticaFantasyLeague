package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
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
import com.example.practicafantasyleague.datos.Alianza
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.ListaPaises
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.Amarillo
import com.example.practicafantasyleague.ui.theme.Azul
import com.example.practicafantasyleague.ui.theme.PracticaFantasyLeagueTheme
import com.example.practicafantasyleague.ui.theme.Rojo
import com.example.practicafantasyleague.ui.theme.Verde
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun PaisComponenteSimple(paisFantasy: PaisFantasy, modoEliminar : Boolean) {
    var seleccionado by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(modoEliminar) }

    val colorFondo = when (paisFantasy.alianza) {
        Alianza.OCCIDENTE -> Azul
        Alianza.BLOQUE_RUSO -> Verde
        Alianza.BLOQUE_CHINO -> Rojo
        Alianza.INDEPENDIENTE -> Amarillo
    }

    // si se le da un valor se asigna, si no es un toggle
    fun setVisible(valor : Boolean?){
        visible = valor ?: !visible
        if (!visible) seleccionado = false // si es "invisible" se resetea
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(colorFondo)
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = paisFantasy.pais.bandera),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
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
        // Checkbox solo visible cuando haga falta. Tamaño fijo para usar spacer para "desactivarlo".
        if (visible){
            Checkbox(
                checked = seleccionado,
                onCheckedChange = { seleccionado = !seleccionado },
                modifier = Modifier.size(30.dp)
            )
        } else {
            Spacer(Modifier.size(30.dp))
        }
    }
}

fun formatoFecha(fecha: Date): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(fecha)
}

@Preview
@Composable
fun PreviewPaisComponenteSimple() {
    PracticaFantasyLeagueTheme {
        Surface {
            PaisComponenteSimple(
                paisFantasy = PaisFantasy(
                    ListaPaises.spain, Alianza.OCCIDENTE,
                    ArrayList<Equipamiento>(), 14, "Francia", Date(10),
                ),
                modoEliminar = false
            )
        }
    }
}