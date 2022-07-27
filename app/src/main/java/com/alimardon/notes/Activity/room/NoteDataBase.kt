package com.alimardon.notes.Activity.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    object DatabaseBuilder {
        private var INSTANSE: NoteDataBase? = null
        fun getdatabase(context: Context): NoteDataBase {
            if (INSTANSE == null) {
                synchronized(NoteDataBase::class.java) {
                    INSTANSE = buildDatabase(context)
                }
            }
            return INSTANSE!!
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDataBase::class.java, "note_baza"
            ).build()
    }
}