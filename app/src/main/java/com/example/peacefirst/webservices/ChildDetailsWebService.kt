package com.example.peacefirst.webservices

import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.models.response.ChildDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChildDetailsWebService {
    @GET("childrenDetails")
    suspend fun getChildDetails(@Query("id") childId: Int): BaseResponse<ChildDetailsResponse>

    @GET("increseAnalytics")
    suspend fun callNumberAnalytics(@Query("id") childId: Int) : BaseResponse<Int>
}