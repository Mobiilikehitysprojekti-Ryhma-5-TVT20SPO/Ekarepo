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
import androidx.core.os.bundleOf
import com.example.hirsipuu.TitleFragment.Companion.newInstance
import javax.xml.validation.SchemaFactory.newInstance
import kotlin.random.Random


class GameFragment : Fragment() {

    private var lettersUsed: String = ""
    private var underscoreWord: String = ""
    private var word: String = ""
    var vaihda:Int = 1
    lateinit var bind:View
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
        bind=binding
        val btn = binding.findViewById<Button>(R.id.guessBut)
        val imageView = binding.findViewById<ImageView>(R.id.imageView2)
        imageView.setImageResource(R.drawable.hirsipuu11)

        word = getRandom()      //Tähän SQlitellä tehdyistä sanoista randomindexillä joku
        generateUnderscores(word) // luodaan kyseiselle sanalle alaviivat alempana olevassa generateUnderscores toteutuksessa
        guessWordText.text = underscoreWord

        btn.setOnClickListener {

            var guessWord = guessInputText.text.toString()
            if (guessWord.length > 1) {
                // tarkistus että ei voi arvata kun yhden kirjaimen kerrallaan, muuten ei tapahdu mitään
                Toast.makeText(context,"You can only guess one letter at a time!",Toast.LENGTH_SHORT).show()
            } else {
                if(guessWord.isNotEmpty()){
                    if(lettersUsed.contains(guessWord)){

                    //tässä tarkistus että samaa kirjainta voi arvata uudelleen
                        Toast.makeText(context,"You have already guessed this letter, try a different one!",Toast.LENGTH_SHORT).show()

                    }else{

                        val letter = guessWord.get(0)

                        play(letter)
                        usedLettersText.text = lettersUsed
                        guessWordText.text = underscoreWord


                    }

                }

                else{
                        Toast.makeText(context,"Your guess cannot be empty!",Toast.LENGTH_SHORT).show() // Eli arvaus ei voi olla tyhjä.
                }

            }
        }
        return binding

    }

    fun getRandom() : String {      // Hakee satunnaisen sanan Tietokannan sanoista.
        val db=Database(context)
        val randomIndex = Random.nextInt(db.loadWords().size)
        return db.loadWords()[randomIndex]
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


        lettersUsed += letter                      //Kun kirjaimia käytetään ne passataan lettersUsed muuttujaan joka liitetään usedLettersTextviewiin
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
        if (indexes.isEmpty()) { //Jos kirjainta ei löydy sanasta, kutsutaan vaihdakuva joka lisää hirsipuulle elementtejä
            vaihda = vaihdakuva(vaihda,bind)
            currentTries++
        }
        underscoreWord = finalUnderscoreWord

        if(underscoreWord.equals(word,true)){   //Jos alaviivasana on paljastettu ja on sama kuin arvattava sana, navigoidaan voitto ruutuun kutsumalla victory()
            victory(word)
        }

    }

    
    fun victory(word:String){
        val db = Database(context)
        db.updateTry(word,currentTries) // päivittää sanan yritysmäärää, kutsumalla updateTry() database välilehdestä.
        val bundle= bundleOf("key" to word)                                         //Tehdään voitto fragmentille bundle jossa pelin sana
        findNavController().navigate(R.id.action_gameFragment_to_victoryFragment,bundle)   // navigoi voitto fragmenttiin kutsuessa
    }
    fun lose(word:String){
        val bundle = bundleOf("key" to word)                                        //Tehdään voitto fragmentille bundle jossa pelin sana
        findNavController().navigate(R.id.action_gameFragment_to_loseFragment,bundle)      // navigoi häviö fragmenttiin kutsuessa


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
            lose(word)
        }


        imageView.setImageResource(kuva)
        return numero
    }
}