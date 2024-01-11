package com.example.projectuas.data

import android.content.ContentValues
import android.util.Log
import com.example.projectuas.model.Instruktur
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface InstrukturRepositori {
    fun getAll(): Flow<List<Instruktur>>

    suspend fun save(instruktur: Instruktur): String

    suspend fun update(instruktur: Instruktur)

    suspend fun delete(instrukturId: String)

    fun getInstrukturById(instrukturId: String): Flow<Instruktur>
}
class InstrukturRepositoriImpl(private val firestore: FirebaseFirestore): InstrukturRepositori{
    override fun getAll(): Flow<List<Instruktur>> = flow{
        val snapshot = firestore.collection("Instruktur")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val Instruktur = snapshot.toObjects(Instruktur::class.java)
        emit(Instruktur)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(instruktur: Instruktur): String {
        return try {
            val documentReference = firestore.collection("Instruktur").add(instruktur).await()

            firestore.collection("Instruktur").document(documentReference.id)
                .set(instruktur.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        }catch (e:Exception){
            Log.w(ContentValues.TAG, "Error adding document",e)
            "Gagal $e"
        }
    }

    override suspend fun update(instruktur: Instruktur) {
        firestore.collection("Instruktur").document(instruktur.id).set(instruktur).await()
    }

    override suspend fun delete(instrukturId: String) {
        firestore.collection("Instruktur").document(instrukturId).delete().await()
    }

    override fun getInstrukturById(instrukturId: String): Flow<Instruktur> {
        return flow {
            val snapshot = firestore.collection("Instruktur").document(instrukturId).get().await()
            val instruktur = snapshot.toObject(Instruktur::class.java)
            emit(instruktur!!)
        }.flowOn(Dispatchers.IO)
    }

}