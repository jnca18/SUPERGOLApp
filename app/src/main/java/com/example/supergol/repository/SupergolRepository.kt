package com.example.supergol.repository

import com.example.supergol.data.model.Reserva
import com.example.supergol.network.ApiService


class SupergolRepository(private val apiService: ApiService) {

    suspend fun crearReserva(reserva: Reserva): Reserva {
        return apiService.crearReserva(reserva)
    }


    // ya existente
    suspend fun getMatches() = apiService.getMatches()



    suspend fun getReservas(): List<Reserva> {
        return apiService.getReservas()
    }

}

