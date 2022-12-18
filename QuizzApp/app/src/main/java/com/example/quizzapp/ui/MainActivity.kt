package com.example.quizzapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.MyApplication
import com.example.quizzapp.R
import com.example.quizzapp.viewModels.QuizViewModel
import com.example.quizzapp.viewModels.QuizViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: QuizViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, QuizViewModelFactory((application as MyApplication).repo)).get(
                QuizViewModel::class.java
            )
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();

    }
}
