package com.peacefirst.ingedny.base

import android.accounts.NetworkErrorException
import com.peacefirst.ingedny.R
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.Exception

object BaseExceptionHandler {
    private val context = PeaceFirstApp.appContext

    fun handleException(t: Throwable): ExceptionTypes {
        return when (t) {
            is HttpException, is JsonParseException, is MalformedJsonException -> ExceptionTypes.ApiException(
                context.getString(R.string.str_api_error_msg)
            )
            is SocketTimeoutException, is SocketException, is UnknownHostException, is NetworkErrorException -> ExceptionTypes.NetworkException(
                context.getString(
                    R.string.str_network_error
                )
            )
            else -> ExceptionTypes.UnKnownException(context.getString(R.string.str_something_wrong))
        }
    }

    sealed class ExceptionTypes(val msg: String) : Exception(msg) {
        class ApiException(val apiExceptionMessage: String) : ExceptionTypes(apiExceptionMessage)
        class NetworkException(val networkExceptionMessage: String) :
            ExceptionTypes(networkExceptionMessage)

        class UnKnownException(val unKnownExceptionMessage: String) :
            ExceptionTypes(unKnownExceptionMessage)
    }
}