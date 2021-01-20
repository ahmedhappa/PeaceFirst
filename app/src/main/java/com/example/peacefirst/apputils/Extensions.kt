package com.example.peacefirst.apputils

import androidx.lifecycle.MutableLiveData

object Extensions {
    fun <T> MutableLiveData<T>.notifyObserver(){
        this.value = this.value
    }
}