package com.example.peacefirst.base

import com.example.peacefirst.BuildConfig
import com.example.peacefirst.apputils.Server.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object BaseApi {
    private const val TIMEOUT_VALUE: Long = 5L
    fun <T> getWebService(webServiceInterface: Class<T>): T {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)


        if (BuildConfig.DEBUG) {
            val httpLogInterceptor = HttpLoggingInterceptor()
            httpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpClient.addInterceptor(httpLogInterceptor)
        }

        val client = okHttpClient.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        return retrofit.create(webServiceInterface)
    }
}