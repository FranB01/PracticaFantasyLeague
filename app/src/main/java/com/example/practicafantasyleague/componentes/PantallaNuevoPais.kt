package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.practicafantasyleague.datos.ListaPaises

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNuevoPais() {
    Column {
        Text(text = "Añadir país", fontSize = 16.sp)
        Text(text = "Elige un país: ")
        menuDesplegable(opciones = ListaPaises.lista)
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> menuDesplegable(opciones : List<T>){
    var menuExpandido by remember { mutableStateOf(false) }
    var opcionSeleccionada by remember { mutableStateOf("") }

    Column {

        ExposedDropdownMenuBox(
            expanded = menuExpandido,
            onExpandedChange = {
                menuExpandido = it
            }
        ) {
            TextField(
                value = opcionSeleccionada,
                readOnly = true,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = menuExpandido)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = menuExpandido,
                onDismissRequest = { menuExpandido = false }
            ) {
                opciones.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.toString()) },
                        onClick = {
                            opcionSeleccionada = it.toString()
                            menuExpandido = false
                        }
                    )
                }
            }
        }
    }
}