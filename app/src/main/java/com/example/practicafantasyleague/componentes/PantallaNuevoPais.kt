package com.example.practicafantasyleague.componentes

import android.widget.CheckBox
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practicafantasyleague.datos.Alianza
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.ListaPaises
import com.example.practicafantasyleague.datos.Pais

class PantallaNuevoPaisViewModel : ViewModel() {
    private val _equipamientoSeleccionado = mutableStateOf(emptyList<Equipamiento>())
    val equipamientoSeleccionado: State<List<Equipamiento>> = _equipamientoSeleccionado
    val paisSeleccionado : Pais = ListaPaises.lista[0]

    fun updateElementosSeleccionados(elemento: Equipamiento, seleccionado: Boolean) {
        _equipamientoSeleccionado.value = if (seleccionado) {
            _equipamientoSeleccionado.value + elemento
        } else {
            _equipamientoSeleccionado.value - elemento
        }
    }

}


@Composable
fun PantallaNuevoPais(navController: NavController) {
    var equipamiento by remember { mutableStateOf(ArrayList<Equipamiento>()) }
    val viewModel : PantallaNuevoPaisViewModel = viewModel()

    Column {
        Text(text = "Añadir país", fontSize = 16.sp)
        Text(text = "Elige un país: ")
        menuDesplegable(opciones = ListaPaises.lista)
        Text(text = "Elige una alianza: ")
        menuDesplegable(opciones = Alianza.values().asList())
        Text(text = "Selecciona el equipamiento (1 o más): ")
        Column {
            Equipamiento.values().forEach {
                CheckBoxEquipamiento(
                    equipamiento = it,
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> menuDesplegable(opciones: List<T>) {
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

@Composable
fun CheckBoxEquipamiento(equipamiento: Equipamiento) {
    var seleccionado by remember { mutableStateOf(false) }
    val viewModel : PantallaNuevoPaisViewModel = viewModel()

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Checkbox(
            checked = seleccionado,
            onCheckedChange = {
                seleccionado = it
                viewModel.updateElementosSeleccionados(equipamiento, seleccionado)
            },
        )
        Text(
            text = equipamiento.toString(),
            Modifier.clickable {
                seleccionado = !seleccionado
                viewModel.updateElementosSeleccionados(equipamiento, seleccionado)
            }
        )
    }

}

/*
@Composable
fun RadioButtonEquipamiento(texto : String){
    var seleccionado by remember { mutableStateOf(false) }

    Row{
        RadioButton(selected = seleccionado, onClick = {
            seleccionado = !seleccionado
        })
        Text(
            text = texto,
            modifier = Modifier.clickable {
                seleccionado = !seleccionado
            }
        )
    }
}
 */