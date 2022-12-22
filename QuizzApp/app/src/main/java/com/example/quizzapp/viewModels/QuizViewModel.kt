package com.example.quizzapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repo: Repo) : ViewModel() {
    val currentQuestionsId = MutableLiveData<Int>(0)
    val currentQuestions = MutableLiveData<String>()
    var userAnswer = -1
    var userAnswers = Array<Int>(getQuiestionsAmount()) { 0 }
    fun getQuiestionsAmount() = repo.data.size

    fun loadNextQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! + 1
        currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }

    fun loadPrevQuestion() {
        currentQuestionsId.value = currentQuestionsId.value!! - 1
        currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }

    fun saveUserAnswers() {
        userAnswers[currentQuestionsId.value!!] = userAnswer
        if (userAnswer != -1) {
            userAnswer = -1
        }
    }

    fun LoadAnswers(questionNumber: Int): ArrayList<String> {
        return repo.answersAsVariants[questionNumber]

    }

    fun loadCurrentQuestion() {
        currentQuestions.value = repo.data[currentQuestionsId.value!!]
    }

    fun getRightAmount(): Int {
        var correctAnswers = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == repo.anserwsId[i]) {
                correctAnswers++
            }
        }
        return correctAnswers
    }

    fun getComment(): CharSequence? {
        val res = getRightAmount()
        if (res > getQuiestionsAmount() * 2 / 3) {
            return "nice job"
        } else if (res > getQuiestionsAmount() * 1 / 3) {
            return "well, try more"
        } else {
            return "You should try again"
        }
    }

    fun restartQuiz() {
        currentQuestionsId.postValue(0)
        userAnswer = -1
        for (i in userAnswers.indices) {
            userAnswers[i] = -1
        }
    }

    fun getResultMessage(): String {
        return  "Hey, take challenge in quiz, where i got ${getRightAmount()}/${getQuiestionsAmount()}!"

    }
}