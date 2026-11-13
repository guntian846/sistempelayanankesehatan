package com.example.sistempelayanankesehatan

import java.io.Serializable

data class Faskes(
    val id: Int,
    val nama: String,
    val kategori: String,
    val alamat: String,
    val jamOperasional: String,
    val dokter: String,
    val deskripsi: String
) : Serializable