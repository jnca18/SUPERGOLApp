package com.example.supergol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.supergol.network.ApiClient
import com.example.supergol.repository.SupergolRepository
import com.example.supergol.ui.theme.SupergolTheme
import com.example.supergol.viewmodel.SupergolViewModel
import com.example.supergol.viewmodel.SupergolViewModelFactory
import com.example.supergol.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SupergolTheme {
                val apiService = ApiClient.apiService
                val repository = SupergolRepository(apiService)
                val viewModel: SupergolViewModel = viewModel(
                    factory = SupergolViewModelFactory(repository)
                )
                NavGraph(viewModel = viewModel)
            }
        }
    }
}


