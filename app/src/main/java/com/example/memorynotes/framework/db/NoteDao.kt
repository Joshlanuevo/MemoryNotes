package com.example.memorynotes.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNoteEntity(noteEntity: NoteEntity)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getNoteEntity(id: Long): NoteEntity?

    @Query("SELECT * FROM note")
    fun getAllNoteEntities(): List<NoteEntity>

    @Delete
    fun deleteNoteEntity(noteEntity: NoteEntity)
}
