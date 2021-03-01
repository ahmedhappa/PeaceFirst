package com.peacefirst.ingedny.base

import androidx.fragment.app.Fragment
import com.peacefirst.ingedny.apputils.DialogUtil
import com.peacefirst.ingedny.base.BaseActivity

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