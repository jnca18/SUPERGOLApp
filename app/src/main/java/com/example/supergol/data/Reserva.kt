package com.example.supergol.data.model

data class Reserva(
    val sede: String,
    val tipo: String,
    val fecha: String,
    val hora: String,
    val id: String? = null,
    val usuario: String = "",
    val pagado: Boolean = false
)

