package com.example.peacefirst.repos

import com.example.peacefirst.base.BaseApi
import com.example.peacefirst.models.BaseResponse
import com.example.peacefirst.models.request.ChildrenRequest
import com.example.peacefirst.models.response.ChildrenResponse
import com.example.peacefirst.webservices.HomeWebService

class HomeRepo {
    private val webService = BaseApi.getWebService(HomeWebService::class.java)

    suspend fun getAllChildren(childrenRequest: ChildrenRequest,page:Int): BaseResponse<MutableList<ChildrenResponse>> {
        return webService.getAllChildren(
            childrenRequest.minAge,
            childrenRequest.maxAge,
            childrenRequest.gender,
            childrenRequest.eyeColor,
            childrenRequest.skinColor,
            childrenRequest.hairColor,
            childrenRequest.minHeight,
            childrenRequest.maxHeight,
            childrenRequest.reportType,
            page
        )
    }
}