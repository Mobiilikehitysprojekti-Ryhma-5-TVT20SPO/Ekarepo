package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class VictoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=inflater.inflate(R.layout.victory_fragment, container, false)
        val playAgainBut : Button = binding.findViewById(R.id.playAgBut)
        val backToStart : Button = binding.findViewById(R.id.backStartBut)
        playAgainBut.setOnClickListener{view : View->view.findNavController().navigate(R.id.action_victoryFragment_to_gameFragment)}
        backToStart.setOnClickListener{view : View->view.findNavController().navigate(R.id.action_victoryFragment_to_titleFragment)}
        return binding
    }
}