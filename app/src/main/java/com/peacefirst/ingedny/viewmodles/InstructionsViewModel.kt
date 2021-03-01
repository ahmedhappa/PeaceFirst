package com.peacefirst.ingedny.viewmodles

import com.peacefirst.ingedny.apputils.AppSharedPreference
import com.peacefirst.ingedny.base.BaseViewModel

class InstructionsViewModel : BaseViewModel() {
    val appInstructions = AppSharedPreference.appInstructions
}