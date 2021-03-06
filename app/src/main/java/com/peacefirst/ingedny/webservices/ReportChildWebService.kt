package com.peacefirst.ingedny.webservices

import com.peacefirst.ingedny.models.BaseResponse
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