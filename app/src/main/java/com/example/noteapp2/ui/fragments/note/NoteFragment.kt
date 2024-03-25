package com.example.noteapp2.ui.fragments.note

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp2.App
import com.example.noteapp2.R
import com.example.noteapp2.data.model.NoteModel
import com.example.noteapp2.databinding.FragmentNoteBinding
import com.example.noteapp2.interfaces.OnClickItem
import com.example.noteapp2.ui.adapter.NoteAdapter

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this)
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
        initialize()
        setupListener()
        getData()
    }

    private fun initialize() {
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListener() {
        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment,
                null,
//                navOptions {
//                    anim {
//                        enter = R.anim.slide_in_right
//                        exit = R.anim.slide_out_left
//                    }
//                }
            )
        }
    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить")
            setPositiveButton("Да") { diolog, which ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { diolog, which ->
                diolog.cancel()
            }
            show()
        }
        builder.create()
    }
}