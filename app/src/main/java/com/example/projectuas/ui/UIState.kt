package com.example.projectuas.ui

import com.example.projectuas.model.Instruktur
import com.example.projectuas.model.Jadwal

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val id: String = "",
    val tanggal: String = "",
    val waktu: String = "",
    val nm_instruktur: String = "",
    val nm_kursus: String = "",
)

fun AddEvent.toJadwal() = Jadwal(
    id = id,
    tanggal = tanggal,
    waktu = waktu,
    nama_Instruktur = nm_instruktur,
    nama_Kursus = nm_kursus,
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Jadwal.toDetailJadwal(): AddEvent =
    AddEvent(
        id = id,
        tanggal = tanggal,
        waktu = waktu,
        nm_instruktur = nama_Instruktur,
        nm_kursus = nama_Kursus,
    )

fun Jadwal.toUIStateJadwal(): AddUIState = AddUIState(
    addEvent = this.toDetailJadwal()
)

data class HomeUIState(
    val listJadwal: List<Jadwal> = listOf(),
    val dataLength: Int = 0
)

data class AddInsUIState(
    val addInsEvent: AddInsEvent = AddInsEvent(),
)

data class AddInsEvent(
    val id: String = "",
    val nama: String = "",
    val telpon: String = "",
)

fun AddInsEvent.toInstruktur() = Instruktur(
    id = id,
    nama = nama,
    telpon = telpon,
)

data class DetailInsUIState(
    val addInsEvent: AddInsEvent = AddInsEvent(),
)

fun Instruktur.toDetailInstruktur(): AddInsEvent =
    AddInsEvent(
        id = id,
        nama = nama,
        telpon = telpon,
    )

fun Instruktur.toUIStateInstruktur(): AddInsUIState = AddInsUIState(
    addInsEvent = this.toDetailInstruktur()
)