package com.peacefirst.ingedny.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

object ModelEnums {
    @Keep
    enum class Gender {
        @SerializedName("Male")
        Male,

        @SerializedName("Female")
        Female
    }

    @Keep
    enum class ReportType {
        @SerializedName("Found")
        Found,

        @SerializedName("Missing")
        Missing
    }
}