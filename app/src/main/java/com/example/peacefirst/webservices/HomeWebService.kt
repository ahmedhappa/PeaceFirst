package com.example.peacefirst.webservices

import com.example.peacefirst.models.BaseResponse
import com.example.peacefirst.models.response.ChildrenResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeWebService {
    @GET("childrens")
    suspend fun getAllChildren(
        @Query("min_age") minAge: Short?,
        @Query("max_age") maxAge: Short?,
        @Query("gender") gender: String?,
        @Query("eye_color") eyeColor: String?,
        @Query("skin_color") skinColor: String?,
        @Query("hair_color") hairColor: String?,
        @Query("min_height") minHeight: Short?,
        @Query("max_height") maxHeight: Short?,
        @Query("report_type") reportType: String?,
        @Query("page") page: Int
    ): BaseResponse<MutableList<ChildrenResponse>>
}