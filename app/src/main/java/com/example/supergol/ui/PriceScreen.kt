package com.example.supergol.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.supergol.R
import com.example.supergol.ui.components.BottomNavigationBar

@Composable
fun PriceScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Tabla de precios", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Fútbol 5 - Día: $60.000 / Noche: $80.000")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fútbol 7 - Día: $90.000 / Noche: $120.000")
            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Imagen ahora está dentro del Column
            Image(
                painter = painterResource(id = R.drawable.sample_cancha),
                contentDescription = "Imagen cancha",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(vertical = 8.dp)
            )
        }
    }
}

