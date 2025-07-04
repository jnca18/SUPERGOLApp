package com.example.supergol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.supergol.repository.SupergolRepository

class SupergolViewModelFactory(
    private val repository: SupergolRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SupergolViewModel::class.java)) {
            return SupergolViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




