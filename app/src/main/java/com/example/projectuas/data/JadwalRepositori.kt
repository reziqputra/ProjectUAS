package com.example.projectuas.data

import android.app.DownloadManager
import android.content.ContentValues
import android.util.Log
import com.example.projectuas.model.Jadwal
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface JadwalRepositori {
    fun getAll(): Flow<List<Jadwal>>

    suspend fun save(jadwal: Jadwal): String

    suspend fun update(jadwal: Jadwal)

    suspend fun delete(jadwalId: String)

    fun getJadwalById(jadwalId: String): Flow<Jadwal>
}
class JadwalRepositoriImpl(private val firestore: FirebaseFirestore): JadwalRepositori{
    override fun getAll(): Flow<List<Jadwal>> = flow{
        val snapshot = firestore.collection("Jadwal")
            .orderBy("tanggal", Query.Direction.ASCENDING)
            .get()
            .await()
        val Jadwal = snapshot.toObjects(Jadwal::class.java)
        emit(Jadwal)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(jadwal: Jadwal): String {
        return try {
            val documentReference = firestore.collection("Jadwal").add(jadwal).await()

            firestore.collection("Jadwal").document(documentReference.id)
                .set(jadwal.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        }catch (e:Exception){
            Log.w(ContentValues.TAG, "Error adding document",e)
            "Gagal $e"
        }
    }

    override suspend fun update(jadwal: Jadwal) {
        firestore.collection("Jadwal").document(jadwal.id).set(jadwal).await()
    }

    override suspend fun delete(jadwalId: String) {
        firestore.collection("Jadwal").document(jadwalId).delete().await()
    }

    override fun getJadwalById(jadwalId: String): Flow<Jadwal> {
        return flow {
            val snapshot = firestore.collection("Jadwal").document(jadwalId).get().await()
            val Jadwal = snapshot.toObject(Jadwal::class.java)
            emit(Jadwal!!)
        }.flowOn(Dispatchers.IO)
    }

}