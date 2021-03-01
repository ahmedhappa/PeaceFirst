package com.peacefirst.ingedny.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.peacefirst.ingedny.base.BaseActivity
import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.databinding.ActivityMainBinding
import com.peacefirst.ingedny.viewmodles.MainViewModel

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding
    private var currentFragment: HomeFragmentsTypes = HomeFragmentsTypes.HOME
    private lateinit var navControllerFragment: NavController
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navControllerFragment = findNavController(R.id.fragment_nav_host_main)
        binding.bnvMain.selectedItemId = R.id.bottom_nav_item_home
        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_item_home -> {
                    if (currentFragment != HomeFragmentsTypes.HOME) {
                        navControllerFragment.navigateUp()
                        currentFragment = HomeFragmentsTypes.HOME
                    }
                    true
                }
                R.id.bottom_nav_item_about -> {
                    when (currentFragment) {
                        HomeFragmentsTypes.HOME -> navControllerFragment.navigate(R.id.action_homeFragment_to_aboutFragment)
                        HomeFragmentsTypes.INSTRUCTIONS -> navControllerFragment.navigate(R.id.action_instructionsFragment_to_aboutFragment)
                        else -> {
                        }
                    }
                    currentFragment = HomeFragmentsTypes.ABOUT
                    true
                }
                R.id.bottom_nav_item_instructions -> {
                    when (currentFragment) {
                        HomeFragmentsTypes.HOME -> navControllerFragment.navigate(R.id.action_homeFragment_to_instructionsFragment)
                        HomeFragmentsTypes.ABOUT -> navControllerFragment.navigate(R.id.action_aboutFragment_to_instructionsFragment)
                        else -> {
                        }
                    }
                    currentFragment = HomeFragmentsTypes.INSTRUCTIONS
                    true
                }
                else -> false
            }
        }
        viewModel.getInstructions()
        viewModel.getSocials()
    }

    override fun onBackPressed() {
        if (currentFragment != HomeFragmentsTypes.HOME) {
            binding.bnvMain.selectedItemId = R.id.bottom_nav_item_home
        } else {
            super.onBackPressed()
        }
    }

    private enum class HomeFragmentsTypes {
        HOME, ABOUT, INSTRUCTIONS
    }

}