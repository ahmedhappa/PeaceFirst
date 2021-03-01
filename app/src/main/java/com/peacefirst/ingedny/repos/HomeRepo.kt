package com.peacefirst.ingedny.repos

import com.peacefirst.ingedny.base.BaseApi
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.models.request.ChildrenRequest
import com.peacefirst.ingedny.models.response.ChildrenResponse
import com.peacefirst.ingedny.webservices.HomeWebService

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