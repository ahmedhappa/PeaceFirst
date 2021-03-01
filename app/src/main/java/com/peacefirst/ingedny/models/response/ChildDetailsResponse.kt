package com.peacefirst.ingedny.models.response

import com.peacefirst.ingedny.models.ModelEnums
import com.google.gson.annotations.SerializedName

data class ChildDetailsResponse(
    @SerializedName("name_ar") val nameAr: String,
    @SerializedName("name_en") val nameEn: String?,
    @SerializedName("img") val image: String,
    @SerializedName("report_type") val childReportType: ModelEnums.ReportType,
    @SerializedName("age") val age: String,
    @SerializedName("height") val height: String,
    @SerializedName("children_type") val gender: ModelEnums.Gender,
    @SerializedName("skin_color") val skinColor: String,
    @SerializedName("hair_color") val hairColor: String,
    @SerializedName("eye_color") val eyeColor: String,
    @SerializedName("missed_place") val place: String,
    @SerializedName("reporter_name") val reporterName: String,
    @SerializedName("reporter_phone") val reporterPhone: String,
    @SerializedName("reporter_address") val reporterAddress: String,
    @SerializedName("reporter_nationality_id") val reporterNationalId: String
)