package com.alimardon.notes.Activity.room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
     fun insertNote(note: Note)
    @Delete
     fun deleteNote(note: Note)
    @Update
     fun updateNote(note: Note)
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): List<Note>
}