package pl.edu.pja.travelerapp.database

import androidx.room.Dao
import androidx.room.Insert
import pl.edu.pja.travelerapp.model.NoteDTO

@Dao
interface NoteDao
{
    @Insert
    fun insert(note: NoteDTO): Long
}