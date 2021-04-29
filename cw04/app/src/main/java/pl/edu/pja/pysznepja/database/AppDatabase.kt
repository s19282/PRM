package pl.edu.pja.pysznepja.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.edu.pja.pysznepja.model.DishDTO

@Database(
    entities = [DishDTO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dish: DishDao

    companion object {
        fun open(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java, "dishdb"
        ).build()
    }
}