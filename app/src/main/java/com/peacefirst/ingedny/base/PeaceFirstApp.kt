package com.peacefirst.ingedny.base

import android.app.Application
import android.content.Context

class PeaceFirstApp : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}