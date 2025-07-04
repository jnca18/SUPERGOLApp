package com.example.supergol.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.supergol.data.model.Reserva
import com.example.supergol.viewmodel.SupergolViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.supergol.repository.SupergolRepository
import com.example.supergol.network.ApiClient
import com.example.supergol.viewmodel.SupergolViewModelFactory

@Composable
fun ReservationListScreen(navController: NavController) {
    val viewModel: SupergolViewModel = viewModel(
        factory = SupergolViewModelFactory(SupergolRepository(ApiClient.apiService))
    )

    val reservas by viewModel.reservas.observeAsState(emptyList())
    val error by viewModel.error.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchReservas()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Reservas") })
        },
        bottomBar = {
            com.example.supergol.ui.components.BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            if (error != null) {
                Text("Error: $error", color = MaterialTheme.colors.error)
            } else {
                LazyColumn {
                    items(reservas) { reserva ->
                        Card(modifier = Modifier.fillMaxWidth().padding(4.dp), elevation = 4.dp) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("Sede: ${reserva.sede}")
                                Text("Tipo: ${reserva.tipo}")
                                Text("Fecha: ${reserva.fecha}")
                            }
                        }
                    }
                }
            }
        }
    }
}
