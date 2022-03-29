package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding=inflater.inflate(R.layout.game_fragment, container, false)
        val btn = binding.findViewById<Button>(R.id.guessBut)
        var vaihda:Int = 1
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(R.drawable.hirsipuu1)

        btn.setOnClickListener( View.OnClickListener {
            vaihda=vaihdakuva(vaihda,binding);
        })


        return binding
    }
    fun victory(){
        findNavController().navigate(R.id.action_gameFragment_to_victoryFragment)
    }
    fun lose(){
        findNavController().navigate(R.id.action_gameFragment_to_loseFragment)
    }
    private fun vaihdakuva(vaihda:Int,binding:View):Int {
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        val numero = vaihda+1
        val kuva= when (numero) {
            2-> R.drawable.hirsipuu2
            3-> R.drawable.hirsipuu3
            4-> R.drawable.hirsipuu4
            5-> R.drawable.hirsipuu5
            6-> R.drawable.hirsipuu6
            7-> R.drawable.hirsipuu7
            else-> R.drawable.hirsipuu8
        }


        imageView.setImageResource(kuva)
        return numero
    }
}