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
import android.widget.Toast


class GameFragment : Fragment() {

    private var lettersUsed: String = ""
    private var underscoreWord: String = ""
    private lateinit var wordToGuess: String
    private var word: String = ""
    var vaihda:Int = 1
    lateinit var bind:View
    private val maxTries = 8

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        val binding=inflater.inflate(R.layout.game_fragment, container, false)
        val guessWordText: TextView = binding.findViewById(R.id.guessWordText)
        val guessInputText: TextView = binding.findViewById(R.id.guessInputText)
        val usedLettersText: TextView = binding.findViewById(R.id.LettersUsedText)
        bind=binding
        val btn = binding.findViewById<Button>(R.id.guessBut)
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(R.drawable.hirsipuu11)


        word = "niko"        //Tähän SQlitellä tehdyistä sanoista randomilla joku
        generateUnderscores(word) // luodaan kyseiselle sanalle alaviivat alempana olevassa generateUnderscores toteutuksessa
        guessWordText.text = underscoreWord

        btn.setOnClickListener {
            var guessWord = guessInputText.text.toString()
            if (guessWord.length > 1) {
                // tarkistus että ei voi arvata kun yhden kirjaimen kerrallaan, muuten ei tapahdu mitään
            } else {
                if(guessWord.isNotEmpty()){
                    if(lettersUsed.contains(guessWord)){

                    //tässä tarkistuksia että kirjaimen arvauskenttä ei ole tyhjä, eikä samaa kirjainta voi arvata uudelleen


                    }else{

                        val letter = guessWord.get(0)

                        play(letter)
                        usedLettersText.text = lettersUsed
                        guessWordText.text = underscoreWord


                    }

                }

                else{

                }

            }
        }
        return binding

    }



    fun generateUnderscores(word: String) {     //tässä luodaan arvattavalle sanalle alaviivat ettei arvattavaa sanaa näe
        val sb = StringBuilder()                //esim. Jos sana olisi: joku niin siitä tulee _ _ _ _
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
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

        var finalUnderscoreWord =  underscoreWord   //eli jos sanasta löytyy arvattu kirjain ^^ niin tämä paljastaa ne kirjaimet
        indexes.forEach { index ->                  // _ _ _ _ -> j _ _ _ jos arvattu kirjain j sanasta joku
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }
        if (indexes.isEmpty()) {                    //Jos kirjainta ei löydy sanasta, kutsutaan vaihdakuva joka lisää hirsipuulle elementtejä
            vaihda = vaihdakuva(vaihda,bind)
        }
        underscoreWord = finalUnderscoreWord

        if(underscoreWord.equals(word,true)){   //Jos alaviivasana on paljastettu ja on sama kuin arvattava sana, navigoidaan voitto ruutuun kutsumalla victory()
            victory()
        }

    }
    fun victory(){
        findNavController().navigate(R.id.action_gameFragment_to_victoryFragment)   // navigoi voitto fragmenttiin kutsuessa
    }
    fun lose(){

        findNavController().navigate(R.id.action_gameFragment_to_loseFragment)      // navigoi häviö fragmenttiin kutsuessa

    }

    private fun vaihdakuva(vaihda:Int,binding:View):Int {
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        val numero = vaihda+1
        val kuva= when (numero) {           //Kun arvataan väärin tämä vaihtaa käsintehtyjä hirsipuukuvia ja lisää elementtejä puuhun
            2-> R.drawable.hirsipuu22
            3-> R.drawable.hirsipuu33
            4-> R.drawable.hirsipuu44
            5-> R.drawable.hirsipuu55
            6-> R.drawable.hirsipuu66
            7-> R.drawable.hirsipuu77
            else-> R.drawable.hirsipuu88
        }
        if(numero == maxTries){         //Kun arvauksia tulee liikaa ja tikku-ukko on kokonaan "piirretty" kutsutaan lose() joka navigoi pelin häviö ruutuun
            lose()
        }


        imageView.setImageResource(kuva)
        return numero
    }
}