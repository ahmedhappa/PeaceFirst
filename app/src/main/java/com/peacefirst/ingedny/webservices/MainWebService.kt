package com.peacefirst.ingedny.webservices

import com.peacefirst.ingedny.models.BaseResponse
import com.peacefirst.ingedny.models.response.SocialResponse
import retrofit2.http.GET

interface MainWebService {
    @GET("instructions")
    suspend fun getInstructions(): BaseResponse<String>

    @GET("socials")
    suspend fun getSocials(): BaseResponse<List<SocialResponse>>
}