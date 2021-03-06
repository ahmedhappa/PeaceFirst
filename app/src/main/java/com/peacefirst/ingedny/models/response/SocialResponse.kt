package com.peacefirst.ingedny.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SocialResponse(
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("icon") val icon: String
) : Parcelable