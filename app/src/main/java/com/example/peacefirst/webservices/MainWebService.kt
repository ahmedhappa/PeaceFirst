package com.example.peacefirst.webservices

import com.example.peacefirst.models.BaseResponse
import com.example.peacefirst.models.response.SocialResponse
import retrofit2.http.GET

interface MainWebService {
    @GET("instructions")
    suspend fun getInstructions(): BaseResponse<String>

    @GET("socials")
    suspend fun getSocials(): BaseResponse<List<SocialResponse>>
}