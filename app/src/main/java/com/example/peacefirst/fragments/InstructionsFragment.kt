package com.example.peacefirst.fragments

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.peacefirst.R
import com.example.peacefirst.apputils.AppSharedPreference
import com.example.peacefirst.base.BaseFragment
import com.example.peacefirst.databinding.FragmentInstructionsBinding

class InstructionsFragment : BaseFragment() {

    private lateinit var binding: FragmentInstructionsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        requireActivity().title = getString(R.string.title_instruction_fragment)
        binding.tvInstructions.movementMethod = ScrollingMovementMethod()
        binding.tvInstructions.text = AppSharedPreference.appInstructions
        return binding.root
    }
}