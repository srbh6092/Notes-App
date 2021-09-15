package com.saurabh.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch


@InternalCoroutinesApi
class NoteViewModel(application: Application):AndroidViewModel(application) {

    private val repo:NoteRepo
    val  allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDB.getDB(application).getNoteDAO()
        repo = NoteRepo(dao)
        allNotes = repo.allNote
    }

    fun insertNote(note:Note)= viewModelScope.launch(Dispatchers.IO){
        repo.insert(note)
    }

    fun deleteNote(note:Note)= viewModelScope.launch(Dispatchers.IO){
        repo.delete(note)
    }

}