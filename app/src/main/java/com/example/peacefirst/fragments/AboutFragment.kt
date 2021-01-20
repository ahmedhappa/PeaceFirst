package com.example.peacefirst.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.peacefirst.R
import com.example.peacefirst.adapters.SocialAdapter
import com.example.peacefirst.apputils.AppSharedPreference
import com.example.peacefirst.base.BaseFragment
import com.example.peacefirst.databinding.FragmentAboutBinding
import com.example.peacefirst.viewmodles.AboutViewModel

class AboutFragment : BaseFragment() {
    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        requireActivity().title = getString(R.string.str_about_us)
        binding.rvSocial.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rvSocial.adapter = SocialAdapter(viewModel.socials) { link ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            val chooser =
                Intent.createChooser(intent, getString(R.string.intent_chooser_open_with))
            startActivity(chooser)
        }

        return binding.root
    }
}