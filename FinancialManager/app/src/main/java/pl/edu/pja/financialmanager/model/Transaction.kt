package pl.edu.pja.financialmanager.model

import android.graphics.drawable.Drawable
import java.time.LocalDate

data class Transaction(
        var amount: Double,
        var place: String,
        var date: LocalDate,
        var category: Int,
        var type: Int,
//        val image: Drawable
)