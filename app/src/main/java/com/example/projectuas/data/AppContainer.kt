package com.example.projectuas.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer{
    val jadwalRepositori : JadwalRepositori
    val instrukturRepositori: InstrukturRepositori

}


class appsContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()


    override val jadwalRepositori: JadwalRepositori by lazy {
        JadwalRepositoriImpl(firestore)
    }



    override val instrukturRepositori: InstrukturRepositori by lazy {
        InstrukturRepositoriImpl(firestore)


    }
}

