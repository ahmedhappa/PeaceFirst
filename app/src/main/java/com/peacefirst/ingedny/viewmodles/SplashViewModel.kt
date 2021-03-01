package com.peacefirst.ingedny.viewmodles

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peacefirst.ingedny.base.BaseViewModel

class SplashViewModel : BaseViewModel() {
    private val _moveToNextScreen = MutableLiveData<Boolean>()
    val moveToNextScreen: LiveData<Boolean>
        get() = _moveToNextScreen

    private val countDownTime = 2000L //2 seconds
    private val countTick = 1000L //1 second each tick

    private val countDownTimer = object : CountDownTimer(countDownTime, countTick) {
        override fun onTick(p0: Long) {}

        override fun onFinish() {
            _moveToNextScreen.value = true
        }
    }

    init {
        countDownTimer.start()
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }
}