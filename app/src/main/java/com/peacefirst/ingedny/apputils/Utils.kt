package com.peacefirst.ingedny.apputils

import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.base.PeaceFirstApp

object Utils {
    val context = PeaceFirstApp.appContext

    object AutoCompleteTextList {
        val SKIN_COLOR_LIST: Array<String> = context.resources.getStringArray(R.array.act_skin_color)
        val HAIR_COLOR_LIST: Array<String> = context.resources.getStringArray(R.array.act_hair_color)
        val EYE_COLOR_LIST : Array<String> = context.resources.getStringArray(R.array.act_eye_color)
    }

}