package com.example.quizzapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.model.Repo

class QuizViewModel(repo: Repo):ViewModel() {
    val cn = MutableLiveData<Int>(0)
    val listSize = repo.data.size
}