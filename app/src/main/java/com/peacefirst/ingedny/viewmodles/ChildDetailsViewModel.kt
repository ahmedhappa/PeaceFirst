package com.peacefirst.ingedny.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.base.BaseViewModel
import com.peacefirst.ingedny.models.response.ChildDetailsResponse
import com.peacefirst.ingedny.repos.ChildDetailsRepo
import com.peacefirst.ingedny.base.Result

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