package com.example.peacefirst.models.request

import android.graphics.Bitmap

data class ReportChildRequest(
    val childNameAr: String,
    val childNameEn: String?,
    val gender: String,
    val age: Short,
    val height: Short,
    val skinColor: String,
    val hairColor: String,
    val eyeColor: String,
    val childImg: Bitmap,
    val childImgName: String,
    val childMissedPlace: String,
    val childCurrentPlace: String?,
    val reportType: String,
    val reporter: Reporter
) {
    data class Reporter(
        val name: String,
        val phone: String,
        val address: String,
        val nationalId: String
    )
}