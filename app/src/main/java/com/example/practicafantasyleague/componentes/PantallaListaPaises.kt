package com.example.practicafantasyleague.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.example.practicafantasyleague.datos.ListaPaisesFantasy
import com.example.practicafantasyleague.datos.PaisFantasy

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}

@Composable
fun PantallaListaPaises() {
    var listaPaisesFantasy = remember { mutableStateListOf<PaisFantasy>() }
    listaPaisesFantasy.swapList(ListaPaisesFantasy.lista)

    // Function to refresh the list
    val onUpdateClick = {
        // Do something that updates the list
        // ...
        listaPaisesFantasy.swapList(ListaPaisesFantasy.lista)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TODO SearchBar

        LazyColumn {
            items(count = listaPaisesFantasy.size) {
                PaisComponenteSimple(paisFantasy = listaPaisesFantasy.get(it))
            }
        }
        Row {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = null)
                Text(text = "Añadir")
            }
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = "Eliminar")
                Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
            }
        }
    }
}