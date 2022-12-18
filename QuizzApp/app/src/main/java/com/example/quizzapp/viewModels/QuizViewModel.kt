package com.example.quizzapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.model.Repo

class QuizViewModel(private val repo: Repo):ViewModel() {
    val currentQuestionsId = MutableLiveData<Int>(0)
    val currentQuestions = MutableLiveData<String>()
    var userAnswer = -1
    var userAnswers= Array<Int>(getQuiestionsAmount()) {0}
    fun getQuiestionsAmount() = repo.data.size
    fun loadNextQuestion() {
        currentQuestionsId.value =  currentQuestionsId.value!! +1
        currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }
    fun loadPrevQuestion() {
        currentQuestionsId.value =  currentQuestionsId.value!! -1
        currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }

    fun saveUserAnswers() {
        userAnswers[currentQuestionsId.value!!] = userAnswer
        if (userAnswer != -1) {
            userAnswer= -1
        }
    }

    fun LoadAnswers(questionNumber: Int): ArrayList<String>  {
        return repo.answersAsVariants[questionNumber]

    }

    fun loadCurrentQuestion() {
     currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }
}