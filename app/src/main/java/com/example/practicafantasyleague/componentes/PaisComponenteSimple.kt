package com.example.practicafantasyleague.componentes

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicafantasyleague.datos.Alianza
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.ListaPaises
import com.example.practicafantasyleague.datos.ListaPaisesFantasy
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.Amarillo
import com.example.practicafantasyleague.ui.theme.Azul
import com.example.practicafantasyleague.ui.theme.PracticaFantasyLeagueTheme
import com.example.practicafantasyleague.ui.theme.Rojo
import com.example.practicafantasyleague.ui.theme.Verde
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun PaisComponenteSimple(
    paisFantasy: PaisFantasy,
    modoEliminar: Boolean,
    eventoClick: () -> Unit,
    //navController : NavController,
) {
    val viewModel: PantallaListaPaisesViewModel = viewModel()
    var seleccionado by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(modoEliminar) }
    visible = modoEliminar
    if (!visible) {
        seleccionado = false
    } // se resetea al hacerse invisible

    val colorFondo = when (paisFantasy.alianza) {
        Alianza.OCCIDENTE -> Azul
        Alianza.BLOQUE_RUSO -> Verde
        Alianza.BLOQUE_CHINO -> Rojo
        Alianza.INDEPENDIENTE -> Amarillo
    }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                eventoClick.invoke()
                Log.i("info", "(interior) Se clickó ${paisFantasy.pais.nombre}")
            }
            .background(colorFondo)
            .fillMaxWidth()
            .padding(6.dp)
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
        if (visible) {
            Checkbox(
                checked = seleccionado,
                onCheckedChange = {
                    seleccionado = it
                    viewModel.updateElementosSeleccionados(paisFantasy, it)
                },
                modifier = Modifier.size(30.dp)
            )
        } else {
            Spacer(Modifier.size(30.dp))
        }
    }
}

@SuppressLint("SimpleDateFormat")
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
                    ArrayList(), 14, "Francia", Date(10),
                ),
                modoEliminar = false,
                eventoClick = {},
                //navController = rememberNavController()
            )
        }
    }
}