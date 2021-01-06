package com.example.peacefirst.webservices

import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.models.request.ReportChildRequest
import com.example.peacefirst.viewmodles.ReportChildViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ReportChildWebService {

    @Multipart
    @POST("createChildren")
    @JvmSuppressWildcards
    suspend fun reportChild(
        @PartMap request: Map<String, RequestBody>,
        @Part childImg: MultipartBody.Part
    ): BaseResponse<String>
}