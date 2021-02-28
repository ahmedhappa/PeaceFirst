package com.example.peacefirst.models.response

import com.example.peacefirst.models.ModelEnums
import com.google.gson.annotations.SerializedName

data class ChildrenResponse(
    @SerializedName("id") val childId: Int,
    @SerializedName("img") val img: String,
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("name_en") val nameEn: String?,
    @SerializedName("age") val age: Short,
    @SerializedName("report_type") val reportType: ModelEnums.ReportType
)