package com.example.supergol.network

import com.example.supergol.data.model.Match
import com.example.supergol.data.model.Reserva
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Partidos recientes
    @GET("matches")
    suspend fun getMatches(): List<Match>

    // Obtener reservas (opcional si deseas mostrar reservas luego)
    @GET("reservas")
    suspend fun getReservas(): List<Reserva>

    // Crear reserva
    @POST("reservas")
    suspend fun crearReserva(@Body reserva: Reserva): Reserva



}

