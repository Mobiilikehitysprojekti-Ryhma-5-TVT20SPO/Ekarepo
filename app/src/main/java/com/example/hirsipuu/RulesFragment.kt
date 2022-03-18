package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class RulesFragment : Fragment (){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.rules_fragment, container, false)
        val backToStart : Button = binding.findViewById(R.id.backRulerBut)
        backToStart.setOnClickListener{view:View->view.findNavController().navigate(R.id.action_rulesFragment_to_titleFragment)}
        return binding
    }
}