package com.peacefirst.ingedny.repos

import com.peacefirst.ingedny.base.BaseApi
import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.models.response.SocialResponse
import com.peacefirst.ingedny.webservices.MainWebService

class MainRepo {
    private val webService = BaseApi.getWebService(MainWebService::class.java)

    suspend fun getInstructions(): BaseResponse<String> {
        return webService.getInstructions()
    }

    suspend fun getSocials(): BaseResponse<List<SocialResponse>> {
        return webService.getSocials()
    }
}