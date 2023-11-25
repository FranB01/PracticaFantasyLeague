package com.example.practicafantasyleague.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicafantasyleague.componentes.PantallaListaPaises
import com.example.practicafantasyleague.componentes.PantallaPaisDetalle
import com.example.practicafantasyleague.datos.ListaPaisesFantasy


@Composable
fun ControladorNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PantallaListaPaises.ruta) {

        composable(Rutas.PantallaListaPaises.ruta) {
            PantallaListaPaises(navController = navController)
        }

        composable(
            "Detalle/{idPais}",
            arguments = listOf(navArgument("idPais") { type = NavType.IntType })
        ) {
            PantallaPaisDetalle(
                navController = navController,
                paisFantasy = ListaPaisesFantasy.lista[it.arguments?.getInt("idPais")!!]
            )
        }


    }

}