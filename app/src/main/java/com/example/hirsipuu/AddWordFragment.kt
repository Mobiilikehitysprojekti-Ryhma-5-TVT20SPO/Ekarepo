package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class AddWordFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.add_word_fragment, container, false)    //Get fragment
        val addText : EditText = binding.findViewById(R.id.addWordText)     //Get textinput
        val addWordBut : Button = binding.findViewById(R.id.addWordBut)     //Get word add button
        val backToStart : Button = binding.findViewById(R.id.backToMainBut) //Get back button
        addWordBut.setOnClickListener{
            val text = addText.text.toString().lowercase()                                      //Get string from textinput
            if (text.isNotEmpty()){                                                             //Check that text is not empty
                if(text.contains("[0-9!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~∅·€°¶£¥¢√±∞§½←→↔↑↓©®™¡¿]".toRegex())) {  //Check if word contains special characters or numbers
                    Toast.makeText(context, "Word contains numbers or special characters", Toast.LENGTH_SHORT).show()   //Tell user why can't add word
                }else{
                    if (text.contains("[äÄöÖëËÿüÜïÏåÅêÊâÂîÎôÔûÛéÉáÁúÚóÓýÝíÍàÀèÈùÙìÌòÒãÃõÕÆæŒœ]".toRegex())) {                 //Check if word contains non english alphabets
                        Toast.makeText(context, "Word contains non english letters", Toast.LENGTH_SHORT).show() //Tell user why can't add word
                    } else {
                        Toast.makeText(context, "$text added to database", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(context, "Text empty", Toast.LENGTH_SHORT).show()    //Tell user why can't add word
            }
        }
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_addWordFragment_to_titleFragment)}  //Navigate to tittle fragment
        return binding
    }
}