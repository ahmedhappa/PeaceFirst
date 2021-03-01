package com.peacefirst.ingedny.base

sealed class Result<out T> {
    data class Success<out T>(val response: T) : Result<T>()
    data class Error(val exception: BaseExceptionHandler.ExceptionTypes) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Complete : Result<Nothing>()
}