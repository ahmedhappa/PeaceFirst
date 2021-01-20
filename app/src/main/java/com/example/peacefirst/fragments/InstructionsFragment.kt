package com.example.peacefirst.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.peacefirst.R
import com.example.peacefirst.base.BaseFragment
import com.example.peacefirst.databinding.FragmentInstructionsBinding
import com.example.peacefirst.viewmodles.InstructionsViewModel

class InstructionsFragment : BaseFragment() {

    private lateinit var binding: FragmentInstructionsBinding
    private val viewModel: InstructionsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        requireActivity().title = getString(R.string.title_instruction_fragment)
        binding.tvInstructions.movementMethod = ScrollingMovementMethod()
        binding.tvInstructions.text = viewModel.appInstructions
        return binding.root
    }
}