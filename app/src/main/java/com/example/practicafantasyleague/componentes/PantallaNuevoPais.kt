package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.practicafantasyleague.datos.Alianza
import com.example.practicafantasyleague.datos.Equipamiento
import com.example.practicafantasyleague.datos.ListaPaises
import com.example.practicafantasyleague.datos.ListaPaisesFantasy
import com.example.practicafantasyleague.datos.Pais
import com.example.practicafantasyleague.datos.PaisFantasy
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PantallaNuevoPaisViewModel : ViewModel() {
    private val _equipamientoSeleccionado = mutableStateOf(emptyList<Equipamiento>())
    val equipamientoSeleccionado: State<List<Equipamiento>> = _equipamientoSeleccionado

    var paisSeleccionado: Pais = ListaPaises.lista[0]
    var alianzaSeleccionada: Alianza = Alianza.OCCIDENTE
    var batallaVSSeleccionada: String = ""
    var fechaSeleccionada: Date = Date(1)

    fun updateElementosSeleccionados(elemento: Equipamiento, seleccionado: Boolean) {
        _equipamientoSeleccionado.value = if (seleccionado) {
            _equipamientoSeleccionado.value + elemento
        } else {
            _equipamientoSeleccionado.value - elemento
        }
    }

    fun <T : Any> enviarMenu(objeto : T, envia : String){
        when(envia){
            "pais" -> if (objeto is Pais){ paisSeleccionado = objeto }
            "paisVS" -> if (objeto is Pais) {batallaVSSeleccionada = objeto.nombre}
            "alianza" -> if (objeto is Alianza) { alianzaSeleccionada = objeto }
        }
    }


    fun enviar(batallasGanadas: Int) {
        ListaPaisesFantasy.lista.add(
            PaisFantasy(
                pais = paisSeleccionado,
                alianza = alianzaSeleccionada,
                batallaVS = batallaVSSeleccionada,
                batallasGanadas = batallasGanadas,
                equipamiento = equipamientoSeleccionado.value,
                fechaGuerra = fechaSeleccionada,
            )
        )
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaNuevoPais(navController: NavController) {
    val viewModel: PantallaNuevoPaisViewModel = viewModel()
    var equipamiento by remember { mutableStateOf(ArrayList<Equipamiento>()) }
    var valorSlider by remember { mutableIntStateOf(0) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var calendarioVisible by remember { mutableStateOf(false) }
    var textoFechaSeleccionado by remember { mutableStateOf("") }
    var fechaSeleccionada by remember { mutableLongStateOf(calendar.timeInMillis) }

    val anno = calendar[Calendar.YEAR]
    val mes = calendar[Calendar.MONTH]
    val dia = calendar[Calendar.DAY_OF_MONTH]
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)



    Column {
        Text(text = "Añadir país", fontSize = 16.sp)
        Text(text = "Elige un país: ")
        menuDesplegable(opciones = ListaPaises.lista, envia = "pais")

        Text(text = "Elige una alianza: ")
        menuDesplegable(opciones = Alianza.values().asList(), envia = "alianza")

        Text(text = "Selecciona el equipamiento (1 o más): ")
        Column {
            Equipamiento.values().forEach {
                CheckBoxEquipamiento(
                    equipamiento = it,
                )
            }
        }

        Text(text = "¿Contra quién luchó?")
        menuDesplegable(opciones = ListaPaises.lista, envia = "paisVS")

        Text(text = "¿Cuántas batallas ganó?")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = valorSlider.toString(),
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 6.dp)
            )
            Slider(
                steps = 51, // 1 a 50, mas el de 0
                valueRange = 0f..50f,
                value = valorSlider.toFloat(),
                onValueChange = {
                    valorSlider = it.toInt()
                },
            )
        }

        Text(text = "¿Cuándo combatieron?")
        TextButton(
            onClick = {
                calendarioVisible = true
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ){
                Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Fecha")
                Text(text = "Elegir fecha")
            }
        }
        Text(text = "Fecha seleccionada: $textoFechaSeleccionado")

        if (calendarioVisible) {
            DatePickerDialog(
                onDismissRequest = { calendarioVisible = false },
                confirmButton = {
                    TextButton(onClick = {
                        calendarioVisible = false
                        fechaSeleccionada = datePickerState.selectedDateMillis!!
                        textoFechaSeleccionado = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
                                .format(Date(fechaSeleccionada))
                    }) {
                        Text(text = "Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        calendarioVisible = false
                    }) {
                        Text(text = "Cancelar")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState
                )
            }
        }

        Button(onClick = {
            viewModel.enviar(valorSlider)
            navController.navigate("Lista")
        }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            Text(text = "Crear país")
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any> menuDesplegable(opciones: List<T>, envia : String) {
    val viewModel: PantallaNuevoPaisViewModel = viewModel()
    var menuExpandido by remember { mutableStateOf(false) }
    var opcionSeleccionada by remember { mutableStateOf<Any?>(null) }

    Column(
        Modifier.fillMaxWidth()
    ) {

        ExposedDropdownMenuBox(
            expanded = menuExpandido,
            onExpandedChange = {
                menuExpandido = it
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = if (opcionSeleccionada == null){""}
                        else opcionSeleccionada.toString(),
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
                            opcionSeleccionada = it
                            viewModel.enviarMenu(opcionSeleccionada as T, envia)
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
    val viewModel: PantallaNuevoPaisViewModel = viewModel()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = seleccionado,
            onCheckedChange = {
                seleccionado = it
                viewModel.updateElementosSeleccionados(equipamiento, seleccionado)
            },
        )
        Text(
            text = equipamiento.toString(),
            Modifier
                .fillMaxWidth()
                .clickable {
                    seleccionado = !seleccionado
                    viewModel.updateElementosSeleccionados(equipamiento, seleccionado)
                }
        )
    }

}
