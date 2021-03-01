package com.peacefirst.ingedny.apputils

import android.content.Context
import com.peacefirst.ingedny.R
import com.peacefirst.ingedny.base.PeaceFirstApp
import com.peacefirst.ingedny.models.response.SocialResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

object AppSharedPreference {
    private val context = PeaceFirstApp.appContext
    private fun getSharedPreference() = context.getSharedPreferences(
        "peace_first_app",
        Context.MODE_PRIVATE
    )

    private fun getSharedPreferenceEditor() = getSharedPreference().edit()

    var appInstructions: String?
        set(value) = getSharedPreferenceEditor().putString(
            Constants.Preferences.PREF_APP_INSTRUCTIONS,
            value
        ).apply()
        get() = getSharedPreference().getString(
            Constants.Preferences.PREF_APP_INSTRUCTIONS, context.getString(
                R.string.pref_default_instructions_value
            )
        )

    fun storeSocialDataList(socialResponse: List<SocialResponse>?) {
        val dataToJson = Gson().toJson(socialResponse)
        getSharedPreferenceEditor().putString(Constants.Preferences.PREF_APP_Social, dataToJson)
            .apply()
    }

    fun getSocialDataList(): List<SocialResponse> {
        val data = getSharedPreference().getString(Constants.Preferences.PREF_APP_Social, null)
        var listOfSocial = listOf<SocialResponse>()
        try {
            if (data != null) {
                val type = object : TypeToken<List<SocialResponse>>() {}.type
                listOfSocial = Gson().fromJson(data, type)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return listOfSocial
    }
}
