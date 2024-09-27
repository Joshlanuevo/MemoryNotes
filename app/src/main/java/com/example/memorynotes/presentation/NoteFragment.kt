package com.example.memorynotes.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.core.data.Note
import com.example.memorynotes.R
import com.example.memorynotes.framework.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteFragment : Fragment() {
    private lateinit var checkButton: FloatingActionButton
    private lateinit var titleView: EditText
    private lateinit var contentView: EditText
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note(0, "", "", 0L, 0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        checkButton = view.findViewById(R.id.checkButton)
        titleView = view.findViewById(R.id.titleView)
        contentView = view.findViewById(R.id.contentView)

        checkButton.setOnClickListener {
            if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                val time: Long = System.currentTimeMillis()
                currentNote.title = titleView.text.toString()
                currentNote.content = contentView.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(titleView).popBackStack()
                hideKeyboard()
            } else {
                Toast.makeText(context, "Something went wrong, please try again!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken, 0)
    }
}