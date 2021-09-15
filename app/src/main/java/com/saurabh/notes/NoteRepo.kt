package com.saurabh.notes

import androidx.lifecycle.LiveData

class NoteRepo(private  val noteDAO: NoteDAO) {

    val allNote: LiveData<List<Note>> = noteDAO.getAllNotes()

    suspend fun insert(note:Note){
        noteDAO.insert(note)
    }

    suspend fun delete(note:Note) {
        noteDAO.delete(note)
    }

}