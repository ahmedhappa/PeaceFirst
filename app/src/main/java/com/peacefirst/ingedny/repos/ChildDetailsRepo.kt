package com.peacefirst.ingedny.repos

import com.peacefirst.ingedny.base.BaseApi
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.models.response.ChildDetailsResponse
import com.peacefirst.ingedny.webservices.ChildDetailsWebService

class ChildDetailsRepo {
    private val webService = BaseApi.getWebService(ChildDetailsWebService::class.java)

    suspend fun getChildDetails(childId: Int): BaseResponse<ChildDetailsResponse> {
        return webService.getChildDetails(childId)
    }

    suspend fun callNumberAnalytics(childId: Int) : BaseResponse<Int> {
        return webService.callNumberAnalytics(childId)
    }
}