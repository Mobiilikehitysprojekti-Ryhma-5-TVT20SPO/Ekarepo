package com.example.hirsipuu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class LoseFragment : Fragment () {
    private var word = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("key")?.let { word=it }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.lose_fragment, container, false)
        val startAgainBut : Button = binding.findViewById(R.id.newGameBut)
        val backToStart : Button = binding.findViewById(R.id.backToStartBut)
        val wordtext : TextView = binding.findViewById(R.id.textView2)
        val wt = "word was:$word"
        wordtext.text = wt
        startAgainBut.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_loseFragment_to_gameFragment)}
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_loseFragment_to_titleFragment)}
        return binding
    }
}