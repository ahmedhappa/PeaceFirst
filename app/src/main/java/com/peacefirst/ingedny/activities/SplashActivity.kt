package com.peacefirst.ingedny.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import com.peacefirst.ingedny.base.BaseActivity
import com.peacefirst.ingedny.databinding.ActivitySplashBinding
import com.peacefirst.ingedny.viewmodles.SplashViewModel

class SplashActivity : BaseActivity(true) {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.moveToNextScreen.observe(this, {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }
}