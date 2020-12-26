package com.example.peacefirst.models

import com.google.gson.annotations.SerializedName

data class ChildrenResponse(
    @SerializedName("children") val children: List<Child>
) {
    data class Child(
        @SerializedName("child_img") val img: String,
        @SerializedName("child_name") val name: String,
        @SerializedName("child_age") val age: Short,
        @SerializedName("child_gender") val gender: Gender,
        @SerializedName("report_type") val reportType: String
    ){
        enum class Gender{
            MALE,FEMALE
        }
    }
}