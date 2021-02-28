package com.example.peacefirst.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peacefirst.models.BaseResponse
import com.example.peacefirst.base.BaseViewModel
import com.example.peacefirst.base.Result
import com.example.peacefirst.models.request.ReportChildRequest
import com.example.peacefirst.repos.ReportChildRepo

class ReportChildViewModel : BaseViewModel() {
    private val reportChildRepo = ReportChildRepo()
    private val _reportChildMutableLiveData = MutableLiveData<Result<BaseResponse<String>>>()
    val reportChildLiveData: LiveData<Result<BaseResponse<String>>>
        get() = _reportChildMutableLiveData

    fun reportChild(reportChildRequest: ReportChildRequest) {
        callApi(_reportChildMutableLiveData) {
            reportChildRepo.reportChild(reportChildRequest)
        }
    }

}