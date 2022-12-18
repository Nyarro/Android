package com.example.quizzapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentQuestionsBinding
import com.example.quizzapp.viewModels.QuizViewModel

class QuestionsFragment : Fragment() {
    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = (activity as MainActivity).viewModel
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupViews()
    }

    private fun setupViews() {
        binding.prevButton.setOnClickListener {
            viewModel.saveUserAnswers()
            viewModel.loadPrevQuestion()

        }
        binding.allTextView.text = viewModel.getQuiestionsAmount().toString()
        viewModel.loadCurrentQuestion()
        binding.radios.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.firstRB -> {
                    viewModel.userAnswer = 1
                }
                R.id.secondRB -> {
                    viewModel.userAnswer = 2
                }
                R.id.thirdRB -> {
                    viewModel.userAnswer = 3
                }
                R.id.fourthRB -> {
                    viewModel.userAnswer = 4
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.currentQuestionsId.observe(viewLifecycleOwner) { questionNumber ->
            binding.prevButton.isEnabled = questionNumber != 0
            if (questionNumber == viewModel.getQuiestionsAmount() - 1) {
                binding.nextButton.text = "Finish"
                binding.nextButton.setOnClickListener {
                    findNavController().navigate(R.id.action_questionsFragment_to_quizResultFragment)
                }
            } else {
                binding.nextButton.text = ">"
                binding.nextButton.setOnClickListener {
                    viewModel.saveUserAnswers()
                    viewModel.loadNextQuestion()
                }
            }
            binding.currentTextView.text = (questionNumber + 1).toString()
            setUpAnswers(questionNumber)
        }
        viewModel.currentQuestions.observe(viewLifecycleOwner) { question ->
            binding.questionsTextView.text = question
            loadUserAnswerIfExistAtQuestion(viewModel.currentQuestionsId.value!!)
        }
    }

    private fun loadUserAnswerIfExistAtQuestion(questionNumber: Int) {
        when (viewModel.userAnswers[questionNumber]) {
            1 -> {
                binding.radios.check(R.id.firstRB)
                viewModel.userAnswer = 1
            }
            2 -> {
                binding.radios.check(R.id.secondRB)
                viewModel.userAnswer = 2
            }
            3 -> {
                binding.radios.check(R.id.thirdRB)
                viewModel.userAnswer = 3
            }
            4 -> {
                binding.radios.check(R.id.fourthRB)
                viewModel.userAnswer = 4
            }
            else -> {
                binding.radios.clearCheck()
                viewModel.userAnswer = -1
            }
        }
    }


    private fun setUpAnswers(questionNumber: Int) {
        val answers = viewModel.LoadAnswers(questionNumber)
        binding.firstRB.text = answers[0]
        binding.secondRB.text = answers[1]
        binding.thirdRB.text = answers[2]
        binding.fourthRB.text = answers[3]

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}