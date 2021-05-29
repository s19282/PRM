package pl.edu.pja.travelerapp.model

import android.graphics.Bitmap

data class Picture (
    val id: Long,
    val note: String,
    val imageName: String,
    val image: Bitmap
)