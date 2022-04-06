package com.example.hirsipuu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.TextView


class GameFragment : Fragment() {

    private var lettersUsed: String = ""
    private var underscoreWord: String = ""
    private lateinit var wordToGuess: String
    private var word: String = ""

    private val maxTries = 8
    private var currentTries = 0




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {


        val binding=inflater.inflate(R.layout.game_fragment, container, false)
        val guessWordText: TextView = binding.findViewById(R.id.guessWordText)
        val guessInputText: TextView = binding.findViewById(R.id.guessInputText)
        val usedLettersText: TextView = binding.findViewById(R.id.LettersUsedText)

        val btn = binding.findViewById<Button>(R.id.guessBut)
        var vaihda:Int = 1
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(R.drawable.hirsipuu11)

        usedLettersText.text = "Letters used: ${lettersUsed}"

        //currentTries = 0
        word = "niko"        //Tähän SQlitellä tehdyistä sanoista randomilla joku
        generateUnderscores(word)
        guessWordText.text = underscoreWord
        btn.setOnClickListener( View.OnClickListener {
           play()




        })


        return binding
    }



    fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_\t\t")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play (letter: Char) {


        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        word.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + word
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }
        if (indexes.isEmpty()) {

        }
        underscoreWord = finalUnderscoreWord



    }

    private fun vaihdakuva(vaihda:Int,binding:View):Int {
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        val numero = vaihda+1
        val kuva= when (numero) {
            2-> R.drawable.hirsipuu22
            3-> R.drawable.hirsipuu33
            4-> R.drawable.hirsipuu44
            5-> R.drawable.hirsipuu55
            6-> R.drawable.hirsipuu66
            7-> R.drawable.hirsipuu77
            else-> R.drawable.hirsipuu88
        }
        fun victory(){
            findNavController().navigate(R.id.action_gameFragment_to_victoryFragment)
        }
        fun lose(){

                findNavController().navigate(R.id.action_gameFragment_to_loseFragment)

        }

        imageView.setImageResource(kuva)
        return numero
    }
}