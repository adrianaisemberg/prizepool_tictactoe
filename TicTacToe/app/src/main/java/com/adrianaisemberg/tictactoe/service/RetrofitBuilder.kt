package com.adrianaisemberg.tictactoe.service

import com.adrianaisemberg.tictactoe.settings.Settings
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

interface RetrofitBuilder {
    fun build(): Retrofit
}

// TODO: put in config
private const val URL = "http://192.168.86.40:5000/"

class RetrofitBuilderImpl(private val settings: Settings) : RetrofitBuilder {

    override fun build(): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                settings.authenticationKey?.let { key ->
                    requestBuilder.header("api-key", key)
                }
                chain.proceed(requestBuilder.build())
            }
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}
