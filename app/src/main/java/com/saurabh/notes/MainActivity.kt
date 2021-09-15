package com.saurabh.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity(), NotesAdapterInterface {

    @InternalCoroutinesApi
    lateinit var  viewModel: NoteViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        recyclerView.adapter=adapter

        viewModel = ViewModelProvider(this
            ,ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(application))
            .get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)}
        })
    }

    @InternalCoroutinesApi
    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text}Deleted",Toast.LENGTH_SHORT).show()
    }

    @InternalCoroutinesApi
    fun submitNote(view: View) {
        val textViewNote= findViewById<TextView>(R.id.textNote)
        val noteText= textViewNote.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            textViewNote.text=""
        }
    }
}