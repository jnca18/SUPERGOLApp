package com.example.supergol.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.supergol.repository.SupergolRepository
import com.example.supergol.data.model.Match
import com.example.supergol.data.model.Reserva
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth



class SupergolViewModel(private val repository: SupergolRepository) : ViewModel() {

    private val _matches = MutableLiveData<List<Match>>()
    val matches: LiveData<List<Match>> = _matches



    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _reservaExitosa = MutableLiveData<Boolean>()
    val reservaExitosa: LiveData<Boolean> = _reservaExitosa


    fun fetchMatches() {
        viewModelScope.launch {
            try {
                _matches.value = repository.getMatches()
            } catch (e: Exception) {
                _error.value = "Error al cargar los partidos"
            }
        }
    }


    private val _reservas = MutableLiveData<List<Reserva>>()
    val reservas: LiveData<List<Reserva>> = _reservas

    fun fetchReservas() {
        viewModelScope.launch {
            try {
                _reservas.value = repository.getReservas()
            } catch (e: Exception) {
                _error.value = "Error al cargar las reservas"
            }
        }
    }

    fun crearReserva(reserva: Reserva) {
        viewModelScope.launch {
            try {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val correoUsuario = currentUser?.email ?: "desconocido"

                val reservaConUsuario = reserva.copy(
                    usuario = correoUsuario,
                    pagado = false
                )

                repository.crearReserva(reservaConUsuario)
                _reservaExitosa.value = true
            } catch (e: Exception) {
                _error.value = "Error al crear la reserva"
                _reservaExitosa.value = false
            }
        }
    }
}

