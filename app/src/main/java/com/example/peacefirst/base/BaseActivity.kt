package com.example.peacefirst.base

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.peacefirst.R
import com.example.peacefirst.apputils.DialogUtil

open class BaseActivity : AppCompatActivity() {
    private lateinit var fullScreenProgress: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenProgress =
            DialogUtil.createFullScreenProgressBlockingDialog(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun showFullProgressDialog() {
        if (!fullScreenProgress.isShowing)
            fullScreenProgress.show()
    }

    fun hideFullProgressDialog() {
        if (fullScreenProgress.isShowing)
            fullScreenProgress.dismiss()
    }

    protected fun showError(msg: String) {
        DialogUtil.showOkAlertDialog(this, msg)
    }
}