package com.example.supergol.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.supergol.data.model.Reserva
import com.example.supergol.viewmodel.AdminViewModel

@Composable
fun AdminScreen(navController: NavController, viewModel: AdminViewModel) {
    val reservas by viewModel.reservas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarReservas()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black)
    ) {
        Text(
            "Reservas Registradas",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (reservas.isEmpty()) {
            Text("No hay reservas aún", color = Color.White)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(reservas) { reserva ->
                    ReservaCard(reserva)
                }
            }
        }
    }
}

@Composable
fun ReservaCard(reserva: Reserva) {
    Card(
        backgroundColor = Color.DarkGray,
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Sede: ${reserva.sede}", color = Color.White)
            Text("Fecha: ${reserva.fecha}", color = Color.White)
            Text("Hora: ${reserva.hora}", color = Color.White)
            Text("Tipo: ${reserva.tipo}", color = Color.White)
            Text("Usuario: ${reserva.usuario}", color = Color.White)
            Text("Pagado: ${if (reserva.pagado) "Sí" else "No"}", color = Color.White)
        }
    }
}
