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
        val wordarray = arrayOf("testing1","testing2")
        val numarray = arrayOf(1,2)
        var text = ""
        var textnum = ""
        val binding = inflater.inflate(R.layout.word_list_fragment, container, false)
        val list : TextView = binding.findViewById(R.id.wordlisttext)
        val listnum : TextView = binding.findViewById(R.id.wordlistnumtext)
        for (words in wordarray){
            text += (words+System.getProperty("line.separator"))
        }
        for (num in numarray){
            textnum += (num.toString()+System.getProperty("line.separator"))
        }
        list.setText(text)
        listnum.setText(textnum)
        val backToStart : Button = binding.findViewById(R.id.backListBut)
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_wordListFragment_to_titleFragment)}
        return binding
    }
}