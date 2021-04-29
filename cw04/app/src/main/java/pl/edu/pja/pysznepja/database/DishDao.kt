package pl.edu.pja.pysznepja.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.edu.pja.pysznepja.model.DishDTO

@Dao
interface DishDao
{
    @Insert
    fun insert(dish: DishDTO): Long

    @Query("SELECT * FROM dish;")
    fun selectAll(): List<DishDTO>

    @Query("SELECT * FROM dish WHERE name = :name;")
    fun selectByName(name: String): List<DishDTO>
}