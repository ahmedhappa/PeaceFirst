package com.example.peacefirst.repos

import android.graphics.Bitmap
import com.example.peacefirst.base.BaseApi
import com.example.peacefirst.base.BaseResponse
import com.example.peacefirst.models.request.ReportChildRequest
import com.example.peacefirst.webservices.ReportChildWebService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class ReportChildRepo {
    private val wevService = BaseApi.getWebService(ReportChildWebService::class.java)

    suspend fun reportChild(reportChildRequest: ReportChildRequest): BaseResponse<String> {
        reportChildRequest.apply {
            val childRequestMap = mutableMapOf(
                "child_name_ar" to stringToRequestBody(childNameAr),
                "gender" to stringToRequestBody(gender),
                "age" to stringToRequestBody(age.toString()),
                "height" to stringToRequestBody(height.toString()),
                "skin_color" to stringToRequestBody(skinColor),
                "hair_color" to stringToRequestBody(hairColor),
                "eye_color" to stringToRequestBody(eyeColor),
                "child_missed_place" to stringToRequestBody(childMissedPlace),
                "report_type" to stringToRequestBody(reportType),
                "reporter_name" to stringToRequestBody(reporter.name),
                "reporter_phone" to stringToRequestBody(reporter.phone),
                "reporter_address" to stringToRequestBody(reporter.address),
                "reporter_national_id" to stringToRequestBody(reporter.nationalId)
            )

            if (!childNameEn.isNullOrEmpty())
                childRequestMap["child_name_en"] = stringToRequestBody(childNameEn)
            if (!childCurrentPlace.isNullOrEmpty())
                childRequestMap["child_current_place"] = stringToRequestBody(childCurrentPlace)

            return wevService.reportChild(
                childRequestMap.toMap(),
                imgRequestBodyToMultiPart(imageToRequestBody(childImg), childImgName)
            )
        }
    }

    private fun stringToRequestBody(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    private fun imgRequestBodyToMultiPart(
        imgRequestBody: RequestBody,
        imgName: String
    ): MultipartBody.Part {
        return MultipartBody.Part.createFormData("child_img", imgName, imgRequestBody)
    }

    private fun imageToRequestBody(img: Bitmap): RequestBody {
        val value = getByteArrayFromBitmap(img)
        return value.toRequestBody("image/*".toMediaTypeOrNull())
    }

    private fun getByteArrayFromBitmap(bitmap: Bitmap): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

}