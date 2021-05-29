package pl.edu.pja.traveler.database

import androidx.room.Dao
import androidx.room.Insert
import pl.edu.pja.traveler.model.NoteDTO

@Dao
interface NoteDao
{
    @Insert
    fun insert(note: NoteDTO): Long
}