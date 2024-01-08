package com.example.projectuas.model

data class Jadwal(
    val id: String,
    val tanggal: String,
    val waktu: String,
    val nama_Instruktur: String,
    val nama_Kursus: String
){
    constructor(): this("","","","","")
}
