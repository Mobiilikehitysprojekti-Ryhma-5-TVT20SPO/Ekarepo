package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=inflater.inflate(R.layout.game_fragment, container, false)
        
        return binding
    }
    fun victory(){
        findNavController().navigate(R.id.action_gameFragment_to_victoryFragment)
    }
    fun lose(){
        findNavController().navigate(R.id.action_gameFragment_to_loseFragment)
    }
}