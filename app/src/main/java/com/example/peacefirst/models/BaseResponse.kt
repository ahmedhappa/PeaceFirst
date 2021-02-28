package com.example.peacefirst.models

import com.google.gson.annotations.SerializedName

open class BaseResponse<T> {
    @SerializedName("status")
    var statusCode: Int = 0

    @SerializedName("STATUS_DESCRIPTION", alternate = ["success"])
    var statusDescription: String? = null

    @SerializedName("data")
    var data: T? = null
}