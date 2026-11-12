package com.example.myapp009amynotehub.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    // CREATE - Vloží novou poznámku do databáze
    @Insert
    suspend fun insert(note: Note)

    // READ - Načte všechny poznámky a vrátí je jako Flow, které umožňuje pozorování změn
    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNotes(): Flow<List<Note>>

    // UPDATE - Aktualizuje existující poznámku
    @Update
    suspend fun update(note: Note)

    // DELETE - Smaže poznámku
    @Delete
    suspend fun delete(note: Note)

}