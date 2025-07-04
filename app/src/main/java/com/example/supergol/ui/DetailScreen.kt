package com.example.supergol.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.supergol.ui.components.BottomNavigationBar

@Composable
fun DetailScreen(navController: NavController) {
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text("Detalles de la cancha", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Reglamento:")
            Text("- Llegar 10 minutos antes.")
            Text("- No se permite el ingreso de bebidas alcohólicas.")
            Text("- El uso del uniforme es obligatorio.")
        }
    }
}