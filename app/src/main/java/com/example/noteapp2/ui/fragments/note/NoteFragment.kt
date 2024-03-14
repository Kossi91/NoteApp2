package com.example.noteapp2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp2.R
import com.example.noteapp2.databinding.FragmentNoteBinding
import com.example.noteapp2.utils.PreferenceHelper

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() = with(binding) {
        val preferences = PreferenceHelper()
        preferences.unit(requireContext())
        saveBtn.setOnClickListener{
            val et = edTxt.text.toString()
            preferences.text = et
            saveTxt.text = et
        }
        saveTxt.text= preferences.text
    }
}