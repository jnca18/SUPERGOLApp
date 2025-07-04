package com.example.supergol.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.supergol.ui.components.BottomNavigationBar
import com.example.supergol.viewmodel.SupergolViewModel
import androidx.lifecycle.LiveData
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.LaunchedEffect
import com.example.supergol.data.model.Match

@Composable
fun HomeScreen(navController: NavController, viewModel: SupergolViewModel) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "SUPERGOL",
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.navigate("reserve") }) {
                Text(text = "Reservar cancha")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.navigate("prices") }) {
                Text(text = "Ver precios")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.navigate("details") }) {
                Text(text = "Ver detalles")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Partidos Recientes:",
                style = MaterialTheme.typography.h6
            )

            Button(onClick = { navController.navigate("reservationList") }) {
                Text("Ver reservas")
            }


            LaunchedEffect(Unit) {
                viewModel.fetchMatches()
            }

            val matches by viewModel.matches.observeAsState(emptyList())
            val errorMessage by viewModel.error.observeAsState()

            if (errorMessage != null) {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colors.error
                )
            } else if (matches.isEmpty()) {
                Text("No hay partidos para mostrar.")
            } else {
                LazyColumn {
                    items(matches) { match ->
                        MatchItem(match)
                    }
                }
            }
        }
    }
}

@Composable
fun MatchItem(match: Match) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "${match.equipoLocal} vs ${match.equipoVisitante}")
            Text(text = "Hora: ${match.hora}")
            Text(text = "Lugar: ${match.lugar}")
        }
    }
}



