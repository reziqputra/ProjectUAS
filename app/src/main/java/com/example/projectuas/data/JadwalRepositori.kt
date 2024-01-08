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

    suspend fun save(Jadwal: Jadwal): String

    suspend fun update(Jadwal: Jadwal)

    suspend fun delete(JadwalId: String)

    fun getJadwalById(JadwalId: String): Flow<Jadwal>
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

    override suspend fun save(Jadwal: Jadwal): String {
        return try {
            val documentReference = firestore.collection("Jadwal").add(Jadwal).await()

            firestore.collection("Jadwal").document(documentReference.id)
                .set(Jadwal.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        }catch (e:Exception){
            Log.w(ContentValues.TAG, "Error adding document",e)
            "Gagal $e"
        }
    }

    override suspend fun update(Jadwal: Jadwal) {
        firestore.collection("Jadwal").document(Jadwal.id).set(Jadwal).await()
    }

    override suspend fun delete(JadwalId: String) {
        firestore.collection("Jadwal").document(JadwalId).delete().await()
    }

    override fun getJadwalById(JadwalId: String): Flow<Jadwal> {
        return flow {
            val snapshot = firestore.collection("Jadwal").document(JadwalId).get().await()
            val Jadwal = snapshot.toObject(Jadwal::class.java)
            emit(Jadwal!!)
        }.flowOn(Dispatchers.IO)
    }

}