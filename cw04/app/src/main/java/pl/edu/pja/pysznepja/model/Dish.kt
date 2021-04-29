package pl.edu.pja.pysznepja.model

import android.graphics.drawable.Drawable

data class Dish(
        val name: String,
        val ingredients: List<String>,
        val photo: Drawable
)
