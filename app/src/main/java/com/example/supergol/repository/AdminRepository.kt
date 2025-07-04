package com.example.supergol.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.example.supergol.data.model.Reserva
import kotlinx.coroutines.tasks.await

class AdminRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun obtenerReservas(): List<Reserva> {
        val snapshot = db.collection("reservas").get().await()
        return snapshot.documents.mapNotNull { it.toObject(Reserva::class.java) }
    }
}
