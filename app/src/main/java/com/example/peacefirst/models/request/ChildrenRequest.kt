package com.example.peacefirst.models.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChildrenRequest(
    @SerializedName("report_type") var reportType: String?,
    @SerializedName("gender") var gender: String?,
    @SerializedName("min_age") var minAge: Short?,
    @SerializedName("max_age") var maxAge: Short?,
    @SerializedName("min_height") var minHeight: Short?,
    @SerializedName("max_height") var maxHeight: Short?,
    @SerializedName("skin_color") var skinColor: String?,
    @SerializedName("hair_color") var hairColor: String?,
    @SerializedName("eye_color") var eyeColor: String?,
) : Parcelable {
    constructor() : this(null, null, null, null, null, null, null, null, null)
}