package pl.edu.pja.pysznepja.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class DishDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val ingredients: List<String>,
    @ColumnInfo(name = "photo_name")
    val photoName: String
)