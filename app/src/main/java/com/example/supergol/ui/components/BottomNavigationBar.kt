package com.example.supergol.ui.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Reservas") },
            label = { Text("Reservar") },
            selected = false,
            onClick = { navController.navigate("reserve") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Precios") },
            label = { Text("Precios") },
            selected = false,
            onClick = { navController.navigate("prices") }
        )
    }
}
