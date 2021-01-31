package com.example.peacefirst.models

import com.google.gson.annotations.SerializedName

object ModelEnums {
    enum class Gender {
        @SerializedName("Male")
        Male,

        @SerializedName("Female")
        Female
    }

    enum class ReportType {
        @SerializedName("Found")
        Found,
        @SerializedName("Missing")
        Missing
    }
}