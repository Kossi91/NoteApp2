package com.example.noteapp2.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp2.R
import com.example.noteapp2.data.model.NoteModel
import com.example.noteapp2.databinding.FragmentNoteBinding
import com.example.noteapp2.extensions.getBackStackData
import com.example.noteapp2.ui.adapter.NoteAdapter
import com.example.noteapp2.utils.PreferenceHelper

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter()
    private val list: ArrayList<NoteModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initalize()
        setupListener()
        getData()
    }

    private fun initalize() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() {
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }

    private fun getData() {
        getBackStackData<String>("key") { data ->
            val noteModel = NoteModel(data)
            list.add(noteModel)
            noteAdapter.submitList(list)
        }
    }
}

//    private fun setupListener() = with(binding) {
//        val preferences = PreferenceHelper()
//        preferences.unit(requireContext())
//        saveBtn.setOnClickListener{
//            val et = edTxt.text.toString()
//            preferences.text = et
//            saveTxt.text = et
//        }
//        saveTxt.text= preferences.text
//    }
//}