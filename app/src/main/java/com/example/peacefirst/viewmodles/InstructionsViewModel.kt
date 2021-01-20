package com.example.peacefirst.viewmodles

import com.example.peacefirst.apputils.AppSharedPreference
import com.example.peacefirst.base.BaseViewModel

class InstructionsViewModel : BaseViewModel() {
    val appInstructions = AppSharedPreference.appInstructions
}