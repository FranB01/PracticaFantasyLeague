package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicafantasyleague.datos.ListaPaisesFantasy
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.PurpleGrey80

@Composable
fun PantallaPaisDetalle(
    navController: NavHostController?,
    paisFantasy: PaisFantasy
) {
    Column(
        //Modifier.background(PurpleGrey80)
    ) {
        Text(text = "${paisFantasy.pais.nombre}, en el bando ${paisFantasy.alianza.toString()}")
        Image(
            painter = painterResource(id = paisFantasy.pais.imagen),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.9f)
                .align(CenterHorizontally)
        )
        Text(text = "Estadísticas: En un total de ${ListaPaisesFantasy.guerrasTotales(paisFantasy.pais)}" +
                " guerras, ha ganado de media ${ListaPaisesFantasy.mediaBatallas(paisFantasy.pais)} batallas. " +
                "Su mayor número de victorias fue de " +
                "${ListaPaisesFantasy.batallasMaximasGanadas(paisFantasy.pais)}.\n")
        Text(text = stringResource(id = paisFantasy.pais.descripcion))
    }
}

@Preview
@Composable
fun PreviewPantallaPaisDetalle() {
    PantallaPaisDetalle(
        navController = rememberNavController(),
        paisFantasy = ListaPaisesFantasy.paisF1
    )
}