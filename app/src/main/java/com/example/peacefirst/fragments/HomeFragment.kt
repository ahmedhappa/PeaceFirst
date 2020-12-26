package com.example.peacefirst.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.peacefirst.base.BaseFragment
import com.example.peacefirst.R
import com.example.peacefirst.adapters.ChildrenAdapter
import com.example.peacefirst.databinding.FragmentHomeBinding
import com.example.peacefirst.models.ChildrenResponse

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        val temp = listOf(ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.FEMALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.FEMALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"),
            ChildrenResponse.Child("https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631230503683E01_DXXX.jpg","ali",10,ChildrenResponse.Child.Gender.MALE,"Missing"))

        binding.rvHomeList.adapter = ChildrenAdapter(temp)

        return binding.root
    }
}