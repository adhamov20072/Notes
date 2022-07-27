package com.alimardon.notes.Activity.room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert()
    suspend fun insertNote(note: Note)
    @Delete()
    suspend fun deleteNote(note: Note)
    @Update()
    suspend fun updateNote(note: Note)
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): ArrayList<Note>
}