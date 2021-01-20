package com.example.peacefirst.apputils

import androidx.core.content.ContextCompat
import com.example.peacefirst.R
import com.example.peacefirst.base.PeaceFirstApp

object Utils {
    val context = PeaceFirstApp.appContext

    object AutoCompleteTextList {
        val SKIN_COLOR_LIST: Array<String> = context.resources.getStringArray(R.array.act_skin_color)
        val HAIR_COLOR_LIST: Array<String> = context.resources.getStringArray(R.array.act_hair_color)
        val EYE_COLOR_LIST : Array<String> = context.resources.getStringArray(R.array.act_eye_color)
    }

}