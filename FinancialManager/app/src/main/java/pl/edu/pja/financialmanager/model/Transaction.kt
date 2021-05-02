package pl.edu.pja.financialmanager.model

import android.graphics.drawable.Drawable
import java.time.LocalDate

data class Transaction(
        val amount: Double,
        val place: String,
        val date: LocalDate,
        val category: Int,
        val type: Int,
//        val image: Drawable
)