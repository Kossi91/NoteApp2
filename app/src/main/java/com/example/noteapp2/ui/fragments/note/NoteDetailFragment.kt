package com.example.noteapp2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp2.App
import com.example.noteapp2.R
import com.example.noteapp2.data.model.NoteModel
import com.example.noteapp2.databinding.FragmentNoteDetailBinding
import com.example.noteapp2.extensions.setBackStackData

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddText.setOnClickListener{
            val title = binding.etAdd.text.toString()
            App().getInstance()?.noteDao()?.insertNote(NoteModel(title))
            findNavController().navigateUp()
           // setBackStackData("key", title , true)
        }
    }
}