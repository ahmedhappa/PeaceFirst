package com.peacefirst.ingedny.viewmodles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.base.BaseViewModel
import com.peacefirst.ingedny.base.Result
import com.peacefirst.ingedny.models.request.ReportChildRequest
import com.peacefirst.ingedny.repos.ReportChildRepo

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