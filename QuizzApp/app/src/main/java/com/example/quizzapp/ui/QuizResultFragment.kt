package com.example.quizzapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentQuestionsBinding
import com.example.quizzapp.databinding.FragmentQuizResultBinding
import com.example.quizzapp.viewModels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizResultFragment : Fragment() {
    private val viewModel: QuizViewModel by activityViewModels()

    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {
        with(binding)
        {
            currentTextView.text = viewModel.getRightAmount().toString()
            allTextView.text = viewModel.getQuiestionsAmount().toString()
            commentTextView.text = viewModel.getComment()

            exitButton.setOnClickListener {
                requireActivity().finish()
            }
            restartButton.setOnClickListener {
                viewModel.restartQuiz()
                findNavController().popBackStack()
            }
            shareButton.setOnClickListener {
                val myIntent = Intent(Intent.ACTION_SEND)
                myIntent.type = "text/plain"
                myIntent.putExtra(Intent.EXTRA_TEXT, viewModel.getResultMessage())
                startActivity(Intent.createChooser(myIntent, "Share with"))

            }
        }
    }
}
