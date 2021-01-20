package com.example.peacefirst.apputils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.peacefirst.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtil {

    fun createFullScreenProgressBlockingDialog(context: Context): Dialog {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.diaolg_loading, null)
        val dialog = Dialog(context, R.style.DialogDefault)
        dialog.apply {
            setContentView(view)
            setCancelable(false)
        }
        return dialog
    }

    fun createFullScreenImageDialog(context: Context, img: String): Dialog {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.dialog_image, null)
        val image: ImageView = view.findViewById(R.id.iv_dialog_image)
        image.load(img)
        val dialog = Dialog(context, R.style.DialogDefault)
        dialog.apply {
            setContentView(view)
            setCancelable(true)
        }
        return dialog
    }

    fun createSimpleFlexibleMaterialDialog(
        context: Context,
        title: String,
        msg: String?,
        posBtnText: String,
        posBtnAction: DialogInterface.OnClickListener,
        negBtnTest: String?,
        negBtnAction: DialogInterface.OnClickListener?,
        neutralBtnText: String?,
        neutralBtnAction: DialogInterface.OnClickListener?,
        items: Array<String>?,
        itemsClickListener: DialogInterface.OnClickListener?,
        isCancelable: Boolean = true
    ): AlertDialog {
        return MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton(posBtnText, posBtnAction)
            .setNegativeButton(negBtnTest, negBtnAction)
            .setNeutralButton(neutralBtnText, neutralBtnAction)
            .setSingleChoiceItems(items, -1, itemsClickListener)
            .setCancelable(isCancelable)
            .create()
    }

    fun showOkAlertDialog(
        context: Context,
        msg: String?
    ) {
        createSimpleFlexibleMaterialDialog(
            context,
            context.getString(R.string.str_error),
            msg,
            context.getString(R.string.str_Ok),
            { p0, _ -> p0.dismiss() },
            null,
            null,
            null,
            null,
            null,
            null,
            true
        ).show()
    }
}