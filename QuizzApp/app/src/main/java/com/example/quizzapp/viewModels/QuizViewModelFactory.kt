package com.example.quizzapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.model.Repo

class QuizViewModelFactory constructor(private val repo: Repo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            QuizViewModel(this.repo) as T
        } else {
            throw  java.lang.IllegalArgumentException("ViewModel not found")
        }
    }
}