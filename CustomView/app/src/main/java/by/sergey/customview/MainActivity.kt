package by.sergey.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sadEmotionalFace = findViewById<EmotionalFace>(R.id.sadEmotionalFace)
        val happyEmotionalFace = findViewById<EmotionalFace>(R.id.happyEmotionalFace)
        val neutralEmotionalFace = findViewById<EmotionalFace>(R.id.neutralEmotionalFace)
        val userEmotionalFace = findViewById<EmotionalFace>(R.id.userEmotionalFace)


        happyEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.HAPPY
        }
        neutralEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.NEUTRAL
        }
        sadEmotionalFace.setOnClickListener {
            userEmotionalFace.happinessState = EmotionalFace.SAD
        }

    }
}