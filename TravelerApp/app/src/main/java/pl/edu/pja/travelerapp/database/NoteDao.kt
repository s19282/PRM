package pl.edu.pja.travelerapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.edu.pja.travelerapp.model.NoteDTO

@Dao
interface NoteDao
{
    @Insert
    fun insert(note: NoteDTO): Long

    @Query("SELECT * FROM Note;")
    fun selectAll(): List<NoteDTO>

    @Query("SELECT * FROM Note where imageName = :imageName;")
    fun selectByImageName(imageName: String): NoteDTO

    @Query("DELETE FROM Note WHERE id = :id")
    fun delete(id: Long)

    @Query("UPDATE Note SET note = :note WHERE id = :id")
    fun update(id: Long, note: String)

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getById(id: Long) : NoteDTO
}