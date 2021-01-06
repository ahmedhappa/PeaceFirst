package com.example.peacefirst.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    protected fun <T> callApi(mutableLiveData: MutableLiveData<Result<T>>, call: suspend () -> T) {
        viewModelScope.launch {
            mutableLiveData.value = Result.Loading
            try {
                val response = call.invoke()
                mutableLiveData.value = Result.Success(response)
            } catch (t: Throwable) {
                mutableLiveData.value = Result.Error(BaseExceptionHandler.handleException(t))
            } finally {
                mutableLiveData.value = Result.Complete
            }
        }
    }
}