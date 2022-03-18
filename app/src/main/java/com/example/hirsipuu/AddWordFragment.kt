package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class AddWordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.add_word_fragment, container, false)
        val backToStart : Button = binding.findViewById(R.id.backToMainBut)
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_addWordFragment_to_titleFragment)}
        return binding
    }
}