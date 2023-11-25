package com.example.practicafantasyleague.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicafantasyleague.componentes.PantallaListaPaises


@Composable
fun ControladorNav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.PantallaListaPaises.ruta ){

        composable(Rutas.PantallaListaPaises.ruta){
            PantallaListaPaises()
        }





    }

}