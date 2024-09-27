package com.example.memorynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Note
import com.example.memorynotes.R
import java.text.SimpleDateFormat
import java.util.Date

class NoteListAdapter(var notes: ArrayList<Note>):RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val noteTitle: TextView = view.findViewById(R.id.title)
        private val noteConent: TextView = view.findViewById(R.id.content)
        private val noteDate: TextView = view.findViewById(R.id.date)

        fun bind(note: Note) {
            noteTitle.text = note.title
            noteConent.text = note.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            noteDate.text = "Last updated: ${sdf.format(resultDate)}"
        }
    }
}