package com.adrianaisemberg.tictactoe.service

import com.adrianaisemberg.tictactoe.settings.Settings
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    // TODO: put in config
    private const val URL = "http://192.168.86.40:5000/"

    @Provides
    fun provideRetrofit(settings: Settings): Retrofit {
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

    @Provides
    fun provideTicTacToeService(retrofit: Retrofit): TicTacToeService =
        retrofit.create(TicTacToeService::class.java)
}