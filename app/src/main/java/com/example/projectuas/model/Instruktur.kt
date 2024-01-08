package com.example.projectuas.model

data class Instruktur(
    val id: String,
    val nama: String,
    val telpon: String,
){
    constructor(): this("","","")
}
