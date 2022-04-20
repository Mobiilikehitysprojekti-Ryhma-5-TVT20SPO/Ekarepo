package com.example.hirsipuu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class VictoryFragment : Fragment() {
    private var word = ""                       //Tehdään tyhjä String muuttuja johon asetetaan pelin sana
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("key")?.let { word=it }        //Asetetaan pelin sana String muuttujaan
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=inflater.inflate(R.layout.victory_fragment, container, false)       //Haetaan view bindiin
        val playAgainBut : Button = binding.findViewById(R.id.playAgBut)                            //haetaan kaikki käytettävät widgetit
        val backToStart : Button = binding.findViewById(R.id.backStartBut)
        val wordtext : TextView = binding.findViewById(R.id.wordtext)
        val wt = "word was:$word"                                                                   //tehdään String jossa pelin sana mukana
        wordtext.text = wt                                                                          //Laitetaan String textviewiin
        playAgainBut.setOnClickListener{view : View->view.findNavController().navigate(R.id.action_victoryFragment_to_gameFragment)}    //Mennään peli fragmenttiin
        backToStart.setOnClickListener{view : View->view.findNavController().navigate(R.id.action_victoryFragment_to_titleFragment)}    //Mennään alku fragmenttiin
        return binding
    }


}