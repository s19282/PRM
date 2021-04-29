package pl.edu.pja.pysznepja

import pl.edu.pja.pysznepja.database.AppDatabase
import pl.edu.pja.pysznepja.model.Dish

object Shared {
    var db: AppDatabase? = null
    val dishList = mutableListOf<Dish>()
}