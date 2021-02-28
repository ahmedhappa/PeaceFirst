package com.example.peacefirst.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peacefirst.models.BaseResponse
import com.example.peacefirst.base.BaseViewModel
import com.example.peacefirst.models.response.ChildDetailsResponse
import com.example.peacefirst.repos.ChildDetailsRepo
import com.example.peacefirst.base.Result

class ChildDetailsViewModel : BaseViewModel() {
    private val childDetailsRepo = ChildDetailsRepo()
    private val _childDetailsResponseMutableLiveData =
        MutableLiveData<Result<BaseResponse<ChildDetailsResponse>>>()
    val childDetailsResponseLiveData: LiveData<Result<BaseResponse<ChildDetailsResponse>>>
        get() = _childDetailsResponseMutableLiveData
    private val _callNumberMutableLiveData =
        MutableLiveData<Result<BaseResponse<Int>>>()

    fun getChildDetails(childId: Int) {
        callApi(_childDetailsResponseMutableLiveData) {
            childDetailsRepo.getChildDetails(childId)
        }
    }

    fun callNumberAnalytics(childId: Int) {
        callApi(_callNumberMutableLiveData) {
            childDetailsRepo.callNumberAnalytics(childId)
        }
    }
}