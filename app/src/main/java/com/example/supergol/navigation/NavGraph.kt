// archivo: com/example/supergol/navigation/NavGraph.kt

package com.example.supergol.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supergol.ui.*
import com.example.supergol.viewmodel.SupergolViewModel


@Composable
fun NavGraph(viewModel: SupergolViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }


        composable("prices") {
            PriceScreen(navController = navController)
        }

        composable("reserve") {
            ReservationScreen(navController = navController, viewModel = viewModel)
        }

        composable("details") {
            DetailScreen(navController = navController)
        }

        composable("reservationList") {
            ReservationListScreen(navController = navController)
        }
    }
}




