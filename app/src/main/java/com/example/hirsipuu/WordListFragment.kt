package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import java.lang.System.*

class WordListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val wordarray = arrayOf("testing1","testing2")      //Haetaan kaikki sanat
        val numarray = arrayOf(1,2)                         //Haetaan sanoille yritysten määrä
        var text = ""
        var textnum = ""
        val binding = inflater.inflate(R.layout.word_list_fragment, container, false)      //Otetaan View bindiin kiinni
        val list : TextView = binding.findViewById(R.id.wordlisttext)                                 //Haetaan sana lista
        val listnum : TextView = binding.findViewById(R.id.wordlistnumtext)                           //Haetaan yritys lista
        for (words in wordarray){
            text += (words+System.getProperty("line.separator"))                                      //Tehdään sana listasta yksi teksti rivin vaihdoilla
        }
        for (num in numarray){
            textnum += (num.toString()+System.getProperty("line.separator"))                          //Tehdään yritys listasta yksi teksti rivin vaihdoilla
        }
        list.setText(text)                                                                            //Asetetaan sana tekstit
        listnum.setText(textnum)                                                                      //Asetetaan yritysten tekstit
        val backToStart : Button = binding.findViewById(R.id.backListBut)                             //Kun nappia painetaan mennään title fragmenttiin
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_wordListFragment_to_titleFragment)}
        return binding
    }
}