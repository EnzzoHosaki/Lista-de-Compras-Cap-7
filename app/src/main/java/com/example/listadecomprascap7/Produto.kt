package com.example.listadecomprascap7

import android.graphics.Bitmap

data class Produto(
    val nome: String,
    val quantidade: Int,
    val valor: Double,
    val foto: Bitmap? = null
)