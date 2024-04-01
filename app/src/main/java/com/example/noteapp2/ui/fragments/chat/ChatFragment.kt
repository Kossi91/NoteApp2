package com.example.noteapp2.ui.fragments.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp2.databinding.FragmentChatBinding
import com.example.noteapp2.ui.adapter.ChatAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {

    private lateinit var binding:FragmentChatBinding
    private var chatAdapter = ChatAdapter()
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setupListener() {
        binding.sendBtn.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.etMessage.text.toString()
            )
            db.collection("user").add(user).addOnCanceledListener {  }
            binding.etMessage.text.clear()
        }
    }
}