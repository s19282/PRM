package pl.edu.pja.travelerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val note: String,
    val imageName: String,
    val city: String,
    val country: String
)