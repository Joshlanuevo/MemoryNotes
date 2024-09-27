package com.example.memorynotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorynotes.R
import com.example.memorynotes.framework.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    private lateinit var noteListView: RecyclerView
    private lateinit var addNoteButton: FloatingActionButton
    private lateinit var viewModel: ListViewModel
    private lateinit var loadingView: ProgressBar
    private val notesListAdapter = NoteListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteListView = view.findViewById(R.id.noteListView)
        noteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        addNoteButton = view.findViewById(R.id.addNote)
        loadingView = view.findViewById(R.id.loadingView)

        addNoteButton.setOnClickListener {
            goToNoteDetails()
        }

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { noteList ->
            loadingView.visibility = View.GONE
            noteListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(noteList.sortedByDescending { it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToNote()
        Navigation.findNavController(requireView()).navigate(action)
    }
}