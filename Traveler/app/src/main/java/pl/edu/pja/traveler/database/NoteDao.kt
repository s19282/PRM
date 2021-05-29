package pl.edu.pja.traveler.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pl.edu.pja.traveler.model.NoteDTO

@Dao
interface NoteDao
{
    @Insert
    fun insert(note: NoteDTO): Long

    @Query("SELECT * FROM Note;")
    fun selectAll(): List<NoteDTO>

    @Query("SELECT * FROM Note where imageName = :imageName;")
    fun selectByImageName(imageName: String): List<NoteDTO>

}