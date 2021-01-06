package com.example.peacefirst.repos

import com.example.peacefirst.base.BaseApi
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.models.response.ChildDetailsResponse
import com.example.peacefirst.webservices.ChildDetailsWebService

class ChildDetailsRepo {
    private val webService = BaseApi.getWebService(ChildDetailsWebService::class.java)

    suspend fun getChildDetails(childId: Int): BaseResponse<ChildDetailsResponse> {
        return webService.getChildDetails(childId)
    }

    suspend fun callNumberAnalytics(childId: Int) :BaseResponse<Int> {
        return webService.callNumberAnalytics(childId)
    }
}