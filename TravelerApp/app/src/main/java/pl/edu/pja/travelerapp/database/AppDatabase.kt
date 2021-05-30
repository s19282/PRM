package pl.edu.pja.travelerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.edu.pja.travelerapp.model.NoteDTO

@Database(
    entities = [NoteDTO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val note: NoteDao

    companion object {
        fun open(context: Context) = Room.databaseBuilder(
            context, AppDatabase::class.java,"noteDB"
        ).build()
    }
}