package com.peacefirst.ingedny.apputils

import androidx.lifecycle.MutableLiveData

object Extensions {
    fun <T> MutableLiveData<T>.notifyObserver(){
        this.value = this.value
    }
}