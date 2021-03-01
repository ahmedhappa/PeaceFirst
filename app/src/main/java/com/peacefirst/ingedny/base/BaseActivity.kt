package com.peacefirst.ingedny.base

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.peacefirst.ingedny.apputils.DialogUtil

open class BaseActivity(private val hideToolBar: Boolean = false) : AppCompatActivity() {
    private lateinit var fullScreenProgress: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenProgress =
            DialogUtil.createFullScreenProgressBlockingDialog(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        if (hideToolBar) {
            supportActionBar?.hide()
        } else {
            // to remove line between activity and action bar
            supportActionBar?.elevation = 0F
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}