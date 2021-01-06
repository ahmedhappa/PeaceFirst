package com.example.peacefirst.base

import androidx.fragment.app.Fragment
import com.example.peacefirst.apputils.DialogUtil

open class BaseFragment : Fragment() {

    protected fun showFullProgressDialog() {
        (activity as BaseActivity).showFullProgressDialog()
    }

    protected fun hideFullProgressDialog() {
        (activity as BaseActivity).hideFullProgressDialog()
    }

    protected fun showError(msg: String) {
        DialogUtil.showOkAlertDialog(requireActivity(), msg)
    }
}