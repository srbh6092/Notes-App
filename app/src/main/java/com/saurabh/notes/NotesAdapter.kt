package com.saurabh.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener: NotesAdapterInterface): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner  class NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val textNote: TextView =itemView.findViewById<TextView>(R.id.textView)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textNote.text=currentNote.text
    }

    override fun getItemCount(): Int = allNotes.size

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NotesAdapterInterface {
    fun onItemClicked(note:Note)
}