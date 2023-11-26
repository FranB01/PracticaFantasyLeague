package com.example.practicafantasyleague.componentes

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.practicafantasyleague.datos.Alianza
import com.example.practicafantasyleague.datos.ListaPaisesFantasy
import com.example.practicafantasyleague.datos.PaisFantasy
import com.example.practicafantasyleague.ui.theme.PracticaFantasyLeagueTheme

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaPaises(navController: NavHostController) {
    var listaPaisesFantasy = remember { mutableStateListOf<PaisFantasy>() }
    listaPaisesFantasy.swapList(ListaPaisesFantasy.lista)

    var textoBarra by remember { mutableStateOf("") }
    var barraActiva by remember { mutableStateOf(false) }
    var alianzaSeleccionada by remember { mutableStateOf<Alianza?>(null) } // si es null no se ha filtrado
    var modoEliminar by remember { mutableStateOf(false) }

    // Function to refresh the list
    val onUpdateClick = {
        // Do something that updates the list
        // ...
        listaPaisesFantasy.swapList(ListaPaisesFantasy.lista)
    }

    fun modoEliminar() {
        // TODO
    }

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        SearchBar(
            query = textoBarra,
            onQueryChange = { textoBarra = it },
            onSearch = { barraActiva = false },
            active = barraActiva,
            onActiveChange = { barraActiva = it },
            placeholder = { Text(text = "Buscar") },
            leadingIcon = {
                if (barraActiva) { // solo sale el boton de cerrar si estamos en la barra
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Borrar",
                        modifier = Modifier.clickable { // Borra el texto, si esta vacio cierra la barra
                            if (textoBarra != "") {
                                textoBarra = ""
                                alianzaSeleccionada = null
                            } else {
                                barraActiva = false
                            }
                        }
                    )
                }
            },
            trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Alianza.values().forEach { // lista de alianzas
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clickable {
                            barraActiva = false
                            textoBarra = it.toString()
                            alianzaSeleccionada = it
                        }
                ) {
                    Text(text = it.toString())
                }
            }
        }

        LazyColumn(Modifier.weight(1f)) {
            items(count = listaPaisesFantasy.size) {
                if (alianzaSeleccionada == null || listaPaisesFantasy[it].alianza == alianzaSeleccionada) { // si no hay alianza seleccionada
                    PaisComponenteSimple(
                        paisFantasy = listaPaisesFantasy.get(it), // todos los paises
                        modoEliminar = modoEliminar,
                        eventoClick = {
                            navController.navigate("Detalle/$it")
                            Log.i("info", "Clickado componente nº $it")
                        },
                        //navController = navController
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            // boton añadir
            FloatingActionButton(
                onClick = { /*TODO*/ },
                Modifier.padding(horizontal = 20.dp)
            ) {
                Row(
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = null)
                    Text(text = "Añadir")
                }
            }
            // boton borrar
            FloatingActionButton(
                onClick = { modoEliminar = !modoEliminar },
                Modifier.padding(horizontal = 20.dp)
            ) {
                Row(
                    Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(text = "Eliminar")
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                }
            }
        }
    }
}

@Preview
@Composable
fun previewPantallaListaPaises() {
    ListaPaisesFantasy.cargarLista()
    PracticaFantasyLeagueTheme {
        Surface {
            PantallaListaPaises(rememberNavController())
        }
    }
}
