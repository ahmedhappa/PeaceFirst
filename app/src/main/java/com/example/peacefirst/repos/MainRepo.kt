package com.example.peacefirst.repos

import com.example.peacefirst.base.BaseApi
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.models.response.SocialResponse
import com.example.peacefirst.webservices.MainWebService

class MainRepo {
    private val webService = BaseApi.getWebService(MainWebService::class.java)

    suspend fun getInstructions(): BaseResponse<String> {
        return webService.getInstructions()
    }

    suspend fun getSocials(): BaseResponse<List<SocialResponse>> {
        return webService.getSocials()
    }
}