package erick.labrada.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import erick.labrada.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    var maxValue=100
    var minValue= 0
    var num: Int = 0
    var won: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val guessing: TextView = findViewById(R.id.guessing)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            updateGuessing(guessing)
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue=num
            if(checkLimits()){
                updateGuessing(guessing)
            } else {
                guessing.setText("Winner Winner, Chicken Dinner")
            }
        }

        down.setOnClickListener {
            maxValue=num
            if(checkLimits()){
                updateGuessing(guessing)
            } else {
                guessing.setText("Winner Winner, Chicken Dinner")
            }
        }

        guessed.setOnClickListener {
            if(!won){
                guessing.setText("I guessed ur number, was it"+num+"?")
                guessed.setText("Wanna play again?")
                won=true
            } else{
                generate.visibility=View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility=View.GONE
                resetValues()
            }

        }

    }

    fun resetValues(){
        minValue=0
        maxValue=100
        num=0;
        won=false
    }

    fun checkLimits(): Boolean{
        return  minValue!=maxValue
    }

    private fun updateGuessing(guessing: TextView){
        num = Random.nextInt(minValue, maxValue)
        guessing.setText(num.toString())

    }
}