package com.example.noteapp2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp2.data.db.daos.NoteDao
import com.example.noteapp2.data.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}